package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소 {
    static int maxSafe = 0; // answer

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        int[][] map = new int[N][M];
        List<Point> emptyList = new ArrayList<>();
        List<Point> virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());

                if (map[i][j] == 0) {
                    emptyList.add(new Point(j, i));
                }
                else if (map[i][j] == 2) {
                    virusList.add(new Point(j, i));
                }
            }
        }

        List<List<Point>> wallCombinationList = new ArrayList<>();
        backtracking(0, new ArrayList<>(), wallCombinationList, emptyList);

        for (List<Point> wallList : wallCombinationList) {  // wallList: 3개의 벽을 세울 위치
            // 바이러스(2)가 빈 칸(0)을 따라 전파하는데, wall(1)을 만나면 pass

            int[][] currMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    currMap[i][j] = map[i][j];

                    for (Point wall : wallList) {  //벽 세우기
                        if (j == wall.x && i == wall.y) {
                            currMap[i][j] = 1;
                        }
                    }
                }
            }

            Queue<Point> queue = new LinkedList<>();  // 바이러스가 전파된 칸
            boolean[][] visited = new boolean[N][M];
            // 모든 바이러스 enqueue
            for (Point virus : virusList) {
                queue.add(virus);
                visited[virus.y][virus.x] = true;
            }

            int[] dy = {1, 0, -1, 0};
            int[] dx = {0, 1, 0, -1};

            // 바이러스 전파시키기
            while (!queue.isEmpty()) {
                Point curr = queue.remove();

                for (int d = 0; d < 4; d++) {
                    int nextX = curr.x + dx[d];
                    int nextY = curr.y + dy[d];

                    if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || visited[nextY][nextX]) continue;

                    if (currMap[nextY][nextX] == 0) {  // 바이러스를 전파할 수 있는 빈 칸(0)이면
                        queue.add(new Point(nextX, nextY));
                        visited[nextY][nextX] = true;
                        currMap[nextY][nextX] = 3;  // 전파된 칸
                    }
                }
            }

            // 안전 영역(0)의 개수 구하기
            int safe = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (currMap[i][j] == 0) {
                        safe++;
                    }
                }
            }

            maxSafe = Math.max(safe, maxSafe);
        }
        System.out.println(maxSafe);
    }

    static void backtracking(int startIndex, List<Point> currWallList, List<List<Point>> wallCombinationList, List<Point> emptyList) {
        if (currWallList.size() == 3) {
            wallCombinationList.add(new ArrayList<>(currWallList));
            return;
        }

        for (int i = startIndex; i < emptyList.size(); i++) {
            currWallList.add(emptyList.get(i));
            backtracking(i + 1, currWallList, wallCombinationList, emptyList);
            currWallList.remove(currWallList.size() - 1);
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
