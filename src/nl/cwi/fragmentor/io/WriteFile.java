package nl.cwi.fragmentor.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;

import nl.cwi.fragmentor.FileInfo;

public class WriteFile {
	private final Integer[] fragments;
	private final LinkedHashMap<Integer, Integer> fragmentStats;
	private final FileInfo info;
	private String fileType;
	private int fragmentSize;
	private String fileName;
	private final int index ;
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
	private final float ratio;
	private String path;
	
	
	


	
	public WriteFile(int index, Integer[] fragments, float ratio, FileInfo info) {
		this.fragments = fragments; 
		this.index = index;
		this.ratio = ratio;
		this.fragmentStats = null;
		this.info = info;
		setFileInfo();

	}
	
	// This constructor is for saving the Byte Instance Frequencies from a mpa to a file
	public WriteFile(int index, float ratio, FileInfo info,LinkedHashMap<Integer, Integer> fragmentStats) {
		this.fragments = null; 
		this.index = index;
		this.ratio = ratio;
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

	public void produceScoreOutput() throws IOException {
		float percentage = ratio;
		definePath(percentage);
		saveStats(fragmentStats);
	}

	public void produceContentOutput() throws IOException {
		float percentage = ratio;
		definePath(percentage);
		saveContent(fragments);
	}
	
	private void definePath(float percentage){
		NumberFormat formatter = new DecimalFormat("#0.00");
		String name = fileName + "_" + index + "_" + fragmentSize + "_"
				+ formatter.format(percentage) + "_" + fileType + ".ext";

		this.path = "/home/jahn/Desktop/fp/" + currentFolder
				+ "/fragments/" + name;
		
		//this.path = "/media/jahn/1234-5678/thesis/"+name;
	
	}
	
	private void saveContent(Integer[] fragment) throws IOException {
		lineByLineWrite(path, fragment);
	}

	// This method currently is not being used. It writes the byte frequency score of a fragment in a file
	private void saveStats(LinkedHashMap<Integer, Integer> stats) throws IOException {
        path += ".score";
	    writeStats(path,stats);
	}
	
	
	
	
	// writes all bytes line-by-line
	private void lineByLineWrite(String path, Integer[] fragment)
			throws IOException {
		FileWriter fstream = new FileWriter(path);
		BufferedWriter out = new BufferedWriter(fstream);
		String content = fragmentToStringLineByLine(fragment);
		out.write(content);
		out.close();

	}
	
	
	private void writeStats(String path,LinkedHashMap<Integer, Integer> stats) throws IOException{
		
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(fragmentStats(stats));
			//increaseFileCounter();
			out.close();
		
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
		String content = "";
		for (Integer b : fragment) {
			content += b.intValue() +"$$" ;
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
