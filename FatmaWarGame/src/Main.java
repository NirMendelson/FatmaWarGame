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
		int warRound = 1;
		// Deal the card
		for (int i = 0; i < 27; i++) {
			playerDeck.add(mainDeck.remove(0));
			computerDeck.add(mainDeck.remove(0));
		}

		System.out.println(Arrays.toString(playerDeck.toArray()));
		System.out.println(Arrays.toString(computerDeck.toArray()));


		System.out.println("Welcome to Fatma War Game!");
		sc.nextLine();
		printNumOfCards(playerDeck, computerDeck, warRound);
	}

	public static ArrayList<String> createDeck() {
		ArrayList<String> deck = new ArrayList<String>();
				String[] values = {"A","2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

		// for warMode debugging
//		String[] values = {"A","A", "Q", "4", "3", "4", "A", "K", "A", "A", "A", "A", "A"};


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

	public static void showCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		String playerCurCard = playerDeck.get(0);
		String compCurCard = computerDeck.get(0);
		System.out.println("AI " + playerCurCard);
		System.out.println("Player " + compCurCard);
		sc.nextLine();
		compareCards(playerDeck, computerDeck, playerCurCard, compCurCard, warRound);

	}

	public static void compareCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck, String playerCurCard, String compCurCard, int warRound) {
		// Compare the cards
		if (getCardValue(playerCurCard) < getCardValue(compCurCard)) {
			// Player wins, add both cards to the end of their deck
			playerDeck.add(playerCurCard);
			playerDeck.add(compCurCard);
			playerDeck.remove(0);
			computerDeck.remove(0);
			printNumOfCards(playerDeck, computerDeck, warRound);

		} else if (getCardValue(playerCurCard) > getCardValue(compCurCard)) {
			// Computer wins, add both cards to the end of their deck
			computerDeck.add(compCurCard);
			computerDeck.add(playerCurCard);
			playerDeck.remove(0);
			computerDeck.remove(0);
			printNumOfCards(playerDeck, computerDeck, warRound);

		} else {
			warMode(playerDeck, computerDeck, warRound);
		}
	}

	public static void printNumOfCards(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		System.out.println("AI " + computerDeck.size() + " cards");
		System.out.println("Player " + playerDeck.size() + " cards");
		playQuest(playerDeck, computerDeck, warRound);
	}

	public static void playQuest(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		boolean gameOn = true;
		if (playerDeck.size() <= 0 || computerDeck.size() <= 0) {
			gameOn = false;
		}
		while (gameOn == true) {
			System.out.println("Play? ");
			String userInput = sc.nextLine();
			if (userInput.toLowerCase().equals("no")) {
				System.out.println("AI wins! Good bye");
				gameOn = false;
			}
			else if (userInput.equals("")) {
				showCards(playerDeck, computerDeck, warRound);
			}
			else {
				System.out.println("The input is wrong. Please try again");
			}
		}
		if (playerDeck.size() <= 0) {
			System.out.println("AI wins");
			System.out.println("Would you like to start a new game?");
			while (playerDeck.size() > 0 || computerDeck.size() > 0) {
				String userInput = sc.nextLine();
				if (userInput.toLowerCase().equals("no")) {
					System.out.println("AI wins! Good bye");
				}
				else if (userInput.equals("")) {
					createDeck();
				}
				else {
					System.out.println("The input is wrong. Please try again");
				}
			}
		}
		else if (computerDeck.size() <= 0) {
			System.out.println("Player wins");
			System.out.println("Would you like to start a new game?");
			while (playerDeck.size() <= 0 || computerDeck.size() <= 0) {
				String userInput = sc.nextLine();
				if (userInput.toLowerCase().equals("no")) {
					System.out.println("AI wins! Good bye");
				}
				else if (userInput.equals("")) {
					createDeck();
				}
				else {
					System.out.println("The input is wrong. Please try again");
				}
			}
		}
		

	}

	public static void warMode(ArrayList<String> playerDeck, ArrayList<String> computerDeck, int warRound) {
		
		while (computerDeck.size() >= warRound+4 && playerDeck.size() >= warRound+4) {
			System.out.println("warRound =" + warRound);
			System.out.println(Arrays.toString(computerDeck.toArray()));
			System.out.println(Arrays.toString(playerDeck.toArray()));
			System.out.println("War!");
			sc.nextLine();
			System.out.print("AI ");
			System.out.print(computerDeck.get(warRound-1));
			System.out.print("###");
			System.out.println(computerDeck.get(warRound+3));

			System.out.print("Player ");
			System.out.print(playerDeck.get(warRound-1));
			System.out.print("###");
			System.out.println(playerDeck.get(warRound+3));

			warRound = warRound + 4;

			compareCardsWar(playerDeck, computerDeck, playerDeck.get(warRound-1), computerDeck.get(warRound-1), warRound);
		}
		if (playerDeck.size() < warRound+4) {
			System.out.println("Not Enought Cards");
			System.out.println("AI wins!");
			playQuest(playerDeck, computerDeck, warRound);
		}
		if (computerDeck.size() < warRound+4) {
			System.out.println("Not Enought Cards");
			System.out.println("player wins!");
			playQuest(playerDeck, computerDeck, warRound);
		}
	}


	public static void compareCardsWar(ArrayList<String> playerDeck, ArrayList<String> computerDeck, String playerCurCard, String compCurCard, int warRound) {
		// Compare the cards
		if (getCardValue(playerCurCard) < getCardValue(compCurCard)) {
			// Player wins, add both cards to the end of their deck
			for (int i = 0; i < warRound; i++) {
				computerDeck.add(playerDeck.get(i));
				computerDeck.add(computerDeck.get(i));
			}
			for (int i = 0; i < warRound; i++) {
				playerDeck.remove(i);
				computerDeck.remove(i);
			}
			warRound = 1;
			printNumOfCards(playerDeck, computerDeck, warRound);  
		} 
		else if (getCardValue(playerCurCard) > getCardValue(compCurCard)) {
			// Computer wins, add both cards to the end of their deck
			for (int i = 0; i < warRound; i++) {
				playerDeck.add(playerDeck.get(i));
				playerDeck.add(computerDeck.get(i));
			}
			for (int i = 0; i < warRound; i++) {
				playerDeck.remove(i);
				computerDeck.remove(i);
			}
			warRound = 1;
			printNumOfCards(playerDeck, computerDeck, warRound);

		} else {
			warMode(playerDeck, computerDeck, warRound);
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
