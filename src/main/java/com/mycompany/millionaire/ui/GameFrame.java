package com.mycompany.millionaire.ui;

import com.mycompany.millionaire.model.Answer;
import com.mycompany.millionaire.model.Question;
import com.mycompany.millionaire.service.AnswerResult;
import com.mycompany.millionaire.service.GameController;
import com.mycompany.millionaire.ui.component.BackgroundPanel;
import com.mycompany.millionaire.ui.component.HexPanel;
import com.mycompany.millionaire.ui.component.LogoPanel;
import com.mycompany.millionaire.ui.component.MillionaireButton;
import com.mycompany.millionaire.ui.component.MoneyLadderPanel;
import com.mycompany.millionaire.ui.component.SoundToggleButton;
import com.mycompany.millionaire.ui.component.UiTheme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameFrame extends JFrame {

    private final GameController gameController = new GameController();
    private final JLabel questionLabel = new JLabel("Question", SwingConstants.CENTER);
    private final MillionaireButton[] answerButtons = new MillionaireButton[4];
    private final MoneyLadderPanel moneyLadder = new MoneyLadderPanel(gameController.getMoneyLadder());
    private final JButton helpButton = createControlButton("50:50");
    private final JButton quitButton = createControlButton("QUIT");
    private final JButton muteButton = createSoundButton();
    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

    private boolean waitingForAnimation;

    public GameFrame() {
        System.setProperty("sun.java2d.noddraw", "true");
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

        content.add(createTopPanel(), BorderLayout.NORTH);

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

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 44));
        statusLabel.setForeground(UiTheme.RED);
        statusLabel.setPreferredSize(new Dimension(100, 62));
        topPanel.add(statusLabel, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 12);
        controls.add(muteButton, gbc);
        controls.add(helpButton, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        controls.add(quitButton, gbc);
        topPanel.add(controls, BorderLayout.EAST);

        return topPanel;
    }

    private JPanel createQuestionArea() {
        JPanel questionArea = new JPanel(new GridBagLayout());
        questionArea.setOpaque(false);

        questionLabel.setOpaque(false);
        questionLabel.setForeground(UiTheme.WHITE);
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

        JPanel answerGrid = new JPanel(new GridLayout(2, 2, 30, 20));
        answerGrid.setOpaque(false);
        answerGrid.setPreferredSize(new Dimension(780, 200));
        answerGrid.setMinimumSize(new Dimension(700, 180));

        String[] letters = {"A:", "B:", "C:", "D:"};
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new MillionaireButton(letters[i]);
            final int answerIndex = i;
            answerButtons[i].addActionListener(e -> {
                Question currentQuestion = gameController.getCurrentQuestion();
                if (currentQuestion != null) {
                    Answer selectedAnswer = currentQuestion.getAnswers().get(answerIndex);
                    checkAnswer(selectedAnswer, answerIndex);
                }
            });
            answerGrid.add(answerButtons[i]);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 20, 0);
        questionArea.add(logoPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 25, 0);
        questionArea.add(questionBox, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 15, 0);
        questionArea.add(answerGrid, gbc);

        return questionArea;
    }

    private JButton createControlButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(UiTheme.GOLD);
        button.setBackground(UiTheme.PANEL_BLUE);
        button.setBorder(BorderFactory.createLineBorder(UiTheme.GOLD, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 48));
        return button;
    }

    private JButton createSoundButton() {
        JButton button = new SoundToggleButton();
        button.setBackground(UiTheme.PANEL_BLUE);
        button.setForeground(UiTheme.GOLD);
        button.setToolTipText("Mute or unmute all game audio");
        button.setPreferredSize(new Dimension(48, 48));
        return button;
    }

    private void loadQuestion() {
        Question question = gameController.getCurrentQuestion();
        if (question == null) {
            showWin();
            return;
        }

        questionLabel.setText("<html><div style='text-align:center;'>" + question.getQuestion() + "</div></html>");
        
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setAnswerText(answers.get(i).getInfo());
            answerButtons[i].setVisible(true);
            answerButtons[i].setEnabled(true);
            answerButtons[i].setBlinkColor(null);
            answerButtons[i].setStaticReveal(false);
        }

        statusLabel.setText("");
        helpButton.setEnabled(!gameController.isLifelineUsed());
        moneyLadder.setCurrentLevel(gameController.getCurrentLevel());
        moneyLadder.setBlinking(false);
        AudioPlayer.playLoop(gameController.getQuestionLoopFile());
    }

    private void checkAnswer(Answer selectedAnswer, int buttonIndex) {
        if (waitingForAnimation || !answerButtons[buttonIndex].isVisible()) {
            return;
        }

        waitingForAnimation = true;
        setAnswerButtonsEnabled(false);

        AnswerResult result = gameController.submitAnswer(selectedAnswer);
        MillionaireButton chosenButton = answerButtons[buttonIndex];
        MillionaireButton correctButton = answerButtons[result.getCorrectAnswer()];

        AudioPlayer.stopLoop();
        if (result.isCorrect()) {
            AudioPlayer.playOnce(result.isFinalQuestion() ? "win.wav" : "correct.wav");
            blinkForResult(chosenButton, null, true, () -> finishCorrectAnswer(result));
        } else {
            AudioPlayer.playOnce("wrong.wav");
            statusLabel.setForeground(UiTheme.RED);
            statusLabel.setText("You Lost");
            blinkForResult(correctButton, chosenButton, false, () -> {
                waitingForAnimation = false;
                returnToMenu();
            });
        }
    }

    private void finishCorrectAnswer(AnswerResult result) {
        waitingForAnimation = false;
        if (result.isGameFinished()) {
            showWin();
        } else {
            loadQuestion();
        }
    }

    private void execute5050() {
        if (waitingForAnimation) {
            return;
        }

        List<Integer> hiddenAnswers = gameController.useFiftyFifty();
        for (Integer index : hiddenAnswers) {
            answerButtons[index].setVisible(false);
        }
        helpButton.setEnabled(false);
    }

    private void blinkForResult(MillionaireButton correctButton, MillionaireButton wrongButton, boolean correctAnswer, Runnable afterBlink) {
        long start = System.currentTimeMillis();
        Timer timer = new Timer(250, null);
        timer.addActionListener(e -> {
            boolean visible = ((System.currentTimeMillis() - start) / 250) % 2 == 0;

            if (correctAnswer) {
                correctButton.setBlinkColor(visible ? UiTheme.GREEN : null);
            } else {
                if (wrongButton != null) {
                    wrongButton.setBlinkColor(UiTheme.GOLD);
                    wrongButton.setStaticReveal(true);
                }
                correctButton.setBlinkColor(visible ? UiTheme.GREEN : null);
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

    private void toggleMute() {
        AudioPlayer.toggleMuted();
        muteButton.repaint();
        if (!AudioPlayer.isMuted() && !waitingForAnimation) {
            AudioPlayer.playLoop(gameController.getQuestionLoopFile());
        }
    }

    private void setAnswerButtonsEnabled(boolean enabled) {
        for (MillionaireButton button : answerButtons) {
            button.setEnabled(enabled);
        }
    }

    private void showWin() {
        statusLabel.setForeground(UiTheme.GREEN);
        statusLabel.setText("You Win!");
        moneyLadder.setCurrentLevel(gameController.getMoneyLadder().size());
        setAnswerButtonsEnabled(false);
        helpButton.setEnabled(false);
    }

    private void returnToMenu() {
        SwingUtilities.invokeLater(() -> {
            AudioPlayer.stopAll();
            new MainMenu().setVisible(true);
            dispose();
        });
    }
}
