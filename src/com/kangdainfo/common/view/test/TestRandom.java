package com.kangdainfo.common.view.test;

import com.kangdainfo.common.util.Common;
import java.security.SecureRandom;
public class TestRandom {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//SecureRandom x = new SecureRandom();
		//for (int i=0; i<100; i++) {			
		//	System.out.println(i+"."+(SecureRandom.getInstance("SHA1PRNG").nextInt(1000)));
			//System.out.println(i+"."+(int)(Math.random()*1000));
		//}
		
		System.out.println(Common.getDigestString("kds123", "SHA-1"));

	}

}
