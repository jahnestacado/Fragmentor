package nl.cwi.bfd.fingerprint;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MOBNormalization {

	public static Map<String, Float> getNormalizedFreqs(Map<String, Integer> freqs) {
		Map<String, Float> normalizedFreqs = new LinkedHashMap<String, Float>();
		float max = (float) Collections.max(freqs.values());
		for (String byteId : freqs.keySet()) {
			int value = freqs.get(byteId);
			float normalizedValue = value / max;
			normalizedFreqs.put(byteId, normalizedValue);
		}

		return normalizedFreqs;

	}

}
