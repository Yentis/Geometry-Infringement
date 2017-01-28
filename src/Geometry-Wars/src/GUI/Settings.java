package GUI;

import GComponents.*;
import Game.Enemy;
import Game.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

/**
 * Created by Yentl-PC on 20/12/2016.
 */
class Settings extends GPanel {
    //region Instance Variables

    private Settings panel = this;

    //endregion

    //region Behaviour

    @Override
    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D horitzontalLine = new Line2D.Float(220, 310, 820,310);

        g2.setColor(Color.white);
        g2.draw(horitzontalLine);
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        String[] difficulties = {"Hard", "Normal", "Easy"};
        String[] inputs = {"Keyboard + Mouse", "Controller"};
        JLabel label = new GLabel("Geometry Wars", 65f, 25, 25, 650, 100, true, Color.darkGray);
        GLabel message = new GLabel("", 24f, 240,120,600,50, false, Color.white);
        GLabel lbldifficulty = new GLabel("Difficulty: ", 24f, 220,170,150,50, false, Color.white);
        JComboBox<String> difficulty = new JComboBox<>(difficulties);
        GLabel lblInput = new GLabel("Input: ", 24f,220,230, 150,50, false, Color.white );
        JComboBox<String> input = new JComboBox<>(inputs);
        GButton set = new GButton("Set", 24f, 720,170,100,50);
        GButton Back = new GButton("Back", 24f, 820,650, 170, 63);
        JLabel backgroundpane = new GPane(220, 300, 600, 300);
        GLabel controlLabel = new GLabel("Controls", 24f, 450,315,600,50, false, Color.white);
        GLabel aim = new GLabel("Aim", 24f, 230,350,600,50, false, Color.white);
        GLabel shoot = new GLabel("Shoot", 24f, 230,380,600,50, false, Color.white);
        GLabel move = new GLabel("Move", 24f, 230,410,600,50, false, Color.white);
        GLabel moveUp = new GLabel("Move up", 24f, 230,460,600,50, false, Color.white);
        GLabel moveDown = new GLabel("Move down", 24f, 230,490,600,50, false, Color.white);
        GLabel moveLeft = new GLabel("Move left", 24f, 230,520,600,50, false, Color.white);
        GLabel moveRight = new GLabel("Move right", 24f, 230,550,600,50, false, Color.white);
        GLabel aim2 = new GLabel("Mouse | Right joystick", 24f, 420,350,600,50, false, Color.white);
        GLabel shoot2 = new GLabel("Mouse Click | R1", 24f, 420,380,600,50, false, Color.white);
        GLabel move2 = new GLabel("Left joystick", 24f, 420,410,600,50, false, Color.white);
        GLabel moveUp2 = new GLabel("Z", 24f, 420,460,600,50, false, Color.white);
        GLabel moveDown2 = new GLabel("S", 24f, 420,490,600,50, false, Color.white);
        GLabel moveLeft2 = new GLabel("Q", 24f, 420,520,600,50, false, Color.white);
        GLabel moveRight2 = new GLabel("D", 24f, 420,550,600,50, false, Color.white);

        difficulty.setFont(new GFont(18));
        difficulty.setSelectedItem(window.getSpel().getCurrentDifficulty());
        message.setVisible(false);

        backgroundpane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        backgroundpane.setBackground(new Color(0,0,0,150));

        difficulty.setBounds(420,170,250,50);
        input.setBounds(420,230,250,50);
        input.setFont(new GFont(18));

        this.add(label);
        this.add(lbldifficulty);
        this.add(lblInput);
        this.add(message);
        this.add(difficulty);
        this.add(input);
        this.add(set);
        this.add(Back);
        this.add(message);
        this.add(controlLabel);
        this.add(aim);
        this.add(shoot);
        this.add(move);
        this.add(moveUp);
        this.add(moveDown);
        this.add(moveLeft);
        this.add(moveRight);
        this.add(aim2);
        this.add(shoot2);
        this.add(move2);
        this.add(moveUp2);
        this.add(moveDown2);
        this.add(moveLeft2);
        this.add(moveRight2);
        this.add(backgroundpane);

        //Action Listeners
        set.addActionListener(evt -> {
            new Sound("click");
            List<Enemy> enemies = new ArrayList<>();
            String currentDifficulty = window.getSpel().getCurrentDifficulty();
            String currentControls = window.getSpel().getCurrentControls();
            String selectedDifficulty = difficulty.getSelectedItem().toString();
            String selectedControls = input.getSelectedItem().toString();

            if(Objects.equals(currentDifficulty, selectedDifficulty)){
                message.setText("Difficulty set.");
            } else {
                //TODO fix this shit
                window.getSpel().setCurrentDifficulty(selectedDifficulty);

                for (Enemy enemy: window.getSpel().getEnemies())
                {
                    enemy.setDifficulty(selectedDifficulty);
                    enemies.add(enemy);
                }
                window.getSpel().setEnemies(enemies);
                message.setText("Difficulty set.");
            }

            if (!Objects.equals(selectedControls, currentControls)){
                window.getSpel().setCurrentControls(selectedControls);
                message.setText("Input set.");
            }
            message.setVisible(true);
        });

        Back.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            window.getMainMenu().setVisible(true);
        });
    }

    //endregion
}