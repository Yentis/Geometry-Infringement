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
public class Window extends Canvas {
    private static JFrame frame;
    private boolean isStarted = false;
    private static Window instance;

    private static final long serialVersionUID = 573860602378245302L;


    // Geeft enkel de Window weer
    public Window(String title /*, Main game*/ ) throws IOException, FontFormatException {
        isStarted = true;
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setMaximumSize(new Dimension(1920, 1080));
        frame.setMinimumSize(new Dimension(1920, 1080));

       /* frame.setContentPane(new JPanel() {

            BufferedImage image = ImageIO.read(new File("src\\Media\\Background.png"));

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, 1920, 1080, this);

            }
        });*/

        JLabel thumb = new JLabel();
        thumb.setSize(1920,1080);
        frame.setLayout(null); thumb.setLayout(null);
        ImageIcon icon = new ImageIcon("src\\Media\\Background.png");
        thumb.setIcon(icon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.add(game);

        //game.start();

        frame.add(thumb);
        frame.setVisible(true);

    }



    /*public static Window getInstance() throws IOException {
        if (instance == null) {

                instance = new Window("Geometry Wars");
            System.out.println(instance);

        }
        return instance;
    }
*/
    public JFrame getFrame() {
        return frame;
    }



    public static void main(String args[]) throws IOException, FontFormatException {
        init();


        new MainMenu(frame);

    }


    public static void init() throws IOException, FontFormatException {
        Window window = new Window("Geometry Wars");
        //new MainMenu(frame);
    }
    //private MainMenu menu = new MainMenu();
    /*public static STATE state = STATE.MainMenu;
    public enum STATE{
        MainMenu,
        Register
    };
*/

}
