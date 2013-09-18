package nl.cwi.counter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.cwi.entropy.CalculateEntropy;

public class IndividualZerosCounter {

	public static 	List<Integer> getNumOfIndieZeros(String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		List<Integer> listWithWordLengths = new ArrayList<Integer>();
		boolean flag = false;
		int wordLength = 0;
		for(int b : fragment){
			if(flag == false){
				if(b == 0){  
					//if(printableChars.contains(b)){
					wordLength++;
					flag = true;
				}
			}
			else{
				if(b == 0){ 
					//if(printableChars.contains(b)){
					wordLength++;
				}
				else{
					flag = false;
					if(wordLength == 1){
						listWithWordLengths.add(wordLength);
					}
					wordLength = 0;
				}
			}
		
		}
		if(wordLength == 1){
			listWithWordLengths.add(wordLength);
		}
		return listWithWordLengths;
	}
	
	public static int getNumOfIndieZeros(Integer[] fragment) throws IOException{
		List<Integer> listWithWordLengths = new ArrayList<Integer>();
		boolean flag = false;
		int wordLength = 0;
		for(int b : fragment){
			if(flag == false){
				if(b == 0){  
					//if(printableChars.contains(b)){
					wordLength++;
					flag = true;
				}
			}
			else{
				if(b == 0){ 
					//if(printableChars.contains(b)){
					wordLength++;
				}
				else{
					flag = false;
					if(wordLength == 1){
						listWithWordLengths.add(wordLength);
					}
					wordLength = 0;
				}
			}
		
		}
		if(wordLength == 1){
			listWithWordLengths.add(wordLength);
		}
		return listWithWordLengths.size();
	}
	
	
	
	public static double getDistanceMeanOfNullIndieValues(String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		List<Integer> listWithWordLengths = new ArrayList<Integer>();
		List<Integer> indeces = new ArrayList<Integer>();
        int index = 0;
		boolean flag = false;
		int wordLength = 0;
		for(int b : fragment){
			index++;
			if(flag == false){
				if(b == 0){  
					//if(printableChars.contains(b)){
					wordLength++;
					flag = true;
				}
			}
			else{
				if(b == 0){ 
					//if(printableChars.contains(b)){
					wordLength++;
				}
				else{
					flag = false;
					if(wordLength == 1){
						listWithWordLengths.add(wordLength);
						indeces.add(index);

					}
					wordLength = 0;
				}
			}
		
		}
		if(wordLength == 1){
			listWithWordLengths.add(wordLength);
			indeces.add(index);

		}
		
		int indexListSize = indeces.size();
		if(indexListSize == 0 ) return 0;
		int sum = 0;
		for(int i = 0; i<= indexListSize-2; i++ ){
			sum+=Math.abs(indeces.get(i)-indeces.get(i+1));
		}
		//double ratio = RatioFilter.getFragmentsRatio(path)/100;
		return (sum/indexListSize);
	}
	
	
}
