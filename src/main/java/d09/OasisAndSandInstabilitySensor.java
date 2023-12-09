package d09;

import java.util.List;

public class OasisAndSandInstabilitySensor {
	
	/**
	 * @param input - Given input
	 * @param reverse Should be false in part A, true in part B
	 * @return sum of extrapolated values (last element of each array)
	 */
	public static long calculate(List<String> input, boolean reverse) {
		long output = 0;
		
		for (String line : input) {
			int[] currentArray = parseStringArray(line.split(" "));
			
			if (reverse) {
				reverseArray(currentArray);
			}
			
			int[] nextArray;
			
			do {
				nextArray = fillArray(currentArray);
				output += currentArray[currentArray.length - 1];
				currentArray = nextArray;
			} while (containsNonZeroElements(nextArray));
		}
		return output;
	}
	
	private static int[] parseStringArray(String[] numberStrings) {
		int[] intArray = new int[numberStrings.length];
		for (int i = 0; i < numberStrings.length; i++) {
			intArray[i] = Integer.parseInt(numberStrings[i]);
		}
		return intArray;
	}
	
	private static void reverseArray(int[] currentArray) {
		int start = 0;
		int end = currentArray.length - 1;
		
		while (start < end) {
			int temp = currentArray[start];
			currentArray[start] = currentArray[end];
			currentArray[end] = temp;
			
			start++;
			end--;
		}
	}
	
	private static int[] fillArray(int[] currentArray) {
		int[] outputArray = new int[currentArray.length - 1];
		for (int i = 0; i < currentArray.length - 1; i++) {
			outputArray[i] = currentArray[i + 1] - currentArray[i];
		}
		return outputArray;
	}
	
	private static boolean containsNonZeroElements(int[] intArray) {
		for (int num : intArray) {
			if (num != 0) {
				return true;
			}
		}
		return false;
	}
}