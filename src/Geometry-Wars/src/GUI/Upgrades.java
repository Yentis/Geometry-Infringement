/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;

import GComponents.*;
import Game.Upgrade;
import Game.Speler;


/**
 * @author Renzie
 */
public class Upgrades extends GPanel {


    private Upgrades panel = this;
    private int upgradeRow = 3;
    private int upgradeColumn = 2;
    private String name;


    public Upgrades() throws IOException, FontFormatException {

        //do not init

        // resize components according to frame size
        //resizeComponents(getWidth(), getHeight());

    }


    @Override
    public void initComponents() throws IOException, FontFormatException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Integer> upgradeList = new ArrayList<>();
        Speler speler = window.getSpel().getSpeler();
        //Make components
        //==================================================

        JButton back = new GButton("Back", 18f, 52,690,133,35);
        JButton skins = new GButton("Skins", 18f, 205,690,133,35);
        JButton techTree = new GButton("Tech Tree", 18f, 365,690,287,35);
        JButton goldenNuggets = new GButton("Buy Golden Nuggets", 18f, 681,690,287,35);
        JLabel lblNuggets = new GLabel("Nuggets: ", 18f, 52,60,150,35, false, Color.white);
        JLabel nuggets = new GLabel("" + speler.getNuggets(), 18f, 200,60,150,35, false, Color.white);
        JLabel message = new GLabel("", 24f, 365, 82, 290, 74, false, Color.white);
        JLabel spaceShipPane = new GPane(52,138,287,535);
        JLabel dronePane = new GPane(365,138,287,534);
        JLabel firePane = new GPane(681,138,287,534);
        JLabel spaceShip = new GLabel("Spaceship", 16f, 151,149,100,28, false, Color.black );
        JLabel drone = new GLabel("Drone", 16f, 482,149,87,28, false, Color.black );
        JLabel fire = new GLabel("fire", 16f, 807,149,87,28, false, Color.black );
        JLabel upgradesPane = new GPane(365,10,287,98);
        JLabel upgrades = new GLabel("Upgrades", 36f, 400,22,218,74, false, Color.black);

        /*
            x for every column of upgrades
            1: 75x
            2: 211x
            3: 392x
            4: 528x
            5: 704x
            6: 841x

            y for every row of upgrades
            1: 218
            2: 358
            3: 504

            width and height for buttons of upgrades
            width: 104
            height: 123

         */

        try {
            upgradeList = window.getSpel().checkUpgrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton upgradeShip1 = new GButton("", 16f,75,218,104,123);
        upgradeShip1.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(1).getFoto()));
        JLabel upgradeShip1Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(1).getKost()), 16f, 85,143,104,123, false, Color.black);
        JButton upgradeShip2 = new GButton("", 16f,211,218,104,123);
        upgradeShip2.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(2).getFoto()));
        JLabel upgradeShip2Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(2).getKost()), 16f, 226,143,104,123, false, Color.black);
        //JButton upgradeShip3 = new GButton("",16f,75,368,104,123);

        JButton upgradeDrone1 = new GButton("", 16f, 392,218,104,123);
        upgradeDrone1.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(3).getFoto()));
        JLabel upgradeDrone1Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(3).getKost()), 16f, 407,143,104,123, false, Color.black);
        JButton upgradeDrone2 = new GButton("",16f, 528,218,104,123);
        upgradeDrone2.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(4).getFoto()));
        JLabel upgradeDrone2Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(4).getKost()), 16f, 543,143,104,123, false, Color.black);
        JButton upgradeDrone3 = new GButton("",16f, 392,368,104,123);
        upgradeDrone3.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(5).getFoto()));
        JLabel upgradeDrone3Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(5).getKost()), 16f, 407,293,104,123, false, Color.black);

        JButton upgradeFire1 = new GButton("", 16f, 704,218,104,123);
        upgradeFire1.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(0).getFoto()));
        JLabel upgradeFire1Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(0).getKost()), 16f, 715,143,104,123, false, Color.black);
        //JButton upgradeFire2 = new GButton("", 16f, 841,218,104,123);
        //JButton upgradeFire3 = new GButton("", 16f, 704,368,104,123);

        for (int i:upgradeList) {
            switch (i){
                case 1:
                    upgradeFire1.setEnabled(false);
                    break;
                case 2:
                    upgradeShip1.setEnabled(false);
                    break;
                case 3:
                    upgradeShip2.setEnabled(false);
                    break;
                case 4:
                case 5:
                case 6:
                    upgradeDrone1.setEnabled(false);
                    upgradeDrone2.setEnabled(false);
                    upgradeDrone3.setEnabled(false);
                    break;
            }
        }

        //==================================================


        //Add Action Listener
        //==================================================

        // TODO give same sort of buttons the same kind of function
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getMainMenu().setVisible(true);
            }
        });

        upgradeShip1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                Upgrade upgrade = window.getSpel().getUpgrades().get(1);

                buyUpgrade(window, upgrade, message, upgradeShip1);
            }
        });

        upgradeShip2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                Upgrade upgrade = window.getSpel().getUpgrades().get(2);

                buyUpgrade(window, upgrade, message, upgradeShip2);
            }
        });

        upgradeDrone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                Upgrade upgrade = window.getSpel().getUpgrades().get(3);

                buyUpgrade(window, upgrade, message, upgradeDrone1);
            }
        });

        upgradeDrone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                Upgrade upgrade = window.getSpel().getUpgrades().get(4);

                buyUpgrade(window, upgrade, message, upgradeDrone2);
            }
        });

        upgradeDrone3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                Upgrade upgrade = window.getSpel().getUpgrades().get(5);

                buyUpgrade(window, upgrade, message, upgradeDrone3);
            }
        });

        upgradeFire1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                Upgrade upgrade = window.getSpel().getUpgrades().get(0);

                buyUpgrade(window, upgrade, message, upgradeFire1);
            }
        });

        //==================================================


        //Set Properties
        //==================================================


        //==================================================

        //Set Bounds
        //==================================================

        //==================================================

        //Add Components
        //==================================================
        panel.add(back);
        panel.add(nuggets);
        panel.add(lblNuggets);
        panel.add(upgrades);
        panel.add(upgradesPane);
        panel.add(skins);
        panel.add(techTree);
        panel.add(goldenNuggets);
        panel.add(spaceShip);
        panel.add(drone);
        panel.add(fire);
        panel.add(upgradeDrone1);
        panel.add(upgradeDrone1Price);
        panel.add(upgradeDrone2);
        panel.add(upgradeDrone2Price);
        panel.add(upgradeDrone3);
        panel.add(upgradeDrone3Price);
        panel.add(upgradeShip1);
        panel.add(upgradeShip1Price);
        panel.add(upgradeShip2);
        panel.add(upgradeShip2Price);
        //panel.add(upgradeShip3);
        panel.add(upgradeFire1);
        panel.add(upgradeFire1Price);
        //panel.add(upgradeFire2);
        //panel.add(upgradeFire3);
        panel.add(spaceShipPane);
        panel.add(dronePane);
        panel.add(firePane);
        panel.add(message);




    }

    private void buyUpgrade(GUI.Window window, Upgrade upgrade, JLabel message, JButton button){
        try {
            if(window.getSpel().buyUpgrade(upgrade.getKost(), (upgrade.getNr() + 1))){
                button.setVisible(false);
                message.setText("Upgrade purchased.");
            } else {
                message.setText("Not enough nuggets.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
