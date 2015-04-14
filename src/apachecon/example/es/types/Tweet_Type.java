
/* First created by JCasGen Fri Apr 10 10:05:29 EDT 2015 */
package apachecon.example.es.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Apr 10 18:55:07 EDT 2015
 * @generated */
public class Tweet_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Tweet_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Tweet_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Tweet(addr, Tweet_Type.this);
  			   Tweet_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Tweet(addr, Tweet_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Tweet.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("apachecon.example.es.types.Tweet");
 
  /** @generated */
  final Feature casFeat_averageSentiment;
  /** @generated */
  final int     casFeatCode_averageSentiment;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getAverageSentiment(int addr) {
        if (featOkTst && casFeat_averageSentiment == null)
      jcas.throwFeatMissing("averageSentiment", "apachecon.example.es.types.Tweet");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_averageSentiment);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAverageSentiment(int addr, float v) {
        if (featOkTst && casFeat_averageSentiment == null)
      jcas.throwFeatMissing("averageSentiment", "apachecon.example.es.types.Tweet");
    ll_cas.ll_setFloatValue(addr, casFeatCode_averageSentiment, v);}
    
  
 
  /** @generated */
  final Feature casFeat_esId;
  /** @generated */
  final int     casFeatCode_esId;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEsId(int addr) {
        if (featOkTst && casFeat_esId == null)
      jcas.throwFeatMissing("esId", "apachecon.example.es.types.Tweet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_esId);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEsId(int addr, String v) {
        if (featOkTst && casFeat_esId == null)
      jcas.throwFeatMissing("esId", "apachecon.example.es.types.Tweet");
    ll_cas.ll_setStringValue(addr, casFeatCode_esId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_movie;
  /** @generated */
  final int     casFeatCode_movie;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMovie(int addr) {
        if (featOkTst && casFeat_movie == null)
      jcas.throwFeatMissing("movie", "apachecon.example.es.types.Tweet");
    return ll_cas.ll_getStringValue(addr, casFeatCode_movie);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMovie(int addr, String v) {
        if (featOkTst && casFeat_movie == null)
      jcas.throwFeatMissing("movie", "apachecon.example.es.types.Tweet");
    ll_cas.ll_setStringValue(addr, casFeatCode_movie, v);}
    
  
 
  /** @generated */
  final Feature casFeat_seen;
  /** @generated */
  final int     casFeatCode_seen;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getSeen(int addr) {
        if (featOkTst && casFeat_seen == null)
      jcas.throwFeatMissing("seen", "apachecon.example.es.types.Tweet");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_seen);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSeen(int addr, boolean v) {
        if (featOkTst && casFeat_seen == null)
      jcas.throwFeatMissing("seen", "apachecon.example.es.types.Tweet");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_seen, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Tweet_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_averageSentiment = jcas.getRequiredFeatureDE(casType, "averageSentiment", "uima.cas.Float", featOkTst);
    casFeatCode_averageSentiment  = (null == casFeat_averageSentiment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_averageSentiment).getCode();

 
    casFeat_esId = jcas.getRequiredFeatureDE(casType, "esId", "uima.cas.String", featOkTst);
    casFeatCode_esId  = (null == casFeat_esId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_esId).getCode();

 
    casFeat_movie = jcas.getRequiredFeatureDE(casType, "movie", "uima.cas.String", featOkTst);
    casFeatCode_movie  = (null == casFeat_movie) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_movie).getCode();

 
    casFeat_seen = jcas.getRequiredFeatureDE(casType, "seen", "uima.cas.Boolean", featOkTst);
    casFeatCode_seen  = (null == casFeat_seen) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_seen).getCode();

  }
}



    