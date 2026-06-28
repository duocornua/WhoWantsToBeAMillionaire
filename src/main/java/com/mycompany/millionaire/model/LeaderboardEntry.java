package com.mycompany.millionaire.model;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {

    private String playerName;
    private int money;
    private int level;
    private String playTime;

    public LeaderboardEntry(String playerName, int money, int level, String dateTime) {
        this.playerName = playerName;
        this.money = money;
        this.level = level;
        this.playTime = playTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    @Override
    public int compareTo(LeaderboardEntry other) {
        return Integer.compare(other.money, this.money);
    }

    @Override
    public String toString() {
        return playerName + ";" + money + ";" + level + ";" + playTime;
    }
    
public static LeaderboardEntry fromString(String line) {

        String[] data = line.split(";");

        if (data.length != 4) {
            return null;
        }

        try {
            return new LeaderboardEntry(
                    data[0],
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    data[3]
            );
        } catch (Exception e) {
            return null;
        }
    }
}
