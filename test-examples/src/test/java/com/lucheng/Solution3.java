package com.lucheng;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
	public boolean wordPattern(String pattern, String str) {
		String k = " ";
		String[] tempArr = str.split(k);
		if (pattern.length() != tempArr.length) {
			return false;
		}

		Map<Character, String> map = new HashMap<>();
		
		for (int i = 0; i < pattern.length(); i++) {
			Character key = pattern.charAt(i);
			if (!map.containsKey(key)) {
				if(map.containsValue(tempArr[i])) {
					return false;
				}
				map.put(key, tempArr[i]);
			}else if(!tempArr[i].equals(map.get(key))){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution3 s = new Solution3();
		System.out.println(s.wordPattern("abba", "dog cat cat do1g"));
	}

}
