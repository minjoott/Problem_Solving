package LeetCode;

import java.util.Stack;

public class LongestValidParantheses {
    public int longestValidParentheses(String s) {
        int longest = 0;  // answer
        Stack<Entry> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peek().c == '(')  {
                    stack.pop();
                    int length = (stack.isEmpty()) ? i + 1 : i - stack.peek().index;
                    longest = Math.max(length, longest);
                }
                else {
                    stack.push(new Entry(s.charAt(i), i));
                }
            }
            else {
                stack.push(new Entry(s.charAt(i), i));
            }
        }

        return longest;
    }

    class Entry {
        char c;
        int index;

        Entry(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }
}
