package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import javax.swing.*;
import GComponents.*;
import Game.Sound;

/**
 * @author Renzie
 */
class MainMenu extends JPanel {
    //region Instance Variables

    private String url = "https://discordapp.com/";

    //endregion

    //region Constructors

    MainMenu() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        //Make components
        //==================================================

        GridLayout layout = new GridLayout(0, 1);
        JPanel mainmenu = new JPanel(layout);
        JButton StartGame = new GButton("Play");
        JButton Upgrades = new GButton("Upgrades");
        JButton Profile = new GButton("Profile");
        JButton Settings = new GButton("Settings");
        JButton LogOut = new GButton("Logout");
        JButton Exit = new GButton("Quit");
        JButton Discord = new JButton(new ImageIcon(((new ImageIcon("resources\\Media\\Discord.jpg")).getImage()).getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH)));
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);

        lblTitle.setFont(new GFont(65f));
        mainmenu.setOpaque(false);
        layout.setVgap(50);
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
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        mainmenu.add(StartGame);
        mainmenu.add(Upgrades);
        mainmenu.add(Profile);
        mainmenu.add(Settings);
        add(mainmenu, c);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 20, 20, 0);
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Discord.setPreferredSize(new Dimension(Discord.getIcon().getIconWidth(), Discord.getIcon().getIconHeight()));
        add(Discord, c);

        c.gridx = 2;
        c.insets = new Insets(0, 0, 20, 140);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(LogOut, c);
        c.insets = new Insets(0, 0, 20, 20);
        add(Exit, c);

        //region Action Listeners

        StartGame.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "startgamepanel");
        });

        Upgrades.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            try {
                window.getSpel().logIn(window.getSpel().getSpeler().getGebruikersnaam());
                window.getSpel().initDankabank();
                window.getUpgrades().initComponents();
            } catch (IOException | FontFormatException | SQLException e) {
                e.printStackTrace();
            }
            window.getCl().show(window.getCards(), "upgradespanel");
        });

        Settings.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            try {
                window.getSettings().initComponents();
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            window.getCl().show(window.getCards(), "settingspanel");
        });

        Discord.addActionListener(evt -> {
            new Sound("click");
            try {
                Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        LogOut.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getSpel().logOut();
            window.getCl().show(window.getCards(), "logoutpanel");
        });

        Profile.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            try {
                window.getSpel().logIn(window.getSpel().getSpeler().getGebruikersnaam());
                window.getProfile().setSpeler(window.getSpel().getSpeler());
                window.getProfile().setFromHighscore(false);
                window.getProfile().initComponents();
            } catch (IOException | FontFormatException | SQLException e) {
                e.printStackTrace();
            }
            window.getCl().show(window.getCards(), "profilepanel");
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        //endregion
    }

    //endregion
}
