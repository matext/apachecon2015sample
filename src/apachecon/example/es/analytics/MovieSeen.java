package apachecon.example.es.analytics;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import apachecon.example.es.types.Tweet;
import apachecon.example.es.types.Verb;

public class MovieSeen  extends JCasAnnotator_ImplBase{
    private Logger logger;
    private String[] patterns;

    @Override
    public void collectionProcessComplete()
            throws AnalysisEngineProcessException {
        super.collectionProcessComplete();
        logger.log(Level.INFO, String.format("Completed Sentence Splitting" ));
    }
    @Override
    public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
        super.initialize(uimaContext);
        logger = getContext().getLogger();
        logger.log(Level.INFO, String.format("Sentence Splitter Initialized"));
        String pattern = "make see saw went was is watch";
        patterns = pattern.split(" ");
    }

    /* Over simplified example for finding a "watch" verb in past tense */
    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
        Iterator<Verb> verbAnnots = JCasUtil.iterator(jCas, Verb.class);
        Iterator<Tweet> tweetAnnots = JCasUtil.iterator(jCas, Tweet.class);
        if (!tweetAnnots.hasNext()){
            return ;// there can be only one!
        }

        /* If the Tweet has a "watch" verb, and it's past tense, set the 
         * Tweet "seen" to true
         */
        Tweet tweet = tweetAnnots.next();
        while(verbAnnots.hasNext())    {
            Verb verbAnnot = verbAnnots.next();
            String verb = verbAnnot.getText(); 
            String pos =  verbAnnot.getPos();
            for (String p : patterns){
                if (verb.startsWith(p)) {
                    tweet.setSeen(pos.equalsIgnoreCase("vbd")); 
                }
            }
        }
    }
}