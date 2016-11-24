package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Renzie on 19/11/2016.
 */
public class SpeelveldRenzie extends JFrame{
    public SpeelveldRenzie(){
        add(new BoardRenzie());

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
                SpeelveldRenzie sv = new SpeelveldRenzie();
                sv.setVisible(true);
            }
        });
    }
}
