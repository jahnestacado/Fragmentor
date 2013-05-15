package nl.cwi.bfd.fingerprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;
import nl.cwi.bfd.fingerprint.io.writer.SaveFingerprint;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class FingerprintCreator {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/fp/xls/fragments/";
	private static int counter = 0;
	static float[] corrStrengthScore;
	private static float[] avgScore;
	private static boolean isFirstTime = true;

	public static void main(String[] args) throws IOException {
		FragmentFilePath paths = new FragmentFilePath(MAIN_FOLDER);
		createAVGFingerprint(paths.getAllPaths());
		isFirstTime = true;
		createCorrStrFingerprint(paths.getAllPaths());
		SaveFingerprint.writeToFile(avgScore, corrStrengthScore);
		System.out.println("OK");
	}

	private static void createCorrStrFingerprint(List<String> paths)
			throws IOException {
		List<Map<String, Float>> normalizedScores = new ArrayList<Map<String, Float>>();
		for (String path : paths) {
			if (counter == 50000) {
				counter = 0;
				createCorrStr(normalizedScores);
				normalizedScores.clear();
			}
			normalizedScores.add(getNormalizedScore(path));
			counter++;
		}
		createCorrStr(normalizedScores);
	}

	private static void createCorrStr(List<Map<String, Float>> normalizedScores) {
		CorrelationFactor factors = new CorrelationFactor(normalizedScores,avgScore);
		if (isFirstTime) {
			isFirstTime = false;
			corrStrengthScore = AVGScore
					.getCorrelationStrengthFingerprint(factors.getCorrFactors());
		} else {
			corrStrengthScore = AVGScore
					.getCachedCorrelationStrengthFingerprint(factors
							.getCorrFactors());
		}
	}

	private static void createAVG(List<Map<String, Float>> normalizedScores) {
		if (isFirstTime) {
			avgScore = AVGScore.getFreqFingerprint(normalizedScores);
			isFirstTime = false;
		} else {
			avgScore = AVGScore.getCachedFreqFingerprint(normalizedScores);
		}

	}

	public static Map<String, Float> getNormalizedScore(String path)
			throws IOException {
		Map<String, Integer> freqs = FragmentReader.getFragmentsFreqs(path);
		Map<String, Float> normalizedFreqs = MOBNormalization
				.getNormalizedFreqs(freqs);
		Map<String, Float> compoundNormalizedFreqs = CompoundNormalization
				.getNormalizedFreqs(normalizedFreqs);
		return compoundNormalizedFreqs;
	}

	private static void createAVGFingerprint(List<String> paths)
			throws IOException {
		List<Map<String, Float>> normalizedScores = new ArrayList<Map<String, Float>>();
		for (String path : paths) {
			if (counter == 25) {
				counter = 0;
				createAVG(normalizedScores);
				normalizedScores.clear();
			}
			normalizedScores.add(getNormalizedScore(path));
			counter++;
		}
		createAVG(normalizedScores);
		normalizedScores = null;
		counter = 0;
	}

}
