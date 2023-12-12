package utils;

public class MathUtils {
	
	// Function to calculate the Greatest Common Divisor (GCD) using Euclidean algorithm
	private static long gcd(long a, long b) {
		while (b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
	
	// Function to calculate the Least Common Multiple (LCM) of two numbers
	private static long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}
	
	// Function to calculate the LCM of an array of numbers
	public static long findLCM(long[] numbers) {
		if (numbers.length < 2) {
			throw new IllegalArgumentException("At least two numbers are required.");
		}
		
		long result = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			result = lcm(result, numbers[i]);
		}
		
		return result;
	}
}
