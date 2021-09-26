public class EqualSum {
    static boolean[][] dp = new boolean[100][100];

    static int ArraySum(int[] arr, int len) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static boolean subsetDP(int[] arr, int len, int sum) {

        for (int j = 0; j <= sum; j++) {
            dp[0][j] = false;
        }

        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = (dp[i - 1][j] || dp[i - 1][j - arr[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][sum];
    }

    static boolean equalSum(int[] arr, int len) {
        int sum = ArraySum(arr, len);

        if (sum % 2 == 1)
            return false;

        return subsetDP(arr, len, sum / 2);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4 };
        System.out.println("equalSum:\t" + equalSum(arr, 4));
    }
}
