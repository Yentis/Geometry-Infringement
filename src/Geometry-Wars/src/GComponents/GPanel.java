package GComponents;

import GUI.*;
import GUI.Window;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;

import java.awt.event.ActionEvent;



/**
 * Created by Renzie on 10/11/2016.
 */
public class GPanel extends JPanel {

    /*private GPanel panel = this;
    private Window window = (Window) SwingUtilities.getRoot(panel.getParent());
    */

    public GPanel(){
        this.setLayout(null);
        this.setSize(1920,1080);
        this.setOpaque(false);

    }

   /* public Window getWindow(){
        return window;
    }

    public void SwitchToPanel(GPanel gPanel, ActionEvent evt){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                panel.setVisible(false);
                gPanel.setVisible(true);
            }
        });


    }
    */


    //private Window window = panel.getParent();;

    /*public Window getWindow(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                    Window window =

            }
        });
        return window;
    }*/
    /*public void SwitchPanelTo(GPanel gpanel, ActionEvent evt){
        panel.setVisible(false);
        Component component = (Component) evt.getSource();
        Window window = (Window) SwingUtilities.getRoot(component);
        window.getStartGame().setVisible(true);
    }*/
}
