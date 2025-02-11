package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<Entry> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new Entry(0, 0, 1));
        visited[0][0] = true;

        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

        while (!queue.isEmpty()) {
            Entry curr = queue.remove();

            if (curr.x == n - 1 && curr.y == n - 1) return curr.length;  // 도착지이면 정답 return

            for (int d = 0; d < 8; d++) {
                int nextX = curr.x + dx[d];
                int nextY = curr.y + dy[d];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextY][nextX]) continue;

                if (grid[nextY][nextX] == 0) {
                    queue.add(new Entry(nextX, nextY, curr.length + 1));
                    visited[nextY][nextX] = true;
                }
            }
        }

        return -1;
    }

    class Entry {
        int x;
        int y;
        int length;

        Entry(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
}
