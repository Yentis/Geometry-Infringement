package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import GComponents.*;


/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class InGameSinglePlayer {

    public InGameSinglePlayer() throws MalformedURLException, IOException, FontFormatException {



        JFrame frame = new JFrame("Game");
        ImageIcon PauseImage = new ImageIcon("src\\Media\\pause-128.png");
        JButton pauze = new JButton(PauseImage);
        JLabel pane = new JLabel();

        GLabel health = new GLabel("Health:", 36, 50,30,169,62, false, Color.cyan);
        GLabel score = new GLabel("Score:", 36, 1150,30,169,62, false, Color.cyan);
        GLabel schipLvl = new GLabel("Ship lvl:", 36, 290,920,222,62, false, Color.green);
        GLabel droneLvl = new GLabel("Drone lvl:", 36, 1060,920,222,62, false, new Color(155,255,204));
        GLabel healthbar = new GLabel("", 36, 9,30,822,67, true, Color.black);
        GLabel dronebar = new GLabel("", 36, 1413,920,406,67, true, Color.black);
        GLabel schipbar = new GLabel("", 36, 585,920,406,67, true, Color.black);

        pane.setOpaque(true);
        pane.setBackground(new Color(255,255,255,2));
        pane.setBorder(BorderFactory.createMatteBorder(
                5, 5, 5, 5, Color.green));


        healthbar.setBackground(new Color(255,255,255,95));
        dronebar.setBackground(new Color(255,255,255,95));
        schipbar.setBackground(new Color(255,255,255,95));

        pauze.setOpaque(true);
        pauze.setBackground(new Color(155,255,204,200));
        pauze.setBorder(null);



        frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });
        frame.setLayout(null);

        //BOUNDS
        pauze.setBounds(1025,22,84,78);
        pane.setBounds(124,126,1670,750);



        //==================================================

        //Add Components
        //==================================================
        frame.add(pane);
        frame.add(healthbar);
        frame.add(schipbar);
        frame.add(dronebar);
        frame.add(pauze);
        frame.add(score);
        frame.add(health);
        frame.add(schipLvl);
        frame.add(droneLvl);

        //-----------------------------------------------------------
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);





    }

    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new InGameSinglePlayer();

    }
}
