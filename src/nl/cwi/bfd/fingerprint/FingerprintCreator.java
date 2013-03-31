package nl.cwi.bfd.fingerprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;
import nl.cwi.bfd.fingerprint.writer.SaveFingerprint;
import nl.cwi.fragmentor.io.FilePath;

public class FingerprintCreator {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/thesis/pdf/fragments/";

	public static void main(String[] args) throws IOException {
		FilePath paths = new FilePath(MAIN_FOLDER);
		List<Map<String, Float>> normalizedScores = new ArrayList<Map<String, Float>>();
		for (String path : paths.getAllPaths()) {
			normalizedScores.add(getNormalizedScore(path));
		}
		float[] avgScore =AVGScore.getFreqFingerprint(normalizedScores);
		CorrelationFactor factors = new CorrelationFactor(normalizedScores, avgScore);
		float[] corrStrengthScore = AVGScore.getCorrelationStrengthFingerprint(factors.getCorrFactors());
	    SaveFingerprint.writeToFile(avgScore, corrStrengthScore);
		
	
	}
	
	private static Map<String, Float> getNormalizedScore(String path) throws IOException{
		Map<String, Integer> freqs = FragmentReader.getFragmentsFreqs(path);
		Map<String, Float> normalizedFreqs = MOBNormalization.getNormalizedFreqs(freqs);
		Map<String, Float> compoundNormalizedFreqs = CompoundNormalization.getNormalizedFreqs(normalizedFreqs);
		return compoundNormalizedFreqs;
	}


}
