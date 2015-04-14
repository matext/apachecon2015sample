package apachecon.example.es.main;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import apachecon.example.es.consumers.TweetCasConsumer;
import apachecon.example.es.readers.ESTweetReader;

/**
 * Main class for running the second pipeline.  
 * 1. Extracts the tweets stored in ElasticSearch, 
 * 2. Annotate if the tweet contains "Imitation Game"
 * 3. Annotate if the tweet has a past tense verb
 * 4. Update the elastic search store with the newly annotated data
 */
public class ESToMovie {
    public static void main(String[] args) throws UIMAException, IOException {   

        String indexName = args[0];
        CollectionReader cr = CollectionReaderFactory.createReader("apachecon.example.es.desc.ESTweetReader", ESTweetReader.INDEX, indexName);

        AnalysisEngine movieFinder = AnalysisEngineFactory.createEngine("apachecon.example.es.desc.MovieFinder");
        AnalysisEngine movieSeen = AnalysisEngineFactory.createEngine("apachecon.example.es.desc.MovieSeen");
        AnalysisEngine casConsumer = AnalysisEngineFactory.createEngine("apachecon.example.es.desc.TweetCasConsumer", TweetCasConsumer.INDEX, indexName);

        SimplePipeline.runPipeline(cr, movieFinder, movieSeen, casConsumer);
        System.out.println("ESToMovie Pipeline Process Complete");                           

    }                                                  
}
