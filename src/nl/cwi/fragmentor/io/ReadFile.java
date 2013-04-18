package nl.cwi.fragmentor.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class ReadFile {

	private final String location;
//	private final Path path;
	private final static int SIZE = 22020096; // 20MB

	public ReadFile(String location) throws IOException{
		this.location = location;
		//this.path = Paths.get(location);
	}

	@SuppressWarnings("unused")
	public byte[] fileToBytes() throws IOException{
		FileInputStream f = new FileInputStream( location );
		FileChannel ch = f.getChannel( );

		MappedByteBuffer mb = ch.map( FileChannel.MapMode.READ_ONLY,
		    0L, ch.size( ) );
		byte[] barray = new byte[SIZE];
		long checkSum = 0L;
		int nGet;
		while( mb.hasRemaining( ) )
		{
		    nGet = Math.min( mb.remaining( ), SIZE );
		    mb.get( barray, 0, nGet );
		    for ( int i=0; i<nGet; i++ )
		        checkSum += barray[i];
		}
		//return Files.readAllBytes(path);
		return barray;
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