package apachecon.example.es.analytics;

import java.util.Iterator;
import java.util.regex.Matcher;
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

/**
 * Silly way to find if the "Imitation Game" is in the tweets.  Do simple 
 * regex search on the cas sofa.
 * @author neal
 *
 */
public class MovieFinder  extends JCasAnnotator_ImplBase{
    private Logger logger;
    private Pattern p;

    @Override
    public void collectionProcessComplete()
            throws AnalysisEngineProcessException {
        super.collectionProcessComplete();
        logger.log(Level.INFO, String.format("Movie finder complete" ));
    }
    @Override
    public void initialize(UimaContext uimaContext) throws ResourceInitializationException {
        super.initialize(uimaContext);
        logger = getContext().getLogger();
        
        /* imitation game finder */
        String pattern = "imitat";
        p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        }

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {

        /* pull out the tweet from the cas and update them if the cas
         *  if the tweet matches the regex
         */
        Iterator<Tweet> tweetAnnots = JCasUtil.iterator(jCas, Tweet.class);
        while(tweetAnnots.hasNext()){
            Tweet annot = tweetAnnots.next();   

            Matcher m = p.matcher(annot.getCoveredText());

            if (m.find()){
                annot.setMovie("Imitation Game");
            }else{
                annot.setMovie("Unknown"); 
            }
        }
    }
}