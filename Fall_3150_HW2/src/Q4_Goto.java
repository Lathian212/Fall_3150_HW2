/* Author: Lathian (Jonathan) Kwiat
 * Question 4:
 * Write a program that breaks out of nested loops using labels.
 * 
 * Oracle has a Java Tutorial on this:
 * Branching Statements: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html
 */
public class Q4_Goto {
	public static void main(String [] args){
		TripleHell:
			while (true) {
				while (true) {
					while (true) {
						break TripleHell; 
					}
				}
			}
		System.out.println("I escaped Infinite-Hell^3!");
	}

}
