package nl.cwi.dtfc.classifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nl.cwi.bfd.algorithm.AccuracyHolder;
import nl.cwi.bfd.fingerprint.io.reader.RatioFilter;
import nl.cwi.counter.IndividualZerosCounter;
import nl.cwi.entropy.CalculateEntropy;


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
	private final static Map<String,Integer> results = new HashMap<String, Integer>();
	private final String path;
	private final String type;


    private static int j =0;
    private final Integer[] fragmentStream;
    
    
	private  static int numOfIndieNullBytes;
	private  static double ratio;
    private static double entropy;
	private static double LOW_NUM_OF_NULL_BYTES = 9;
	private final static float TEXT_MAX_ENTROPY = 6;
	private final static float MAX_XLS_PLAIN_TEXT_CONCENTRATION = 50;
	private final static float MIN_XLS_NUM_OF_NULL_BYTES= 50;
	private final static float AVG_PDF_ENTROPY= 5.8f;
	private static int nullValueMetricXls = 25;
	private static float COMPRESSED_FRAGMENT_ENTROPY = 3.9f;

	
	public Results(AccuracyHolder holder, String path, String type,Integer[] fragmentStream) throws IOException{
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
		this.fragmentStream = fragmentStream;
	}

	public void set() throws IOException {
		float[] array = { pdf, doc, xls, ppt, text, zip, mp4, jpg, png, ogg };
		float max = max(array);
		classifyFragment(max);
	}
	
	public  void getResults(){
		int sum = 0;
		for(String key : results.keySet()){
			sum += results.get(key);
		}
		
		for(String key : results.keySet()){
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
	

	
	private void classifyFragment(float max) throws IOException {
		if (max == text) {
			j++;
			numOfIndieNullBytes = IndividualZerosCounter
					.getNumOfIndieZeros(fragmentStream);
			ratio = RatioFilter.getFragmentsRatio(path);
			entropy = CalculateEntropy.getFragmentsEntropy(fragmentStream);
			
			
			if (ratio < 100) {
				if (isXLS()) {
					setXls();
				} else if (isNotPdf(entropy)) {
					setDoc();
				} else if (isPDF()) {
					setPdf();
				} else {
					setDoc();
				}
			}

			else if (entropy < TEXT_MAX_ENTROPY) {
				setText();
			} else if (isPDF()) {
				setPdf();
			} else {
				setDoc();
			}

		}
	}
	
	private boolean isXLS(){
		return numOfIndieNullBytes > MIN_XLS_NUM_OF_NULL_BYTES && ratio < MAX_XLS_PLAIN_TEXT_CONCENTRATION;
	}
	
	
	private boolean isPDF(){
		return pdf > doc && numOfIndieNullBytes <= LOW_NUM_OF_NULL_BYTES || entropy >= AVG_PDF_ENTROPY;
	}
	
	
	private boolean isNotPdf(double entropy) throws IOException{
		return entropy <= COMPRESSED_FRAGMENT_ENTROPY &&  numOfIndieNullBytes >= nullValueMetricXls;
	}

	private void initMap(){
		results.put(DOC_TYPE, 0);
		results.put(PDF_TYPE, 0);
		results.put(XLS_TYPE, 0);
		results.put(TEXT_TYPE, 0);
	}
	
	public void clearResults(){
		results.clear();
		System.out.println(j);
	}
	
	
	
	private void setPdf(){
		int prevValue = results.get(PDF_TYPE);
		results.put(PDF_TYPE, prevValue + 1);
	}
	
	private void setText(){
		int prevValue = results.get(TEXT_TYPE);
		results.put(TEXT_TYPE, prevValue + 1);
	}
	
	private void setXls(){
		int prevValue = results.get(XLS_TYPE);
		results.put(XLS_TYPE, prevValue + 1);
	}
	
	private void setDoc(){
		int prevValue = results.get(DOC_TYPE);
		results.put(DOC_TYPE, prevValue + 1);
	}

}