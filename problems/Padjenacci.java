/**************************************************************
 * 
 * 	Padjenacci
 * 
 * 		Recursively computes the nth padjenacci number
 * 
 * 		The padjenacci numbers are defined as:
 * 
 * 			0 < n < 4 : p(n) = n
 * 			n >= 4    : p(n) = p(n-1) * p(n-2) - p(n-3)
 * 
 * 		This is the padjenacci sequence:
 * 
 * 			1: 1
 * 			2: 2
 * 			3: 3
 * 			4: 5
 * 			5: 13
 * 			6: 62
 * 			7: 801
 * 			8: 49649
 * 			9: 39768787
 * 			...
 * 
 **************************************************************/

package problems;

public class Padjenacci {

	/**
	 * main
	 * 
	 * 
	 * @param args - args[1] is the number in the fibonacci sequence to compute
	 */
	public static void main(String[] args) {
		int n = 21;
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
		
		/*int p  = padjenacci(n);
		
		// set the ordinal naming of the number. English is stupid most of the time.
		String ext = "th";
		if (n != 11 && n % 10 == 1) {
			ext = "st";
		} else if (n != 12 && n % 10 == 2) {
			ext = "nd";
		} else if (n != 13 && n % 10 == 3) {
			ext = "rd";
		}
		
		System.out.println("The "+ n + ext + " padjenacci number is " + p + ".");
*/
	}
	
	
	/**
	 *	padjenacci
	 * 		recursively computes the nth padjenacci number
	 * 
	 * @param n - the number in the padjenacci sequence to compute. must be greater than 0.
	 *
	 * @return	the nth padjenacci number
	 */
	/*private static int padjenacci(int n)  {
		// your code goes here
	}
	/**
	 * quit
	 * 		exit the program when user entered an invalid number
	 * 
	 * @param entry - the String the user typed at the command line
	 */
	private static void quit(String entry) {
		System.out.println("Cannot find the " + entry + "th padjenacci number.");
		System.exit(0);
	}

}
