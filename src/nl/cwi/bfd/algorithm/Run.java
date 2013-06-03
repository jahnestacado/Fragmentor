package nl.cwi.bfd.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.algorithm.reader.FingerPrintReader;
import nl.cwi.bfd.fingerprint.AVGScore;
import nl.cwi.bfd.fingerprint.FingerprintCreator;
import nl.cwi.bfd.fingerprint.io.reader.RatioFilter;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class Run {

	private final static String[] typePaths = {"pdf","zip","doc","xls","ppt","mp4","ogg","text","png","jpg"};
	private final static String FRAGMENT_INPUT_FOLDER = "/home/jahn/Desktop/fp/ppt/fragments/";
	// NA vazw ta full fingerprints otan thelw na kanw copy-paste ta classified as TEXT fragments
	

	
	private final static List<String[]> fingerprints = new ArrayList<String[]>();
	private static Results results;
	
	static{
		fingerprints.add(FingerPrintPaths.getPdfFPPaths());
		fingerprints.add(FingerPrintPaths.getDocFPPaths());
		fingerprints.add(FingerPrintPaths.getXlsFPPaths());
		fingerprints.add(FingerPrintPaths.getPptFPPaths());
		fingerprints.add(FingerPrintPaths.getTextFPPaths());
		fingerprints.add(FingerPrintPaths.getZipFPPaths());
		fingerprints.add(FingerPrintPaths.getMp4FPPaths());
		fingerprints.add(FingerPrintPaths.getJpgFPPaths());
		fingerprints.add(FingerPrintPaths.getPngFPPaths());
		fingerprints.add(FingerPrintPaths.getOggFPPaths());
		
	}

	
	
	public static void main(String[] args) throws IOException {
		for(String type : typePaths){
		int numOffragments = 0;
		FragmentFilePath paths = new FragmentFilePath("/home/jahn/Desktop/text_output/"+type+"/");
		System.out.println("******* "+type);
		for (String path : paths.getAllPaths()) {
		//	if(RatioFilter.checkRatio(0, 25, path)){
			//numOffragments++;
			int index = 0;
			float[] accuracies = new float[fingerprints.size()];
			for (String[] fingerprint : fingerprints) {

				float[] avgScore = FingerPrintReader.getScores(fingerprint[0]);
				float[] corrStrengthScore = FingerPrintReader
						.getScores(fingerprint[1]);

				accuracies[index++] = calculateAssuranceLevel(avgScore,corrStrengthScore, path);
			}
			AccuracyHolder holder = new AccuracyHolder(accuracies);
			results = new Results(holder,path);
			results.set();
			
			//}
			
		}
	

		results.getResults();
		System.out.println("OK ");
		results.clearResults();
		}
		
	}
	
	private static float calculateAssuranceLevel(float[] avgScore, float[] corrStrengthScore , String path ) throws IOException{
		Map<String, Float> normalizedFragment = FingerprintCreator.getNormalizedScore(path);
		Map<String, Float> special = new LinkedHashMap<String,Float>();
		special.put("32", normalizedFragment.get("32"));
		special.put("10", normalizedFragment.get("10"));
		special.put("9", normalizedFragment.get("9"));
		special.put("13", normalizedFragment.get("13"));

		//float[] fragmentScore = AVGScore.valuesToArray(normalizedFragment);
		float[] fragmentScore = AVGScore.valuesToArray(special);

		float assuranceLevel = BFD.getAssuranceLevel(avgScore, corrStrengthScore, fragmentScore);
		return assuranceLevel;
	}

}
