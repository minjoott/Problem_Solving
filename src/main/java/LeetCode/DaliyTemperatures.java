package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class DaliyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<Entry> stack = new ArrayDeque<>();

        for (int curr = 0; curr < temperatures.length; curr++) {
            while (!stack.isEmpty() && stack.peek().temperature < temperatures[curr]) {
                Entry past = stack.pop();
                answer[past.day] = curr - past.day;
            }
            stack.push(new Entry(temperatures[curr], curr));
        }

        return answer;
    }

    class Entry {
        int temperature;
        int day;

        Entry(int temperature, int day) {
            this.temperature = temperature;
            this.day = day;
        }
    }
}
