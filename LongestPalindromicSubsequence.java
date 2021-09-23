public class LongestPalindromicSubsequence {

    static int[][] dp = new int[100][100];

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    static public int longestRepeatingSubsequenceHelper(String str1, String str2, int idx1, int idx2) {
        if (idx1 == 0 || idx2 == 0)
            return 0;
        if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1) && idx1 != idx2)
            return 1 + longestRepeatingSubsequenceHelper(str1, str2, idx1 - 1, idx2 - 1);
        return max(longestRepeatingSubsequenceHelper(str1, str2, idx1 - 1, idx2),
                longestRepeatingSubsequenceHelper(str1, str2, idx1, idx2 - 1));
    }

    static public int longestRepeatingSubsequenceHelperDP(String str1, String str2, int idx1, int idx2) {
        for (int j = 0; j <= idx2; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i <= idx1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= idx1; i++) {
            for (int j = 1; j <= idx2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[idx1][idx2];
    }

    static int longestCommonSubsequenceDP(String str1, String str2, int idx1, int idx2) {
        for (int j = 0; j <= idx2; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i <= idx2; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= idx1; i++) {
            for (int j = 1; j <= idx2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[idx1][idx2];
    }

    static int longestRepeatingSubsequence(String str, int idx) {
        return longestRepeatingSubsequenceHelper(str, str, idx, idx);
    }

    static int longestRepeatingSubsequenceDP(String str, int idx) {
        return longestRepeatingSubsequenceHelperDP(str, str, idx, idx);
    }

    static int longestPalindromicSubsequence(String str, int idx) {
        return longestCommonSubsequenceDP(str, reverseString(str), idx, idx);
    }

    static int MinimumNumberOfDeletionToConvert(String str, int idx) {
        return idx - longestCommonSubsequenceDP(str, reverseString(str), idx, idx);
    }

    public static void main(String[] args) {
        String str = "aabebcdd";
        System.out.println("Longest Palindromic Subsequence:\t" + longestPalindromicSubsequence(str, 8));
        System.out.println(
                "Minimum Number Of Deletion To Convert into palindrome:\t" + MinimumNumberOfDeletionToConvert(str, 8));
        System.out.println("Longest Repeating Subsequence:\t" + longestRepeatingSubsequence(str, 8));
        System.out.println("Longest Repeating Subsequence using DP:\t" + longestRepeatingSubsequenceDP(str, 8));
    }
}
