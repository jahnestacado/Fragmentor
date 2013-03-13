package nl.cwi.fragmentor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ByteInstanceCounter {
	private final ArrayList<LinkedHashMap<Byte, Integer>> mapper = new ArrayList<LinkedHashMap<Byte, Integer>>();
	private final List<byte[]> fragments;

	public ByteInstanceCounter(List<byte[]> fragments) {
		this.fragments = fragments;
		setCounter();
	}

	private void setCounter() {
		for (byte[] fragment : fragments) {
			LinkedHashMap<Byte, Integer> instanceCounter = new LinkedHashMap<Byte, Integer>();
			for (int z = 0; z <= fragment.length - 1; z++) {
				if (instanceCounter.containsKey(fragment[z])) {
					int newValue = instanceCounter.get(fragment[z]) + 1;
					instanceCounter.put(fragment[z], newValue);
				} else
					instanceCounter.put(fragment[z], 1);

			}
			mapper.add(instanceCounter);
		}

	}

	public List<LinkedHashMap<Byte, Integer>> getCounter() {
		return mapper;
	}

}
