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

	
	// 캐시
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
	
	
	// 빈 block 찾기
	public int searchEmptyBlock(int s) {
		LinkedList<Integer> set = data.get(s);
		for(i=0; i<a; i++) {
			if(block[s][i].empty) {
				return i;
			}
		} return set.remove();
	}
	
	
	//인덱스
	public int setIndex(BigInteger t, int c) {
		for (i=0; i<a; i++) {
			if(block[c][i].tag != null && block[c][i].tag.compareTo(t) == 0)
				return i;
		} return -1;
	}
	
	
	// 읽기
	public void read(BigInteger t, int c) {
		int index = setIndex(t, c);
		
		// Hit 한 경우 - data 갱신
		if(index != -1) {
			reData(c, index);
			hitCount++;
		}
		
		// Miss 된 경우
		else {
			index = searchEmptyBlock(c);
			Blocks blocks = block[c][index];
			
			// 블럭채움, 채웠다는 표시로 empty false
			blocks.tag = t;
			blocks.empty = false; 
			
			missCount++;
			readCount++;
		}
		
		reData(c, index);
	}
	
	public void write(BigInteger t, int c) {
		// 미완성
	}
	
	public void reData(int c, int index) {
		LinkedList<Integer> set = data.get(c);
		// 미완성
	}

}
