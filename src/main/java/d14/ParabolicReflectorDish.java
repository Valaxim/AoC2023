package d14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ParabolicReflectorDish {
	
	private static final char ROUNDED_ROCKS = 'O';
	public static final char CUBE_ROCKS = '#';
	public static final char EMPTY_SPACE = '.';
	public static final long CYCLE_AMOUNT = 1000000000;
	
	public static long calculate(char[][] input) {
		for (int row = 0; row < input.length; row++) {
			for (int col = 0; col < input[0].length; col++) {
				moveRockToNorth(input, row, col);
			}
		}
		return calculateDamage(input);
	}
	
	public static long calculateB(char[][] input) {
		Map<Long, Cache> cache = new HashMap<>();
		long cycle;
		for (cycle = 0; cycle < CYCLE_AMOUNT; cycle++) {
			for (int row = 0; row < input.length; row++) {
				for (int col = 0; col < input[0].length; col++) {
					moveRockToNorth(input, row, col);
				}
			}
			
			for (int row = 0; row < input.length; row++) {
				for (int col = 0; col < input[0].length; col++) {
					moveRockToWest(input, row, col);
				}
			}
			
			for (int row = input.length - 1; row >= 0; row--) {
				for (int col = input[0].length - 1; col >= 0; col--) {
					moveRockToSouth(input, row, col);
				}
			}
			
			for (int row = input.length - 1; row >= 0; row--) {
				for (int col = input[0].length - 1; col >= 0; col--) {
					moveRockToEast(input, row, col);
				}
			}
			
			// create deep copy
			char[][] deepCopy = new char[input.length][input[0].length];
			for (int i = 0; i < input.length; i++) {
				deepCopy[i] = Arrays.copyOf(input[i], input[i].length);
			}
			
			long currentDamage = calculateDamage(input);
			Cache currentCache = new Cache(deepCopy, cycle + 1);
			
			if (cache.get(currentDamage) == null) {
				cache.put(currentDamage, currentCache);
			} else {
				Cache firstElementOfCycle = cache.get(currentDamage);
				if (firstElementOfCycle.equals(currentCache)) {
					long noOfElementsFromCycle = (CYCLE_AMOUNT - firstElementOfCycle.cycleNo()) % (currentCache.cycleNo() - firstElementOfCycle.cycleNo());
					
					return cache.entrySet().stream()
							.filter(entry -> entry.getValue().cycleNo() == noOfElementsFromCycle + firstElementOfCycle.cycleNo())
							.map(Map.Entry::getKey)
							.findFirst()
							.orElse(-1L);
				}
			}
		}
		return calculateDamage(input);
	}
	
	private static long calculateDamage(char[][] input) {
		long output = 0;
		int rowsNum = input.length;
		for (int row = 0; row < input.length; row++) {
			for (int col = 0; col < input[0].length; col++) {
				output += input[row][col] == ROUNDED_ROCKS ? rowsNum - row : 0;
			}
		}
		return output;
	}
	
	private static void moveRockToNorth(char[][] input, int row, int col) {
		if (input[row][col] == ROUNDED_ROCKS && row != 0) {
			int currentRow = row;
			while (canMoveUp(input, currentRow, col)) {
				input[currentRow - 1][col] = ROUNDED_ROCKS;
				input[currentRow][col] = EMPTY_SPACE;
				currentRow--;
			}
		}
	}
	
	private static void moveRockToSouth(char[][] input, int row, int col) {
		if (input[row][col] == ROUNDED_ROCKS && row < input.length - 1) {
			int currentRow = row;
			while (canMoveDown(input, currentRow, col)) {
				input[currentRow + 1][col] = ROUNDED_ROCKS;
				input[currentRow][col] = EMPTY_SPACE;
				currentRow++;
			}
		}
	}
	
	private static void moveRockToEast(char[][] input, int row, int col) {
		if (input[row][col] == ROUNDED_ROCKS && col <= input[0].length) {
			int currentCol = col;
			while (canMoveRight(input, row, currentCol)) {
				input[row][currentCol + 1] = ROUNDED_ROCKS;
				input[row][currentCol] = EMPTY_SPACE;
				currentCol++;
			}
		}
	}
	
	private static void moveRockToWest(char[][] input, int row, int col) {
		if (input[row][col] == ROUNDED_ROCKS && col != 0) {
			int currentCol = col;
			while (canMoveLeft(input, row, currentCol)) {
				input[row][currentCol - 1] = ROUNDED_ROCKS;
				input[row][currentCol] = EMPTY_SPACE;
				currentCol--;
			}
		}
	}
	
	private static boolean canMoveLeft(char[][] input, int row, int col) {
		return col - 1 >= 0
				&& input[row][col - 1] == EMPTY_SPACE;
	}
	
	private static boolean canMoveRight(char[][] input, int row, int col) {
		return col + 1 < input[0].length
				&& input[row][col + 1] == EMPTY_SPACE;
	}
	
	private static boolean canMoveUp(char[][] input, int row, int col) {
		return row - 1 >= 0
				&& input[row - 1][col] == EMPTY_SPACE;
	}
	
	private static boolean canMoveDown(char[][] input, int row, int col) {
		return row + 1 < input.length
				&& input[row + 1][col] == EMPTY_SPACE;
	}
	
	record Cache(char[][] input, long cycleNo) {
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Cache cache)) return false;
			return Arrays.deepEquals(input, cache.input);
		}
		
		@Override
		public int hashCode() {
			return Arrays.deepHashCode(input);
		}
		
		@Override
		public String toString() {
			return new StringJoiner(", ", Cache.class.getSimpleName() + "[", "]")
					.add("input=" + Arrays.toString(input))
					.toString();
		}
	}
}
