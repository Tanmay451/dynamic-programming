public class SubsetSum {
	static boolean[][] dp = new boolean[100][100];
	static int[][] m = new int[100][100];

	static boolean subsetRecursion(int[] arr, int len, int sum) {
		if (sum == 0)
			return true;
		if (len == 0)
			return false;

		if (m[len][sum] != -1) {
			if (m[len][sum] == 1)
				return true;
			else
				return false;
		}

		boolean flag;

		if (sum >= arr[len - 1])
			flag = (subsetRecursion(arr, len - 1, sum - arr[len - 1]) || subsetRecursion(arr, len - 1, sum));
		else
			flag = subsetRecursion(arr, len - 1, sum);

		if (flag == true)
			m[len][sum] = 1;
		else
			m[len][sum] = 0;
		return flag;
	}

	static boolean subsetMemoization(int[] arr, int len, int sum) {
		if (sum == 0)
			return true;
		if (len == 0)
			return false;

		if (m[len][sum] != -1) {
			if (m[len][sum] == 1)
				return true;
			else
				return false;
		}

		boolean flag;

		if (sum >= arr[len - 1])
			flag = (subsetMemoization(arr, len - 1, sum - arr[len - 1]) || subsetMemoization(arr, len - 1, sum));
		else
			flag = subsetMemoization(arr, len - 1, sum);

		if (flag == true)
			m[len][sum] = 1;
		else
			m[len][sum] = 0;
		return flag;
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

	public static void main(String[] args) {
		int[] arr = { 1, 2, 5, 3 };
		int S = 5;
		int N = 4;

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = -1;
			}
		}
		System.out.println("subsetRecursion:\t" + subsetRecursion(arr, N, S));
		System.out.println("subsetMemoization:\t" + subsetMemoization(arr, N, S));
		System.out.println("subsetDP:\t" + subsetDP(arr, N, S));
	}
}
