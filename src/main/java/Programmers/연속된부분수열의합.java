package main.java.Programmers;

class 연속된부분수열의합 {
    public int[] solution(int[] sequence, int k) {

        int start = 0;
        int end = 0;
        int sum = sequence[0];
        int minLen = 1000001;
        int[] answer = {-1, -1};

        while (end < sequence.length) {
            if (sum < k) {
                if (end + 1 < sequence.length) {
                    end++;
                    sum += sequence[end];
                }
                else {
                    break;
                }
            }
            else if (sum > k) {
                sum -= sequence[start];
                start++;
            }
            else {  // sum == k
                int len = end - start;
                if (len < minLen) {
                    minLen = len;
                    answer[0] = start;
                    answer[1] = end;
                }

                sum -= sequence[start];
                start++;
            }
        }

        return answer;
    }
}
