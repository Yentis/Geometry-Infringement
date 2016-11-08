package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Game extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 7364682855700581664L;

    Toolkit tk = Toolkit.getDefaultToolkit();
    public int WIDTH = (int) tk.getScreenSize().getWidth(), HEIGHT = (int) tk.getScreenSize().getHeight();

    private Thread thread;
    private boolean running = false;

    public Game(){
        new Window(WIDTH,HEIGHT,"Geometry Wars",this);
    }

    // Start the game
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    // Stop the Game
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void run() {
        long lastTime = System.nanoTime(); // get current time to the nanosecond
        double amountOfTicks = 60.0; // set the number of ticks
        double ns = 1000000000 / amountOfTicks; // this determines how many times we can divide 60 into 1e9 of nano seconds or about 1 second
        double delta = 0; // change in time (delta always means a change like delta v is change in velocity)
        long timer = System.currentTimeMillis(); // get current time
        int frames = 0; // set frame variable
        while(running){
            long now = System.nanoTime(); // get current time in nanoseconds during current loop
            delta += (now - lastTime) / ns; // add the amount of change since the last loop
            lastTime = now;  // set lastTime to now to prepare for next loop
            while(delta >= 1){
                /* one tick of time has passed in the game this
                ensures that we have a steady pause in our game loop
                so we don't have a game that runs way too fast
                basically we are waiting for  enough time to pass so we
                 have about 60 frames per one second interval determined to the nanosecond (accuracy)
                 once this pause is done we render the next frame */
                tick();
                delta--;  // lower our delta back to 0 to start our next frame wait
            }
            if(running){
                render(); // render the visuals of the game
            }
            frames++; // note that a frame has passed
            if(System.currentTimeMillis() - timer > 1000 ){ // if one second has passed
                timer+= 1000; // add a thousand to our timer for next time
                System.out.println("FPS: " + frames); // print out how many frames have happend in the last second
                frames = 0; // reset the frame count for the next second
            }
        }
        stop(); // no longer running stop the thread
    }

    private void tick(){

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]){
        new Game();
    }

}
