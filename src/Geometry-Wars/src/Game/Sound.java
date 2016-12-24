package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.*;

/**
 * Created by Yentl-PC on 23/12/2016.
 */
public class Sound implements Runnable{
    //region Instance Variables

    private String location;

    //endregion

    //region Constructors

    public Sound(String location){
        this.location = location;

        (new Thread(this)).start();
    }

    //endregion

    //region Behaviour

    public void run() {
        try {
            playSound();
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private void playSound() throws IOException, UnsupportedAudioFileException {
        File clipFile = new File(location);
        AudioListener listener = new AudioListener();

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile)) {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                clip.close();
            }
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region Classes

    private class AudioListener implements LineListener {
        private boolean done = false;
        @Override public synchronized void update(LineEvent event) {
            Type eventType = event.getType();
            if (eventType == Type.STOP || eventType == Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }
        synchronized void waitUntilDone() throws InterruptedException {
            while (!done) { wait(); }
        }
    }

    //endregion
}
