
/* First created by JCasGen Fri Apr 10 10:08:03 EDT 2015 */
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
 * Updated by JCasGen Fri Apr 10 10:08:03 EDT 2015
 * @generated */
public class TweetSentiment_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TweetSentiment_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TweetSentiment_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TweetSentiment(addr, TweetSentiment_Type.this);
  			   TweetSentiment_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TweetSentiment(addr, TweetSentiment_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TweetSentiment.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("apachecon.example.es.types.TweetSentiment");
 
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
      jcas.throwFeatMissing("averageSentiment", "apachecon.example.es.types.TweetSentiment");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_averageSentiment);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAverageSentiment(int addr, float v) {
        if (featOkTst && casFeat_averageSentiment == null)
      jcas.throwFeatMissing("averageSentiment", "apachecon.example.es.types.TweetSentiment");
    ll_cas.ll_setFloatValue(addr, casFeatCode_averageSentiment, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TweetSentiment_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_averageSentiment = jcas.getRequiredFeatureDE(casType, "averageSentiment", "uima.cas.Float", featOkTst);
    casFeatCode_averageSentiment  = (null == casFeat_averageSentiment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_averageSentiment).getCode();

  }
}



    