package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseUtil {
	
	public static List<String> readInputLineByLine(String fileName) throws IOException {
		List<String> strings = new ArrayList<>();
		InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);
		
		try (var defReader = new BufferedReader(new InputStreamReader(systemResourceAsStream))) {
			String line;
			while ((line = defReader.readLine()) != null) {
				strings.add(line);
			}
		}
		return strings;
	}
}
