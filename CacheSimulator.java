package cacheSimulator;

import java.math.BigInteger;
import java.util.Scanner;

public class CacheSimulator {
	static final int MAX_SIZE = 32;
	static double total = 0;
	
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		int a = Integer.parseInt(args[1]);
		String testFile = args[2];
		
		Cache cache = new Cache(a, size);
		Scanner cacheScan = new Scanner(File(testFile));
		
		BigInteger address;
		BigInteger tag;
		char op;
		int setCount;
		String textLine;
		
		// 읽어오기
		while(cacheScan.hasNextLine()) {
			textLine = cacheScan.nextLine();
			op = textLine.charAt(0);
			address =new BigInteger(textLine.substring(4),16);
			
			// tag 계산 주소 / 사이즈
			tag = address.divide(BigInteger.valueOf(MAX_SIZE));
			
			// setCount계산
			setCount = (tag.mod(BigInteger.valueOf(cache.setCount))).intValue();
			
			total++;
			
			switch(op) {
			// 데이터 읽는 경우
			case 'R':
				cache.read(tag, setCount);
				break;
			
			// 데이터 쓰는 경우
			case 'W':
				cache.write(tag, setCount);
				break;
			default:
				System.out.println("ERROR");
			}
		}
		
		// 결과 출력
		System.out.println("hit: " + Cache.hitCount);
		System.out.println("miss: " + Cache.missCount);
		System.out.println("total: " + total);
	}

	private static Readable File(String testFile) {
		return null;
	}
}
