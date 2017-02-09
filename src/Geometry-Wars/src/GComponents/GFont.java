package GComponents;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Renzie on 9/11/2016.
 */
public class GFont extends Font{
    public GFont(int size) throws IOException, FontFormatException {
        super(new Font("Audiowide", Font.TRUETYPE_FONT, size));
        GraphicsEnvironment.getLocalGraphicsEnvironment();
    }
}

