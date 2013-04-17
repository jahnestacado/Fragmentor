package nl.cwi.bfd.algorithm;

public class AccuracyHolder {
	private final float pdf;
	private final float doc;
	private final float xls;
	private final float ppt;
	private final float text;
	
	public AccuracyHolder(float[] accuracies){
		pdf = accuracies[0];
		doc = accuracies[1];
		xls = accuracies[2];
		ppt = accuracies[3];
		text = accuracies[4];
	}
	
	public float getPDFAccuracy(){
		return pdf;
	}
	
	public float getTEXTAccuracy(){
		return text;
	}
	
	public float getDOCAccuracy(){
		return doc;
	}
	
	public float getXLSAccuracy(){
		return xls;
	}
	
	public float getPPTAccuracy(){
		return ppt;
	}

}
