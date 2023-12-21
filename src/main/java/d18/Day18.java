package d18;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/18">Advent of Code 2023 - Day 18</a>
 */
public class Day18 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay18.txt");
		
		long startTime = System.nanoTime();
		double outputGaussA = Gauss.calculateGaussA(input);
		long endTime = System.nanoTime();
		System.out.printf("Answer Day18 partA: %.0f, calculated in %f ms %n", outputGaussA, (endTime - startTime) / 1000000.0);
		
		startTime = System.nanoTime();
		double outputGaussB = Gauss.calculateGaussB(input);
		endTime = System.nanoTime();
		System.out.printf("Answer Day18 partB: %.0f, calculated in %f ms %n", outputGaussB, (endTime - startTime) / 1000000.0);
		// 7934109210 - too low
	}
}

