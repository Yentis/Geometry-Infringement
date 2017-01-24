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
class MainMenu extends GPanel {
    //region Instance Variables

    private String url = "https://discordapp.com/";
    private MainMenu panel = this;

    //endregion

    //region Constructors

    MainMenu() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        //Make components
        //==================================================

        JButton StartGame = new GButton("Play", 24f, 120,200,260,70);
        JButton Upgrades = new GButton("Upgrades", 24f, 190,320,260,70);
        JButton Profile = new GButton("Profile", 24f, 237,440,260,70);
        JButton Settings = new GButton("Settings", 24f, 235,560,260,70);
        JButton FriendsBtn = new GButton("Friends", 24f, 820, 25, 170,63);
        JButton LogOut = new GButton("Logout", 24f, 635,650, 170, 63);
        JButton Quit = new GButton("Quit", 24f, 820, 650, 170, 63);
        JButton Discord = new JButton(new ImageIcon(((new ImageIcon("resources\\Media\\Discord.jpg")).getImage()).getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH)));
        JLabel Title = new JLabel("Geometry Wars", SwingConstants.CENTER);
        JLabel JoinDiscord = new JLabel("Join Server!");
        JList Friends = new JList();
        GLabel confirmationlabel = new GLabel("Are you sure you want to quit?", 30, 320,285,550,60, false, Color.cyan);
        JButton Yes = new GButton("Yes", 24f, 455, 380, 100, 47);
        JButton No = new GButton("No", 24f, 595, 380, 100, 47);
        JLabel pauzepane = new JLabel();


        //==================================================


        //Add Action Listener
        //==================================================

        StartGame.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            window.getStartGame().setVisible(true);
        });

        Upgrades.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            try {
                window.getSpel().logIn(window.getSpel().getSpeler().getGebruikersnaam());
                window.getSpel().initDankabank();
                window.getUpgrades().initComponents();
            } catch (IOException | FontFormatException | SQLException e) {
                e.printStackTrace();
            }
            window.getUpgrades().setVisible(true);
        });

        Settings.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            try {
                window.getSettings().initComponents();
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            window.getSettings().setVisible(true);
        });

        FriendsBtn.addActionListener(evt -> {
            new Sound("click");
            if (!Friends.isShowing()) {
                Friends.setVisible(true);
            } else {
                Friends.setVisible(false);
            }
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
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            window.getSpel().logOut();
            window.getLogout().setVisible(true);
        });

        Profile.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            try {
                window.getSpel().logIn(window.getSpel().getSpeler().getGebruikersnaam());
                window.getProfile().initComponents();
            } catch (IOException | FontFormatException | SQLException e) {
                e.printStackTrace();
            }
            window.getProfile().setVisible(true);
        });

        Quit.addActionListener(evt -> {
            new Sound("click");
            pauzepane.setVisible(true);
            Yes.setVisible(true);
            No.setVisible(true);
            confirmationlabel.setVisible(true);
            StartGame.setVisible(false);
            Profile.setVisible(false);
            Upgrades.setVisible(false);
            Settings.setVisible(false);

            Friends.setVisible(false);
            Quit.setVisible(false);
            LogOut.setVisible(false);
            Discord.setVisible(false);
            FriendsBtn.setVisible(false);
            JoinDiscord.setVisible(false);
        });

        No.addActionListener(evt -> {
            new Sound("click");
            pauzepane.setVisible(false);
            Yes.setVisible(false);
            No.setVisible(false);
            confirmationlabel.setVisible(false);
            StartGame.setVisible(true);
            Profile.setVisible(true);
            Upgrades.setVisible(true);
            Settings.setVisible(true);

            Friends.setVisible(true);
            Quit.setVisible(true);
            LogOut.setVisible(true);
            Discord.setVisible(true);
            FriendsBtn.setVisible(true);
            JoinDiscord.setVisible(true);
        });

        Yes.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        //==================================================

        //Set Properties
        //==================================================

        Title.setFont(new GFont(65));
        Title.setOpaque(true);
        Title.setBackground(new Color(255, 255, 255, 95));
        JoinDiscord.setFont(new GFont(24));
        JoinDiscord.setForeground(Color.white);

        Friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Friends.setVisible(false);
        Friends.setBackground(new Color(255, 255, 255, 95));

        pauzepane.setOpaque(true);
        pauzepane.setBackground(new Color(255,255,255,50));
        pauzepane.setBorder(BorderFactory.createMatteBorder(
                2, 2, 2, 2, Color.cyan));

        //==================================================

        //Set Bounds
        //==================================================

        Title.setBounds(25,25,650,100);
        JoinDiscord.setBounds(102, 1000, 180, 35);
        Friends.setBounds(820, 129, 170, 300);
        Discord.setBounds(35, 650, 65, 65);
        pauzepane.setBounds(280,265,630,245);

        pauzepane.setVisible(false);
        Yes.setVisible(false);
        No.setVisible(false);
        confirmationlabel.setVisible(false);

        //==================================================

        //Add Components
        //==================================================

        panel.add(StartGame);
        panel.add(Profile);
        panel.add(Upgrades);
        panel.add(Settings);
        panel.add(Title);
        panel.add(Friends);
        panel.add(Quit);
        panel.add(LogOut);
        panel.add(Discord);
        panel.add(FriendsBtn);
        panel.add(JoinDiscord);

        panel.add(pauzepane);
        panel.add(Quit);
        panel.add(confirmationlabel);
        panel.add(Yes);
        panel.add(No);
    }

    //endregion
}
