package GUI;

import GComponents.GButton;
import GComponents.GInputField;
import GComponents.GLabel;
import GComponents.GPasswordField;
import Game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Random;

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
    private BackgroundPane background = new BackgroundPane();

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
                background.setBackground(ImageIO.read(getClass().getResourceAsStream("/Media/Background.png")));
                background.setImgSize(Toolkit.getDefaultToolkit().getScreenSize());

                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Font/Audiowide-Regular.ttf")));

                setTitle(title);
                cards = new JPanel(cl);
                cards.setOpaque(false);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setContentPane(background);
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

    MainMenu getMainMenu() {
        return mainMenu;
    }

    StartGameCampaign getStartGameCampaign() {
        return startGameCampaign;
    }

    Profile getProfile() {
        return profile;
    }

    InGame getInGame() {return inGame;}

    Settings getSettings() {
        return settings;
    }

    Highscores getHighScores() {
        return highscores;
    }

    Upgrades getUpgrades() {
        return upgrades;

    }

    public void setBackgroundPane(Dimension size) {
        background.setImgSize(size);
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
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private class BackgroundPane extends JPanel {
        private BufferedImage img;
        private BufferedImage scaled;
        private int w;
        private int h;

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                w = img.getWidth();
                h = img.getHeight();
                repaint();
            }
        }

        void setImgSize(Dimension size){
            scaled = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = AffineTransform.getScaleInstance(size.getWidth() / w, size.getHeight() / h);
            AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            scaled = scaleOp.filter(img, scaled);
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : Toolkit.getDefaultToolkit().getScreenSize();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (scaled != null) {
                g.drawImage(scaled, 0, 0, this);
            }
        }
    }

    //endregion
}