package com.kangdainfo.util.lang.runtime;

public class HelloWorld {

	public static void main(String args[]) {
		System.out.println("hello world!");
		
		int i = 0;
		while(true) {
			try {
				Thread.sleep(5000);
				System.out.println("running " + i++);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
