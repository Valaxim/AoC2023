package d01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Solution {
	
	public List<Integer> createDataFromInput(List<String> input) {
		List<Integer> values = new ArrayList<>();
		int value = 0;
		Iterator var4 = input.iterator();
		
		String str = null;
		while (var4.hasNext()) {
			str = (String) var4.next();
			if (str.equals("")) {
				values.add(value);
				value = 0;
			} else {
				value += Integer.parseInt(str);
			}
		}
		values.add(Integer.parseInt(str));
		return values;
	}
	
	public Integer getInteger(List<Integer> dataFromInput) {
		Integer var = dataFromInput.stream()
				.max(Integer::compare)
				.get();
		return var;
	}
	
	public int getVarTop3(List<Integer> dataFromInput) {
		return  dataFromInput.stream()
				.sorted(Comparator.reverseOrder())
				.limit(3)
				.mapToInt(Integer::intValue)
				.sum();
		
	}
	
}
