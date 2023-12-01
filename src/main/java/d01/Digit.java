package d01;

/**
 * Enum that matches the text representation of a digit to its corresponding integer.
 */

enum Digit {
	
	ONE("one", 1),
	TWO("two", 2),
	THREE("three", 3),
	FOUR("four", 4),
	FIVE("five", 5),
	SIX("six", 6),
	SEVEN("seven", 7),
	EIGHT("eight", 8),
	NINE("nine", 9);
	
	private final String digitAsString;
	private final int digitValue;
	
	Digit(String digitAsString, int digitValue) {
		this.digitAsString = digitAsString;
		this.digitValue = digitValue;
	}
	
	public int getDigitValue() {
		return digitValue;
	}
	
	public String getDigitAsString() {
		return digitAsString;
	}
}