package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        backtracking(nums, new ArrayList<>(), answer);
        return answer;
    }

    void backtracking(int[] nums, List<Integer> curr, List<List<Integer>> answer) {
        if (curr.size() == nums.length) {
            answer.add(new ArrayList<>(curr));
            return;
        }

        for (int num : nums) {
            if (!curr.contains(num)) {
                curr.add(num);
                backtracking(nums, curr, answer);
                curr.remove(curr.size() - 1);
            }
        }
    }
}
