package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Font.GFont;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class StartGame {

    public StartGame() throws MalformedURLException, IOException, FontFormatException {



        //Make components
        //==================================================
        JFrame frame = new JFrame("Startgame");
        JButton Campaign = new JButton("Campaign");
        JButton War = new JButton("War");
        JButton SoloGame = new JButton("Solo Game");
        JButton Coop = new JButton("Co-op");
        JLabel label = new JLabel("title", SwingConstants.CENTER);


        Campaign.setText("Campaign");
        War.setText("War");
        SoloGame.setText("Solo Game");
        Coop.setText("Co-op");
        label.setText("Geometry Wars");

        label.setFont(new GFont(80));
        War.setFont(new GFont(36));
        Campaign.setFont(new GFont(36));
        SoloGame.setFont(new GFont(36));
        Coop.setFont(new GFont(36));


        label.setOpaque(true);
        label.setBackground(new Color(255,255,255,95));
        War.setBackground(new Color(255,255,255,200));
        Campaign.setBackground(new Color(255,255,255,200));
        SoloGame.setBackground(new Color(255,255,255,200));
        Coop.setBackground(new Color(255,255,255,200));

        frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });
        frame.setLayout(null);

        //Bounds
        Campaign.setBounds(400,264,472,135);
        War.setBounds(968,264,472,135);
        SoloGame.setBounds(542,490,472,135);
        Coop.setBounds(578,724,472,135);
        label.setBounds(25,25,800,125);


        frame.add(Campaign);
        frame.add(War);
        frame.add(SoloGame);
        frame.add(Coop);
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);


        }
    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new StartGame();
    }


}
