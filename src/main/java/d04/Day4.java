package d04;

import utils.ParseUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/4">Advent of Code 2023 - Day 4</a>
 */
public class Day4 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay4.txt");
		
		double outputA = Scratchcards.calculateTotalPoints(input);
		BigInteger outputB = Scratchcards.calculateTotalCardAmounts(input);
		
		System.out.println("Answer Day4 partA: " + outputA);
		System.out.println("Answer Day4 partB: " + outputB);
	}
}

