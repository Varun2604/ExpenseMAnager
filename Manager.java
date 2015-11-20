
/**
 * @author Varun Iyer
 * @version 1.1
 * @param month,year - to open the file holding the data of the present month and year
 * The class opens the file and does all the file manipulation work*/

package phase1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	
	Scanner input = new Scanner(System.in);
	// CostOperations class defines all methods relating to expense and budget
	CostOperations cost = new CostOperations();
	// FileOperations class defines all methods relating to file operations like reading and writing
	FileOperations file = new FileOperations();
	
	public void makeDailyLog() throws IOException
	{
		/**
		 * Method is called by the MenuUI, to get the entry
		 * Asks the user for the date, item, and cost for a single item
		 * calls the enterLog, to make an entry in the Log Book
		 */
		Log log = new Log();
		System.out.print("\nEnter the Date (dd/mm/yyyy): ");
		log.setDate(input.next());
		System.out.print("\nEnter the item: ");
		log.setItem(input.next());
		System.out.print("\nEnter Cost: Rs");
		log.setCost(input.nextInt());
		enterLog(log);
	}
	
	public void displayExpense() throws IOException {
		/**
		 * Method to display the expense done till date.
		 */
		String budgetBookdata = file.readBudgetBook();
		// get the expense incurred till date from the "Budget.txt" file
		int expense = cost.getExpense(budgetBookdata);
		int budget = cost.getBudget(budgetBookdata);
		System.out.print("\nThe expense done till date is: Rs." + expense);
		System.out.print("\nThe amount that can yet be spent is: Rs." + (budget - expense));
	}

	public void getExpenseDetails() throws IOException{
		/**
		 * Method to get the date on which the expense is to be shown to the user
		 * Calls the getDetails method, to get the details from the Log Book
		 */
		System.out.print("\nEnter the Date (dd/mm/yyyy): ");
		String date = input.next();
		getDetails(date);
	}
	
	public void setBudget() throws IOException {
		/**
		 * Method to get the budget from the user, and re write the "BudgetFile.txt"
		 * Warns the user that setting budget would set the expense to zero
		 */
		System.out.print("\nResetting the Budget for the month will reset your expense as well!\nDo you want to continue? (y/n): ");
		char ch = input.next().charAt(0);
		if(ch == 'y' || ch == 'Y'){
			// if the condition is true, the budget is taken from the user, and updated in the "Budget.txt"
			System.out.print("\nEnter the budget: Rs.");
			int budget = input.nextInt();
			file.updateBudgetBook(budget);
		}
		ch = 'n';
		System.out.print("\nDo you want to erase the previous records?(y/n) :");
		ch = input.next().charAt(0);
		if(ch == 'y' || ch == 'Y')
			file.resetLogBook();
	}
	
	private void enterLog(Log log) throws IOException{
		/** 
		 * Method appends the data that is given to it by the MEMClass
		 * The set budget set in the "Budget.txt" file, is checked, and if the budget exceeds, a waring message is flashed
		 * The budget and the expense is updated
		 */
		String budgetBookdata = file.readBudgetBook();
		// get the budget set for the month from the "Budget.txt" file
		int budget = cost.getBudget(budgetBookdata);
		// get the expense incurred till date from the "Budget.txt" file
		int expense = cost.getExpense(budgetBookdata);
		// Checking if the expense has exceeded the budget set, if yes, displaying the message
		if(cost.budgetCheck(budget, expense, log.getCost()))
			System.out.println("\n!!!Your monthly budget has exceeded, Please take care !!!");
		//Appending the file with new data
		file.updateLogBook(log.getDate(), log.getItem(), log.getCost());
		//Updating the "Budget.txt" file
		file.updateBudgetBook(budget, expense, log.getCost());
	}

	private void getDetails(String date) throws IOException {
		/**
		 * Method accepts the date from the user, and Compares the date with all the dates in the file
		 * @return the details if the date is present, or an exception if the date is not present 
		 */
		ArrayList<Log> list1 = file.checkDate(date);
		int n = list1.size();
		if(n == 0)
			System.out.print("No entry has been made for the date entered! Please Check the date");
		else{
			System.out.println("\nThe details of the expense done on " + date + " :");
			for(int i = 0; i < n; i++){
				Log log = list1.get(i);
				System.out.println((i+1) + " : " + "\nExpense Item: " + log.getItem() + "\nExpenditure: Rs." + log.getCost());
				}
			}
			
	}
	
}
