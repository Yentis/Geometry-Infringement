package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import GComponents.*;
import Game.Drone;
import Game.Enemy;
import Game.Kogel;
import Game.Schip;


/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class InGameSinglePlayer extends GPanel implements ActionListener{
    private InGameSinglePlayer panel = this;

    private GamePanel gamePanel = new GamePanel();
    private boolean running = false;
    private boolean paused = false;
    private int FPS = 60;
    private int frameCount = 0;
    private GButton startGame = new GButton("Start", 24f, 200, 200, 500, 200);
    private int lastFpsTime;



    public InGameSinglePlayer() throws IOException, FontFormatException {
        initComponents();
        panel.add(gamePanel);
        gamePanel.setOpaque(false);
        panel.add(startGame);
        startGame.addActionListener(this);
        gamePanel.setVisible(false);

    }


    public boolean startRunning(){
        return running = true;
    }

    public void initGamePanel(){
        gamePanel.setVisible(true);
        gamePanel.requestFocus();
        gamePanel.startGame();
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {


        ImageIcon PauseImage = new ImageIcon("src\\Media\\pause-128.png");
        JButton pauze = new JButton(PauseImage);
        JLabel pane = new JLabel();
        JLabel pauzepane = new JLabel();

        GLabel health = new GLabel("Health:", 24, 40,18,169,62, false, Color.cyan);
        GLabel score = new GLabel("Score:", 24, 606,18,169,62, false, Color.cyan);
        GLabel schipLvl = new GLabel("Ship lvl:", 24, 200,667,222,62, false, Color.green);
        GLabel droneLvl = new GLabel("Drone lvl:", 24, 600,667,222,62, false, new Color(155,255,204));
        GLabel healthbar = new GLabel("", 24, 15,23,434,47, true, Color.black);
        GLabel dronebar = new GLabel("", 24, 753,672,216,47, true, Color.black);
        GLabel schipbar = new GLabel("", 24, 350,672,216,47, true, Color.black);
        GLabel confirmationlabel = new GLabel("Are you sure?", 30, 470,285,500,60, false, Color.cyan);
        JButton Quit = new GButton("Quit", 24f, 20, 675, 100, 47);
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


        healthbar.setBackground(new Color(255,255,255,95));
        dronebar.setBackground(new Color(255,255,255,95));
        schipbar.setBackground(new Color(255,255,255,95));

        pauze.setOpaque(true);
        pauze.setBackground(new Color(155,255,204,200));
        pauze.setBorder(null);

        //BOUNDS
        pauze.setBounds(510,23,60,58);
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
        this.add(healthbar);
        this.add(schipbar);
        this.add(dronebar);
        this.add(pauze);
        this.add(score);
        this.add(health);
        this.add(schipLvl);
        this.add(droneLvl);
        this.add(pauzepane);
        this.add(Quit);
        this.add(confirmationlabel);
        this.add(Yes);
        this.add(No);




        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                pauzepane.setVisible(true);
                Yes.setVisible(true);
                No.setVisible(true);
                confirmationlabel.setVisible(true);
            }



        });

        No.addActionListener(new java.awt.event.ActionListener() {
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



        });



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startGame){
            startGame.setVisible(false);
             initGamePanel();
            startRunning();
            if(running){
                runGameLoop();
            }
        }
    }

    private void runGameLoop() {
        Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };
        loop.start();
    }


    // TL;DR : zorgt ervoor dat de loop binnen de 60 fps blijft EN zorgt ervoor dat de game constant repaint; - Renzie
    private void gameLoop(){
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        // keep looping round til the game ends
        while (running)
        {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            //double delta = updateLength / ((double)OPTIMAL_TIME);

            // update the frame counter
            lastFpsTime += updateLength;
            FPS++;

            // update our FPS counter if a second has passed since
            // we last recorded
            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+FPS+")");
                lastFpsTime = 0;
                FPS= 0;
            }

            // update the game logic
            updateGame();

            // draw everyting
            drawGame();

            // we want each frame to take 10 milliseconds, to do this
            // we've recorded when we started the frame. We add 10 milliseconds
            // to this and then factor in the current time to give
            // us our final value to wait for
            // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
            try{
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void updateGame(){
        gamePanel.update();
    }

    private void drawGame(){
        //gamePanel.repaint();
    }
}
