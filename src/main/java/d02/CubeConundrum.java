package d02;

import java.util.List;
import java.util.stream.Stream;

public class CubeConundrum {
	
	private static final int MAX_AMOUNT_RED_CUBES = 12;
	private static final int MAX_AMOUNT_GREEN_CUBES = 13;
	private static final int MAX_AMOUNT_BLUE_CUBES = 14;
	
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
		return dataFromInput.stream()
				.map(CubeConundrum::getGameDetailsFromRow)
				.toList();
	}
	
	private static Game getGameDetailsFromRow(String str) {
		String[] gameId = str.split(":");
		String id = gameId[0].replace("Game ", "");
		String[] setOfGames = gameId[1].split(";");
		
		int maxAmountRedCubes = getMaxCubeAmount(setOfGames, "red");
		int maxAmountBlueCubes = getMaxCubeAmount(setOfGames, "blue");
		int maxAmountGreenCubes = getMaxCubeAmount(setOfGames, "green");
		
		return new Game(Integer.parseInt(id), maxAmountRedCubes, maxAmountBlueCubes, maxAmountGreenCubes);
	}
	
	private static int getMaxCubeAmount(String[] setOfGames, String color) {
		return Stream.of(setOfGames)
				.flatMap(game -> Stream.of(game.split(",")))
				.filter(c -> c.contains(color))
				.mapToInt(c -> Integer.parseInt(c.replace(color, "").trim()))
				.max()
				.orElse(0);
	}
	
	private static Integer calculateIdsSumWithRequiredCubeAmount(List<Game> gameList) {
		return gameList.stream()
				.filter(game -> game.blueCubes() <= MAX_AMOUNT_BLUE_CUBES)
				.filter(game -> game.redCubes() <= MAX_AMOUNT_RED_CUBES)
				.filter(game -> game.greenCubes() <= MAX_AMOUNT_GREEN_CUBES)
				.mapToInt(Game::id)
				.sum();
	}
	
	private static Long calculatePowerOfMinimalCubeAmount(List<Game> gameList) {
		return gameList.stream()
				.mapToLong(game -> (long) game.greenCubes() * game.blueCubes() * game.redCubes())
				.sum();
	}
	
	private record Game(int id, int redCubes, int blueCubes, int greenCubes) {
	}
}