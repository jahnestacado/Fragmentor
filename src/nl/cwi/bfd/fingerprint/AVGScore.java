package nl.cwi.bfd.fingerprint;

import java.util.List;
import java.util.Map;

public class AVGScore {
	private final static int arraySize = 98; // size of printable ASCII charachters set
	private static float[] avgScores = new float[arraySize];
	private static int exSize = 0;
	private static float[] corrStr = new float[arraySize];

	
	
	public static float[] getFreqFingerprint(List<Map<String, Float>> compoundNormalizedFreqs){
		exSize = 0;
		System.out.println("Finger :"+exSize);
		for(int i=0;i<= compoundNormalizedFreqs.size()-1; i++){
			int prevNumOfFiles = i;
			float[] newFileScore = valuesToArray(compoundNormalizedFreqs.get(i));		
			float[] temp = addArrays(mulArrayElementsWith(avgScores,prevNumOfFiles), newFileScore);
			avgScores = divArrayElementsWith(temp , prevNumOfFiles);
		}
		 exSize += compoundNormalizedFreqs.size();

		return avgScores;
	}
	
	
	public static float[] getCorrelationStrengthFingerprint(List<float[]> correlationFactors){
		exSize = 0;
		for(int i=0;i<= correlationFactors.size()-1; i++){
			int prevNumOfFiles = i;
			float[] temp = addArrays(mulArrayElementsWith(corrStr,prevNumOfFiles), correlationFactors.get(i));
			corrStr = divArrayElementsWith(temp , prevNumOfFiles);
			
		}
		 exSize += correlationFactors.size();
		
		return corrStr;
	}
	
	
	public static float[] getCachedFreqFingerprint(List<Map<String, Float>> compoundNormalizedFreqs){
		for(int i=0;i<= compoundNormalizedFreqs.size()-1; i++){
			int prevNumOfFiles = exSize+i;
			float[] newFileScore = valuesToArray(compoundNormalizedFreqs.get(i));
			float[] temp = addArrays(mulArrayElementsWith(avgScores,prevNumOfFiles), newFileScore);
			avgScores = divArrayElementsWith(temp , prevNumOfFiles);
			
		}
		exSize += compoundNormalizedFreqs.size();
       	return avgScores;
	}
	
	public static float[] getCachedCorrelationStrengthFingerprint(List<float[]> correlationFactors){
		for(int i=0;i<= correlationFactors.size()-1; i++){
			int prevNumOfFiles = exSize+i;
			float[] temp = addArrays(mulArrayElementsWith(corrStr,prevNumOfFiles), correlationFactors.get(i));
			System.out.println(temp[0]);
			corrStr = divArrayElementsWith(temp , prevNumOfFiles);
		}
		exSize += correlationFactors.size();
		return corrStr;
	}
	
	public static float[] mulArrayElementsWith(float[] array, int numOfFiles){
		for(int i=0; i < array.length; i++) {
			  array[i] = array[i] * numOfFiles;
			}
		return array;
	}
	
	
	public static float[] divArrayElementsWith(float[] array, int numOfFiles){
		for(int i=0; i < array.length; i++) {
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
		float[] array = new float[arraySize];
		int index = 0;
		for(String key : compoundNormalizedFreqs.keySet()){
			array[index++]=compoundNormalizedFreqs.get(key);
		}
		return array;
	}
	
	
	
	
	
	
	

}
