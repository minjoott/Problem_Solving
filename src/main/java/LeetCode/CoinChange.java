package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Queue<Entry> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        if (amount == 0) return 0;

        queue.add(new Entry(0, 0));
        while (!queue.isEmpty()) {
            Entry curr = queue.remove();

            for (int coin : coins) {
                int nextCoin = curr.amount + coin;

                if (nextCoin > amount) continue;

                if (nextCoin == amount) return curr.count + 1;

                if (!visited[nextCoin]) {
                    visited[nextCoin] = true;
                    queue.add(new Entry(nextCoin, curr.count + 1));
                }
            }
        }

        return -1;
    }

    class Entry {
        int amount;
        int count;

        Entry(int amount, int count) {
            this.amount = amount;
            this.count = count;
        }
    }
}
