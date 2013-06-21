package nl.cwi.bfd.fingerprint.io.reader;

public class RatioFilter {

	public static boolean checkRatio(float minRatio, float maxRatio, String path){
		String[] array = path.split("_");
		float fragmentsRatio = Float.valueOf(array[array.length -2]); //is the substring of the path that contains the ratio information
		if(fragmentsRatio > minRatio && fragmentsRatio <= maxRatio)
			return true;
	
		return false;
	}
	
	public static double getFragmentsRatio(String path){
		String[] array = path.split("_");
		double fragmentsRatio = Float.valueOf(array[array.length -2]); //is the substring of the path that contains the ratio information
		return fragmentsRatio;
	}

}
