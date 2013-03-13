package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentFactory {

	private final byte[] fileContent;
	private final static int FRAGMENT_SIZE = 512;
	private List<byte[]> fileFragments;

	public FragmentFactory(byte[] fileContent) {
		this.fileContent = fileContent;
		createFragments();
	}

	private void createFragments() {
		List<byte[]> fragments = new ArrayList<byte[]>();
		int size = fileContent.length - 1;

		for (int i = 0; i <= size - FRAGMENT_SIZE; i += FRAGMENT_SIZE) {
			byte[] cluster = new byte[FRAGMENT_SIZE];
			cluster = Arrays.copyOfRange(fileContent, i, i + FRAGMENT_SIZE);
			fragments.add(cluster);
		}
		fileFragments = fragments;
	}

	public List<byte[]> getFileFragments() {
		return fileFragments;
	}
	
	public int getFragmentSize(){
		return FRAGMENT_SIZE;
	}

}
