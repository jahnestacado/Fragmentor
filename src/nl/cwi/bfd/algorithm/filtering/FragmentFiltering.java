package nl.cwi.bfd.algorithm.filtering;

import java.util.ArrayList;
import java.util.List;
import nl.cwi.fragmentor.FragmentListFiltering;




public class FragmentFiltering {

	private final static Integer NEWLINE = 10;
	private final static Integer TAB = 9;
	private final static Integer CARRIAGE_RETURN = 13;
	private final static List<Integer> printableChars = new ArrayList<Integer>();
	private final static int FRAGMENT_SIZE = 512;

	//** initialize array content comprised of all printables byte characters +
	//** newline, tab and carriage_return
	static {
		for (int i = 36; i <= 126; i++) {
			printableChars.add(i);
		}
		printableChars.add(NEWLINE);
		printableChars.add(TAB);
		printableChars.add(CARRIAGE_RETURN);
	}

	public static Integer[] getFilteredFragment(Integer[] rawFragment) {
		List<Integer> filteredFragment = new ArrayList<Integer>();
		for (int i = 0; i <= FRAGMENT_SIZE - 1; i++) {
			if (printableChars.contains(rawFragment[i])) {
				filteredFragment.add(rawFragment[i]);
			}
		}
		return FragmentListFiltering.toIntArray(filteredFragment);
	}

	

	

}
