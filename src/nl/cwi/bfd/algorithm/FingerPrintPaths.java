package nl.cwi.bfd.algorithm;

public class FingerPrintPaths {
	private final static String FINGERPRINTS_FOLDER ="/home/jahn/Desktop/corpus/fingerprints";
	
	private final static String[]  PDF_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/PDF_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/PDF_CORR_STR_fingerprint.fgp" 
		};

	private final static String[]  DOC_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/DOC_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/DOC_CORR_STR_fingerprint.fgp" 
		};

	private final static String[]  XLS_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/XLS_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/XLS_CORR_STR_fingerprint.fgp" 
		};

	private final static String[]  PPT_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/PPT_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/PPT_CORR_STR_fingerprint.fgp" 
		};

	private final static String[]  TEXT_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/TEXT_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/TEXT_CORR_STR_fingerprint.fgp" 
		};
	
	private final static String[]  OGG_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/OGG_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/OGG_CORR_STR_fingerprint.fgp" 
		};
	
	private final static String[]  PNG_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/PNG_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/PNG_CORR_STR_fingerprint.fgp" 
		};
	
	private final static String[]  JPG_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/JPG_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/JPG_CORR_STR_fingerprint.fgp" 
		};
	
	private final static String[]  MP4_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/MP4_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/MP4_CORR_STR_fingerprint.fgp" 
		};
	
	private final static String[]  ZIP_FINGERPRINT_PATHS = {
		FINGERPRINTS_FOLDER + "/ZIP_AVGfingerprint.fgp",
		FINGERPRINTS_FOLDER + "/ZIP_CORR_STR_fingerprint.fgp" 
		};
	
	public  static String[] getTextFPPaths(){
		return TEXT_FINGERPRINT_PATHS;
	}
	
	public  static String[] getMp4FPPaths(){
		return MP4_FINGERPRINT_PATHS;
	}
	
	public  static String[] getZipFPPaths(){
		return ZIP_FINGERPRINT_PATHS;
	}
	
	public  static String[] getOggFPPaths(){
		return OGG_FINGERPRINT_PATHS;
	}
	
	public  static String[] getPngFPPaths(){
		return PNG_FINGERPRINT_PATHS;
	}
	
	public  static String[] getDocFPPaths(){
		return DOC_FINGERPRINT_PATHS;
	}
	
	public  static String[] getPdfFPPaths(){
		return PDF_FINGERPRINT_PATHS;
	}
	
	public  static String[] getXlsFPPaths(){
		return XLS_FINGERPRINT_PATHS;
	}
	
	public  static String[] getPptFPPaths(){
		return PPT_FINGERPRINT_PATHS;
	}
	
	public  static String[] getJpgFPPaths(){
		return JPG_FINGERPRINT_PATHS;
	}

}
