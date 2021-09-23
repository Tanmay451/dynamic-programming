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

    static void printLongestCommonSubsequenceMemoization(String str1, String str2, int idx1, int idx2) {
        System.out.println("Print Longest Common Subsequence using array Memoization");
        int i = idx1, j = idx2;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                System.out.print(str2.charAt(j - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        System.out.println();
    }

    static void printLongestCommonSubsequenceDP(String str1, String str2, int idx1, int idx2) {
        System.out.println("Print Longest Common Subsequence using array DP");
        int i = idx1, j = idx2;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                System.out.print(str2.charAt(j - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        System.out.println();
    }

    static void printShortestCommonSupersequenceDP(String str1, String str2, int idx1, int idx2) {
        System.out.println("Print Shortest Common Supersequence using array DP");

        int i = idx1, j = idx2;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                System.out.print(str2.charAt(j - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    System.out.print(str2.charAt(j - 1));
                    j--;
                } else {
                    System.out.print(str1.charAt(i - 1));
                    i--;
                }
            }
        }
        while (i > 0) {
            System.out.print(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            System.out.print(str2.charAt(j - 1));
            j--;
        }

        System.out.println();
    }

    static int smallestCommonSupersequence(String str1, String str2, int idx1, int idx2) {
        int lcs = longestCommonSubsequenceDP(str1, str2, idx1, idx2);
        return idx1 + idx2 - lcs;
    }

    static int MinimumNumberOfInsertionAndDeletionToConvert(String str1, String str2, int idx1, int idx2) {
        int lcs = longestCommonSubsequenceDP(str1, str2, idx1, idx2);
        return idx1 + idx2 - (2 * lcs);
    }

    public static void main(String[] args) {
        String str1 = "abcdef";
        String str2 = "eage";
        System.out.println("Longest Common Subsequence:\t" + longestCommonSubsequence(str1, str2, 6, 4));

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                m[i][j] = -1;
            }
        }
        System.out.println("Longest Common Subsequence using Memoization:\t"
                + longestCommonSubsequenceMemoization(str1, str2, 6, 4));
        System.out.println("Longest Common Subsequence using DP:\t" + longestCommonSubsequenceDP(str1, str2, 6, 4));

        printLongestCommonSubsequenceMemoization(str1, str2, 6, 4);
        printLongestCommonSubsequenceDP(str1, str2, 6, 4);

        System.out.println("Smallest Common Supersequence:\t" + smallestCommonSupersequence(str1, str2, 6, 4));
        System.out.println("Minimum Number Of Insertion And Deletion To Convert:\t"
                + MinimumNumberOfInsertionAndDeletionToConvert(str1, str2, 6, 4));
        printShortestCommonSupersequenceDP(str1, str2, 6, 4);
    }
}