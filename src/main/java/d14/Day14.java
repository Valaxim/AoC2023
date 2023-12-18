package d14;

import utils.ParseUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static d14.ParabolicReflectorDish.CUBE_ROCKS;
import static d14.ParabolicReflectorDish.EMPTY_SPACE;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/14">Advent of Code 2023 - Day 14</a>
 */
public class Day14 {
	
	private static final char NOTHING = ' ';
	
	public static void main(String[] args) throws IOException {
		
		char[][] input = ParseUtil.read2DArray("inputDay14.txt");
		
		long startTime = System.nanoTime();
		long outputA = ParabolicReflectorDish.calculate(input);
		long endTime = System.nanoTime();

		System.out.println("Answer Day14 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = ParabolicReflectorDish.calculateB(input);
		endTime = System.nanoTime();
		
		System.out.println("Answer Day14 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

