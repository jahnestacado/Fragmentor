package nl.cwi.bfd.algorithm;

import java.util.ArrayList;
import java.util.List;


public class AdditionalChecker {
	
	private final float pdf;
	private final float doc;
	private final float xls;
	private final float ppt;
	private final float text;
	private final static String DOC_TYPE = "DOC_TYPE";
	private final static String PDF_TYPE= "PDF_TYPE";
	private final static String XLS_TYPE = "XLS_TYPE";
	private final static String TEXT_TYPE = "TEXT_TYPE";
	private final static List<Float> pdfRes = new ArrayList<Float>();
	private final static List<Float> docRes = new ArrayList<Float>();
	private final static List<Float> xlsRes = new ArrayList<Float>();
	private final static List<Float> sum = new ArrayList<Float>();
	
	
	public AdditionalChecker(AccuracyHolder holder){
		pdf = holder.getPDFAccuracy();
		doc = holder.getDOCAccuracy();
		xls = holder.getXLSAccuracy();
		ppt = holder.getPPTAccuracy();
		text = holder.getTEXTAccuracy();
	}
	
	public String check(){
		float[] array = {pdf, doc, xls, text};
		float max = max(array);
		
		/*
		sum.add(pdf+doc+xls);
		docRes.add(doc);
		pdfRes.add(pdf);
		xlsRes.add(xls);
	return "PDF: " + pdf +" DOC: "+doc + " XLS: "+ xls+" PPT:"+ppt+ " result: "+isNotDoc(max);
	*/
		//if(xls >= 0.445) return XLS_TYPE;
		//if(doc >= 0.54) return DOC_TYPE;
		if(max == pdf ) return PDF_TYPE;
		if(max == doc) return DOC_TYPE;
		if(max == xls) return XLS_TYPE;
		if(max == text) return TEXT_TYPE;
		
		return "*";
	
		
		
	}
	
	public static void getResults(){
		System.out.println("MIN:  PDF: "+min(pdfRes)+" DOC: "+min(docRes)+" XLS: "+ min(xlsRes));
		System.out.println("MAX:  PDF: "+max(pdfRes)+" DOC: "+max(docRes)+" XLS: "+ max(xlsRes));
		System.out.println("SUM MIN: "+min(sum));
		System.out.println("SUM MAX: "+max(sum));
	}
	
	private boolean checkDoc(){
		if(doc >= 0.54) return true;
		return false;
		
	}
	
	private boolean isNotDoc(float max){
		float dif1 = Math.abs(pdf - doc);
		float dif2 = Math.abs(doc - xls);
		float total = dif1 + dif2;
		
		if(checkXls()) return false;
		if(pdf == max ) return true;
		if(dif1 >= 0.08) return false;
		if((total <= 0.28 && max <=0.54)) return true;
		
		return false;
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
	
	private boolean checkXls(){
		if(xls >= 0.445) return true;
		return false;
	}
	
	
	private boolean isNotPDF(float max){
		if(xls == max) return true;
		
		return false;
	}
	

	

}
