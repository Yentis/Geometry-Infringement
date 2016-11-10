/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.gm;



import GComponents.*;
import Game.Spel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Renzie
 */
public class Register {

    public Register() throws MalformedURLException, IOException, FontFormatException {
        // TODO code application logic here

        //Make components
        //==================================================

        JFrame frame = new JFrame("Test");
        JButton register = new GButton("Register",36f,1621,831,265,50);
        JLabel registered = new GLabel("Registration Successful", 16f,1358,815,550,80,false, Color.BLACK);
        JTextField username = new JTextField();
        JLabel lblusername = new GLabel("Username:", 20f,1358,275,130,24,false, Color.BLACK);
        JPasswordField password = new JPasswordField();
        JLabel lblpassword = new GLabel("Password:",20f,1358,349,130,24,false, Color.BLACK);
        JPasswordField Rpassword = new JPasswordField();
        JLabel lblRpassword = new GLabel("Retype Password:",20f, 1358,419,210,24,false, Color.BLACK);
        JTextField email = new JTextField();
        JLabel lblemail = new GLabel("Email:", 20f, 1358,552,130,24, false, Color.BLACK);
        JTextField Remail = new JTextField();
        JLabel lblRemail = new GLabel("Retype Email:",20f,1358, 621, 190,24, false, Color.BLACK);
        JLabel RegisterPane = new JLabel();
        JLabel Info = new GLabel("At least 8 characters [a-z/0-9]", 16f, 1585,464,300,24, false, Color.BLACK);
        JLabel Background = new JLabel(new ImageIcon(((new ImageIcon("src\\Media\\Background.png")).getImage().getScaledInstance(1920,1080, java.awt.Image.SCALE_SMOOTH))));
        JLabel Title = new GTitle(300,187);

        //==================================================

        //Set Properties
        //==================================================
        username.setFont(new GFont(20));
        password.setFont(new GFont(20));
        Rpassword.setFont(new GFont(20));
        email.setFont(new GFont(20));
        Remail.setFont(new GFont(20));
        registered.setFont(new GFont(16));
        Info.setFont(new GFont(16));
       
        RegisterPane.setOpaque(true);
        RegisterPane.setBackground(new Color(255,255,255,95));
               
        //==================================================

        //Set Bounds
        //==================================================

        Title.setBounds(300,187,900,131);
        username.setBounds(1585,262,300,50);
        password.setBounds(1585,336,300,50);
        Rpassword.setBounds(1585,406,300,50);
        email.setBounds(1585,539,300,50);
        Remail.setBounds(1585,608,300,50);
        
        registered.setBounds(1358,815,550,80);
        RegisterPane.setBounds(1241,187,679,724);
        Background.setBounds(0,0,1920,1080);

        //==================================================

        //Add Components
        //==================================================

        frame.add(register);
        frame.add(username);
        frame.add(password);
        frame.add(Rpassword);
        frame.add(email);
        frame.add(Remail);
        frame.add(lblusername);
        frame.add(lblpassword);
        frame.add(lblRpassword);
        frame.add(Info);
        frame.add(lblemail);
        frame.add(lblRemail);
        frame.add(Title);
        frame.add(registered);
        frame.add(RegisterPane);
        frame.add(Background);

        //==================================================

        //Behaviour
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(username.getText(), "")){
                    registered.setText("Please enter a username");
                } else if (password.getPassword().length == 0){
                    registered.setText("Please enter a password");
                } else if (Rpassword.getPassword().length == 0){
                    registered.setText("Please confirm your password");
                } else if (Objects.equals(email.getText(), "")){
                    registered.setText("Please enter your email address");
                } else if (!Arrays.equals(password.getPassword(), Rpassword.getPassword())){
                    registered.setText("Your passwords do not match, please try again");
                } else {
                    String result = checkAndCreate(username.getText(), password.getPassword(), email.getText());

                    if(Objects.equals(result, "")){
                        registered.setText("Registration Successful");
                    } else {
                        registered.setText(result + " bestaat al.");
                    }
                }
                registered.setVisible(true);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }





    /*public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new Register();
    }*/


    private String checkAndCreate(String gebruikersnaam, char[] password, String email){
        Spel spel = new Spel();
        String result = "";
        try {
            result = spel.infoChecker(gebruikersnaam, email);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        if(Objects.equals(result, "")){
            try {
                spel.registerPlayer(gebruikersnaam, password, email);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        } else {
            return result;
        }
        return "";
    }
}
