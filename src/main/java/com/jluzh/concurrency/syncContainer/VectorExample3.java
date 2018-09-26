package com.jluzh.concurrency.syncContainer;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample3 {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test03(vector);
    }

    //java.util.ConcurrentModificationException
    private static void test01(Vector<Integer> vector) {
        for (Integer i : vector) {
            if (3 == i) {
                vector.remove(i);
            }
        }
    }
    // java.util.ConcurrentModificationException
    private static void test02(Vector<Integer> vector) {
        Iterator<Integer> integerIterator = vector.iterator();
        while (integerIterator.hasNext()) {
            Integer i = integerIterator.next();
            if (3 == i) {
                vector.remove(i);
            }
        }
    }
    // success
    private static void test03(Vector<Integer> vector) {
        for (int i = 0; i < vector.size() ; i++) {
            if (3 == i) {
                vector.remove(i);
            }
        }
    }
}
