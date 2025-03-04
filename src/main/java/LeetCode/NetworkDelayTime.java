package main.java.LeetCode;

import java.util.*;

class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // times -> 그래프 변환
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new Edge(time[1], time[2]));
        }

        int[] timeSum = new int[n + 1];
        Arrays.fill(timeSum, Integer.MAX_VALUE);

        Queue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(k, 0));
        timeSum[k] = 0;

        while (!pq.isEmpty()) {
            Entry curr = pq.remove();
            if (curr.timeSum > timeSum[curr.node]) continue;

            for (Edge edge : graph.get(curr.node)) {
                int newTimeSum = curr.timeSum + edge.time;
                if (newTimeSum < timeSum[edge.to]) {
                    pq.add(new Entry(edge.to, newTimeSum));
                    timeSum[edge.to] = newTimeSum;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (timeSum[i] == Integer.MAX_VALUE) return -1;

            answer = Math.max(timeSum[i], answer);
        }

        return answer;
    }

    class Edge {
        int to;
        int time;

        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    class Entry implements Comparable<Entry> {
        int node;
        int timeSum;

        Entry(int node, int timeSum) {
            this.node = node;
            this.timeSum = timeSum;
        }

        @Override
        public int compareTo(Entry e) {
            return this.timeSum - e.timeSum;
        }
    }
}
