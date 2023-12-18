package d13;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/13">Advent of Code 2023 - Day 13</a>
 */
public class Day13 {
	
	public static void main(String[] args) throws IOException {

		List<String> input = ParseUtil.readInputLineByLine("inputDay13.txt");

		long startTime = System.nanoTime();
		long outputA = PointOfIncidence.calculate(input);
		long endTime = System.nanoTime();

		System.out.println("Answer Day13 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = PointOfIncidence.calculateB(input);
		endTime = System.nanoTime();
		
		System.out.println("Answer Day13 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

