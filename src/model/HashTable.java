package model;

import java.io.FileNotFoundException;
import java.util.Observable;

public class HashTable extends Observable {

	public static final int size = 53;	// hash table size
	
	public HashTable() {
		System.out.println("Creating new hash table...");
		try {
			CSVScanner.main();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("File not found!");
		}
	}
	
	public int getSize() { 	return size;		}
	
}
