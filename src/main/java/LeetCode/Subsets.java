package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        backtracking(nums, 0, new ArrayList<>(), answer);
        return answer;
    }

    void backtracking(int[] nums, int startIdx, List<Integer> curr, List<List<Integer>> answer) {
        answer.add(new ArrayList<>(curr));

        for (int i = startIdx; i < nums.length; i++) {
            curr.add(nums[i]);
            backtracking(nums, i + 1, curr, answer);
            curr.remove(curr.size() - 1);
        }
    }
}
