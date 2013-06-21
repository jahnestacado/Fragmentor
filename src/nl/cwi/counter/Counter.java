package nl.cwi.counter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.cwi.entropy.CalculateEntropy;

public class Counter {
	private final static Integer NEWLINE = 10;
	private final static Integer TAB = 9;
	private final static Integer CARRIAGE_RETURN = 13;
	private final static List<Integer> printableChars = new ArrayList<Integer>();
	private final static List<Integer> capitalLetters = new ArrayList<Integer>();
	


	//** initialize array content comprised of all printables byte characters +
	//** newline, tab and carriage_return
	static {
		for (int i = 32; i <= 126; i++) {
			printableChars.add(i);
		}
		printableChars.add(NEWLINE);
		printableChars.add(TAB);
		printableChars.add(CARRIAGE_RETURN);
		
		for(int y=65; y<=90;y++){
			capitalLetters .add(y);
		}
	}
	
	public static int getNumofByteInFragment(int byteDecValue, String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		int counter = 0;
		for(int b : fragment){
			if(b == byteDecValue) counter++;
		}
		return counter;
	}
	
	public static int getNumofPrintableChars(String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		int counter = 0;
		for(int b : fragment){
			if(printableChars.contains(b)) counter++;
		}
		return counter;
	}
	
	public static int getNumofCapitalLetters(String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		int counter = 0;
		for(int b : fragment){
			if(capitalLetters.contains(b)) counter++;
		}
		return counter;
	}
	
	
	public static double getCapitalRatioMetric(String path) throws IOException{
		int numOFCapLetters = getNumofCapitalLetters(path);
		int numOfPrintableChars = getNumofPrintableChars(path);
		
		return numOFCapLetters/numOfPrintableChars;
	}
	
	
	public static double getZeroDivisionMetric(String path) throws IOException{
		double zeros = getNumofByteInFragment(0,path);
		double indieZeros = IndividualZerosCounter.getNumOfIndieZeros(path).size();
		if(zeros == 0.0) return 0.0;
		return indieZeros/zeros;
	}
	
	public static double getSpaceWithWordsRatio(String path) throws IOException{
		double spaces =  getNumofByteInFragment(32,path);
		int words = IndividualZerosCounter.getNumOfIndieZeros(path).size();
		double result = 0;
		if(words == 0){
			 result = 0;
		}
		else result = spaces/words;
		return result;
	}
	
	
	

}
