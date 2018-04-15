package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVScanner {

	public static String[] main() throws FileNotFoundException {
		// init temp variable & counter
		String temp = "";
		int i = 0;
		// init table
		String[] input = new String[53];
		// init scanner
		Scanner scanner = new Scanner(new File("files/states.csv"));
		// set the delimiter to ","
		scanner.useDelimiter(",");
		// scan the input file
		while (scanner.hasNext()) {
			temp = scanner.next();
//			System.out.print(temp + " | ");
			input[i] = temp;
			i++;
		}
		scanner.close();
		return input;
	}
	
}
