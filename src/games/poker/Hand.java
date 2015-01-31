package games.poker;

import java.util.Stack;

/**
 * User: Steven
 */
public class Hand {

	private Stack<Card> hand = new Stack<Card>();
	private HandType handType;

	public Stack<Card> getHand() {
		return hand;
	}

	public void setHand(Stack<Card> hand) {
		this.hand.removeAllElements();
		for(Card card: hand){
			this.hand.push(card);
		}
	}

	public HandType getHandType() {
		return handType;
	}

	public void setHandType(HandType handType) {
		this.handType = handType;
	}
}
