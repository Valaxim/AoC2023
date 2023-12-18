package d18;

import d18.LavaductLagoon.Point;
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
		long outputA = LavaductLagoon.calculate(input, new Point(100,300));
		long endTime = System.nanoTime();
		
		System.out.println("Answer Day18 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

