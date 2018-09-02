import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BingoAve {

	//List of bingo cards
	static ArrayList<BingoCard> cards;

	//Random number generator object
	static Random randGen = new Random();

	//List of number picked in a certain bingo game
	static ArrayList<Integer> pickedNumbers = new ArrayList<Integer>();

	public static void main(String[] args) {

		//Scanner for user input 
		Scanner scnr = new Scanner(System.in);

		//Initializes list of bingo cards
		cards = new ArrayList<BingoCard>();

		//Tracks if there is a winner in a certain game
		boolean noWinner = true;

		//The number of iterations in a certain test
		int NUMBER_OF_GAMES_TESTED = 10000;

		//list that tracks the number of picks for each iteration in test
		ArrayList<Integer> picksPerWin = new ArrayList<Integer>();

		//Prompt and storage for user to determine how many people are playing
		System.out.println("How many players are there?");
		int numberOfPlayers = scnr.nextInt();

		//Creates correct number of bingo cards based on users input
		for(int i = 0; i < numberOfPlayers; ++i) {
			cards.add(i, new BingoCard());
		}

		//Prompt telling user test is running
		System.out.println("Testing through " + NUMBER_OF_GAMES_TESTED + " iterations of games to find\nthe average number of picks before somebody wins...");

		//Loop to run a game over and over to collect data to create average
		for(int q = 0; q < NUMBER_OF_GAMES_TESTED; ++q){

			//Resets the cards of each player in each test
			for(int i = 0; i < numberOfPlayers; ++i) {
				cards.set(i, new BingoCard());
			}

			//Resets the picked numbers list to hold nothing
			int numPickedLast = pickedNumbers.size();
			for(int c = 0; c < numPickedLast; ++c){
				pickedNumbers.remove(0);
			}

			//Resets the number of picks to win a game to zero
			int picksToWin = 0;

			//Tracks if there was a winner in the currect round
			noWinner = true;

			//loop that runs while there isn't a winner
			while (noWinner) {

				//Picks next number and checks that number for each card
				pickNextNumber();

				//Updates the picks to win in the current round
				picksToWin += 1;

				//Checks each card to see if there was a winner
				for(int k = 0; k < cards.size(); ++k) {
					noWinner = cards.get(k).checkWinner();
					if(!noWinner){
						break;
					}
				}
			}
			//Add the number of picks for the last round of the list keeping track of the number of picks to win
			picksPerWin.add(picksToWin);
		}

		//Calculates the total amount of picks to find average
		double totalAdded = 0;
		for(int t = 0; t < picksPerWin.size(); ++t){
			totalAdded += picksPerWin.get(t);
		}

		//Prints out the average number of picks for that amount of people playing
		System.out.println("The average number of numbers picked before somebody\nwins with " +
				numberOfPlayers + " people playing is " + (totalAdded/picksPerWin.size()));

		picksPerWin = orderLeastToGreatest(picksPerWin);

		//System.out.println(picksPerWin.toString());

		System.out.println("The amount of times each number of picks created a win\nfor " + numberOfPlayers + " number of players is in the list as follows: ");
		printNumberOfEachPick(picksPerWin);
	}

	public static void pickNextNumber(){

		//Used to track if the number picked was picked before or not
		boolean notNewNum = true;

		//Used to track which number was randomly picked
		int numberPicked = 0;

		//Loop runs while pick is not a new pick
		while(notNewNum){

			//Picks a random number in the bingo range
			numberPicked = randGen.nextInt(75) +1;

			//Resets new number check to false
			notNewNum = false;

			//Checks new picked number with previously picked to see if it's new or not
			for(int p = 0; p < pickedNumbers.size(); ++p){
				if(numberPicked == pickedNumbers.get(p)) {
					notNewNum = true;
					break;
				}
			}
		}

		//Adds new picked number to arraylist checking new numbers
		pickedNumbers.add(numberPicked);

		//Uses the new picked number and checks each board to see if it was one it
		for(int i = 0; i < cards.size(); ++i){
			cards.get(i).checkCalledNumber(numberPicked);
		}

	}

	public static ArrayList<Integer> orderLeastToGreatest(ArrayList<Integer> allPicks){

		ArrayList<Integer> allPicksRemade = new ArrayList<Integer>();

		allPicksRemade = allPicks;

		int temp;
		boolean notDone = true;

		while(notDone){
			int changesMade = 0;

			for(int i = 0; i < allPicksRemade.size() - 1; ++i){
				if(allPicksRemade.get(i) > allPicksRemade.get(i+1)){
					temp = allPicksRemade.get(i);
					allPicksRemade.set(i, allPicksRemade.get(i+1));
					allPicksRemade.set(i+1, temp);
					changesMade += 1;
				}
			}

			if(changesMade == 0){
				notDone = false;
			}
		}


		return allPicksRemade;
	}

	public static void printNumberOfEachPick(ArrayList<Integer> winningPickInOrder){

		int number;

		for(int i = 0; i < winningPickInOrder.size(); ++i){
			number = 0;
			for(int j = 0; j < winningPickInOrder.size(); ++j){
				if(winningPickInOrder.get(j) == i){
					number += 1;
				}
			}
			if(number != 0){
				System.out.println(i + " picks to win: " + number);
			}
		}


	}

}
