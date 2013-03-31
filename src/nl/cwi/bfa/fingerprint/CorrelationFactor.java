package nl.cwi.bfa.fingerprint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CorrelationFactor {
	private final List<Map<String, Float>> normalizedScores;
	private final float[] avgScore;
	private final List<float[]> correlationFactors = new ArrayList<float[]>();
	private final static float σ = 0.0375f;
	
	public CorrelationFactor(List<Map<String, Float>> normalizedScores, float[] avgScore){
		this.normalizedScores = normalizedScores;
		this.avgScore = avgScore;
		calculateCorrFactors();
	}
	
	private void calculateCorrFactors(){
		for(Map<String, Float> byteMap : normalizedScores){
			float[] score = AVGScore.valuesToArray(byteMap);
			float[] difference = subArrays(score, avgScore);
			correlationFactors.add(getBellCurveofArray(difference));
		}
	}

	private float[] getBellCurveofArray(float[] score) {
		for (int i = 0; i <= score.length - 1; i++) {
			float power = -(float) (Math.pow(score[i], 2) / (2 * Math.pow(σ, 2)));
			score[i] = (float) Math.pow(Math.E, power);
		}
		return score;
	}
	
	public static float[] subArrays(float[] currentScore, float[] avgScore){
		for(int i=0; i < currentScore.length; i++) {
			currentScore[i] = currentScore[i] - avgScore[i] ;
			}
		return currentScore;
	}
	
	public List<float[]> getCorrFactors(){
		return correlationFactors;
	}

}
