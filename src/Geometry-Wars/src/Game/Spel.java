package Game;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Spel implements Cloneable{
    //region Instance Variables

    private Speler speler;
    private List<Speler> spelers = new ArrayList<>();
    private List<Schip> schepen = new ArrayList<>();
    private List<Drone> drones = new ArrayList<>();
    private List<Upgrade> upgrades = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private String currentDifficulty = "Normal";
    private String currentControls = "Keyboard + Mouse";
    private String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7150029";
    private String user = "sql7150029";
    private String pass = "3Ngdr6LYhR";
    private Connection myConn = null;

    //endregion

    //region Constructors

    public Spel() throws SQLException {
        readDatabase();
        initDankabank();
    }

    //endregion

    //region Getters & Setters

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public Speler getSpeler() {
        return speler;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Schip> getSchepen() {
        return schepen;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public String getCurrentControls() {
        return currentControls;
    }

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public List<Speler> getSpelers() {
        return spelers;
    }

    public void setCurrentControls(String currentControls) {
        this.currentControls = currentControls;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setCurrentDifficulty(String currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }

    //endregion

    //region Behaviour

    private void readDatabase() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        myConn = DriverManager.getConnection(url, user, pass);
    }

    public List<Integer> checkUpgrades() throws SQLException {
        List<Integer> upgradelist = new ArrayList<>();
        Statement myStmt = myConn.createStatement();

        ResultSet upgrades = myStmt.executeQuery("select * from spelerupgrades where pid = (select nr from speler where nr = " + (speler.getNr() + 1) + ")");

        int i = 0;
        while(upgrades.next()){
            upgradelist.add(i, upgrades.getInt("uid"));
            i++;
        }

        return upgradelist;
    }

    public boolean buyUpgrade(int cost, int uid) throws SQLException {
        Statement myStmt = myConn.createStatement();
        Statement myStmt2 = myConn.createStatement();
        Statement myStmt3 = myConn.createStatement();

        ResultSet upgrades = myStmt.executeQuery("select nuggets from speler where gebruikersnaam = '" + speler.getGebruikersnaam() + "'");

        if(upgrades.next()){
            if(upgrades.getInt("nuggets") >= cost){
                int a = myStmt2.executeUpdate("UPDATE speler SET nuggets = nuggets - " + cost + " WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
                int b = myStmt3.executeUpdate("INSERT INTO spelerupgrades (pid, uid) VALUES (" + (speler.getNr() + 1) + ", " + uid + ")");
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public void initDankabank() throws SQLException {
        Statement myStmt = myConn.createStatement();

        spelers = new ArrayList<>();
        schepen = new ArrayList<>();
        drones = new ArrayList<>();
        upgrades = new ArrayList<>();
        enemies = new ArrayList<>();

        //region Spelers
        ResultSet speler = myStmt.executeQuery("select * from speler order by highscore desc");

        int i = 0;
        while (speler.next()){
            spelers.add(i, new Speler(speler.getInt("nr") - 1, speler.getString("gebruikersnaam"), speler.getInt("level"), speler.getInt("experience"), speler.getString("rank"), speler.getInt("nuggets"), speler.getInt("golden nuggets"), speler.getInt("highscore")));
            i++;
        }
        //endregion

        //region Schepen
        ResultSet schip = myStmt.executeQuery("select * from schip");

        i = 0;
        while (schip.next()){
            schepen.add(i, new Schip(schip.getInt("nr") - 1, schip.getInt("hp"), schip.getInt("kracht"), schip.getString("image"), 81, 68, 90, 83, schip.getInt("speed"), null));
            i++;
        }
        //endregion

        //region Drones
        ResultSet drone = myStmt.executeQuery("select * from drone");

        i = 0;
        while (drone.next()){
            drones.add(i, new Drone(drone.getInt("nr") - 1, drone.getString("naam"), drone.getString("beschrijving"), drone.getInt("kracht"), drone.getString("uiterlijk"), drone.getInt("type")));
            i++;
        }
        //endregion

        //region Upgrades
        ResultSet upgrade = myStmt.executeQuery("select * from upgrade");

        i = 0;
        while (upgrade.next()){
            upgrades.add(i, new Upgrade(upgrade.getInt("nr") - 1, upgrade.getString("naam"), upgrade.getString("foto"), upgrade.getInt("kost")));
            i++;
        }
        //endregion

        //region Enemies
        ResultSet enemy = myStmt.executeQuery("select * from vijand");

        i = 0;
        while (enemy.next()){
            enemies.add(i, new Enemy(enemy.getInt("nr") - 1, enemy.getString("naam"), enemy.getString("beschrijving"), enemy.getInt("hp"), enemy.getInt("kracht"), enemy.getString("uiterlijk"), enemy.getInt("experience"), enemy.getInt("score"), enemy.getInt("snelheid")));
            i++;
        }
        //endregion
    }

    public void submitScore(int score) throws SQLException {
        int nuggets = score / 10000;
        Statement myStmt = myConn.createStatement();
        Statement myStmt2 = myConn.createStatement();

        ResultSet highscore = myStmt.executeQuery("select * from speler where gebruikersnaam = '" + speler.getGebruikersnaam() + "'");

        while(highscore.next()){
            if(highscore.getInt("highscore") < score){
                int a = myStmt2.executeUpdate("UPDATE speler SET highscore = " + score + ", nuggets = nuggets + " + nuggets + " WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
            } else {
                int a = myStmt2.executeUpdate("UPDATE speler SET nuggets = nuggets + " + nuggets + " WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
            }
        }
    }

    public void logIn(String gebruikersnaam) throws SQLException {
        Statement myStmt = myConn.createStatement();

        ResultSet myRs = myStmt.executeQuery("select * from speler where gebruikersnaam = '" + gebruikersnaam + "'");

        speler = null;

        while(myRs.next()){
            speler = new Speler(myRs.getInt("nr") - 1, myRs.getString("gebruikersnaam"), myRs.getInt("level"), myRs.getInt("experience"), myRs.getString("rank"), myRs.getInt("nuggets"), myRs.getInt("golden nuggets"), myRs.getInt("highscore"));
        }
    }

    public void logOut(){
        speler = null;
    }

    public void registerPlayer(String gebruikersnaam, String wachtwoord, String email) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Statement myStmt = myConn.createStatement();

        int a = myStmt.executeUpdate("insert into speler (gebruikersnaam, wachtwoord, email, rank, highscore)" +
                "values('" + gebruikersnaam +"', '" + wachtwoord + "', '" + email + "', 'Bronze 1', 0)");
    }

    public String loginChecker(String gebruikersnaam, String wachtwoord) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Statement myStmt = myConn.createStatement();

        ResultSet myRs = myStmt.executeQuery("select count(*) from speler where gebruikersnaam = '" + gebruikersnaam + "' AND wachtwoord = '" + wachtwoord + "'");

        while(myRs.next()){
            if(myRs.getInt("count(*)") == 0){
                return "Username or password";
            }
        }
        return "";
    }

    public String infoChecker(String gebruikersnaam, String email) throws SQLException {
        Statement myStmt = myConn.createStatement();

        //Gebruikersnaam
        ResultSet myRs = myStmt.executeQuery("select count(*) from speler where gebruikersnaam = '" + gebruikersnaam + "'");

        while(myRs.next()){
            if(myRs.getInt("count(*)") > 0){
                return "Username";
            }
        }

        //Email
        ResultSet myRs2 = myStmt.executeQuery("select count(*) from speler where email = '" + email + "'");

        while(myRs2.next()){
            if(myRs2.getInt("count(*)") > 0){
                return "Email";
            }
        }
        return "";
    }

    public void checkRank() throws SQLException {
        Statement myStmt = myConn.createStatement();
        Statement myStmt2 = myConn.createStatement();
        List<Integer> scores = new ArrayList<>();
        double amountofplayers = 0;

        ResultSet ranking = myStmt.executeQuery("select * from speler ");

        while(ranking.next()){
            scores.add(ranking.getInt("highscore"));
        }

        Collections.sort(scores);
        Collections.reverse(scores);

        amountofplayers = scores.size();

        double diamond = (amountofplayers / 10) * 1; //top 10%
        double platinum = (amountofplayers / 10) * 3; //top 30%
        double gold = (amountofplayers / 10) * 5; //top 50%
        double silver = (amountofplayers / 10) * 7; //top 70%
        double bronze = (amountofplayers / 10) * 9; //top 90%

        if (speler.getHighscore() >= scores.get((int)diamond)){
            myStmt2.executeUpdate("UPDATE speler SET rank = 'Diamond 1' WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
        } else if (speler.getHighscore() >= scores.get((int)platinum)){
            myStmt2.executeUpdate("UPDATE speler SET rank = 'Platinum 1' WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
        } else if (speler.getHighscore() >= scores.get((int)gold)){
            myStmt2.executeUpdate("UPDATE speler SET rank = 'Gold 1' WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
        } else if (speler.getHighscore() >= scores.get((int)silver)){
            myStmt2.executeUpdate("UPDATE speler SET rank = 'Silver 1' WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
        } else if (speler.getHighscore() >= scores.get((int)bronze)){
            myStmt2.executeUpdate("UPDATE speler SET rank = 'Bronze 1' WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
        } else {
            myStmt2.executeUpdate("UPDATE speler SET rank = 'Unranked' WHERE gebruikersnaam = '" + speler.getGebruikersnaam() + "'");
        }

        /*for (int i:scores)
        {
            if(scores.get((int)diamond) >= i){

            }
        }*/


        /*while(ranking.next()){
            if(ranking.getInt("highscore") >= average * 1.8){
                //diamond
            } else if (ranking.getInt("highscore") >= average * 1.4){
                //platinum
            } else if (ranking.getInt("highscore") >= average){
                //gold
            } else if (ranking.getInt("highscore") >= average * 0.6){
                //silver
            } else if (ranking.getInt("highscore") >= average * 0.2){
                //bronze
            } else {
                //unranked
            }
        }*/
    }

    //endregion
}
