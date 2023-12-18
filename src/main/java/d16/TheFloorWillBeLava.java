package d16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static d16.Beam.BeamDirection.DOWN;
import static d16.Beam.BeamDirection.LEFT;
import static d16.Beam.BeamDirection.RIGHT;
import static d16.Beam.BeamDirection.UP;

public class TheFloorWillBeLava {
	
	static Set<Beam> beamSet = new HashSet<>();
	protected static char[][] game;
	static int gameRows;
	static int gameCols;
	
	public static long calculate(char[][] input) {
		List<Beam> activeBeams = initBeam(input);
		
		do {
			activeBeams = moveAllBeams(activeBeams);
		} while (!activeBeams.isEmpty());
		
		Set<Point> collect = beamSet.stream()
				.map(Beam::point)
				.collect(Collectors.toSet());
		
		return collect.size();
	}
	
	private static List<Beam> initBeam(char[][] input) {
		game = input;
		gameRows = input.length;
		gameCols = input[0].length;
		
		List<Beam> activeBeams = new ArrayList<>();
		Beam currentBeam = new Beam(new Point(0, 0), RIGHT);
		activeBeams.add(currentBeam);
		return activeBeams;
	}
	
	private static List<Beam> moveAllBeams(List<Beam> activeBeams) {
		List<Beam> newActiveBeams = new ArrayList<>();
		
		for (Beam beam : activeBeams) {
			if (isValidIndex(beam.point().row(), beam.point().col())) {
				beamSet.add(beam);
				char currentSymbol = game[beam.point().row()][beam.point().col()];
				List<Beam> beams = move(beam, currentSymbol);
				newActiveBeams.addAll(beams);
			}
		}
		newActiveBeams.removeAll(beamSet);
		return newActiveBeams;
	}
	
	private static boolean isValidIndex(int currentRow, int currentCol) {
		return currentRow >= 0
				&& currentRow < gameRows
				&& currentCol >= 0
				&& currentCol < gameCols;
	}
	
	private static List<Beam> move(Beam beam, char currentChar) {
		return switch (beam.beamDirection()) {
			case RIGHT -> beam.moveRight(currentChar);
			case LEFT -> beam.moveLeft(currentChar);
			case UP -> beam.moveUp(currentChar);
			case DOWN -> beam.moveDown(currentChar);
		};
	}
	
	public static long calculateB(char[][] input) {
		Set<Beam> allPossibleBeam = generateAllPossibleBeam(input);
		Map<Beam, Integer> output = new HashMap<>();
		for (Beam beam : allPossibleBeam) {
			
			List<Beam> activeBeams = resetBeam(beam, input);
			
			do {
				activeBeams = moveAllBeams(activeBeams);
			} while (!activeBeams.isEmpty());
			
			Set<Point> collect = beamSet.stream()
					.map(Beam::point)
					.collect(Collectors.toSet());
			output.put(beam, collect.size());
		}
		return output.values().stream()
				.max(Integer::compareTo)
				.orElse(0);
	}
	
	private static Set<Beam> generateAllPossibleBeam(char[][] input) {
		Set<Beam> beams = new HashSet<>();
		
		int rows = input.length;
		int cols = input[0].length;
		
		// Top and bottom rows
		for (int col = 0; col < cols; col++) {
			beams.add(new Beam(new Point(0, col), DOWN));
			beams.add(new Beam(new Point(rows - 1, col), UP));
		}
		
		// Left and right columns
		for (int row = 0; row < rows; row++) {
			beams.add(new Beam(new Point(row, 0), RIGHT));
			beams.add(new Beam(new Point(rows, cols - 1), LEFT));
		}
		return beams;
	}
	
	private static List<Beam> resetBeam(Beam currentBeam, char[][] input) {
		game = input;
		gameRows = input.length;
		gameCols = input[0].length;
		beamSet = new HashSet<>();
		
		List<Beam> activeBeams = new ArrayList<>();
		activeBeams.add(currentBeam);
		return activeBeams;
	}
	
}