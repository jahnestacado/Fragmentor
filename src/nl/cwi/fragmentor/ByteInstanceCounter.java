package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ByteInstanceCounter {
	//** Integer key in map is the unsigned value of a byte
	private LinkedHashMap<Integer, Integer> mapper;
	private final Integer[] fragment;
	private final static Integer NEWLINE = 10;
	private final static Integer TAB = 9;
	private final static Integer CARRIAGE_RETURN = 13;
	private final static List<Integer> printableChars = new ArrayList<Integer>();


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

	public ByteInstanceCounter(Integer[] fragment) {
		this.fragment = fragment;
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
		LinkedHashMap<Integer, Integer> instanceCounter = initCounter();
		for (int z = 0; z <= fragment.length - 1; z++) {
			int newValue = (instanceCounter.get(fragment[z]) + 1);
			instanceCounter.put(fragment[z], newValue);
		}
		mapper = instanceCounter;
	}
	
	// This is a workaround cause the code changed many times and in order to not affect the whole program I did this :p
	private LinkedHashMap<String, Integer> keysToString(LinkedHashMap<Integer, Integer> map){
		LinkedHashMap<String, Integer> score = new LinkedHashMap<String, Integer>();
		for(Integer key : map.keySet()){
			score.put(String.valueOf(key), map.get(key));
		}
		return score;
	}

	public LinkedHashMap<String, Integer> getScore() {
		return keysToString(mapper);
	}

}
