package d02;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	
	private static final int MAX_AMOUNT_RED_CUBES = 12;
	private static final int MAX_AMOUNT_GREEN_CUBES = 13;
	private static final int MAX_AMOUNT_BLUE_CUBES = 14;
	
	private record Game(int id, int redCubes, int blueCubes, int greenCubes) {
	}
	
	// partA
	public static Integer getIdsSum(List<String> dataFromInput) {
		List<Game> gameList = getGames(dataFromInput);
		return calculateIdsSumWithRequiredCubeAmount(gameList);
	}
	
	// partB
	public static Long getPowerOfMinimalCubesAmount(List<String> dataFromInput) {
		List<Game> gameList = getGames(dataFromInput);
		return calculatePowerOfMinimalCubeAmount(gameList);
	}
	
	private static List<Game> getGames(List<String> dataFromInput) {
		List<Game> gameList = new ArrayList<>();
		
		for (String str : dataFromInput) {
			Game game = getGameDetailsFromRow(str);
			gameList.add(game);
		}
		return gameList;
	}
	
	private static Game getGameDetailsFromRow(String str) {
		
		int maxAmountRedCubes = 0, maxAmountBlueCubes = 0, maxAmountGreenCubes = 0;
		String[] gameId = str.split(":");
		String id = gameId[0].replace("Game ", "");
		String[] setOfGames = gameId[1].split(";");
		
		for (String game : setOfGames) {
			String[] split = game.split(",");
			for (String color : split) {
				if (color.contains("red")) {
					maxAmountRedCubes = Math.max(maxAmountRedCubes, Integer.parseInt(color.replace("red", "").trim()));
				} else if (color.contains("blue")) {
					maxAmountBlueCubes = Math.max(maxAmountBlueCubes, Integer.parseInt(color.replace("blue", "").trim()));
				} else if (color.contains("green")) {
					maxAmountGreenCubes = Math.max(maxAmountGreenCubes, Integer.parseInt(color.replace("green", "").trim()));
				}
			}
		}
		return new Game(Integer.parseInt(id), maxAmountRedCubes, maxAmountBlueCubes, maxAmountGreenCubes);
	}
	
	private static Integer calculateIdsSumWithRequiredCubeAmount(List<Game> gameList) {
		return gameList.stream()
				.filter(it -> it.blueCubes() <= MAX_AMOUNT_BLUE_CUBES)
				.filter(it -> it.redCubes() <= MAX_AMOUNT_RED_CUBES)
				.filter(it -> it.greenCubes() <= MAX_AMOUNT_GREEN_CUBES)
				.map(Game::id)
				.mapToInt(Integer::intValue)
				.sum();
	}
	
	
	private static Long calculatePowerOfMinimalCubeAmount(List<Game> gameList) {
		return gameList.stream()
				.mapToLong(it -> (long) it.greenCubes() * it.blueCubes * it.redCubes)
				.sum();
	}
}

