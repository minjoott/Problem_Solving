package main.java.Programmers;

import java.util.*;

class 과제진행하기 {
    public String[] solution(String[][] oldPlans) {

        int N = oldPlans.length;
        Plan[] plans = new Plan[N];
        for (int i = 0; i < N; i++) {
            plans[i] = new Plan();

            plans[i].name = oldPlans[i][0];

            String[] numsStr = oldPlans[i][1].split(":");
            plans[i].start = Integer.parseInt(numsStr[0]) * 60 + Integer.parseInt(numsStr[1]);

            plans[i].playtime = Integer.parseInt(oldPlans[i][2]);
        }
        Arrays.sort(plans, (p1, p2) -> (p1.start - p2.start));

        int curTime = plans[0].start;
        int curIdx = 0;
        int completedCnt = 0;
        String[] answer = new String[N];
        Stack<Entry> stack = new Stack<>();
        while (completedCnt < N) {
            if (curIdx + 1 < N) {
                int endTime = curTime + plans[curIdx].playtime;
                if (endTime > plans[curIdx + 1].start) {
                    stack.push(new Entry(plans[curIdx].name, endTime - plans[curIdx + 1].start));
                    curTime = plans[curIdx + 1].start;
                    curIdx++;
                }
                else {
                    answer[completedCnt++] = plans[curIdx].name;
                    curTime = endTime;

                    if (endTime < plans[curIdx + 1].start) {
                        while (!stack.isEmpty()) {
                            Entry curr = stack.pop();
                            int endTime2 = curTime + curr.playtime;
                            if (endTime2 > plans[curIdx + 1].start) {
                                stack.push(new Entry(curr.name, endTime2 - plans[curIdx + 1].start));
                                break;
                            }

                            curTime = endTime2;
                            answer[completedCnt++] = curr.name;
                        }
                    }

                    curTime = plans[curIdx + 1].start;
                    curIdx++;
                }
            }
            else {
                curTime += plans[curIdx].playtime;
                answer[completedCnt++] = plans[curIdx].name;

                while (!stack.isEmpty()) {
                    Entry remain = stack.pop();
                    answer[completedCnt++] = remain.name;
                }
            }
        }

        return answer;
    }

    class Plan {
        String name;
        int start;
        int playtime;
    }

    class Entry {
        String name;
        int playtime;

        Entry (String name, int playtime) {
            this.name = name;
            this.playtime = playtime;
        }
    }
}