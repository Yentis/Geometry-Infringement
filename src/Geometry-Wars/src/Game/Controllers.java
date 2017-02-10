package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.sound.sampled.Control;
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
                System.out.println(controller.getType());
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

            Controller.Type type = controller.getType();

            // X axis, Y axis
            final int[] rxAxis = {getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RX).getPollData())};
            final int[] ryAxis = {getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RY).getPollData())};
            int[] zAxis = null;
            int[] rzAxis = null;
            if(type == Controller.Type.STICK){
                zAxis = new int[]{getAxisValueInPercentage(controller.getComponent(Identifier.Axis.Z).getPollData())};
                rzAxis = new int[]{getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RZ).getPollData())};
            }

            if (mousePressedTimer == null){
                int[] finalZAxis = zAxis;
                int[] finalRzAxis = rzAxis;
                mousePressedTimer = new Timer(150, e -> {
                    if(controller.getComponent(Identifier.Button._5).getPollData() != 0.0f){

                        if(type == Controller.Type.STICK){
                            finalZAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.Z).getPollData());
                            finalRzAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RZ).getPollData());

                            schip.controllerAim(finalZAxis[0] * (schip.getSCREEN_WIDTH() / 100), finalRzAxis[0] * (schip.getSCREEN_HEIGHT() / 100));
                        } else {
                            rxAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RX).getPollData());
                            ryAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RY).getPollData());

                            schip.controllerAim(rxAxis[0] * (schip.getSCREEN_WIDTH() / 100), ryAxis[0] * (schip.getSCREEN_HEIGHT() / 100));
                        }

                    }
                });
            }else{
                mousePressedTimer.start();
            }

            int xAxis = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.X).getPollData());
            int yAxis = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.Y).getPollData());

            // X axis
            if(xAxis <= 35){
                //Pointing left
                schip.controllerPressed(81);
            } else if (xAxis >= 65){
                //Pointing right
                schip.controllerPressed(68);
            } else {
                schip.controllerReleased(81);
                schip.controllerReleased(68);
            }

            // Y axis
            if(yAxis <= 35){
                //Pointing up
                schip.controllerPressed(90);
            } else if (yAxis >= 65){
                //Pointing down
                schip.controllerPressed(83);
            } else {
                schip.controllerReleased(90);
                schip.controllerReleased(83);
            }

            // We have to give processor some rest.
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getAxisValueInPercentage(float axisValue)
    {
        return (int)(((2 - (1 - axisValue)) * 100) / 2);
    }

    //endregion
}
