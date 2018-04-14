package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVScanner {

	public static void main() throws FileNotFoundException {
		// init scanner
		Scanner scanner = new Scanner(new File("files/mountain.csv"));
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			System.out.print(scanner.next()+" | ");
		}
		scanner.close();
	}
	
}
