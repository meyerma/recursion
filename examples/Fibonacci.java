/**************************************************************
 * 
 * 	Fibonacci
 * 
 * 		Recursively computes the nth fibonacci number
 * 
 * 		!!! Warning !!!
 * 		Horribly inefficient
 * 		Only used to demonstrate how recursion works
 * 
 **************************************************************/

package examples;

public class Fibonacci {

	/**
	 * main
	 * 
	 * 
	 * @param args - args[1] is the number in the fibonacci sequence to compute
	 */
	public static void main(String[] args) {
		int n = 8;
		if (args.length > 1) {
			try {
				n = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				quit(args[1]);
			}
			if (n < 1) {
				quit(args[1]);
			}
		}
		
		int f  = fibonacci(n);
		
		// set the ordinal naming of the number. English is stupid sometimes.
		String ext = "th";
		if (n != 11 && n % 10 == 1) {
			ext = "st";
		} else if (n != 12 && n % 10 == 2) {
			ext = "nd";
		} else if (n != 13 && n % 10 == 3) {
			ext = "rd";
		}
		
		System.out.println("The "+ n + ext + " fibonacci number is " + f + ".");

	}
	
	
	/**
	 * fibonacci
	 * 		recursively computes the nth fibonacci number
	 * 
	 * @param n - the number in the fibonacci sequence to compute. must be greater than 0.
	 *
	 * @return	the nth fibonacci number
	 */
	private static int fibonacci(int n)  {
		if (n == 1 || n == 2) {
			return 1;
		}
		
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	/**
	 * quit
	 * 		exit the program when user entered an invalid number
	 * 
	 * @param entry - the String the user typed at the command line
	 */
	private static void quit(String entry) {
		System.out.println("Cannot find the " + entry + "th fibonacci number.");
		System.exit(0);
	}

}
