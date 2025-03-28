package main.java.Programmers;

import java.util.*;

class 호텔대실 {
    public int solution(String[][] book_time) {

        int N = book_time.length;

        // book_time을 대기 시작 시각이 빠른 순으로 정렬
        int[][] bookTime = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] startStr = book_time[i][0].split(":");
            bookTime[i][0] = Integer.parseInt(startStr[0]) * 60 + Integer.parseInt(startStr[1]);

            String[] endStr = book_time[i][1].split(":");
            bookTime[i][1] = Integer.parseInt(endStr[0]) * 60 + Integer.parseInt(endStr[1]);
        }
        Arrays.sort(bookTime, (b1, b2) -> b1[0] - b2[0]);

        // bookTime 순회하며 처리
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Boolean> rooms = new ArrayList<>();
        for (int[] times : bookTime) {
            pq.add(times[1] + 10);

            if (!pq.isEmpty() && pq.peek() <= times[0]) {
                pq.poll();
            }
            else {
                rooms.add(true);
            }
        }

        return rooms.size();
    }
}
