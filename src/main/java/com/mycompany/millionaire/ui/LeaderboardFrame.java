package com.mycompany.millionaire.ui;

import com.mycompany.millionaire.model.LeaderboardEntry;
import com.mycompany.millionaire.service.LeaderboardManager;
import com.mycompany.millionaire.ui.component.BackgroundPanel;
import com.mycompany.millionaire.ui.component.UiTheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class LeaderboardFrame extends JFrame {

    private final LeaderboardManager manager = new LeaderboardManager();

    private JTable table;
    private DefaultTableModel model;

    private JButton btnClear;
    private JButton btnClose;

    public LeaderboardFrame() {
        initComponents();
        loadData();
    }

    private void initComponents() {

        setTitle("Leaderboard");
        setSize(760, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel background = new BackgroundPanel();
        background.setLayout(new BorderLayout(20, 20));
        background.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(background);

        JLabel title = new JLabel("TOP 10 LEADERBOARD", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(UiTheme.GOLD);
        background.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new Object[]{
                    "Rank",
                    "Player",
                    "Money",
                    "Level",
                    "Play Time"
                }, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.setRowHeight(34);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        table.setBackground(UiTheme.PANEL_BLUE);
        table.setForeground(Color.WHITE);

        table.setSelectionBackground(UiTheme.PANEL_BLUE);
        table.setSelectionForeground(Color.WHITE);

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        table.getTableHeader().setBackground(new Color(0, 0, 90));
        table.getTableHeader().setForeground(UiTheme.GOLD);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        table.getTableHeader().setBorder(
                BorderFactory.createLineBorder(Color.WHITE));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer left = new DefaultTableCellRenderer();
        left.setHorizontalAlignment(SwingConstants.LEFT);

        table.getColumnModel().getColumn(0).setCellRenderer(left);

        table.getColumnModel().getColumn(1).setCellRenderer(left);

        table.getColumnModel().getColumn(2).setCellRenderer(left);

        table.getColumnModel().getColumn(3).setCellRenderer(left);

        table.getColumnModel().getColumn(4).setCellRenderer(left);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(UiTheme.GOLD, 2));
        scrollPane.getViewport().setBackground(UiTheme.PANEL_BLUE);

        background.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        bottom.setOpaque(false);

        btnClear = new JButton("CLEAR");
        btnClose = new JButton("CLOSE");

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 15);

        btnClear.setFont(buttonFont);
        btnClose.setFont(buttonFont);

        btnClear.setBackground(UiTheme.PANEL_BLUE);
        btnClose.setBackground(UiTheme.PANEL_BLUE);

        btnClear.setForeground(UiTheme.GOLD);
        btnClose.setForeground(UiTheme.GOLD);

        btnClear.setFocusPainted(false);
        btnClose.setFocusPainted(false);

        btnClear.setBorder(BorderFactory.createLineBorder(UiTheme.GOLD, 2));
        btnClose.setBorder(BorderFactory.createLineBorder(UiTheme.GOLD, 2));

        btnClear.setPreferredSize(new Dimension(140, 42));
        btnClose.setPreferredSize(new Dimension(140, 42));

        btnClear.addActionListener(e -> clearLeaderboard());
        btnClose.addActionListener(e -> dispose());

        bottom.add(btnClear);
        bottom.add(btnClose);

        background.add(bottom, BorderLayout.SOUTH);
    }

    private void loadData() {

        model.setRowCount(0);

        ArrayList<LeaderboardEntry> list = manager.getLeaderboard();

        int rank = 1;

        for (LeaderboardEntry entry : list) {

            model.addRow(new Object[]{
                rank,
                entry.getPlayerName(),
                String.format("%,d", entry.getMoney()),
                entry.getLevel(),
                entry.getPlayTime()
            });

            rank++;
        }
    }

    private void clearLeaderboard() {

        int option = JOptionPane.showConfirmDialog(
                this,
                "Do you want to clear the leaderboard?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            manager.clear();
            loadData();
        }
    }
}
