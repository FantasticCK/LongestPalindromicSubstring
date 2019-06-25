package com.CK;

public class Main {

    public static void main(String[] args) {
        String result1 = new Solution1().longestPalindrome("abba");
        System.out.println(result1);
        String result2 = new Solution2().longestPalindrome("abba");
        System.out.println(result2);
    }
}


class Solution1 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        } else {
            int maxPalindromeLength = 0;
            String longest = "";
            int length = s.length();
            boolean[][] ifPalindrome = new boolean[length][length];
            for (int i = length - 1; i >= 0; i--) {
                for (int j = i; j < length; j++) {
                    ifPalindrome[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || ifPalindrome[i + 1][j - 1]);
                    if (ifPalindrome[i][j] && s.substring(i, j + 1).length() > maxPalindromeLength) {
                        longest = s.substring(i, j + 1);
                        maxPalindromeLength = longest.length();
                    }
                }
            }
            return longest;
        }
    }
}

class Solution2 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}