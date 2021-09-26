import java.util.Arrays;

public class MinimumNumberOfCoins {

	static int[][] m = new int[100][100];
	static int[][] dp = new int[100][100];

	static int min(int a, int b) {
		return (a > b) ? b : a;
	}

	static int minimumNumberOfCoins(int[] array, int L, int S, int idx) {
		if (S == 0)
			return 0;
		if (idx == 0)
			return 9999;

		if (array[idx - 1] > S)
			return minimumNumberOfCoins(array, L, S, idx - 1);
		return min(minimumNumberOfCoins(array, L, S, idx - 1),
				1 + minimumNumberOfCoins(array, L, S - array[idx - 1], idx));
	}

	static int minimumNumberOfCoinsMemoization(int[] array, int L, int S, int idx) {
		if (S == 0)
			return 0;
		if (idx == 0)
			return 9999;
		if (m[idx][S] != -1)
			return m[idx][S];

		if (array[idx - 1] > S)
			return m[idx][S] = minimumNumberOfCoinsMemoization(array, L, S, idx - 1);
		return m[idx][S] = min(minimumNumberOfCoinsMemoization(array, L, S, idx - 1),
				1 + minimumNumberOfCoinsMemoization(array, L, S - array[idx - 1], idx));
	}

	static int minimumNumberOfCoinsDP(int[] array, int L, int S, int idx) {
		for (int j = 0; j <= S; j++)
			dp[0][j] = 9999;
		for (int i = 0; i <= idx; i++)
			dp[i][0] = 0;

		for (int i = 1; i <= idx; i++) {
			for (int j = 1; j <= S; j++) {
				if (array[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = min(dp[i - 1][j], 1 + dp[i][j - array[i - 1]]);
			}
		}
		return dp[idx][S];
	}

	public static void main(String[] args) {
		int array[] = { 1, 2, 3 };
		int sum = 5;
		System.out.println("Minimum number of coins to get sum as " + sum + " from " + Arrays.toString(array) + " is "
				+ minimumNumberOfCoins(array, 3, sum, 3));
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m[i][j] = -1;
			}
		}
		System.out.println("Minimum number of coins to get sum as " + sum + " from " + Arrays.toString(array)
				+ " using memoization is " + minimumNumberOfCoinsMemoization(array, 3, sum, 3));

		System.out.println("Minimum number of coins to get sum as " + sum + " from " + Arrays.toString(array)
				+ " using DP is " + minimumNumberOfCoinsDP(array, 3, sum, 3));

	}
}
