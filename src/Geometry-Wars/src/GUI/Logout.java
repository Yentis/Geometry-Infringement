package GUI;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import GComponents.GButton;
import GComponents.GFont;
import GComponents.GLabel;
import Game.Sound;
import sun.rmi.runtime.Log;

/**
 * Created by Laurens Visser on 9/11/2016.
 */
class Logout extends JPanel {
    //region Constructors

    Logout() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridLayout layout = new GridLayout(1, 1);
        JPanel logout = new JPanel(layout);
        JLabel LoggedOut = new GLabel("You are now logged out.", false, Color.white);
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);
        JButton Exit = new GButton("Quit");
        JButton Login = new GButton("Login");

        LoggedOut.setFont(new GFont(36));
        lblTitle.setFont(new GFont(65));
        logout.setOpaque(false);
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
        logout.add(LoggedOut);
        add(logout, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 140);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(Login, c);
        c.insets = new Insets(0, 0, 20, 20);
        add(Exit, c);

        //Action listeners
        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        Login.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "loginpanel");
        });
    }

    //endregion
}
