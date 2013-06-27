package nl.cwi.bfd.fingerprint.io.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import nl.cwi.bfd.algorithm.filtering.FragmentFiltering;
import nl.cwi.fragmentor.ByteInstanceCounter;

public class FragmentReader {
	public final static int FRAGMENT_SIZE = 512;
	
	/*public static Map<String,Integer> getFragmentsFreqs(String path) throws IOException{
		Map<String,Integer> freqMapper = new LinkedHashMap<String,Integer>();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] freqOfByte = parseLine(line);
			freqMapper.put(freqOfByte[0], Integer.parseInt(freqOfByte[1]));
		}
		reader.close();
		return freqMapper;
	}
	*/
	
	
	public static Integer[] getFragmentsStream(String path) throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		int index = 0;
		Integer[] rawFragment = new Integer[FRAGMENT_SIZE];
		while ((line = reader.readLine()) != null) {
			rawFragment[index] = Integer.valueOf(line.trim());
			index++;
		}
		reader.close();
		return rawFragment;
	}
	
	
	public static Map<String,Integer> getFragmentsFreqs(Integer[] rawFragment) throws IOException{
		
		Integer[] filteredFragment = FragmentFiltering.getFilteredFragment(rawFragment);
		Map<String,Integer> freqMapper = new ByteInstanceCounter(filteredFragment).getScore();
		return freqMapper;
	}
	
	
	public static Map<String,Integer> getFragmentsFreqs(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		int index = 0;
		Integer[] rawFragment = new Integer[FRAGMENT_SIZE];
		while ((line = reader.readLine()) != null) {
			rawFragment[index] = Integer.valueOf(line.trim());
			index++;
		}
		reader.close();
		Integer[] filteredFragment = FragmentFiltering.getFilteredFragment(rawFragment);
		Map<String,Integer> freqMapper = new ByteInstanceCounter(filteredFragment).getScore();
		return freqMapper;
	}
	

	
	public static String[] parseLine(String line){
		String[] dataOfLine = line.split(":");
		dataOfLine[0] = dataOfLine[0].trim();
		dataOfLine[1] = dataOfLine[1].trim();
		
		return dataOfLine;
				
	}
	
	
	


}
