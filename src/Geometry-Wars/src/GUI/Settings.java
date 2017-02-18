package GUI;

import GComponents.*;
import Game.Enemy;
import Game.Sound;
import Game.Spel;

import javax.swing.*;
import javax.tools.Tool;
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
        Spel spel = window.getSpel();
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
        GInputField moveUp2 = new GInputField(set, 10);
        GInputField moveDown2 = new GInputField(set, 10);
        GInputField moveLeft2 = new GInputField(set, 10);
        GInputField moveRight2 = new GInputField(set, 10);

        moveUp2.setDocument(new JTextFieldLimit(1));
        moveUp2.setText(spel.getKeys().get(0));
        moveDown2.setDocument(new JTextFieldLimit(1));
        moveDown2.setText(spel.getKeys().get(1));
        moveLeft2.setDocument(new JTextFieldLimit(1));
        moveLeft2.setText(spel.getKeys().get(2));
        moveRight2.setDocument(new JTextFieldLimit(1));
        moveRight2.setText(spel.getKeys().get(3));
        resolution.setFont(new GFont(18));
        lblTitle.setFont(new GFont(65));
        difficulty.setFont(new GFont(18));
        difficulty.setSelectedItem(spel.getCurrentDifficulty());
        Dimension res = spel.getScreenSize();
        resolution.setSelectedItem((int)(res.getWidth()) + "x" + (int)(res.getHeight()));
        windowed.setSelected(spel.isWindowed());
        input.setSelectedItem(spel.getCurrentControls());
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
        c.insets = new Insets(0, 0, 20, 140);
        add(Back, c);
        c.insets = new Insets(0, 0, 20, 270);
        add(set, c);

        //Action Listeners
        set.addActionListener(evt -> {
            new Sound("click");
            List<Enemy> enemies = new ArrayList<>();
            String currentDifficulty = spel.getCurrentDifficulty();
            String currentControls = spel.getCurrentControls();
            Dimension currentResolution = spel.getScreenSize();
            ArrayList<String> currentKeys = spel.getKeys();
            String selectedDifficulty = difficulty.getSelectedItem().toString();
            String selectedControls = input.getSelectedItem().toString();
            String selectedResolution = resolution.getSelectedItem().toString();
            ArrayList<String> selectedKeys = new ArrayList<>();
            int width = parseInt(selectedResolution.substring(0, selectedResolution.indexOf("x")));
            int height = parseInt(selectedResolution.substring(selectedResolution.indexOf("x") + 1));
            Dimension chosenResolution = new Dimension(width, height);

            selectedKeys.add(moveUp2.getText());
            selectedKeys.add(moveDown2.getText());
            selectedKeys.add(moveLeft2.getText());
            selectedKeys.add(moveRight2.getText());

            if(!Objects.equals(currentDifficulty, selectedDifficulty)){
                spel.setCurrentDifficulty(selectedDifficulty);

                for (Enemy enemy: spel.getEnemies())
                {
                    enemy.setDifficulty(selectedDifficulty);
                    enemies.add(enemy);
                }
                spel.setEnemies(enemies);
                message.setText("Settings changed.");
            }

            if (!Objects.equals(selectedControls, currentControls)){
                spel.setCurrentControls(selectedControls);
                message.setText("Settings changed.");
            }

            if (currentResolution != chosenResolution){
                window.setSize(chosenResolution);
                window.setBackgroundPane(chosenResolution);
                spel.setScreenSize(chosenResolution);
                message.setText("Settings changed.");
            }

            if(currentKeys != selectedKeys){
                spel.setKeys(selectedKeys);
                message.setText("Keybinds changed.");
            }
        });

        Back.addActionListener(evt -> {
            new Sound("click");
            window.getCl().show(window.getCards(), "mainmenupanel");
        });

        windowed.addItemListener(evt -> {
            new Sound("click");
            if(evt.getStateChange() == ItemEvent.SELECTED){
                spel.setWindowed(true);
                window.setExtendedState(JFrame.NORMAL);
                window.dispose();
                window.setUndecorated(false);
                window.setVisible(true);
            } else {
                Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                spel.setWindowed(false);
                spel.setScreenSize(size);
                window.setBackgroundPane(size);
                resolution.setSelectedItem((int)(size.getWidth()) + "x" + (int)(size.getHeight()));
                window.setExtendedState(JFrame.MAXIMIZED_BOTH);
                window.dispose();
                window.setUndecorated(true);
                window.setVisible(true);
            }
        });


    }

    //endregion
}