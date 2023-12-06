package d05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Almanac {
	
	private static List<Long> seedList;
	private static final List<NumberMap> seedToSoilList = new ArrayList<>();
	private static final List<NumberMap> soilToFertilizerList = new ArrayList<>();
	private static final List<NumberMap> fertilizerToWaterList = new ArrayList<>();
	private static final List<NumberMap> waterToLightList = new ArrayList<>();
	private static final List<NumberMap> lightToTemperatureList = new ArrayList<>();
	private static final List<NumberMap> temperatureToHumidityList = new ArrayList<>();
	private static final List<NumberMap> humidityToLocationList = new ArrayList<>();
	
	public static long calculate(List<String> input) {
		Context currentContext = null;
		
		for (String line : input) {
			if (line.contains("map:")) {
				currentContext = setContextFromMapLine(line);
			} else if (line.contains("seeds:")) {
				seedList = extractSeeds(line);
			} else if (!line.isBlank()) {
				writeLine(line, currentContext);
			}
		}
		return calculateMinLocation();
	}
	
	private static Context setContextFromMapLine(String line) {
		for (Context context : Context.values()) {
			if (line.replaceAll("-", "_").contains(context.name().toLowerCase())) {
				return context;
			}
		}
		return null;
	}
	
	private static List<Long> extractSeeds(String line) {
		String[] split = line.split("seeds:");
		return Arrays.stream(split[1].trim().split(" "))
				.mapToLong(Long::valueOf)
				.boxed()
				.collect(Collectors.toList());
	}
	
	private static void writeLine(String line, Context currentContext) {
		List<Long> entry = Arrays.stream(line.split(" "))
				.mapToLong(Long::valueOf)
				.boxed()
				.toList();
		
		NumberMap map = new NumberMap(entry.get(0), entry.get(1), entry.get(2));
		
		getListForContext(currentContext).add(map);
	}
	
	private static List<NumberMap> getListForContext(Context currentContext) {
		return switch (currentContext) {
			case SEED_TO_SOIL -> seedToSoilList;
			case SOIL_TO_FERTILIZER -> soilToFertilizerList;
			case FERTILIZER_TO_WATER -> fertilizerToWaterList;
			case WATER_TO_LIGHT -> waterToLightList;
			case LIGHT_TO_TEMPERATURE -> lightToTemperatureList;
			case TEMPERATURE_TO_HUMIDITY -> temperatureToHumidityList;
			case HUMIDITY_TO_LOCATION -> humidityToLocationList;
		};
	}
	
	private static long calculateMinLocation() {
		long output = Long.MAX_VALUE;
		
		for (Long seed : seedList) {
			long location = seed;
			
			List<List<NumberMap>> mappingLists = Arrays.asList(
					seedToSoilList,
					soilToFertilizerList,
					fertilizerToWaterList,
					waterToLightList,
					lightToTemperatureList,
					temperatureToHumidityList,
					humidityToLocationList
			);
			
			for (List<NumberMap> mappingList : mappingLists) {
				location = applyMapping(location, mappingList);
			}
			
			output = Math.min(output, location);
		}
		
		return output;
	}
	
	private static long applyMapping(long value, List<NumberMap> mappingList) {
		return mappingList.stream()
				.filter(item -> item.sourceRangeStart() <= value && value < item.sourceRangeStart() + item.rangeLength())
				.findFirst()
				.map(it -> it.destinationRangeStart() + (value - it.sourceRangeStart()))
				.orElse(value);
	}
	
	enum Context {
		SEED_TO_SOIL,
		SOIL_TO_FERTILIZER,
		FERTILIZER_TO_WATER,
		WATER_TO_LIGHT,
		LIGHT_TO_TEMPERATURE,
		TEMPERATURE_TO_HUMIDITY,
		HUMIDITY_TO_LOCATION
	}
	
	private record NumberMap(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
	}
}
