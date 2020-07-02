package com.lucheng.arithmetic.sort;

import java.util.Arrays;
/**
 * 冒泡排序
 * @author lucheng
 *
 */
public class BubbleSort {
	public static void bubbleSort(int[] arr) {
		
		for (int i = 0; i < arr.length; i++) {
			boolean flag = false;
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					flag = true;
				}
			}
			if(!flag){
                break;//若果没有发生交换，则退出循环
            }
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 6, 2, 2, 5 };
		BubbleSort.bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
