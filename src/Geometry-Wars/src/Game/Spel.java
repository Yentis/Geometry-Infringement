package Game;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Spel {
    //region Instance Variables

    private Speler speler;
    private List<Schip> schepen = new ArrayList<>();
    private List<Drone> drones = new ArrayList<>();
    private List<Upgrade> upgrades = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private String currentDifficulty = "Normal";
    private String url = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7150029";
    private String user = "sql7150029";
    private String pass = "3Ngdr6LYhR";

    //endregion

    //region Properties


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

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void changeSchipspeed(double multiplier){
        for (Schip schip:schepen) {
            schip.setSpeed(schip.getSpeed() * multiplier);
        }
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setCurrentDifficulty(String currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }

    //endregion

    //region Behaviour

    public void initDankabank() throws SQLException{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection(url, user, pass);
        Statement myStmt = myConn.createStatement();

        //region Schepen
        ResultSet schip = myStmt.executeQuery("select * from schip");

        int i = 0;
        while (schip.next()){
            schepen.add(i, new Schip(schip.getInt("nr") - 1, schip.getInt("hp"), schip.getInt("kracht"), schip.getString("image"), 37, 39, 38, 40, schip.getInt("speed")));
            i++;
        }
        //endregion

        //region Drones
        ResultSet drone = myStmt.executeQuery("select * from drone");

        i = 0;
        while (drone.next()){
            drones.add(i, new Drone(drone.getInt("nr") - 1, drone.getString("naam"), drone.getString("beschrijving"), drone.getInt("hp"), drone.getInt("kracht"), drone.getString("uiterlijk")));
            i++;
        }
        //endregion

        //region Upgrades
        ResultSet upgrade = myStmt.executeQuery("select * from upgrade");

        i = 0;
        while (upgrade.next()){
            upgrades.add(i, new Upgrade(upgrade.getInt("nr") - 1, upgrade.getString("naam"), upgrade.getString("beschrijving"), upgrade.getInt("level"), upgrade.getString("foto"), upgrade.getInt("kost")));
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

    public void logIn(String gebruikersnaam) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection(url, user, pass);
        Statement myStmt = myConn.createStatement();

        ResultSet myRs = myStmt.executeQuery("select * from speler where gebruikersnaam = '" + gebruikersnaam + "'");

        while(myRs.next()){
            speler = new Speler(myRs.getInt("nr") - 1, myRs.getString("gebruikersnaam"), myRs.getString("wachtwoord"), myRs.getString("email"), myRs.getInt("level"), myRs.getInt("experience"), myRs.getString("rank"), myRs.getInt("nuggets"), myRs.getInt("golden nuggets"));
        }
    }

    public void logOut(){
        speler = null;
    }

    public void registerPlayer(String gebruikersnaam, String wachtwoord, String email) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection(url, user, pass);
        Statement myStmt = myConn.createStatement();

        int a = myStmt.executeUpdate("insert into speler (gebruikersnaam, wachtwoord, email, rank)" +
                "values('" + gebruikersnaam +"', '" + wachtwoord + "', '" + email + "', 0)");
    }

    public String loginChecker(String gebruikersnaam, String wachtwoord) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection(url, user, pass);
        Statement myStmt = myConn.createStatement();

        System.out.println("Password: " + wachtwoord);

        ResultSet myRs = myStmt.executeQuery("select count(*) from speler where gebruikersnaam = '" + gebruikersnaam + "' AND wachtwoord = '" + wachtwoord + "'");

        while(myRs.next()){
            if(myRs.getInt("count(*)") == 0){
                return "Username or password";
            }
        }
        return "";
    }

    public String infoChecker(String gebruikersnaam, String email) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection(url, user, pass);
        Statement myStmt = myConn.createStatement();

        //Gebruikersnaam
        ResultSet myRs = myStmt.executeQuery("select count(*) from speler where gebruikersnaam = '" + gebruikersnaam + "'");

        while(myRs.next()){
            if(myRs.getInt("count(*)") > 0){
                return "Gebruikersnaam";
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

    //endregion
}
