package GUI;

import GComponents.GButton;
import GComponents.GFont;
import GComponents.GPane;
import GComponents.GPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Laurens Visser on 22/12/2016.
 */
public class PanelwControls extends GPanel{
    private PanelwControls panel = this;

    public PanelwControls() throws IOException, FontFormatException {
        initComponents();
    }

    @Override
    public void initComponents() throws IOException, FontFormatException {

        JLabel backgroundpane = new GPane(220, 160, 234, 300);

        //Add Components
        this.add(backgroundpane);









}}
