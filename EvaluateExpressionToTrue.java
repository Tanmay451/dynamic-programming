public class EvaluateExpressionToTrue {

    static int evaluateExpressionToTrue(String str, int i, int j, boolean isTrue) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (isTrue) {
                if (str.charAt(i) == 'T') {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (str.charAt(i) == 'F') {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        int count = 0;
        for (int k = i + 1; k < j; k += 2) {
            int lt, lf, rt, rf;
            lt = evaluateExpressionToTrue(str, i, k - 1, true);
            lf = evaluateExpressionToTrue(str, i, k - 1, false);
            rt = evaluateExpressionToTrue(str, k + 1, j, true);
            rf = evaluateExpressionToTrue(str, k + 1, j, false);

            if (str.charAt(k) == '&') {
                if (isTrue) {
                    count += (lt * rt);
                } else {
                    count += (lt * rf) + (lf * rt) + (lf * rf);
                }
            }

            if (str.charAt(k) == '|') {
                if (isTrue) {
                    count += (lt * rt) + (lt * rf) + (lf * rt);
                } else {
                    count += (lf * rf);
                }
            }

            if (str.charAt(k) == '^') {
                if (isTrue) {
                    count += (lt * rf) + (lf * rt);
                } else {
                    count += (lt * rt) + (lf * rf);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String str = "T|T&F^T";
        System.out.println("Number of ways Expression can be evaluated to true:\t"
                + evaluateExpressionToTrue(str, 0, str.length() - 1, true));
    }
}
