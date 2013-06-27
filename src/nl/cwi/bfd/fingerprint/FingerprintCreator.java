package nl.cwi.bfd.fingerprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;
import nl.cwi.bfd.fingerprint.io.reader.RatioFilter;
import nl.cwi.bfd.fingerprint.io.writer.SaveFingerprint;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class FingerprintCreator {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/fp/xls/fragments/";
	private static int counter = 0;
	static float[] corrStrengthScore;
	private static float[] avgScore;
	private static boolean isFirstTime = true;
	private static int index = 0;
	private final static int CACHE_SIZE = 25000;

	public static void main(String[] args) throws IOException {
		FragmentFilePath paths = new FragmentFilePath(MAIN_FOLDER);
		List<String> allPaths = paths.getAllPaths();
		//allPaths = getSubsetWithRatio(allPaths, 0, 25);
		createAVGFingerprint(allPaths);
		isFirstTime = true;
		createCorrStrFingerprint(allPaths);
		SaveFingerprint.writeToFile(avgScore, corrStrengthScore);
		System.out.println("OK");
	}

	private static void createCorrStrFingerprint(List<String> paths)
			throws IOException {
		List<Map<String, Float>> normalizedScores = new ArrayList<Map<String, Float>>();
		for (String path : paths) {
			if (counter == CACHE_SIZE) {
				counter = 0;
				createCorrStr(normalizedScores);
				normalizedScores.clear();
			}
			normalizedScores.add(getNormalizedScore(path));
			counter++;
		}
		createCorrStr(normalizedScores);
		System.out.println("End of Phaze#2");
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

	public static Map<String, Float> getNormalizedScore(Integer[] fragment)
			throws IOException {
		Map<String, Integer> freqs = FragmentReader.getFragmentsFreqs(fragment);
		Map<String, Float> normalizedFreqs = MOBNormalization
				.getNormalizedFreqs(freqs);
		Map<String, Float> compoundNormalizedFreqs = CompoundNormalization
				.getNormalizedFreqs(normalizedFreqs);
		return compoundNormalizedFreqs;
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
		
			if (counter == CACHE_SIZE) {
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
		System.out.println("End of Phaze#1");
	}
	
	private static  List<String> getSubsetWithRatio(List<String> paths, int minRatio,int maxRatio) {
		List<String> filteredRatios = new LinkedList<String>();
		for (String path : paths) {
			if (RatioFilter.checkRatio(minRatio, maxRatio, path)) {
				System.out.println(++index);
				filteredRatios.add(path);
			}
		}
		return filteredRatios;
	}
	
}
