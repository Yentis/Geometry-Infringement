package GComponents;

import GUI.*;
import GUI.Window;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.io.IOException;


/**
 * Created by Renzie on 10/11/2016.
 */
public abstract class GPanel extends JPanel {

    /*private GPanel panel = this;
    private Window window = (Window) SwingUtilities.getRoot(panel.getParent());
    */

    public GPanel(){
        this.setLayout(null);
        this.setSize(1920,1080);
        this.setOpaque(false);

    }

    public abstract void initComponents() throws IOException, FontFormatException;


}
