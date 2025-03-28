package main.java.Programmers;

import java.util.*;

class 뒤에있는큰수찾기 {
    public int[] solution(int[] numbers) {
        int N = numbers.length;

        int[] answer = new int[N];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < numbers[i]) {
                answer[stack.pop()[1]] = numbers[i];
            }
            stack.push(new int[]{numbers[i], i});
        }

        while (!stack.isEmpty()) {
            answer[stack.pop()[1]] = -1;
        }

        return answer;
    }
}
