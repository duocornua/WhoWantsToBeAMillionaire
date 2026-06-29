package com.mycompany.millionaire.model;

/**
 * Represents one saved leaderboard row.
 *
 * Entries are sorted by highest money, then highest level, then fastest play
 * time.
 */
public class LeaderboardEntry implements Comparable<LeaderboardEntry> {

    private String playerName;
    private int money;
    private int level;
    private String playTime;

    /**
     * Creates a leaderboard entry.
     *
     * @param playerName player display name
     * @param money prize money reached by the player
     * @param level highest level reached by the player
     * @param playTime elapsed play time in {@code mm:ss} format
     */
    public LeaderboardEntry(String playerName, int money, int level, String playTime) {
        this.playerName = playerName;
        this.money = money;
        this.level = level;
        this.playTime = playTime;
    }

    /**
     * Gets the player name.
     *
     * @return player display name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the money earned.
     *
     * @return prize money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Gets the level reached.
     *
     * @return highest level reached
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the play time text.
     *
     * @return elapsed time in {@code mm:ss} format
     */
    public String getPlayTime() {
        return playTime;
    }

    /**
     * Updates the player name.
     *
     * @param playerName new player display name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Updates the money earned.
     *
     * @param money new prize amount
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Updates the level reached.
     *
     * @param level new highest level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Updates the elapsed play time.
     *
     * @param playTime new time in {@code mm:ss} format
     */
    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    /**
     * Compares entries for leaderboard ranking.
     *
     * @param other entry to compare with this entry
     * @return negative when this entry should appear before {@code other}
     */
    @Override
    public int compareTo(LeaderboardEntry other) {

        if (this.money != other.money) {
            return Integer.compare(other.money, this.money);
        }

        if (this.level != other.level) {
            return Integer.compare(other.level, this.level);
        }

        return Integer.compare(
                convertToSecond(this.playTime),
                convertToSecond(other.playTime)
        );
    }

    /**
     * Converts {@code mm:ss} text to seconds for ranking by fastest time.
     *
     * @param time time text to convert
     * @return total seconds, or {@link Integer#MAX_VALUE} when invalid
     */
    private int convertToSecond(String time) {

        try {
            String[] t = time.split(":");

            int minute = Integer.parseInt(t[0]);
            int second = Integer.parseInt(t[1]);

            return minute * 60 + second;
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }

    }

    /**
     * Serializes this entry for the leaderboard text file.
     *
     * @return semicolon-separated leaderboard row
     */
    @Override
    public String toString() {
        return playerName + ";" + money + ";" + level + ";" + playTime;
    }

    /**
     * Parses a leaderboard row from the text file.
     *
     * @param line semicolon-separated row
     * @return parsed entry, or {@code null} if the row is invalid
     */
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
