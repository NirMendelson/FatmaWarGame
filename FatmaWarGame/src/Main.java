import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// warRound have to be 0
		int warRound = 0;
		boolean gameOn = true;
		outerLoop:
			while (gameOn) {
				ArrayList<String> mainDeck = createDeck();
				ArrayList<String> playerDeck = new ArrayList<String>();
				ArrayList<String> computerDeck = new ArrayList<String>();
				dealCards(mainDeck, playerDeck, computerDeck);
				System.out.println("Welcome to Fatma War Game!");
				sc.nextLine();
				printNumOfCards(playerDeck, computerDeck);
				innerLoop:
					while (enoughCards(playerDeck, computerDeck)) {
						if (playQuest() == false) {
							break outerLoop;
						}
						showCards(playerDeck, computerDeck);

						while (areCardsTheSame(playerDeck, computerDeck, warRound)) {
							if (enoughCardsForWar(playerDeck, computerDeck, warRound)) {
								warMode(playerDeck, computerDeck, warRound); 
								warRound = warRound + 4;
							}
							else {
								break innerLoop;
							}
						}
						compareAndAddCards(playerDeck, computerDeck, warRound);
						warRound = 0;
						printNumOfCards(playerDeck, computerDeck);	
					}
				gameOn = playAgain(playerDeck, computerDeck);
			}
	}
	
	
	// creates and returns a shuffled deck of cards
	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<String>();
		String[] values = {"A","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};


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
	
	
	// split the deck between the computer and the player
	public static void dealCards(ArrayList<String> mainDeck, ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		for (int i = 0; i < 27; i++) {
			playerDeck.add(mainDeck.remove(0));
			computerDeck.add(mainDeck.remove(0));
		}
	}
	
	
	// checks if the players have enough cards to continue playing
	public static boolean enoughCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		if (playerDeck.isEmpty()) {
			System.out.println("AI wins");
		} 
		else if (computerDeck.isEmpty()) {
			System.out.println("Player wins");
		}
		else {
			return true;
		}
		return false;
	}
	
	
	// asking the player if he wants to play, if he reply "no" then the computer wins
	public static boolean playQuest() {
		System.out.println("Play? ");
		while (true) {
			String userInput = sc.nextLine().toLowerCase();
			if (userInput.toLowerCase().equals("no")) {
				System.out.println("AI wins! Good bye");
				return false;
			}
			else if (userInput.equals("")) {
				return true;
			}
			else {
				System.out.println("The input is wrong. Please try again.");
			}
		}
	}
	
	
	// show the first card of each player
	public static void showCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		System.out.println("AI " + computerDeck.get(0));
		System.out.println("Player " + playerDeck.get(0));
		sc.nextLine();
	}

	
	// checking if the cards of the player and the computer are equal, works for war and non war
	public static boolean areCardsTheSame(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		if (getCardValue(playerDeck.get(0 + warRound)) == getCardValue(computerDeck.get(0 + warRound))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	// checks if the players have enough cards for "war"- in each war round you need to draw 4 cards
	public static boolean enoughCardsForWar(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		if (playerDeck.size() <= warRound*4+5) {
			System.out.println("Not enough cards");
			System.out.println("AI wins!");
		} 
		else if (computerDeck.size() <= warRound*4+5) {
			System.out.println("Not enough cards");
			System.out.println("Player wins!");
		}
		else {
			return true;
		}
		return false;
	}
	
	
	// in war mode every player need to draw four cards
	public static void warMode(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		System.out.println("War!");
		sc.nextLine();
		System.out.print("AI ");
		System.out.print(computerDeck.get(warRound));
		System.out.print("###");
		System.out.println(computerDeck.get(warRound+4));

		System.out.print("Player ");
		System.out.print(playerDeck.get(warRound));
		System.out.print("###");
		System.out.println(playerDeck.get(warRound+4));
	}

	
	// checks who have the higher card, the one with the highest takes all of the cards that were drawn. works for war and non war
	public static void compareAndAddCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		if (getCardValue(playerDeck.get(0 + warRound)) < getCardValue(computerDeck.get(0 + warRound))) {
			// AI wins, add cards to the end of their deck
			for (int i = 0; i <= 0 + warRound; i++) {
				computerDeck.add(playerDeck.get(i));
				computerDeck.add(computerDeck.get(i));
			}
			// remove the cards that were drawn
			for (int i = 0; i <= 0 + warRound; i++) {
				playerDeck.remove(i);
				computerDeck.remove(i);
			}
		} 
		else {
			// player wins, add cards to the end of their deck
			for (int i = 0; i <= 0 + warRound; i++) {
				playerDeck.add(playerDeck.get(i));
				playerDeck.add(computerDeck.get(i));
			}
			// remove the cards that were drawn
			for (int i = 0; i <= 0 + warRound; i++) {
				playerDeck.remove(i);
				computerDeck.remove(i);
			}
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

		
		// prints how many cards each player has in their decks
	public static void printNumOfCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		System.out.println("AI " + computerDeck.size() + " cards");
		System.out.println("Player " + playerDeck.size() + " cards");
	}


	// asking the player if he wants to play again, if he reply "no" then the game is over
	public static boolean playAgain(ArrayList<String> playerDeck, ArrayList<String> computerDeck) {
		System.out.println("Would you like to start a new game?");
		while (true) {
			String userInput = sc.nextLine().toLowerCase();
			if (userInput.toLowerCase().equals("no")) {
				System.out.println("Good bye");
				return false;
			}
			else if (userInput.equals("")) {
				return true;
			}
			else {
				System.out.println("The input is wrong. Please try again.");
			}
		}
	}
}
