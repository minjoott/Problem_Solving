package main.java.Programmers;

import java.util.*;

class 광물캐기 {
    public int solution(int[] picks, String[] minerals) {

        int[][] power = {
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };

        // 광물 5개씩 묶기
        int pickCount = picks[0] + picks[1] + picks[2];
        int mineralLimit = Math.min(minerals.length, pickCount * 5);
        List<Chunk> chunks = new ArrayList<>();

        for (int i = 0; i < mineralLimit; i += 5) {
            int dia = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5 && j < mineralLimit; j++) {
                if (minerals[j].equals("diamond")) ++dia;
                else if (minerals[j].equals("iron")) ++iron;
                else ++stone;
            }
            chunks.add(new Chunk(dia, iron, stone));
        }

        chunks.sort((c1, c2) -> c2.getWeight() - c1.getWeight());

        int answer = 0;
        for (Chunk chunk : chunks) {
            for (int i = 0; i < 3; i++) {
                if (picks[i] == 0) continue;

                answer += power[i][0] * chunk.dia + power[i][1] * chunk.iron + power[i][2] * chunk.stone;
                --picks[i];
                break;
            }
        }

        return answer;
    }

    class Chunk {
        int dia;
        int iron;
        int stone;

        Chunk(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }

        int getWeight() {
            return dia * 25 + iron * 5 + stone;
        }
    }
}