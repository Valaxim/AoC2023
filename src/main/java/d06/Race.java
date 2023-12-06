package d06;

import java.util.Arrays;
import java.util.List;

public class Race {
	
	public static long calculateAsArray(List<String> input) {
		String[] timeValues = input.get(0).replaceAll("[^0-9 ]", "").trim().split("\\s+");
		String[] distanceValues = input.get(1).replaceAll("[^0-9 ]", "").trim().split("\\s+");
		
		long[] raceTimes = Arrays.stream(timeValues)
				.mapToLong(Long::parseLong)
				.toArray();
		
		long[] raceDistances = Arrays.stream(distanceValues)
				.mapToLong(Long::parseLong)
				.toArray();
		
		return calculateWinningCombinationAmount(raceTimes, raceDistances);
	}
	
	public static long calculateAsSingleValue(List<String> input) {
		String timeValue = input.get(0).replaceAll("[^0-9]", "").trim();
		String distanceValue = input.get(1).replaceAll("[^0-9]", "").trim();
		
		long[] raceTimes = new long[]{Long.parseLong(timeValue)};
		long[] raceDistances = new long[]{Long.parseLong(distanceValue)};
		
		return calculateWinningCombinationAmount(raceTimes, raceDistances);
	}
	
	private static long calculateWinningCombinationAmount(long[] raceTimes, long[] raceDistances) {
		long output = 1;
		
		for (int i = 0; i < raceTimes.length; i++) {
			long time = raceTimes[i];
			long distance = raceDistances[i];
			
			long suceeded = 0;
			for (int hold = 0; hold < time; hold++) {
				if ((time - hold) * hold > distance) {
					suceeded++;
				}
			}
			output *= suceeded;
		}
		return output;
	}
}

