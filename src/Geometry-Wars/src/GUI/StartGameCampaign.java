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
class StartGameCampaign extends GPanel {
    //region Instance Variables

    private StartGameCampaign panel = this;

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Integer> upgradeList = new ArrayList<>();
        List<Integer> droneList = new ArrayList<>();
        JButton Start = new GButton("Start", 24f, 150,230,300,80);
        //JButton NewCampaign = new GButton("New Campaign", 24f, 200,350,350,80);
        //JButton ClearCampaign = new GButton("Clear Campaign", 24f, 250,470,350,80);
        JButton Back = new GButton("Back", 24f, 820, 650, 170, 63);

        JLabel label = new GLabel("Geometry Wars", 65f, 25, 25, 650, 100, true, Color.darkGray);
        JLabel labelDrone = new GLabel("Choose drone: ", 18, 500,250,200,50,false, Color.white);

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
        chooseDrone.setSelectedItem(window.getSpel().getSpeler().getActiveDrone());

        //props
        Start.setBackground(new Color(255,255,255,200));
        //NewCampaign.setBackground(new Color(255,255,255,200));
        //ClearCampaign.setBackground(new Color(255,255,255,200));

        Back.setBackground(new Color(255,255,255,200));
        chooseDrone.setFont(new GFont(18));
        //Bounds
        chooseDrone.setBounds(650,250,200,50);

        this.add(labelDrone);
        this.add(chooseDrone);
        this.add(Start);
        //this.add(NewCampaign);
        //this.add(ClearCampaign);
        this.add(label);
        this.add(Back);

        //Action listeners
        Back.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            window.getStartGame().setVisible(true);
        });
        Start.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            window.getInGame().setVisible(true);
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
    }

    //endregion
}