package phase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Varun Iyer
 * @version 1.1
 * Deals with all the File related operation
 */
public class FileOperations {
	
	File logBookFile = new File("LogBook.txt");
	File budgetFile = new File("BudgetBook.txt");
	Scanner input = new Scanner(System.in);
	
	public String readBudgetBook() throws IOException {
		/**
		 * Method used by enterData, to read the budget and the expense
		 */
		// BufferedReader to read the budget and the expense from the "Budget.txt" file
		FileReader read = new FileReader(budgetFile);
		BufferedReader rd = new BufferedReader(read);
		String budgetBookData = rd.readLine();
		rd.close();
		return budgetBookData;
	}

	public void updateLogBook(String date, String item, int cost) throws IOException {
		/**
		 * Method used by enterData, to update "LogBook.txt" with the input given by the user
		 */
		// BufferedWriter , to append the data in the "Data.txt" file
		FileWriter writer = new FileWriter(logBookFile,true); 
		BufferedWriter br = new BufferedWriter(writer);
		br.append(date + "\t" + item + "\t" + cost );
		br.newLine();
		br.close();
	}

	public void updateBudgetBook(int budget, int expense, int cost) throws IOException {
		/**
		 * Method used by enterData, to update "BudgetBook.txt" with the new expense
		 */
		FileWriter writer = new FileWriter(budgetFile);
		BufferedWriter  br = new BufferedWriter(writer);
		br.write(budget + " " + (expense + cost));
		br.close();
	}

	public void updateBudgetBook(int budget) throws IOException {
		/**
		 * Method to set new new budget taken from the user, in "BudgetBook.txt"
		 * Sets the expense to zero
		 * Method calls the readBudget method from FileOperaations, and gives the user savings for the particular period
		 */
		char ch = 'n';
		String temp = readBudgetBook();
		int[] arr  = new int[2];
		StringTokenizer st = new StringTokenizer(temp);			//StringTokenizer, used to get the total savings done, since the last budget update
		int i = 0; 
		while(st.hasMoreTokens())
		{
			arr[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		if(arr[1] == 0){
			//If no expense has been made since the budget is set, if and else will make sure that the user is notified that the budget is already reset
			//if is executed if no expenses has been made after the budget has been reset
			System.out.print("\nNo Expenses has been made since the budget is reset.\nDo you still want to reset the budget?(y/n): ");
			ch = input.next().charAt(0);
			}
		else{
			//else is executed, when changes have been made after setting the budget
			ch = 'y';
			System.out.print("\nYou have reset the budget!");
			System.out.print("\nThe Savings since the last budget update is Rs.: " + (arr[0] - arr[1]));
		}
		if(ch == 'y' || ch == 'Y'){
		FileWriter writer = new FileWriter(budgetFile);
		BufferedWriter  br = new BufferedWriter(writer);
		br.write(budget + " " + 0);
		br.close();	
		}
	}
	
	public ArrayList<Log> checkDate( String date) throws IOException
	{
		/**
		 * Method to check if the given date exists in the file
		 * if the date exists, then the details are wrapped in Data object, and returned to the manager
		 * else, null is returned
		 * 
		 * */
		FileReader read = new FileReader(logBookFile);
		BufferedReader rd = new BufferedReader(read);
		String temp = new String();
		// The data object is added to an array list
		ArrayList<Log> list1 = new ArrayList<Log>();
		while((temp = rd.readLine()) != null ){
			Log log = new Log();
			StringTokenizer st = new StringTokenizer(temp, "\t");
			String[] str = new String[5];
			int i = 0;
			while(st.hasMoreTokens()){
				// Store all the tokens of a particular line in str[]
				str[i] = st.nextToken();
				i++;
			}
			
			if(str[0].toString().equals(date))
			{
				// in case the date give by the user matches with the date in the file, the details are wrapped in log, and returned to manager
				log.setDate(str[0]);
				log.setItem(str[1]);
				log.setCost(Integer.parseInt(str[2]));
				list1.add(log);
			}
		}
		rd.close();
		
		return list1;	
	}
	
	public void resetLogBook() throws IOException {
		/**
		 * Method used reset the Log Book
		 */
		//The file LogBook.txt is checked for entries, If any entry is present, then it is erased, else only a message telling that there is no record to be deleted is flashed.
		FileReader read = new FileReader(logBookFile);
		BufferedReader rd = new BufferedReader(read);
		if( rd.readLine() != null){
			// BufferedWriter , to reset the log in the "LogBook.txt" file
			FileWriter writer = new FileWriter(logBookFile); 
			BufferedWriter br = new BufferedWriter(writer);
			br.close();
			System.out.print("\nAll previous records have been erased!");

		}
		else
			System.out.print("\nThere are no previous records to be erased!");
		rd.close();
		
	}

}
