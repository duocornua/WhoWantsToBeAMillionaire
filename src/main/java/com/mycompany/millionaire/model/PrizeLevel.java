package com.mycompany.millionaire.model;

public class PrizeLevel {

    private final int level;
    private final int money;
    private final boolean milestone;

    public PrizeLevel(int level, int money, boolean milestone) {
        this.level = level;
        this.money = money;
        this.milestone = milestone;
    }

    public int getLevel() {
        return level;
    }

    public int getMoney() {
        return money;
    }

    public boolean isMilestone() {
        return milestone;
    }
}
