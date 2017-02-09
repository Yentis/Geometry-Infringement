package GUI;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import javax.swing.*;
import GComponents.*;
import Game.Sound;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
class StartGame extends JPanel {
    //region Constructors

    StartGame() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        JPanel middlePanel = new JPanel(new GridBagLayout());
        JButton SoloGame = new GButton("Solo Game");
        JButton Coop = new GButton("Co-op");
        JButton Back = new GButton("Back");
        JButton Exit = new GButton("Quit");
        JButton Highscores = new GButton("High Scores");
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);

        lblTitle.setFont(new GFont(65));
        middlePanel.setOpaque(false);
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

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 60);
        c.anchor = GridBagConstraints.LINE_START;
        middlePanel.add(SoloGame, c);

        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_END;
        middlePanel.add(Highscores, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(60, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        middlePanel.add(Coop, c);

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
        c.insets = new Insets(0, 0, 20, 130);
        add(Back, c);

        //ActionListeners

        SoloGame.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            try {
                window.getSpel().initDankabank();
                window.getStartGameCampaign().initComponents();
            } catch (IOException | FontFormatException | SQLException e) {
                e.printStackTrace();
            }
            window.getCl().show(window.getCards(), "startgamecampaignpanel");
        });

        Coop.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "ingamepanel");
            window.getInGame().setCoop(true);
        });

        Back.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "mainmenupanel");
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        Highscores.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());

            try {
                window.getSpel().initDankabank();
                window.getHighScores().initComponents();
            } catch (IOException | FontFormatException | SQLException e) {
                e.printStackTrace();
            }
            window.getCl().show(window.getCards(), "highscorespanel");
        });
    }

    //endregion
}