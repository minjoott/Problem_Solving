package main.java.Programmers;

import java.util.*;

class 등산코스정하기 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 무방향 가중치 그래프로 변환
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] path : paths) {
            graph.get(path[0]).add(new Edge(path[1], path[2]));
            graph.get(path[1]).add(new Edge(path[0], path[2]));
        }

        // 산봉우리 집합에 저장
        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        // 다익스트라 알고리즘
        int[] minIntensity = new int[n + 1];
        Arrays.fill(minIntensity, 10000001);
        Queue<Entry> pq = new PriorityQueue<>();

        for (int gate : gates) {
            pq.add(new Entry(gate, 0));
            minIntensity[gate] = 0;
        }

        while (!pq.isEmpty()) {
            Entry curr = pq.poll();
            // curr의 minIntensity가 minIntensity[node]보다 크면 PASS => minIntensity 경로로 나아가야 하니까 !
            if (curr.minIntensity > minIntensity[curr.node]) continue;

            // 다음 node까지의 newIntensity 구해서, minIntensity[다음 node]보다 작으면 enqueue && minIntensity 갱신
            for (Edge next : graph.get(curr.node)) {
                int newIntensity = Math.max(curr.minIntensity, next.time);
                if (newIntensity < minIntensity[next.to]) {
                    minIntensity[next.to] = newIntensity;

                    // 만약 다음 node가 산봉우리이면, enqueue는 생략해야 함 !
                    if (!summitSet.contains(next.to)) {
                        pq.add(new Entry(next.to, newIntensity));
                    }
                }
            }
        }

        int[] answer = {0, 10000001};
        for (int summit : summitSet) {
            if (minIntensity[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = minIntensity[summit];
            }
            else if (minIntensity[summit] == answer[1]) {
                answer[0] = Math.min(summit, answer[0]);
            }
        }

        return answer;
    }

    class Edge {
        int to;
        int time;

        Edge (int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    class Entry implements Comparable<Entry> {
        int node;
        int minIntensity;

        Entry (int node, int minIntensity) {
            this.node = node;
            this.minIntensity = minIntensity;
        }

        @Override
        public int compareTo(Entry e) {
            return this.minIntensity - e.minIntensity;
        }
    }
}
