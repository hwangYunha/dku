package cacheSimulator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class Cache {
	
	int a, i, j;
	int setCount;
	int size;
	Blocks[][] block;
	ArrayList<LinkedList<Integer>> data;
	
	static final int MAX_SIZE = 32;
	static double hitCount, missCount, readCount = 0;

	
	// ĳ��
	public Cache(int a, int s) {
		this.a = a;
		this.size= s;
		setCount = s / (MAX_SIZE * a);
		block = new Blocks[setCount][a];
		data = new ArrayList<LinkedList<Integer>>();
		
		for(i=0; i<setCount; i++) {
			data.add(new LinkedList<Integer>());
		}
		
		for(i=0; i<block.length; i++) {
			for(j=0; j<block[i].length; j++) {
				block[i][j] = new Blocks();
			}
		}
	}
	
	
	// �� block ã��
	public int searchEmptyBlock(int s) {
		LinkedList<Integer> set = data.get(s);
		for(i=0; i<a; i++) {
			if(block[s][i].empty) {
				return i;
			}
		} return set.remove();
	}
	
	
	//�ε���
	public int setIndex(BigInteger t, int c) {
		for (i=0; i<a; i++) {
			if(block[c][i].tag != null && block[c][i].tag.compareTo(t) == 0)
				return i;
		} return -1;
	}
	
	
	// �б�
	public void read(BigInteger t, int c) {
		int index = setIndex(t, c);
		
		// Hit �� ��� - data ����
		if(index != -1) {
			reData(c, index);
			hitCount++;
		}
		
		// Miss �� ���
		else {
			index = searchEmptyBlock(c);
			Blocks blocks = block[c][index];
			
			// ��ä��, ä���ٴ� ǥ�÷� empty false
			blocks.tag = t;
			blocks.empty = false; 
			
			missCount++;
			readCount++;
		}
		
		reData(c, index);
	}
	
	public void write(BigInteger t, int c) {
		// �̿ϼ�
	}
	
	public void reData(int c, int index) {
		LinkedList<Integer> set = data.get(c);
		// �̿ϼ�
	}

}
