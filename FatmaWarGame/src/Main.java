import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> mainDeck = createDeck();
        ArrayList<String> playerDeck = new ArrayList<String>();
        ArrayList<String> computerDeck = new ArrayList<String>();
        
        // Deal the cards
        for (int i = 0; i < 27; i++) {
            playerDeck.add(mainDeck.remove(0));
            computerDeck.add(mainDeck.remove(0));
        }
	}

	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<String>();
		String[] values = {"Ace", "Ace", "Ace", "Ace", "2", "2", "2", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		
		// Add 4 cards of each value
		for (int i = 0; i < 13; i++) {
	        for (int j = 0; j < 4; j++) {
	            deck.add(values[i]);
	        }
	    }
		
		// Add 2 jokers
	    for (int i = 0; i < 2; i++) {
	        deck.add("Joker");
	    }
		
		Collections.shuffle(deck);
		
		return deck;
	}
}
