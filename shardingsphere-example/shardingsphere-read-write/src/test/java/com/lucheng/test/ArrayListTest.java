package com.lucheng.test;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
				add(4);
			}
		};
		
		Stream<Integer> stream = list.stream();
		
		list = (ArrayList<Integer>) stream.filter(x->x==1).collect(Collectors.toList());
//		list = (ArrayList<Integer>) stream.filter(x-> x>0).collect(Collectors.toList());
		System.out.println(list);
	}
	

}
