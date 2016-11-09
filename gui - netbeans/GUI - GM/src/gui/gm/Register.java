/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
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

    /**
     * @param args the command line arguments
     */
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



        //Make components
        //==================================================
        JFrame frame = new JFrame("Test");
        JButton register = new JButton("Register");
        JTextField username = new JTextField("Username");
        JPasswordField password = new JPasswordField("Password");
        JLabel label = new JLabel("Geometry Wars", SwingConstants.CENTER);


        //==================================================

        //Set text
        //==================================================





        //==================================================

        //Set Properties
        //==================================================
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

        register.setBounds(350,250,300,80);
        username.setBounds(475,425,300,80);
        password.setBounds(535,600,300,80);

        label.setBounds(25,25,800,125);

        //==================================================

        //Add Components
        //==================================================
        frame.add(register);
        frame.add(username);
        frame.add(password);

        frame.add(label);

        //==================================================




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }





    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new Register();
    }
}
