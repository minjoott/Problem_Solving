package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        backtracking(n, k, 1, new ArrayList<>(), answer);
        return answer;
    }

    void backtracking(int n, int k, int startNum, List<Integer> curr, List<List<Integer>> answer) {
        if (curr.size() == k) {
            answer.add(new ArrayList<>(curr));
            return;
        }

        for (int i = startNum; i <= n; i++) {
            curr.add(i);
            backtracking(n, k, i + 1, curr, answer);
            curr.remove(curr.size() - 1);
        }
    }
}
