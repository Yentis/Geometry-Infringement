package GComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Renzie on 10/11/2016.
 */
public abstract class GPanel extends JPanel {
    private Component[] components = this.getComponents();

    public GPanel() {
        this.setLayout(null);
        this.setSize(1024, 768);
        this.setOpaque(false);

    }
    protected void resizeComponents(int width, int height){
        for (Component c : components){
            c.setBounds( c.getX() * width / height, c.getY() * width / height, c.getWidth() * width / height, c.getHeight() * width / height);
        }
    }

    protected void setAllComponentsVisible(){
        for (Component component : components){
            component.setVisible(true);
        }
    }

    public void setAllComponentsInVisible(){
        for (Component component : components){
            component.setVisible(false);
        }
    }

    public abstract void initComponents() throws IOException, FontFormatException;


}
