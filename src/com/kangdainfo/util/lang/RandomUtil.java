package com.kangdainfo.util.lang;

import java.util.Random;

public class RandomUtil {
	
	private static Random random = new Random(System.currentTimeMillis());
	
	public static double nextDouble() {
		return random.nextDouble();
	}
	
}
