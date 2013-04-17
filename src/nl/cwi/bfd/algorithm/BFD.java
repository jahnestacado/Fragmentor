package nl.cwi.bfd.algorithm;

import nl.cwi.bfd.fingerprint.CorrelationFactor;


public class BFD {

	public static float getAssuranceLevel(float[] avgScore,float[] corrStrengthScore, float[] fragmentScore) {
		float[] difference = CorrelationFactor.subArrays(fragmentScore,avgScore);
		float[] corrFactors = CorrelationFactor.getBellCurveOfArray(difference);
	
		return arrayAvg(corrFactors);

	}
	
	public static float arrayAvg(float[] array) {
		float sum = 0.0f;
		for (float value : array) {
			sum += value;
		}
		float avg = sum / array.length;
		return avg;
	}
}
