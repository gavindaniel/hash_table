package model;

import java.io.FileNotFoundException;
import java.util.Observable;

public class HashTable extends Observable {

	// static final variable(s)
	public static final int size = 53;	// hash table size
	// variable(s)
	private String[] table;
	
	public HashTable() {
		System.out.println("Creating new hash table...");
		table = new String[size];
		try {
			table = CSVScanner.main();
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("File not found!");
		}
	}
	
	public int getSize() { 	return size;		}
	public String[] getTable() {		return table;	}
	
}
