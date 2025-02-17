package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0)
            return false;

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {  // 여는 괄호
                stack.push(c);
            }
            else {  // 닫는 괄호
                if (stack.isEmpty()) {
                    return false;
                }
                else {
                    char topC = stack.pop();
                    if ((c == ')' && topC == '(') || (c == '}' && topC == '{') || (c == ']' && topC == '['))
                        continue;
                    else
                        return false;
                }
            }
        }

        if (stack.isEmpty())
            return true;
        else
            return false;
    }
}
