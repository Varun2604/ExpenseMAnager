/**
 * @author Varun Iyer
 * @version 1.0
 */
/**
 * Monthly expense manager
 * The application keeps a track of the expenses made by the user, making a note of the date, the item on which expenditure is done and the cost
 * User can enter budget for every month
 * Every time user makes a new entry, the budget is checked, and a message is flashed, if expenses made exceeds the set budget
 * User can enter a date and get all the entries made on that particular date
 * User can get the expenses done from the time, the budget has been reset
 */

/**
 * The program employs five classes
 * The Log class instantiates an object which holds the data and is passed while communicating between different classes
 * The Class CostOperation has methods that do the cost related operations, it communicates only with Class Manager
 * The Class FileOperations has methods which does file related operations, it Communicates only with Class Manager
 * The Class Manager does all the managing part and communicates with the MenuUI Class and all the other classes.
 * The Class MenuUI is the main class, which tells the program which operation to perform and communicates with the user and the Class Manager
 */

/**
 * To perform any operation, the text file "BudgetBook.txt" should be present at the start, or else, error will be thrown
 */

package phase1;