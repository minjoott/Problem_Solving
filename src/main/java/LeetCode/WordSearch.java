package LeetCode;

public class WordSearch {
    int[] dy = {1, 0, -1, 0};
    int[] dx = {0, 1, 0, -1};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (backtracking(i, j, 1, visited, board, word))
                        return true;
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    boolean backtracking(int y, int x, int searchIdx, boolean[][] visited, char[][] board, String word) {
        // base case
        if (searchIdx == word.length()) return true;

        for (int d = 0; d < 4; d++) {
            int nextY = y + dy[d];
            int nextX = x + dx[d];

            int m = board.length;
            int n = board[0].length;
            if (nextY < 0 || nextY >= m || nextX < 0 || nextX >= n || visited[nextY][nextX]) continue;

            if (board[nextY][nextX] == word.charAt(searchIdx)) {
                visited[nextY][nextX] = true;
                if (backtracking(nextY, nextX, searchIdx + 1, visited, board, word)) return true;
                visited[nextY][nextX] = false;
            }
        }
        return false;
    }
}
