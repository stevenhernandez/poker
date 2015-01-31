package games.poker;

import java.util.Stack;

/**
 * User: Steven
 */
public class HandComparison {

	private Hand winner;
	private Hand loser;
	private boolean tie = false;
    private Stack<Card> relevantCardsToHandType;

    public Stack<Card> getRelevantCardsToHandType() {
        return relevantCardsToHandType;
    }

    public void setRelevantCardsToHandType(Stack<Card> relevantCardsToHandType) {
        this.relevantCardsToHandType = relevantCardsToHandType;
    }

    public Hand getWinner() {
		return winner;
	}

	public void setWinner(Hand winner) {
		this.winner = winner;
	}

	public Hand getLoser() {
		return loser;
	}

	public void setLoser(Hand loser) {
		this.loser = loser;
	}

	public boolean isTie() {
		return tie;
	}

	public void setTie(boolean tie) {
		this.tie = tie;
	}
}
