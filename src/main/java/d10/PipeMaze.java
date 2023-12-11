package d10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PipeMaze {
	
	private static final char START_CHAR = 'S';
	private static final Map<Character, Set<Direction>> SYMBOL_MOVES = new HashMap<>();
	
	public static long calculate(char[][] input) throws IOException {
		createSymbolMoves();
		Point startPoint = findElement(input, START_CHAR);
		System.out.println("Element found at position " + startPoint);
		
		Point currentPoint = startPoint;
		Point previousPoint = currentPoint;
		List<Point> pointList = new ArrayList<>();
		long output = 0;
		
		do {
			Set<Direction> directions = SYMBOL_MOVES.get(input[currentPoint.row()][currentPoint.col()]);
			
			if (tryMove(input, currentPoint.northPoint(), directions, Direction.UP, previousPoint, PipeMaze::isAllowedCharacterUp)) {
				output++;
				previousPoint = currentPoint;
				currentPoint = currentPoint.northPoint();
				pointList.add(currentPoint);
			} else if (tryMove(input, currentPoint.southPoint(), directions, Direction.DOWN, previousPoint, PipeMaze::isAllowedCharacterDown)) {
				output++;
				previousPoint = currentPoint;
				currentPoint = currentPoint.southPoint();
				pointList.add(currentPoint);
			} else if (tryMove(input, currentPoint.eastPoint(), directions, Direction.RIGHT, previousPoint, PipeMaze::isAllowedCharacterRight)) {
				output++;
				previousPoint = currentPoint;
				currentPoint = currentPoint.eastPoint();
				pointList.add(currentPoint);
			} else if (tryMove(input, currentPoint.westPoint(), directions, Direction.LEFT, previousPoint, PipeMaze::isAllowedCharacterLeft)) {
				output++;
				previousPoint = currentPoint;
				currentPoint = currentPoint.westPoint();
				pointList.add(currentPoint);
			}
		} while (!startPoint.equals(currentPoint));
		
		//printMaze(input, pointList);
		return output / 2;
	}
	
	private static Point findElement(char[][] pattern, char target) {
		int rows = pattern.length;
		int cols = pattern[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (pattern[i][j] == target) {
					return new Point(i, j);
				}
			}
		}
		throw new IllegalArgumentException("Element not found");
	}
	
	private static boolean tryMove(char[][] input, Point point, Set<Direction> directions, Direction moveDirection, Point previousPoint, MoveValidator validator) {
		return checkAdjacentField(input, point)
				&& directions.contains(moveDirection)
				&& validator.isValidCharacter(input[point.row()][point.col()])
				&& !point.equals(previousPoint);
	}
	
	private static boolean checkAdjacentField(char[][] game, Point point) {
		int rowsAmount = game.length;
		int colsAmount = game[0].length;
		return isValidIndex(point, rowsAmount, colsAmount);
	}
	
	private static boolean isValidIndex(Point point, int rowsAmount, int colsAmount) {
		return point.row() >= 0
				&& point.row() < rowsAmount
				&& point.col() >= 0
				&& point.col() < colsAmount;
	}
	
	private static boolean isAllowedCharacterUp(char ch) {
		return ch == 'F' || ch == '7' || ch == '|' || ch == START_CHAR;
	}
	
	private static boolean isAllowedCharacterDown(char ch) {
		return ch == 'J' || ch == 'L' || ch == '|' || ch == START_CHAR;
	}
	
	private static boolean isAllowedCharacterRight(char ch) {
		return ch == 'J' || ch == '7' || ch == '-' || ch == START_CHAR;
	}
	
	private static boolean isAllowedCharacterLeft(char ch) {
		return ch == 'L' || ch == 'F' || ch == '-' || ch == START_CHAR;
	}
	
	private static void printMaze(char[][] input, List<Point> pointList) {
		for (int i = 0; i < input.length; i++) {
			final int rowNumber = i;  // Create final variable for row
			for (int j = 0; j < input[0].length; j++) {
				final int colNumber = j;  // Create final variable for column
				Point point = pointList.stream()
						.filter(p -> p.row() == rowNumber)
						.filter(p -> p.col() == colNumber)
						.findFirst()
						.orElse(null);
				System.out.print(point != null ? input[rowNumber][colNumber] : " ");
			}
			System.out.println();
		}
	}
	
	public enum Direction {
		RIGHT, LEFT, UP, DOWN
	}
	
	private static void createSymbolMoves() {
		SYMBOL_MOVES.put('S', EnumSet.allOf(Direction.class));
		SYMBOL_MOVES.put('-', EnumSet.of(Direction.LEFT, Direction.RIGHT));
		SYMBOL_MOVES.put('|', EnumSet.of(Direction.UP, Direction.DOWN));
		SYMBOL_MOVES.put('L', EnumSet.of(Direction.UP, Direction.RIGHT));
		SYMBOL_MOVES.put('F', EnumSet.of(Direction.DOWN, Direction.RIGHT));
		SYMBOL_MOVES.put('7', EnumSet.of(Direction.LEFT, Direction.DOWN));
		SYMBOL_MOVES.put('J', EnumSet.of(Direction.UP, Direction.LEFT));
		SYMBOL_MOVES.put('.', null);
	}
	
	private record Point(int row, int col) {
		
		@Override
		public String toString() {
			return "(" + row + ", " + col + ")";
		}
		
		public Point northPoint() {
			return new Point(this.row() - 1, this.col());
		}
		
		public Point southPoint() {
			return new Point(this.row() + 1, this.col());
		}
		
		public Point eastPoint() {
			return new Point(this.row(), this.col() + 1);
		}
		
		public Point westPoint() {
			return new Point(this.row(), this.col() - 1);
		}
	}
	
	@FunctionalInterface
	private interface MoveValidator {
		boolean isValidCharacter(char ch);
	}
}
