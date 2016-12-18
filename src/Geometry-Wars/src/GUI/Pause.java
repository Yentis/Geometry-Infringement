package GUI;

import GComponents.GButton;
import GComponents.GPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Matthias on 17/12/2016.
 */
public class Pause extends JFrame{
    private Pause panel = this;

    public static void main(String args[]) throws IOException, FontFormatException {
        new Pause();

    }

    public Pause() throws IOException, FontFormatException {
        this.setSize(1024,768);
        this.setVisible(true);
        this.setLayout(null);
        initComponents();
        this.setBackground(Color.black);

        // resize components according to frame size
        //resizeComponents(getWidth(), getHeight());

    }

    //@Override
    public void initComponents() throws IOException, FontFormatException {

        JButton Continue = new GButton("Continue", 24f, 325,84,375,120);
        JButton Restart = new GButton("Restart", 24f, 325,284,375,120);
        JButton Menu = new GButton("Back to Main Menu", 24f, 325,484,375,120);


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
        Continue.setVisible(true);
        Restart.setVisible(true);
        Menu.setVisible(true);
    }


}
