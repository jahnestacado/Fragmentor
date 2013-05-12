package nl.cwi.bfd.fingerprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;
import nl.cwi.bfd.fingerprint.io.reader.RatioFilter;
import nl.cwi.bfd.fingerprint.io.writer.SaveFingerprint;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class FingerprintCreator {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/fp/doc/fragments/";

	public static void main(String[] args) throws IOException {
		FragmentFilePath paths = new FragmentFilePath(MAIN_FOLDER);
		List<Map<String, Float>> normalizedScores = new ArrayList<Map<String, Float>>();
		int yi=0;
		for (String path : paths.getAllPaths()) {
			
			if(RatioFilter.checkRatio(75, 100, path)){
				normalizedScores.add(getNormalizedScore(path));
				++yi;
				//System.out.println(yi);
			}
		
			
		}
		float[] avgScore =AVGScore.getFreqFingerprint(normalizedScores);
		CorrelationFactor factors = new CorrelationFactor(normalizedScores, avgScore);
		float[] corrStrengthScore = AVGScore.getCorrelationStrengthFingerprint(factors.getCorrFactors());
	    SaveFingerprint.writeToFile(avgScore, corrStrengthScore);
		System.out.println("OK " +yi);
	
	}
	
	public static Map<String, Float> getNormalizedScore(String path) throws IOException{
		Map<String, Integer> freqs = FragmentReader.getFragmentsFreqs(path);
		Map<String, Float> normalizedFreqs = MOBNormalization.getNormalizedFreqs(freqs);
		Map<String, Float> compoundNormalizedFreqs = CompoundNormalization.getNormalizedFreqs(normalizedFreqs);
		return compoundNormalizedFreqs;
	}


}
