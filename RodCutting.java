public class RodCutting {
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static int[][] m = new int[100][100];
	static int[][] dp = new int[100][100];

	static int rodCutting(int[] len, int[] val, int L, int idx) {
		if (idx == 0 || L == 0) {
			return 0;
		}

		if (len[idx - 1] > L) {
			return rodCutting(len, val, L, idx - 1);
		} else {
			return max((val[idx - 1] + rodCutting(len, val, L - len[idx - 1], idx)), rodCutting(len, val, L, idx - 1));
		}
	}

	static int rodCuttingMemoization(int[] len, int[] val, int L, int idx) {
		if (idx == 0 || L == 0) {
			return 0;
		}

		if (m[L][idx] != -1) {
			return m[L][idx];
		}

		if (len[idx - 1] > L) {
			return m[L][idx] = rodCuttingMemoization(len, val, L, idx - 1);
		} else {
			return m[L][idx] = max((val[idx - 1] + rodCuttingMemoization(len, val, L - len[idx - 1], idx)),
					rodCuttingMemoization(len, val, L, idx - 1));
		}
	}

	static int rodCuttingDP(int[] len, int[] val, int L, int idx) {
		for (int i = 0; i <= idx; i++)
			dp[i][0] = 0;
		for (int j = 0; j <= L; j++)
			dp[0][j] = 0;

		for (int i = 1; i <= idx; i++) {
			for (int j = 1; j <= L; j++) {
				if (len[i - 1] > j)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = max(val[i - 1] + dp[i][j - len[i - 1]], dp[i - 1][j]);
			}
		}

		return dp[idx][L];
	}

	public static void main(String[] args) {
		int[] len = { 1, 2, 3, 4 };
		int[] val = { 1, 2, 8, 8 };
		int L = 6;
		System.out.println("rodCutting: " + rodCutting(len, val, L, 4));
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m[i][j] = -1;
			}
		}
		System.out.println("rodCutting using memoization: " + rodCuttingMemoization(len, val, L, 4));
		System.out.println("rodCutting using DP: " + rodCuttingDP(len, val, L, 4));
	}
}
