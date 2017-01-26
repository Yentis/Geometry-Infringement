package Game;

import jdk.internal.util.xml.impl.Input;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Created by Yentl on 25-Dec-16.
 */
public class Sound {
    private class AudioListener implements LineListener {
        private boolean done = false;
        @Override public synchronized void update(LineEvent event) {
            LineEvent.Type eventType = event.getType();
            if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }
        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) { wait(); }
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

                if (sourceLine == null) {
                    return;
                }

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
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(10000);
        } catch (InterruptedException | LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Sound(String soundEffect) {
        try{
            switch (soundEffect) {
                case "enemydeath":
                    playSfx(new FileInputStream("resources/Sound/enemydeath.wav"));
                    break;
                case "playerdeath":
                    playSfx(new FileInputStream("resources/Sound/playerdeath.wav"));
                    break;
                case "playerhit":
                    playSfx(new FileInputStream("resources/Sound/playerhit.wav"));
                    break;
                case "shoot":
                    playSfx(new FileInputStream("resources/Sound/shoot.wav"));
                    break;
                case "shieldactive":
                    playSfx(new FileInputStream("resources/Sound/shieldactive.wav"));
                    break;
                case "shieldinactive":
                    playSfx(new FileInputStream("resources/Sound/shieldinactive.wav"));
                    break;
                case "mainmenu":
                    playBgm(new FileInputStream("resources/Sound/mainmenu.wav"));
                    break;
                case "click":
                    playSfx(new FileInputStream("resources/Sound/click.wav"));
                    break;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
