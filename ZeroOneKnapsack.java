public class ZeroOneKnapsack {
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static int[][] m = new int[100][100];
	static int[][] dp = new int[100][100];

	static int knapsackRecursive(int[] wt, int[] val, int W, int n) {
		if (W == 0 || n == 0)
			return 0;
		if (wt[n - 1] <= W) {
			return max(val[n - 1] + knapsackRecursive(wt, val, W - wt[n - 1], n - 1),
					knapsackRecursive(wt, val, W, n - 1));
		} else
			return knapsackRecursive(wt, val, W, n - 1);
	}

	static int knapsackMemoization(int[] wt, int[] val, int W, int N) {
		if (W == 0 || N == 0)
			return 0;

		if (m[W][N] != -1)
			return m[W][N];

		if (wt[N - 1] <= W) {
			return m[W][N] = max(val[N - 1] + knapsackMemoization(wt, val, W - wt[N - 1], N - 1),
					knapsackMemoization(wt, val, W, N - 1));
		} else
			return m[W][N] = knapsackMemoization(wt, val, W, N - 1);
	}

	static int knapsackDP(int[] wt, int[] val, int W, int N) {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= W; j++) {
				if (wt[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
			}
		}
		return dp[N][W];
	}

	public static void main(String[] args) {
		int[] wt = { 1, 3, 4, 1, 1, 1, 1, };
		int[] val = { 1, 8, 3, 1, 1, 1, 1 };
		int W = 7;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m[i][j] = -1;
			}
		}

		System.out.println("0/1 Knapsack using recurtion:\t" + knapsackRecursive(wt, val, W, 7));
		System.out.println("0/1 Knapsack using memoization:\t" + knapsackMemoization(wt, val, W, 7));
		System.out.println("0/1 Knapsack using DP:\t" + knapsackDP(wt, val, W, 7));
	}
}
