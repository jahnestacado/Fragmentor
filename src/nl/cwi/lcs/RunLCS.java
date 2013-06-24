package nl.cwi.lcs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.entropy.CalculateEntropy;
import nl.cwi.fragmentor.io.FragmentFilePath;

public class RunLCS {
	private final static String MAIN_FOLDER = "/home/jahn/Desktop/text_output/xls/";
	private static String doc = "nullnull1null20000000000000000000000000000000000000000000000000000000000000000000000000000011101100000000000000000000001011null0255255252552552550null100000000000000255255255500000011411110000001null251010000000001000000000000000000010210255255255255255255001004101114null11500000000001041021121100000000000000000001011031010310000000201100325525505255001432100000000000010000000000000000000000000111010000000000010111000000000000000001000000000000000000000000000015000000000000210010000000000000000005000000000000000000000000000000000000500000000000000000000022255255255001010111010101110100010511255255null1521021100000000000000000000000000000001000000000000001000000000000000000000000000000000000000000000000000000000000000null3102102100000000000000000000000000000110010011109925525525525525525525525525525525525571101097116105111110101012102111102321511111null13101011101100320025502552552552552552552552552552552552552552552552551101010000000001101114101001110321511613000000511011032111210112550002552";
	
	private static String xls ="5000000nullnull00000025000000253010000000000000015000000150000000000000024063102530100255000000003214020210270002530100000000000null1500000253010010270000150000000000111890180001500000null00015000002550000001150821605500000000000101900012621001500000null00000000019001126210000160000000000803000000003100010150000200000032140015000000501890null2000000000006422000600000000001890500000000020150000000150000000000001500000000330002500000009036000000003000251602105000000115082160null0000150000015000023000002021027000253010012530100null0102550000001000010230000000240638010150000000022000002300000001000000010500002200000150000000500025301006420321403000000000000000000008010255000000110000000240638023021033000253010019218900150000000000000000002302300000018903601502103500025301001502101015000001890420001501023000000010210270150000000null02000002700000120520";
	private static Map<String,Integer> map = new HashMap<String,Integer>();
	
	
	
	public static void main(String[] args) throws IOException {
	
        System.out.println(xls.length());
		FragmentFilePath paths = new FragmentFilePath(MAIN_FOLDER);
		List<String> allPaths = paths.getAllPaths();
	    List<String> list = new ArrayList<String>();
		int r = 0;
		for (int i = 0; i <=499; i++) {
			String fragment1 = CalculateEntropy.getFragmentsContentAsStrings(allPaths.get(i));
			
		/*	String t1 = LCS.longestSubstring(fragment1, doc);
			String t2 = LCS.longestSubstring(fragment1, xls);
             if(t1.length() < t2.length()){
            	r++;
            }

		}
		*/
		for (int t = 0; t <= 499; t++) {
				if(i!=t){
				String fragment2 = CalculateEntropy.getFragmentsContentAsStrings(allPaths.get(t));
				String temp = LCS.longestSubstring(fragment1, fragment2);

				//temp = LCS.longestSubstring(fragment1, fragment2);

				if (temp.length() != 0 && temp != null) {

					if (list.contains(temp)) {
					
						if(map.containsKey(temp)){
							map.put(temp, map.get(temp)+1);
						}
						else{
							map.put(temp, 1);
						}
					}
					else 	list.add(temp);
				}
			}
			

		}
		
		}
		Map<String,Integer> sortedMap = MapSorter.sortMap(map);
		int counter = 0;
		String doc_50 = new String();
		for(String key : sortedMap.keySet() ){
			if(counter==100){
				save(doc_50,"100_lcs_xls_new");
				System.out.println("100: "+doc_50);
                System.out.println();
			}
			if(counter==500){
				save(doc_50,"500_lcs_xls_new");
				System.out.println("1500: "+doc_50);
                System.out.println();

			}
			if(counter==1000){
				save(doc_50,"1000_lcs_xls_new");
				System.out.println("1500: "+doc_50);
                System.out.println();

			}
			if(counter==1500){
				save(doc_50,"1500_lcs_xls_new");
				System.out.println("1500: "+doc_50);
                System.out.println();

			}
			doc_50 += key;
			counter++;
		}
		
     	System.out.println("Full   "+doc_50);

		
	}
	
	private static void save(String content, String fileName){
		try {
			FileWriter fstream = new FileWriter("/home/jahn/Desktop/" + fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	}


