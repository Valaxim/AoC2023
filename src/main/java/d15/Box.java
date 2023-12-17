package d15;

public class Box {
	private final String name;
	private int focalLength;
	
	public Box(String name, int focalLength) {
		this.name = name;
		this.focalLength = focalLength;
	}
	
	public String getName() {
		return name;
	}
	
	public int getFocalLength() {
		return focalLength;
	}
	
	public void setFocalLength(int newFocalLength) {
		this.focalLength = newFocalLength;
	}
}