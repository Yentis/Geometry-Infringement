package GUI;

import GComponents.*;
import Game.Enemy;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

/**
 * Created by Yentl-PC on 20/12/2016.
 */
public class Settings extends GPanel {
    private Settings panel = this;

    public Settings() throws MalformedURLException, IOException, FontFormatException {
        //do not init
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        String[] difficulties = {"Hard", "Normal", "Easy"};

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        GLabel message = new GLabel("", 24f, 240,120,600,50, false, Color.white);
        GLabel lbldifficulty = new GLabel("Difficulty: ", 24f, 200,170,150,50, false, Color.white);
        JComboBox<String> difficulty = new JComboBox<String>(difficulties);
        GButton set = new GButton("Set", 24f, 200,240,170,50);
        GButton Back = new GButton("Back", 24f, 635,650, 170, 63);
        JButton Exit = new GButton("Exit", 24f, 820, 650, 170, 63);

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));
        difficulty.setFont(new GFont(24));
        difficulty.setSelectedItem(window.getSpel().getCurrentDifficulty());
        message.setVisible(false);

        label.setBounds(25,25,650,100);
        difficulty.setBounds(370,170,150,50);

        this.add(label);
        this.add(lbldifficulty);
        this.add(message);
        this.add(difficulty);
        this.add(set);
        this.add(Back);
        this.add(Exit);
        this.add(message);

        //Action Listeners
        set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                List<Enemy> enemies = new ArrayList<>();
                String currentDifficulty = window.getSpel().getCurrentDifficulty();
                String selectedDifficulty = difficulty.getSelectedItem().toString();

                if(Objects.equals(currentDifficulty, selectedDifficulty)){
                    message.setText("Difficulty set.");
                } else {
                    for (Enemy enemy:window.getSpel().getEnemies())
                    {
                        switch(selectedDifficulty){
                            case "Hard":
                                if(Objects.equals(currentDifficulty, "Normal")){
                                    enemy.setHp(enemy.getHP()*2);
                                    enemy.setKracht(enemy.getKracht()*2);
                                    enemy.setSpeed(enemy.getSpeed()*2);
                                } else if (Objects.equals(currentDifficulty, "Easy")) {
                                    enemy.setHp(enemy.getHP()*4);
                                    enemy.setKracht(enemy.getKracht()*4);
                                    enemy.setSpeed(enemy.getSpeed()*4);
                                }
                                break;
                            case "Normal":
                                if(Objects.equals(currentDifficulty, "Hard")){
                                    enemy.setHp(enemy.getHP()/2);
                                    enemy.setKracht(enemy.getKracht()/2);
                                    enemy.setSpeed(enemy.getSpeed()/2);
                                } else if (Objects.equals(currentDifficulty, "Easy")) {
                                    enemy.setHp(enemy.getHP()*2);
                                    enemy.setKracht(enemy.getKracht()*2);
                                    enemy.setSpeed(enemy.getSpeed()*2);
                                }
                                break;
                            case "Easy":
                                if(Objects.equals(currentDifficulty, "Hard")){
                                    enemy.setHp(enemy.getHP()/4);
                                    enemy.setKracht(enemy.getKracht()/4);
                                    enemy.setSpeed(enemy.getSpeed()/4);
                                } else if (Objects.equals(currentDifficulty, "Normal")) {
                                    enemy.setHp(enemy.getHP()/2);
                                    enemy.setKracht(enemy.getKracht()/2);
                                    enemy.setSpeed(enemy.getSpeed()/2);
                                }
                                break;
                        }

                        enemies.add(enemy);
                    }
                    window.getSpel().setEnemies(enemies);
                    message.setText("Difficulty set.");
                }
                message.setVisible(true);
            }
        });

        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getLogin().setVisible(true);
            }
        });
    }
}
