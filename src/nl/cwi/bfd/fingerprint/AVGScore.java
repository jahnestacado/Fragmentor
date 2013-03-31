package nl.cwi.bfd.fingerprint;

import java.util.List;
import java.util.Map;

public class AVGScore {
	private final static int arraySize = 94;
	
	
	public static float[] getFreqFingerprint(List<Map<String, Float>> compoundNormalizedFreqs){
		float[] avgScores = new float[arraySize];
		for(int i=0;i<= compoundNormalizedFreqs.size()-1; i++){
			int prevNumOfFiles = i;
			float[] newFileScore = valuesToArray(compoundNormalizedFreqs.get(i));
			float[] temp = addArrays(mulArrayElementsWith(avgScores,prevNumOfFiles), newFileScore);
			avgScores = divArrayElementsWith(temp , prevNumOfFiles);
		}
		return avgScores;
	}
	
	public static float[] getCorrelationStrengthFingerprint(List<float[]> correlationFactors){
		float[] avgScores = new float[arraySize];
		for(int i=0;i<= correlationFactors.size()-1; i++){
			int prevNumOfFiles = i;
			float[] temp = addArrays(mulArrayElementsWith(avgScores,prevNumOfFiles), correlationFactors.get(i));
			avgScores = divArrayElementsWith(temp , prevNumOfFiles);
		}
		return avgScores;
	}
	
	public static float[] mulArrayElementsWith(float[] array, int numOfFiles){
		for(int i=0; i<array.length; i++) {
			  array[i] = array[i] * numOfFiles;
			}
		return array;
	}
	
	
	public static float[] divArrayElementsWith(float[] array, int numOfFiles){
		for(int i=0; i<array.length; i++) {
			  array[i] = array[i] / (numOfFiles + 1.0f);
			}
		return array;
	}
	
	
	public static float[] addArrays(float[] oldArray, float[] currentArray){
		for(int i=0; i < currentArray.length; i++) {
			currentArray[i] = oldArray[i] + currentArray[i];
			}
		return currentArray;
	}
	
	public static float[] valuesToArray(Map<String, Float> compoundNormalizedFreqs){
		float[] array = new float[94];
		int index = 0;
		for(String key : compoundNormalizedFreqs.keySet()){
			array[index++]=compoundNormalizedFreqs.get(key);
		}
		return array;
	}
	
	
	
	
	

}
