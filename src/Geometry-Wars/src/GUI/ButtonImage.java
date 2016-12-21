package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Laurens Visser on 10/11/2016.
 */
public class ButtonImage extends JFrame {

    JButton pauze;
    ButtonImage() {

        ImageIcon PauseImage = new ImageIcon("resources\\Media\\pause-128.png");
        pauze = new JButton(PauseImage);

        setLayout(new FlowLayout());
        add(pauze);
        setVisible(true);
        setSize(600, 300);
        setLocation(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args)  throws MalformedURLException, IOException, FontFormatException
    {
        new ButtonImage();

    }




    }



