package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AlgorithmUtils {
	
	// Stack-based flood fill
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
	
	private record Point(int row, int col) {
	}
}
