/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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

        //create the font to use. Specify the size!
        Font font80 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\Audiowide-Regular.ttf")).deriveFont(80f);
        GraphicsEnvironment ge64 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge64.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\Audiowide-Regular.ttf")));

        Font font36 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\Audiowide-Regular.ttf")).deriveFont(36f);
        GraphicsEnvironment ge36 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge36.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\Audiowide-Regular.ttf")));

        Font font20 = Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\Audiowide-Regular.ttf")).deriveFont(20f);
        GraphicsEnvironment ge20 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge20.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\Audiowide-Regular.ttf")));

        //Make components
        //==================================================
        JFrame frame = new JFrame("Test");
        JButton register = new JButton("Register");
        JLabel registered = new JLabel("Registration Successful");
        JTextField username = new JTextField();
        JLabel lblusername = new JLabel("Username");
        JPasswordField password = new JPasswordField();
        JLabel lblpassword = new JLabel("Password");
        JPasswordField passwordconfirm = new JPasswordField();
        JLabel lblpasswordconfirm = new JLabel("Repeat Password");
        JTextField email = new JTextField();
        JLabel lblemail = new JLabel("E-mail");
        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);


        //==================================================

        //Set Properties
        //==================================================
        registered.setFont(font20);
        registered.setForeground(Color.WHITE);
        registered.setVisible(false);
        lblusername.setFont(font20);
        lblusername.setOpaque(true);
        lblusername.setBackground(new Color(255,255,255,95));
        lblpassword.setFont(font20);
        lblpassword.setOpaque(true);
        lblpassword.setBackground(new Color(255,255,255,95));
        lblpasswordconfirm.setFont(font20);
        lblpasswordconfirm.setOpaque(true);
        lblpasswordconfirm.setBackground(new Color(255,255,255,95));
        lblemail.setFont(font20);
        lblemail.setOpaque(true);
        lblemail.setBackground(new Color(255,255,255,95));
        label.setFont(font80);
        label.setOpaque(true);
        label.setBackground(new Color(255,255,255,95));
        register.setFont(font36);
        register.setOpaque(true);
        register.setBackground(new Color(255,255,255,200));
                /*.setFont(font36);
                Upgrades.setOpaque(true);
                Upgrades.setBackground(new Color(255,255,255,200));
                Profile.setFont(font36);
                Profile.setOpaque(true);
                Profile.setBackground(new Color(255,255,255,200));
                Settings.setFont(font36);
                Settings.setOpaque(true);
                Settings.setBackground(new Color(255,255,255,200));*/

        //==================================================

        frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });
        frame.setLayout(null);




        //Set Bounds
        //==================================================

        register.setBounds(900,700,300,80);
        username.setBounds(900,300,300,80);
        lblusername.setBounds(550,300,300,80);
        password.setBounds(900,400,300,80);
        lblpassword.setBounds(550,400,300,80);
        passwordconfirm.setBounds(900,500,300,80);
        lblpasswordconfirm.setBounds(550,500,300,80);
        email.setBounds(900,600,300,80);
        lblemail.setBounds(550,600,300,80);
        registered.setBounds(900,800,550,80);

        label.setBounds(25,25,800,125);

        //==================================================

        //Add Components
        //==================================================
        frame.add(register);
        frame.add(username);
        frame.add(password);
        frame.add(passwordconfirm);
        frame.add(email);
        frame.add(lblusername);
        frame.add(lblpassword);
        frame.add(lblpasswordconfirm);
        frame.add(lblemail);
        frame.add(label);
        frame.add(registered);

        //==================================================

        //Behaviour
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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





    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new Register();
    }

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
