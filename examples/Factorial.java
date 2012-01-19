/**************************************************************
 * 
 * 	Factorial
 * 
 * 		Recursively computes the factorial of n
 * 
 **************************************************************/

package examples;

public class Factorial {

	/**
	 * main
	 * 
	 * 
	 * @param args - args[1] is the number to find the factorial of.
	 */
	public static void main(String[] args) {
		int n = 0;
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
		
		int f  = factorial(n);
		System.out.println("The factorial of "+ n + " is " + f + ".");

	}
	
	
	/**
	 * factorial
	 * 		recursively computes the factorial of n
	 * 
	 * @param n - the number to find the factorial of. must be non-negative.
	 *
	 * @return	the factorial of n
	 */
	private static int factorial(int n)  {
		// base case
		if (n <= 1) {
			return 1;
		}
		
		// reduction
		return n*factorial(n-1);
	}
	
	public static int factorial(int n) {
		int fact = 1;
		
		for (int i=2;i<=n;i++) {
			fact *= i;
		}

		return fact;
	}
	
	/**
	 * quit
	 * 		exit the program when user entered an invalid number
	 * 
	 * @param entry - the String the user typed at the command line
	 */
	private static void quit(String entry) {
		System.out.println("Cannot find the factorial of "+ entry + ".");
		System.exit(0);
	}

}
