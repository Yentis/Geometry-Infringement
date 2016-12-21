package GUI;

import GComponents.GButton;
import GComponents.GLabel;
import GComponents.GPanel;
import Game.Enemy;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Yentl-PC on 19/12/2016.
 */
public class Coop extends GPanel implements ActionListener {
    private Coop panel = this;

    private GamePanel gamePanel;


    private GButton startGame = new GButton("Start", 24f, 200, 200, 500, 200);
    private Timer gameTimer;

    //PausePanel
    private GButton Continue = new GButton("Continue", 24f, 325, 84, 375, 120);
    private GButton restart = new GButton("Restart", 24f, 325, 284, 375, 120);
    private GButton menu = new GButton("Back to Main Menu", 24f, 325, 484, 375, 120);
    private GPanel pause = new GPanel() {
        @Override
        public void initComponents() throws IOException, FontFormatException {
            pause.add(Continue);
            pause.add(restart);
            pause.add(menu);
        }
    };



    public Coop(List<Enemy> enemies) throws IOException, FontFormatException {
        gamePanel = new GamePanel(enemies);
        initComponents();
        panel.add(gamePanel);
        gamePanel.setOpaque(false);
        panel.add(startGame);
        startGame.addActionListener(this);
        gamePanel.setVisible(false);

    }



    public void initGamePanel(){
        setupGameTimer();
        gamePanel.setVisible(true);
        gamePanel.requestFocus();
        gamePanel.setCoop(true);
        gamePanel.startGame();
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        ImageIcon PauseImage = new ImageIcon("resources/Media/pause-128.png");
        JButton pauze = new JButton(PauseImage);
        JLabel pane = new JLabel();
        JLabel pauzepane = new JLabel();

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
        pane.setBackground(new Color(255,255,255,2));
        pane.setBorder(BorderFactory.createMatteBorder(
                5, 5, 5, 5, Color.green));

        pauzepane.setOpaque(true);
        pauzepane.setBackground(new Color(255,255,255,50));
        pauzepane.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.cyan));


        healthbarp1.setBackground(new Color(255,255,255,95));
        healthbarp2.setBackground(new Color(255,255,255,95));
        dronebarp1.setBackground(new Color(255,255,255,95));
        dronebarp2.setBackground(new Color(255,255,255,95));
        schipbarp1.setBackground(new Color(255,255,255,95));
        schipbarp2.setBackground(new Color(255,255,255,95));

        pauze.setOpaque(true);
        pauze.setBackground(new Color(155,255,204,200));
        pauze.setBorder(null);

        //BOUNDS
        pauze.setBounds(480,23,60,58);
        pane.setBounds(50,125,900,500);
        pauzepane.setBounds(395,265,370,245);

        pauzepane.setVisible(false);
        Yes.setVisible(false);
        No.setVisible(false);
        confirmationlabel.setVisible(false);



        //==================================================

        //Add Components
        //==================================================
        this.add(pane);
        this.add(healthbarp1);
        this.add(healthbarp2);
        this.add(schipbarp1);
        this.add(schipbarp2);
        this.add(dronebarp1);
        this.add(dronebarp2);
        this.add(pauze);
        this.add(scorep1);
        this.add(scorep2);
        this.add(healthp1);
        this.add(healthp2);
        this.add(schipLvlp1);
        this.add(schipLvlp2);
        this.add(droneLvlp1);
        this.add(droneLvlp2);
        this.add(pauzepane);
        //this.add(Quit);
        this.add(confirmationlabel);
        this.add(Yes);
        this.add(No);




        /*Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(true);
                Yes.setVisible(true);
                No.setVisible(true);
                confirmationlabel.setVisible(true);
            }



        });*/

        /*No.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(false);
                Yes.setVisible(false);
                No.setVisible(false);
                confirmationlabel.setVisible(false);
            }



        });

        Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauzepane.setVisible(false);
                Yes.setVisible(false);
                No.setVisible(false);
                confirmationlabel.setVisible(false);
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getScoreboard().setVisible(true);

            }



        });*/

       /* pauze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                pauseGameLoop();
            }



        });*/





    }


    public void addActionListeners() { //TODO
        startGame.addActionListener(panel);
        Continue.addActionListener(panel);
    }





    public void setupGameTimer() {
        gameTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawGame();
                updateGame();
            }
        });
    }

    public void initPausePanel() {
        pause.setVisible(true);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        System.out.println(e);
        if (source == startGame) {
            startGame.setVisible(false);
            initGamePanel();
            runGameLoop();
        } else if (source == Continue) {
            resumeGameLoop();
        }
    }

    private void pauseGameLoop() {
        gamePanel.pauseGame();
        gamePanel.setFocusable(false);
        initPausePanel();
        gameTimer.stop();
    }

    private void runGameLoop() {
        gameTimer.start();
    }

    private void resumeGameLoop() {
        pause.setVisible(false);
        gamePanel.startGame();
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
