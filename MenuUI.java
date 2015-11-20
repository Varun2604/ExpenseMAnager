package phase1;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Varun Iyer
 * @version 1.1
 * Main class accesses the Data class, Cost Manager, and the FileManager
 * @param file - an object of FileManager, which deals with all the file managing operations.
 * 
 * */
public class MenuUI {

	public static void main(String[] args) throws IOException {
		
		Scanner input = new Scanner(System.in);
		int ch;
		char userChoice = 'N';
		do{
		System.out.print("\n Options available are: \n1. Make an Entry \n2. Check Expense \n3. Get the expense for a particular date \n4. Set budget \n Enter your choice: ");
		ch = input.nextInt();
		Manager manage = new Manager();
		switch(ch)
		{
		case 1:
			//getting the data from the user, and updating 
			manage.makeDailyLog();
			break;
		case 2: 
			// Display the expense done till date to the user
			manage.displayExpense();
			break;
		case 3:
			//Get the expense details for a particular date
			manage.getExpenseDetails();
			break;
		case 4:
			// Set the budget for the month
			manage.setBudget();
		}
		System.out.print("\nWant to make more Entries/Checkings?(y/n): ");
		userChoice  = input.next().charAt(0);
		}while(userChoice == 'y' || userChoice == 'Y' );
		input.close();
	}

}
