package GUI;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Renzie on 8/11/2016.
 */
public class MainMenu extends JFrame{


    private JButton bg;

    public MainMenu() {

    }

    private void createUIComponents() {
        bg.add(new JLabel(new ImageIcon("../Media/Background.png")));

    }
}
