package main.java.Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        int[][] map = new int[N][N];
        List<Position> emptyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());

                if (map[i][j] == 0) emptyList.add(new Position(j, i));
            }
        }

        // 만약 활성 바이러스의 개수가 빈 칸의 개수보다 크면, 즉시 0 출력
        if (M > emptyList.size()) {
            System.out.println(0);
            return;
        }

        int minActiveCount = Integer.MAX_VALUE;  // answer

        List<List<Position>> emptyCombinations = new ArrayList<>();
        backtracking(0, new ArrayList<>(), emptyCombinations, emptyList, M);  // 활성 바이러스 M개를 놓을 빈 칸 조합 구하기

        for (List<Position> emptyListToActive : emptyCombinations) {
            // map 복사 && 현재 빈칸 조합에 활성 바이러스 M개 두고, enqueue(활성 바이러스)
            int[][] currMap = new int[N][N];
            Queue<Entry> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    currMap[i][j] = map[i][j];

                    // 해당 빈 칸에 활성 바이러스 두기 && enqueue(활성 바이러스)
                    for (Position virus : emptyListToActive) {
                        if (j == virus.x && i == virus.y) {
                            currMap[i][j] = -1;
                            queue.add(new Entry(virus, 0));
                            visited[i][j] = true;
                        }
                    }
                }
            }

            int[] dy = {1, 0, -1, 0};
            int[] dx = {0, 1, 0, -1};
            Entry currActiveVirus = null;
            // 활성 바이러스 전파시키기
            while (!queue.isEmpty()) {
                currActiveVirus = queue.remove();

                for (int d = 0; d < 4; d++) {
                    int nextX = currActiveVirus.position.x + dx[d];
                    int nextY = currActiveVirus.position.y + dy[d];

                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextY][nextX]) continue;

                    if (currMap[nextY][nextX] == 0 || currMap[nextY][nextX] == 2) {
                        queue.add(new Entry(new Position(nextX, nextY), currActiveVirus.count + 1));
                        visited[nextY][nextX] = true;
                        currMap[nextY][nextX] = -1;
                    }
                }
            }

            boolean failAllActive = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (currMap[i][j] == 0) failAllActive = true;
                }
            }
            if (!failAllActive) {  // 모든 빈 칸에 활성 바이러스 전파 성공했으면
                minActiveCount = Math.min(currActiveVirus.count, minActiveCount);
            }
        }

        if (minActiveCount == Integer.MAX_VALUE)  // 모든 빈 칸에 활성 바이러스 전파를 성공한 케이스가 없으면,
            System.out.println(-1);
        else
            System.out.println(minActiveCount);
    }

    static void backtracking(int startIndex, List<Position> currEmptyList, List<List<Position>> emptyCombinations, List<Position> emptyList, int M) {
        if (currEmptyList.size() == M) {
            emptyCombinations.add(new ArrayList<>(currEmptyList));
            return;
        }

        for (int i = startIndex; i < emptyList.size(); i++) {
            currEmptyList.add(emptyList.get(i));
            backtracking(i + 1, currEmptyList, emptyCombinations, emptyList, M);
            currEmptyList.remove(currEmptyList.size() - 1);
        }
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
        Position position;
        int count;

        Entry(Position position, int count) {
            this.position = position;
            this.count = count;
        }
    }
}