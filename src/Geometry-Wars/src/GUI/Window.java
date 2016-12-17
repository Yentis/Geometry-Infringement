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
public class Window extends JFrame implements ActionListener{

    // Panels
    // TODO fill in other panels , add getters too
    private MainMenu mainMenu = new MainMenu();
    private StartGame startGame = new StartGame();
    private Profile profile = new Profile();
    private StartGameCampaign startGameCampaign = new StartGameCampaign();
    private Logout logout = new Logout();
    private Login login = new Login();
    private InGameSinglePlayer inGameSinglePlayer = new InGameSinglePlayer();
    private Scoreboard scoreboard = new Scoreboard();
//    private GamePanel gamePanel = new GamePanel();



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







    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        System.out.println(source);
    }
}
