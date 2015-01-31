package games.poker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

/**
 * Created by Steven on 1/30/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    @Test
    public void shouldReturnBothPlayers(){
        ScoreService scoreService = new ScoreService();
        Stack<Player> players = new Stack<Player>();
        Player player = new Player();
        Stack<Card> cards = new Stack<Card>();
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.ACE);
        cards.push(card);
        card = new Card(Suit.DIAMONDS, Rank.EIGHT);
        cards.push(card);
        card = new Card(Suit.CLUBS, Rank.EIGHT);
        cards.push(card);
        card = new Card(Suit.CLUBS, Rank.FIVE);
        cards.push(card);
        Hand hand = new Hand();
        hand.setHand(cards);
        player.setBestHand(hand);
        player.getBestHand().setHandType(HandType.TWO_PAIR);
        players.push(player);
        cards.remove(card);
        card = new Card(Suit.DIAMONDS, Rank.FIVE);
        cards.push(card);
        hand = new Hand();
        hand.setHand(cards);
        player = new Player();
        player.setBestHand(hand);
        player.getBestHand().setHandType(HandType.TWO_PAIR);
        players.push(player);

        Stack<Player> result = new Stack<Player>();
        result = Main.findWinners(players, scoreService);
        assertEquals(players, result);
    }
}
