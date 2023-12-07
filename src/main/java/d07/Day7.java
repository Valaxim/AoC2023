package d07;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/7">Advent of Code 2023 - Day 7</a>
 */
public class Day7 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay7.txt");
		
		Long startTime = System.nanoTime();
		long outputA = CamelCards.calculate(input);
		Long endTime = System.nanoTime();
		
		System.out.println("Answer Day7 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = CamelCards.calculateB(input);
		endTime = System.nanoTime();
		
		System.out.println("Answer Day7 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

