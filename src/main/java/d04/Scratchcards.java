package d04;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Scratchcards {
	
	public static double calculateTotalPoints(List<String> input) {
		double totalPoints = 0;
		for (String line : input) {
			totalPoints += calculatePointsInGame(line);
		}
		return totalPoints;
	}
	
	public static BigInteger calculateTotalCardAmounts(List<String> input) {
		List<Card> cardList = new ArrayList<>();
		int currentLine = 0;
		
		for (String line : input) {
			int winningNumbersAmount = countWinningNumbers(line);
			cardList.add(new Card(++currentLine, winningNumbersAmount));
		}
		
		updateCardAmounts(cardList);
		
		return cardList.stream()
				.map(Card::getCardAmounts)
				.reduce(BigInteger.ZERO, BigInteger::add);
	}
	
	private static void updateCardAmounts(List<Card> cardList) {
		for (Card card : cardList) {
			if (card.getWinningNumbersAmount() > 0) {
				int currentId = card.getId();
				for (int i = 0; i < card.getWinningNumbersAmount(); i++) {
					cardList.get(currentId + i).addCards(card.getCardAmounts());
				}
			}
		}
	}
	
	public static double calculatePointsInGame(String input) {
		Set<Integer> winningNumbers = getWinningNumbers(input);
		return winningNumbers.isEmpty() ? 0 : Math.pow(2, winningNumbers.size() - 1.0);
	}
	
	public static int countWinningNumbers(String input) {
		Set<Integer> winningNumbers = getWinningNumbers(input);
		return winningNumbers.size();
	}
	
	private static Set<Integer> getWinningNumbers(String input) {
		String[] split = input.split(":");
		String[] parts = split[1].split("\\|");
		
		Set<Integer> set1 = extractNumbers(parts[0]);
		Set<Integer> set2 = extractNumbers(parts[1]);
		
		set1.retainAll(set2);
		
		return set1;
	}
	
	private static Set<Integer> extractNumbers(String part) {
		return Arrays.stream(part.trim().split("\\s+"))
				.map(Integer::parseInt)
				.collect(Collectors.toSet());
	}
}
