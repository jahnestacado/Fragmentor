package nl.cwi.bfd.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.fingerprint.io.reader.RatioFilter;
import nl.cwi.counter.Counter;
import nl.cwi.counter.IndividualZerosCounter;
import nl.cwi.counter.StringsInFragment;
import nl.cwi.entropy.CalculateEntropy;
import nl.cwi.lcs.XLS;


public class Results {
	
	private  float pdf;
	private  float doc;
	private  float xls;
	private final float ppt;
	private  float text;
	private final float zip;
	private final float jpg;
	private final float mp4;
	private final float png;
	private final float ogg;
	private final static String DOC_TYPE = "doc";
	private final static String PDF_TYPE= "pdf";
	private final static String XLS_TYPE = "xls";
	private final static String TEXT_TYPE = "text";
	private final static String MP4_TYPE = "mp4";
	private final static String ZIP_TYPE= "zip";
	private final static String PNG_TYPE = "png";
	private final static String JPG_TYPE = "jpg";
	private final static String OGG_TYPE = "ogg";
	private final static String PPT_TYPE= "ppt";
	private final static String OTHER_TYPE= "*";
	private final static Map<String,Integer> results = new HashMap<String, Integer>();
	private final String path;
	private final String type;
	private static int nullValueMetricXls = 25;
	private static float ENTROPY = 3.9f;
	private  static int numOfIndieZeros;
	private  static double ratio;
  private static double entropy;

 private static int j =0;
 private static int tt =0;

	private static double nullValueMetricDoc = 9;

	
	public Results(AccuracyHolder holder, String path, String type) throws IOException{
		this.path = path;
		pdf = holder.getPDFAccuracy();
		doc = holder.getDOCAccuracy();
		xls = holder.getXLSAccuracy();
		ppt = holder.getPPTAccuracy();
		text = holder.getTEXTAccuracy();
		zip = holder.getZIPAccuracy();
		mp4 = holder.getMP4Accuracy();
		jpg = holder.getJPGAccuracy();
		png = holder.getPNGAccuracy();
		ogg = holder.getOGGAccuracy();
	
		if (results.size() == 0) {
			initMap();
		}	
		this.type = type;
		//numOfIndieZeros = IndividualZerosCounter.getNumOfIndieZeros(path).size();
		//ratio = RatioFilter.getFragmentsRatio(path);
		//entropy = CalculateEntropy.getFragmentsEntropy(path);
		tt++;
	}

	public void set() throws IOException {
		float[] array = { pdf, doc, xls, ppt, text, zip, mp4, jpg, png, ogg };
		float max = max(array);
		updateCounter(max);
	}
	
	public  void getResults(){
		int sum = 0;
		for(String key : results.keySet()){
			sum += results.get(key);
		}
		
		for(String key : results.keySet()){
			System.out.println(key+"  "+ results.get(key));
			float percentage = (results.get(key) * 100f) / sum;
			System.out.print(key+":"+percentage+"%, ");
		}
	}
	
	
	
	private  static float max(float[] array) {
	    float maximum = array[0];   
	    for (int i=1; i<array.length; i++) {
	        if (array[i] > maximum) {
	            maximum = array[i];   
	        }
	    }
	    return maximum;
	}
	
	private  static float min(List<Float> array) {
	    float minimum = array.get(0);   
	    for (int i=1; i<array.size()-1; i++) {
	        if (array.get(i) < minimum) {
	            minimum = array.get(i);   
	        }
	    }
	    return minimum;
	}
	
	
	private  static float max(List<Float> array) {
	    float minimum = array.get(0);   
	    for (int i=1; i<array.size()-1; i++) {
	        if (array.get(i) > minimum) {
	            minimum = array.get(i);   
	        }
	    }
	    return minimum;
	}
	
	private void updateCounter(float max) throws IOException {
		if (max == pdf){
			System.out.println("pdf");
			/*
			int prevValue = results.get(PDF_TYPE);
			results.put(PDF_TYPE, prevValue + 1);
			System.out.println(++pdfIndex+" PDF");
			*/
			return;
		}
		if (max == doc){
			System.out.println("doc");

			/*
			int prevValue = results.get(DOC_TYPE);
			results.put(DOC_TYPE, prevValue + 1);
			System.out.println(++docIndex+" DOC");
	*/
			return;
		}
		if (max == xls){
			System.out.println("xls");

			/*
			
			if(firstCheck(entropy)){
				if( numOfIndieZeros > 50  && ratio < 50  ){
					int prevValue = results.get(XLS_TYPE);
					results.put(XLS_TYPE, prevValue + 1);
				}
				else if( pdf>doc && numOfIndieZeros <= nullValueMetricDoc  || entropy >= 5.8){
					int prevValue = results.get(PDF_TYPE);
					results.put(PDF_TYPE, prevValue + 1);
				}
				else {
					int prevValue = results.get(DOC_TYPE);
					results.put(DOC_TYPE, prevValue + 1);
				}
				return;
			}
			
			if(numOfIndieZeros > 50 && ratio < 50){
			int prevValue = results.get(XLS_TYPE);
			results.put(XLS_TYPE, prevValue + 1);
			return;
			}
			int prevValue = results.get(DOC_TYPE);
			results.put(DOC_TYPE, prevValue + 1);
			*/
			return;
		}
		if (max == ogg){
			System.out.println("ogg");

			/*
			int prevValue = results.get(OGG_TYPE);
			results.put(OGG_TYPE, prevValue + 1);
			*/
			return;
		}
		if (max == mp4){
			System.out.println("mp4");

			/*
			int prevValue = results.get(MP4_TYPE);
			results.put(MP4_TYPE, prevValue + 1);
			*/
			return;
		}
		if (max == png){
			System.out.println("png");

			/*
			int prevValue = results.get(PNG_TYPE);
			results.put(PNG_TYPE, prevValue + 1);
			*/
			return;
		}
		if (max == jpg){
			System.out.println("jpg");

			/*
			int prevValue = results.get(JPG_TYPE);
			results.put(JPG_TYPE, prevValue + 1);
			//System.out.println(JPG_TYPE);
			 * */
			 
			return;
		}
		if (max == zip){
			System.out.println("zip");

			/*
			int prevValue = results.get(ZIP_TYPE);
			results.put(ZIP_TYPE, prevValue + 1);
			*/
			return;
		}
		if (max == text){
			/*double entropy = CalculateEntropy.getFragmentsEntropy(path);
			if(numOfIndieZeros > 0 || entropy > 5 ){
				
				if(firstCheck(entropy)){
					if( numOfIndieZeros > 50  && ratio < 50  ){
						int prevValue = results.get(XLS_TYPE);
						results.put(XLS_TYPE, prevValue + 1);
					}
					else if( pdf>doc && numOfIndieZeros <= nullValueMetricDoc  || entropy >= 5.8){
						int prevValue = results.get(PDF_TYPE);
						results.put(PDF_TYPE, prevValue + 1);
					}
					else {
						int prevValue = results.get(DOC_TYPE);
						results.put(DOC_TYPE, prevValue + 1);
					}
					return;
				}
				
				if(numOfIndieZeros > 50 && ratio < 50){
				int prevValue = results.get(XLS_TYPE);
				results.put(XLS_TYPE, prevValue + 1);
				return;
				}
				int prevValue = results.get(DOC_TYPE);
				results.put(DOC_TYPE, prevValue + 1);
				return;
			}
			
			int prevValue = results.get(TEXT_TYPE);
			results.put(TEXT_TYPE, prevValue + 1);
			//FileCopy.copyTo(path);
		*/
			j++;
			numOfIndieZeros = IndividualZerosCounter.getNumOfIndieZeros(path).size();
			ratio = RatioFilter.getFragmentsRatio(path);
			entropy = CalculateEntropy.getFragmentsEntropy(path);
			if( ratio < 100 ){
				
				if( firstCheck(entropy)){
					if( numOfIndieZeros > 50  && ratio < 50  ){
						int prevValue = results.get(XLS_TYPE);
						results.put(XLS_TYPE, prevValue + 1);
					}
					else if( pdf>doc && numOfIndieZeros <= nullValueMetricDoc  || entropy >= 5.8){
						int prevValue = results.get(PDF_TYPE);
						results.put(PDF_TYPE, prevValue + 1);
					}
					else {
						int prevValue = results.get(DOC_TYPE);
						results.put(DOC_TYPE, prevValue + 1);
					}
					return;
				}
				
				if(numOfIndieZeros > 50 && ratio < 50){
				int prevValue = results.get(XLS_TYPE);
				results.put(XLS_TYPE, prevValue + 1);
				return;
				}
				int prevValue = results.get(DOC_TYPE);
				results.put(DOC_TYPE, prevValue + 1);
				return;
			}
			
			if(entropy < 6){
			int prevValue = results.get(TEXT_TYPE);
			results.put(TEXT_TYPE, prevValue + 1);
			}
			else if( pdf>doc && numOfIndieZeros <= nullValueMetricDoc  || entropy >= 5.8){
				int prevValue = results.get(PDF_TYPE);
				results.put(PDF_TYPE, prevValue + 1);
			}
			else {
				int prevValue = results.get(DOC_TYPE);
				results.put(DOC_TYPE, prevValue + 1);
			}
			
		
			 
			return;
		}
		if (max == ppt){
			System.out.println("ppt");

			/*
			int prevValue = results.get(PPT_TYPE);
			results.put(PPT_TYPE, prevValue + 1);
			*/
			return;
		}
		System.out.println("other");

		int prevValue = results.get(OTHER_TYPE);
		results.put(OTHER_TYPE, prevValue + 1);
		
		
	}

	private void initMap(){
		results.put(DOC_TYPE, 0);
		results.put(PDF_TYPE, 0);
		results.put(XLS_TYPE, 0);
		results.put(OGG_TYPE, 0);
		results.put(MP4_TYPE, 0);
		results.put(PPT_TYPE, 0);
		results.put(TEXT_TYPE, 0);
		results.put(PNG_TYPE, 0);
		results.put(JPG_TYPE, 0);
		results.put(ZIP_TYPE, 0);
		results.put(OTHER_TYPE, 0);
	}
	
	public void clearResults(){
		results.clear();
		System.out.println(j);
		System.out.println(tt);

		j=0;
	}
	
	private boolean firstCheck(double entropy) throws IOException{
		if( entropy >= ENTROPY ||  numOfIndieZeros <= nullValueMetricXls) return true;
		
		return false;
	}

}