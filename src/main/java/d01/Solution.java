package d01;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	
	public static Integer getSum(List<String> dataFromInput) {
		int sum = 0;
		
		for (String str : dataFromInput) {
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			for (char ch : str.toCharArray()) {
				if (Character.isDigit(ch)) {
					stack.push(Integer.parseInt(String.valueOf(ch)));
				}
			}
			sum = calculateCalibrationValue(stack, sum);
		}
		return sum;
	}
	
	/**
	 * Method that create Calibration Value from first and last digit from each row
	 */
	private static int calculateCalibrationValue(ArrayDeque<Integer> stack, int sum) {
		int lastDigit = stack.getFirst();
		int firstDigit = stack.getLast();
		sum += firstDigit * 10 + lastDigit;
		return sum;
	}
	
	/**
	 * Method that replace e.g. "one" into "one1one"
	 */
	public static List<String> replaceDigitsWrittenAsString(List<String> input) {
		List<String> output = new ArrayList<>();
		
		for (String str : input) {
			for (Digit dig : Digit.values()) {
				str = str.replaceAll(dig.getDigitAsString(), dig.getDigitAsString().concat(String.valueOf(dig.getDigitValue())).concat(dig.getDigitAsString()));
			}
			output.add(str);
		}
		return output;
	}
}
