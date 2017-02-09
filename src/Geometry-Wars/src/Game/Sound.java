package Game;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Created by Yentl on 25-Dec-16.
 */
public class Sound {
    private Clip clip;

    private class AudioListener implements LineListener {
        @Override public synchronized void update(LineEvent event) {
            LineEvent.Type eventType = event.getType();
            if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                notifyAll();
            }
        }
    }

    private void playSfx(final InputStream fileStream) {
        ActivityManager.getInstance().submit(() -> {
            try {
                BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedStream);

                final int BUFFER_SIZE = 128000;
                SourceDataLine sourceLine = null;

                AudioFormat audioFormat = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);

                sourceLine.start();
                int nBytesRead = 0;
                byte[] abData = new byte[BUFFER_SIZE];
                while (nBytesRead != -1) {
                    try {
                        nBytesRead = bufferedStream.read(abData, 0, abData.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (nBytesRead >= 0) {
                        sourceLine.write(abData, 0, nBytesRead);
                    }
                }

                sourceLine.drain();
                sourceLine.close();
                bufferedStream.close();
                audioInputStream.close();

            } catch (IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    private void playBgm(final InputStream fileStream){
        InputStream bufferedIn = new BufferedInputStream(fileStream);
        AudioListener listener = new AudioListener();
        AudioInputStream inputStream = null;

        try{
            inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopMusic(){
        clip.stop();
        clip.close();
    }

    public void pauseMusic(){
        clip.stop();
    }

    public void playMusic(){
        clip.start();
    }

    public Sound(String soundEffect) {
        InputStream sound = getClass().getResourceAsStream("/Sound/" + soundEffect + ".wav");

        switch (soundEffect) {
            case "enemydeath":
            case "playerdeath":
            case "playerhit":
            case "shoot":
            case "shieldactive":
            case "click":
            case "shieldinactive":
                playSfx(sound);
                break;
            case "mainmenu":
                playBgm(sound);
                break;
        }
    }
}
