package GUI;

import GComponents.GButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private InGameSinglePlayer inGameSinglePlayer;
    private Scoreboard scoreboard;
    private Pause pause;



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

    public static void main(String args[]) throws IOException, FontFormatException {
        new Window("Geometry Wars");
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
        inGameSinglePlayer = new InGameSinglePlayer();
        scoreboard = new Scoreboard();

        // Add UI panels
        cp.add(mainMenu);
        cp.add(startGame);
        cp.add(profile);
        cp.add(startGameCampaign);
        cp.add(logout);
        cp.add(login);
        cp.add(inGameSinglePlayer);
        cp.add(scoreboard);


        // Set all panels invisible except the starting panel
        startGame.setVisible(false);
        profile.setVisible(false);
        startGameCampaign.setVisible(false);
        logout.setVisible(false);
        login.setVisible(false);
        inGameSinglePlayer.setVisible(false);
        scoreboard.setVisible(false);
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

    public InGameSinglePlayer getInGameSinglePlayer() {return inGameSinglePlayer;}

    public Scoreboard getScoreboard() {return scoreboard;}

    public Pause getPause() {return pause;}


}
