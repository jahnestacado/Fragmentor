package nl.cwi.fragmentor;

import java.io.IOException;
import java.util.List;

import nl.cwi.fragmentor.io.FragmentFilePath;
import nl.cwi.fragmentor.io.ReadFile;

public class Run {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/thesis/";
	
	public static void main(String[] args) {
		FragmentFilePath paths = new FragmentFilePath(MAIN_FOLDER);
		for (String path : paths.getAllPaths()) {
			System.out.println(path);
			getFragments(path);
		}
		System.out.println("ok");
	}
	
	private static void getFragments(String path) {
		
		try {
			ReadFile reader = new ReadFile(path);
			FileInfo info = new FileInfo();
			info.setType(reader.getType());
			info.setName(reader.getName());
			FragmentFactory factory = new FragmentFactory(reader.fileToBytes());
			info.setSize(factory.getFragmentSize());
		
			List<Integer[]> fragments = factory.getFileFragments();
			List<Integer[]> filteredFragments = FragmentFiltering.getFilteredFragments(fragments);
			List<Float> ratios = FragmentFiltering.calculateRatio(filteredFragments);
			
		 // List<LinkedHashMap<Integer, Integer>> stats = counter.getStats();

		    ByteInstanceCounter counter = new ByteInstanceCounter(filteredFragments, info, ratios);
		  
		    
		//	WriteFile write = new WriteFile(filteredFragments,ratios, info, stats);
		//	write.produceOutput();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	
	
}
