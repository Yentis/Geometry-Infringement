/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.io.*;
import javax.swing.*;

import GComponents.*;


/**
 * @author Renzie
 */
public class Upgrades extends GPanel {


    private Upgrades panel = this;
    private int upgradeRow = 3;
    private int upgradeColumn = 2;
    private String name;


    public Upgrades() throws IOException, FontFormatException {

        initComponents();

        // resize components according to frame size
        resizeComponents(getWidth(), getHeight());

    }


    @Override
    public void initComponents() throws IOException, FontFormatException {
        //Make components
        //==================================================

        JButton back = new GButton("Back", 18f, 52,690,133,35);
        JButton skins = new GButton("Skins", 18f, 205,690,133,35);
        JButton techTree = new GButton("Tech Tree", 18f, 365,690,287,35);
        JButton goldenNuggets = new GButton("Buy Golden Nuggets", 18f, 681,690,287,35);
        JLabel spaceShipPane = new GPane(52,138,287,535);
        JLabel dronePane = new GPane(365,138,287,534);
        JLabel firePane = new GPane(681,138,287,534);
        JLabel spaceShip = new GLabel("Spaceship", 16f, 151,149,100,28, false, Color.black );
        JLabel drone = new GLabel("Drone", 16f, 482,149,87,28, false, Color.black );
        JLabel fire = new GLabel("fire", 16f, 807,149,87,28, false, Color.black );
        JLabel upgradesPane = new GPane(368,30,287,98);
        JLabel upgrades = new GLabel("Upgrades", 36f, 400,42,218,74, false, Color.black);

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

        JButton upgradeShip1 = new GButton("", 16f,75,218,104,123);
        JButton upgradeShip2 = new GButton("", 16f,211,218,104,123);
        JButton upgradeShip3 = new GButton("",16f,75,358,104,123);

        JButton upgradeDrone1 = new GButton("", 16f, 392,218,104,123);
        JButton upgradeDrone2 = new GButton("",16f, 528,218,104,123);
        JButton upgradeDrone3 = new GButton("",16f, 392,358,104,123);

        JButton upgradeFire1 = new GButton("", 16f, 704,218,104,123);
        JButton upgradeFire2 = new GButton("", 16f, 841,218,104,123);
        JButton upgradeFire3 = new GButton("", 16f, 704,358,104,123);

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
        panel.add(upgrades);
        panel.add(upgradesPane);
        panel.add(skins);
        panel.add(techTree);
        panel.add(goldenNuggets);
        panel.add(spaceShip);
        panel.add(drone);
        panel.add(fire);
        panel.add(upgradeDrone1);
        panel.add(upgradeDrone2);
        panel.add(upgradeDrone3);
        panel.add(upgradeShip1);
        panel.add(upgradeShip2);
        panel.add(upgradeShip3);
        panel.add(upgradeFire1);
        panel.add(upgradeFire2);
        panel.add(upgradeFire3);
        panel.add(spaceShipPane);
        panel.add(dronePane);
        panel.add(firePane);




    }
}
