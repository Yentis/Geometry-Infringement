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
import Game.Sound;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
class InGame extends JPanel implements ActionListener {
    //region Instance Variables

    private Sound backgroundMusic;
    private boolean coop;

    //GamePanel
    private GamePanel gamePanel;

    private GButtonPause startGame = new GButtonPause("Click to Start");
    private Timer gameTimer;

    //PausePanel
    private GButtonPause Continue = new GButtonPause("Continue");
    private GButtonPause menuPauze = new GButtonPause("Main Menu");

    private GButtonPause menuGameEnd = new GButtonPause("Main Menu");
    private ImageIcon PauseImage = new ImageIcon(((new ImageIcon("resources\\Media\\pause-128.png")).getImage()).getScaledInstance(60, 58, java.awt.Image.SCALE_SMOOTH));
    private JButton pauze = new JButton(PauseImage);
    private GLabel gameOver = new GLabel("Oopsy daisy! u dead fam", true, Color.white);
    private GLabel nuggetAmount = new GLabel("", false, Color.white);
    private JPanel pause = new JPanel(new GridBagLayout());
    private JPanel gameEnd = new JPanel(new GridBagLayout());

    //coop
    private GLabel scorep2Background;
    private GLabel shipLvlp2Background;
    private GLabel droneLvlp2Background;

    //endregion

    //region Constructors
    InGame() throws IOException, FontFormatException {
        gamePanel = new GamePanel();
        initPause();
        initComponents();
        addActionListeners();
    }

    //endregion

    //region Getters & Setters

    void setCoop(boolean coop) {
        this.coop = coop;
    }

    GButtonPause getStartGame() {
        return startGame;
    }

    JPanel getGameEnd() {
        return gameEnd;
    }

    JPanel getPause() {
        return pause;
    }

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        JLabel pane = new JLabel();
        JPanel middlePanel = new JPanel(new GridBagLayout());
        GLabel scorep1Background = new GLabel("Score:", false, Color.cyan);
        GLabel shipLvlp1Background = new GLabel("Ship:", false, Color.green);
        GLabel droneLvlp1Background = new GLabel("Drone:", false, new Color(155, 255, 204));
        scorep2Background = new GLabel("Score:", false, Color.cyan);
        shipLvlp2Background = new GLabel("Ship:", false, Color.green);
        droneLvlp2Background = new GLabel("Drone:", false, new Color(155, 255, 204));

        gameOver.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        gameOver.setBackground(Color.black);
        pane.setBackground(new Color(255, 255, 255, 2));
        pane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
        startGame.setFont(new GFont(35f));
        shipLvlp1Background.setFont(new GFont(20f));
        droneLvlp1Background.setFont(new GFont(20f));
        shipLvlp2Background.setFont(new GFont(20f));
        droneLvlp2Background.setFont(new GFont(20f));
        middlePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
        middlePanel.setOpaque(false);
        pause.setOpaque(false);
        gameEnd.setOpaque(false);
        pause.setVisible(false);
        gameEnd.setVisible(false);
        gamePanel.setOpaque(false);
        gamePanel.setVisible(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 0;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        pauze.setBorderPainted(false);
        pauze.setContentAreaFilled(false);
        pauze.setFocusPainted(false);
        pauze.setOpaque(false);
        add(pauze, c);

        c.gridx = 0;
        c.ipadx = 0;
        c.insets = new Insets(60, 20, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(scorep1Background, c);

        c.gridx = 2;
        c.insets = new Insets(60, 0, 0, 340);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(scorep2Background, c);

        c.gridx = 0;
        c.insets = new Insets(0, 20, 20, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(shipLvlp1Background, c);

        c.insets = new Insets(0, 240, 20, 0);
        add(droneLvlp1Background, c);

        c.gridx = 2;
        c.insets = new Insets(0, 0, 20, 400);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(shipLvlp2Background, c);

        c.insets = new Insets(0, 0, 20, 160);
        add(droneLvlp2Background, c);

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 10;
        c.ipady = 10;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 50);
        c.anchor = GridBagConstraints.CENTER;
        pause.add(Continue, c);

        c.insets = new Insets(0, 0, 20, 0);
        gameEnd.add(nuggetAmount, c);

        c.gridy = 1;
        gameEnd.add(gameOver, c);

        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        gameEnd.add(menuGameEnd, c);

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 50, 0, 0);
        pause.add(menuPauze, c);

        c.gridx = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        middlePanel.add(pause, c);
        middlePanel.add(gameEnd, c);
        middlePanel.add(startGame, c);

        c.gridx = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(100, 50, 100, 50);
        add(middlePanel, c);

        c.insets = new Insets(0, 0, 0, 0);
        add(gamePanel, c);

        pauze.addActionListener(evt -> {
            new Sound("click");
            pauseGameLoop();
        });

        menuGameEnd.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            try {
                window.getSpel().submitScore(gamePanel.getScore());
                window.getSpel().logIn(window.getSpel().getSpeler().getGebruikersnaam());
                window.getSpel().checkRank(window.getSpel().getSpeler());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            window.getCl().show(window.getCards(), "mainmenupanel");
        });
    }

    private void initPause(){
        menuPauze.addActionListener(evt -> {
            new Sound("click");
            gamePanel.setGameFinished(true);
            backgroundMusic.stopMusic();
            gameEnd.setVisible(false);

            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "mainmenupanel");
        });
    }

    private void showCoopUI(){
        scorep2Background.setVisible(true);
        shipLvlp2Background.setVisible(true);
        droneLvlp2Background.setVisible(true);
    }

    private void hideCoopUI(){
        scorep2Background.setVisible(false);
        shipLvlp2Background.setVisible(false);
        droneLvlp2Background.setVisible(false);
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
        backgroundMusic = new Sound("mainmenu");
    }

    private void addActionListeners() {
        menuPauze.addActionListener(this);
        startGame.addActionListener(this);
        Continue.addActionListener(this);
    }

    private void checkGameFinished() {
        if (gamePanel.getGameFinished()) {
            backgroundMusic.stopMusic();
            pauze.setVisible(false);
            initEndGamePanel();
            gameTimer.stop();
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
        nuggetAmount.setText("You obtained: " + gamePanel.getScore() / 10000 + " nuggets!");
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
        backgroundMusic.pauseMusic();
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
        backgroundMusic.playMusic();
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
