package GComponents;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Renzie on 9/11/2016.
 */
public class GFont extends Font{

    public GFont(float size) throws IOException, FontFormatException {

        super(Font.createFont(Font.TRUETYPE_FONT, new File("src\\GComponents\\Audiowide-Regular.ttf")).deriveFont(size));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    }
}
