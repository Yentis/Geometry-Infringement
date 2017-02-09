package GUI;

import GComponents.*;
import Game.Enemy;
import Game.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Yentl-PC on 20/12/2016.
 */
class Settings extends JPanel {
    private Dimension resolution;

    //region Behaviour

    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());
        String[] difficulties = {"Hard", "Normal", "Easy"};
        String[] inputs = {"Keyboard + Mouse", "Controller"};
        String[] resolutions = {"1024x768", "1280x720", "1366x768", "1440x900", "1600x900", "1680x1050", "1920x1080", "1920x1200", "2560x1440", "4096x2160"};
        JPanel middlePanel = new JPanel(new GridBagLayout());
        JPanel controlPanel = new JPanel(new GridBagLayout());
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);
        GLabel message = new GLabel(" ", false, Color.white);
        GLabel lbldifficulty = new GLabel("Difficulty: ", false, Color.white);
        JComboBox<String> difficulty = new JComboBox<>(difficulties);
        GLabel lblInput = new GLabel("Input: ", false, Color.white );
        GLabel lblWindowed = new GLabel("Windowed: ", false, Color.white);
        JCheckBox windowed = new JCheckBox();
        GLabel lblResolution = new GLabel("Resolution: ", false, Color.white);
        JComboBox<String> resolution = new JComboBox<>(resolutions);
        JComboBox<String> input = new JComboBox<>(inputs);
        GButton set = new GButton("Apply");
        GButton Back = new GButton("Back");
        JButton Exit = new GButton("Quit");
        GLabel controlLabel = new GLabel("Controls", false, Color.white);
        GLabel aim = new GLabel("Aim", false, Color.white);
        GLabel shoot = new GLabel("Shoot", false, Color.white);
        GLabel move = new GLabel("Move", false, Color.white);
        GLabel moveUp = new GLabel("Move up", false, Color.white);
        GLabel moveDown = new GLabel("Move down", false, Color.white);
        GLabel moveLeft = new GLabel("Move left", false, Color.white);
        GLabel moveRight = new GLabel("Move right", false, Color.white);
        GLabel aim2 = new GLabel("Mouse | Right joystick", false, Color.white);
        GLabel shoot2 = new GLabel("Mouse Click | R1", false, Color.white);
        GLabel move2 = new GLabel("Left joystick", false, Color.white);
        GLabel moveUp2 = new GLabel("Z", false, Color.white);
        GLabel moveDown2 = new GLabel("S", false, Color.white);
        GLabel moveLeft2 = new GLabel("Q", false, Color.white);
        GLabel moveRight2 = new GLabel("D", false, Color.white);

        resolution.setFont(new GFont(18));
        lblTitle.setFont(new GFont(65));
        difficulty.setFont(new GFont(18));
        difficulty.setSelectedItem(window.getSpel().getCurrentDifficulty());
        input.setFont(new GFont(18));
        middlePanel.setOpaque(false);
        controlPanel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridwidth = 4;
        c.insets = new Insets(20, 20, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblTitle, c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 40, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(message, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(lbldifficulty, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.CENTER;
        difficulty.setPreferredSize(input.getPreferredSize());
        middlePanel.add(difficulty, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.insets = new Insets(15, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(lblInput, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(input, c);

        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(lblWindowed, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(windowed, c);

        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(lblResolution, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(resolution, c);

        c.gridy = 5;
        c.gridwidth = 1;
        c.insets = new Insets(40, 0, 20, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        middlePanel.add(controlLabel, c);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(aim, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(aim2, c);

        c.gridx = 0;
        c.gridy = 7;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(shoot, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(shoot2, c);

        c.gridx = 0;
        c.gridy = 8;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(move, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(move2, c);

        c.gridx = 0;
        c.gridy = 9;
        c.insets = new Insets(20, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(moveUp, c);

        c.gridx = 1;
        c.insets = new Insets(20, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(moveUp2, c);

        c.gridx = 0;
        c.gridy = 10;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(moveDown, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(moveDown2, c);

        c.gridx = 0;
        c.gridy = 11;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(moveLeft, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(moveLeft2, c);

        c.gridx = 0;
        c.gridy = 12;
        c.insets = new Insets(0, 0, 0, 40);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(moveRight, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(moveRight2, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        add(middlePanel, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 20);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(Exit, c);
        c.insets = new Insets(0, 0, 20, 130);
        add(Back, c);
        c.insets = new Insets(0, 0, 20, 250);
        add(set, c);

        //Action Listeners
        set.addActionListener(evt -> {
            new Sound("click");
            List<Enemy> enemies = new ArrayList<>();
            String currentDifficulty = window.getSpel().getCurrentDifficulty();
            String currentControls = window.getSpel().getCurrentControls();
            Dimension currentResolution = window.getSpel().getScreenSize();
            String selectedDifficulty = difficulty.getSelectedItem().toString();
            String selectedControls = input.getSelectedItem().toString();
            String selectedResolution = resolution.getSelectedItem().toString();
            int width = parseInt(selectedResolution.substring(0, selectedResolution.indexOf("x")));
            int height = parseInt(selectedResolution.substring(selectedResolution.indexOf("x") + 1));
            Dimension chosenResolution = new Dimension(width, height);

            if(!Objects.equals(currentDifficulty, selectedDifficulty)){
                window.getSpel().setCurrentDifficulty(selectedDifficulty);

                for (Enemy enemy: window.getSpel().getEnemies())
                {
                    enemy.setDifficulty(selectedDifficulty);
                    enemies.add(enemy);
                }
                window.getSpel().setEnemies(enemies);
                message.setText("Settings changed.");
            }

            if (!Objects.equals(selectedControls, currentControls)){
                window.getSpel().setCurrentControls(selectedControls);
                message.setText("Settings changed.");
            }

            if (currentResolution != chosenResolution){
                window.setSize(chosenResolution);
                window.getSpel().setScreenSize(chosenResolution);
                message.setText("Settings changed.");
            }
        });

        Back.addActionListener(evt -> {
            new Sound("click");
            window.getCl().show(window.getCards(), "mainmenupanel");
        });

        windowed.addItemListener(evt -> {
            new Sound("click");
            if(evt.getStateChange() == ItemEvent.SELECTED){
                window.setExtendedState(JFrame.NORMAL);
                window.dispose();
                window.setUndecorated(false);
                window.setVisible(true);
            } else {
                window.setExtendedState(JFrame.MAXIMIZED_BOTH);
                window.dispose();
                window.setUndecorated(true);
                window.setVisible(true);
            }
        });
    }

    //endregion
}