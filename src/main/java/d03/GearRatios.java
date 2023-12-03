package d03;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GearRatios {
	
	public static final char EMPTY_SYMBOL = '.';
	public static final char GEAR_SYMBOL = '*';
	
	public static int solution(char[][] game) {
		List<EnginePart> enginePartList = createEnginePartList(game);
		
		return enginePartList.stream()
				.filter(EnginePart::isPartNumber)
				.mapToInt(EnginePart::serialNumber)
				.sum();
	}
	
	public static long solutionB(char[][] game) {
		List<EnginePart> enginePartList = createEnginePartList(game);
		List<Item> itemList = findGearItems(game);
		
		long output = 0;
		for (Item item : itemList) {
			Set<Integer> adjacentEngineParts = getAdjacentEngineParts(item, enginePartList);
			if (adjacentEngineParts.size() == 2) {
				output += adjacentEngineParts.stream().mapToLong(Integer::longValue).reduce(1, (a, b) -> a * b);
			}
		}
		return output;
	}
	
	private static List<Item> findGearItems(char[][] game) {
		List<Item> itemList = new ArrayList<>();
		
		for (int row = 0; row < game.length; row++) {
			for (int col = 0; col < game[0].length; col++) {
				if (game[row][col] == GEAR_SYMBOL) {
					itemList.add(new Item(row, col));
				}
			}
		}
		return itemList;
	}
	
	private static Set<Integer> getAdjacentEngineParts(Item item, List<EnginePart> enginePartList) {
		return item.getNeighbours().stream()
				.map(point -> getNumberIfNeighbour(enginePartList, point.row, point.col))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toSet());
	}
	
	private static Optional<Integer> getNumberIfNeighbour(List<EnginePart> enginePartList, int x, int y) {
		return enginePartList.stream()
				.filter(enginePart -> enginePart.points().contains(new Point(x, y)))
				.map(EnginePart::serialNumber)
				.findFirst();
	}
	
	private static List<EnginePart> createEnginePartList(char[][] game) {
		List<EnginePart> output = new ArrayList<>();
		String tempNumber = StringUtils.EMPTY;
		boolean isPartNumber = false;
		int startCol = -1;
		
		for (int row = 0; row < game.length; row++) {
			for (int col = 0; col < game[0].length; col++) {
				if (!Character.isDigit(game[row][col])) {
					tempNumber = createEnginePartIfExistsAndClearCache(tempNumber, output, isPartNumber, row, startCol);
					startCol = -1;
					isPartNumber = false;
				} else if (Character.isDigit(game[row][col])) {
					tempNumber = tempNumber.concat(String.valueOf(game[row][col]));
					if (startCol == -1) {
						startCol = col;
					}
					if (!isPartNumber) {
						isPartNumber = checkAdjacentField(game, row, col);
					}
				}
			}
			tempNumber = createEnginePartIfExistsAndClearCache(tempNumber, output, isPartNumber, row, startCol);
			startCol = -1;
		}
		
		return output;
	}
	
	private static boolean checkAdjacentField(char[][] game, int currentRow, int currentCol) {
		int amountOfRow = game.length;
		int amountOfCols = game[0].length;
		
		return (isValidIndex(currentRow - 1, currentCol - 1, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow - 1][currentCol - 1]))
				|| (isValidIndex(currentRow - 1, currentCol, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow - 1][currentCol]))
				|| (isValidIndex(currentRow - 1, currentCol + 1, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow - 1][currentCol + 1]))
				|| (isValidIndex(currentRow, currentCol - 1, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow][currentCol - 1]))
				|| (isValidIndex(currentRow, currentCol + 1, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow][currentCol + 1]))
				|| (isValidIndex(currentRow + 1, currentCol - 1, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow + 1][currentCol - 1]))
				|| (isValidIndex(currentRow + 1, currentCol, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow + 1][currentCol]))
				|| (isValidIndex(currentRow + 1, currentCol + 1, amountOfRow, amountOfCols) && isNotDigitOrEmptySymbol(game[currentRow + 1][currentCol + 1]));
	}
	
	private static boolean isValidIndex(int currentRow, int currentCol, int rowsAmount, int colsAmount) {
		return currentRow >= 0
				&& currentRow < rowsAmount
				&& currentCol >= 0
				&& currentCol < colsAmount;
	}
	
	private static boolean isNotDigitOrEmptySymbol(Character character) {
		return !Character.isDigit(character) && character != EMPTY_SYMBOL;
	}
	
	private static String createEnginePartIfExistsAndClearCache(String tempNumber, List<EnginePart> output, boolean isPartNumber, int row, int colStartNumber) {
		if (!tempNumber.isEmpty()) {
			List<Point> points = new ArrayList<>();
			for (int i = 0; i < tempNumber.length(); i++) {
				points.add(new Point(row, i + colStartNumber));
			}
			output.add(new EnginePart(Integer.parseInt(tempNumber), isPartNumber, points));
		}
		tempNumber = StringUtils.EMPTY;
		return tempNumber;
	}
	
	private record EnginePart(int serialNumber, boolean isPartNumber, List<Point> points) {
	}
	
	private record Point(int row, int col) {
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Point point)) return false;
			return row == point.row && col == point.col;
		}
	}
	
	private record Item(int row, int col) {
		
		public List<Point> getNeighbours() {
			int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
			int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};
			
			List<Point> neighbours = new ArrayList<>();
			for (int i = 0; i < rowOffsets.length; i++) {
				int newRow = row + rowOffsets[i];
				int newCol = col + colOffsets[i];
				neighbours.add(new Point(newRow, newCol));
			}
			return neighbours;
		}
	}
	
}
