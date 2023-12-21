package d19;

import java.util.Optional;

record Rule(String condition, String accepted) {
	
	public Optional<String> apply(Part part) {
		if (this.accepted().isBlank()) {
			return Optional.of(condition); // return A, R, or direct instruction
		} else {
			return parse(this.condition(), part) ? Optional.of(accepted()) : Optional.empty();
		}
	}
	
	public boolean parse(String input, Part part) {
		if (input.contains(">")) {
			String[] split = input.split(">");
			int value = determineValue(split[0], part);
			return value > Integer.parseInt(split[1]);
			
		} else if (input.contains("<")) {
			String[] split = input.split("<");
			int value = determineValue(split[0], part);
			return value < Integer.parseInt(split[1]);
		}
		throw new IllegalArgumentException("Error in parsing");
	}
	
	private int determineValue(String input, Part part) {
		return switch (input) {
			case "x" -> part.x();
			case "m" -> part.m();
			case "a" -> part.a();
			case "s" -> part.s();
			default -> throw new IllegalStateException("Unexpected value: " + input);
		};
	}
}
