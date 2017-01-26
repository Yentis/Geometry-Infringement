package GUI;

import GComponents.*;
import Game.Sound;
import Game.Spel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;

/**
 *
 * @author Yentl
 */
class Register extends GPanel {
    //region Instance Variables

    private Register panel = this;

    //endregion

    //region Constructors

    Register() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);
        GButton register = new GButton("Register", 24f, 220,500,170,50);
        GButton Back = new GButton("Back", 24f, 635,650, 170, 63);
        GLabel registered = new GLabel("Registration Successful", 24f, 220,120,350,50, false, Color.white);
        GInputField username = new GInputField(480,190,200,50);
        GLabel lblusername = new GLabel("Username", 24f, 220,190,150,50, false, Color.white);
        GPasswordField password = new GPasswordField(480, 260, 200, 50);
        GLabel lblpassword = new GLabel("Password", 24f, 220, 260, 150, 50, false, Color.white);
        GPasswordField passwordconfirm = new GPasswordField(480,330,200,50);
        GLabel lblpasswordconfirm = new GLabel("Repeat Password", 24f, 220, 330, 300, 50, false, Color.white);
        GInputField email = new GInputField(480,400,200,50);
        GLabel lblemail = new GLabel("E-mail", 24f, 220, 400, 150, 50, false, Color.white);
        JButton Exit = new GButton("Quit", 24f, 820, 650, 170, 63);

        label.setOpaque(true);
        label.setFont(new GFont(65));
        label.setBackground(new Color(255,255,255,95));
        registered.setVisible(false);

        label.setBounds(25,25,650,100);

        this.add(label);
        this.add(register);
        this.add(registered);
        this.add(username);
        this.add(lblusername);
        this.add(password);
        this.add(lblpassword);
        this.add(passwordconfirm);
        this.add(lblpasswordconfirm);
        this.add(email);
        this.add(lblemail);
        this.add(Exit);
        this.add(Back);

        //Action Listeners
        register.addActionListener(e -> {
            new Sound("click");
            if(Objects.equals(username.getText(), "")){
                registered.setText("Please enter a username");
            } else if (password.getPassword().length == 0){
                registered.setText("Please enter a password");
            } else if (passwordconfirm.getPassword().length == 0){
                registered.setText("Please confirm your password");
            } else if (Objects.equals(email.getText(), "")){
                registered.setText("Please enter your email address");
            } else if (!Arrays.equals(password.getPassword(), passwordconfirm.getPassword())){
                registered.setText("Your passwords do not match, please try again");
            } else {
                String result = null;
                try {
                    result = checkAndCreate(username.getText(), password.getPassword(), email.getText());
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e1) {
                    e1.printStackTrace();
                }

                if(Objects.equals(result, "")){
                    registered.setText("Registration Successful");
                } else {
                    registered.setText(result + " already exists.");
                }
            }
            registered.setVisible(true);
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        Back.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(panel.getParent());
            window.getLogin().setVisible(true);
        });
    }

    private String checkAndCreate(String gebruikersnaam, char[] password, String email) throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        Spel spel = new Spel();
        String result;

        //hash
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(new String(password).getBytes("UTF-8"));
        byte[] digest = md.digest();
        String hashed = "";
        for (byte item:digest) {
            hashed += item;
        }

        result = spel.infoChecker(gebruikersnaam, email);

        if(Objects.equals(result, "")){
            spel.registerPlayer(gebruikersnaam, hashed, email);
        } else {
            return result;
        }
        return "";
    }

    //endregion
}