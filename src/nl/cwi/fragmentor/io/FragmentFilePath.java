package nl.cwi.fragmentor.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FragmentFilePath {
	private final List<String> paths;
	private final static String OUTPUT_FOLDER = "fragments";
	private final static String FINGERPRINT_FOLDER = "fingerprints";


	public FragmentFilePath(String path) {
		paths = new ArrayList<String>();
		setPaths(path);
	}

	private void setPaths(String path) {
		File folder = new File(path);
		String[] names = folder.list();
		for (String name : names) {
			if (name.equals(OUTPUT_FOLDER) || name.equals(FINGERPRINT_FOLDER)){
				//skip that file
			}
			else if (!name.contains(".")) {
				setPaths(path + name + "/");
			} else
				paths.add(path + name);
		}
	}

	public List<String> getAllPaths() {
		return paths;
	}

}
