public class ZeroOneKnapsack {
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static int[][] t = new int[100][100];

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

		if (t[W][N] != -1)
			return t[W][N];
		if (wt[N - 1] <= W) {
			return max(val[N - 1] + knapsackMemoization(wt, val, W - wt[N - 1], N - 1),
					knapsackMemoization(wt, val, W, N - 1));
		} else
			return knapsackMemoization(wt, val, W, N - 1);
	}

	public static void main(String[] args) {
		int[] wt = { 1, 3, 4 };
		int[] val = { 1, 8, 3 };
		int W = 5;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				t[i][j] = -1;
			}
		}

		System.out.println("0/1 Knapsack using recurtion:\t" + knapsackRecursive(wt, val, W, 3));
		System.out.println("0/1 Knapsack using memoization:\t" + knapsackMemoization(wt, val, W, 3));
	}
}
