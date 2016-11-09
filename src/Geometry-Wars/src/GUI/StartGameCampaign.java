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
public class StartGameCampaign  {

    public StartGameCampaign() throws MalformedURLException, IOException, FontFormatException {

        JFrame frame = new JFrame("StartgameCampaign");
        JButton Continue = new JButton("Continue");
        JButton newCampaign = new JButton("New Campagin");
        JButton clearCampaign = new JButton("Clear Campagin");
        JLabel label = new JLabel("title", SwingConstants.CENTER);

        Continue.setText("Continue");
        newCampaign .setText("New Campaign");
        clearCampaign.setText("Clear Campaign");
        label.setText("Geometry Wars");




        label.setFont(new GFont(80));
        newCampaign.setFont(new GFont(36));
        clearCampaign.setFont(new GFont(36));
        Continue.setFont(new GFont(36));

        label.setOpaque(true);
        label.setBackground(new Color(255,255,255,95));
        Continue.setBackground(new Color(255,255,255,200));
        newCampaign.setBackground(new Color(255,255,255,200));
        clearCampaign.setBackground(new Color(255,255,255,200));

        frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });







        frame.setLayout(null);

        //Bounds
        Continue.setBounds(398,262,472,135);
        newCampaign.setBounds(540,488,472,135);
        clearCampaign.setBounds(576,722,472,135);

        label.setBounds(25,25,800,125);


        frame.add(Continue);
        frame.add(newCampaign);
        frame.add(clearCampaign);

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);


    }
    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new StartGameCampaign();
    }
}
