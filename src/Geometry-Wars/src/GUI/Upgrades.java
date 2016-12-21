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
        panel.add(spaceShipPane);
        panel.add(dronePane);
        panel.add(firePane);




    }
}
