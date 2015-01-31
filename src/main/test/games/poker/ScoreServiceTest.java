package games.poker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by Steven on 1/31/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {

    @Test
    public void shouldReturnHand1(){
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        Stack<Card> hand1Cards = new Stack<Card>();
        Stack<Card> hand2Cards = new Stack<Card>();
        Card card = new Card(Suit.CLUBS, Rank.JACK);
        hand1Cards.push(card);
        card = new Card(Suit.SPADES, Rank.KING);
        hand1Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.FOUR);
        hand1Cards.push(card);
        card = new Card(Suit.SPADES, Rank.SIX);
        hand1Cards.push(card);
        card = new Card(Suit.CLUBS, Rank.KING);
        hand1Cards.push(card);


        card = new Card(Suit.SPADES, Rank.NINE);
        hand2Cards.push(card);
        card = new Card(Suit.SPADES, Rank.KING);
        hand2Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.FOUR);
        hand2Cards.push(card);
        card = new Card(Suit.SPADES, Rank.SIX);
        hand2Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.KING);
        hand2Cards.push(card);

        hand1.setHand(hand1Cards);
        hand1.setHandType(HandType.PAIR);
        hand2.setHand(hand2Cards);
        hand2.setHandType(HandType.PAIR);
        ScoreService scoreService = new ScoreService();
        HandComparison comparedHands = scoreService.compareHands(hand1, hand2);

        assertEquals(scoreService.sameHand(hand1, comparedHands.getWinner()), true);
        assertEquals(scoreService.sameHand(comparedHands.getLoser(), hand2), true);

    }

    @Test
    public void shouldStillReturnHand1(){
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();

        Stack<Card> hand1Cards = new Stack<Card>();
        Stack<Card> hand2Cards = new Stack<Card>();
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        hand1Cards.push(card);
        card = new Card(Suit.HEARTS, Rank.THREE);
        hand1Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.THREE);
        hand1Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.QUEEN);
        hand1Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.KING);
        hand1Cards.push(card);


        card = new Card(Suit.SPADES, Rank.EIGHT);
        hand2Cards.push(card);
        card = new Card(Suit.SPADES, Rank.JACK);
        hand2Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.QUEEN);
        hand2Cards.push(card);
        card = new Card(Suit.SPADES, Rank.JACK);
        hand2Cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.KING);
        hand2Cards.push(card);

        hand1.setHand(hand1Cards);
        hand1.setHandType(HandType.PAIR);
        hand2.setHand(hand2Cards);
        hand2.setHandType(HandType.PAIR);
        ScoreService scoreService = new ScoreService();
        HandComparison comparedHands = scoreService.compareHands(hand1, hand2);

        assertEquals(scoreService.sameHand(hand1, comparedHands.getWinner()), true);
        assertEquals(scoreService.sameHand(comparedHands.getLoser(), hand2), true);

    }
}
