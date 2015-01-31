package games.poker;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.Stack;

/**
 * User: Steven
 */
public class ScoreService {

	//public Stack<Hand> handsTested = new Stack<Hand>();
	//public int scoredCounter = 0;
	static private int HAND_SIZE = 5;

	public Hand bestHand(HoleCards holeCards, Board board){
		Stack<Card> hand = holeCards.getHoleCards();
		for(Card card: board.getBoard()){
			hand.add(card);
		}
		Hand bestHand = buildHand(hand);
		//handsTested.removeAllElements();
		return bestHand;
	}

	private Hand buildHand(Stack<Card> hand) {
        Hand bestHand = new Hand();
        Hand testHand = new Hand();
        HandComparison comparedHands;
        ICombinatoricsVector<Card> hands = Factory.createVector(hand);
        Generator<Card> gen = Factory.createSimpleCombinationGenerator(hands, HAND_SIZE);
        for(ICombinatoricsVector<Card> combination: gen){
            for(Card card: combination){
                testHand.getHand().push(card);
            }
            if(bestHand.getHand().isEmpty()) {
                comparedHands = compareHands(testHand, testHand);
            } else {
                comparedHands = compareHands(testHand, bestHand);
            }
            testHand.getHand().clear();
            bestHand = comparedHands.getWinner();
        }
        return bestHand;
        /*Stack<Card> tempHand = new Stack<Card>();
		HandComparison comparedHands;
		while(hand.size() > 5){
			tempHand.add(hand.pop());
		}
		Hand bestHand = scoreHand(hand);
		Hand testHand = new Hand();
		for(Card newCard: tempHand){
			for(Card oldCard: hand){
				testHand.setHand(hand);
				testHand.getHand().remove(oldCard);
				testHand.getHand().push(newCard);
				comparedHands = compareHands(bestHand, testHand);
				bestHand = comparedHands.getWinner();
			}
		}
		testHand.setHand(hand);
		hand.removeAllElements();
		for(Card newCard: tempHand){
			hand.push(newCard);
		}

        for(int i = 0; i < 3; i++){
            if(!testHand.getHand().empty()){
		        hand.push(testHand.getHand().pop());
            }
        }

		Stack<Card> testPackage = new Stack<Card>();
		for(Card card: tempHand){
			testPackage.push(card);
		}
		tempHand.removeAllElements();
		for(Card card: testHand.getHand()){
			tempHand.push(card);
			testPackage.push(card);
		}
		for(Card newCard: tempHand){
			for(Card oldCard: hand){
				testHand.setHand(hand);
				testHand.getHand().remove(oldCard);
				testHand.getHand().push(newCard);
				comparedHands = compareHands(bestHand, testHand);
				bestHand = comparedHands.getWinner();
			}
		}
		for(Card card: testPackage){
			hand.remove(card);
		}
		for(Card card: hand){
			testHand.setHand(testPackage);
			testHand.getHand().push(card);
			comparedHands = compareHands(bestHand, testHand);
			bestHand = comparedHands.getWinner();
			testHand.getHand().remove(card);
		}


		return bestHand;*/
	}

	boolean sameHand(Hand test1, Hand test2){
		for(Card card:test1.getHand()){
			if(!test2.getHand().contains(card)){
				return false;
			}
		}
		return true;
	}

	public HandComparison compareHands(Hand bestHand, Hand testHand) {
		HandComparison comparedHands = new HandComparison();
		/*for(Hand hand:handsTested){
			if(sameHand(hand, testHand) || testHand.getHand().size() < 5)
			{
				comparedHands.setWinner(bestHand);
				return comparedHands;
			}
		}*/
		bestHand = scoreHand(bestHand.getHand());
		testHand = scoreHand(testHand.getHand());
		if(bestHand.getHandType().HandType() >= testHand.getHandType().HandType()){
			if(bestHand.getHandType().HandType() == testHand.getHandType().HandType()){
				int[] bestHandSorted = sortRanks(bestHand.getHand());
				int[] testHandSorted = sortRanks(testHand.getHand());
				for(int i = 13; i >= 0; i--){
					if(bestHandSorted[i] > testHandSorted[i]){
						comparedHands.setWinner(bestHand);
                        comparedHands.setLoser(testHand);
						return comparedHands;
					} else if(testHandSorted[i] > bestHandSorted[i]){
						comparedHands.setWinner(testHand);
                        comparedHands.setLoser(bestHand);
						return comparedHands;
					}
				}
                comparedHands.setTie(true);
			}
			comparedHands.setWinner(bestHand);
            comparedHands.setLoser(testHand);
			return comparedHands;
		}
		comparedHands.setWinner(testHand);
        comparedHands.setLoser(bestHand);
		return comparedHands;
	}

	private Hand scoreHand(Stack<Card> hand) {
		//scoredCounter++;
		Hand scoredHand = new Hand();
		scoredHand.setHand(hand);
		//handsTested.push(scoredHand);
		//int[] cardRanks = sortRanks(hand);
		if(isStraightFlush(hand)){
			scoredHand.setHandType(HandType.STRAIGHT_FLUSH);
			return scoredHand;
		} else if(isFourOfAKind(hand)){
			scoredHand.setHandType(HandType.FOUR_OF_A_KIND);
			return scoredHand;
		} else if(isFullHouse(hand)){
			scoredHand.setHandType(HandType.FULL_HOUSE);
			return scoredHand;
		} else if(isFlush(hand)){
			scoredHand.setHandType(HandType.FLUSH);
			return scoredHand;
		} else if(isStraight(hand)){
			scoredHand.setHandType(HandType.STRAIGHT);
			return scoredHand;
		} else if(isThreeOfAKind(hand)){
			scoredHand.setHandType(HandType.THREE_OF_A_KIND);
			return scoredHand;
		} else if(isTwoPair(hand)){
			scoredHand.setHandType(HandType.TWO_PAIR);
			return scoredHand;
		} else if(isPair(hand)){
			scoredHand.setHandType(HandType.PAIR);
			return scoredHand;
		} else {
			scoredHand.setHandType(HandType.HIGH_CARD);
			return scoredHand;
		}
	}

	private int[] sortRanks(Stack<Card> hand) {
		int[] ranks = new int[14];
		for(Card card: hand){
			ranks[card.rank.ordinal()]++;
		}
		return ranks;
	}

	private boolean isPair(Stack<Card> hand) {
		int[] cardSet = sortRanks(hand);
		for(int i:cardSet){
			if(i == 2){
				return true;
			}
		}
		return false;
	}

	private boolean isTwoPair(Stack<Card> hand) {int[] cardSet = sortRanks(hand);
		boolean onePair = false;
		for(int i:cardSet){
			if(i == 2){
				if(onePair){
					return true;
				}
				onePair = true;
			}
		}
		return false;
	}

	private boolean isThreeOfAKind(Stack<Card> hand) {
		int[] cardSet = sortRanks(hand);
		for(int i:cardSet){
			if(i == 3){
				return true;
			}
		}
		return false;
	}

	private boolean isStraight(Stack<Card> hand) {
		int[] cardSet = sortRanks(hand);
		int counter = 0;
		for(int i:cardSet){
			if(i == 1){
				for(int j = counter; j < counter+5; j++){
					if(cardSet[j] != 1){
						if(j == counter+4 && cardSet[j-1] == 5 && cardSet[j] ==14){
							return true;
						} else {
							return false;
						}
					}
				}
				return true;
			}
			counter++;
		}
		return false;
	}

	private boolean isFlush(Stack<Card> hand) {
		Suit suit = hand.peek().suit;
		for(Card card:hand){
			if(card.suit != suit){
				return false;
			}
		}
		return true;
	}

	private boolean isFullHouse(Stack<Card> hand) {
		if(isPair(hand) && isThreeOfAKind(hand)){
			return true;
		}
		return false;
	}

	private boolean isFourOfAKind(Stack<Card> hand) {
		int[] cardSet = sortRanks(hand);
		for(int i:cardSet){
			if(i == 4){
				return true;
			}
		}
		return false;
	}

	private boolean isStraightFlush(Stack<Card> hand) {
		if(isStraight(hand) && isFlush(hand))
		{
			return true;
		}
		return false;
	}

	private Stack<Hand> handCombinations(Stack<Card> cards){
		Stack<Hand> result = new Stack<Hand>();
		Stack<Card> base = new Stack();
		for(int i = 0; (i < HAND_SIZE && i < cards.size()); i++){
			base.push(cards.pop());
		}
		Hand testHand = new Hand();
		testHand.setHand(base);
		result.push(testHand);

		for(Card unused:cards){
			for(Card used: base){
				testHand = new Hand();
				testHand.setHand(base);
				testHand.getHand().remove(used);
				testHand.getHand().push(unused);
				result.push(testHand);
				Stack<Card> unusedCards = new Stack<Card>();
				for(Card unusedCard: unusedCards){

				}
			}
		}



		return null;
	}
}
