package nl.cwi.bfd.algorithm.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FingerPrintReader {
	
	public static float[] getScores(String path) throws IOException{
		float[] content = new float[98];
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			content[i++] = Float.parseFloat(line.trim());
		}
		reader.close();
		return content;
	}

}
