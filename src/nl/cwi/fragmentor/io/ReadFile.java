package nl.cwi.fragmentor.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ReadFile {

	private final String location;
	private final Path path;
	
	public ReadFile(String location) throws IOException{
		this.location = location;
		this.path = Paths.get(location);
		fileToBytes();
	}
	
	public byte[] fileToBytes() throws IOException{
		return Files.readAllBytes(path);
	}
	
	public  String getType() {
		int lastDotPosition = location.lastIndexOf(".");
		String type = location.substring(lastDotPosition + 1, location.length());
		return type;

	}
	
	public  String getName() {
		int lastSlashPosition = location.lastIndexOf("/");
		int lastDotPosition = location.lastIndexOf(".");
		String name = location.substring(lastSlashPosition + 1, lastDotPosition);
		return name;

	}
	

}
