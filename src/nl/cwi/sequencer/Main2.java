package nl.cwi.sequencer;

import java.io.IOException;
import java.util.List;

import nl.cwi.fragmentor.FragmentFactory;
import nl.cwi.fragmentor.io.ReadFile;

public class Main2 {

	
	public static void main(String[] args) {
		sequencer("/home/jahn/Desktop/thesis/pdf/process2.pdf");
			/**FilePath paths = new FilePath();
			for(String path:paths.getAllPaths()){
				System.out.println(path);
			}
			**/
	}
	
	
	private static void sequencer(String path) {
		try {
			ReadFile reader = new ReadFile(path);
			FragmentFactory factory = new FragmentFactory(reader.fileToBytes());
            List<byte[]> fragments = factory.getFileFragments();
			Sequencer sequencer = new Sequencer(fragments);
			List<List<String>>  fragmentSequences = sequencer.getSequencesInFragments();
			for(List<String> list:fragmentSequences){
				for(String s : list){
					System.out.println(s);
				}
				System.out.println("end of fragment****");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
}
