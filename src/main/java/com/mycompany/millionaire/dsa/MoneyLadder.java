package com.mycompany.millionaire.dsa;

import com.mycompany.millionaire.model.PrizeLevel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builds and exposes the fixed 15-level prize ladder.
 */
public class MoneyLadder {

    private final ArrayList<PrizeLevel> levels = new ArrayList<>();

    /**
     * Creates the standard prize sequence and marks safe milestone levels.
     */
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

    /**
     * Returns how many prize levels the game has.
     *
     * @return number of levels in the ladder
     */
    public int size() {
        return levels.size();
    }

    /**
     * Returns a display-friendly copy of the ladder from highest to lowest.
     *
     * @return reversed copy of the prize levels
     */
    public List<PrizeLevel> getLevelsDescending() {
        ArrayList<PrizeLevel> copy = new ArrayList<>(levels);
        Collections.reverse(copy);
        return copy;
    }

    /**
     * Finds a prize level by its 1-based game level.
     *
     * @param level 1-based question/prize level
     * @return prize level for the requested level, or {@code null} when invalid
     */
    public PrizeLevel getPrizeLevel(int level) {

        if (level < 1 || level > levels.size()) {
            return null;
        }

        return levels.get(level - 1);
    }
}
