package GUI;

import Game.*;
import javax.swing.*;
import java.awt.*;
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
    private static Spel spel = null;
    private Container cp = getContentPane();

    //endregion

    //region Constructors

    public Window(String title) throws IOException, FontFormatException {
        // Set Window size and stuff
        this.setTitle(title);
        this.setPreferredSize(new Dimension(1024, 768));
        this.setMaximumSize(new Dimension(1024, 768));
        this.setMinimumSize(new Dimension(1024, 768));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Set the panels
        initPanels();

        // Set the background
        setBackground();

        // Start the frame
        this.setVisible(true);

        pack();
    }

    //endregion

    //region Getters & Setters

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
        //new Sound("mainmenu");
    }

    // Set Background
    public void setBackground() {
        JLabel bg = new JLabel();
        bg.setLayout(null);
        bg.setSize(1024, 768);
        ImageIcon icon = new ImageIcon("resources\\Media\\resized_background-768.png");
        bg.setIcon(icon);
        cp.add(bg);
        bg.setVisible(true);
    }

    private void initPanels() throws IOException, FontFormatException {
        // Make UI panels
        login = new Login();
        cp.add(login);

        //Try to contact database
        try {
            spel = new Spel();

            mainMenu = new MainMenu();
            startGame = new StartGame();
            profile = new Profile();
            startGameCampaign = new StartGameCampaign();
            logout = new Logout();
            inGame = new InGame(spel.getEnemies());
            upgrades = new Upgrades();
            register = new Register();
            settings = new Settings();
            highscores = new Highscores();

            // Add UI panels
            cp.add(mainMenu);
            cp.add(startGame);
            cp.add(profile);
            cp.add(startGameCampaign);
            cp.add(logout);
            cp.add(inGame);
            cp.add(register);
            cp.add(upgrades);
            cp.add(settings);
            cp.add(highscores);

            // Set all panels invisible except the starting panel
            mainMenu.setVisible(false);
            startGame.setVisible(false);
            profile.setVisible(false);
            startGameCampaign.setVisible(false);
            logout.setVisible(false);
            inGame.setVisible(false);
            register.setVisible(false);
            upgrades.setVisible(false);
            settings.setVisible(false);
            highscores.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //endregion
}