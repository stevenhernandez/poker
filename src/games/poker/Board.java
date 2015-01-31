package games.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Steven
 */
public class Board {

	private List<Card> board = new ArrayList<Card>();

	public List<Card> getBoard() {
		return board;
	}

	public void setBoard(List<Card> board) {
		this.board = board;
	}

	public void addCard(Card card){
		board.add(card);
	}

	public void addAll(List<Card> cards){
		for(Card card:cards){
			addCard(card);
		}
	}

}
