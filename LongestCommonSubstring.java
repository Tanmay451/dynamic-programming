import java.util.concurrent.ForkJoinPool;

public class LongestCommonSubstring {

    static int[][] dp = new int[100][100];
    static int[][] m = new int[100][100];

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int longestCommonSubstring(String str1, String str2, int idx1, int idx2, int count) {
        if (idx1 == 0 || idx2 == 0)
            return count;
        if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1))
            count = longestCommonSubstring(str1, str2, idx1 - 1, idx2 - 1, count + 1);
        return max(max(longestCommonSubstring(str1, str2, idx1 - 1, idx2, 0),
                longestCommonSubstring(str1, str2, idx1, idx2 - 1, 0)), count);
    }

    static int longestCommonSubstringMemoization(String str1, String str2, int idx1, int idx2, int count) {
        if (idx1 == 0 || idx2 == 0)
            return count;

        if (m[idx1][idx2] != -1) {
            return m[idx1][idx2];
        }
        if (str1.charAt(idx1 - 1) == str2.charAt(idx2 - 1))
            count = longestCommonSubstring(str1, str2, idx1 - 1, idx2 - 1, count + 1);
        return m[idx1][idx2] = max(max(longestCommonSubstring(str1, str2, idx1 - 1, idx2, 0),
                longestCommonSubstring(str1, str2, idx1, idx2 - 1, 0)), count);
    }

    static int longestCommonSubstringDP(String str1, String str2, int idx1, int idx2) {
        for (int j = 0; j < idx2; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i < idx2; i++) {
            dp[i][0] = 0;
        }
        int result = 0;
        for (int i = 1; i < idx1; i++) {
            for (int j = 1; j < idx2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    result = max(dp[i][j], result);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str1 = "abcde", str2 = "abfce";

        System.out.println("longestCommonSubstring:\t" + longestCommonSubstring(str1, str2, 5, 5, 0));
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                m[i][j] = -1;
            }
        }

        System.out.println("Longest Common Substring using Memoization:\t"
                + longestCommonSubstringMemoization(str1, str2, 5, 5, 0));
        System.out.println("Longest Common Substring using DP:\t" + longestCommonSubstringDP(str1, str2, 5, 5));
    }
}