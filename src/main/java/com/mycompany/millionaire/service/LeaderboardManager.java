/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Admin
 */
public class LeaderboardManager {
    
    private static final String FILE_NAME = "leaderboard.txt";

    private final ArrayList<LeaderboardEntry> leaderboard;

    public LeaderboardManager() {
        leaderboard = new ArrayList<>();
        load();
    }

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

    private void save() {

        try (FileWriter fw = new FileWriter(FILE_NAME)) {

            for (LeaderboardEntry entry : leaderboard) {
                fw.write(entry.toString());
                fw.write(System.lineSeparator());
            }

        } catch (IOException e) {
        }
    }

    public void addPlayer(String name, int money, int level, String playTime) {

        leaderboard.add(new LeaderboardEntry(name, money, level, playTime));

        Collections.sort(leaderboard);

        while (leaderboard.size() > 10) {
            leaderboard.remove(leaderboard.size() - 1);
        }

        save();
    }

    public ArrayList<LeaderboardEntry> getLeaderboard() {
        return leaderboard;
    }

    public void clear() {
        leaderboard.clear();
        save();
    }

    public void reload() {
        load();
    }

    public boolean isEmpty() {
        return leaderboard.isEmpty();
    }
}
