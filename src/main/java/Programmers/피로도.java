package Programmers;

class 피로도 {
    static int ans = 0;

    public int solution(int k, int[][] dungeons) {
        backtracking(new boolean[dungeons.length], 0, k, dungeons);
        return ans;
    }

    void backtracking(boolean[] visited, int curr, int k, int[][] dungeons) {
        if (curr > ans)
            ans = curr;

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] || k < dungeons[i][0]) continue;

            visited[i] = true;
            backtracking(visited, curr + 1, k - dungeons[i][1], dungeons);
            visited[i] = false;
        }
    }
}
