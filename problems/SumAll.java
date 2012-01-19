/**************************************************************
 * 
 * 	SumAll
 * 
 * 		Recursively computes the sum from 1 to n
 * 
 **************************************************************/

package problems;

public class SumAll {

	/**
	 * main
	 * 
	 * 
	 * @param args - args[1] is the number to find the factorial of.
	 */
	public static void main(String[] args) {
		int n = 10;
		if (args.length > 1) {
			try {
				n = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				quit(args[1]);
			}
			if (n < 0) {
				quit(args[1]);
			}
		}
		
		int sum = sumAll(n);
		/*int recSum  = recursiveSumAll(n);
		System.out.println("The sum from 1 to "+ n + " is " + sum + ".");
		if (sum == recSum) {
			System.out.println("Your recursive method answer matches. Huzzah!");
		} else {
			System.out.println("Your recursive method said it is " 
								+ recSum + ". So close.");
		}*/

	}
	
	
	/**
	 * recursiveSumAll
	 * 		recursively computes the sum from 1 to n
	 * 
	 * @param n - the upper limit of the sumation. must be greater than 0.
	 *
	 * @return	the sum from 1 to n
	 */
	/*private static int recursiveSumAll(int n)  {
		// your code goes here
	}*/
	
	
	/**
	 * sumAll
	 * 		computes the sum from 1 to n using the standard equation
	 * 
	 * @param n - the upper limit of the sumation. must be greater than 0.
	 * @return the sum from 1 to n
	 */
	private static int sumAll(int n) {
		return n*(n+1)/2;
	}
	
	
	/**
	 * quit
	 * 		exit the program when user entered an invalid number
	 * 
	 * @param entry - the String the user typed at the command line
	 */
	private static void quit(String entry) {
		System.out.println("Cannot find the sum from 1 to "+ entry + ".");
		System.exit(0);
	}

}
