package com.mycompany.millionaire.service;

import com.mycompany.millionaire.model.LeaderboardEntry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Loads, saves, sorts, and clears the top leaderboard entries.
 */
public class LeaderboardManager {

    private static final String FILE_NAME = "leaderboard.txt";

    private final ArrayList<LeaderboardEntry> leaderboard;

    /**
     * Creates a manager and loads existing entries from disk.
     */
    public LeaderboardManager() {
        leaderboard = new ArrayList<>();
        load();
    }

    /**
     * Reads leaderboard rows from the text file into memory.
     */
    private void load() {
        leaderboard.clear();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                LeaderboardEntry entry = LeaderboardEntry.fromString(line);

                if (entry != null) {
                    leaderboard.add(entry);
                }
            }

            Collections.sort(leaderboard);

        } catch (IOException e) {
        }
    }

    /**
     * Writes the in-memory leaderboard back to the text file.
     */
    private void save() {

        try (FileWriter fw = new FileWriter(FILE_NAME)) {

            for (LeaderboardEntry entry : leaderboard) {
                fw.write(entry.toString());
                fw.write(System.lineSeparator());
            }

        } catch (IOException e) {
        }
    }

    /**
     * Adds a player result, sorts the table, trims it to the top 10, and saves.
     *
     * @param name player display name
     * @param money prize money reached
     * @param level highest level reached
     * @param playTime elapsed time in {@code mm:ss} format
     */
    public void addPlayer(String name, int money, int level, String playTime) {

        leaderboard.add(new LeaderboardEntry(name, money, level, playTime));

        Collections.sort(leaderboard);

        while (leaderboard.size() > 10) {
            leaderboard.remove(leaderboard.size() - 1);
        }

        save();
    }

    /**
     * Gets the current leaderboard entries.
     *
     * @return mutable list of entries sorted by rank
     */
    public ArrayList<LeaderboardEntry> getLeaderboard() {
        return leaderboard;
    }

    /**
     * Deletes all leaderboard entries and saves the empty file.
     */
    public void clear() {
        leaderboard.clear();
        save();
    }

    /**
     * Reloads entries from disk.
     */
    public void reload() {
        load();
    }

    /**
     * Checks whether the leaderboard has no entries.
     *
     * @return {@code true} when there are no saved scores
     */
    public boolean isEmpty() {
        return leaderboard.isEmpty();
    }
}
