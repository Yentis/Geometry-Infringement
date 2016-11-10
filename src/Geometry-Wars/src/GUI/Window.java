package GUI;

import Game.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Window extends JFrame {


    //private static final long serialVersionUID = 573860602378245302L;


    // Geeft enkel de Window weer
    public Window(String title) throws IOException, FontFormatException {

        // Set Window size and stuff
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setMaximumSize(new Dimension(1920, 1080));
        this.setMinimumSize(new Dimension(1920, 1080));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);





        this.setVisible(true);

        //mainMenu.setVisible(false);

    }

    // Set Background
    public void setBackground(){

        JLabel bg = new JLabel();
        bg.setSize(1920,1080);
        ImageIcon icon = new ImageIcon("src\\Media\\Background.png");
        bg.setIcon(icon);
        this.add(bg);
    }


    public void initGUI() throws IOException, FontFormatException {
        // Make UI panels
        MainMenu mainMenu = new MainMenu();


        // Add UI panels
        this.add(mainMenu);

    }





    public static void main(String args[]) throws IOException, FontFormatException {
       new Window("Geometry Wars");


    }


}
