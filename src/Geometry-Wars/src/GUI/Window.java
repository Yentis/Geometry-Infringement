package GUI;

import Game.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import GComponents.*;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Window extends JFrame {

    // Panels
    // TODO fill in other panels , add getters too
    private MainMenu mainMenu;
    private StartGame startGame;
    private Profile profile;


    // this
    private Window frame = this;

    public Window(String title) throws IOException, FontFormatException {

        // Set Window size and stuff
        this.setTitle(title);
        this.setPreferredSize(new Dimension(1024, 768));
        this.setMaximumSize(new Dimension(1024, 768));
        this.setMinimumSize(new Dimension(1024, 768));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Set the panels
        initPanels();

        // Set the background
        setBackground();


        // Start the frame
        this.setVisible(true);


        pack();

    }

    // Set Background
    public void setBackground(){

        JLabel bg = new JLabel();
        bg.setLayout(null);
        bg.setSize(1024,768);
        ImageIcon icon = new ImageIcon("src\\Media\\Background-768.png");
        bg.setIcon(icon);
        frame.add(bg);
        bg.setVisible(true);

    }




    public void initPanels() throws IOException, FontFormatException {
        // Make UI panels
        mainMenu = new MainMenu();
        startGame = new StartGame();
        profile = new Profile();

        // Add UI panels
        frame.add(mainMenu);
        frame.add(startGame);
        frame.add(profile);


        // Set all panels invisible except the starting panel
        startGame.setVisible(false);
        profile.setVisible(false);
    }


    // Getters
    public StartGame getStartGame() {
        return startGame;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public Profile getProfile(){ return profile; }




    public static void main(String args[]) throws IOException, FontFormatException {
       new Window("Geometry Wars");
    }


}
