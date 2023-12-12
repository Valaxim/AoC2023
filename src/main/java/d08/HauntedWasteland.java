package d08;

import utils.MathUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HauntedWasteland {
	
	private static final String STARTING_LOCATION = "AAA";
	private static final String DESTINATION = "ZZZ";
	private static final char RIGHT = 'R';
	
	private String instruction;
	private List<Node> nodeList;
	
	public BigInteger calculate(List<String> input) {
		initializeData(input);
		
		BigInteger step = BigInteger.ZERO;
		Node currentNode = findStartingNode();
		
		int instructionLength = instruction.length();
		
		while (!currentNode.name().equals(DESTINATION)) {
			char direction = getDirectionAtCurrentStep(step, instructionLength);
			currentNode = getNextNode(currentNode, direction);
			step = step.add(BigInteger.ONE);
		}
		return step;
	}
	
	public BigInteger calculateB(List<String> input) {
		initializeData(input);
		Map<Node, BigInteger> cycleMap = new HashMap<>();
		BigInteger step = BigInteger.ZERO;
		
		List<Node> currentNodes = nodeList.stream()
				.filter(it -> it.name().endsWith("A"))
				.toList();
		
		int instructionLength = instruction.length();
		
		while (!checkExitCondition(cycleMap, currentNodes)) {
			List<Node> newNode = new ArrayList<>();
			for (Node node : currentNodes) {
				char direction = getDirectionAtCurrentStep(step, instructionLength);
				node = getNextNode(node, direction);
				newNode.add(node);
			}
			step = step.add(BigInteger.ONE);
			currentNodes = newNode;
			
			for (Node node : currentNodes) {
				if (node.name().endsWith("Z")) {
					cycleMap.putIfAbsent(node, step);
					if (cycleMap.size() == currentNodes.size()) {
						break;
					}
				}
			}
		}
		
		long[] array = cycleMap.values().stream()
				.mapToLong(BigInteger::longValue)
				.toArray();
		
		long lcm = MathUtils.findLCM(array);
		return BigInteger.valueOf(lcm);
	}
	
	private Node findStartingNode() {
		return nodeList.stream()
				.filter(it -> it.name().equals(HauntedWasteland.STARTING_LOCATION))
				.findFirst()
				.orElseThrow();
	}
	
	private boolean checkExitCondition(Map<Node, BigInteger> cycleMap, List<Node> currentNodes) {
		long size = currentNodes.size();
		return cycleMap.size() == size;
	}
	
	private void initializeData(List<String> input) {
		getInstructionsAndNodes(input);
	}
	
	private char getDirectionAtCurrentStep(BigInteger step, int instructionLength) {
		return instruction.charAt(step.mod(BigInteger.valueOf(instructionLength)).intValue());
	}
	
	private Node getNextNode(Node currentNode, char direction) {
		return nodeList.stream()
				.filter(node -> direction == RIGHT ? node.name().equals(currentNode.right()) : node.name().equals(currentNode.left()))
				.findFirst()
				.orElseThrow();
	}
	
	private void getInstructionsAndNodes(List<String> input) {
		nodeList = new ArrayList<>();
		for (String line : input) {
			if (line.contains("=")) {
				String[] parts = line.replaceAll("\\s", "").split("[=(,)]");
				nodeList.add(new Node(parts[0], parts[2], parts[3]));
			} else if (!line.isBlank()) {
				instruction = line;
			}
		}
	}
	
	public record Node(String name, String left, String right) {
	}
}
