package GUI;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.*;

import GComponents.GFont;
import GComponents.GPanel;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
public class StartGameCampaign extends GPanel {
    private StartGameCampaign panel = this;

    public StartGameCampaign() throws MalformedURLException, IOException, FontFormatException {

       
        initComponents();


    }

    @Override
    public void initComponents() throws IOException, FontFormatException {
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









        panel.setLayout(null);

        //Bounds
        Continue.setBounds(398,262,472,135);
        newCampaign.setBounds(540,488,472,135);
        clearCampaign.setBounds(576,722,472,135);

        label.setBounds(25,25,800,125);


        panel.add(Continue);
        panel.add(newCampaign);
        panel.add(clearCampaign);

        panel.add(label);


        
    }


}
