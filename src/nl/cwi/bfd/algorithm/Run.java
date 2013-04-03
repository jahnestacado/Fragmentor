package nl.cwi.bfd.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.algorithm.reader.FingerPrintReader;
import nl.cwi.bfd.fingerprint.AVGScore;
import nl.cwi.bfd.fingerprint.FingerprintCreator;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class Run {

	
	private final static String PDF_FRAGMENT_FOLDER = "/home/jahn/Desktop/thesis/pdf/fragments/";
	private final static String FINGERPRINTS_FOLDER ="/home/jahn/Desktop/thesis/fingerprints";

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
	
	private final static String[] results = {"PDF: ", "DOC: ", "XLS: "};
	
	private final static List<String[]> fingerprints = new ArrayList<String[]>();
	
	static{
		fingerprints.add(PDF_FINGERPRINT_PATHS);
		fingerprints.add(DOC_FINGERPRINT_PATHS);
		fingerprints.add(XLS_FINGERPRINT_PATHS);
	}

	
	
	public static void main(String[] args) throws IOException {
	
		
		FragmentFilePath paths = new FragmentFilePath(PDF_FRAGMENT_FOLDER);
	
		for (String path : paths.getAllPaths()) {
			int index = 0;
			for(String[] fingerprint : fingerprints){
				float[] avgScore = FingerPrintReader.getScores(fingerprint[0]);
				float[] corrStrengthScore = FingerPrintReader.getScores(fingerprint[1]);
				System.out.print(results[index++]);
			calculateAssuranceLevel(avgScore, corrStrengthScore, path);
			
			}
			System.out.println("********");
		}
		
		
	}
	
	private static void calculateAssuranceLevel(float[] avgScore, float[] corrStrengthScore , String path ) throws IOException{
		Map<String, Float> normalizedFragment = FingerprintCreator.getNormalizedScore(path);
		float[] fragmentScore = AVGScore.valuesToArray(normalizedFragment);
		
		float assuranceLevel = BFD.getAssuranceLevel(avgScore, corrStrengthScore, fragmentScore);
		System.out.print(assuranceLevel + " ");
	}

}
