package nl.cwi.fragmentor.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.fragmentor.FileInfo;

public class WriteFile {
	private final Map<byte[], Float> fragments;
	private final List<LinkedHashMap<Byte, Integer>> fragmentStats;
	private final FileInfo info;
	private String fileType;
	private int fragmentSize;
	private String fileName;
	private int index = 0;

	public WriteFile(Map<byte[], Float> fragments,
			List<LinkedHashMap<Byte, Integer>> fragmentStats, FileInfo info) {
		this.fragments = fragments;
		this.fragmentStats = fragmentStats;
		this.info = info;
		setFileInfo();

	}

	private void setFileInfo() {
		fileType = info.getType();
		fragmentSize = info.getSize();
		fileName = info.getName();
	}

	public void produceOutput() {
		int fragmentNum = 0;
		for (byte[] fragment : fragments.keySet()) {
			float percentage = fragments.get(fragment);
			LinkedHashMap<Byte, Integer> stats = fragmentStats.get(fragmentNum);
			save(fragment, percentage, stats);
			fragmentNum++;
		}

	}

	private void save(byte[] fragment, float percentage,
			LinkedHashMap<Byte, Integer> stats) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String name = fileName + index + "_" + fragmentSize + "_"
				+ formatter.format(percentage) + "." + fileType  + ".ext";
		
	 String path = "/home/jahn/Desktop/thesis_workspace/doc/fragments/" + name;
		//final String path = "src/nl/cwi/fragmentor/io/outputdata/" + name;

		try {
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(fragmentContent(fragment));
			out.write('\n');
			out.write(fragmentStats(stats));
			index++;
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	private String fragmentStats(LinkedHashMap<Byte, Integer> stats) {
		String fragmentStats = new String();
		int counter = 0;
		for (byte b : stats.keySet()) {
			if (counter == 4) {
				fragmentStats += b + ": " + stats.get(b) + '\n';
				counter = 0;
			} else {
				counter++;
				fragmentStats += b + ": " + stats.get(b) + "           ";
			}
		}

		return fragmentStats;
	}
	
	

	private String fragmentContent(byte[] fragment) {
		String content = new String();
		int counter = 0;
		for (byte b : fragment) {
			if (counter == 35) {
				content += " " + b + '\n';
				counter = 0;
			} else {
				counter++;
				content += " " + b;
			}
		}

		return content + '\n' + '\n';
	}

}
