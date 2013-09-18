package nl.cwi.lcs;

import java.io.IOException;
import java.util.List;

import nl.cwi.entropy.CalculateEntropy;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class CreateFingerprint {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/text_output/xls/fragments/";

	public static void main(String[] args) throws IOException {
		FragmentFilePath paths = new FragmentFilePath(MAIN_FOLDER);
		List<String> allPaths = paths.getAllPaths();
	
		for(String path : allPaths){
			Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		}
	
		//SaveFingerprint.writeToFile(avgScore, corrStrengthScore);
		System.out.println("OK");
	}
	
}
