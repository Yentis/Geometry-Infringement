package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.*;

/**
 * Created by Yentl-PC on 21/12/2016.
 */
public class Controllers implements Runnable {
    private ArrayList<Controller> foundControllers;
    private Schip schip;
    private Timer mousePressedTimer;
    private int index;

    public void run() {
        startShowingControllerData(index);
    }

    public Controllers(Schip schip, int index) {
        this.schip = schip;
        this.index = index;
        foundControllers = new ArrayList<>();
        searchForControllers();

        (new Thread(this)).start();
    }

    private void searchForControllers() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for(int i = 0; i < controllers.length; i++){
            Controller controller = controllers[i];

            if (
                    controller.getType() == Controller.Type.STICK ||
                            controller.getType() == Controller.Type.GAMEPAD ||
                            controller.getType() == Controller.Type.WHEEL ||
                            controller.getType() == Controller.Type.FINGERSTICK
                    )
            {
                // Add new controller to the list of all controllers.
                foundControllers.add(controller);
            }
        }
    }

    private void startShowingControllerData(int index){
        while(true){
            // Currently selected controller.
            try{
                Controller controller = foundControllers.get(index);

                if( !controller.poll() ){
                    break;
                }

                // X axis and Y axis
                final int[] rxAxis = {getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RX).getPollData())};
                final int[] ryAxis = {getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RY).getPollData())};

                if (mousePressedTimer == null){
                    mousePressedTimer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(controller.getComponent(Identifier.Button._5).getPollData() != 0.0f){

                                rxAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RX).getPollData());
                                ryAxis[0] = getAxisValueInPercentage(controller.getComponent(Identifier.Axis.RY).getPollData());

                                schip.controllerAim(rxAxis[0] * 10.24, ryAxis[0] * 7.68);
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
            } catch (IndexOutOfBoundsException e){
            }
        }
    }

    public int getAxisValueInPercentage(float axisValue)
    {
        return (int)(((2 - (1 - axisValue)) * 100) / 2);
    }
}
