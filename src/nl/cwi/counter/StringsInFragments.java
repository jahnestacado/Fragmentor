package nl.cwi.counter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.cwi.entropy.CalculateEntropy;

public class StringsInFragments {
	private final static Integer NEWLINE = 10;
	private final static Integer TAB = 9;
	private final static Integer CARRIAGE_RETURN = 13;
	private final static List<Integer> printableChars = new ArrayList<Integer>();
	private final static int WORD_LENGTH = 5;
	


	//** initialize array content comprised of all printables byte characters +
	//** newline, tab and carriage_return
	static {
		for (int i = 32; i <= 126; i++) {
			printableChars.add(i);
		}
		printableChars.add(NEWLINE);
		printableChars.add(TAB);
		printableChars.add(CARRIAGE_RETURN);
	}
	
	public static int getNumOfWordsInFragment(String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		List<Integer> wordCounter = new ArrayList<Integer>();
		boolean flag = false;
		int wordLength = 0;
		for(int b : fragment){
			if(flag == false){
					if(printableChars.contains(b)){
					wordLength++;
					flag = true;
				}
			}
			else{
			
					if(printableChars.contains(b)){
					wordLength++;
				}
				else{
					flag = false;
					if(wordLength >= WORD_LENGTH){
						wordCounter.add(wordLength);
					}
					wordLength = 0;
				}
			}
		
		}
		if(wordLength >= WORD_LENGTH){
			wordCounter.add(wordLength);
		}
		return wordCounter.size();
	}

}
