public class PalindromePartitioning {

    static int[][] m = new int[100][100];

    static boolean isPalindrome(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;

    }

    static int palindromePartitioning(String str, int i, int j) {
        if (i >= j || isPalindrome(str, i, j))
            return 0;

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = palindromePartitioning(str, i, k) + palindromePartitioning(str, k + 1, j) + 1;
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    static int palindromePartitioningMemoization(String str, int i, int j) {
        if (i >= j || isPalindrome(str, i, j))
            return 0;
        if (m[i][j] != -1) {
            return m[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = palindromePartitioningMemoization(str, i, k) + palindromePartitioningMemoization(str, k + 1, j)
                    + 1;
            if (temp < min) {
                min = temp;
            }
        }
        return m[i][j] = min;
    }

    public static void main(String[] args) {
        System.out.println("Palindrome Partitioning:\t" + palindromePartitioning("geekeega", 0, 7));
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                m[i][j] = -1;
            }
        }
        System.out.println(
                "Palindrome Partitioning using Memoization:\t" + palindromePartitioningMemoization("geekeega", 0, 7));

    }
}
