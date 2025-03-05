package main.java.LeetCode;

import java.util.*;

class PathWithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // 그래프 변환
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(new Edge(edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Edge(edges[i][0], succProb[i]));
        }

        double[] maxProbability = new double[n];

        Queue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(start, 1));
        maxProbability[start] = 1;

        while (!pq.isEmpty()) {
            Entry curr = pq.remove();
            if (curr.maxProbability < maxProbability[curr.node])
                continue;

            List<Edge> edgeList = graph.get(curr.node);
            if (edgeList != null) {
                for (Edge edge : edgeList) {
                    double newProbability = curr.maxProbability * edge.probability;
                    if (newProbability > maxProbability[edge.to]) {
                        pq.add(new Entry(edge.to, newProbability));
                        maxProbability[edge.to] = newProbability;
                    }
                }
            }

        }

        if (maxProbability[end] == 0)
            return 0;
        else
            return maxProbability[end];
    }

    class Edge {
        int to;
        double probability;

        Edge(int to, double probability) {
            this.to = to;
            this.probability = probability;
        }
    }

    class Entry implements Comparable<Entry> {
        int node;
        double maxProbability;

        Entry(int node, double maxProbability) {
            this.node = node;
            this.maxProbability = maxProbability;
        }

        @Override
        public int compareTo(Entry e) {
            return Double.compare(e.maxProbability, this.maxProbability);
        }
    }
}