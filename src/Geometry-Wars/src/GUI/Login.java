package GUI;

import GComponents.*;
import Game.Sound;

import java.awt.Color;
import java.awt.FontFormatException;
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
class Login extends GPanel{
    //region Instance Variables
    
    private Login panel = this;

    //endregion

    //region Constructors

    Login() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        GLabel message = new GLabel("", 24f, 220,120,600,50, false, Color.white);
        GLabel lblusername = new GLabel("Username: ", 24f, 200,170,150,50, false, Color.white);
        GLabel lblpassword = new GLabel("Password: ", 24f, 200,240,150,50, false, Color.white);
        GButton register = new GButton("Register", 24f, 200,300,170,50);
        JButton loginButton = new GButton("Login", 24f, 390,300,170,50);
        JTextField username = new GInputFieldEnter(360,170,200,50, loginButton);
        JPasswordField password = new GPasswordFieldEnter(360,240,200,50, loginButton);
        JButton Exit = new GButton("Quit", 24f, 820, 650, 170, 63);

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));

        label.setBounds(25,25,650,100);

        this.add(label);
        this.add(lblusername);
        this.add(lblpassword);
        this.add(username);
        this.add(password);
        this.add(register);
        this.add(loginButton);
        this.add(Exit);
        this.add(message);

        //Action Listeners
        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        register.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            window.getRegister().setVisible(true);
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
                    panel.setVisible(false);
                    Window window = (Window) SwingUtilities.getRoot(panel.getParent());
                    window.getMainMenu().setVisible(true);
                } else if (result == null){
                    message.setText("Database connection failed.");
                } else if (Objects.equals(result, "banned")){
                    message.setText("Your account is banned.");
                } else {
                    message.setText(result + " is not correct.");
                }
            }
            message.setVisible(true);
        });
    }

    private String checkAndCreate(String gebruikersnaam, char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
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