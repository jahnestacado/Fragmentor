package nl.cwi.counter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.cwi.entropy.CalculateEntropy;
import nl.cwi.entropy.Distance;

public class ROC {
	
	public static double getRateOfChange(String path) throws IOException{
		Integer[] fragment = CalculateEntropy.getFragmentsContent(path);
		List<Double> rocValues = new ArrayList<Double>();
		for(int i = 0; i <= fragment.length-2; i++){
			rocValues.add((double) Math.abs(fragment[i] - fragment[i+1]));
		}
		double roc = Distance.getListMean(rocValues);
		return roc;
	}

}
