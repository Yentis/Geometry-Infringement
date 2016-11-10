package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yentl-PC on 9/11/2016.
 */
public class Speelveld extends JFrame {
    public Speelveld(){
        add(new Board());

        setSize(1640, 800);
        setResizable(false);

        setTitle("Geometry Wars");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Speelveld sv = new Speelveld();
                sv.setVisible(true);
            }
        });
    }
}
