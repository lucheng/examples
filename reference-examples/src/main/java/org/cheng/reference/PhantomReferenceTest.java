package org.cheng.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.PhantomReference;

public class PhantomReferenceTest {

    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.gc();
        System.out.println("---");
    }
}