public class NumberOfSubsetWithGivenDifference {
	static int[][] dp = new int[100][100];

	static int arraySum(int[] array, int l) {
		int s = 0;
		for (int i = 0; i < l; i++)
			s += array[i];
		return s;
	}

	static int subsetCount(int[] array, int sum, int l) {
		for (int j = 0; j < sum + 1; j++)
			dp[0][j] = 0;
		for (int i = 0; i < l + 1; i++)
			dp[i][0] = 1;
		for (int i = 1; i < l + 1; i++) {
			for (int j = 1; j < sum + 1; j++) {
				if (array[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - array[i - 1]];
			}
		}
		return dp[l][sum];
	}

	static int numberOfSubsetWithGivenDifference(int[] array, int l, int d) {
		int sum = arraySum(array, l);

		return subsetCount(array, l, (d + sum) / 2);
	}

	public static void main(String[] args) {
		int[] array = { 1, 1, 2, 3 };
		System.out.println(numberOfSubsetWithGivenDifference(array, 4, 1));
	}
}
