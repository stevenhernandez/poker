package games.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * User: Steven
 */
public class Deck {

	private Stack<Card> deck = new Stack<Card>();

	public Deck(){
		for(Rank rank: Rank.values()){
			for(Suit suit: Suit.values()){
				deck.push(new Card(suit, rank));
			}
		}
	}

    public Card get(int index){
        return deck.get(index);
    }

    public int size(){
        return deck.size();
    }

	public void shuffle(){
		Random rn = new Random();
		Stack<Card> tempDeck = new Stack<Card>();
		int deckSize = deck.size();
		for(int i = 0; i < deckSize; i++){
			int cardAt = rn.nextInt(deckSize - i);
			Card card = deck.get(cardAt);
			deck.remove(cardAt);
			tempDeck.push(card);
		}
		deck = tempDeck;
	}

	public Stack<Card> dealHand(){
		Stack<Card> hand = new Stack<Card>();
		hand.add(deck.pop());
		hand.add(deck.pop());
		return hand;
	}

	public List<Card> flop(){
		List<Card> board = new ArrayList<Card>();
		board.add(deck.pop());
		board.add(deck.pop());
		board.add(deck.pop());
		return board;
	}

	public Card dealOne(){
		return deck.pop();
	}

	public Card turn(){
		return dealOne();
	}

	public Card river(){
		return dealOne();
	}
}
