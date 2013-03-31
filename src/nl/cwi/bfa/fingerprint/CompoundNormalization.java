package nl.cwi.bfa.fingerprint;

import java.util.Map;

/*
 * passes the frequency distribution through a companding function
 */

public class CompoundNormalization {
	
	public static Map<String, Float> getNormalizedFreqs(Map<String, Float> normalizedFreqs) {
		for (String byteId : normalizedFreqs.keySet()) {
			double value = normalizedFreqs.get(byteId);
			double β = 1.5f;
			float compoundNormalizedValue =  (float) Math.pow(value, ( 1 / β));
			normalizedFreqs.put(byteId, compoundNormalizedValue);
		}

		return normalizedFreqs;

	}


}
