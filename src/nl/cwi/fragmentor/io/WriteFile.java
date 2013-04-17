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
	private final List<LinkedHashMap<Integer, Integer>> fragmentStats;
	private final FileInfo info;
	private String fileType;
	private int fragmentSize;
	private String fileName;
	private int index = 0;
	private final static String DOC = "doc";
	private final static String EXCEL = "xls";
	private final static String PDF = "pdf";
	private final static String ZIP = "zip";
	private final static String OGG = "ogg";
	private final static String MP4 = "mp4";
	private final static String TEXT = "text";
	private final static String PNG = "png";
	private final static String JPG = "jpg";
	private final static String PPT = "ppt";
	private String currentFolder;
	private final List<Float> ratios;
	private String path;
	


	
	public WriteFile(List<Integer[]> fragments, List<Float> ratios, FileInfo info,List<LinkedHashMap<Integer, Integer>> fragmentStats) {
		this.fragments = fragments;
		this.ratios = ratios;
		this.fragmentStats = fragmentStats;
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
		for (int i=0; i<= fragments.size()-1; i++) {
			float percentage = ratios.get(i);
			Integer[] fragment = fragments.get(i);
			definePath(percentage);
			//saveContent(fragment);
			saveStats(fragmentStats.get(index));
		}

	}
	
	private void definePath(float percentage){
		NumberFormat formatter = new DecimalFormat("#0.00");
		String name = fileName + "_" + index + "_" + fragmentSize + "_"
				+ formatter.format(percentage) + "_" + fileType + ".ext";

		this.path = "/home/jahn/Desktop/thesis/" + currentFolder
				+ "/fragments/" + name;
		
	}
	
	private void saveContent(Integer[] fragment) {
		lineByLineWrite(path, fragment);
	}

	private void saveStats(LinkedHashMap<Integer, Integer> stats) {
        path += ".score";
	    writeStats(path,stats);
	}
	
	
	
	
	// writes all bytes line-by-line
	private void lineByLineWrite(String path,Integer[] fragment){
		try {
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(fragmentToStringLineByLine(fragment));
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	
	private void writeStats(String path,LinkedHashMap<Integer, Integer> stats){
		try {
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(fragmentStats(stats));
			increaseFileCounter();
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	private void increaseFileCounter(){
		index++;
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
			case EXCEL:
				currentFolder = EXCEL;
				break;
			case DOC:
				currentFolder = DOC;
				break;
			case PDF:
				currentFolder = PDF;
				break;
			case OGG:
				currentFolder = OGG;
				break;
			case ZIP:
				currentFolder = ZIP;
				break;
			case PPT:
				currentFolder = PPT;
				break;
			case MP4:
				currentFolder = MP4;
				break;
			case PNG:
				currentFolder = PNG;
				break;
			case JPG:
				currentFolder = JPG;
				break;
			case TEXT:
				currentFolder = TEXT;
				break;
			default:
				break;

		}

	}

}
