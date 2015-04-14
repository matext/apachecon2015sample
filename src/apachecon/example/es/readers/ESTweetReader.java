package apachecon.example.es.readers;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchScroll;
import io.searchbox.params.Parameters;

import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.util.Logger;
import org.apache.uima.util.Progress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import apachecon.example.es.types.Tweet;
import apachecon.example.es.types.Verb;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * Pulls from the Elastic Search Index.  Creates a Span / Scroll Id in the index, then 
 * continues to pull from the elastic search service until there are no more objects
 * to pull
 * @author neal
 *
 */
public class ESTweetReader extends CollectionReader_ImplBase {


    public static final String INDEX = "indexName";
    @ConfigurationParameter(name = INDEX)
    private String indexName;
    private JestClient client; 
    private Logger logger;
    private String scrollId; // Batch Processing for Elastic Search
    private String currResult; 
    private JsonArray currArray;

    @Override
    public void initialize(){
        logger = getUimaContext().getLogger();

        /* initialize Jest client */
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                .build());

        client = factory.getObject();
        indexName = (String) getConfigParameterValue(INDEX);

        /* build search query for scrolling through 1 document at a time */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString())
        .addIndex(indexName)
        .addType("sentiment")
        .setParameter(Parameters.SIZE, 1)
        .setParameter(Parameters.SCROLL, "5m")
        .build();

        try {
            JestResult result = client.execute(search);
            scrollId = result.getJsonObject().get("_scroll_id").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getNext(CAS cas ) throws IOException, CollectionException {

        /* pull out the text information from the cas */
        JsonObject json = currArray.get(0).getAsJsonObject().getAsJsonObject("_source");
        String id = currArray.get(0).getAsJsonObject().get("_id").toString();
        id = id.substring(1, id.length() - 1); // strip out \"

        String tweet = json.get("tweet").toString();

        tweet = tweet.substring(1, tweet.length() - 1);
        Float averageSentiment  = json.get("average_sentiment").getAsFloat();
        cas.setDocumentText(tweet);
        
        /**
         * Recreate the CAS object.  This is handled now through Uima 2.7.0 
         * JsonCasDeserializer, but for now, just manually build
         */
        Tweet tweetAnnot;
        try {
            tweetAnnot = new Tweet(cas.getJCas());
            tweetAnnot.setBegin(0);
            tweetAnnot.setEnd(tweet.length());
            tweetAnnot.setAverageSentiment(averageSentiment);
            tweetAnnot.setEsId(id);
            tweetAnnot.addToIndexes();
            JsonArray verbArray = json.get("verbs").getAsJsonArray();
            for (JsonElement j : verbArray){
                String verb = j.getAsJsonObject().get("verb").toString();
                verb = verb.substring(1, verb.length() - 1); //strip out the ""
                String pos = j.getAsJsonObject().get("pos").toString();
                pos = pos.substring(1, pos.length() - 1); // strip out the ""
                Verb verbAnnot = new Verb(cas.getJCas());
                verbAnnot.setText(verb);
                verbAnnot.setPos(pos);
                verbAnnot.addToIndexes();
            }

        } catch (CASException e) {
            e.printStackTrace();
        }

    }

    /**
     * keep scrolling until  there are no more documents
     */
    @Override
    public boolean hasNext() throws IOException, CollectionException {
        JestResult result = null;
        SearchScroll scroll = new SearchScroll.Builder(scrollId, "5m")
        .setParameter(Parameters.SIZE, 1).build();

        try {
            result = client.execute(scroll);
        } catch (Exception e) {
            e.printStackTrace();
        }

        currArray = result.getJsonObject().getAsJsonObject("hits").getAsJsonArray("hits");
        scrollId = result.getJsonObject().getAsJsonPrimitive("_scroll_id").getAsString();

        return currArray.size() > 0;
    }


    @Override
    public void close() throws IOException { } 

    @Override
    public Progress[] getProgress() {
        return null;
    }

}