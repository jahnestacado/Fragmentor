package nl.cwi.lcs;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MapSorter {

	 public static Map<String,Integer> sortMap(Map<String,Integer>  map) {
	        ValueComparator bvc =  new ValueComparator(map);
	        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);

	        System.out.println("unsorted map: "+map);

	        sorted_map.putAll(map);
	        System.out.println("results: "+sorted_map);

            return sorted_map;
	    }
	}

	class ValueComparator implements Comparator<String> {

	    Map<String, Integer> base;
	    public ValueComparator(Map<String, Integer> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	

}
