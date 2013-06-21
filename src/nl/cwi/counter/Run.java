package nl.cwi.counter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nl.cwi.bfd.algorithm.Graphs;
import nl.cwi.bfd.fingerprint.io.reader.RatioFilter;
import nl.cwi.entropy.CalculateEntropy;
import nl.cwi.entropy.Distance;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class Run {

	private final static String[] typePaths = {"pdf","doc","xls","zip","ppt","mp4","ogg","text","png","jpg"};
	private final static String INPUT_FOLDER ="/home/jahn/Desktop/text_output/";
	private  static List<Double> zerosFreqs ;
	private static  double res ;
	
	public static void main(String[] args) throws IOException {
		for(String type : typePaths){
			zerosFreqs= new LinkedList<Double>();
		FragmentFilePath paths = new FragmentFilePath(INPUT_FOLDER+type+"/");
		for (String path : paths.getAllPaths()) {
			if(RatioFilter.checkRatio(0,100, path)){
			//zerosFreqs.add(ROC.getRateOfChange(path));
			//zerosFreqs.add(Counter.getCapitalRatioMetric(path));
			//zerosFreqs.add((double) Counter.getNumofByteInFragment(32, path));
				double rat = IndividualZerosCounter.getDistanceMeanOfNullIndieValues(path);
				if(rat > 0){
				zerosFreqs.add( rat);
				}
			//zerosFreqs.add((double) StringsInFragments.getNumOfWordsInFragment(path));
			/*double nullValues = IndividualZerosCounter.getNumOfIndieZeros(path).size();;
			double ratio = RatioFilter.getFragmentsRatio(path);
			if(nullValues == 0) nullValues = 0.1;
            double nullRatioAnalogy = (ratio/100)/nullValues;
			zerosFreqs.add( nullRatioAnalogy);
			*/

			//zerosFreqs.add( CalculateEntropy.getFragmentsEntropy(path));
				//zerosFreqs.add((double)IndividualZerosCounter.getNumOfIndieZeros(path).size());
			}
		}
		System.out.println(type);
		/*
		int count = 0;
		for(double d : zerosFreqs){
			if(d < 20) count++;
		}
		System.out.println(count);
		count = 0;
		*/
        Graphs.getHistogram(zerosFreqs,type);
		System.out.println("Done");

	    double mean = Distance.getListMean(zerosFreqs);
		System.out.println("Mean:"+mean+"  ## "+getMean(zerosFreqs));
		System.out.println("MAx: "+Collections.max(zerosFreqs));
		System.out.println("Min: "+Collections.min(zerosFreqs));
		System.out.println("Median: "+Distance.getListMedian(zerosFreqs)+" ## "+getMedian(zerosFreqs));
		System.out.println("Mode: "+ getMode(zerosFreqs));
		System.out.println("Stanard Dev: "+ getStandardDev(zerosFreqs,mean));


		System.out.println();
	}
	}
	
	private static double getMode(List<Double> score){
		Map<Double,Double> temp = new HashMap<Double,Double>();
		for(double b : score){
			if(b == 0);
			else if(temp.containsKey(b)){
				temp.put(b, temp.get(b)+1);
			}
			else temp.put(b, 1.0);
		}
		double max = 0;
		double modeKey = 0;
		for(Double key : temp.keySet()){
			if(temp.get(key) >= max){
				max = temp.get(key);
				modeKey = key;
			}
		}
		return modeKey;
	}
	
	 public static double getStandardDev(List<Double> values, double mean){
	        int sum = 0;
	        for (double i : values)
	            sum += Math.pow((i - mean), 2);
	        return Math.sqrt( sum / ( values.size() - 1 )); // sample
	    }
	 
	 
	 public static double getMean (List<Double> list){
	        double sum = 0;
	        for(double b : list ){
	        	sum+=b;
	        }
	        double mean = 0;
	        mean = sum / (list.size() * 1.0);
	        return mean;
	    }
	    public static double getMedian (List<Double> a){
	        int middle = a.size()/2;
	 
	        if (a.size() % 2 == 1) {
	            return a.get(middle);
	        } else {
	           return (a.get(middle-1) + a.get(middle)) / 2.0;
	        }
	    }

}
