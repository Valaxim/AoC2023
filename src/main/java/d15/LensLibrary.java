package d15;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LensLibrary {
	
	public static Map<String, Integer> outputMap = new HashMap<>();
	
	public static long calculate(List<String> input) throws IOException {
		List<String> instructions = Arrays.stream(input.get(0).split(",")).toList();
		long output = 0;
		
		for (String instruction : instructions) {
			int currentValue = runHashAlgorithm(instruction);
			outputMap.put(instruction, currentValue);
			output += currentValue;
		}
		
		return output;
	}
	
	private static int runHashAlgorithm(String instruction) {
		int currentValue = 0;
		
		for (char currentChar : instruction.toCharArray()) {
			currentValue = increment(currentChar, currentValue);
			currentValue *= 17;
			currentValue = currentValue % 256;
		}
		
		return currentValue;
	}
	
	private static int increment(char currentChar, int currentValue) {
		return currentChar + currentValue;
	}
	
	public static long calculateB(List<String> input) {
		Map<Integer, ArrayDeque<Box>> boxMap = initializeBoxMap();
		
		List<String> instructions = Arrays.stream(input.get(0).split(",")).toList();
		
		for (String instruction : instructions) {
			processInstruction(instruction, boxMap);
		}
		
		return calculateFocusingPower(boxMap);
	}
	
	private static Map<Integer, ArrayDeque<Box>> initializeBoxMap() {
		Map<Integer, ArrayDeque<Box>> boxMap = new HashMap<>();
		
		for (int i = 0; i < 256; i++) {
			boxMap.put(i, new ArrayDeque<>());
		}
		
		return boxMap;
	}
	
	private static void processInstruction(String instruction, Map<Integer, ArrayDeque<Box>> boxMap) {
		if (instruction.contains("=")) {
			processAssignmentInstruction(instruction, boxMap);
		} else {
			processRemovalInstruction(instruction, boxMap);
		}
	}
	
	private static void processAssignmentInstruction(String instruction, Map<Integer, ArrayDeque<Box>> boxMap) {
		String[] split = instruction.split("=");
		String name = split[0];
		int focalLength = Integer.parseInt(split[1]);
		int noBox = runHashAlgorithm(name);
		
		ArrayDeque<Box> boxes = boxMap.get(noBox);
		
		Optional<Box> existingBox = boxes.stream().filter(it -> it.getName().equals(name)).findFirst();
		
		if (existingBox.isPresent()) {
			existingBox.get().setFocalLength(focalLength);
		} else {
			boxes.add(new Box(name, focalLength));
		}
	}
	
	private static void processRemovalInstruction(String instruction, Map<Integer, ArrayDeque<Box>> boxMap) {
		String[] split = instruction.split("-");
		String name = split[0];
		int noBox = runHashAlgorithm(name);
		
		ArrayDeque<Box> boxes = boxMap.get(noBox);
		boxes.removeIf(it -> it.getName().equals(name));
	}
	
	private static long calculateFocusingPower(Map<Integer, ArrayDeque<Box>> boxMap) {
		long focusingPower = 0;
		
		for (Map.Entry<Integer, ArrayDeque<Box>> entry : boxMap.entrySet()) {
			int key = entry.getKey();
			ArrayDeque<Box> value = entry.getValue();
			int i = 1;
			
			for (Box box : value) {
				focusingPower += (key + 1L) * i++ * box.getFocalLength();
			}
		}
		
		return focusingPower;
	}
}
