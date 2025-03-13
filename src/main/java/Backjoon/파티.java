package main.java.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 파티 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        int X = Integer.valueOf(st.nextToken());

        // 단방향 가중치 그래프 생성
        Map<Integer, List<Edge>> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer path = new StringTokenizer(br.readLine());
            int from = Integer.valueOf(path.nextToken());
            int to = Integer.valueOf(path.nextToken());
            int time = Integer.valueOf(path.nextToken());

            map.get(from).add(new Edge(to, time));
        }

        int[] totalTimes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int[] totalTimeGo = new int[N + 1];
            Arrays.fill(totalTimeGo, Integer.MAX_VALUE);
            Queue<Entry> pq = new PriorityQueue<>();

            pq.add(new Entry(i, 0));
            totalTimeGo[i] = 0;

            // 파티에 갔다가,
            while (!pq.isEmpty()) {
                Entry curr = pq.poll();
                if (curr.totalTime > totalTimeGo[curr.node]) continue;

                for (Edge path : map.get(curr.node)) {
                    int newTotalTime = curr.totalTime + path.time;
                    if (newTotalTime < totalTimeGo[path.to]) {
                        totalTimeGo[path.to] = newTotalTime;

                        if (path.to != X) {
                            pq.add(new Entry(path.to, newTotalTime));
                        }
                    }
                }
            }

            // 집으로 돌아오기
            int[] totalTimeBack = new int[N + 1];
            Arrays.fill(totalTimeBack, Integer.MAX_VALUE);

            pq.add(new Entry(X, 0));
            totalTimeBack[X] = 0;

            while (!pq.isEmpty()) {
                Entry curr = pq.poll();
                if (curr.totalTime > totalTimeBack[curr.node]) continue;

                for (Edge path : map.get(curr.node)) {
                    int newTotalTime = curr.totalTime + path.time;
                    if (newTotalTime < totalTimeBack[path.to]) {
                        totalTimeBack[path.to] = newTotalTime;

                        if (path.to != i) {
                            pq.add(new Entry(path.to, newTotalTime));
                        }
                    }
                }
            }

            totalTimes[i] = totalTimeGo[X] + totalTimeBack[i];
        }

        int answer = -1;
        for (int totalTime : totalTimes) {
            answer = Math.max(totalTime, answer);
        }
        System.out.println(answer);
        return;
    }

    static class Edge {
        int to;
        int time;

        Edge (int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    static class Entry implements Comparable<Entry> {
        int node;
        int totalTime;

        Entry (int node, int totalTime) {
            this.node = node;
            this.totalTime = totalTime;
        }

        @Override
        public int compareTo(Entry e) {
            return this.totalTime - e.totalTime;
        }
    }
}
