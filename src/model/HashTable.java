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
		String[] temp = new String[size];
		try {
//			table = CSVScanner.main();
			temp = CSVScanner.main();
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("File not found!");
		}
//		printTable();
		HashResult hr = new HashResult();
		for (int i = 0; i < 53; i ++) {//table.length; i ++) {
			if (temp[i] != null) {
				hr = hash(temp[i]);
				if (!hr.getResult()) // failure
					System.out.println("'" + temp[i] + "' collided at index " + hr.getIndex());
				else { // sucess
	//				System.out.println("'" + temp[i] + "' added successfully");
				}
	//			printTable();
			}
		}
	}
	
	public int getSize() { 	return size;		}
	public String[] getTable() {		return table;	}
	public void setTable(String[] ht) {	table = ht;	}
	
	private boolean add(int index, String state) {
		String[] htable = this.getTable();
		if (htable[index] == null) {	// the table cell is empty
			htable[index] = state;
			this.setTable(htable);
			setChanged();
			notifyObservers();
			return true;
		} else {						// the table cell is NOT empty
			return false;
		}
		
	}
	
	public HashResult hash(String state) {
		
		HashResult hash_result = new HashResult();
		int key = 0;
		boolean success = true;
		
		for (int i = 0; i < state.length(); i++) {
			key += state.charAt(i);
		}
		key = key % 53;
		
		success = this.add(key, state);
		
		if (!success) {
			success = probe(key, state, 0);
		}
		hash_result.setIndex(key);
		hash_result.setKey(state);
		hash_result.setResult(success);
		
		return hash_result;
	}
	
	private boolean probe(int key, String state, int count) {
		
		int newKey = 0;
		boolean success = true;
		
		newKey = key + (count*count);
		newKey = newKey % 53;
		
		success = this.add(newKey, state);
		
		if (success) {
			return true;
		} else if (!success && count != 53) {
			probe(key, state, count+1);
		}  //else 
		return false;
	}
	
	private void printTable() {
		for (int i = 0; i < table.length; i++) {
			System.out.println("| " + i + " | " + table[i] + " |");
		}
	}
}
