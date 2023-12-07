package d07;

public record Hands(String cards, PokerHand type, int bet) implements Comparable<Hands> {
	
	@Override
	public int compareTo(Hands o) {
		if (this.type != o.type) {
			return Integer.compare(this.type().getStrength(), o.type().getStrength());
		} else {
			for (int i = 0; i < this.cards().length(); i++) {
				if (this.cards.charAt(i) != o.cards.charAt(i)) {
					return Integer.compare(RegularCards.getValueBySymbol(this.cards().charAt(i)), RegularCards.getValueBySymbol(o.cards().charAt(i)));
				}
			}
		}
		return 0;
	}
}
