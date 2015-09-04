import java.util.Scanner;

/*
 * Question 3:
 * Write a program to check whether a string is a palindrome (a string that's 
 * the same forward and backwards, for eg, madamimadam)
 * Author: Lathian (Jonathan) Kwiat
 * Fall 2015 CISC 3150
 */

public class Q3_Palindrome {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Below so whitespace can be considered part of the palindrome
		input.useDelimiter("\n");
		String str = null;
		do {
			System.out.print("Please enter a String Sequence or hit Enter to end: ");
			str = input.next();
			if(isPalindrome(str)) {
				System.out.println("Gee wilikers! That's a Palindrome!");
			}
			else
				System.out.println("Sadly what you entered is NOT a Palindrome.");
		} while (str.length()!=0);
		
		System.out.println("Exited the loop");
		input.close();

	}
public static boolean isPalindrome(String s) {
	if (s.length() == 1) {
		return true;
	}
	// Integer division comes up with the right stop point in both odd and
	// even String cases.
	int stopComparing = (s.length()/2);
	for (int i = 0; i < stopComparing; i++)
	{
		if (s.charAt(i)!=s.charAt(s.length()-1-i)){
			return false;
		}
	}
	return true;
}
}
