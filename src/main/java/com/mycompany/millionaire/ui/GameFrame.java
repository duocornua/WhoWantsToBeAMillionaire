package com.mycompany.millionaire.ui;

import com.mycompany.millionaire.model.Question;
import com.mycompany.millionaire.model.QuestionBank;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameFrame extends JFrame {

    private static final Color PANEL_BLUE = new Color(7, 18, 100);
    private static final Color ANSWER_BLUE_TOP = new Color(43, 93, 190);
    private static final Color ANSWER_BLUE_BOTTOM = new Color(12, 18, 116);
    private static final Color GOLD = new Color(245, 196, 52);
    private static final Color WHITE = Color.WHITE;
    private static final Color GREEN = new Color(43, 210, 89);
    private static final Color RED = new Color(230, 55, 55);
    private static final int[] MONEY_STEPS = {
        100, 200, 300, 500, 1_000,
        2_000, 4_000, 8_000, 16_000, 32_000,
        64_000, 125_000, 250_000, 500_000, 1_000_000
    };

    private final List<Question> questions;
    private final JLabel questionLabel = new JLabel("Question", SwingConstants.CENTER);
    private final MillionaireButton[] answerButtons = new MillionaireButton[4];
    private final MoneyLadderPanel moneyLadder = new MoneyLadderPanel();
    private final JButton helpButton = createControlButton("50:50");
    private final JButton quitButton = createControlButton("QUIT");
    private final JButton muteButton = createSoundButton();
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

    private int currentQuestion = 0;
    private boolean waitingForAnimation = false;
    private boolean helpUsed = false;

    public GameFrame() {
        System.setProperty("sun.java2d.noddraw", "true");
        questions = QuestionBank.getQuestions();
        initComponents();
        loadQuestion();
    }

    private void initComponents() {
        setTitle("Who Wants To Be A Millionaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1120, 720));
        setLocationRelativeTo(null);

        JPanel content = new BackgroundPanel();
        content.setLayout(new BorderLayout(18, 18));
        content.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));
        setContentPane(content);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 44));
        statusLabel.setForeground(RED);
        statusLabel.setPreferredSize(new Dimension(100, 62));
        topPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setOpaque(false);
        GridBagConstraints controlGbc = new GridBagConstraints();
        controlGbc.gridy = 0;
        controlGbc.insets = new Insets(0, 0, 0, 12);
        controls.add(muteButton, controlGbc);
        controlGbc.insets = new Insets(0, 0, 0, 12);
        controls.add(helpButton, controlGbc);
        controlGbc.insets = new Insets(0, 0, 0, 0);
        controls.add(quitButton, controlGbc);
        topPanel.add(controls, BorderLayout.EAST);
        content.add(topPanel, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(22, 0));
        center.setOpaque(false);
        center.add(createQuestionArea(), BorderLayout.CENTER);
        center.add(moneyLadder, BorderLayout.EAST);
        content.add(center, BorderLayout.CENTER);

        helpButton.addActionListener(e -> execute5050());
        quitButton.addActionListener(e -> returnToMenu());
        muteButton.addActionListener(e -> toggleMute());

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createQuestionArea() {
        JPanel questionArea = new JPanel(new GridBagLayout());
        questionArea.setOpaque(false);

        questionLabel.setOpaque(false);
        questionLabel.setForeground(WHITE);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        LogoPanel logoPanel = new LogoPanel();
        logoPanel.setPreferredSize(new Dimension(720, 320));
        logoPanel.setMinimumSize(new Dimension(500, 220));

        HexPanel questionBox = new HexPanel();
        questionBox.setLayout(new BorderLayout());
        questionBox.setPreferredSize(new Dimension(780, 92));
        questionBox.setMinimumSize(new Dimension(700, 92));
        questionBox.add(questionLabel, BorderLayout.CENTER);

        // Đặt kích thước cố định vừa vặn cho khung lưới chứa đáp án tránh bị co giãn kéo dài
        JPanel answerGrid = new JPanel(new GridLayout(2, 2, 30, 20));
        answerGrid.setOpaque(false);
        answerGrid.setPreferredSize(new Dimension(780, 200));
        answerGrid.setMinimumSize(new Dimension(700, 180));

        String[] labels = {"A:", "B:", "C:", "D:"};
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new MillionaireButton(labels[i]);
            final int answerIndex = i;
            answerButtons[i].addActionListener(e -> checkAnswer(answerIndex));
            answerGrid.add(answerButtons[i]);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        
        // ---- VẼ LOGO ----
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6; // Ưu tiên cấp nhiều không gian dọc cho Logo khi phóng to
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(10, 0, 20, 0);
        questionArea.add(logoPanel, gbc);

        // ---- VẼ KHUNG CÂU HỎI ----
        gbc.gridy = 1;
        gbc.weighty = 0.0; // Khóa không cho giãn tự do theo chiều dọc
        gbc.fill = GridBagConstraints.HORIZONTAL; // Chỉ giãn ngang đều hai bên
        gbc.insets = new Insets(0, 0, 25, 0);
        questionArea.add(questionBox, gbc);

        // ---- VẼ GRID ĐÁP ÁN ----
        gbc.gridy = 2;
        gbc.weighty = 0.0; // Khóa chiều cao cố định để giữ nút bấm chuẩn hình lục giác
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 15, 0);
        questionArea.add(answerGrid, gbc);

        return questionArea;
    }

    private static JButton createControlButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(GOLD);
        button.setBackground(PANEL_BLUE);
        button.setBorder(BorderFactory.createLineBorder(GOLD, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 48));
        return button;
    }

    private JButton createSoundButton() {
        JButton button = new MainMenu.SoundToggleButton();
        button.setBackground(PANEL_BLUE);
        button.setForeground(GOLD);
        button.setToolTipText("Mute or unmute all game audio");
        button.setPreferredSize(new Dimension(48, 48));
        return button;
    }

    private void toggleMute() {
        AudioPlayer.toggleMuted();
        muteButton.repaint();
        if (!AudioPlayer.isMuted() && !waitingForAnimation) {
            AudioPlayer.playLoop(getQuestionLoopFile());
        }
    }

    private void loadQuestion() {
        if (currentQuestion >= questions.size()) {
            statusLabel.setForeground(GREEN);
            statusLabel.setText("You Win!");
            disableAnswers();
            return;
        }

        Question q = questions.get(currentQuestion);
        questionLabel.setText("<html><div style='text-align:center;'>" + q.getQuestion() + "</div></html>");

        String[] letters = {"A:", "B:", "C:", "D:"};
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setAnswerText(letters[i], q.getOptions()[i]);
            answerButtons[i].setVisible(true);
            answerButtons[i].setEnabled(true);
            answerButtons[i].setBlinkColor(null);
            answerButtons[i].setStaticReveal(false); // Reset lại trạng thái màu
        }

        statusLabel.setText("");
        helpButton.setEnabled(!helpUsed);
        moneyLadder.setCurrentLevel(currentQuestion + 1);
        moneyLadder.setBlinking(false);
        AudioPlayer.playLoop(getQuestionLoopFile());
    }

    private void checkAnswer(int answer) {
        if (waitingForAnimation || !answerButtons[answer].isVisible()) {
            return;
        }

        waitingForAnimation = true;
        setAnswerButtonsEnabled(false);

        Question q = questions.get(currentQuestion);
        boolean correct = answer == q.getCorrectAnswer();
        MillionaireButton chosenButton = answerButtons[answer];
        MillionaireButton correctButton = answerButtons[q.getCorrectAnswer()];

        if (correct) {
            AudioPlayer.stopLoop();
            boolean finalQuestion = currentQuestion == questions.size() - 1;
            AudioPlayer.playOnce(finalQuestion ? "win.wav" : "correct.wav");
            blinkForResult(chosenButton, null, true, () -> {
                currentQuestion++;
                waitingForAnimation = false;
                if (currentQuestion >= questions.size()) {
                    showWin();
                } else {
                    loadQuestion();
                }
            });
        } else {
            AudioPlayer.stopLoop();
            AudioPlayer.playOnce("wrong.wav");
            statusLabel.setForeground(RED);
            statusLabel.setText("You Lost");
            blinkForResult(correctButton, chosenButton, false, () -> {
                waitingForAnimation = false;
                returnToMenu();
            });
        }
    }

    private void blinkForResult(MillionaireButton correctButton, MillionaireButton wrongButton, boolean correctAnswer, Runnable afterBlink) {
        long start = System.currentTimeMillis();
        Timer timer = new Timer(250, null);
        timer.addActionListener(e -> {
            boolean visible = ((System.currentTimeMillis() - start) / 250) % 2 == 0;

            if (correctAnswer) {
                correctButton.setBlinkColor(visible ? GREEN : null);
            } else {
                if (wrongButton != null) {
                    wrongButton.setBlinkColor(GOLD);
                    wrongButton.setStaticReveal(true); 
                }
                correctButton.setBlinkColor(visible ? GREEN : null);
            }

            moneyLadder.setBlinking(correctAnswer && visible);

            if (System.currentTimeMillis() - start >= 5000) {
                timer.stop();
                correctButton.setBlinkColor(null);
                if (wrongButton != null) {
                    wrongButton.setBlinkColor(null);
                    wrongButton.setStaticReveal(false);
                }
                moneyLadder.setBlinking(false);
                afterBlink.run();
            }
        });
        timer.start();
    }

    private void execute5050() {
        if (waitingForAnimation) {
            return;
        }

        Question q = questions.get(currentQuestion);
        int correctIndex = q.getCorrectAnswer();
        List<MillionaireButton> wrongButtons = new ArrayList<>();

        for (int i = 0; i < answerButtons.length; i++) {
            if (i != correctIndex) {
                wrongButtons.add(answerButtons[i]);
            }
        }

        Collections.shuffle(wrongButtons);
        wrongButtons.get(0).setVisible(false);
        wrongButtons.get(1).setVisible(false);
        helpUsed = true;
        helpButton.setEnabled(false);
    }

    private void setAnswerButtonsEnabled(boolean enabled) {
        for (MillionaireButton button : answerButtons) {
            button.setEnabled(enabled);
        }
    }

    private void disableAnswers() {
        setAnswerButtonsEnabled(false);
        helpButton.setEnabled(false);
    }

    private void showWin() {
        statusLabel.setForeground(GREEN);
        statusLabel.setText("You Win!");
        moneyLadder.setCurrentLevel(15);
        disableAnswers();
    }

    private String getQuestionLoopFile() {
        return currentQuestion < 5 ? "1to5.wav" : "6to15.wav";
    }

    private void returnToMenu() {
        SwingUtilities.invokeLater(() -> {
            AudioPlayer.stopAll();
            new MainMenu().setVisible(true);
            dispose();
        });
    }

    private static String formatMoney(int value) {
        return String.format("$%,d", value);
    }

    private static class BackgroundPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0, 0, new Color(6, 10, 42), getWidth(), getHeight(), new Color(18, 32, 96)));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    private static class HexPanel extends JPanel {

        HexPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Polygon shape = createHexShape(getWidth(), getHeight(), 34);
            g2.setPaint(new GradientPaint(0, 0, ANSWER_BLUE_TOP, 0, getHeight(), ANSWER_BLUE_BOTTOM));
            g2.fillPolygon(shape);
            g2.setColor(new Color(205, 210, 225));
            g2.setStroke(new BasicStroke(2.2f));
            g2.drawPolygon(shape);
            g2.dispose();
        }

        private static Polygon createHexShape(int width, int height, int notch) {
            return new Polygon(
                    new int[]{notch, width - notch, width, width - notch, notch, 0},
                    new int[]{0, 0, height / 2, height, height, height / 2},
                    6
            );
        }
    }

    private static class LogoPanel extends JPanel {

        private final Image logo = new ImageIcon("logo.png").getImage();

        LogoPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (logo.getWidth(this) <= 0 || logo.getHeight(this) <= 0) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            double scale = Math.min((double) getWidth() / logo.getWidth(this), (double) getHeight() / logo.getHeight(this));
            int drawWidth = (int) (logo.getWidth(this) * scale);
            int drawHeight = (int) (logo.getHeight(this) * scale);
            int x = (getWidth() - drawWidth) / 2;
            int y = (getHeight() - drawHeight) / 2;
            g2.drawImage(logo, x, y, drawWidth, drawHeight, this);
            g2.dispose();
        }
    }

    private static class MillionaireButton extends JButton {

        private final String letter;
        private String answer = "";
        private Color blinkColor;
        private boolean staticReveal = false;

        MillionaireButton(String letter) {
            this.letter = letter;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 24));
            setPreferredSize(new Dimension(360, 86));
        }

        void setAnswerText(String letter, String answer) {
            this.answer = answer;
            setText(letter + " " + answer);
        }

        void setBlinkColor(Color blinkColor) {
            this.blinkColor = blinkColor;
            repaint();
        }
        
        void setStaticReveal(boolean staticReveal) {
            this.staticReveal = staticReveal;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Polygon shape = HexPanel.createHexShape(getWidth(), getHeight(), 38);
            
            if (blinkColor != null) {
                g2.setColor(blinkColor);
                g2.fillPolygon(shape);
            } else {
                g2.setPaint(new GradientPaint(0, 0, ANSWER_BLUE_TOP, 0, getHeight(), ANSWER_BLUE_BOTTOM));
                g2.fillPolygon(shape);
            }
            
            if (staticReveal) {
                g2.setColor(GOLD);
                g2.setStroke(new BasicStroke(2.2f));
            } else if (blinkColor == GREEN) {
                g2.setColor(Color.WHITE); 
                g2.setStroke(new BasicStroke(3.5f));
            } else {
                g2.setColor(new Color(205, 210, 225)); 
                g2.setStroke(new BasicStroke(2.2f));
            }
            g2.drawPolygon(shape);
            
            Color letterColor = GOLD; 
            Color textColor = WHITE;
            
            // Ép cả 2 trường hợp: Đang nhấp nháy hiện nền Xanh (GREEN) hoặc câu sai đứng yên nền Vàng (staticReveal) đều chuyển chữ sang màu Đen
            if (blinkColor == GREEN || staticReveal) {
                letterColor = Color.BLACK;
                textColor = Color.BLACK;
            }
            
            Font letterFont = getFont().deriveFont(Font.BOLD, 34f);
            g2.setFont(letterFont);
            g2.setColor(letterColor);
            FontMetrics letterMetrics = g2.getFontMetrics();
            int letterX = 52;
            int centerY = (getHeight() + letterMetrics.getAscent() - letterMetrics.getDescent()) / 2;
            g2.drawString(letter, letterX, centerY);

            g2.setFont(getFont());
            g2.setColor(textColor);
            drawFittedText(g2, answer, 142, centerY, getWidth() - 170);
            g2.dispose();
        }

        private void drawFittedText(Graphics2D g2, String text, int x, int baseline, int maxWidth) {
            Font original = g2.getFont();
            FontMetrics metrics = g2.getFontMetrics();
            while (metrics.stringWidth(text) > maxWidth && g2.getFont().getSize() > 14) {
                g2.setFont(g2.getFont().deriveFont((float) g2.getFont().getSize() - 1));
                metrics = g2.getFontMetrics();
            }
            g2.drawString(text, x, baseline);
            g2.setFont(original);
        }
    }

    private static class MoneyLadderPanel extends JPanel {

        private int currentLevel = 1;
        private boolean blinking;

        MoneyLadderPanel() {
            setOpaque(false);
            setPreferredSize(new Dimension(310, 610));
        }

        void setCurrentLevel(int currentLevel) {
            this.currentLevel = currentLevel;
            repaint();
        }

        void setBlinking(boolean blinking) {
            this.blinking = blinking;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 60, 225));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

            int rowHeight = getHeight() / 15;
            g2.setFont(new Font("Segoe UI", Font.BOLD, 27));
            for (int level = 15; level >= 1; level--) {
                int rowFromTop = 15 - level;
                int y = rowFromTop * rowHeight;
                boolean active = level == currentLevel;
                if (active) {
                    g2.setColor(blinking ? GREEN : new Color(245, 166, 45));
                    Polygon highlight = new Polygon(
                            new int[]{28, getWidth(), getWidth(), 28, 8},
                            new int[]{y + 3, y + 3, y + rowHeight - 3, y + rowHeight - 3, y + rowHeight / 2},
                            5
                    );
                    g2.fillPolygon(highlight);
                }

                boolean milestone = level == 5 || level == 10 || level == 15;
                g2.setColor(active ? Color.BLACK : milestone ? WHITE : GOLD);
                int baseline = y + rowHeight - 10;
                g2.drawString(String.valueOf(level), 42, baseline);
                if (level < 15) {
                    g2.fillOval(108, baseline - 12, 8, 8);
                }
                drawMoneyText(g2, formatMoney(MONEY_STEPS[level - 1]), 128, baseline, getWidth() - 138);
            }
            g2.dispose();
        }

        private void drawMoneyText(Graphics2D g2, String text, int x, int baseline, int maxWidth) {
            Font original = g2.getFont();
            FontMetrics metrics = g2.getFontMetrics();
            while (metrics.stringWidth(text) > maxWidth && g2.getFont().getSize() > 18) {
                g2.setFont(g2.getFont().deriveFont((float) g2.getFont().getSize() - 1));
                metrics = g2.getFontMetrics();
            }
            g2.drawString(text, x, baseline);
            g2.setFont(original);
        }
    }
}
