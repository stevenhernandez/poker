package games.poker;

/**
 * User: Steven
 */
public class Player {

	private HoleCards holeCards = new HoleCards();
	private Hand bestHand = new Hand();
	private Hand knownOut = new Hand();
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HoleCards getHoleCards() {
		return holeCards;
	}

	public void setHoleCards(HoleCards holeCards) {
		this.holeCards = holeCards;
	}

	public Hand getBestHand() {
		return bestHand;
	}

	public void setBestHand(Hand bestHand) {
		this.bestHand = bestHand;
	}

	public Hand getKnownOut() {
		return knownOut;
	}

	public void setKnownOut(Hand knownOut) {
		this.knownOut = knownOut;
	}
}
