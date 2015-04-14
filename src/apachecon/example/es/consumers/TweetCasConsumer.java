package apachecon.example.es.consumers;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import apachecon.example.es.types.Tweet;

/**
 * This class is used to update the  the Elastic Search service with the current
 * movie_seen and movie_name annotations
 */
public class TweetCasConsumer  extends JCasAnnotator_ImplBase{

    public static final String INDEX = "indexName";
    @ConfigurationParameter(name = INDEX)
    private String indexName;

    private Logger logger;
    private JestClient client;
    private int count;
    private ArrayList<Update> updateList;

    @Override
    public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
        super.initialize(uimaContext);
        logger = getContext().getLogger();
        logger.log(Level.INFO, String.format("TweetCasConsumer Initialized"));

        indexName = (String) getContext().getConfigParameterValue(INDEX);

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
        .multiThreaded(true)
        .build());
        client = factory.getObject();

        updateList = new ArrayList<Update>();
    }

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {

        count++;
        if(count % 5000 == 0){
            System.out.println("count " + count);
            bulkUpdate();
            updateList.clear();
        }

        Iterator<Tweet> tweetIter = JCasUtil.iterator(jCas, Tweet.class); 

        // There can be only one! 
        if(!tweetIter.hasNext()) {
            return; 
        }

        Tweet tweetAnnot = tweetIter.next();
        String id = tweetAnnot.getEsId();
        String updateQuery = createJsonStringFrom(tweetAnnot); 

        if(updateQuery == null){
            System.out.println("it's null");
            return;
        }
        try {
            Update update = new Update.Builder(updateQuery).index(indexName).type("sentiment").id(id).build();

            updateList.add(update);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


    @Override
    public void collectionProcessComplete() throws AnalysisEngineProcessException {
        super.collectionProcessComplete();
        logger.log(Level.INFO, String.format("Bulk Load into Elastic Search Now"));
        bulkUpdate();
    }

    private void bulkUpdate() {
        System.out.println("Bulk Loading");
        Bulk bulk = new Bulk.Builder()
        .defaultIndex(indexName)
        .defaultType("sentiment")
        .addAction(updateList)
        .build();

        try {
            client.execute(bulk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    private String createJsonStringFrom(Tweet tweetAnnot) {
        XContentBuilder xb = null;
        try {
            // Grab the Tweet information


            xb = XContentFactory.jsonBuilder().startObject();
            // Tweet Data
            xb.startObject("doc");
            xb.field("movie_name", tweetAnnot.getMovie());
            xb.field("movie_seen", tweetAnnot.getSeen());
            xb.endObject();
            xb.endObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String output = null;
        try {
            output = xb.string();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }
}