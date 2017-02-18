package Game;

import java.util.ArrayList;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import javax.swing.*;

/**
 * Created by Yentl-PC on 21/12/2016.
 */
public class Controllers implements Runnable {
    //region Instance Variables

    private ArrayList<Controller> foundControllers;
    private Schip schip;
    private Timer mousePressedTimer;
    private int index;

    //endregion

    //region Constructors

    public Controllers(Schip schip, int index) {
        this.schip = schip;
        this.index = index;
        foundControllers = new ArrayList<>();
        searchForControllers();

        (new Thread(this)).start();
    }

    //endregion

    //region Behaviour

    public void run() {
        startShowingControllerData(index);
    }

    private void searchForControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller controller : controllers) {
            if (
                    controller.getType() == Controller.Type.STICK ||
                    controller.getType() == Controller.Type.GAMEPAD ||
                    controller.getType() == Controller.Type.WHEEL ||
                    controller.getType() == Controller.Type.FINGERSTICK
                ) {
                // Add new controller to the list of all controllers.
                foundControllers.add(controller);
            }
        }
    }

    private void startShowingControllerData(int index){
        while(true){
            // Currently selected controller.
            foundControllers.get(index);

            Controller controller = foundControllers.get(index);

            if( !controller.poll() ){
                break;
            }

            aimAndShoot(controller);
            movement(controller);

            // We have to give processor some rest.
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void movement(Controller controller){
        int xAxis = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.X).getPollData());
        int yAxis = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.Y).getPollData());

        moveDirection(xAxis, schip.getKeyLeft(), schip.getKeyRight());
        moveDirection(yAxis, schip.getKeyUp(), schip.getKeyDown());
    }

    private void moveDirection(int axis, int direction1, int direction2){
        if(axis <= 35){
            schip.controllerPressed(direction1);
        } else if (axis >= 65){
            schip.controllerPressed(direction2);
        } else {
            schip.controllerReleased(direction1);
            schip.controllerReleased(direction2);
        }
    }

    private void aimAndShoot(Controller controller){
        int shootDelay = 150;
        final int[] rxAxis;
        final int[] ryAxis;

        if(controller.getType() == Controller.Type.STICK){
            rxAxis = new int[]{getAxisValueInPercentage(controller.getComponent(Identifier.Axis.Z).getPollData())};
            ryAxis = new int[]{getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RZ).getPollData())};
        } else {
            rxAxis = new int[]{getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RX).getPollData())};
            ryAxis = new int[]{getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RY).getPollData())};
        }

        if (mousePressedTimer == null){
            mousePressedTimer = new Timer(shootDelay, e -> {
                if(controller.getComponent(Identifier.Button._5).getPollData() != 0.0f){
                    rxAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RX).getPollData());
                    ryAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RY).getPollData());

                    double locationX = (schip.getCurrentLocation().getX() + schip.getWidth() / 2) + ((rxAxis[0] * 8) - 400);
                    double locationY = (schip.getCurrentLocation().getY() + schip.getHeight() / 2) + ((ryAxis[0] * 8) - 400);

                    schip.controllerAim(locationX, locationY);
                }
            });
        }else{
            mousePressedTimer.start();
        }
    }

    private int getAxisValueInPercentage(float axisValue)
    {
        return (int)(((2 - (1 - axisValue)) * 100) / 2);
    }

    //endregion
}
