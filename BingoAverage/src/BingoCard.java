import java.util.Random;
import java.util.Arrays;

public class BingoCard {

	//Creates arrays for each bingo column with size of 5
	private int[] bColumn = new int[5];
	private int[] iColumn = new int[5];
	private int[] nColumn = new int[5];
	private int[] gColumn = new int[5];
	private int[] oColumn = new int[5];
	
	//Random number generator object
	private Random randGen = new Random();
	
	//Sets the column size of each letter to five
	private final int COLUMN_SIZE = 5;
	
	
	public BingoCard() {

		//Runs a loop for each position in the b column
		for(int i = 0; i < COLUMN_SIZE; ++i) {

			//Sets a random number 1-15 for each position in b column
			bColumn[i] = randGen.nextInt(15) + 1;

			//If number repeats redo that position
			for(int j = 1; j < bColumn.length; ++j) {
				if(i-j>=0)
					if(i>0)
						if(bColumn[i] == bColumn[i-j])
							--i;
			}
		}

		//Runs a loop for each position in the i column
		for(int i = 0; i < COLUMN_SIZE; ++i) {

			//Sets a random number 16-30 for each position in the i column
			iColumn[i] = randGen.nextInt(15) + 16;

			//If number repeats redo that position
			for(int j = 1; j < iColumn.length; ++j) {
				if(i-j>=0)
					if(i>0)
						if(iColumn[i] == iColumn[i-j])
							--i;
			}
		}
		
		//Sets a loop for each position in the n column
		for(int i = 0; i < COLUMN_SIZE; ++i) {

			//Set a random number 31-45 for each position in the n column
			nColumn[i] = randGen.nextInt(15) + 31;

			//If number repeats redo that position
			for(int j = 1; j < nColumn.length; ++j) {
				if(i-j>=0)
					if(i>0)
						if(nColumn[i] == nColumn[i-j])
							--i;
			}
		}

		//Sets a loop for each position in the g column
		for(int i = 0; i < COLUMN_SIZE; ++i) {

			//Set a random number 46-60 for each position in the g column
			gColumn[i] = randGen.nextInt(15) + 46;

			//If number repeats redo that position
			for(int j = 1; j < gColumn.length; ++j) {
				if(i-j>=0)
					if(i>0)
						if(gColumn[i] == gColumn[i-j])
							--i;
			}
		}

		//Sets a loop for each position in the o column
		for(int i = 0; i < COLUMN_SIZE; ++i) {

			//Set a random number 61-75 for each position in the o column
			oColumn[i] = randGen.nextInt(15) + 61;

			//If number repeats redo that position 
			for(int j = 1; j < oColumn.length; ++j) {
				if(i-j>=0)
					if(i>0)
						if(oColumn[i] == oColumn[i-j])
							--i;
			}
		}
		
		//Sets middle square as a free space
		nColumn[2] = 0;
		
		
		return;
	}
	
	public void checkCalledNumber(int calledNum){
		
		//Loop that checks each space on the board
		//And changes the number position that is the same
		//to zero which symbolizes it has been a match
		for(int i = 0; i < COLUMN_SIZE; ++i) {
			if(bColumn[i] == calledNum){
				bColumn[i] = 0;
			}
			if(iColumn[i] == calledNum){
				iColumn[i] = 0;
			}
			if(nColumn[i] == calledNum){
				nColumn[i] = 0;
			}
			if(gColumn[i] == calledNum){
				gColumn[i] = 0;
			}
			if(oColumn[i] == calledNum){
				oColumn[i] = 0;
			}
		}
		
	}
	
	public boolean checkWinner(){
		
		//Checks to see if there is a match across a row
		for(int i = 0; i < COLUMN_SIZE; ++i){
			if(bColumn[i] == iColumn[i] && bColumn[i] == nColumn[i] && bColumn[i] == gColumn[i] && bColumn[i] == oColumn[i]){
				return false;
			}
		}
		
		//Checks the columns as a whole to see if they totally match for a win
		if(bColumn[0] == bColumn[1] && bColumn[0] == bColumn[2] && bColumn[0] == bColumn[3] && bColumn[0] == bColumn[4]){
			return false;
		}
		
		if(iColumn[0] == iColumn[1] && iColumn[0] == iColumn[2] && iColumn[0] == iColumn[3] && iColumn[0] == iColumn[4]){
			return false;
		}
		
		if(nColumn[0] == nColumn[1] && nColumn[0] == nColumn[2] && nColumn[0] == nColumn[3] && nColumn[0] == nColumn[4]){
			return false;
		}
		
		if(gColumn[0] == gColumn[1] && gColumn[0] == gColumn[2] && gColumn[0] == gColumn[3] && gColumn[0] == gColumn[4]){
			return false;
		}
		
		if(oColumn[0] == oColumn[1] && oColumn[0] == oColumn[2] && oColumn[0] == oColumn[3] && oColumn[0] == oColumn[4]){
			return false;
		}
		
		//Checks the diagonals to see if they are matching for a win
		if(bColumn[0] == 0 && iColumn[1] == 0 && nColumn[2] == 0 && gColumn[3] == 0 && oColumn[4] == 0) {
			return false;
		}
		
		if(bColumn[4] == 0 && iColumn[3] == 0 && iColumn[2] == 0 && gColumn[1] == 0 && oColumn[0] == 0) {
			return false;
		}
			
		return true;
	}

	//Used  in debugging
	public String toString() {
		return "B: " + Arrays.toString(bColumn) + "\nI: " + Arrays.toString(iColumn) + 
				"\nN: " + Arrays.toString(nColumn) + "\nG: " + Arrays.toString(gColumn) +
				"\nO: " + Arrays.toString(oColumn);
	}
}
