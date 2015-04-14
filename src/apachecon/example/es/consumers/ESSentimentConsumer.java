package apachecon.example.es.consumers;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

import apachecon.example.es.types.Sentence;
import apachecon.example.es.types.Tweet;
import apachecon.example.es.types.Verb;

/**
 * Get the jcas at the end of the pipeline, convert it to json, then send it to the elastic search 
 * backend
 * @author neal
 *
 */
public class ESSentimentConsumer  extends JCasAnnotator_ImplBase{
    private String esHost;

    public static final String INDEX_NAME = "indexName";
    @ConfigurationParameter(name = INDEX_NAME)
    private String indexName;

    private Logger logger;

    private JestClient client;
    private ArrayList<Index> indexList;

    @Override
    public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
        super.initialize(uimaContext);
        logger = getContext().getLogger();
        esHost = "http://localhost:9200";
        indexName = (String) getContext().getConfigParameterValue(INDEX_NAME);
        logger.log(Level.INFO, String.format("Initializing Elastic Search Connect to %s:/%s", esHost, indexName));

        // Construct a new Jest client according to configuration via factory
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(esHost)
                .multiThreaded(true)
                .build());
        client = factory.getObject();
        indexList = new ArrayList<Index>();
    }

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {

        /* Begin Tweet Object Building */
        XContentBuilder xb = createJSON(jCas);
        if (xb == null) return;

        try {
            indexList.add(new Index.Builder(xb.string()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XContentBuilder createJSON(JCas jCas) {
        XContentBuilder xb = null;
        try {
            // Grab the Tweet information
            Iterator<Tweet> tweetIter = JCasUtil.iterator(jCas, Tweet.class); 

            // There can be only one! 
            if(!tweetIter.hasNext()) {
                return null; 
            }
            Tweet tweetAnnot = tweetIter.next();
            String tweet = tweetAnnot.getCoveredText();
            Float averageSentiment = tweetAnnot.getAverageSentiment();
            //System.out.println(averageSentiment + " " + tweet);

            xb = XContentFactory.jsonBuilder().startObject();
            // Tweet Data
            xb.field("tweet", tweet);
            xb.field("average_sentiment", averageSentiment);
            //Sentence Data
            Iterator<Sentence> sentenceIter = JCasUtil.iterator(jCas, Sentence.class);
            xb.startArray("sentences");
            while (sentenceIter.hasNext()){
                Sentence sentence = sentenceIter.next();
                xb.startObject();
                xb.field("sentence", sentence.getText());
                xb.field("sentiment", sentence.getSentiment());
                xb.endObject();
            }
            xb.endArray();

            // Verb Data
            Iterator<Verb> verbIter = JCasUtil.iterator(jCas,Verb.class);
            xb.startArray("verbs");
            while(verbIter.hasNext()){
                Verb verb = verbIter.next();
                xb.startObject();
                xb.field("verb", verb.getText());
                xb.field("pos", verb.getPos()) ;
                xb.endObject();
            }
            xb.endArray();
            xb.endObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return xb;
    }

    /* Execute the Elastic Search Bulk Load at then of the each pipeline. This only 
     * works well if we assume the pipeline doesn't process too much at a time.  
     * The best way to handle this would be through the BulkProcessor Api, setting values for when 
     * to load and flush.  But the following will work for this small demo
     * (non-Javadoc)
     * @see org.apache.uima.analysis_component.AnalysisComponent_ImplBase#collectionProcessComplete()
     */
    @Override
    public void collectionProcessComplete() throws AnalysisEngineProcessException {
        super.collectionProcessComplete();
        logger.log(Level.INFO, String.format("Bulk Load into Elastic Search Now"));

        Bulk bulk = new Bulk.Builder()
        .defaultIndex(indexName)
        .defaultType("sentiment")
        .addAction(indexList)
        .build();

        try {
            client.execute(bulk);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}