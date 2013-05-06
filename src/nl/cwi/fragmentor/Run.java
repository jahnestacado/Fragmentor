package nl.cwi.fragmentor;

import java.io.IOException;
import java.util.List;

import nl.cwi.fragmentor.io.FragmentFilePath;
import nl.cwi.fragmentor.io.ReadFile;
import nl.cwi.fragmentor.io.WriteFile;

public class Run {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/thesis/";
	//private final static String MAIN_FOLDER = "/media/jahn/1234-5678/thesis/";

	
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
			List<Integer[]> filteredFragments = FragmentListFiltering.getFilteredFragments(fragments);
			List<Float> ratios = FragmentListFiltering.calculateRatio(filteredFragments);
			
		 // List<LinkedHashMap<Integer, Integer>> stats = counter.getStats();

		 //   ByteInstanceCounter counter = new ByteInstanceCounter(filteredFragments, info, ratios);
		  
		    for(int i=0;i<=ratios.size()-1;i++){
			WriteFile write = new WriteFile(i,fragments.get(i),ratios.get(i), info);
	        write.produceContentOutput();
		    }
	        
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	
	
}
