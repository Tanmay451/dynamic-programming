public class ScrambledString {

    static boolean scrambledString(String str1, String str2) {
        int len = str1.length();

        if (str1.equals(str2)) {
            return true;
        }

        if (len <= 1) {
            return false;
        }

        for (int k = 1; k < len; k++) {
            if (scrambledString(str1.substring(0, k), str2.substring(0, k))
                    && scrambledString(str1.substring(k, len), str2.substring(k, len))) {
                return true;
            }
            if (scrambledString(str1.substring(0, k), str2.substring(len - k, len))
                    && scrambledString(str1.substring(k, len), str2.substring(0, len - k))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        String s1 = "eatrg", s2 = "great";
        System.out.println("Scrambled String:\t" + scrambledString(s1, s2));
    }
}
