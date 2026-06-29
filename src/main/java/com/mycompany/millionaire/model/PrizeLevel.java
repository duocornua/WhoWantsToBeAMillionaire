package com.mycompany.millionaire.model;

/**
 * Describes one row of the prize ladder.
 */
public class PrizeLevel {

    private final int level;
    private final int money;
    private final boolean milestone;

    /**
     * Creates a prize level.
     *
     * @param level 1-based ladder level
     * @param money prize amount for this level
     * @param milestone {@code true} when this level is a safe milestone
     */
    public PrizeLevel(int level, int money, boolean milestone) {
        this.level = level;
        this.money = money;
        this.milestone = milestone;
    }

    /**
     * Gets the ladder level number.
     *
     * @return 1-based ladder level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the prize money for this level.
     *
     * @return prize amount
     */
    public int getMoney() {
        return money;
    }

    /**
     * Checks whether this level is a milestone.
     *
     * @return {@code true} for milestone levels
     */
    public boolean isMilestone() {
        return milestone;
    }
}
