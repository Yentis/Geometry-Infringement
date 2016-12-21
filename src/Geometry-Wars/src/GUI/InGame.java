package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

import GComponents.*;
import Game.Enemy;


/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class InGame extends GPanel implements ActionListener {
    private InGame panel = this;
    private boolean coop;

    //GamePanel
    private GamePanel gamePanel;

    private GButton startGame = new GButton("Click to Start", 35f, 280, 250, 400, 200);
    private Timer gameTimer;

    //PausePanel
    private GButton Continue = new GButton("Continue", 24f, 315, 260, 375, 120);
    private GButton restartPauze = new GButton("Restart", 24f, 150, 420, 275, 120);
    private GButton menuPauze = new GButton("Main Menu", 24f, 550, 420, 275, 120);
    private GButton restartGameEnd = new GButton("Restart", 24f, 150, 420, 275, 120);
    private GButton menuGameEnd = new GButton("Main Menu", 24f, 550, 420, 275, 120);
    private ImageIcon PauseImage = new ImageIcon("resources\\Media\\pause-128.png");
    private JButton pauze = new JButton(PauseImage);
    private GLabel gameOver = new GLabel("Oopsy daisy! u dead fam", 25f, 325,260,375,120, true, Color.white);



    private GPanel pause = new GPanel() {
        @Override
        public void initComponents() throws IOException, FontFormatException {







            Continue.setBackground(Color.black);
            Continue.setForeground(Color.white);
            Continue.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));


            restartPauze.setBackground(Color.black);
            restartPauze.setForeground(Color.white);
            restartPauze.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));


            menuPauze.setBackground(Color.black);
            menuPauze.setForeground(Color.white);
            menuPauze.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));


            pause.add(Continue);
            pause.add(restartPauze);
            pause.add(menuPauze);



        }
    };
    private GPanel gameEnd = new GPanel() {
        @Override
        public void initComponents() throws IOException, FontFormatException {

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

            menuGameEnd.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    panel.setVisible(false);
                    GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                    window.getMainMenu().setVisible(true);

                }
            });
        }
    };

    public InGame(List<Enemy> enemies) throws IOException, FontFormatException {
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

    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {

        JLabel pane = new JLabel();

        GLabel healthp1 = new GLabel("Health:", 24, 40,18,169,62, false, Color.black);
        GLabel healthp2 = new GLabel("Health:", 24, 595,18,169,62, false, Color.black);
        GLabel scorep1 = new GLabel("Score:", 24, 25,65,169,62, false, Color.cyan);
        GLabel scorep2 = new GLabel("Score:", 24, 580,65,169,62, false, Color.cyan);
        GLabel schipLvlp1 = new GLabel("Ship lvl:", 24, 25,667,222,62, false, Color.green);
        GLabel schipLvlp2 = new GLabel("Ship lvl:", 24, 580,667,222,62, false, Color.green);
        GLabel droneLvlp1 = new GLabel("Drone lvl:", 24, 250,667,222,62, false, new Color(155,255,204));
        GLabel droneLvlp2 = new GLabel("Drone lvl:", 24, 800,667,222,62, false, new Color(155,255,204));
        GLabel healthbarp1 = new GLabel("", 24, 15,23,434,47, true, Color.black);
        GLabel healthbarp2 = new GLabel("", 24, 570,23,434,47, true, Color.black);
        GLabel dronebarp1 = new GLabel("", 24, 380,672,70,47, true, Color.black);
        GLabel dronebarp2 = new GLabel("", 24, 930,672,70,47, true, Color.black);
        GLabel schipbarp1 = new GLabel("", 24, 140,672,100,47, true, Color.black);
        GLabel schipbarp2 = new GLabel("", 24, 690,672,100,47, true, Color.black);
        GLabel confirmationlabel = new GLabel("Are you sure?", 30, 470,285,500,60, false, Color.cyan);
        //JButton Quit = new GButton("Quit", 24f, 20, 675, 100, 47);
        JButton Yes = new GButton("Yes", 24f, 455, 380, 100, 47);
        JButton No = new GButton("No", 24f, 595, 380, 100, 47);

        pane.setOpaque(true);
        pane.setBackground(new Color(255, 255, 255, 2));
        pane.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
        healthbarp1.setBackground(new Color(255,255,255,95));
        healthbarp2.setBackground(new Color(255,255,255,95));
        dronebarp1.setBackground(new Color(255,255,255,95));
        dronebarp2.setBackground(new Color(255,255,255,95));
        schipbarp1.setBackground(new Color(255,255,255,95));
        schipbarp2.setBackground(new Color(255,255,255,95));
        pauze.setOpaque(true);
        pauze.setBackground(new Color(155, 255, 204, 200));
        pauze.setBorder(null);

        //BOUNDS
        pauze.setBounds(510, 23, 60, 58);
        pane.setBounds(50, 125, 900, 500);

        Yes.setVisible(false);
        No.setVisible(false);
        confirmationlabel.setVisible(false);


        //==================================================

        //Add Components
        //==================================================
        if(coop){
            this.add(healthbarp2);
            this.add(schipbarp2);
            this.add(dronebarp2);
            this.add(scorep2);
            this.add(healthp2);
            this.add(schipLvlp2);
            this.add(droneLvlp2);
        }
        this.add(pane);
        this.add(healthbarp1);

        //this.add(dronebarp1);
        this.add(pauze);


        this.add(scorep1);
        this.add(healthp1);
        this.add(startGame);
        /*this.add(schipLvlp1);
        this.add(droneLvlp1);*/



        //this.add(Quit);
        this.add(confirmationlabel);
        this.add(Yes);
        this.add(No);

        pauze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //panel.setVisible(false);
                pauseGameLoop();
                //pause.setVisible(true);
                //pane.setVisible(true);
            }


        });






    }

    public GButton getStartGame() {
        return startGame;
    }

    public GPanel getGameEnd() {
        return gameEnd;
    }

    public void initGamePanel() {
        setupGameTimer();
        gamePanel.setVisible(true);
        gamePanel.setCoop(coop);
        gamePanel.startGame();
    }

    public void addActionListeners() { //TODO
        startGame.addActionListener(panel);
        Continue.addActionListener(panel);
        restartPauze.addActionListener(panel);

    }

    public void checkGameFinished(){
        if (gamePanel.getGameFinished()) {
            pauze.setVisible(false);
            initEndGamePanel();
            gameTimer.stop();
            System.out.println("game over");
            gamePanel.resetGame();
        }
    }

    public void setupGameTimer() {
        gameTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGameFinished();
                gamePanel.revalidate();
                drawGame();
                updateGame();
            }
        });
    }

    public void initPausePanel() {
        pause.setVisible(true);
    }

    public void initEndGamePanel() {
        gameEnd.setVisible(true);

        gamePanel.setFocusable(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startGame) {
            gameEnd.setVisible(false);
            gamePanel.setGameFinished(false);
            startGame.setVisible(false);
            initGamePanel();
            runGameLoop();
        } else if (source == Continue) {
            resumeGameLoop();
        }
    }

    private void pauseGameLoop() {
        gameTimer.stop();
        gamePanel.pauseGame();
        gamePanel.setFocusable(false);
        initPausePanel();
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

    private void updateGame() {
        gamePanel.update();
    }

    private void drawGame() {
        gamePanel.repaint();
    }

}
