package main.java.Programmers;

import java.util.*;

class 귤고르기 {
    public int solution(int k, int[] tangerines) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int tangerineSize : tangerines) {
            hashtable.put(tangerineSize, hashtable.getOrDefault(tangerineSize, 0) + 1);
        }

        List<Integer> sizeList = new ArrayList<>(hashtable.keySet());
        // 개수가 많은 크기대로 귤의 크기 종류 정렬
        sizeList.sort((size1, size2) -> (hashtable.get(size2) - hashtable.get(size1)));

        int answer = 0;  // 크기가 서로 다른 종류의 수의 최솟값
        for (int tangerineSize : sizeList) {
            if (k <= 0) break;

            k -= hashtable.get(tangerineSize);
            answer++;
        }

        return answer;
    }
}
