package nl.cwi.bfa.fingerprint.io.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class FragmentReader {
	
	
	public static Map<String,Integer> getFragmentsFreqs(String path) throws IOException{
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
	
	public static String[] parseLine(String line){
		String[] dataOfLine = line.split(":");
		dataOfLine[0] = dataOfLine[0].trim();
		dataOfLine[1] = dataOfLine[1].trim();
		
		return dataOfLine;
				
	}
	
	
	


}
