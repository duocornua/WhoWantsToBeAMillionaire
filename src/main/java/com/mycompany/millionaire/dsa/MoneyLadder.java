package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.PrizeLevel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyLadder {

    private final ArrayList<PrizeLevel> levels = new ArrayList<>();

    public MoneyLadder() {
        int[] prizes = {
            100, 200, 300, 500, 1_000,
            2_000, 4_000, 8_000, 16_000, 32_000,
            64_000, 125_000, 250_000, 500_000, 1_000_000
        };

        for (int i = 0; i < prizes.length; i++) {
            int level = i + 1;
            levels.add(new PrizeLevel(level, prizes[i], level == 5 || level == 10 || level == 15));
        }
    }

    public int size() {
        return levels.size();
    }

    public List<PrizeLevel> getLevelsDescending() {
        ArrayList<PrizeLevel> copy = new ArrayList<>(levels);
        Collections.reverse(copy);
        return copy;
    }
}
