package GUI;

import GComponents.*;
import Game.Speler;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Renzie
 */
class Profile extends GPanel {
    //region Instance Variables

    private Profile panel = this;

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        Speler speler = window.getSpel().getSpeler();

        //Make components
        //==================================================

        JLabel Title = new GTitle(25, 25);
        JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("resources\\Media\\Background.png")).getImage().getScaledInstance(1920, 1080, java.awt.Image.SCALE_SMOOTH))));
        JLabel Profile = new GPane(220, 160, 234, 300);
        JButton Back = new GButton("Back", 24f, 320, 655, 140, 45);
        JLabel ProfileInfo = new GPane(480, 160, 500, 540);
        JLabel Username = new GLabel("  " + speler.getGebruikersnaam(), 24f, 245, 175, 180, 64, true, Color.BLACK);
        JLabel ProfilePicture = new GIcon("ProfilePictures\\" + speler.getGebruikersnaam() + ".png", 245, 268, 180, 170, true);
        JLabel Rank = new GLabel(speler.getRank(), 24f, 660, 210, 280, 62, false, Color.BLACK);
        JLabel RankPicture = new GIcon("Badges\\" + speler.getRank() + ".png", 520, 175, 130, 130, false);
        //JButton Achievements = new GButton("Achievements", 24f, 60, 982, 340, 67);

        ProfileInfo.setFont(new GFont(22));
        ProfileInfo.setForeground(Color.BLACK);
        ProfileInfo.setBorder(new EmptyBorder(0, 10, 0, 0));
        ProfileInfo.setText("<html>Level: " + speler.getLevel() + "<br>Experience: " + speler.getExperience() + "<br>Rank: " + speler.getRank() + "<br>Nuggets: " + speler.getNuggets() + "<br>Golden Nuggets: " + speler.getGnuggets() + "<br>Highscore: " + speler.getHighscore() + "<html>");

        //Add Action Listener
        //==================================================
        Back.addActionListener(evt -> {
            panel.setVisible(false);
            Window window1 = (Window) SwingUtilities.getRoot(panel.getParent());
            window1.getMainMenu().setVisible(true);
        });
        //==================================================
        //Add Components
        //==================================================
        this.add(Title);
        this.add(ProfilePicture);
        this.add(Back);
        //this.add(Achievements);
        this.add(Username);
        this.add(RankPicture);
        this.add(Rank);
        this.add(Profile);
        this.add(ProfileInfo);
        this.add(Background);
        //==================================================
    }

    //endregion
}