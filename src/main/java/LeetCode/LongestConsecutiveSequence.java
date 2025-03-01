package main.java.LeetCode;

import java.util.HashSet;
import java.util.Set;

class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {

        int answer = 0;
        Set<Integer> hashSet = new HashSet<>();

        for (int num : nums) {
            hashSet.add(num);
        }

        for (int num : hashSet) {
            int count = 1;
            if (!hashSet.contains(num - 1)) {
                int nextNum = num;
                while (true) {
                    if (hashSet.contains(++nextNum)) {
                        count++;
                    }
                    else {
                        break;
                    }
                }
            }
            if (count > answer) answer = count;
        }

        return answer;
    }
}
