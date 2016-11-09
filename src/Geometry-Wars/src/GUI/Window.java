package GUI;

import Game.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Window extends Canvas {

    private static final long serialVersionUID = 573860602378245302L;

    public Window(int width, int height, String title, Main game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
