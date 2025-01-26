package LeetCode;

import java.util.ArrayList;
import java.util.List;


class TwoSum_for {
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }
}

class TwoSum_recursion {
    public int[] twoSum(int[] nums, int target) {
        return backtracking(nums, target, new ArrayList<Integer>(), 0);
    }

    private int[] backtracking(int[] nums, int target, List<Integer> list, int startIdx) {
        if (list.size() == 2) {
            if (nums[list.get(0)] + nums[list.get(1)] == target) {
                return new int[]{list.get(0), list.get(1)};
            }
            return null;
        }

        for (int i = startIdx; i < nums.length; i++) {
            list.add(i);
            int[] result = backtracking(nums, target, list, i + 1);
            if (result != null) {  // 정답 발견 시
                return result;
            }
            list.remove(list.size() - 1);
        }

        return null;
    }
}