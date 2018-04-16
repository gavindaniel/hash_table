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
			temp = CSVScanner.main();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		HashResult add_result = new HashResult();
		for (int i = 0; i < 53; i ++) {
			if (temp[i] != null) {
				if (checkDuplicate(temp[i]) == false) {
					add_result = this.add(temp[i]);
					if (add_result.getResult() == false) // failure
						System.out.println("Error!\n'" + temp[i] + "' collided at index " + add_result.getIndex());
					else { // sucess
		//				System.out.println("'" + temp[i] + "' added successfully");
					}
				} else {
					System.out.println("Duplicate (" + temp[i] + ") found");
				}
			}
		}
	}
	
	public int getSize() { 	return size;		}
	public String[] getTable() {		return table;	}
	public void setTable(String[] ht) {	table = ht;	}
	

	private boolean checkEmpty(int index) {
		if (this.getTable()[index] == null) {
			return true; // empty slot
		} else 
			return false;
	}
	
	private boolean checkDuplicate(String state) {
		String[] htable = this.getTable();
		String temp1, temp2 = state.toLowerCase();
		for (int i = 0; i < htable.length; i++) {
			if (htable[i] != null) {
				temp1 = htable[i].toLowerCase();
				if (temp1.equals(temp2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private HashResult insert(int index, String state) {
		HashResult insert_result = new HashResult();
		insert_result.setIndex(index);
		insert_result.setKey(state);
		String[] htable = this.getTable();
		if (checkEmpty(index)) {	// the table cell is empty
			htable[index] = state;
			this.setTable(htable);
			insert_result.setResult(true);
			setChanged();
			notifyObservers();
		} else {						// the table cell is NOT empty
			insert_result.setResult(false);
		}
		return insert_result;
	}
	
	public HashResult delete(String state) {
		// variables
		HashResult delete_result = new HashResult();
//		HashResult probe_result = new HashResult();
		int hash_key = 0, probe_hash_key = 0;
		int count = 0;
		String[] htable = this.getTable();
		// generate hash key
		hash_key = hash(state);
		String temp1 = state.toLowerCase();
		String temp2 = htable[hash_key].toLowerCase();
		// check if the table is empty at the index
		if (checkEmpty(hash_key) == false) { // the table cell is not empty
			if (temp2.equals(temp1)) { // check that the state to be deleted matches the state at the index in the hash table
				htable[hash_key] = null; //"[deleted]"
				this.setTable(htable);
				delete_result.setIndex(hash_key);
				delete_result.setKey(state);
				delete_result.setResult(true);
				setChanged();
				notifyObservers();
			}
			else { // the state to delete does not match the state at that index in the hash table
				probe_hash_key = probe(hash_key, state, count);
				// check if the slot is empty
				temp2 = htable[probe_hash_key].toLowerCase(); 
				while (!temp2.equals(temp1) && count < 53) {
					count++;
					probe_hash_key = probe(hash_key, state, count);
					if (htable[probe_hash_key] != null)
						temp2 = htable[probe_hash_key].toLowerCase();
				}
				delete_result.setIndex(probe_hash_key);
				delete_result.setKey(state);
				if (temp2.equals(temp1)) { // check that the state to be deleted matches the state at the index in the hash table
					htable[probe_hash_key] = null; //"[deleted]"
					this.setTable(htable);
					delete_result.setResult(true);
					setChanged();
					notifyObservers();
				} else { // we hit the MAX count limit (53)
					delete_result.setResult(false);
				}
			}
		}
		return delete_result;
	}
	// function used to generate hash key value 
	private int hash(String state) {
		// hash key
		int hash_key = 0;
		String temp = state.toLowerCase();
		// hash function
		for (int i = 0; i < temp.length(); i++) {
			hash_key += temp.charAt(i);
		}
		hash_key = hash_key % 53;
		// return generated hash key
		return hash_key;
	}
	
	public HashResult add(String state) {
		// variables
		HashResult add_result = new HashResult();
		HashResult insert_result = new HashResult();
		int probe_hash_key = 0;
		int hash_key = 0;
		int count = 0;
		// first check if this is a duplicate
		if (checkDuplicate(state) == false) {
			// generate hash key from state string
			hash_key = hash(state);
			// try to insert the state at the generated hash index
			insert_result = this.insert(hash_key, state);
			// check for insertion success
			if (insert_result.getResult() == false) { // original insertion resulted in a collision
				System.out.println("'" + state + "' collided at index " + insert_result.getIndex());
				probe_hash_key = probe(hash_key, state, count);
				while (checkEmpty(probe_hash_key) == false && count < 53) {
					if (count > 0)
						System.out.println("'" + state + "' collided at index " + probe_hash_key);
					count++;
					probe_hash_key = probe(hash_key, state, count);
				}
				add_result.setIndex(probe_hash_key);
				add_result.setKey(state);
				insert_result = this.insert(probe_hash_key, state);
				if (insert_result.getResult() == true) {
					add_result.setResult(true);
				}
				else {
					add_result.setResult(false);
				}
			} else { // original insertion worked 
				add_result.setIndex(hash_key);
				add_result.setKey(state);
				add_result.setResult(true);
			}
		}
		// return the result of add method
		return add_result;
	}
	
	private int probe(int key, String state, int count) {
		// variables
//		HashResult probe_result = new HashResult();
		int newKey = 0;
		// quadratic probing function
		newKey = key + (count*count);
		newKey = newKey % 53;
		// return the new hash key
		return newKey;
	}
	
	private void printTable() {
		for (int i = 0; i < table.length; i++) {
			System.out.println("| " + i + " | " + table[i] + " |");
		}
	}
}
