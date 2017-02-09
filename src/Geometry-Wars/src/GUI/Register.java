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
class Register extends JPanel {
    //region Constructors

    Register() throws IOException, FontFormatException {
        initComponents();
    }

    //endregion

    //region Behaviour
    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridLayout layout = new GridLayout(0, 2);
        JPanel registerin = new JPanel(layout);
        JLabel lblTitle = new GLabel("Geometry Wars", true, Color.black);
        JButton register = new GButton("Register");
        JButton Back = new GButton("Back");
        JLabel message = new GLabel("", false, Color.white);
        JTextField username = new GInputField(register, 10);
        JLabel lblusername = new GLabel("Username: ", false, Color.white);
        JPasswordField password = new GPasswordField(register, 10);
        JLabel lblpassword = new GLabel("Password: ", false, Color.white);
        JPasswordField passwordconfirm = new GPasswordField(register, 10);
        JLabel lblpasswordconfirm = new GLabel("Repeat Password: ", false, Color.white);
        JTextField email = new GInputField(register, 10);
        JLabel lblemail = new GLabel("E-mail: ", false, Color.white);
        JButton Exit = new GButton("Quit");

        lblTitle.setFont(new GFont(65));
        registerin.setOpaque(false);
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
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 300, 0);
        c.anchor = GridBagConstraints.CENTER;
        add(message, c);

        c.insets = new Insets(0, 0, 0, 0);
        registerin.add(lblusername);
        registerin.add(username);
        registerin.add(lblpassword);
        registerin.add(password);
        registerin.add(lblpasswordconfirm);
        registerin.add(passwordconfirm);
        registerin.add(lblemail);
        registerin.add(email);
        registerin.add(register);
        add(registerin, c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 0.2;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 20, 140);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(Back, c);
        c.insets = new Insets(0, 0, 20, 20);
        add(Exit, c);

        //Action Listeners
        register.addActionListener(e -> {
            GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());

            new Sound("click");
            if(Objects.equals(username.getText(), "")){
                message.setText("Please enter a username");
            } else if (password.getPassword().length == 0){
                message.setText("Please enter a password");
            } else if (passwordconfirm.getPassword().length == 0){
                message.setText("Please confirm your password");
            } else if (Objects.equals(email.getText(), "")){
                message.setText("Please enter your email address");
            } else if (!Arrays.equals(password.getPassword(), passwordconfirm.getPassword())){
                message.setText("Your passwords do not match, please try again");
            } else {
                String result = null;
                try {
                    result = checkAndCreate(username.getText(), password.getPassword(), email.getText());
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException | SQLException e1) {
                    e1.printStackTrace();
                }

                if(Objects.equals(result, "")){
                    try {
                        window.getSpel().logIn(username.getText());
                        setVisible(false);
                        window.getMainMenu().setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    message.setText(result + " already exists.");
                }
            }
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        Back.addActionListener(evt -> {
            new Sound("click");
            setVisible(false);
            Window window = (Window) SwingUtilities.getRoot(getParent());
            window.getCl().show(window.getCards(), "loginpanel");
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