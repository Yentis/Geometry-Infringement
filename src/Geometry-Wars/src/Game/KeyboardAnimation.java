package Game;

import javax.swing.*;
import java.awt.event.*;


/**
 * Created by Renzie on 23/11/2016.
 */
public class KeyboardAnimation implements ActionListener {

    private Timer timer;
    private JComponent component;

    public KeyboardAnimation(JComponent component, int delay){
        timer = new Timer(delay, this);
        timer.setInitialDelay(0);
    }

    public void addAction(String keyStroke){
        //      InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        InputMap inputMap = component.getInputMap();
        ActionMap actionMap = component.getActionMap();

        String pressedKey = "PRESSED " + keyStroke;
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke( pressedKey );
       // Action pressedAction = new AnimationAction(keyStroke);

        String releasedKey = "Released " + keyStroke;
        KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke( releasedKey );
        //Action releasedAction = new AnimationAction(keyStroke);
        inputMap.put(releasedKeyStroke, releasedKey);
        //actionMap.put(releasedKey, releasedAction);
    }

    //public void rotate()

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class AnimationAction extends AbstractAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
