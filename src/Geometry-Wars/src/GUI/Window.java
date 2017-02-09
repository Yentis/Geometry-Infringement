package GUI;

import GComponents.GButton;
import GComponents.GInputField;
import GComponents.GLabel;
import GComponents.GPasswordField;
import Game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Window extends JFrame {
    //region Instance Variables

    private MainMenu mainMenu;
    private StartGame startGame;
    private Profile profile;
    private StartGameCampaign startGameCampaign;
    private Logout logout;
    private Login login;
    private InGame inGame;
    private Register register;
    private Upgrades upgrades;
    private Settings settings;
    private Highscores highscores;
    private JPanel cards;
    private static Spel spel = null;
    private CardLayout cl = new CardLayout();

    //endregion

    //region Constructors

    public Window(String title) throws IOException, FontFormatException {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            try {
                BackgroundPane background1 = new BackgroundPane();
                background1.setBackground(ImageIO.read(new File("resources\\Media\\Background.png")));

                setTitle(title);
                cards = new JPanel(cl);
                cards.setOpaque(false);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setContentPane(background1);
                setLayout(new BorderLayout());

                // Set the panels
                initPanels();
                cl.show(cards, "loginpanel");
                add(cards, BorderLayout.CENTER);

                pack();
                setLocationRelativeTo(null);
                setVisible(true);
            } catch (IOException | FontFormatException exp) {
                exp.printStackTrace();
            }
        });
    }

    //endregion

    //region Getters & Setters

    JPanel getCards() {
        return cards;
    }

    CardLayout getCl() {
        return cl;
    }

    public Spel getSpel() {
        return spel;
    }

    StartGame getStartGame() {
        return startGame;
    }

    MainMenu getMainMenu() {
        return mainMenu;
    }

    StartGameCampaign getStartGameCampaign() {
        return startGameCampaign;
    }

    Profile getProfile() {
        return profile;
    }

    Logout getLogout() {
        return logout;
    }

    Login getLogin() {
        return login;
    }

    InGame getInGame() {return inGame;}

    Register getRegister() {
        return register;
    }

    Settings getSettings() {
        return settings;
    }

    Highscores getHighScores() {
        return highscores;
    }

    Upgrades getUpgrades() {
        return upgrades;

    }

    //endregion

    //region Behaviour

    public static void main(String args[]) throws IOException, FontFormatException, SQLException, InterruptedException {
        new Window("Geometry Wars");
    }

    private void initPanels() throws IOException, FontFormatException {
        // Make UI panels
        login = new Login();
        cards.add(login, "loginpanel");

        //Try to contact database
        try {
            spel = new Spel();

            mainMenu = new MainMenu();
            startGame = new StartGame();
            profile = new Profile();
            startGameCampaign = new StartGameCampaign();
            logout = new Logout();
            inGame = new InGame();
            upgrades = new Upgrades();
            register = new Register();
            settings = new Settings();
            highscores = new Highscores();

            // Add UI panels
            cards.add(mainMenu, "mainmenupanel");
            cards.add(startGame, "startgamepanel");
            cards.add(profile, "profilepanel");
            cards.add(startGameCampaign, "startgamecampaignpanel");
            cards.add(logout, "logoutpanel");
            cards.add(inGame, "ingamepanel");
            cards.add(register, "registerpanel");
            cards.add(upgrades, "upgradespanel");
            cards.add(settings, "settingspanel");
            cards.add(highscores, "highscorespanel");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class BackgroundPane extends JPanel {
        private BufferedImage img;
        private BufferedImage scaled;

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : Toolkit.getDefaultToolkit().getScreenSize();
        }

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                repaint();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            scaled = getScaledInstanceToFill(img, getSize());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (scaled != null) {
                int x = (getWidth() - scaled.getWidth()) / 2;
                int y = (getHeight() - scaled.getHeight()) / 2;
                g.drawImage(scaled, x, y, this);
            }
        }

    }

    private static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {

        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);

    }

    private static double getScaleFactorToFill(BufferedImage img, Dimension size) {
        double dScale = 1;

        if (img != null) {
            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);
        }

        return dScale;
    }

    private static double getScaleFactor(int iMasterSize, int iTargetSize) {
        double dScale = (double) iTargetSize / (double) iMasterSize;

        return dScale;
    }

    private static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {
        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {
        BufferedImage imgScale = img;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

        if (dScaleFactor <= 1.0d) {

            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        } else {

            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        }

        return imgScale;
    }

    protected static BufferedImage getScaledDownInstance(BufferedImage img,
                                                         int targetWidth,
                                                         int targetHeight,
                                                         Object hint,
                                                         boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        if (targetHeight > 0 || targetWidth > 0) {
            int w, h;
            if (higherQuality) {
                // Use multi-step technique: start with original size, then
                // scale down in multiple passes with drawImage()
                // until the target size is reached
                w = img.getWidth();
                h = img.getHeight();
            } else {
                // Use one-step technique: scale directly from original
                // size to target size with a single drawImage() call
                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    protected static BufferedImage getScaledUpInstance(BufferedImage img,
                                                       int targetWidth,
                                                       int targetHeight,
                                                       Object hint,
                                                       boolean higherQuality) {

        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
            tmp = null;

        } while (w != targetWidth || h != targetHeight);
        return ret;
    }

    //endregion
}