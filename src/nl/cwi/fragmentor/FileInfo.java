package nl.cwi.fragmentor;

public class FileInfo {
	private String name;
	private String type;
	private int fragmentSize;

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSize(int size) {
		this.fragmentSize = size;
	}

	public int getSize() {
		return fragmentSize;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
