/**
 * KMP
 */
public class KMP {

    private void computeLps(String pattern, int[] lps) {
        int i = 1;
        int len = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = ++len;
                i++;
            } else {
                if (len != 0) {
                    // This is a catch. Think about the example "ababe......ababc," where i is the
                    // index of "c," and len==4. The longest prefix and suffix are "abab" and
                    // "ababe" and "ababc," which are not equal, are created when pat[i]!=pat[len].
                    // Consequently, we are unable to increase the length of the LP based on the
                    // present LP "abab" with len==4. We might want to increase it according to the
                    // longest prefix suffix with length len==4, which is lps of "abab" by
                    // definition. Therefore, we changed the lps to "ab" and set len to lps[len-1],
                    // which is 2. Due to the while loop, which is also the reason why we did not
                    // increment I here, check pat[i]==pat[len] once again. If no lps ends with
                    // pat[i] were found or if an lps ends with pat[i] was found, the iteration of I
                    // would continue until len==0.
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    int string_matching(String pattern, String target) {
        int N = target.length();
        int M = pattern.length();
        int[] lps = new int[M];
        computeLps(pattern, lps);

        int i = 0, j = 0;
        while (i < N) {
            if (target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
            if (j == M) {
                return i - j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String target = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        KMP k = new KMP();
        System.out.println(k.string_matching(pattern, target));
    }
}