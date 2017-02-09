package GUI;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import GComponents.GButton;
import GComponents.GFont;
import GComponents.GLabel;
import GComponents.GPanel;
import Game.Sound;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
class StartGameCampaign extends JPanel {
    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());
        List<Integer> upgradeList = new ArrayList<>();
        List<Integer> droneList = new ArrayList<>();
        JPanel middlePanel = new JPanel(new GridBagLayout());
        JButton Start = new GButton("Start");
        JButton Back = new GButton("Back");
        JButton Exit = new GButton("Quit");
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);
        JLabel labelDrone = new GLabel("Choose drone: ", false, Color.white);

        try {
            upgradeList = window.getSpel().checkUpgrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int j:upgradeList) {
            switch (j){
                case 4:
                case 5:
                case 6:
                    droneList.add(j);
                    break;
            }
        }

        String[] drones = new String[droneList.size() + 1];

        drones[0] = "No drone";

        int i = 1;
        for (int j:droneList) {
            drones[i] = window.getSpel().getUpgrades().get(j-1).getNaam();
            i++;
        }

        JComboBox<String> chooseDrone = new JComboBox<>(drones);
        lblTitle.setFont(new GFont(65f));
        labelDrone.setFont(new GFont(18f));
        chooseDrone.setFont(new GFont(18f));
        chooseDrone.setSelectedItem(window.getSpel().getSpeler().getActiveDrone());
        middlePanel.setOpaque(false);
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

        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 80);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(Start, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 20);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(labelDrone, c);

        c.gridx = 2;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_END;
        middlePanel.add(chooseDrone, c);

        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 0;
        c.ipady = 0;
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

        //Action listeners
        Back.addActionListener(evt -> {
            new Sound("click");
            window.getCl().show(window.getCards(), "startgamepanel");
        });

        Start.addActionListener(evt -> {
            new Sound("click");
            window.getCl().show(window.getCards(), "ingamepanel");
            window.getInGame().getStartGame().setVisible(true);
            window.getInGame().getGameEnd().setVisible(false);
            window.getInGame().getPause().setVisible(false);
            window.getInGame().setCoop(false);
        });

        chooseDrone.addActionListener(evt -> {
            new Sound("click");
            if(chooseDrone.getSelectedItem() == "No drone"){
                window.getSpel().getSpeler().setActiveDrone("No drone");
            } else {
                window.getSpel().getSpeler().setActiveDrone(chooseDrone.getSelectedItem().toString());
            }
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });
    }

    //endregion
}