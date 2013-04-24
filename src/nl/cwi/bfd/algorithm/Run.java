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

	
	private final static String FRAGMENT_INPUT_FOLDER = "/home/jahn/Desktop/thesis/pdf/fragments/";
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
	
	private final static String[]  PPT_FINGERPRINT_PATHS = {
															FINGERPRINTS_FOLDER + "/PPT_AVGfingerprint.fgp",
															FINGERPRINTS_FOLDER + "/PPT_CORR_STR_fingerprint.fgp" 
															};
	
	private final static String[]  TEXT_FINGERPRINT_PATHS = {
															FINGERPRINTS_FOLDER + "/TEXT_AVGfingerprint.fgp",
															FINGERPRINTS_FOLDER + "/TEXT_CORR_STR_fingerprint.fgp" 
															};
	
	
	
	private final static List<String[]> fingerprints = new ArrayList<String[]>();
	
	static{
		fingerprints.add(PDF_FINGERPRINT_PATHS);
		fingerprints.add(DOC_FINGERPRINT_PATHS);
		fingerprints.add(XLS_FINGERPRINT_PATHS);
		fingerprints.add(PPT_FINGERPRINT_PATHS);
		fingerprints.add(TEXT_FINGERPRINT_PATHS);
	}

	
	
	public static void main(String[] args) throws IOException {
		
		
		FragmentFilePath paths = new FragmentFilePath(FRAGMENT_INPUT_FOLDER);
		
		for (String path : paths.getAllPaths()) {
			int index = 0;
			float[] accuracies = new float[fingerprints.size()];
			for (String[] fingerprint : fingerprints) {

				float[] avgScore = FingerPrintReader.getScores(fingerprint[0]);
				float[] corrStrengthScore = FingerPrintReader
						.getScores(fingerprint[1]);

				accuracies[index++] = calculateAssuranceLevel(avgScore,corrStrengthScore, path);
			}
			AccuracyHolder holder = new AccuracyHolder(accuracies);
			AdditionalChecker checker = new AdditionalChecker(holder);
			System.out.println(checker.check());
			
		}
		System.out.println("OK");
		
	}
	
	private static float calculateAssuranceLevel(float[] avgScore, float[] corrStrengthScore , String path ) throws IOException{
		Map<String, Float> normalizedFragment = FingerprintCreator.getNormalizedScore(path);
		float[] fragmentScore = AVGScore.valuesToArray(normalizedFragment);
		float assuranceLevel = BFD.getAssuranceLevel(avgScore, corrStrengthScore, fragmentScore);
		return assuranceLevel;
	}

}
