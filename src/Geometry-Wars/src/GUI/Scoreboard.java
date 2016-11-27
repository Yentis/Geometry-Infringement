package GUI;

import GComponents.GButton;
import GComponents.GFont;
import GComponents.GLabel;
import GComponents.GPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Laurens Visser on 26/11/2016.
 */
public class Scoreboard extends GPanel{

    private Scoreboard panel = this;

    public Scoreboard() throws IOException, FontFormatException {
        initComponents();
    }



    @Override
    public void initComponents() throws IOException, FontFormatException {

        JButton Back = new GButton("Back to menu", 24f, 765, 650, 230, 63);
        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        GLabel Player = new GLabel("Player:", 24, 235,175,169,62, false, Color.cyan);
        GLabel Score = new GLabel("Score:", 24, 235,245,169,62, false, Color.cyan);
        GLabel HighScore = new GLabel("Highscore:", 24, 235,315,169,62, false, Color.cyan);


        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255, 255, 255, 95));


        label.setBounds(25,25,650,100);

        this.add(label);
        this.add(Back);
        this.add(Player);
        this.add(Score);
        this.add(HighScore);




        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getMainMenu().setVisible(true);
            }



        });

    }


}