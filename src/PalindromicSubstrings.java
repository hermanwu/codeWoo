/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.

*/


public class PalindromicSubstrings {
    class Solution {
        int result = 0;

        public int countSubstrings(String s) {

            for (int i = 0; i < s.length(); i++) {
                extendCount(s, i, i);
                extendCount(s, i, i + 1);
            }

            return result;
        }

        private void extendCount(String s, int left, int right) {
            while (left >= 0 &&
                    right < s.length() &&
                    s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                result++;
            }
        }
    }
}
