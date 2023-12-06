package d05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static d05.Almanac.Context.FERTILIZER_TO_WATER;
import static d05.Almanac.Context.HUMIDITY_TO_LOCATION;
import static d05.Almanac.Context.LIGHT_TO_TEMPERATURE;
import static d05.Almanac.Context.SEED_TO_SOIL;
import static d05.Almanac.Context.SOIL_TO_FERTILIZER;
import static d05.Almanac.Context.TEMPERATURE_TO_HUMIDITY;
import static d05.Almanac.Context.WATER_TO_LIGHT;

public class Almanac {
	
	enum Context {
		SEED_TO_SOIL,
		SOIL_TO_FERTILIZER,
		FERTILIZER_TO_WATER,
		WATER_TO_LIGHT,
		LIGHT_TO_TEMPERATURE,
		TEMPERATURE_TO_HUMIDITY,
		HUMIDITY_TO_LOCATION
	}
	
	private static List<Long> seedList;
	private static List<NumberMap> seedToSoilList = new ArrayList<>();
	private static List<NumberMap> soilToFertilizerList = new ArrayList<>();
	private static List<NumberMap> fertilizerToWaterList = new ArrayList<>();
	private static List<NumberMap> waterToLightList = new ArrayList<>();
	private static List<NumberMap> lightToTemperatureList = new ArrayList<>();
	private static List<NumberMap> temperatureToHumidityList = new ArrayList<>();
	private static List<NumberMap> humidityToLocationList = new ArrayList<>();
	
	
	public static long calculate(List<String> input) {
		Context currentContext = SEED_TO_SOIL;
		for (String line : input) {
			if (line.contains("seeds:")) {
				String[] split = line.split("seeds:");
				seedList = Arrays.stream(split[1].trim().split(" "))
						.mapToLong(Long::valueOf)
						.boxed()
						.collect(Collectors.toList());
			} else if (line.contains("seed-to-soil")) {
				currentContext = SEED_TO_SOIL;
			} else if (line.contains("soil-to-fertilizer")) {
				currentContext = SOIL_TO_FERTILIZER;
			} else if (line.contains("fertilizer-to-water")) {
				currentContext = FERTILIZER_TO_WATER;
			} else if (line.contains("water-to-light")) {
				currentContext = WATER_TO_LIGHT;
			} else if (line.contains("light-to-temperature")) {
				currentContext = LIGHT_TO_TEMPERATURE;
			} else if (line.contains("temperature-to-humidity")) {
				currentContext = TEMPERATURE_TO_HUMIDITY;
			} else if (line.contains("humidity-to-location")) {
				currentContext = HUMIDITY_TO_LOCATION;
			} else if (line.isBlank()) {
				// skip
			} else {
				writeLine(line, currentContext);
			}
		}
		
		long minLocation = calculateMinLocation();
		return minLocation;
	}
	
	private static long calculateMinLocation() {
		long output = Long.MAX_VALUE;
		for (Long seed : seedList) {
			long soil = seedToSoilList.stream()
					.filter(item -> item.sourceRangeStart() <= seed && seed < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (seed - it.sourceRangeStart))
					.orElse(seed);
			
			long fertilizer = soilToFertilizerList.stream()
					.filter(item -> item.sourceRangeStart() <= soil && soil < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (soil - it.sourceRangeStart))
					.orElse(soil);
			
			long water = fertilizerToWaterList.stream()
					.filter(item -> item.sourceRangeStart() <= fertilizer && fertilizer < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (fertilizer - it.sourceRangeStart))
					.orElse(fertilizer);
			
			long light = waterToLightList.stream()
					.filter(item -> item.sourceRangeStart() <= water && water < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (water - it.sourceRangeStart))
					.orElse(water);
			
			long temperature = lightToTemperatureList.stream()
					.filter(item -> item.sourceRangeStart() <= light && light < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (light - it.sourceRangeStart))
					.orElse(light);
			
			long humidity = temperatureToHumidityList.stream()
					.filter(item -> item.sourceRangeStart() <= temperature && temperature < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (temperature - it.sourceRangeStart))
					.orElse(temperature);
			
			long location = humidityToLocationList.stream()
					.filter(item -> item.sourceRangeStart() <= humidity && humidity < item.sourceRangeStart() + item.rangeLength())
					.findFirst()
					.map(it -> it.destinationRangeStart() + (humidity - it.sourceRangeStart))
					.orElse(humidity);

			output = Math.min(output, location);
		}
		return output;
	}
	
	private static void writeLine(String line, Context currentContext) {
		List<Long> entry = Arrays.stream(line.split(" "))
				.mapToLong(Long::valueOf)
				.boxed()
				.toList();
		
		NumberMap map = new NumberMap(entry.get(0), entry.get(1), entry.get(2));
		
		switch (currentContext) {
			case SEED_TO_SOIL -> seedToSoilList.add(map);
			case SOIL_TO_FERTILIZER -> soilToFertilizerList.add(map);
			case FERTILIZER_TO_WATER -> fertilizerToWaterList.add(map);
			case WATER_TO_LIGHT -> waterToLightList.add(map);
			case LIGHT_TO_TEMPERATURE -> lightToTemperatureList.add(map);
			case TEMPERATURE_TO_HUMIDITY -> temperatureToHumidityList.add(map);
			case HUMIDITY_TO_LOCATION -> humidityToLocationList.add(map);
		}
	}
	
	private record NumberMap(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
	}
}

