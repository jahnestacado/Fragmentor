package nl.cwi.entropy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Distance {
	private final Integer[] fragment;
	private final static List<Integer> capitalChars = new ArrayList<Integer>();
	private float mean;
	private float median;
	static{
		//for(int k=65; k<=90; k++){
			capitalChars.add(13);
		//}
	}

	
	public Distance(Integer[] fragment) {
		this.fragment = fragment;
		System.out.println(calculateShannonEntropy(arrayToList(fragment)));
		//List<Integer> distances = setDistances(getCapitalIndices());
		//mean = getListMean(distances);
		//median = getListMedian(distances);
	}
	
	
	private List<Integer> setDistances(List<Integer> capitalIndices) {
		List<Integer> distances = new ArrayList<Integer>();
		for(int y = 0; y<= capitalIndices.size()-2; y++){
			int dif = capitalIndices.get(y) - capitalIndices.get(y+1);
			if (dif <= 1)
				distances.add(Math.abs(dif));
		}
		return distances;
	}

	private List<Integer> getCapitalIndices() {
		List<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i <= fragment.length - 1; i++) {
			if (capitalChars.contains(fragment[i])) {
				indices.add(i);
			}
		}
		return indices;
	}
	
	public final static double getListMean(List<Double> list){
		float sum = 0;
		int size = list.size();
		for(int i=0; i<= size -1;i++){
			sum += list.get(i);
		}
		float avg = (float) sum / size;
		return avg;
	}
	
	public final static double getListMedian(List<Double> list) {
		Collections.sort(list);
		int size = list.size();
		if( size == 0) return 0;
		int middle = list.size() / 2;
		if (size % 2 == 1) {
			return list.get(middle);
		}

		return (list.get(middle) - 1 + list.get(middle) / 2.0f);

	}
	
	public float getMean(){
		return mean;
	}
	
	public float getMedian(){
		return median;
	}
	
	public static Double calculateShannonEntropy(List<Integer> values) {
		  Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		  // count the occurrences of each value
		  for (Integer sequence : values) {
		    if (!map.containsKey(sequence)) {
		      map.put(sequence, 0);
		    }
		    map.put(sequence, map.get(sequence) + 1);
		  }
		 
		  // calculate the entropy
		  Double result = 0.0;
		  for (Integer sequence : map.keySet()) {
		    Double frequency = (double) map.get(sequence) / values.size();
		    result -= frequency * (Math.log(frequency) / Math.log(2));
		  }
		 
		  return result;
		}

	public static List<Integer>  arrayToList(Integer[] array){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<= array.length-1;i++){
			list.add(array[i]);
		}
		return list;
	}
}
