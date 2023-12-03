package d02;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/2">Advent of Code 2023 - Day 2</a>
 */
public class Day2 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay2.txt");
		
		int outputA = CubeConundrum.getIdsSum(input);
		long outputB = CubeConundrum.getPowerOfMinimalCubesAmount(input);
		
		System.out.println("Answer Day1 partA: " + outputA);
		System.out.println("Answer Day1 partB: " + outputB);
	}
}

