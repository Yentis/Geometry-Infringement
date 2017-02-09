package GUI;

import GComponents.*;
import Game.Sound;
import Game.Speler;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Renzie
 */
class Profile extends JPanel {
    //region Instance Variables

    private Speler speler;
    private boolean fromHighscore = false;

    //endregion

    //region Getters & Setters

    public void setFromHighscore(boolean fromHighscore) {
        this.fromHighscore = fromHighscore;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    //endregion

    //region Behaviour

    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        //Make components
        //==================================================

        JPanel userandpic = new JPanel(new GridBagLayout());
        JPanel stats = new JPanel(new GridBagLayout());
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);
        JButton Back = new GButton("Back");
        JButton Exit = new GButton("Quit");
        JLabel Profile = new GPane();
        JLabel ProfileInfo = new GPane();
        JLabel Username = new GLabel(speler.getGebruikersnaam(), true, Color.BLACK);
        JLabel ProfilePicture = new GIcon("ProfilePictures\\" + speler.getGebruikersnaam() + ".png", 180, 170, false);
        JLabel Rank = new GLabel(speler.getRank(), false, Color.WHITE);
        JLabel RankPicture = new GIcon("Badges\\" + speler.getRank() + ".png", 130, 130, false);

        lblTitle.setFont(new GFont(65));
        ProfileInfo.setFont(new GFont(22));
        ProfileInfo.setForeground(Color.BLACK);
        ProfileInfo.setText("<html>Level: " + speler.getLevel() + "<br>Experience: " + speler.getExperience() + "<br>Rank: " + speler.getRank() + "<br>Nuggets: " + speler.getNuggets() + "<br>Golden Nuggets: " + speler.getGnuggets() + "<br>Highscore: " + speler.getHighscore() + "<html>");
        userandpic.setOpaque(false);
        stats.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridwidth = 4;
        c.insets = new Insets(20, 20, 0, 0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lblTitle, c);

        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 10;
        c.ipady = 10;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 100, 200);
        c.anchor = GridBagConstraints.CENTER;
        userandpic.add(Username, c);
        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(150, 0, 0, 200);
        userandpic.add(ProfilePicture, c);
        c.insets = new Insets(100, 0, 0, 200);
        Profile.setBorder(new EmptyBorder(140, 110, 140, 110));
        userandpic.add(Profile, c);
        c.insets = new Insets(0, 0, 0, 0);
        add(userandpic, c);

        c.gridy = 1;
        c.insets = new Insets(0, 400, 200, 0);
        stats.add(RankPicture, c);
        c.insets = new Insets(0, 650, 200, 0);
        stats.add(Rank, c);
        c.insets = new Insets(200, 520, 0, 0);
        ProfileInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        stats.add(ProfileInfo, c);
        c.insets = new Insets(0, 0, 0, 0);
        add(stats, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 140);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(Back, c);
        c.insets = new Insets(0, 0, 20, 20);
        add(Exit, c);

        //Add Action Listener
        //==================================================
        Back.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());

            if(fromHighscore){
                window.getCl().show(window.getCards(), "highscorespanel");
            } else {
                window.getCl().show(window.getCards(), "mainmenupanel");
            }
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });
    }

    //endregion
}