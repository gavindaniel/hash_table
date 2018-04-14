package model;

import java.util.Observable;

public class HashTable extends Observable {

	public static final int size = 53;	// hash table size
	
	public HashTable() {
		System.out.println("Creating new hash table...");
	}
	
	public int getSize() { 	return size;		}
	
}
