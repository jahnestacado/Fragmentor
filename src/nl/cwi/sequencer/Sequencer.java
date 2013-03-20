package nl.cwi.sequencer;

import java.util.ArrayList;
import java.util.List;

public class Sequencer {
	private final List<byte[]> fragments;
	private final static byte NEWLINE = 10;
	private final static byte TAB = 9;
	private final static byte CARRIAGE_RETURN = 13;
	private final static List<Byte> printableChars = new ArrayList<Byte>();
	private final static List<List<String>> sequencesInFragments = new ArrayList<List<String>>();
	private final static List<String> nonTextBytes =new ArrayList<String>();
	

	// initialize array content comprised of all printables byte characters +
	// newline, tab and carriage_return
	static {
		for (byte i = 36; i <= 126; i++) {
			printableChars.add(i);
		}
		printableChars.add(NEWLINE);
		printableChars.add(TAB);
		printableChars.add(CARRIAGE_RETURN);
		
		nonTextBytes.add("13");
		nonTextBytes.add("10");
		nonTextBytes.add("9");
	}
	
	public Sequencer(List<byte[]> fragments){
		this.fragments = fragments;
		formSequencesInFragments();
	}
	
	private void formSequencesInFragments() {
		for (byte[] fragment : fragments) {
			boolean flag = false;
			List<String> sequencesInOneFragment = new ArrayList<String>();
			String sequence = new String();
			for (byte b : fragment) {
				 if (printableChars.contains(b)) {
						if(flag){
						sequence+=String.valueOf(b)+" ";
						}
						flag = true;
				} else{
					flag = false;
					if(sequence.length() >= 7 && !sequence.isEmpty() ) 
						sequencesInOneFragment.add(sequence);
					sequence = "";
				}
			}
            sequencesInFragments.add(sequencesInOneFragment);
		}
	}
	
	public List<List<String>> getSequencesInFragments(){
		return sequencesInFragments;
	}
	
	private boolean checkPercentage(String sequence){
		int counter = 0;
		int size = sequence.length();
		int oneFifth = size/8;
		
		for (int i = 0; i < size; i++){
		    char c = sequence.charAt(i);        
		    if(nonTextBytes.contains(c)){
		    	counter++;
		    }
		}
		return counter>=oneFifth;
	}

}
