package GUI;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.*;
import java.net.MalformedURLException;
import javax.swing.*;
import GComponents.GButton;
import GComponents.GFont;
import GComponents.GLabel;
import GComponents.GPanel;
import Game.Sound;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
class Logout extends GPanel {
    //region Instance Variables

    private Logout panel = this;

    //endregion

    //region Constructors

    Logout() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        JLabel LoggedOut = new GLabel("You are now logged out.", 36f, 320, 370, 600, 62, false, Color.white);
        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        JButton Exit = new GButton("Quit", 24f, 820, 650, 170, 63);
        JButton Login = new GButton("Login", 24f, 635,650, 170, 63);

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));

        label.setBounds(25,25,650,100);

        this.add(LoggedOut);
        this.add(label);
        this.add(Exit);
        this.add(Login);

        //Action listeners
        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        Login.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            window.getLogin().setVisible(true);
        });
    }

    //endregion
}
