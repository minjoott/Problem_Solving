package LeetCode;

public class PermutationSequence {
    int cnt = 0;

    public String getPermutation(int n, int k) {
        return backtracking("", n, k);
    }

    String backtracking(String curr, int n, int k) {
        if (curr.length() == n) {
            if (++cnt == k) {
                return curr;
            }
            return "";
        }

        for (int i = 1; i <= n; i++) {
            if (curr.contains(i + "")) continue;

            String result = backtracking(curr + i, n, k);
            if (result != "")
                return result;
        }

        return "";
    }
}
