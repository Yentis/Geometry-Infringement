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
    private double targetAngle;
    private int keyLeft;
    private int keyRight;
    private int keyUp;
    private int keyDown;

    // Constructor
    public Movement(Schip schip, int keyLeft, int keyRight, int keyUp, int keyDown) {
        this.schip = schip;
        setTimer();
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
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

    private void rotateCounterClockwise(double degrees) {

        schip.setCurrentAngle(schip.getCurrentAngle() - degrees);
    }

    private void rotateClockwise(double degrees) {
        schip.setCurrentAngle(schip.getCurrentAngle() + degrees);
    }

    private double rotate(double degrees, double targetAngle) {
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
        System.out.println(key);
        // Movement: graden worden in radialen omgezet in Board
        if(key == keyLeft){
            targetAngle = 270;
            schip.moveLeft(3);
        } else if (key == keyRight){
            targetAngle = 90;
            schip.moveRight(3);
        } else if (key == keyUp){
            targetAngle = 0;
            schip.moveUp(3);
        } else if (key == keyDown){
            targetAngle = 180;
            schip.moveDown(3);
        }
    }


    public void keyReleased(KeyEvent e) {
        //keyPressed = true;
        int key = e.getKeyCode();
        // Movement: graden worden in radialen omgezet in Board
        if(key == keyLeft){
            schip.moveLeft(0);
        } else if (key == keyRight){
            schip.moveRight(0);
        } else if (key == keyUp){
            schip.moveUp(0);
        } else if (key == keyDown){
            schip.moveDown(0);
        }
    }


}

