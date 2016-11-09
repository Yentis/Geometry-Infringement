package GUI;

import Game.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Window extends Canvas {
    private JFrame frame;

    private static final long serialVersionUID = 573860602378245302L;

    public Window(String title /*, Main game*/ ) throws IOException {
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setMaximumSize(new Dimension(1920, 1080));
        frame.setMinimumSize(new Dimension(1920, 1080));

        frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.add(game);
        frame.setVisible(true);
        //game.start();
    }

    public static void main(String args[]) throws IOException {
        Window window = new Window("Geometry Wars");
    }

    public JFrame getFrame() {
        return frame;
    }

    public static enum STATE{
        MENU,
        GAME,
        REGISTER,
        PROFILE
    };

    private STATE State = STATE.MENU;
}
