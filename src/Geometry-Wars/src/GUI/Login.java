package GUI;

import GComponents.*;
import Game.Sound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.*;

/**
 *
 * @author Renzie
 */
class Login extends JPanel{
    //region Constructors

    Login() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridLayout layout = new GridLayout(0, 2);
        JPanel login = new JPanel(layout);
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);
        JLabel message = new GLabel(" ", false, Color.white);
        JLabel lblusername = new GLabel("Username: ", false, Color.white);
        JLabel lblpassword = new GLabel("Password: ", false, Color.white);
        JButton register = new GButton("Register");
        JButton loginButton = new GButton("Login");
        JTextField username = new GInputField(loginButton, 10);
        JPasswordField password = new GPasswordField(loginButton, 10);
        JButton Exit = new GButton("Quit");
        login.setOpaque(false);
        lblTitle.setFont(new GFont(65));
        layout.setHgap(10);
        layout.setVgap(10);
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
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 300, 0);
        c.anchor = GridBagConstraints.CENTER;
        add(message, c);

        c.insets = new Insets(0, 0, 0, 0);
        login.add(lblusername);
        login.add(username);
        login.add(lblpassword);
        login.add(password);
        login.add(register);
        login.add(loginButton);
        add(login, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 20, 20);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(Exit, c);

        //Action Listeners
        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        register.addActionListener(evt -> {
            new Sound("click");
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "registerpanel");
        });

        loginButton.addActionListener(e -> {
            new Sound("click");
            if(Objects.equals(username.getText(), "")){
                message.setText("Please enter a username");
            } else if (password.getPassword().length == 0){
                message.setText("Please enter a password");
            } else {
                String result = null;
                try {
                    result = checkAndCreate(username.getText(), password.getPassword());
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e1) {
                    e1.printStackTrace();
                }

                if(Objects.equals(result, "")){
                    message.setText(" ");
                    Window window = (Window) SwingUtilities.getRoot(getParent());
                    window.getCl().show(window.getCards(), "mainmenupanel");
                } else if (result == null){
                    message.setText("Database connection failed.");
                } else if (Objects.equals(result, "banned")){
                    message.setText("Your account is banned.");
                } else {
                    message.setText(result + " is not correct.");
                }
            }
        });
    }

    private String checkAndCreate(String gebruikersnaam, char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());
        String result;

        //hash
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(new String(password).getBytes("UTF-8"));
        byte[] digest = md.digest();
        String hashed = "";
        for (byte item:digest) {
            hashed += item;
        }

        result = window.getSpel().loginChecker(gebruikersnaam, hashed);

        if(Objects.equals(result, "")){
            if(!window.getSpel().logIn(gebruikersnaam)){
                return "banned";
            }
        } else {
            return result;
        }
        return "";
    }

    //endregion
}