package LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class keysAndRooms_bfs {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[rooms.size()];

        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int i = queue.remove();
            for (int key : rooms.get(i)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.add(key);
                }
            }
        }

        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }
}

class KeysAndRooms_dfs {
    boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];

        dfs(0, rooms);

        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    void dfs(int i, List<List<Integer>> rooms) {
        visited[i] = true;
        for (int key : rooms.get(i)) {
            if (!visited[key]) {
                dfs(key, rooms);
            }
        }
    }
}


