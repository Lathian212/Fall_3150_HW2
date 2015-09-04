/* Author: Lathian (Jonathan) Kwiat
 * Course: 3150
 * Question 2:
 * Write a program that displays a calendar on the screen. It asks the user 
 * the year, what day the first of January fell on, then displays every month 
 * from January to December. Make sure to get the leap years right. Your 
 * months should resemble the following format:
 *
 *  February 2015      
 *Su Mo Tu We Th Fr Sa  
 *1  2  3  4  5  6  7  
 *8  9 10 11 12 13 14  
 *15 16 17 18 19 20 21  
 *22 23 24 25 26 27 28 
 *
 *http://docs.oracle.com/javase/8/docs/api/java/time/package-frame.html
 *http://docs.oracle.com/javase/8/docs/api/java/util/GregorianCalendar.html
 *
 *
*/

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;

public class Q2_Calendar  {
	public static void main (String [] args) {
		// Create objects of the enum types of DayOfWeek and Month
		Month [] months = Month.values();
		DayOfWeek startDay = null;
		
		
		Scanner input = new Scanner(System.in);
		boolean correctInput = false;
		int year = 0; 
		String day = null;
		
		do {
			System.out.print("Please enter the year: ");
			try {
				year = input.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("You just entered an illegal value for+ "
						+ "the year or ctrl-z to exit.");
				input.next();
				continue;
			}
			correctInput = true;
		} while (!correctInput);
			
		do {
			System.out.print("Please enter the day of the week the first" +
					" day of January falls upon for that year: ");
			// Note a try block is not appropriate here because .next() will
			// accept any characters instead using this string the program
			// will attempt to find the start day using the DayOfWeek enums
				day = input.next();
				// Below to compare to constant enum value
				day = day.toUpperCase();
				try {
					startDay = DayOfWeek.valueOf(day);
				}
				catch (IllegalArgumentException e) {
					System.out.println("You have entered something that is" +
						" not recognized as a day of the week.\n Please check" +
						" your spelling and spell the day of the week" +
						" completely not using any abbreviations...\n");
					continue;

				}
				break;	
		} while (true);
		
		// System.out.println("The year is: " + year);
		// System.out.println(startDay);
		for (Month monthsToPrint : months) {
			startDay = printCalendar (monthsToPrint, startDay, year);
		}
		input.close();
		return;
	}
	/* This is a private helper function that determines if the current year
	 * is a leap year and so Feb. gets 29 days. Note this is not the Julian
	 * rules but the Gregorian ones and so will not be accurate prior to prior
	 * to around 1550 because then the Julian calendar was in effect.
	 * The algorithm for this can be found at:
	 * http://en.wikipedia.org/wiki/Leap_year#Algorithm
	 */
	private static boolean isLeapYear (int year) {
		if ( year % 4 != 0) {
			return false;
		}
		else if ( year % 100 != 0) {
			return true;
		}
		else if ( year % 400 != 0) {
			return false;
		}
		else {
			return true;
		}
	}
	/* This function prints out the whole calendar of days once given the
	 * starting day of the week of the first of January. It Returns the 
	 * DayOfWeek of the next month. 
	 */
	private static DayOfWeek printCalendar (Month printMonth, DayOfWeek startDay, 
			int year) {
		
		// Taking care of the header Month, Year, and second line has days of
		// the week.
		System.out.printf("        %s %d%n", printMonth.getDisplayName(
				TextStyle.FULL, Locale.US), year);
		DayOfWeek [] allDays = DayOfWeek.values();
		for (DayOfWeek currentDay : allDays) {
			System.out.printf("%s ", currentDay.getDisplayName(TextStyle.SHORT,
					Locale.US));
		}
		System.out.printf("%n");
		
		
		// Printing out calendar days under each day of the week for the month.
		// If Sat is the first and the most number of days in a month is 31
		// then it the month could need six lines.
		
		// Each line is seven entries across with different padding needs
		// depending on whether 0, 1 or 2 digits need to be displayed
		
		// The first line of each month is  a special case so will be printed
		// with it's own tests.
		int dayNumber = 1;
		for (int i = 0; i < 7; i++) {
			if (allDays[i].getValue() < startDay.getValue()) {
				System.out.print("    ");
			}
			else {
				System.out.printf("  %d ", dayNumber);
				dayNumber++;
			}
		}
		System.out.printf("%n");
		
		// Now get the number of days in this month. Note I am using my own
		// boolean leap year method here for learning purposes.
		int daysInMonth = printMonth.length(isLeapYear(year));
		int crCounter = 1; 
		for (int i = dayNumber; i <= daysInMonth; i++) {
			if (dayNumber < 10) {
				System.out.print("  ");
			}
			else {
			 	System.out.print(" ");
			}
			System.out.printf("%d ", dayNumber);
			dayNumber++;
			if (crCounter % 7 == 0) {
				System.out.printf("%n");
			}
			crCounter++;
		}
		System.out.printf("%n%n%n");
		// Now set up logic, the method will return the day of the week
		// the next month falls on.
		DayOfWeek nextMonthsFirstDay = startDay.plus(daysInMonth);
		return nextMonthsFirstDay;
	}
}

