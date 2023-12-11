package d10;

import utils.ParseUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/10">Advent of Code 2023 - Day 10</a>
 */
public class Day10 {
	
	public static void main(String[] args) throws IOException {

		char[][] chars = ParseUtil.read2DArray("inputDay10.txt");
		
		Long startTime = System.nanoTime();
		long outputA = PipeMaze.calculate(chars);
		Long endTime = System.nanoTime();
		
		System.out.println("Answer Day10 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

