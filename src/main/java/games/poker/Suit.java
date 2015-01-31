package games.poker;

/**
 * User: Steven
 */
public enum Suit {
	HEARTS (1),
	DIAMONDS (2),
	SPADES (3),
	CLUBS (4);

	private final int suit;

	Suit(int suit){
		this.suit = suit;
	}

	public int Suit()
	{
		return suit;
	}
}
