package nl.cwi.bfd.fingerprint.io.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class SaveFingerprint {
	private final static String outputPath ="/home/jahn/Desktop/fp/fingerprints";
	
	public static  void writeToFile(float[] avgScore, float[] corrStrengthScore){
		saveScore(avgScore,"XLS_AVGfingerprint.fgp");
		saveScore(corrStrengthScore,"XLS_CORR_STR_fingerprint.fgp");
	}
	
	
	private static void saveScore(float[] score, String fileName){
		try {
			FileWriter fstream = new FileWriter(outputPath + "/" + fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			String content = scoreToStringLineByLine(score);
			out.write(content);
			out.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	 // returns a String which contains all bytes , line by line (one byte per line) maintining original sequence
		private static String scoreToStringLineByLine(float[] score) {
			String content = new String();
			for (float value : score) {
				content += value +"$$" ;
			}
			return content.replace("$$", ""+'\n');
		}

}
