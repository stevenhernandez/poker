package games.poker;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    System.out.print("Number of Players : ");
	    String input = in.nextLine();
	    int numPlayers = Integer.parseInt(input);


	    Deck deck = new Deck();
	    for(int i = 0; i < 52; i++){
		    Card card = deck.deck.get(i);
	        System.out.println(card.rank.name() + " of " + card.suit.name());
	    }

	    System.out.println();
	    System.out.println();
	    System.out.println("End of first set");
	    System.out.println();
	    System.out.println();

	    deck.shuffle();
	    for(int i = 0; i < deck.deck.size(); i++){
		    Card card = deck.deck.get(i);
		    System.out.println(card.rank.name() + " of " + card.suit.name());
	    }
	    Stack<Player> players = new Stack<Player>();
	    for(int i = 0; i < numPlayers; i++){
		    Player player = new Player();
		    player.getHoleCards().setHoleCards(deck.dealHand());
		    players.push(player);
		    player.setName("Player" + i);
	    }

	    System.out.println();
	    System.out.println();

	    System.out.println("Player Hole Cards");
	    int counter = 0;
	    for(Player player: players){
		    System.out.println("Player" + counter + " Hole Cards");
		    for(Card card: player.getHoleCards().getHoleCards()){
			    System.out.println(card.rank.name() + " of " + card.suit.name());
		    }
		    System.out.println();
		    System.out.println();
		    counter++;
	    }

	    Board board = new Board();
	    ScoreService scoreService = new ScoreService();

	    for(Player player: players){
		    player.setBestHand(scoreService.bestHand(player.getHoleCards(), board));
	    }

	    board.setBoard(deck.flop());
	    board.getBoard().add(deck.turn());
	    board.getBoard().add(deck.river());

	    System.out.println("Board Cards");
	    for(Card card: board.getBoard()){
		    System.out.println(card.rank.name() + " of " + card.suit.name());
	    }
	    System.out.println();
	    System.out.println();

	    for(Player player: players){
		    player.setBestHand(scoreService.bestHand(player.getHoleCards(), board));
	    }


	    System.out.println("Player Best Hands");
	    counter = 0;
	    for(Player player: players){
		    System.out.println("Player" + counter + " Best Hand");
	        for(Card card: player.getBestHand().getHand()){
		        System.out.println(card.rank.name() + " of " + card.suit.name());
	        }
	        System.out.println(player.getBestHand().getHandType().name());

	        System.out.println();
	        System.out.println();
		    counter++;
	    }

	    HandComparison comparedHands = new HandComparison();
	    comparedHands.setWinner(players.peek().getBestHand());
	    Stack<Player> winners = new Stack<Player>();
	    for(Player player: players){
	        comparedHands = scoreService.compareHands(comparedHands.getWinner(), player.getBestHand());
		    if(scoreService.sameHand(comparedHands.getWinner(), player.getBestHand())){
			    if(!comparedHands.isTie()){
				    winners.removeAllElements();
			    }
			    winners.push(player);
		    }
	    }
	    if(winners.size() > 1){
		    System.out.println("The winners are: ");
	    } else {
		    System.out.println("The winner is: ");
	    }
		    for(Player player: winners){
			    System.out.println(player.getName());
		    }
    }
}
