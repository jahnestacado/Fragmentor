package nl.cwi.lcs;

import java.util.HashMap;
import java.util.Map;

public class ByteInstanceAverage {
	private static boolean isFirstFragment = true;
	//private static Integer[] avgValues = new Integer[512];
	private static Map<Integer,Double> totalByteInstaceCounter = new HashMap<Integer,Double>();

	
	public static void formAverageFingerprint(Integer[] fragment){
		 Map<Integer,Double> instanceCounter =  getByteInstanceCount(fragment);
		if(isFirstFragment){
		 totalByteInstaceCounter = instanceCounter;
		 isFirstFragment = false;
		 return;
		}
		for(int b : totalByteInstaceCounter.keySet()){
			totalByteInstaceCounter.put(b, totalByteInstaceCounter.get(b) + instanceCounter.get(b));
		}
	}
	
	private static void initMap(Map<Integer,Double> map){
		for(int i = 0 ; i<= 255; i ++){
			map.put(i, 0.0);
		}
	}
	
	private static Map<Integer,Double> getByteInstanceCount(Integer[] fragment){
		Map<Integer,Double> instanceCounter = new HashMap<Integer,Double>();
		initMap(instanceCounter);
		for(int b : fragment){
			instanceCounter.put(b, instanceCounter.get(b)+1.0);
		}
		return instanceCounter;
	}
	
	
}
