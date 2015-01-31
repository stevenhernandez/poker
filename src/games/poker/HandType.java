package games.poker;

/**
 * User: Steven
 */
public enum HandType {

	HIGH_CARD(0),
	PAIR(1),
	TWO_PAIR(2),
	THREE_OF_A_KIND(3),
	STRAIGHT(4),
	FLUSH(5),
	FULL_HOUSE(6),
	FOUR_OF_A_KIND(7),
	STRAIGHT_FLUSH(8);

	private final int strength;

	HandType(int handType){
		this.strength = handType;
	}

	public int HandType()
	{
		return strength;
	}
}
