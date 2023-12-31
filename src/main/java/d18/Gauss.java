package d18;

import java.util.ArrayList;
import java.util.List;

public class Gauss {
	
	private static long perimeter = 0L;
	
	public static double calculateGaussA(List<String> input) {
		List<Instruction> instructionList = parseInput(input);
		List<Point> borderPoints = parseInstructionsGauss(instructionList);
		return calculateAreaGauss(borderPoints) + (0.5 * perimeter + 1); // adjustment, points have area
	}
	
	public static double calculateGaussB(List<String> input) {
		List<Instruction> instructionList = parseHexInput(input);
		List<Point> borderPoints = parseInstructionsGauss(instructionList);
		return calculateAreaGauss(borderPoints) + (0.5 * perimeter + 1); // adjustment, points have area
	}
	
	private static List<Instruction> parseInput(List<String> input) {
		List<Instruction> list = new ArrayList<>();
		for (String line : input) {
			String[] split = line.split(" ");
			list.add(new Instruction(split[0], Integer.parseInt(split[1])));
		}
		return list;
	}
	
	private static List<Instruction> parseHexInput(List<String> input) {
		List<Instruction> list = new ArrayList<>();
		for (String line : input) {
			String[] split = line.split(" ");
			String replace = split[2].substring(2, 8);
			String direction = switch (replace.charAt(5)) {
				case '0' -> "R";
				case '1' -> "D";
				case '2' -> "L";
				case '3' -> "U";
				default -> throw new IllegalStateException("Unexpected value: " + replace.charAt(5));
			};
			list.add(new Instruction(direction, Integer.parseInt(replace.substring(0, 5), 16)));
		}
		return list;
	}
	
	public static double calculateAreaGauss(List<Point> pointSet) {
		int pointsAmount = pointSet.size();
		double area = 0;
		
		for (int point = 0; point < pointsAmount; point++) {
			Point currentPoint = pointSet.get(point);
			Point nextPoint = pointSet.get((point + 1) % pointsAmount);
			
			area += ((double) currentPoint.row * nextPoint.col) - ((double) currentPoint.col * nextPoint.row);
		}
		
		return 0.5 * Math.abs(area);
	}
	
	private static List<Point> parseInstructionsGauss(List<Instruction> instructionList) {
		perimeter = 0L;
		Point currentPoint = new Point(0, 0);
		List<Point> pointSet = new ArrayList<>();
		pointSet.add(currentPoint);
		
		for (Instruction instruction : instructionList) {
			currentPoint = switch (instruction.direction) {
				case "R" -> new Point(currentPoint.row(), currentPoint.col() + instruction.step());
				case "U" -> new Point(currentPoint.row() - instruction.step(), currentPoint.col());
				case "L" -> new Point(currentPoint.row(), currentPoint.col() - instruction.step());
				case "D" -> new Point(currentPoint.row() + instruction.step(), currentPoint.col());
				default -> throw new IllegalStateException("Unexpected value: " + instruction.direction);
			};
			perimeter += instruction.step();
			pointSet.add(currentPoint);
		}
		return pointSet;
	}
	
	
	private record Instruction(String direction, int step) {
	}
	
	public record Point(int row, int col) {
	}
}