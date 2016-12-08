package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by Renzie on 26/11/2016.
 */
public class Movement implements KeyListener {
    private Schip schip;
    private Timer startMoves;
    private int targetAngle;

    // Constructor
    public Movement(Schip schip) {
        this.schip = schip;
        setTimer();

    }



    private void setTimer() {
        startMoves = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(10, targetAngle);
            }
        });
        startMoves.start();
    }

    private void rotateCounterClockwise(int degrees) {

        schip.setCurrentAngle(schip.getCurrentAngle() - degrees);
    }

    private void rotateClockwise(int degrees) {
        schip.setCurrentAngle(schip.getCurrentAngle() + degrees);
    }

    private double rotate(int degrees, int targetAngle) {
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

    public boolean timerIsRunning(){
        return startMoves.isRunning();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }



    @Override
    public void keyPressed(KeyEvent e) {
        //keyPressed = true;

        int key = e.getKeyCode();
        // Movement: graden worden in radialen omgezet in Board
        switch (key) {
            case KeyEvent.VK_LEFT:
                targetAngle = 270;
                schip.moveLeft(3);

                break;
            case KeyEvent.VK_RIGHT:
                targetAngle = 90;
                schip.moveRight(3);

                break;
            case KeyEvent.VK_UP:
                targetAngle = 0;
                schip.moveUp(3);

                break;
            case KeyEvent.VK_DOWN:
                targetAngle = 180;
                schip.moveDown(3);

                break;
        }
    }


    public void keyReleased(KeyEvent e) {
        //keyPressed = true;
        int key = e.getKeyCode();
        // Movement: graden worden in radialen omgezet in Board
        switch (key) {
            case KeyEvent.VK_LEFT:
                schip.moveLeft(0);


                break;
            case KeyEvent.VK_RIGHT:
                schip.moveRight(0);

                break;
            case KeyEvent.VK_UP:
                schip.moveUp(0);

                break;
            case KeyEvent.VK_DOWN:
                schip.moveDown(0);

                break;
        }
    }


}

