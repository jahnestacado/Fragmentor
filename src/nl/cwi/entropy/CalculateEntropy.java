package nl.cwi.entropy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;

public class CalculateEntropy {

	public static double getFragmentsEntropy(String path) throws IOException{
		Integer[] fragment = getFragmentsContent(path);
		return calculateShannonEntropy(Distance.arrayToList(fragment));

	}
	
	public static double getFragmentsEntropy(Integer[] fragment) throws IOException{
		return calculateShannonEntropy(Distance.arrayToList(fragment));

	}
	
	public static Integer[] getFragmentsContent(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		int index = 0;
		Integer[] content = new Integer[FragmentReader.FRAGMENT_SIZE];
		while ((line = reader.readLine()) != null) {
			content[index] = Integer.valueOf(line.trim());
			index++;
		}
		reader.close();
		return content;
	}
	
	public static String getFragmentsContentAsStrings(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		String content = null;
		while ((line = reader.readLine()) != null) {
			//int decimal = Integer.valueOf(line.trim());
			content+= line.trim();
		}
		reader.close();
		return content;
	}
	
	
	private static Double calculateShannonEntropy(List<Integer> fragment) {
	  //  long startTime = System.currentTimeMillis();

		  Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		  // count the occurrences of each value
		  for (Integer sequence : fragment) {
		    if (!map.containsKey(sequence)) {
		      map.put(sequence, 0);
		    }
		    map.put(sequence, map.get(sequence) + 1);
		  }
		 
		  // calculate the entropy
		  Double result = 0.0;
		  for (Integer sequence : map.keySet()) {
		    Double frequency = (double) map.get(sequence) / fragment.size();
		    result -= frequency * (Math.log(frequency) / Math.log(2));
		  }
		//  long endTime = System.currentTimeMillis();
		 // System.out.println("Total execution time: " + (endTime-startTime) + "ms");
		  return result;
		}
	
	
}
