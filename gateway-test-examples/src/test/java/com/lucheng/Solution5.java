package com.lucheng;

public class Solution5 {
	public int numberOfSubstrings(String s) {
//		int num = 0;
//		int[] count = new int[3];
//		int leftStart = 0;
//		for (int j = 0; j < s.length(); j++) {
//			char charAtEnd = s.charAt(j);
//			count[charAtEnd - 'a']++;
//			while (count[0] >= 1 && count[1] >= 1 && count[2] >= 1) {
//				num = num + (s.length() - j);
//				char charAtStart = s.charAt(leftStart);
//				count[charAtStart - 'a']--;
//				leftStart++;
//			}
//		}
//		return num;
		
		int res = 0;
        // 记录 'a', 'b', 'c' 上次出现的位置
        int[] record = {-1, -1, -1}; 
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                res += Math.min(record[1], record[2]) + 1;
            } else if (s.charAt(i) == 'b') {
                res += Math.min(record[0], record[2]) + 1;
            } else {
                res += Math.min(record[0], record[1]) + 1;
            }
            // 更新 'a', 'b', 'c' 最近出现的位置
            record[s.charAt(i) - 'a'] = i;
        }
        return res;
	}

	public static void main(String[] args) {
		Solution5 s = new Solution5();

		System.out.println(s.numberOfSubstrings("abcabc"));
	}

}
