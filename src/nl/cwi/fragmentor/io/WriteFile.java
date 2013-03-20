package nl.cwi.fragmentor.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;

import nl.cwi.fragmentor.FileInfo;

public class WriteFile {
	private final List<Integer[]> fragments;
	private  List<LinkedHashMap<Integer, Integer>> fragmentStats;
	private final FileInfo info;
	private String fileType;
	private int fragmentSize;
	private String fileName;
	private int index = 0;
	private final static String doc = "doc";
	private final static String excel = "xls";
	private final static String pdf = "pdf";
	private String currentFolder;
	private final List<Float> ratios;
	


	
	public WriteFile(List<Integer[]> fragments, List<Float> ratios, FileInfo info) {
		this.fragments = fragments;
		this.ratios = ratios;
		this.info = info;
		setFileInfo();

	}
	

	private void setFileInfo() {
		fileType = info.getType().toLowerCase();
		fragmentSize = info.getSize();
		fileName = info.getName();
		setFolder(fileType);
	}

	public void produceOutput() {
		int fragmentNum = 0;
		for (int i=0; i<= fragments.size()-1; i++) {
			float percentage = ratios.get(i);
			Integer[] fragment = fragments.get(i);
			
			saveContent(fragment, percentage);
			fragmentNum++;
		}

	}
	
	private void saveContent(Integer[] fragment, float percentage) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String name = fileName + "_" + index + "_" + fragmentSize + "_"
				+ formatter.format(percentage) + "_" + fileType + ".ext";

		String path1 = "/home/jahn/Desktop/thesis/" + currentFolder
				+ "/fragments/" + name;
		lineByLineWrite(path1, fragment);
	}

	private void save(Integer[] fragment, float percentage,
			LinkedHashMap<Integer, Integer> stats) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String name1 = fileName + "_" + index + "_" + fragmentSize + "_"
				+ formatter.format(percentage) + "_" + fileType  + ".ext";
		String name2 = fileName + "_" + index + "_" + fragmentSize + "_"
				+ formatter.format(percentage) + "_" + fileType  + ".stats";
	    String path1 = "/home/jahn/Desktop/thesis/"+ currentFolder+ "/fragments/" + name1;
	    String path2 = "/home/jahn/Desktop/thesis/"+ currentFolder+ "/fragments/" + name2;

	    lineByLineWrite(path1, fragment);
	    statsWrite(path2,stats);
	  

	}
	
	
	
	
	// writes all bytes line-per-line
	private void lineByLineWrite(String path,Integer[] fragment){
		try {
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(fragmentToStringLineByLine(fragment));
			index++;
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	
	private void statsWrite(String path,LinkedHashMap<Integer, Integer> stats){
		try {
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(fragmentStats(stats));
			index++;
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	

	private String fragmentStats(LinkedHashMap<Integer, Integer> stats) {
		String fragmentStats = new String();
		for (int b : stats.keySet()) {
			fragmentStats += b + ": " + stats.get(b) + '\n';
		}
		return fragmentStats;
	}
	
	
	
	

    // returns a String which contains all bytes , line by line (one byte per line) maintining original sequence
	private String fragmentToStringLineByLine(Integer[] fragment) {
		String content = new String();
		for (int b : fragment) {
			content += b +"$$" ;
		}
		return content.replace("$$", ""+'\n');
	}

	
	
	
	

	
	private void setFolder(String type) {
	
		switch (type) {
			case excel:
				currentFolder = excel;
				break;
			case doc:
				currentFolder = doc;
				break;
			case pdf:
				currentFolder = pdf;
				break;
			default:
				break;

		}

	}

}
