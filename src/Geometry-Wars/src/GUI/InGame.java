package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.Timer;

import GComponents.*;
import Game.Enemy;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
class InGame extends GPanel implements ActionListener {
    //region Instance Variables

    private InGame panel = this;
    private boolean coop;

    //GamePanel
    private GamePanel gamePanel;

    private GButton startGame = new GButton("Click to Start", 35f, 280, 250, 400, 200);
    private Timer gameTimer;

    //PausePanel
    private GButton Continue = new GButton("Continue", 24f, 185, 250, 275, 120);
    private GButton controls = new GButton("Controls", 24f, 530, 250, 275, 120);
    private GButton restartPauze = new GButton("Restart", 24f, 185, 405, 275, 120);
    private GButton menuPauze = new GButton("Main Menu", 24f, 530, 405, 275, 120);

    private GButton restartGameEnd = new GButton("Restart", 24f, 150, 420, 275, 120);
    private GButton menuGameEnd = new GButton("Main Menu", 24f, 550, 420, 275, 120);
    private ImageIcon PauseImage = new ImageIcon("resources\\Media\\pause-128.png");
    private JButton pauze = new JButton(PauseImage);
    private GLabel gameOver = new GLabel("Oopsy daisy! u dead fam", 25f, 325, 260, 375, 120, true, Color.white);
    private GPanel pause = new GPanel() {
        @Override
        public void initComponents() throws IOException, FontFormatException {
            pause.removeAll();
            Continue.setBackground(Color.black);
            Continue.setForeground(Color.white);
            Continue.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));

            restartPauze.setBackground(Color.black);
            restartPauze.setForeground(Color.white);
            restartPauze.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));

            menuPauze.setBackground(Color.black);
            menuPauze.setForeground(Color.white);
            menuPauze.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));

            controls.setBackground(Color.black);
            controls.setForeground(Color.white);
            controls.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));

            pause.add(Continue);
            pause.add(restartPauze);
            pause.add(menuPauze);
            pause.add(controls);

            menuPauze.addActionListener(evt -> {
                gamePanel.setGameFinished(true);
                gameEnd.setVisible(false);
                panel.setVisible(false);

                Window window = (Window) SwingUtilities.getRoot(panel.getParent());
                window.getMainMenu().setVisible(true);
            });
        }
    };
    private GPanel gameEnd = new GPanel() {
        @Override
        public void initComponents() throws IOException, FontFormatException {
            gameEnd.removeAll();
            JLabel pane = new JLabel();
            pane.setOpaque(true);
            pane.setBackground(new Color(255, 255, 255, 2));
            pane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
            pane.setBounds(50, 125, 900, 500);

            restartGameEnd.setBackground(Color.black);
            restartGameEnd.setForeground(Color.white);
            restartGameEnd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));

            menuGameEnd.setBackground(Color.black);
            menuGameEnd.setForeground(Color.white);
            menuGameEnd.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));

            gameOver.setHorizontalAlignment(SwingConstants.CENTER);
            gameOver.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
            gameOver.setBackground(Color.black);
            gameEnd.add(pane);
            gameEnd.add(gameOver);
            gameEnd.add(restartGameEnd);
            gameEnd.add(menuGameEnd);

            menuGameEnd.addActionListener(evt -> {
                panel.setVisible(false);
                Window window = (Window) SwingUtilities.getRoot(panel.getParent());
                pause.setVisible(false);
                try {
                    window.getSpel().submitScore(gamePanel.getScore());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                window.getMainMenu().setVisible(true);

            });
        }
    };

    //coop
    private GLabel healthp2Background;
    private GLabel scorep2Background;
    private GLabel schipLvlp2Background;
    private GLabel droneLvlp2Background;
    private GLabel healthbarp2Background;
    private GLabel dronebarp2Background;
    private GLabel schipbarp2BackGround;

    //endregion

    //region Constructors

    InGame(List<Enemy> enemies) throws IOException, FontFormatException {
        gamePanel = new GamePanel(enemies);
        pause.initComponents();
        gameEnd.initComponents();
        initComponents();

        this.add(pause);
        pause.setVisible(false);

        this.add(gameEnd);
        gameEnd.setVisible(false);

        this.add(gamePanel);
        gamePanel.setOpaque(false);

        startGame.setBackground(Color.black);
        startGame.setForeground(Color.white);
        startGame.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        //this.add(startGame);

        addActionListeners();

        gamePanel.setVisible(false);
    }

    //endregion

    //region Getters & Setters

    void setCoop(boolean coop) {
        this.coop = coop;
    }

    GButton getStartGame() {
        return startGame;
    }

    GPanel getGameEnd() {
        return gameEnd;
    }

    GPanel getPause() {
        return pause;
    }

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();
        JLabel pane = new JLabel();

        healthp2Background = new GLabel("Health:", 24, 595, 18, 169, 62, false, Color.black);
        scorep2Background = new GLabel("Score:", 24, 580, 65, 169, 62, false, Color.cyan);
        schipLvlp2Background = new GLabel("Ship:", 20, 600, 680, 222, 62, false, Color.green);
        droneLvlp2Background = new GLabel("Drone:", 20, 800, 680, 222, 62, false, new Color(155, 255, 204));
        healthbarp2Background = new GLabel("", 24, 570, 23, 410, 47, true, Color.black);
        dronebarp2Background = new GLabel("", 20, 880, 695, 125, 35, true, Color.black);
        schipbarp2BackGround = new GLabel("", 20, 660, 695, 125, 35, true, Color.black);

        //region Single player

        GLabel healthp1Background = new GLabel("Health:", 24, 40, 18, 169, 62, false, Color.black);
        GLabel scorep1Background = new GLabel("Score:", 24, 25, 65, 169, 62, false, Color.cyan);
        GLabel healthbarp1Background = new GLabel("", 24, 15, 23, 410, 47, true, Color.black);
        healthbarp1Background.setBackground(new Color(255, 255, 255, 95));
        this.add(healthbarp1Background);
        this.add(scorep1Background);
        this.add(healthp1Background);

        //endregion

        //region Coop

        healthbarp2Background.setBackground(new Color(255, 255, 255, 95));
        dronebarp2Background.setBackground(new Color(255, 255, 255, 95));
        schipbarp2BackGround.setBackground(new Color(255, 255, 255, 95));

        this.add(healthbarp2Background);
        this.add(schipbarp2BackGround);
        this.add(dronebarp2Background);
        this.add(scorep2Background);
        this.add(healthp2Background);
        this.add(schipLvlp2Background);
        this.add(droneLvlp2Background);

        //endregion

        pane.setOpaque(true);
        pane.setBackground(new Color(255, 255, 255, 2));
        pane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));

        pauze.setOpaque(true);
        pauze.setBackground(new Color(155, 255, 204, 200));
        pauze.setBorder(null);

        //BOUNDS
        pauze.setBounds(482, 23, 60, 58);
        pane.setBounds(50, 125, 900, 500);

        //==================================================

        //Add Components
        //==================================================

        this.add(pane);
        this.add(pauze);
        this.add(startGame);

        pauze.addActionListener(evt -> {
            pauseGameLoop();
        });
    }

    private void showCoopUI(){
        healthp2Background.setVisible(true);
        scorep2Background.setVisible(true);
        schipLvlp2Background.setVisible(true);
        droneLvlp2Background.setVisible(true);
        healthbarp2Background.setVisible(true);
        dronebarp2Background.setVisible(true);
        schipbarp2BackGround.setVisible(true);
    }

    private void hideCoopUI(){
        healthp2Background.setVisible(false);
        scorep2Background.setVisible(false);
        schipLvlp2Background.setVisible(false);
        droneLvlp2Background.setVisible(false);
        healthbarp2Background.setVisible(false);
        dronebarp2Background.setVisible(false);
        schipbarp2BackGround.setVisible(false);
    }

    private void initGamePanel() throws SQLException {
        setupGameTimer();
        gamePanel.setVisible(true);
        gamePanel.setCoop(coop);
        if (coop){
            showCoopUI();
        } else{
            hideCoopUI();
        }
        gamePanel.startGame();
    }

    private void addActionListeners() {
        menuPauze.addActionListener(panel);
        startGame.addActionListener(panel);
        Continue.addActionListener(panel);
        restartPauze.addActionListener(panel);
    }

    private void checkGameFinished() {
        if (gamePanel.getGameFinished()) {
            pauze.setVisible(false);
            initEndGamePanel();
            gameTimer.stop();
            gamePanel.resetGame();
        }
    }

    private void setupGameTimer() {
        gameTimer = new Timer(10, e -> {
            checkGameFinished();
            gamePanel.revalidate();
            drawGame();
            try {
                updateGame();
            } catch (IOException | UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void initEndGamePanel() {
        gameEnd.setVisible(true);
        gamePanel.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startGame) {
            gameEnd.setVisible(false);
            gamePanel.setGameFinished(false);
            gamePanel.setFocusable(true);
            startGame.setVisible(false);
            pauze.setVisible(true);
            try {
                initGamePanel();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            runGameLoop();
        } else if (source == Continue) {
            resumeGameLoop();
        }
    }

    private void pauseGameLoop() {
        gameTimer.stop();
        gamePanel.pauseGame();
        gamePanel.setFocusable(false);
        gameTimer.stop();
        initPausePanel();
    }

    private void initPausePanel() {
        pause.setVisible(true);
    }

    private void runGameLoop() {
        gameTimer.start();
    }

    private void resumeGameLoop() {
        pause.setVisible(false);
        gamePanel.resumeGame();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(); // anders werkt de keybinds niet meer
        gameTimer.start();
    }

    private void updateGame() throws IOException, UnsupportedAudioFileException {
        gamePanel.update();
    }

    private void drawGame() {
        gamePanel.repaint();
    }

    //endregion
}
