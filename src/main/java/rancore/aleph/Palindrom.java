package rancore.aleph;

/**
 * Die Klasse Palindrom sucht den längsten palindromischen Teilstring in einem gegebenen String.
 * <p>
 * Das Ziel ist es, den längsten Teilstring zu finden, der von vorne nach hinten und von hinten nach vorne identisch ist.
 * Die Lösung verwendet dynamisches Programmieren, um alle möglichen Teilstrings zu überprüfen und den längsten zu finden.
 */
public class Palindrom {

    /**
     * Findet den längsten palindromischen Teilstring.
     *
     * @param s Der Eingabestring.
     * @return Der längste palindromische Teilstring.
     */
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        int maxLength = 1;
        int start = 0;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        for (int length = 3; length <= n; length++) {
            for (int i = 0; i < n - length + 1; i++) {
                int j = i + length - 1;
                if (dp[i][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (length > maxLength) {
                        start = i;
                        maxLength = length;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Längster palindromischer Teilstring: " + longestPalindrome(s));
    }
}
