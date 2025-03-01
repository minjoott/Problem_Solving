package LeetCode;

import java.util.*;


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

class TwoSum_sortAndTwoPointer {

    public int[] twoSum(int[] nums, int target) {

        Number[] numbers = new Number[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numbers[i] = new Number(nums[i], i);
        }

        Arrays.sort(numbers, (n1, n2) -> n1.number - n2.number);

        int front = 0;
        int rear = nums.length - 1;
        while (front < rear) {
            int sum = numbers[front].number + numbers[rear].number;
            if (sum < target)
                front++;
            else if (sum > target)
                rear--;
            else
                return new int[]{numbers[front].index, numbers[rear].index};

        }
        return new int[]{-1, -1};
    }

    class Number {
        int number;
        int index;

        Number(int number, int index) {
            this.number = number;
            this.index = index;
        }
    }
}

class TwoSum_hashMap {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> hashtable = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(nums[i])) {
                result[0] = i;
                result[1] = hashtable.get(nums[i]);
            }
            else {
                hashtable.put(target - nums[i], i);
            }
        }

        return result;
    }
}