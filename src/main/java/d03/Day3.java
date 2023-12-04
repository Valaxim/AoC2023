package d03;

import utils.ParseUtil;

import java.io.IOException;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/3">Advent of Code 2023 - Day 3</a>
 */
public class Day3 {
	
	public static void main(String[] args) throws IOException {
		char[][] game = ParseUtil.read2DArray("inputDay3.txt");
		
		int outputA = GearRatios.getPartNumbersSum(game);
		long outputB = GearRatios.getAllGearRatiosSum(game);
		
		System.out.println("Answer Day3 partA: " + outputA);
		System.out.println("Answer Day3 partB: " + outputB);
	}
}

