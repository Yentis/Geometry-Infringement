package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by Renzie on 26/11/2016.
 */
public class Rotation implements KeyListener {
    private Schip schip;
    private JPanel panel;
    private boolean keyPressed;
    private Timer left, right, up, down;
    private Rotation rotation;



    // Constructor
    public Rotation(JPanel panel, Schip schip) {
        this.panel = panel;
        this.schip = schip;
        setTimers();

    }



    private void setTimers() {
        left = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(10, 270);
            }
        });
        right = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(10, 90);
            }
        });
        up = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(10, 0);
            }
        });
        down = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(10, 180);
            }
        });
    }

    public void rotateCounterClockwise(int degrees) {

        schip.setCurrentAngle(schip.getCurrentAngle() - degrees);
    }

    public void rotateClockwise(int degrees) {
        schip.setCurrentAngle(schip.getCurrentAngle() + degrees);
    }

    public double rotate(int degrees, int targetAngle) {
            if (schip.getCurrentAngle() - targetAngle == 0) return schip.getCurrentAngle();

            if (schip.getCurrentAngle() < targetAngle && (targetAngle - schip.getCurrentAngle()) % 360 <= 180) {
                rotateClockwise(degrees);
            }
            if (targetAngle < schip.getCurrentAngle() && schip.getCurrentAngle() - targetAngle <= 180) {
                rotateCounterClockwise(degrees);
            }
            if (schip.getCurrentAngle() < targetAngle && targetAngle - schip.getCurrentAngle() >= 180) {
                rotateCounterClockwise(degrees);
            }
            if (targetAngle < schip.getCurrentAngle() && schip.getCurrentAngle() - targetAngle >= 180) {
                rotateClockwise(degrees);
            }
            schip.setCurrentAngle(schip.normalizeAngle(schip.getCurrentAngle()));


        return schip.getCurrentAngle();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //keyPressed = true;

        int key = e.getKeyCode();
        // Rotation: graden worden in radialen omgezet in Board
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (!left.isRunning()){
                    left.start();
                }

                break;
            case KeyEvent.VK_RIGHT:
                if (!right.isRunning()){
                    right.start();
                }

                break;
            case KeyEvent.VK_UP:
                if (!up.isRunning()){
                    up.start();
                }

                break;
            case KeyEvent.VK_DOWN:
                if (!down.isRunning()){
                    down.start();
                }

                break;
        }
    }


    public void keyReleased(KeyEvent e) {
        //keyPressed = true;
        int key = e.getKeyCode();
        // Rotation: graden worden in radialen omgezet in Board
        switch (key) {
            case KeyEvent.VK_LEFT:

                left.stop();
                break;
            case KeyEvent.VK_RIGHT:
                right.stop();
                break;
            case KeyEvent.VK_UP:
                up.stop();
                break;
            case KeyEvent.VK_DOWN:
                down.stop();
                break;
        }
    }


}

