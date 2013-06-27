package nl.cwi.dtfc.classifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.algorithm.AccuracyHolder;
import nl.cwi.bfd.algorithm.BFD;
import nl.cwi.bfd.algorithm.FingerPrintPaths;
import nl.cwi.bfd.algorithm.reader.FingerPrintReader;
import nl.cwi.bfd.fingerprint.AVGScore;
import nl.cwi.bfd.fingerprint.FingerprintCreator;
import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class Run {

	private final static String[] typePaths = {"mp4","pdf","text","doc","xls","zip","ppt","ogg","png","jpg"};
	//private final static String FRAGMENT_INPUT_FOLDER = "/home/jahn/Desktop/fp/mp4/fragments/";
	// NA vazw ta full fingerprints otan thelw na kanw copy-paste ta classified as TEXT fragments
	
//	private final static String[] typePaths = {"mp4"};
	private static Integer[] fragmentStream;
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
		 long startTime = System.currentTimeMillis();
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
			results = new Results(holder,path,type,fragmentStream);
			results.set();
		
			}
			
		
	
		long endTime = System.currentTimeMillis();
	    System.out.println("Total execution time: " + (endTime-startTime) + "ms");
		results.getResults();
		System.out.println("OK ");
		results.clearResults();
		}
		
	}
	
	private static float calculateAssuranceLevel(float[] avgScore, float[] corrStrengthScore , String path ) throws IOException{
		fragmentStream = FragmentReader.getFragmentsStream(path);
		Map<String, Float> normalizedFragment = FingerprintCreator.getNormalizedScore(fragmentStream);
		float[] fragmentScore = AVGScore.valuesToArray(normalizedFragment);
		float assuranceLevel = BFD.getAssuranceLevel(avgScore, corrStrengthScore, fragmentScore);
		return assuranceLevel;
	}

}