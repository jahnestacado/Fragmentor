package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.List;

public class FragmentListFiltering {

	private final static float PERCENTAGE_THRESHOLD = 0.00f;
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

	public static List<Integer[]> getFilteredFragments(
			List<Integer[]> rawFragments) {
		List<Integer[]> filteredFragments = new ArrayList<Integer[]>();
		for (Integer[] fragment : rawFragments) {
			List<Integer> filteredFragment = new ArrayList<Integer>();
			for (int i = 0; i <= FRAGMENT_SIZE - 1; i++) {
				if (printableChars.contains(fragment[i])) {
					filteredFragment.add(fragment[i]);
				}
			}
			if (checkThreshold(filteredFragment)) {
				filteredFragments.add(toIntArray(filteredFragment));
			}
		}
		return filteredFragments;
	}

	public static List<Float> calculateRatio(List<Integer[]> filteredFragments) {
		List<Float> ratios = new ArrayList<Float>();
		for (Integer[] fragment : filteredFragments) {
			float ratio = (fragment.length * 100.0f / FRAGMENT_SIZE);
			ratios.add(ratio);
		}
		return ratios;
	}

	public static Integer[] toIntArray(List<Integer> list) {
		Integer[] ret = new Integer[list.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = list.get(i);
		return ret;
	}

	public static boolean checkThreshold(List<Integer> fragment) {
		float ratio = (fragment.size() * 100.0f / FRAGMENT_SIZE);
		if (ratio > FragmentListFiltering.PERCENTAGE_THRESHOLD)
			return true;

		return false;
	}

	public static int getUnsignedByteValue(int value) {
		if (value < 0) {
			value = value + 256;
		}
		return value;
	}

}
