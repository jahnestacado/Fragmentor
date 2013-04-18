package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import nl.cwi.fragmentor.io.WriteFile;

public class ByteInstanceCounter {
	//** Integer key in map is the unsigned value of a byte
	private final ArrayList<LinkedHashMap<Integer, Integer>> mapper = new ArrayList<LinkedHashMap<Integer, Integer>>();
	private final List<Integer[]> fragments;
	private final static Integer NEWLINE = 10;
	private final static Integer TAB = 9;
	private final static Integer CARRIAGE_RETURN = 13;
	private final static List<Integer> printableChars = new ArrayList<Integer>();
	private final FileInfo info;
	private final List<Float> ratios;

	//** initialize array content comprised of all printables byte characters
	//** +plus
	//** newline, tab and carriage_return
	static {
		for (int i = 36; i <= 126; i++) {
			printableChars.add(i);
		}
		printableChars.add(NEWLINE);
		printableChars.add(TAB);
		printableChars.add(CARRIAGE_RETURN);
	}

	public ByteInstanceCounter(List<Integer[]> fragments, FileInfo info, List<Float> ratios) {
		this.fragments = fragments;
		this.info = info;
		this.ratios = ratios;
		setCounter();
	}

	private LinkedHashMap<Integer, Integer> initCounter() {
		LinkedHashMap<Integer, Integer> instanceCounter = new LinkedHashMap<Integer, Integer>();
		for (int i = 0; i <= printableChars.size() - 1; i++) {
			instanceCounter.put(printableChars.get(i), 0);
		}
		return instanceCounter;
	}

	private void setCounter() {
		int index = 0;
		for (Integer[] fragment : fragments) {
			LinkedHashMap<Integer, Integer> instanceCounter = initCounter();
			for (int z = 0; z <= fragment.length - 1; z++) {
				int newValue = (instanceCounter.get(fragment[z]) + 1);
				instanceCounter.put(fragment[z], newValue);
			}
			//mapper.add(instanceCounter);
			new WriteFile(index, ratios.get(index),info,instanceCounter).produceOutput();
			index++;
		}
	}

	public List<LinkedHashMap<Integer, Integer>> getStats() {
		return mapper;
	}

}
