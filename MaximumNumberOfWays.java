import java.util.Arrays;

public class MaximumNumberOfWays {

	static int[][] m = new int[100][100];
	static int[][] dp = new int[100][100];

	static int maximumNumberOfWays(int[] array, int L, int S, int idx) {
		if (S == 0)
			return 1;
		if (idx == 0)
			return 0;

		if (array[idx - 1] > S)
			return maximumNumberOfWays(array, L, S, idx - 1);
		return maximumNumberOfWays(array, L, S, idx - 1) + maximumNumberOfWays(array, L, S - array[idx - 1], idx);
	}

	static int maximumNumberOfWaysMemoization(int[] array, int L, int S, int idx) {
		if (S == 0)
			return 1;
		if (idx == 0)
			return 0;
		if (m[idx][S] != -1)
			return m[idx][S];

		if (array[idx - 1] > S)
			return m[idx][S] = maximumNumberOfWaysMemoization(array, L, S, idx - 1);
		return m[idx][S] = maximumNumberOfWaysMemoization(array, L, S, idx - 1)
				+ maximumNumberOfWaysMemoization(array, L, S - array[idx - 1], idx);

	}

	static int maximumNumberOfWaysDP(int[] array, int L, int S, int idx) {
		for (int j = 0; j <= S; j++)
			dp[0][j] = 0;
		for (int i = 0; i <= idx; i++)
			dp[i][0] = 1;

		for (int i = 1; i <= idx; i++) {
			for (int j = 1; j <= S; j++) {
				if (array[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j] + dp[i][j - array[i - 1]];
			}
		}

		return dp[idx][S];
	}

	public static void main(String[] args) {
		int array[] = { 1, 2, 3 };
		int sum = 5;
		System.out.println("Maximum number of ways to get sum as " + sum + " from " + Arrays.toString(array) + " is "
				+ maximumNumberOfWays(array, 3, sum, 3));
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m[i][j] = -1;
			}
		}
		System.out.println("Maximum number of ways to get sum as " + sum + " from " + Arrays.toString(array)
				+ " using memoization is " + maximumNumberOfWaysMemoization(array, 3, sum, 3));

		System.out.println("Maximum number of ways to get sum as " + sum + " from " + Arrays.toString(array)
				+ " using DP is " + maximumNumberOfWaysDP(array, 3, sum, 3));

	}
}
