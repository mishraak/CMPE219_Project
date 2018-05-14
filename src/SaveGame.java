import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame extends CSV {

	// list of the names in csv file
	// might add more things as the game gets more complicated
	static final int name = 0;
	static final int maxScores = 1;
	static final int crashes = 2;

	static SaveGame s = new SaveGame();

	public SaveGame() {
		// file needs to be in the same folder or give the path of the file
		super("saveGame.csv");
	}

	/**
	 * this method gets all the score NOTE will return error if score is not a
	 * int
	 * 
	 * @return a int array of all score
	 */
	public static int[] getAllScores() {
		String[] score = s.readColumn(maxScores, true);
		int[] returnArray = new int[score.length];
		// might move this block into its own method if more things are ints or
		// might make another method in the super class
		for (int i = 0; i < score.length; i++) {
			returnArray[i] = Integer.parseInt(score[i]);
		}
		return returnArray;
	}

	/**
	 * this method gets all names in the saveGame.csv
	 * 
	 * @return string array of all names
	 */
	public static String[] getAllNames() {
		return s.readColumn(name, true);
	}

	/**
	 * get the 'Crashes' column
	 * 
	 * @return a string array of crashes
	 */
	public static String[] getAllCrashes() {
		return s.readColumn(crashes, true);
	}


	public static void setName(String newName, int row) {
		s.editColumn(newName, row, name);
	}


	public static void setScore(int newScore, int row) {
		s.editColumn("" + newScore, row, maxScores);
	}


	public static void setCrashes(int newCrashes, int row) {
		s.editColumn("" + newCrashes, row, crashes);
	}


	public static void addNewRow(String[] input) {
		s.addRow(input);
	}


	public static float getAverage() {
		int[] scores = s.getAllScores();
		float sum = 0;
		for (int i = 0; i < scores.length; i++) {
			sum = sum + scores[i];
		}
		return sum / scores.length;
	}


	public static void addSorted(String[] newRow) {
		// this method will insert new row but sorted by highest score first
		// in order to write put another row the input must have the same
		// columns as the csv
		String[][] allRows = s.getAllRows();
		s.clearAllRows();
		boolean change = false;

		s.addRow(allRows[0]); //add the first line
		
		for(int i = 1; i < allRows.length; i++){
			if(!change && Integer.parseInt(allRows[i][s.maxScores]) <= Integer.parseInt(newRow[maxScores])){
				change = true; //make it escape the infinite loop	
				s.addRow(newRow); //add the new row
				i--; //so it can write all rows
			}
			else{
				s.addRow(allRows[i]);
			}
		}
		

	}

	/**
	 * this method edits the score in a row and sorts it.
	 * @param score new score (in a game this should only change when the score is higher)
	 * @param row the location of the row that is going to be edited. 
	 */
	public static void setScoreSorted(int score, int row){
		String[] newRow = s.readRow(row); //get row that is going to change
		newRow[maxScores] = "" + score; //change the score
		s.removeRow(row); //remove the row
		s.addSorted(newRow);
		
	}
	/**
	 * this method gets the player info in a String[]
	 * @param player String of the player name in the CSV file
	 * @return a String[] with the player info
	 */
	public static String[] getPlayerInfo(String player){
		//first get all names and compare
		String[] names = s.readColumn(name);
		for(int i = 0; i < names.length; i++){
			if(names[i].toLowerCase().equals(player.toLowerCase())){
				return s.readRow(i);
			}
		}
		//if it is done with the loop then it didnt find it so...
		String[] newName = {player,"0","0"};
		s.addNewRow(newName);
		return newName;
	}
	public static int getPlayerRow(String player){
		String[] names = s.readColumn(name);
		for(int i = 0; i < names.length; i++){
			if(names[i].toLowerCase().equals(player.toLowerCase())){
				return i;
			}
		}
		//if player doesnt exist then add another one (i dont know if you want to keep this)
		String[] newName = {player,"0","0"};
		s.addNewRow(newName);
		return s.numRows-1;
	}
	// testing purpose
	public static void main(String args[]) {
		String[] player21 = s.getPlayerInfo("player21");
		System.out.println(s.getPlayerRow("Player41"));
		System.out.println();

		
		String[] player12 = s.getPlayerInfo("player12");
		for(int i = 0; i < player12.length; i++){
			System.out.println(player12[i]);
		}

	}

}
