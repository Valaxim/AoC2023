package d19;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aplenty {
	
	public static long calculate(List<String> input) {
		List<List<String>> inputResult = splitList(input); // 0 - workflows, 1 - instructions
		
		List<Workflow> workflows = createWorkflows(inputResult.get(0));
		List<Part> instructionLists = parseInstructionSets(inputResult.get(1));
		
		return instructionLists.stream()
				.filter(instruction -> evaluateInstruction(workflows, instruction).equals("A"))
				.mapToLong(Part::getRating)
				.sum();
	}
	
	private static String evaluateInstruction(List<Workflow> workflows, Part instruction) {
		String result = "in";
		
		while (!result.equals("A") && !result.equals("R")) {
			String finalResult = result;
			Optional<Workflow> optionalWorkflow = workflows.stream()
					.filter(workflow -> workflow.name().equals(finalResult))
					.findFirst();
			
			if (optionalWorkflow.isPresent()) {
				Workflow currentWorkflow = optionalWorkflow.get();
				result = currentWorkflow.apply(instruction);
			} else {
				throw new IllegalArgumentException("Invalid workflow format");
			}
		}
		
		return result;
	}
	
	public static List<List<String>> splitList(List<String> input) {
		List<List<String>> result = new ArrayList<>();
		List<String> currentSublist = new ArrayList<>();
		
		for (String line : input) {
			if (line.isEmpty()) {
				result.add(currentSublist);
				currentSublist = new ArrayList<>();
			} else {
				currentSublist.add(line);
			}
		}
		
		result.add(currentSublist);
		return result;
	}
	
	private static List<Part> parseInstructionSets(List<String> input) {
		List<Part> instructionSets = new ArrayList<>();
		
		for (String line : input) {
			Part instructionSet = parseInstructionSet(line);
			instructionSets.add(instructionSet);
		}
		return instructionSets;
	}
	
	private static List<Workflow> createWorkflows(List<String> input) {
		List<Workflow> workflows = new ArrayList<>();
		
		for (String line : input) {
			Workflow workflow = parseWorkflow(line);
			workflows.add(workflow);
		}
		
		return workflows;
	}
	
	private static Workflow parseWorkflow(String input) {
		Pattern pattern = Pattern.compile("^(\\w+)\\{(.*?)\\}$");
		Matcher matcher = pattern.matcher(input);
		
		if (matcher.find()) {
			String name = matcher.group(1);
			String ruleString = matcher.group(2);
			List<Rule> ruleList = parseRules(ruleString);
			return new Workflow(name, ruleList);
		}
		throw new IllegalArgumentException("Invalid input format");
	}
	
	private static Part parseInstructionSet(String input) {
		// Use regular expressions to extract information from the input string
		String[] parts = input.replaceAll("[{}]", "").split(",");
		
		int x = 0, m = 0, a = 0, s = 0;
		
		for (String part : parts) {
			String[] keyValue = part.split("=");
			if (keyValue.length == 2) {
				String key = keyValue[0].trim();
				int value = Integer.parseInt(keyValue[1].trim());
				
				switch (key) {
					case "x" -> x = value;
					case "m" -> m = value;
					case "a" -> a = value;
					case "s" -> s = value;
					default -> throw new IllegalStateException("Unexpected value: " + key);
				}
			}
		}
		return new Part(x, m, a, s);
	}
	
	private static List<Rule> parseRules(String ruleString) {
		List<Rule> ruleList = new ArrayList<>();
		
		String[] ruleTokens = ruleString.split(",");
		
		for (String ruleToken : ruleTokens) {
			int firstColonIndex = ruleToken.indexOf(":");
			if (firstColonIndex != -1) {
				String condition = ruleToken.substring(0, firstColonIndex);
				String accepted = ruleToken.substring(firstColonIndex + 1);
				ruleList.add(new Rule(condition, accepted));
			} else {
				ruleList.add(new Rule(ruleToken, ""));
			}
		}
		return ruleList;
	}
	
}