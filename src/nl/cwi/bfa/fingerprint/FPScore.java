package nl.cwi.bfa.fingerprint;

import java.util.List;
import java.util.Map;

public class FPScore {
	private float[] FPS;
	private final List<Map<String, Float>> compoundNormalizedFreqs;
	private final int size;
	
	public FPScore(List<Map<String, Float>> compoundNormalizedFreqs){
		this.compoundNormalizedFreqs = compoundNormalizedFreqs;
		size = compoundNormalizedFreqs.size();
		FPS = new float[94];
		createFingerprint();
	}
	
	private void createFingerprint(){
		for(int i=0;i<= size-1; i++){
			int prevNumOfFiles = i;
			float[] newFileScore = valuesToArray(compoundNormalizedFreqs.get(i));
			float[] temp = addArrays(mulArrayElementsWith(FPS,prevNumOfFiles), newFileScore);
			FPS = divArrayElementsWith(temp , prevNumOfFiles);
			
		}
		System.out.println("*********************");
		for(int y = 0;y<=93;y++){
            System.out.print(FPS[y]+",");}
		System.out.println("*********************");
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
	
	private static float[] valuesToArray(Map<String, Float> compoundNormalizedFreqs){
		float[] array = new float[94];
		int index = 0;
		for(String key : compoundNormalizedFreqs.keySet()){
			array[index++]=compoundNormalizedFreqs.get(key);
		}
		return array;
	}
	
	public float[] getFPS(){
		return FPS;
	}
	
	
	

}
