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
	
	public static char[][] read2DArray(String fileName) throws IOException {
		List<String> rowsArray = readInputLineByLine(fileName);
		
		char[][] arrays = new char[rowsArray.size()][rowsArray.get(0).length()];
		for (int i = 0; i < rowsArray.size(); i++) {
			String s = rowsArray.get(i);
			
			for (int j = 0; j < s.length(); j++) {
				arrays[i][j] = s.charAt(j);
			}
		}
		return arrays;
	}
	
	public static int[][] read2DIntegerArray(String fileName) throws IOException {
		List<String> rowsArray = readInputLineByLine(fileName);
		
		int[][] arrays = new int[rowsArray.size()][rowsArray.get(0).length()];
		for (int i = 0; i < rowsArray.size(); i++) {
			String s = rowsArray.get(i);
			
			for (int j = 0; j < s.length(); j++) {
				arrays[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
			}
		}
		return arrays;
	}
	
	public static char[][] parseListToCharArray(List<String> stringList) {
		int numRows = stringList.size();
		int maxNumCols = 0;
		
		// Find the maximum number of columns
		for (String str : stringList) {
			maxNumCols = Math.max(maxNumCols, str.length());
		}
		
		// Create the 2D char array
		char[][] charArray2D = new char[numRows][maxNumCols];
		
		// Populate the 2D char array
		for (int i = 0; i < numRows; i++) {
			String str = stringList.get(i);
			for (int j = 0; j < str.length(); j++) {
				charArray2D[i][j] = str.charAt(j);
			}
		}
		
		return charArray2D;
	}
}
