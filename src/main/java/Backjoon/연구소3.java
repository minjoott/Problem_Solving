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

        int minTime = Integer.MAX_VALUE;  // answer

        int[][] map = new int[N][N];
        List<Position> virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());

                if (map[i][j] == 2) {  // 바이러스
                    virusList.add(new Position(j, i));
                }
            }
        }

        List<List<Position>> activeCombination = new ArrayList<>();
        backtracking(0, new ArrayList<>(), activeCombination, virusList, M);

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        for (List<Position> activeVirusList : activeCombination) {
            Queue<Entry> queue = new LinkedList<>();
            int[][] activeMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    activeMap[i][j] = map[i][j];
                }
            }

            // 활성 바이러스 배치
            for (Position activeVirus : activeVirusList) {
                activeMap[activeVirus.y][activeVirus.x] = 3;
                queue.add(new Entry(new Position(activeVirus.x, activeVirus.y), 0));
            }

            Entry currVirus = null;
            while (!queue.isEmpty()) {
                currVirus = queue.remove();

                for (int d = 0; d < 4; d++) {
                    int nextX = currVirus.position.x + dx[d];
                    int nextY = currVirus.position.y + dy[d];

                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

                    if (activeMap[nextY][nextX] == 0) {
                        activeMap[nextY][nextX] = 3;
                        queue.add(new Entry(new Position(nextX, nextY), currVirus.time + 1));
                    } else if (activeMap[nextY][nextX] == 2) {
                        activeMap[nextY][nextX] = 3;
                        queue.add(new Entry(new Position(nextX, nextY), currVirus.time));
                    }

                }
            }

            boolean failActive = false;
            outerLoop:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (activeMap[i][j] == 0) {
                        failActive = true;
                        break outerLoop;
                    }
                }
            }

            if (!failActive) {
                minTime = Math.min(currVirus.time, minTime);
            }
        }

        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(minTime);
        }
        return;
    }

    static void backtracking(int start, List<Position> currActive, List<List<Position>> activeCombination, List<Position> virusList, int M) {
        if (currActive.size() == M) {
            activeCombination.add(new ArrayList<>(currActive));
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            currActive.add(virusList.get(i));
            backtracking(i + 1, currActive, activeCombination, virusList, M);
            currActive.remove(currActive.size() - 1);
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
        int time;

        Entry(Position position, int time) {
            this.position = position;
            this.time = time;
        }
    }
}