package Game;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Created by Yentl on 25-Dec-16.
 */
public class Sound {
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

            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    public Sound(String soundEffect) throws FileNotFoundException {

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
            case "song":
                playSfx(new FileInputStream("resources/Sound/song.wav"));
                break;
        }
    }
}
