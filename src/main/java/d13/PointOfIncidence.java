package d13;

import utils.ParseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointOfIncidence {
	
	private static final Map<Integer, ReflectionLine> outputMap = new HashMap<>();
	
	public static long calculate(List<String> input) {
		List<Pattern> patterns = readAllPatterns(input);
		long output = 0L;
		
		for (int patternNo = 0; patternNo < patterns.size(); patternNo++) {
			long tempOutput = 0L;
			char[][] array2D = patterns.get(patternNo).array2D();
			
			tempOutput += findIdenticalRows(array2D, true, patternNo);
			char[][] chars = rotateClockwise(array2D);
			tempOutput += findIdenticalRows(chars, false, patternNo);
			
			output += tempOutput;
		}
		return output;
	}
	
	public static long calculateB(List<String> input) {
		List<Pattern> patterns = readAllPatterns(input);
		long output = 0L;
		
		for (int patternNo = 0; patternNo < patterns.size(); patternNo++) {
			char[][] array2D = patterns.get(patternNo).array2D();
			
			changing:
			for (int row = 0; row < array2D.length; row++) {
				for (int col = 0; col < array2D[0].length; col++) {
					long temporaryOutput = 0L;
					
					//deep copy
					char[][] mirrorWithFixedSmudge = new char[array2D.length][array2D[0].length];
					for (int i = 0; i < array2D.length; i++) {
						mirrorWithFixedSmudge[i] = Arrays.copyOf(array2D[i], array2D[i].length);
					}
					
					mirrorWithFixedSmudge[row][col] = changeValueToOpposite(array2D[row][col]);
					
					temporaryOutput += findIdenticalRows(mirrorWithFixedSmudge, true, patternNo);
					if (temporaryOutput == 0) {
						char[][] chars = rotateClockwise(mirrorWithFixedSmudge);
						temporaryOutput += findIdenticalRows(chars, false, patternNo);
					}
					
					if (temporaryOutput > 0) {
						output += temporaryOutput;
						break changing;
					}
				}
			}
		}
		return output;
	}
	
	private static char changeValueToOpposite(char c) {
		return c == '#' ? '.' : '#';
	}
	
	private static long findIdenticalRows(char[][] array2D, boolean isHorizontal, int patternNo) {
		
		int numRows = array2D.length;
		int numCols = array2D[0].length;
		
		for (int currentRow = 0; currentRow < numRows - 1; currentRow++) {
			boolean isReflection = false;
			char[] firstRow = new char[numCols];
			char[] secondRow = new char[numCols];
			
			// Copy the first row into the new array
			System.arraycopy(array2D[currentRow], 0, firstRow, 0, numCols);
			System.arraycopy(array2D[currentRow + 1], 0, secondRow, 0, numCols);
			if (Arrays.equals(firstRow, secondRow)) {
				isReflection = true;
				for (int currentRowSecond = 1; currentRow - currentRowSecond >= 0 && currentRow + 1 + currentRowSecond < numRows; currentRowSecond++) {
					System.arraycopy(array2D[currentRow - currentRowSecond], 0, firstRow, 0, numCols);
					System.arraycopy(array2D[currentRow + 1 + currentRowSecond], 0, secondRow, 0, numCols);
					if (!Arrays.equals(firstRow, secondRow)) {
						isReflection = false;
						break;
					}
				}
			}
			
			if (isReflection) {
				if (isHorizontal) {
					if (outputMap.get(patternNo) == null) {
						outputMap.put(patternNo, new ReflectionLine(Direction.HORIZONTAL, currentRow + 1));
						return (currentRow + 1) * 100L;
					}
					
					ReflectionLine reflectionLine = new ReflectionLine(Direction.HORIZONTAL, currentRow + 1);
					if (!outputMap.get(patternNo).equals(reflectionLine)) {
						outputMap.put(patternNo, new ReflectionLine(Direction.HORIZONTAL, currentRow + 1));
						return (currentRow + 1) * 100L;
					}
					
				} else {
					if (outputMap.get(patternNo) == null) {
						outputMap.put(patternNo, new ReflectionLine(Direction.VERTICAL, currentRow + 1));
						return (currentRow + 1);
					}
					
					ReflectionLine reflectionLine = new ReflectionLine(Direction.VERTICAL, currentRow + 1);
					if (!outputMap.get(patternNo).equals(reflectionLine)) {
						outputMap.put(patternNo, new ReflectionLine(Direction.VERTICAL, currentRow + 1));
						return (currentRow + 1);
					}
					
				}
			}
			
		}
		return 0L;
	}
	
	private static char[][] rotateClockwise(char[][] originalArray) {
		int numRows = originalArray.length;
		int numCols = originalArray[0].length;
		
		char[][] rotatedArray = new char[numCols][numRows];
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				rotatedArray[j][numRows - 1 - i] = originalArray[i][j];
			}
		}
		
		return rotatedArray;
	}
	
	private static List<Pattern> readAllPatterns(List<String> input) {
		List<Pattern> output = new ArrayList<>();
		List<String> singlePattern = new ArrayList<>();
		for (String line : input) {
			if (line.isBlank()) {
				char[][] chars = ParseUtil.parseListToCharArray(singlePattern);
				output.add(new Pattern(chars));
				singlePattern = new ArrayList<>();
			} else {
				singlePattern.add(line);
			}
		}
		if (!singlePattern.isEmpty()) {
			char[][] chars = ParseUtil.parseListToCharArray(singlePattern);
			output.add(new Pattern(chars));
		}
		return output;
	}
	
	private record Pattern(char[][] array2D) {
	}
	
	private record ReflectionLine(Direction direction, int includedRowsOrCols) {
	}
	
	public enum Direction {
		HORIZONTAL,
		VERTICAL
	}
}
