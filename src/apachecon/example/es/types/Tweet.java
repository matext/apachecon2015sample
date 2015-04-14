

/* First created by JCasGen Fri Apr 10 10:05:29 EDT 2015 */
package apachecon.example.es.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Apr 10 18:55:07 EDT 2015
 * XML source: /Users/neal/workspace/ApacheCon/src/apachecon/example/es/desc/ESTweetTypeSystem.xml
 * @generated */
public class Tweet extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Tweet.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Tweet() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Tweet(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Tweet(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Tweet(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: averageSentiment

  /** getter for averageSentiment - gets 
   * @generated
   * @return value of the feature 
   */
  public float getAverageSentiment() {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_averageSentiment == null)
      jcasType.jcas.throwFeatMissing("averageSentiment", "apachecon.example.es.types.Tweet");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Tweet_Type)jcasType).casFeatCode_averageSentiment);}
    
  /** setter for averageSentiment - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAverageSentiment(float v) {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_averageSentiment == null)
      jcasType.jcas.throwFeatMissing("averageSentiment", "apachecon.example.es.types.Tweet");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Tweet_Type)jcasType).casFeatCode_averageSentiment, v);}    
   
    
  //*--------------*
  //* Feature: esId

  /** getter for esId - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEsId() {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_esId == null)
      jcasType.jcas.throwFeatMissing("esId", "apachecon.example.es.types.Tweet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Tweet_Type)jcasType).casFeatCode_esId);}
    
  /** setter for esId - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEsId(String v) {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_esId == null)
      jcasType.jcas.throwFeatMissing("esId", "apachecon.example.es.types.Tweet");
    jcasType.ll_cas.ll_setStringValue(addr, ((Tweet_Type)jcasType).casFeatCode_esId, v);}    
   
    
  //*--------------*
  //* Feature: movie

  /** getter for movie - gets 
   * @generated
   * @return value of the feature 
   */
  public String getMovie() {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_movie == null)
      jcasType.jcas.throwFeatMissing("movie", "apachecon.example.es.types.Tweet");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Tweet_Type)jcasType).casFeatCode_movie);}
    
  /** setter for movie - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setMovie(String v) {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_movie == null)
      jcasType.jcas.throwFeatMissing("movie", "apachecon.example.es.types.Tweet");
    jcasType.ll_cas.ll_setStringValue(addr, ((Tweet_Type)jcasType).casFeatCode_movie, v);}    
   
    
  //*--------------*
  //* Feature: seen

  /** getter for seen - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getSeen() {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_seen == null)
      jcasType.jcas.throwFeatMissing("seen", "apachecon.example.es.types.Tweet");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Tweet_Type)jcasType).casFeatCode_seen);}
    
  /** setter for seen - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSeen(boolean v) {
    if (Tweet_Type.featOkTst && ((Tweet_Type)jcasType).casFeat_seen == null)
      jcasType.jcas.throwFeatMissing("seen", "apachecon.example.es.types.Tweet");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Tweet_Type)jcasType).casFeatCode_seen, v);}    
  }

    