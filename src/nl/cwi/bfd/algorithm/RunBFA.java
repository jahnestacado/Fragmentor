package nl.cwi.bfd.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.algorithm.reader.FingerPrintReader;
import nl.cwi.bfd.fingerprint.AVGScore;
import nl.cwi.bfd.fingerprint.FingerprintCreator;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class RunBFA {

	private final static String[] typePaths = {"doc","xls","pdf","zip","ppt","mp4","ogg","text","png","jpg"};
	private final static String FRAGMENT_INPUT_FOLDER = "/home/jahn/Desktop/fp/ppt/fragments/";
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
		FragmentFilePath paths = new FragmentFilePath("/home/jahn/Desktop/text_output/"+type+"/");
		System.out.println("******* "+type);
		System.out.print(paths.getAllPaths().size());
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
			results = new Results(holder,path);
			results.set();
			
		}
	

		results.getResults();
		System.out.println("OK ");
		results.clearResults();
		}
		
	}
	
	private static float calculateAssuranceLevel(float[] avgScore, float[] corrStrengthScore , String path ) throws IOException{
		Map<String, Float> normalizedFragment = FingerprintCreator.getNormalizedScore(path);
		float[] fragmentScore = AVGScore.valuesToArray(normalizedFragment);
		float assuranceLevel = BFD.getAssuranceLevel(avgScore, corrStrengthScore, fragmentScore);
		return assuranceLevel;
	}

}