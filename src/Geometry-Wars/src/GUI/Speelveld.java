package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Yentl-PC on 9/11/2016.
 */
public class Speelveld extends JFrame{
    public Speelveld() throws IOException, FontFormatException {
        add(new GamePanel());

        setSize(1024, 768);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        setResizable(false);

        setTitle("derp");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Speelveld sv = null;
                try {
                    sv = new Speelveld();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }

                sv.setVisible(true);
            }
        });
    }



}
