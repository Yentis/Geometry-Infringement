package GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GComponents.GButton;
import GComponents.GFont;

import GComponents.*;
import Game.Sound;
import Game.Speler;

/**
 * Created by Laurens Visser on 21/12/2016.
 */
class Highscores extends JPanel {
    //region Instance Variables

    private JList<String> listPlayer;

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);
        java.util.List<String> strings = new ArrayList<>();
        java.util.List<Speler> spelers;

        JPanel middlePanel = new JPanel(new GridBagLayout());
        JPanel playerPanel = new JPanel(new GridBagLayout());
        JPanel playerList = new JPanel(new GridBagLayout());
        JPanel scorePanel = new JPanel(new GridBagLayout());
        JPanel scoreList = new JPanel(new GridBagLayout());
        JLabel lblTitle = new GLabel("HIGHSCORES", true, Color.white);
        JLabel Player = new GLabel("Player", false, Color.white);
        JLabel Score = new GLabel("Score", false, Color.white);
        JButton Back = new GButton("Back");
        JButton Exit = new GButton("Quit");
        DefaultListModel<String> listModelPlayer = new DefaultListModel<>();
        DefaultListModel<String> listModelScore = new DefaultListModel<>();

        spelers = processNames();

        int i = 0;
        for (Speler speler:spelers) {
            strings.add(i, (i+1) + " - " + speler.getGebruikersnaam());
            i++;
        }

        i = 0;
        for (String string:strings) {
            if(i<10){
                listModelPlayer.addElement(string);
            }
            i++;
        }

        listPlayer = new JList<>(listModelPlayer);

        strings = processScores();

        i = 0;
        for (String string:strings) {
            if(i<10){
                listModelScore.addElement(string);
            }
            i++;
        }

        JList<String> listScore = new JList<>(listModelScore);

        //properties

        lblTitle.setFont(new GFont(65));
        playerPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.cyan));
        scorePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.cyan));
        playerList.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 0, Color.cyan));
        scoreList.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.cyan));
        lblTitle.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green));
        lblTitle.setBackground(Color.black);
        middlePanel.setBackground(Color.black);
        playerPanel.setBackground(Color.black);
        scorePanel.setBackground(Color.black);
        playerList.setBackground(Color.black);
        scoreList.setBackground(Color.black);

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
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridwidth = 4;
        c.insets = new Insets(20, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        add(lblTitle, c);

        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        Player.setBorder(new EmptyBorder(10, 70, 10, 70));
        playerPanel.add(Player, c);

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 20;
        middlePanel.add(playerPanel, c);

        c.ipadx = 0;
        c.insets = new Insets(0, 0, 0, 0);
        Score.setBorder(new EmptyBorder(10, 38, 10, 38));
        scorePanel.add(Score, c);

        c.gridx = 1;
        c.ipadx = 20;
        middlePanel.add(scorePanel, c);

        c.gridx = 0;
        c.ipadx = 0;
        listPlayer.setBorder(new EmptyBorder(10, 10, 10, 10));
        playerList.add(listPlayer, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(playerList, c);

        c.gridy = 1;
        c.ipady = 0;
        c.anchor = GridBagConstraints.CENTER;
        listScore.setBorder(new EmptyBorder(10, 10, 10, 10));
        scoreList.add(listScore, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_END;
        middlePanel.add(scoreList, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        add(middlePanel, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 20);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(Exit, c);
        c.insets = new Insets(0, 0, 20, 140);
        add(Back, c);

        //actionlisteners

        Back.addActionListener(evt -> {
            new Sound("click");
            setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "startgamepanel");
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        listPlayer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Sound("click");
                Window window = (Window) SwingUtilities.getRoot(getParent());
                Speler speler = spelers.get(listPlayer.getSelectedIndex());
                try {
                    window.getSpel().checkRank(speler);
                    window.getProfile().setSpeler(speler);
                    window.getProfile().setFromHighscore(true);
                    window.getProfile().initComponents();
                } catch (IOException | FontFormatException | SQLException ef) {
                    ef.printStackTrace();
                }
                window.getCl().show(window.getCards(), "profilepanel");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                listPlayer.setCursor(new Cursor(Cursor.HAND_CURSOR));
                listMenuMouseEntered();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                listPlayer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                listPlayer.clearSelection();
            }
        });
    }

    private void listMenuMouseEntered() {
        listPlayer.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent me) {
                Point p = new Point(me.getX(),me.getY());
                listPlayer.setSelectedIndex(listPlayer.locationToIndex(p));
            }
        });
    }

    private List<Speler> processNames(){
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());

        return window.getSpel().getSpelers();
    }

    private List<String> processScores(){
        List<String> strings = new ArrayList<>();
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());
        List<Speler> spelers;
        spelers = window.getSpel().getSpelers();

        int i = 0;
        for (Speler speler:spelers) {
            strings.add(i, String.valueOf(speler.getHighscore()));
            i++;
        }

        return strings;
    }

    //endregion
}
