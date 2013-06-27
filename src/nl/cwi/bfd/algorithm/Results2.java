package nl.cwi.bfd.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Results2 {

	private final float pdf;
	private final float doc;
	private final float xls;
	private final float ppt;
	private final float text;
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
	private static int counter =0;


	public Results2(AccuracyHolder holder, String path){
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
	}

	public void set() {
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

	private void updateCounter(float max) {
		if (max == pdf){
			int prevValue = results.get(PDF_TYPE);
			results.put(PDF_TYPE, prevValue + 1);
			//System.out.println(PDF_TYPE);
			return;
		}
		if (max == doc){
			int prevValue = results.get(DOC_TYPE);
			results.put(DOC_TYPE, prevValue + 1);
			//System.out.println(DOC_TYPE);
			return;
		}
		if (max == xls){
			int prevValue = results.get(XLS_TYPE);
			results.put(XLS_TYPE, prevValue + 1);
			//System.out.println(XLS_TYPE);
			return;
		}
		if (max == ogg){
			int prevValue = results.get(OGG_TYPE);
			results.put(OGG_TYPE, prevValue + 1);
			//System.out.println(OGG_TYPE);
			return;
		}
		if (max == mp4){
			int prevValue = results.get(MP4_TYPE);
			results.put(MP4_TYPE, prevValue + 1);
			//System.out.println(MP4_TYPE);
			return;
		}
		if (max == png){
			int prevValue = results.get(PNG_TYPE);
			results.put(PNG_TYPE, prevValue + 1);
			//System.out.println(PNG_TYPE);
			return;
		}
		if (max == jpg){
			int prevValue = results.get(JPG_TYPE);
			results.put(JPG_TYPE, prevValue + 1);
			//System.out.println(JPG_TYPE);
			return;
		}
		if (max == zip){
			int prevValue = results.get(ZIP_TYPE);
			results.put(ZIP_TYPE, prevValue + 1);
			//System.out.println(ZIP_TYPE);
			return;
		}
		if (max == text){
			int prevValue = results.get(TEXT_TYPE);
			results.put(TEXT_TYPE, prevValue + 1);
			//System.out.println(TEXT_TYPE);
			counter++;
			//FileCopy.copyTo(path);
			return;
		}
		if (max == ppt){
			int prevValue = results.get(PPT_TYPE);
			results.put(PPT_TYPE, prevValue + 1);
			//System.out.println(PPT_TYPE);
			return;
		}

		int prevValue = results.get(OTHER_TYPE);
		//System.out.println(OTHER_TYPE);
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
		System.out.println(counter);
	}



}