public class ZeroOneKnapsack {
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static int knapsackRecursive(int[] wt, int[] val, int W, int n) {
		if (W == 0 || n == 0)
			return 0;
		if (wt[n - 1] <= W) {
			return max(val[n - 1] + knapsackRecursive(wt, val, W - wt[n - 1], n - 1),
					knapsackRecursive(wt, val, W, n - 1));
		} else
			return knapsackRecursive(wt, val, W, n - 1);
	}

	public static void main(String[] args) {
		int[] wt = { 1, 3, 4 };
		int[] val = { 1, 8, 3 };
		int W = 5;
		System.out.println("0/1 Knapsack using recurtion:\t" + knapsackRecursive(wt, val, W, 3));
	}
}
