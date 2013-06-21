package nl.cwi.entropy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import nl.cwi.bfd.fingerprint.io.reader.FragmentReader;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class Run {
	private final static String INPUT_FOLDER ="/home/jahn/Desktop/text_output/xls/";

	
	public static void main(String[] args) throws IOException {
		FragmentFilePath paths = new FragmentFilePath(INPUT_FOLDER);
		for (String path : paths.getAllPaths()) {
			//System.out.println(path);
			capitalDistance(path);
		}
		System.out.println("ok");
	}


	private static void capitalDistance(String path) throws IOException {
		Integer[] content = getFragmentsContent(path);
		Distance avg = new Distance(content);
		//System.out.println("Mean: "+avg.getMean()+" Median: "+avg.getMedian());
		
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
	
	
	

}
