package nl.cwi.fragmentor;

import java.io.IOException;
import java.util.List;

import nl.cwi.fragmentor.io.FilePath;
import nl.cwi.fragmentor.io.ReadFile;
import nl.cwi.fragmentor.io.WriteFile;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
			
			FilePath paths = new FilePath();
			for(String s:paths.getAllPaths()){
				create(s);
			}
		

	}
	
	private static void create(String path){
		try {
		ReadFile reader = new ReadFile(path);
		FileInfo info = new FileInfo();
		info.setType(reader.getType());
		info.setName(reader.getName());
		FragmentFactory factory = new FragmentFactory(reader.fileToBytes());
		info.setSize(factory.getFragmentSize());
		List<byte[]> fragments=factory.getFileFragments();
		ByteInstanceCounter instanceCounter = new ByteInstanceCounter(fragments);
		FragmentFiltering filter = new FragmentFiltering( instanceCounter.getCounter(),fragments);
		
		WriteFile write =new WriteFile(filter.getQualifiedFragments(),filter.getQualifiedFragmentStats(),info);
		write.produceOutput();
	}
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
}
