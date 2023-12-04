package d04;

import java.math.BigInteger;

public class Card {
	
	private final int id;
	private final int winningNumbersAmount;
	private BigInteger cardAmounts;
	
	public Card(int id, int winningNumbersAmount) {
		this.id = id;
		this.winningNumbersAmount = winningNumbersAmount;
		this.cardAmounts = BigInteger.ONE;
	}
	
	public void addCards(BigInteger winningNumbersAmount) {
		this.cardAmounts = this.cardAmounts.add(winningNumbersAmount);
	}
	
	public int getId() {
		return id;
	}
	
	public int getWinningNumbersAmount() {
		return winningNumbersAmount;
	}
	
	public BigInteger getCardAmounts() {
		return cardAmounts;
	}
}


