package nl.cwi.bfd.algorithm;

public class AccuracyHolder {
	private final float pdf;
	private final float doc;
	private final float xls;
	private final float ppt;
	private final float text;
	private final float zip;
	private final float mp4;
	private final float jpg;
	private final float png;
	private final float ogg;
	
	public AccuracyHolder(float[] accuracies){
		pdf = accuracies[0];
		doc = accuracies[1];
		xls = accuracies[2];
		ppt = accuracies[3];
		text = accuracies[4];
		zip = accuracies[5];
		mp4 = accuracies[6];
		jpg = accuracies[7];
		png = accuracies[8];
		ogg = accuracies[9];
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
	
	public float getZIPAccuracy(){
		return zip;
	}
	
	public float getMP4Accuracy(){
		return mp4;
	}
	
	public float getJPGAccuracy(){
		return jpg;
	}
	
	public float getPNGAccuracy(){
		return png;
	}
	
	public float getOGGAccuracy(){
		return ogg;
	}

}
