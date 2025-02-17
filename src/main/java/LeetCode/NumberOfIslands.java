package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

class NumberOfIslands_bfs {
    public int numIslands(char[][] grid) {
        int count = 0;  // answer
        Queue<Entry> queue = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    visited[i][j] = true;
                    queue.add(new Entry(j, i));

                    while (!queue.isEmpty()) {
                        Entry curr = queue.remove();

                        for (int d = 0; d < 4; d++) {
                            int nextX = curr.x + dx[d];
                            int nextY = curr.y + dy[d];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visited[nextY][nextX]) continue;

                            if (grid[nextY][nextX] == '1') {
                                visited[nextY][nextX] = true;
                                queue.add(new Entry(nextX, nextY));
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    class Entry {
        int x;
        int y;

        Entry(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

class NumberOfIslands_dfs {
    int m, n;
    boolean[][] visited;
    public int numIslands(char[][] grid) {
        int count = 0;  // answer
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs(j, i, grid);
                }
            }
        }

        return count;
    }

    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};

    void dfs(int x, int y, char[][] grid) {
        visited[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visited[nextY][nextX]) continue;

            if (grid[nextY][nextX] == '1') {
                dfs(nextX, nextY, grid);
            }
        }
    }
}

