package Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Renzie on 26/11/2016.
 */
public class Movement implements KeyListener {
    //region Instance Variables

    private Schip schip;
    private double targetAngle;
    private int keyLeft;
    private int keyRight;
    private int keyUp;
    private int keyDown;

    //endregion

    //region Constructors

    Movement(Schip schip, int keyLeft, int keyRight, int keyUp, int keyDown) {
        this.schip = schip;
        setTimer();
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
    }

    //endregion

    //region Behaviour

    private void setTimer() {
        Timer startMoves = new Timer(20, e -> rotate(10, targetAngle));
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

    void controllerPressed(int key){
        checkKeys(key, true);
    }

    void controllerReleased(int key){
        checkKeys(key, false);
    }

    private void checkKeys(int key, boolean pressed){
        if(pressed){
            if(key == keyLeft){
                targetAngle = 270;
                schip.moveLeft(schip.getSpeed());
            } else if (key == keyRight){
                targetAngle = 90;
                schip.moveRight(schip.getSpeed());
            } else if (key == keyUp){
                targetAngle = 0;
                schip.moveUp(schip.getSpeed());
            } else if (key == keyDown){
                targetAngle = 180;
                schip.moveDown(schip.getSpeed());
            }
        } else {
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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Movement: graden worden in radialen omgezet in Board
        checkKeys(key, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Movement: graden worden in radialen omgezet in GamePanel
        checkKeys(key, false);
    }

    //endregion
}

