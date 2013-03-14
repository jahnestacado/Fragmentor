package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FragmentFiltering {
	private final List<LinkedHashMap<Byte, Integer>> instanceCounter;
	private final List<LinkedHashMap<Byte, Integer>> qualifiedFragmentStats;
	private final List<byte[]> initFragments;
	private final Map<byte[], Float> qualifiedFragments;
	private final static float PERCENTAGE_THRESHOLD = 85.00f;
	private final static byte NEWLINE = 10;
	private final static byte TAB = 9;
	private final static byte CARRIAGE_RETURN = 13;
	private final static List<Byte> printableChars = new ArrayList<Byte>();

	// initialize arrays content comprised of all printables byte characters +
	// newline, tab and carriage_return
	static {
		for (byte i = 36; i <= 126; i++) {
			printableChars.add(i);
		}
		printableChars.add(NEWLINE);
		printableChars.add(TAB);
		printableChars.add(CARRIAGE_RETURN);
	}

	public FragmentFiltering(
			List<LinkedHashMap<Byte, Integer>> instanceCounter,
			List<byte[]> initFragments) {
		this.instanceCounter = instanceCounter;
		this.initFragments = initFragments;
		this.qualifiedFragmentStats = new ArrayList<LinkedHashMap<Byte, Integer>>();
		this.qualifiedFragments = new LinkedHashMap<byte[], Float>();
		filtering();

	}

	private void filtering() {
		int totalBytes;
		int numOfInstances;
		int fragmentIndex = 0;
		for (LinkedHashMap<Byte, Integer> fragmentMap : instanceCounter) {
			totalBytes = 0;
			numOfInstances = 0;
			for (byte key : fragmentMap.keySet()) {
				int frequency = fragmentMap.get(key);
				if (printableChars.contains(key)) {
					numOfInstances += frequency;
					totalBytes += frequency;
				} else
					totalBytes += frequency;

			}
			float percentage = getRatio(totalBytes, numOfInstances);
			if (percentage >= PERCENTAGE_THRESHOLD) {
				qualifiedFragmentStats.add(fragmentMap);
				qualifiedFragments.put(initFragments.get(fragmentIndex),
						percentage);
			}
			fragmentIndex++;
		}

	}

	public List<LinkedHashMap<Byte, Integer>> getQualifiedFragmentStats() {
		return qualifiedFragmentStats;
	}

	public Map<byte[], Float> getQualifiedFragments() {
		return qualifiedFragments;
	}

	public static float getRatio(int total, int frequency) {
		// System.out.println("**"+total+"**"+" **+"+frequency+"**");
		if (frequency == 0)
			return 0;
		float percentage = frequency * 100.0f / total;
		// System.out.println("result  :"+percentage);
		return percentage;
	}

}
