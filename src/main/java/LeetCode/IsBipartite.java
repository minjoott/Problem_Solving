package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

class IsBipartite_bfs {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] set = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                visited[i] = true;  // set A 설정
                queue.add(i);

                while (!queue.isEmpty()) {
                    int curr = queue.remove();

                    for (int next : graph[curr]) {
                        if (!visited[next]) {  // 아직 방문하지 않은 node이면
                            set[next] = !set[curr];
                            visited[next] = true;
                            queue.add(next);
                        }
                        else {  // 이미 방문한 node이면
                            if (set[curr] == set[next]) return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}

class IsBipartite_dfs {
    static boolean[] visited;
    static boolean[] set;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        set = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (!dfs(i, graph)) return false;
            }
        }

        return true;
    }

    boolean dfs(int curr, int[][] graph) {
        visited[curr] = true;

        for (int next : graph[curr]) {
            if (!visited[next]) {
                set[next] = !set[curr];
                visited[next] = true;
                boolean result = dfs(next, graph);
                if (!result) return false;
            }
            else {
                if (set[curr] == set[next]) return false;
            }
        }

        return true;
    }
}