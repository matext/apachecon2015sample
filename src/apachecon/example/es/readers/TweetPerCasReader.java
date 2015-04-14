package apachecon.example.es.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.util.Level;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.apache.uima.util.Logger;


/**
 * Tweet Per Cas Collection Reader.
 * Reads in text file with a single tweet per line, annotating each as a sentence.
 * @author neal
 *
 */
public class TweetPerCasReader extends CollectionReader_ImplBase {


    public static final String FILE_PATH  = "filePath";
    @ConfigurationParameter(name = FILE_PATH)

    private String inFilePath;
    private Logger logger;
    private int numLines = 0;
    private BufferedReader fileReader; 
    private String currLine;
    @Override
    public void initialize(){
        logger = getUimaContext().getLogger();
        inFilePath = (String) getConfigParameterValue(FILE_PATH);
        logger.log(Level.INFO, "Loading " + inFilePath);
        logger.log(Level.INFO, String.format("Initialized TweetPerCas for file %s", inFilePath));

        try {
            numLines = countLines(inFilePath);
            logger.log(Level.INFO, inFilePath + " contains " + numLines + " lines");
            fileReader = new BufferedReader( new InputStreamReader( new FileInputStream(inFilePath), "UTF8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public int countLines(String inFilePath) throws IOException{
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(inFilePath));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    @Override
    public void getNext(CAS cas ) throws IOException, CollectionException {
        cas.setDocumentText(currLine);

    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public Progress[] getProgress() {
        return new Progress[]{ new ProgressImpl(0,1, Progress.ENTITIES)};
    }
    /* stop running when there are no more lines in the file */
    @Override
    public boolean hasNext() throws IOException, CollectionException {
        currLine = fileReader.readLine();
        return currLine != null;
    }

}