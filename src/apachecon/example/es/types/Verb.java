

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
public class Verb extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Verb.class);
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
  protected Verb() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Verb(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Verb(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Verb(JCas jcas, int begin, int end) {
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
  //* Feature: pos

  /** getter for pos - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPos() {
    if (Verb_Type.featOkTst && ((Verb_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "apachecon.example.es.types.Verb");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Verb_Type)jcasType).casFeatCode_pos);}
    
  /** setter for pos - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPos(String v) {
    if (Verb_Type.featOkTst && ((Verb_Type)jcasType).casFeat_pos == null)
      jcasType.jcas.throwFeatMissing("pos", "apachecon.example.es.types.Verb");
    jcasType.ll_cas.ll_setStringValue(addr, ((Verb_Type)jcasType).casFeatCode_pos, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Verb_Type.featOkTst && ((Verb_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "apachecon.example.es.types.Verb");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Verb_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Verb_Type.featOkTst && ((Verb_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "apachecon.example.es.types.Verb");
    jcasType.ll_cas.ll_setStringValue(addr, ((Verb_Type)jcasType).casFeatCode_text, v);}    
  }

    