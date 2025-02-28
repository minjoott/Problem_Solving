package main.java.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        char[][] board = new char[N][M];
        Position R = null;
        Position B = null;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);

                if (board[i][j] == 'R') {
                    R = new Position(j, i);
                }
                else if (board[i][j] == 'B') {
                    B = new Position(j, i);
                }
            }
        }

        Entry start = new Entry(R, B, 0);

        Queue<Entry> queue = new LinkedList<>();
        queue.add(start);

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Entry curr = queue.remove();

            if (curr.count > 10) {
                System.out.println(0);
                return;
            }

            for (int d = 0; d < 4; d++) {
                // 파란 구슬 굴리기
                int countB = 0;
                boolean isGoalB = false;
                int nextBX = curr.B.x + dx[d];
                int nextBY = curr.B.y + dy[d];
                while (board[nextBY][nextBX] != '#') {
                    nextBX += dx[d];
                    nextBY += dy[d];

                    countB++;

                    if (board[nextBY][nextBX] == 'O') {
                        isGoalB = true;
                        break;
                    }
                }
                nextBX -= dx[d];
                nextBY -= dy[d];

                if (isGoalB) continue;

                // 빨간 구슬 굴리기
                int countR = 0;
                boolean isGoalR = false;
                int nextRX = curr.R.x + dx[d];
                int nextRY = curr.R.y + dy[d];
                while (board[nextRY][nextRX] != '#') {
                    nextRX += dx[d];
                    nextRY += dy[d];

                    countR++;

                    if (board[nextRY][nextRX] == 'O') {
                        isGoalR = true;
                        break;
                    }
                }
                nextRX -= dx[d];
                nextRY -= dy[d];

                if (isGoalR && !isGoalB) {
                    System.out.println(1);
                    return;
                }
                else if (!isGoalR && !isGoalB) {
                    if (nextRX == nextBX && nextRY == nextBY) {
                        if (countR > countB) {
                            nextRX -= dx[d];
                            nextRY -= dy[d];
                        }
                        else {
                            nextBX -= dx[d];
                            nextBY -= dy[d];
                        }
                    }

                    queue.add(new Entry(new Position(nextRX, nextRY), new Position(nextBX, nextBY), curr.count + 1));
                }
            }
        }

        System.out.println(0);
        return;
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Entry {
        Position R;
        Position B;
        int count;

        Entry(Position R, Position B, int count) {
            this.R = R;
            this.B = B;
            this.count = count;
        }
    }
}
