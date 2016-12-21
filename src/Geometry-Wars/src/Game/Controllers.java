package Game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Created by Yentl-PC on 21/12/2016.
 */
public class Controllers implements Runnable {
    private ArrayList<Controller> foundControllers;
    private Schip schip;

    public void run() {
        startShowingControllerData();
    }

    public Controllers(Schip schip) {
        this.schip = schip;
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

    private void startShowingControllerData(){
        while(true){
            // Currently selected controller.
            Controller controller = foundControllers.get(0);

            if( !controller.poll() ){
                break;
            }

            // X axis and Y axis
            int xAxisPercentage = 0;
            int yAxisPercentage = 0;

            Component[] components = controller.getComponents();

            for(int i=0; i < components.length; i++)
            {
                Component component = components[i];
                Identifier componentIdentifier = component.getIdentifier();

                // Axes
                if(component.isAnalog()){
                    float axisValue = component.getPollData();
                    int axisValueInPercentage = getAxisValueInPercentage(axisValue);

                    // X axis
                    if(componentIdentifier == Component.Identifier.Axis.X){
                        xAxisPercentage = axisValueInPercentage;

                        if(xAxisPercentage <= 35){
                            //Pointing left
                            System.out.println("left");
                            schip.controllerPressed(0);
                        } else if (xAxisPercentage >= 65){
                            //Pointing right
                            System.out.println("right");
                            schip.controllerPressed(1);
                        } else {
                            schip.controllerReleased(0);
                            schip.controllerReleased(1);
                        }
                        continue; // Go to next component.
                    }
                    // Y axis
                    if(componentIdentifier == Component.Identifier.Axis.Y){
                        yAxisPercentage = axisValueInPercentage;

                        if(yAxisPercentage <= 35){
                            //Pointing up
                            System.out.println("up");
                            schip.controllerPressed(2);
                        } else if (yAxisPercentage >= 65){
                            //Pointing down
                            System.out.println("down");
                            schip.controllerPressed(3);
                        } else {
                            schip.controllerReleased(2);
                            schip.controllerReleased(3);
                        }
                        continue; // Go to next component.
                    }
                }
            }


            // We have to give processor some rest.
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getAxisValueInPercentage(float axisValue)
    {
        return (int)(((2 - (1 - axisValue)) * 100) / 2);
    }
}
