package model;

public class HashResult {

	private int index;
	private String key;
	private boolean result;
	
	public HashResult() {
		index = -1;
		key = "";
		result = false;
	}
	public HashResult(int i, String k, boolean r) {
		index = i;
		key = k;
		result = r;
	}
	
	public int getIndex() {	return index;	}
	public String getKey() {	return key;	}
	public boolean getResult() {	return result;	}
	
	public void setIndex(int i) {	index = i;	}
	public void setKey(String k) {	key = k;		}
	public void setResult(boolean r) {	result = r;	}
	
}
