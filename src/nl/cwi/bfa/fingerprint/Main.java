package nl.cwi.bfa.fingerprint;

import java.io.IOException;
import java.util.Map;

import nl.cwi.bfa.fingerprint.io.reader.FragmentReader;


public class Main {
	private final static String SRC_FOLDER = "/home/jahn/Desktop/thesis/doc/fragments/OSLicQA_3_512_84.77_doc.ext.stats";
	
	public static void main(String[] args) throws IOException{
		Map<String,Integer> freqs = FragmentReader.getFragmentsFreqs(SRC_FOLDER);
		Map<String, Float> normalizedFreqs = MOBNormalization.getNormalizedFreqs(freqs);
		Map<String, Float> compoundNormalizedFreqs = CompoundNormalization.getNormalizedFreqs(normalizedFreqs);
		System.out.println(compoundNormalizedFreqs);
	}
	

}
