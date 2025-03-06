package main.java.Programmers;

import java.util.Arrays;

class 요격시스템 {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (t1, t2) -> t1[1] - t2[1]);

        int currentTime = 0;
        int targetIndex = 0;
        while (targetIndex < targets.length) {
            currentTime = targets[targetIndex][1];
            targetIndex++;
            while (targetIndex < targets.length && targets[targetIndex][0] < currentTime) {
                targetIndex++;
            }
            answer++;
        }

        return answer;
    }
}
