package d07;

public enum CardsWithJoker {
	
	ACE('A', 12),
	KING('K', 11),
	QUEEN('Q', 10),
	TEN('T', 9),
	NINE('9', 8),
	EIGHT('8', 7),
	SEVEN('7', 6),
	SIX('6', 5),
	FIVE('5', 4),
	FOUR('4', 3),
	THREE('3', 2),
	TWO('2', 1),
	JOKER('J', 0);
	
	private final char symbol;
	private final int value;
	
	CardsWithJoker(char symbol, int value) {
		this.symbol = symbol;
		this.value = value;
	}
	
	public static int getValueBySymbol(char symbol) {
		for (CardsWithJoker card : CardsWithJoker.values()) {
			if (card.symbol == symbol) {
				return card.value;
			}
		}
		throw new IllegalArgumentException("Invalid card symbol: " + symbol);
	}
	
}