package GUI;

import Game.Spel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Renzie on 7/11/2016.
 */
public class Window extends JFrame{

    // Panels
    private MainMenu mainMenu;
    private StartGame startGame;
    private Profile profile;
    private StartGameCampaign startGameCampaign;
    private Logout logout;
    private Login login;
    private InGame inGame;
    private Scoreboard scoreboard;
    private Register register;
    private Coop startCoop;
    private Settings settings;
    private static Spel spel = new Spel();

    //Game
    private Game game;


    private Container cp = getContentPane();

    // this
    private Window frame = this;

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

    public static void main(String args[]) throws IOException, FontFormatException, SQLException, InterruptedException {
        spel.initDankabank();
        new Window("Geometry Wars");
    }

    public Spel getSpel(){
        return spel;
    }

    // Set Background
    public void setBackground(){
        JLabel bg = new JLabel();
        bg.setLayout(null);
        bg.setSize(1024,768);
        ImageIcon icon = new ImageIcon("src\\Media\\resized_background-768.png");
        bg.setIcon(icon);
        cp.add(bg);
        bg.setVisible(true);
    }



    public void initPanels() throws IOException, FontFormatException {
        // Make UI panels
        mainMenu = new MainMenu();
        startGame = new StartGame();
        profile = new Profile();
        startGameCampaign = new StartGameCampaign();
        logout  = new Logout();
        login = new Login();
        inGame = new InGame(spel.getEnemies());
        scoreboard = new Scoreboard();
        startCoop = new Coop(spel.getEnemies());
        register = new Register();
        settings = new Settings();

        // Add UI panels
        cp.add(mainMenu);
        cp.add(startGame);
        cp.add(profile);
        cp.add(startGameCampaign);
        cp.add(logout);
        cp.add(login);
        cp.add(inGame);
        cp.add(scoreboard);
        cp.add(startCoop);
        cp.add(register);
        cp.add(settings);


        // Set all panels invisible except the starting panel
        mainMenu.setVisible(false);
        startGame.setVisible(false);
        profile.setVisible(false);
        startGameCampaign.setVisible(false);
        logout.setVisible(false);
        inGame.setVisible(false);
        scoreboard.setVisible(false);
        startCoop.setVisible(false);
        register.setVisible(false);
        settings.setVisible(false);
    }


    // Getters
    public StartGame getStartGame() {
        return startGame;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public StartGameCampaign getStartGameCampaign() {
        return startGameCampaign;
    }

    public Profile getProfile(){ return profile; }

    public Logout getLogout(){ return logout; }

    public Login getLogin() {return login;}

    public InGame getInGameSinglePlayer() {return inGame;}

    public Scoreboard getScoreboard() {return scoreboard;}

    public Register getRegister() {
        return register;
    }

    public Settings getSettings() {
        return settings;
    }

    public Coop getStartCoop() {
        return startCoop;
    }
}