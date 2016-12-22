/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GComponents.GButton;
import GComponents.GFont;
import GComponents.GPanel;

import GComponents.*;
import Game.Speler;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

/**
 * Created by Laurens Visser on 21/12/2016.
 */
public class Highscores extends GPanel {
    private Highscores panel = this;

    public Highscores() throws IOException, FontFormatException {

        //do not init

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D horitzontalLine = new Line2D.Float(220, 230, 869,230);
        Line2D verticalLine= new Line2D.Float(545, 160, 545,699);
        g2.setColor(Color.cyan);
        g2.draw(horitzontalLine);
        g2.draw(verticalLine);
    }


    @Override
    public void initComponents() throws IOException, FontFormatException {
        List<String> strings;

        JLabel label = new JLabel("HIGH SCORES", SwingConstants.CENTER);
        JLabel Player = new GLabel("Player", 24f, 330, 166, 180, 64, false, Color.white);
        JLabel Score = new GLabel("Score", 24f, 655, 166, 180, 64, false, Color.white);
        JButton Back = new GButton("Back", 24f, 20, 675, 120, 45);
        JLabel backgroundpane = new GPane(220, 160, 650, 540);
        DefaultListModel listModelPlayer = new DefaultListModel();
        DefaultListModel listModelScore = new DefaultListModel();

        strings = processNames();

        for (String string:strings) {
            listModelPlayer.addElement(string);
        }

        JList listPlayer = new JList(listModelPlayer);

        strings = processScores();

        for (String string:strings) {
            listModelScore.addElement(string);
        }

        JList listScore = new JList(listModelScore);

        //properties

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green));
        label.setBackground(Color.black);
        label.setForeground(Color.white);

        listPlayer.setLayoutOrientation(JList.VERTICAL);
        listPlayer.setFont(new GFont(24));
        listPlayer.setBackground(Color.black);
        listPlayer.setForeground(Color.white);
        listPlayer.setBorder(new EmptyBorder(20, 20, 0, 0));

        listScore.setLayoutOrientation(JList.VERTICAL);
        listScore.setFont(new GFont(24));
        listScore.setBackground(Color.black);
        listScore.setForeground(Color.white);
        listScore.setBorder(new EmptyBorder(20, 20, 0, 0));

        Back.setBackground(new Color(255,255,255,200));
        backgroundpane.setBackground(Color.BLACK);
        backgroundpane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.cyan));


        //bounds
        label.setBounds(220,25,650,100);

        listPlayer.setBounds(221,230,318,469);
        listScore.setBounds(551,230,318,469);

        //components toevoegen
        this.add(label);
        this.add(Back);
        this.add(Player);
        this.add(Score);
        this.add(listPlayer);
        this.add(listScore);
        this.add(backgroundpane);

        //actionlisteners

        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panel.setVisible(false);
                GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
                window.getStartGame().setVisible(true);
            }
        });
    }

    private List<String> processNames(){
        List<String> strings = new ArrayList<>();
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Speler> spelers;
        spelers = window.getSpel().getSpelers();

        int i = 0;
        for (Speler speler:spelers) {
            strings.add(i, (i+1) + " - " + speler.getGebruikersnaam());
            i++;
        }

        return strings;
    }

    private List<String> processScores(){
        List<String> strings = new ArrayList<>();
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Speler> spelers;
        spelers = window.getSpel().getSpelers();

        int i = 0;
        for (Speler speler:spelers) {
            strings.add(i, String.valueOf(speler.getHighscore()));
            i++;
        }

        return strings;
    }

}
