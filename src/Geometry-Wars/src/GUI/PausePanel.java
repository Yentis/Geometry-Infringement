package GUI;

import GComponents.GButton;
import GComponents.GPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 18/12/2016.
 */
public class PausePanel extends GPanel {

    GButton Continue;
    PausePanel panel = this;

    public PausePanel() throws IOException, FontFormatException {
        initComponents();
        this.setVisible(false);
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
        JLabel pauzepane = new JLabel();

        Continue = new GButton("Continue", 24f, 325,84,375,120);
        JButton Restart = new GButton("Restart", 24f, 325,284,375,120);
        JButton Menu = new GButton("Back to Main Menu", 24f, 325,484,375,120);


        pauzepane.setOpaque(true);
        pauzepane.setBackground(new Color(255,255,255,50));
        pauzepane.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.cyan));


        pauzepane.setBounds(395,265,370,245);

        pauzepane.setVisible(false);

        this.add(pauzepane);


        Continue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getInGameSinglePlayer().setVisible(true);
            }
        });

        Restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }

        });
        panel.add(Continue);
        panel.add(Restart);
        panel.add(Menu);
    }

    public GButton getContinueButton() {
        return Continue;
    }
}
