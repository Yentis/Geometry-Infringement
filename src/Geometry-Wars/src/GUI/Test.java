package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

import Font.GFont;

/**
 * Created by Renzie on 9/11/2016.
 */
public class Test extends JPanel{



    public void addComponentsToPane(Container pane, JFrame frame) throws IOException, FontFormatException {

        frame.setLayout(new CardLayout());

        //Make components
        //==================================================


       /* JButton StartGame = new JButton("StartGame");
        JButton Upgrades = new JButton("Upgrades");
        JButton Profile = new JButton("Profile");
        JButton Settings = new JButton("Settings");
        JLabel label = new JLabel("title", SwingConstants.CENTER);
*/

        //==================================================
        //Set Properties
        //==================================================
       /* label.setFont(new GFont(80));

        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255, 95));
        StartGame.setFont(new GFont(36));
        StartGame.setOpaque(true);
        StartGame.setBackground(new Color(255, 255, 255, 200));
        Upgrades.setFont(new GFont(36));
        Upgrades.setOpaque(true);
        Upgrades.setBackground(new Color(255, 255, 255, 200));
        Profile.setFont(new GFont(36));
        Profile.setOpaque(true);
        Profile.setBackground(new Color(255, 255, 255, 200));
        Settings.setFont(new GFont(36));
        Settings.setOpaque(true);
        Settings.setBackground(new Color(255, 255, 255, 200));
*/
        //==================================================
        //Set Bounds
        //==================================================

       /* StartGame.setBounds(350, 250, 300, 80);

        Upgrades.setBounds(475, 425, 300, 80);
        Profile.setBounds(535, 600, 300, 80);
        Settings.setBounds(550, 775, 300, 80);
        label.setBounds(25, 25, 800, 125);

        //==================================================
        //Add Components
        //==================================================
        frame.add(StartGame);
        pane.add(Profile);
        frame.add(Upgrades);
        frame.add(Settings);
        frame.add(label);
        */
        //==================================================



    }


    public void createAndShowUI(JFrame frame) throws MalformedURLException, IOException, FontFormatException {
        addComponentsToPane(frame.getContentPane(), frame);

        frame.pack();
        frame.setVisible(true);
    }

    public Test(JFrame frame) throws IOException, FontFormatException {
        createAndShowUI(frame);
    }




   /* public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException {
        new MainMenu();
    }*/
}
