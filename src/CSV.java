import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {
	
	private String fileName; //is the whole location, if it is not in the same folder
	private String splitBy; //what you are going to split this by
	BufferedReader buffRead = null;
	BufferedWriter buffWrite = null;
	
	int numColumns; 
	int numRows;
		
	/**
	 * constructor
	 * @param fileName the location of the file 
	 * @param splitBy what the csv is split by
	 */
	public CSV(String fileName, String splitBy){
		this.fileName = fileName;
		this.splitBy = splitBy;
		this.numColumns = getNumColumns();
		this.numRows = getNumRows();
	}
	/**
	 * other constructor where the default splitBy is ","
	 * @param fileName the location of the file
	 */
	public CSV(String fileName){
		this.fileName = fileName;
		this.splitBy = ",";
		this.numColumns = getNumColumns();
		this.numRows = getNumRows();
	}
	/**
	 * this method is needed for getting just a column
	 * this method gets the number of columns in a csv file
	 * @return the number of columns 
	 */
	public int getNumColumns(){
		int returnInt = 0;
		try{
			buffRead = new BufferedReader(new FileReader(fileName));
			//does not check if null so make sure its not null or add and if to check
			return buffRead.readLine().split(splitBy).length;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffRead != null) {
				try {
					buffRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnInt;
	}
	
	
	/**
	 * this method returns a String[] of the row in a csv file
	 * assumes the row exist *NEED TO TEST THIS
	 * @param row which row to return
	 * @return a String array of what the row contains, returns {""} if nothing is there
	 */
	public String[] readRow(int row){
		String line = ""; //represents the line

		try{
			buffRead = new BufferedReader(new FileReader(fileName));
			int lineNum = 0; //the line number
			while ((line = buffRead.readLine()) != null) {
				if(lineNum == row){ //add an if to avoid going through the whole file and row doesn't exist
					return line.split(splitBy);
				}
				lineNum++; 
			}
		}
		//exception
	 catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (buffRead != null) {
			try {
				buffRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		return line.split(",");
	}
	/**
	 * this method can remove a row from a csv file 
	 * @param row the row that is going to be remove
	 */
	public void removeRow(int row){
		//to edit a row you need to write the whole document all over
		String[][] allRows = getAllRows(); //all the rows and columns
		//clear the rows
		clearAllRows();
		//write everything
		for(int i = 0; i < row; i++){
			addRow(allRows[i]);
			}
		for(int i = row+1; i< allRows.length; i++){
			addRow(allRows[i]);
		}
	}
	/**
	 * this method gets the column from a csv file
	 * @param column the column number (starting with 0)
	 * @return a String[] of what the column contains
	 */
	public String[] readColumn(int column){
		String line = "";
		String returnStringA = "" ; //this will contain all the words in the columns
		//String split = "";
		try{
			buffRead = new BufferedReader(new FileReader(fileName));
			line = buffRead.readLine();
			//format reasons start by adding the first string without ","
			if(line != null){
				returnStringA = line.split(splitBy)[column];
			}
			while ((line = buffRead.readLine()) != null) {
				//have to check if the column exist or else...
				if(column < numColumns){
					returnStringA = returnStringA + "," + line.split(splitBy)[column];
				}
			}
		}
		//exception
	 catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (buffRead != null) {
			try {
				buffRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		//String[] returnString = null;
		return  returnStringA.split(",");	
		}
	
	/**
	 * this method gets the column from a csv file without the first line
	 * @param column the column number (starting with 0)
	 * @return a String[] of what the column contains
	 */
	public String[] readColumn(int column, boolean skip1stLine){
		if(!skip1stLine){
			return readColumn(column);
		}
		String line = "";
		String returnStringA = ""; //this will contain all the words in the columns
		try{
			buffRead = new BufferedReader(new FileReader(fileName));
			//skip the first line
			buffRead.readLine();
			line = buffRead.readLine();
			//format reasons start by adding the first string without ","
			if(line != null){
				returnStringA = line.split(splitBy)[column];
			}
			while ((line = buffRead.readLine()) != null) {
				//have to check if the column exist or else...
				if(column < numColumns){
					returnStringA = returnStringA + "," + line.split(splitBy)[column];
				}
			}
		}
		//exceptions
	 catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (buffRead != null) {
			try {
				buffRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		//test this on an empty string later
		return returnStringA.split(",");	
		}
	/**
	 * this method adds a row to a csv file
	 * @param input the row you want to add to the csv
	 * @throws IOException 
	 */
	public void addRow(String[] input){
		//in order to write put another row the input must have tha same columns as the csv
		if(input.length == numColumns){
			try {
				//use the append mode in FileWriter
				buffWrite = new BufferedWriter(new FileWriter(fileName,true));
				buffWrite.append(String.join(",",input));
				buffWrite.newLine();
				numRows++; //update the number of rows
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if(buffWrite != null){
					try{
						buffWrite.close();
					}
					catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * this method gets all rows in the csv file
	 * this method is necessary for 
	 * @return all rows with their columns 
	 */
	protected String[][] getAllRows(){
		String[][] returnArray = new String[numRows][numColumns];
		for(int i = 0; i < numRows; i++){
			returnArray[i] = readRow(i);
		}
		return returnArray;
		
	}
	/**
	 * this method gets the number of rows in the csv file.
	 * @return an integer of number of rows
	 */
	public int getNumRows(){
		int returnInt = 0; //the line number
		try{
			buffRead = new BufferedReader(new FileReader(fileName));
			while ((buffRead.readLine()) != null) {
				returnInt++;
			}
		}
		//exceptions
	 catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (buffRead != null) {
			try {
				buffRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		return returnInt;
	}
	/**
	 * this method edits a whole row for a csv fle
	 * NOTE: this does not check if row exist
	 * @param input row that will replace previous
	 * @param row which row is it in
	 */
	public void editRows(String[] input, int row){
		//to edit a row you need to write the whole document all over
		String[][] allRows = getAllRows(); //all the rows and columns
		allRows[row] = input; //replace the row
		//clear the rows
		clearAllRows();
		//write everything
		for(int i = 0; i < allRows.length; i++){
			addRow(allRows[i]);
		}
		
	}
	/**
	 * this method edits a column in a row in the csv file
	 * @param input the string that replaces the current one
	 * @param row the row location
	 * @param column the column location
	 */
	public void editColumn(String input, int row, int column){
		String[] rowString = readRow(row); //get the whole row
		rowString[column] = input; //replace the column
		editRows(rowString,row); //put it in the csv file
	}
	
	/**
	 * this method clears all rows in the csv file
	 * this method is necessary for editing rows. 
	 */
	protected void clearAllRows(){
		try {
			//use the append mode in FileWriter
			buffWrite = new BufferedWriter(new FileWriter(fileName));
			buffWrite.write("");
			//buffWrite.append(String.join(",",input));
			numRows = 0; //update the number of rows
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(buffWrite != null){
				try{
					buffWrite.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	

}
