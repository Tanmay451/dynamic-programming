public class MinimumSubsetSumDifference {
	static boolean[][] dp = new boolean[100][100];

	static int ArraySum(int array[], int l) {
		int s = 0;
		for (int i = 0; i < l; i++)
			s += array[i];
		return s;
	}

	static void subsetDP(int[] arr, int len, int sum) {

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
	}

	static int minimumSubsetSumDifference(int array[], int l) {
		int sum = ArraySum(array, l);
		subsetDP(array, l, sum / 2);
		int min = 9999;

		for (int j = 0; j <= sum / 2; j++) {
			if (dp[l][j])
				min = j;
		}
		return sum - (2 * min);
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 7 };
		System.out.println(minimumSubsetSumDifference(array, 3));
	}
}
