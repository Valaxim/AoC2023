package d18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LavaductLagoon {
	
	public static long calculate(List<String> input, Point seed) {
		List<Instruction> instructionList = parseInput(input);
		Set<Point> borderPoints = parseInstructions(instructionList);
		
		char[][] frame = generateCharArray(borderPoints);
		
		Set<Point> interiorPoints = findInteriorPointsStack(frame, borderPoints, seed);
		interiorPoints.addAll(borderPoints);
		
		frame = generateCharArray(interiorPoints);
		printCharArray(frame);
		
		return interiorPoints.size();
	}
	
	private static List<Instruction> parseInput(List<String> input) {
		List<Instruction> list = new ArrayList<>();
		for (String line : input) {
			String[] split = line.split(" ");
			list.add(new Instruction(split[0], Integer.parseInt(split[1])));
		}
		return list;
	}
	
	private static Set<Point> parseInstructions(List<Instruction> instructionList) {
		Point currentPoint = new Point(0, 0);
		Set<Point> pointSet = new HashSet<>();
		for (Instruction instruction : instructionList) {
			for (int step = 0; step < instruction.step(); step++) {
				pointSet.add(currentPoint);
				currentPoint = switch (instruction.direction) {
					case "R" -> new Point(currentPoint.row(), currentPoint.col() + 1);
					case "U" -> new Point(currentPoint.row() - 1, currentPoint.col());
					case "L" -> new Point(currentPoint.row(), currentPoint.col() - 1);
					case "D" -> new Point(currentPoint.row() + 1, currentPoint.col());
					default -> throw new IllegalStateException("Unexpected value: " + instruction.direction);
				};
			}
		}
		return pointSet;
	}
	
	private static char[][] generateCharArray(Set<Point> points) {
		int minRow = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxCol = Integer.MIN_VALUE;
		
		// Find the bounding box of the points
		for (Point point : points) {
			minRow = Math.min(minRow, point.row);
			maxRow = Math.max(maxRow, point.row);
			minCol = Math.min(minCol, point.col);
			maxCol = Math.max(maxCol, point.col);
		}
		
		// Calculate the dimensions of the result array
		int numRows = maxRow - minRow + 1;
		int numCols = maxCol - minCol + 1;
		
		// Create the result array filled with '.'
		char[][] resultArray = new char[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				resultArray[i][j] = '.';
			}
		}
		
		// Mark the points in the array as '#'
		for (Point point : points) {
			int adjustedRow = point.row - minRow;
			int adjustedCol = point.col - minCol;
			resultArray[adjustedRow][adjustedCol] = '#';
		}
		
		return resultArray;
	}
	
	private static Set<Point> findInteriorPointsStack(char[][] frame, Set<Point> borderPoints, Point seed) {
		// Choose any point inside the frame as the seed point - pass seed as param
		Set<Point> interiorPoints = new HashSet<>();
		
		Stack<Point> stack = new Stack<>();
		stack.push(seed);
		
		while (!stack.isEmpty()) {
			Point current = stack.pop();
			Point currentPoint = new Point(current.row, current.col);
			
			if (!borderPoints.contains(currentPoint) && !interiorPoints.contains(currentPoint)) {
				// If the point is not a border point and has not been visited, add it to the interior points
				interiorPoints.add(currentPoint);
				
				// Check neighboring points and push them onto the stack
				if (current.row - 1 >= 0 && frame[current.row - 1][current.col] != '#') {
					stack.push(new Point(current.row - 1, current.col)); // Up
				}
				
				if (current.row + 1 < frame.length && frame[current.row + 1][current.col] != '#') {
					stack.push(new Point(current.row + 1, current.col)); // Down
				}
				
				if (current.col - 1 >= 0 && frame[current.row][current.col - 1] != '#') {
					stack.push(new Point(current.row, current.col - 1)); // Left
				}
				
				if (current.col + 1 < frame[current.row].length && frame[current.row][current.col + 1] != '#') {
					stack.push(new Point(current.row, current.col + 1)); // Right
				}
			}
		}
		
		return interiorPoints;
	}
	
	private static void printCharArray(char[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}
	
	private record Instruction(String direction, int step) {
	}
	
	public record Point(int row, int col) {
	}
}