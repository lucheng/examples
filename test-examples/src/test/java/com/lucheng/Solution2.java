package com.lucheng;

public class Solution2 {
	public int search(int[] nums, int target) {
		int low = 0;
		int high = nums.length - 1;
		while (low <= high) {
			// 计算出中间索引值
			int middle = (high + low) >>> 1;// 防止溢出
			if (target == nums[middle]) {
				return middle;
			}
			
			if (nums[middle] < nums[high]) {
				if(target == nums[high]) {
					return high;
				}
				
				if (target > nums[middle] && target < nums[high]) {
					low = middle + 1;
				}else {
					high = middle - 1;
				}
			}else {
				if(target == nums[low]) {
					return low;
				}
				if (target > nums[low] && target < nums[middle]) {
					high = middle - 1;
				}else {
					low = middle + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Solution2 s = new Solution2();
		System.out.println(s.search(new int[] { 5,1, 2, 3, 4 }, 1));
	}

}
