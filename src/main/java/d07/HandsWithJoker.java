package d07;


public record HandsWithJoker(String cards, PokerHand type, int bet) implements Comparable<HandsWithJoker> {
	
	@Override
	public int compareTo(HandsWithJoker o) {
		if (this.type != o.type) {
			return Integer.compare(this.type().getStrength(), o.type().getStrength());
		} else {
			for (int i = 0; i < this.cards().length(); i++) {
				if (this.cards.charAt(i) != o.cards.charAt(i)) {
					return Integer.compare(CardsWithJoker.getValueBySymbol(this.cards().charAt(i)), CardsWithJoker.getValueBySymbol(o.cards().charAt(i)));
				}
			}
		}
		return 0;
	}
}