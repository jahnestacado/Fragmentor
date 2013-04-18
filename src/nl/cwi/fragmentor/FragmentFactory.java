package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentFactory {

	private final byte[] fileContent;
	private final static int FRAGMENT_SIZE = 512;
	private List<Integer[]> fileFragments;

	public FragmentFactory(byte[] fileContent) {
		this.fileContent = fileContent;
		createFragments();
	}

	private void createFragments() {
		List<Integer[]> fragments = new ArrayList<Integer[]>();
		int size = fileContent.length - 1;

		for (int i = 0; i <= size - FRAGMENT_SIZE; i += FRAGMENT_SIZE) {
			byte[] cluster = new byte[FRAGMENT_SIZE];
			cluster = Arrays.copyOfRange(fileContent, i, i + FRAGMENT_SIZE);
			fragments.add(convertToIntArray(cluster));
		}
		fileFragments = fragments;
	}

	public List<Integer[]> getFileFragments() {
		return fileFragments;
	}

	
	public int getFragmentSize(){
		return FRAGMENT_SIZE;
	}
	
	private static Integer[] convertToIntArray(byte[] fragment){
		Integer[] fragmentToInt = new Integer[FRAGMENT_SIZE];
		for(int i =0; i<=FRAGMENT_SIZE-1; i++){
			int currentByte =(int) fragment[i];
			if(currentByte<0){
				currentByte = FragmentFiltering.getUnsignedByteValue(currentByte);
			}
			fragmentToInt[i] =currentByte;
		}
		return fragmentToInt;
	}

}
