import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


public class Main {

	static Scanner sc = new Scanner(System.in);
	

	public static void main(String[] args) {
		ArrayList<String> mainDeck = createDeck();
        ArrayList<String> playerDeck = new ArrayList<String>();
        ArrayList<String> computerDeck = new ArrayList<String>();
        String Token = "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";
        // Deal the cards
        for (int i = 0; i < 27; i++) {
            playerDeck.add(mainDeck.remove(0));
            computerDeck.add(mainDeck.remove(0));
        }
        
        System.out.println(Arrays.toString(playerDeck.toArray()));
        System.out.println(Arrays.toString(computerDeck.toArray()));

        
        System.out.println("Welcome to Fatma War Game!");
        sc.nextLine();
        printNumOfCards(playerDeck, computerDeck);
	}

	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<String>();
//		String[] values = {"A","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		// for warMode debugging
		String[] values = {"A","A", "Q", "4", "A", "A", "A", "K", "A", "A", "A", "A", "A"};

		
		// Add 4 cards of each value
		for (int i = 0; i < 13; i++) {
	        for (int j = 0; j < 4; j++) {
	            deck.add(values[i]);
	        }
	    }
		
		// Add 2 jokers
	    for (int i = 0; i < 2; i++) {
	        deck.add("Jo");
	    }
		
		Collections.shuffle(deck);
		
		return deck;
	}
	
	public static void showCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		String playerCurCard = playerDeck.get(0);
		String compCurCard = computerDeck.get(0);
		System.out.println("AI " + playerCurCard);
		System.out.println("Player " + compCurCard);
		sc.nextLine();
		compareCards(playerDeck, computerDeck, playerCurCard, compCurCard);
		
	}
	
	public static void compareCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck, String playerCurCard, String compCurCard) {
		// Compare the cards
	    if (getCardValue(playerCurCard) < getCardValue(compCurCard)) {
	        // Player wins, add both cards to the end of their deck
	        playerDeck.add(playerCurCard);
	        playerDeck.add(compCurCard);
	        playerDeck.remove(0);
			computerDeck.remove(0);
	        printNumOfCards(playerDeck, computerDeck);
	        
	    } else if (getCardValue(playerCurCard) > getCardValue(compCurCard)) {
	        // Computer wins, add both cards to the end of their deck
	        computerDeck.add(compCurCard);
	        computerDeck.add(playerCurCard);
	        playerDeck.remove(0);
			computerDeck.remove(0);
	        printNumOfCards(playerDeck, computerDeck);
	        
	    } else {
	        warMode(playerDeck, computerDeck);
	    }
	}
	
	public static void printNumOfCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		System.out.println("AI " + computerDeck.size() + " cards");
		System.out.println("Player " + playerDeck.size() + " cards");
		playQuest(playerDeck, computerDeck);
	}
	
	public static void playQuest(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		System.out.println("Play? ");
		String userInput = sc.nextLine();
        if (userInput.toLowerCase().equals("no")) {
        	System.out.println("AI wins! Good bye");
        }
        else if (userInput.equals("")) {
        	showCards(playerDeck, computerDeck);
        }
        else {
        	System.out.println("The input is wrong. Please try again");
        }
	}
	
	public static void warMode(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		System.out.println("War!");
		sc.nextLine();
		ArrayList<String> playerWarList = new ArrayList<String>();
		ArrayList<String> compWarList = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			playerWarList.add(playerDeck.get(i));
		}
		for (int i = 0; i < 5; i++) {
			compWarList.add(computerDeck.get(i));
		}
		System.out.print("AI ");
		System.out.print(compWarList.get(0));
		System.out.print("###");
		System.out.println(compWarList.get(4));

		System.out.print("Player ");
		System.out.print(playerWarList.get(0));
		System.out.print("###");
		System.out.println(playerWarList.get(4));
		
		compareCardsWar(playerDeck, computerDeck, playerWarList.get(4), compWarList.get(4));
	}
			
	
	public static void compareCardsWar(ArrayList<String> playerDeck, ArrayList<String> computerDeck, String playerCurCard, String compCurCard) {
		// Compare the cards
	    if (getCardValue(playerCurCard) < getCardValue(compCurCard)) {
	        // Player wins, add both cards to the end of their deck
	        for (int i = 0; i < 5; i++) {
	        	computerDeck.add(playerDeck.get(i));
	        	computerDeck.add(computerDeck.get(i));
	        }
	        for (int i = 0; i < 5; i++) {
		        playerDeck.remove(i);
				computerDeck.remove(0);
	        }
	        printNumOfCards(playerDeck, computerDeck);  
	    } 
	    else if (getCardValue(playerCurCard) > getCardValue(compCurCard)) {
	        // Computer wins, add both cards to the end of their deck
	    	for (int i = 0; i < 5; i++) {
	        	playerDeck.add(playerDeck.get(i));
	        	playerDeck.add(computerDeck.get(i));
	        }
	    	for (int i = 0; i < 5; i++) {
		        playerDeck.remove(i);
				computerDeck.remove(0);
	        }
	        printNumOfCards(playerDeck, computerDeck);
	        
	    } else {
	        warMode(playerDeck, computerDeck);
	    }
	}
	
	// A helper method to get the value of a card
	public static int getCardValue(String card) {
	    int value = 0;
	    try {
	        value = Integer.parseInt(card);
	    } catch (NumberFormatException e) {
	        // It's not a number, so it must be a face card or a joker
	        switch (card) {
	            case "Jo":
	                value = 15;
	                break;
	            case "A":
	                value = 14;
	                break;
	            case "J":
	                value = 11;
	                break;
	            case "Q":
	                value = 12;
	                break;
	            case "K":
	                value = 13;
	                break;
	        }
	    }
	    return value;
	}
}
