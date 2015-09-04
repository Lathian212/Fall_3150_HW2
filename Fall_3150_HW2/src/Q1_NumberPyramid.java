/*
 * Author: Lathian (Jonathan) Kwiat
 * Course: CISC 3150 Fall 
Question 1:
Write a program that asks the user how tall of a pyramid do they want to 
display on the screen, and then displays the pyramid that counts up on the 
left, and counts down on the right. For eg, if the user enters 4, the 
program shows:

        1
      1 2 1
    1 2 3 2 1
  1 2 3 4 3 2 1
*/
import java.util.Scanner;
import java.util.InputMismatchException;

public class Q1_NumberPyramid {
	public static void main (String [] args) {
		Scanner input = new Scanner(System.in);
		int pyHeight = 0;
		System.out.print("Please enter an integer from 1 or above for the" +
				"the height of a pyramid: ");
		// Unless you are taking in a String and don't care what it is
		// a scanner method invocation should be surrounded with a try catch block.
		do {
			try {
				pyHeight = input.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("You just entered an illegal value.");
				System.out.print("Please enter an integer >= 1 or ctrl-D" +
						" to exit the program: ");
				input.next();
				continue;
			}
			if (pyHeight < 1) {
				System.out.print(" You entered and integer less then 1." +
						"Please enter an integer >= 1.");
			}
		} while (pyHeight < 1);
		printPyramid(pyHeight);
		input.close();
		return;
	}
	/* This is the print function for the Pyramid. The center line number is
	 * the level of the pyramid from 1 to the height of the pyramid. Each step
	 * away from the center line is 1 minus that midline value a zero value is
	 * reached and then no numbers are printed just whitespace.
	 */
	private static void printPyramid(int pyHeight) {
		int distFromMidline = 0;
		int pyVal = 0;
		int heightNumDigits = numDigits (pyHeight);
		// whitespace padding for pyramid.
		int padAmount = 0;
		
		for ( int i =1; i <= pyHeight; i++) {
			// One padding space for each level of the pyramid so bottom layer of pyramids
			// left most number is not up against the edge of the screen.
			System.out.print(" ");
			// 2 * pyHeight the pyramid needs to fall away to 1 on both left and right side
			// of midline.
			for (int j =1; j <= 2 * pyHeight; j++) {
				System.out.print(" ");
				distFromMidline = Math.abs(pyHeight-j);
				pyVal = i - distFromMidline;
				if ( pyVal > 0 ) {
					System.out.print(pyVal);
					// Depending on how many digits just printed padding needs
					// to be adjusted.
					padAmount = Math.abs(heightNumDigits 
							- numDigits(pyVal));
					for (int l =0; l < padAmount; l++) {
						System.out.print(" ");
					}
					
				}
				else {
					// Extra padding comes in if the user enters
					// a two digit number or more.
					padAmount = heightNumDigits;
					for (int k =0; k < padAmount; k++) {
						System.out.print(" ");
					}	
				}
			}
			System.out.println();
		}
	}
	/* This is a helper function for printPyramid which determines the number
	 * of digits in a number so that the whitespace padding can be set
	 * to keep the pyramid symetrical. 
	 */
	private static int numDigits (int num){
		// I discovered a bug in my original code. If someone enters a Height
		// greater then one digit the padding is off. Below discovers how
		// many digits are in the pyHeight value to set the padding.
		int numDigits = 0 ;
		int powOfTen = 1;
		while (true) {
			// Math.pow returns a double!
			powOfTen = (int) Math.pow( 10, numDigits) ;
			if ( (num / powOfTen ) == 0) {
				return numDigits;
			}
			else {
				numDigits++;
			}
		}

		
	}
}
