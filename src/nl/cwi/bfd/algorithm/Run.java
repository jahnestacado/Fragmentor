package nl.cwi.bfd.algorithm;

import java.io.IOException;

import nl.cwi.bfd.algorithm.reader.FingerPrintReader;

public class Run {

	private final static String FINGERPRINTS_FOLDER ="/home/jahn/Desktop/thesis/fingerprints";

	private final static String[]  PDF_FINGERPRINTS_PATHS = {
															FINGERPRINTS_FOLDER + "/PDF_AVGfingerprint.fgp",
															FINGERPRINTS_FOLDER + "/PDF_CORR_STR_fingerprint.fgp" 
															};
	
	public static void main(String[] args) throws IOException {
		float[] avgScore = FingerPrintReader.getScores(PDF_FINGERPRINTS_PATHS[0]);
		float[] corrStrengthScore = FingerPrintReader.getScores(PDF_FINGERPRINTS_PATHS[1]);

		
		
	}

}
