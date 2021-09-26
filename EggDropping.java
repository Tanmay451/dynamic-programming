public class EggDropping {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int eggDropping(int numberOfEgg, int numberOfFloors) {
        if (numberOfFloors <= 1) {
            return numberOfFloors;
        }

        if (numberOfEgg == 1) {
            return numberOfFloors;
        }

        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= numberOfFloors; k++) {
            int temp = 1 + max(eggDropping(numberOfEgg - 1, k - 1), eggDropping(numberOfEgg, numberOfFloors - k));
            if (min > temp) {
                min = temp;
            }
        }

        return min;

    }

    public static void main(String[] args) {
        System.out.println("Egg Dropping:\t" + eggDropping(2, 10));
    }
}
