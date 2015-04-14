package apachecon.example.es.main;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import apachecon.example.es.consumers.ESSentimentConsumer;
import apachecon.example.es.readers.TweetPerCasReader;

/**
 * Pipeline responsible for sending of the elastic search:
 * 1. read from file, creating 1 cas at a time per tweet
 * 2. annotate the tweet with sentiment (note, this analytic engine is a stub right now)
 * 3. send the cas object an elastic search engine, where the index is passed in through cmd line
 * @author neal
 */
public class SentimentToES {
	public static void main(String[] args) throws UIMAException, IOException {   
        
	   String infile = args[0];
	   String indexName = args[1];

       CollectionReader cr = CollectionReaderFactory.createReader("apachecon.example.es.desc.TweetPerCasReader", TweetPerCasReader.FILE_PATH,  infile);
       
       AnalysisEngine sentiment = AnalysisEngineFactory.createEngine("apachecon.example.es.desc.SentimentAnalyzer");
       AnalysisEngine esConsumer = AnalysisEngineFactory.createEngine("apachecon.example.es.desc.ESSentimentConsumer", ESSentimentConsumer.INDEX_NAME, indexName);

       SimplePipeline.runPipeline(cr, sentiment, esConsumer);
       
       System.out.println("SentimentToES Pipeline Process Complete");                           
                                                                                   
    }                                                  
}
