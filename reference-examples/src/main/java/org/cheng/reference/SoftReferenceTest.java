package org.cheng.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {

	public static void main(String[] args) {
		SoftReference ref = new SoftReference(new MyDate());
		ReferenceTest.drainMemory();
		System.out.println("---");
	}

}
