package d07;

public enum PokerHand {
	
	HIGH_CARD(1),
	ONE_PAIR(2),
	TWO_PAIR(3),
	THREE_OF_A_KIND(4),
	FULL_HOUSE(5),
	FOUR_OF_A_KIND(6),
	FIVE_OF_A_KIND(7);
	
	private final int strength;
	
	PokerHand(int strength) {
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}
}

