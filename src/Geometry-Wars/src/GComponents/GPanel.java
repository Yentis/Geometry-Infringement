package GComponents;

import GUI.*;
import GUI.Window;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.io.IOException;


/**
 * Created by Renzie on 10/11/2016.
 */
public abstract class GPanel extends JPanel {

    private Component[] components = this.getComponents();

    public GPanel(){
        this.setLayout(null);
        this.setSize(1024,768);
        this.setOpaque(false);
    }

    protected void resizeComponents(int width, int height){
        for (Component c : components){
            System.out.println(c);
            c.setBounds( c.getX() * width / height, c.getY() * width / height, c.getWidth() * width / height, c.getHeight() * width / height);
        }
    }

    public void setAllComponentsVisible(boolean toVisibleOrInvisible){
        for (Component component : components){
            component.setVisible(toVisibleOrInvisible);
        }
    }

    public abstract void initComponents() throws IOException, FontFormatException;


}
