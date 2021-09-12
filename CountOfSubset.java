public class CountOfSubset {

	static int[][] m = new int[100][100];
	static int[][] dp = new int[100][100];

	static int countOfSubsetRecursion(int[] array, int S, int l) {
		if (S == 0)
			return 1;
		if (l == 0)
			return 0;
		if (S < array[l - 1])
			return countOfSubsetRecursion(array, S, l - 1);

		return (countOfSubsetRecursion(array, S, l - 1) + countOfSubsetRecursion(array, S - array[l - 1], l - 1));
	}

	static int countOfSubsetMemoization(int[] array, int S, int l) {
		if (S == 0)
			return 1;
		if (l == 0)
			return 0;

		if (m[S][l] != -1)
			return m[S][l];

		if (S < array[l - 1])
			return countOfSubsetMemoization(array, S, l - 1);

		return m[S][l] = (countOfSubsetMemoization(array, S, l - 1)
				+ countOfSubsetMemoization(array, S - array[l - 1], l - 1));
	}

	static int countOfSubsetDP(int[] array, int S, int l) {
		for (int j = 0; j < l + 1; j++)
			dp[0][j] = 0;
		for (int i = 0; i < l + 1; i++)
			dp[i][0] = 1;

		for (int i = 1; i < l + 1; i++) {
			for (int j = 1; j < S + 1; j++) {
				if (array[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - array[i - 1]];
			}
		}
		return dp[S][l];
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5 };
		System.out.println(countOfSubsetRecursion(array, 5, 5));
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m[i][j] = -1;
			}
		}
		System.out.println(countOfSubsetMemoization(array, 5, 5));

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(countOfSubsetDP(array, 5, 5));
	}
}
