package apachecon.example.es.analytics;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import apachecon.example.es.types.Sentence;
import apachecon.example.es.types.Tweet;
import apachecon.example.es.types.Verb;

/**
 * The original sentiment analysis demo used a library that wasn't compatible
 * with Apache Licensed software.  This class is now a stub class for where
 * to put sentiment analysis in the pipeline
 */
public class SentimentAnalyzer  extends JCasAnnotator_ImplBase{

    private Logger logger;
    private int count;
    @Override
    public void collectionProcessComplete()
            throws AnalysisEngineProcessException {
        super.collectionProcessComplete();
        logger.log(Level.INFO, String.format("Completed Sentiment Analysis"));
    }
    
    
    /* Annotate CAS with Sentiment */
    @Override
    public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
        super.initialize(uimaContext);
        logger = getContext().getLogger();
        logger.log(Level.INFO, String.format("Sentence Splitter Initialized"));

        count = 0;
    }

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
        count++;
        if(count % 5000 == 0){
            System.out.println("Current count: " + count );
        }

        /* get document to analyze */
        String sofa = jCas.getDocumentText();

        /* Create tweet object covering complete document */
        Tweet tweetAnnot = new Tweet(jCas);
        tweetAnnot.setBegin(0);
        tweetAnnot.setEnd(sofa.length());
        tweetAnnot.setAverageSentiment(1);
        tweetAnnot.addToIndexes();


        /* annotation sentences */
        Sentence sentenceAnnot = new Sentence(jCas);
        sentenceAnnot.setText(sofa); 
        sentenceAnnot.setSentiment(1);
        sentenceAnnot.addToIndexes();

        /* annoate verbs */
        Verb verbAnnot = new Verb(jCas);
        verbAnnot.setText("VERB");
        verbAnnot.setPos("POS");
        verbAnnot.addToIndexes();

    }
}