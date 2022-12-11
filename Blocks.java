package cacheSimulator;

import java.math.BigInteger;

public class Blocks {
	BigInteger tag;
	boolean empty;
	boolean full;
	
	public Blocks() {
		tag = BigInteger.valueOf(0);
		empty = true;
		full = false;
	}
}
