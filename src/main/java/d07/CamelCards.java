package d07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static d07.PokerHand.FIVE_OF_A_KIND;
import static d07.PokerHand.FOUR_OF_A_KIND;
import static d07.PokerHand.FULL_HOUSE;
import static d07.PokerHand.HIGH_CARD;
import static d07.PokerHand.ONE_PAIR;
import static d07.PokerHand.THREE_OF_A_KIND;
import static d07.PokerHand.TWO_PAIR;

public class CamelCards {
	
	public static long calculate(List<String> input) {
		
		List<Hands> allHands = new ArrayList<>();
		
		for (String line : input) {
			String[] playerGame = line.split(" ");
			PokerHand type = determineHandType(playerGame[0]);
			allHands.add(new Hands(playerGame[0], type, Integer.parseInt(playerGame[1])));
		}
		
		List<Hands> sortedRankOfEveryHand = allHands.stream().sorted().toList();
		
		return IntStream.range(0, sortedRankOfEveryHand.size())
				.map(i -> sortedRankOfEveryHand.get(i).bet() * (i + 1))
				.sum();
	}
	
	private static PokerHand determineHandType(String hand) {
		HandStructure result = getHandStructure(hand);
		return getPokerHandType(result.valuesCount(), result.pairs(), result.threeOfAKind(), result.fourOfAKind(), result.fiveOfAKind());
	}
	
	public static long calculateB(List<String> input) {
		List<HandsWithJoker> allHands = new ArrayList<>();
		
		for (String line : input) {
			String[] playerGame = line.split(" ");
			PokerHand type = determineHandTypeWithJoker(playerGame[0]);
			HandsWithJoker handsWithJoker = new HandsWithJoker(playerGame[0], type, Integer.parseInt(playerGame[1]));
			allHands.add(handsWithJoker);
		}
		
		List<HandsWithJoker> sorted = allHands.stream().sorted().toList();
		
		return IntStream.range(0, sorted.size())
				.map(i -> sorted.get(i).bet() * (i + 1))
				.sum();
	}
	
	private static PokerHand determineHandTypeWithJoker(String hand) {
		HandStructure result = getHandStructure(hand);
		
		Integer jokerAmount = result.valuesCount().remove('J');
		int output = (jokerAmount != null) ? jokerAmount : 0;
		
		if (output == 5) {
			return FIVE_OF_A_KIND;
		}
		
		replaceJokerByBestPossibleCard(output, result);
		
		return getPokerHandType(result.valuesCount(), result.pairs(), result.threeOfAKind(), result.fourOfAKind(), result.fiveOfAKind());
	}
	
	private static void replaceJokerByBestPossibleCard(int output, HandStructure result) {
		if (output > 0) {
			Optional<Map.Entry<Character, Integer>> max = result.valuesCount().entrySet().stream()
					.max(Map.Entry.comparingByValue());
			
			max.ifPresent(entry -> {
				Character key = entry.getKey();
				Integer value = entry.getValue() + output;
				result.valuesCount().put(key, result.valuesCount().getOrDefault(key, value) + output);
			});
		}
	}
	
	private static PokerHand getPokerHandType(Map<Character, Integer> valuesCount, int pairs, int threeOfAKind, int fourOfAKind, int fiveOfAKind) {
		for (int count : valuesCount.values()) {
			if (count == 2) {
				pairs++;
			} else if (count == 3) {
				threeOfAKind++;
			} else if (count == 4) {
				fourOfAKind++;
			} else if (count == 5) {
				fiveOfAKind++;
			}
		}
		if (fiveOfAKind == 1) {
			return FIVE_OF_A_KIND;
		} else if (fourOfAKind == 1) {
			return FOUR_OF_A_KIND;
		} else if (threeOfAKind == 1 && pairs == 1) {
			return FULL_HOUSE;
		} else if (threeOfAKind == 1) {
			return THREE_OF_A_KIND;
		} else if (pairs == 2) {
			return TWO_PAIR;
		} else if (pairs == 1) {
			return ONE_PAIR;
		} else {
			return HIGH_CARD;
		}
	}
	
	private static HandStructure getHandStructure(String hand) {
		Map<Character, Integer> valuesCount = new HashMap<>();
		for (char card : hand.toCharArray()) {
			valuesCount.put(card, valuesCount.getOrDefault(card, 0) + 1);
		}
		
		int pairs = 0;
		int threeOfAKind = 0;
		int fourOfAKind = 0;
		int fiveOfAKind = 0;
		return new HandStructure(valuesCount, pairs, threeOfAKind, fourOfAKind, fiveOfAKind);
	}
	
	private record HandStructure(Map<Character, Integer> valuesCount, int pairs, int threeOfAKind, int fourOfAKind, int fiveOfAKind) {
	}
}
