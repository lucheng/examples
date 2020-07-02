package com.lucheng;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public boolean isPalindrome(String s) {
		String temp1 = s;
		List<Character> list = new ArrayList<>();
		for (char c : temp1.toLowerCase().toCharArray()) {
			if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
				list.add(c);
			}
		}

		int size = list.size();
		
		for (int i = 0; i < size / 2; i++) {
			if(list.get(i)!=list.get(size-i-1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
		char c = 1;
		System.out.println(c >= 0 && c <= 9);

	}

}
