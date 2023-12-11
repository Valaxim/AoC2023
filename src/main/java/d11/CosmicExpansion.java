package d11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CosmicExpansion {
	
	public static final char PLANET = '#';
	
	public static long calculate(char[][] input, long extendFactor) {
		List<Integer> rowsWithoutPlanet = getRowsWithoutPlanet(input);
		List<Integer> colsWithoutPlanet = getColsWithoutPlanet(input);
		
		List<Planet> planetList = findAllPlanet(input);
		
		Set<Pair<Planet>> result = new HashSet<>();
		for (int i = 0; i < planetList.size(); i++) {
			for (int j = i + 1; j < planetList.size(); j++) {
				Pair<Planet> pair = new Pair<>(planetList.get(i), planetList.get(j));
				result.add(pair);
			}
		}
		
		long output = 0;
		for (Pair<Planet> planetPair : result) {
			long allEmptyRowsBetweenPlanets = getAllEmptyRowsBetweenPlanets(rowsWithoutPlanet, planetPair);
			long allEmptyColsBetweenPlanets = getAllEmptyColsBetweenPlanets(colsWithoutPlanet, planetPair);
			output += (extendFactor - 1) * (allEmptyRowsBetweenPlanets + allEmptyColsBetweenPlanets);
			output += Planet.calculateDistance(planetPair.getFirst(), planetPair.getSecond());
		}
		return output;
	}
	
	private static List<Planet> findAllPlanet(char[][] galaxy) {
		List<Planet> planetList = new ArrayList<>();
		
		int rows = galaxy.length;
		int cols = galaxy[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (galaxy[i][j] == CosmicExpansion.PLANET) {
					planetList.add(new Planet(i, j));
				}
			}
		}
		return planetList;
	}
	
	private static List<Integer> getRowsWithoutPlanet(char[][] input) {
		List<Integer> rowsWithoutSymbol = new ArrayList<>();
		int counter = 0;
		for (char[] row : input) {
			boolean containsSymbol = false;
			for (char c : row) {
				if (c == CosmicExpansion.PLANET) {
					containsSymbol = true;
					break;
				}
			}
			if (!containsSymbol) {
				rowsWithoutSymbol.add(counter);
			}
			counter++;
		}
		return rowsWithoutSymbol;
	}
	
	private static List<Integer> getColsWithoutPlanet(char[][] input) {
		List<Integer> colsWithoutSymbol = new ArrayList<>();
		int numRows = input.length;
		int numCols = input[0].length;
		
		for (int col = 0; col < numCols; col++) {
			boolean containsSymbol = false;
			for (int row = 0; row < numRows; row++) {
				if (input[row][col] == CosmicExpansion.PLANET) {
					containsSymbol = true;
					break;
				}
			}
			if (!containsSymbol) {
				colsWithoutSymbol.add(col);
			}
		}
		return colsWithoutSymbol;
	}
	
	
	private static long getAllEmptyColsBetweenPlanets(List<Integer> colsWithoutPlanet, Pair<Planet> planetPair) {
		int minCol = Math.min(planetPair.getFirst().col(), planetPair.getSecond().col());
		int maxCol = Math.max(planetPair.getFirst().col(), planetPair.getSecond().col());
		return colsWithoutPlanet.stream()
				.filter(it -> it > minCol)
				.filter(it -> it < maxCol)
				.count();
	}
	
	private static long getAllEmptyRowsBetweenPlanets(List<Integer> rowsWithoutPlanet, Pair<Planet> planetPair) {
		int minRow = Math.min(planetPair.getFirst().row(), planetPair.getSecond().row());
		int maxRow = Math.max(planetPair.getFirst().row(), planetPair.getSecond().row());
		return rowsWithoutPlanet.stream()
				.filter(it -> it > minRow)
				.filter(it -> it < maxRow)
				.count();
	}
	
	private record Planet(int row, int col) {
		public static long calculateDistance(Planet p1, Planet p2) {
			return Math.abs(p1.row() - p2.row()) + Math.abs(p1.col() - p2.col());
		}
	}
	
}
