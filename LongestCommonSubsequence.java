public class LongestCommonSubsequence {

    static int[][] m = new int[100][100];
    static int[][] dp = new int[100][100];

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int longestCommonSubsequence(String str1, String str2, int idx1, int idx2) {
        if (idx1 == 0 || idx2 == 0)
            return 0;
        if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1))
            return 1 + longestCommonSubsequence(str1, str2, idx1 - 1, idx2 - 1);

        return max(longestCommonSubsequence(str1, str2, idx1 - 1, idx2),
                longestCommonSubsequence(str1, str2, idx1, idx2 - 1));
    }

    static int longestCommonSubsequenceMemoization(String str1, String str2, int idx1, int idx2) {
        if (idx1 == 0 || idx2 == 0)
            return 0;

        if (m[idx1][idx2] != -1)
            return m[idx1][idx2];

        if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1))
            return m[idx1][idx2] = 1 + longestCommonSubsequenceMemoization(str1, str2, idx1 - 1, idx2 - 1);

        return m[idx1][idx2] = max(longestCommonSubsequenceMemoization(str1, str2, idx1 - 1, idx2),
                longestCommonSubsequenceMemoization(str1, str2, idx1, idx2 - 1));
    }

    static int longestCommonSubsequenceDP(String str1, String str2, int idx1, int idx2) {
        for (int i = 0; i <= idx1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= idx1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= idx1; i++) {
            for (int j = 1; j <= idx2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j];
                else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[idx1][idx2];
    }

    public static void main(String[] args) {
        String str1 = "abcdef";
        String str2 = "abef";
        System.out.println("Longest Common Subsequence:\t" + longestCommonSubsequence(str1, str2, 6, 4));

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                m[i][j] = -1;
            }
        }
        System.out.println("Longest Common Subsequence using Memoization:\t"
                + longestCommonSubsequenceMemoization(str1, str2, 6, 4));
        System.out.println("Longest Common Subsequence using DP:\t" + longestCommonSubsequenceDP(str1, str2, 6, 4));
    }
}