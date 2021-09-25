public class MatrixChainMultiplication {

    static int[][] m = new int[100][100];

    static int matrixChainMultiplication(int array[], int i, int j) {
        if (j <= i)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = matrixChainMultiplication(array, i, k) + matrixChainMultiplication(array, k + 1, j)
                    + array[i - 1] * array[k] * array[j];
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    static int matrixChainMultiplicationMemoization(int array[], int i, int j) {
        if (j <= i)
            return 0;

        if (m[i][j] != -1)
            return m[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = matrixChainMultiplicationMemoization(array, i, k)
                    + matrixChainMultiplicationMemoization(array, k + 1, j) + array[i - 1] * array[k] * array[j];
            if (temp < min) {
                min = temp;
            }
        }
        return m[i][j] = min;
    }

    public static void main(String[] args) {
        int array[] = { 10, 20, 30, 40, 30 };
        System.out.println("Matrix Chain Multiplication:\t" + matrixChainMultiplication(array, 1, 4));
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                m[i][j] = -1;
            }
        }
        System.out.println(
                "Matrix Chain Multiplication using Memoization:\t" + matrixChainMultiplicationMemoization(array, 1, 4));
    }
}
