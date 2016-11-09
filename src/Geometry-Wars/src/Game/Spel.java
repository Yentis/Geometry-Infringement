package Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yentl-PC on 8/11/2016.
 */
public class Spel {
    //region Instance Variables

    private List<Speler> spelers = new ArrayList<>();
    private List<Schip> schepen = new ArrayList<>();
    private List<Drone> drones = new ArrayList<>();
    private List<Upgrade> upgrades = new ArrayList<>();
    private List<Vijand> vijanden = new ArrayList<>();

    //endregion

    //region Properties
    //endregion

    //region Behaviour

    public void initDankabank() throws SQLException{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/geometry-infringement", "root", "");
        Statement myStmt = myConn.createStatement();

        //region Spelers
        ResultSet myRs = myStmt.executeQuery("select * from speler");

        int i = 0;
        while (myRs.next()){
            spelers.add(i, new Speler(myRs.getInt("nr") - 1, myRs.getString("gebruikersnaam"), myRs.getString("wachtwoord"), myRs.getString("email"), myRs.getInt("level"), myRs.getInt("experience"), myRs.getString("profielfoto"), myRs.getInt("rank"), myRs.getInt("nuggets"), myRs.getInt("golden nuggets")));
            i++;
        }
        //endregion

        //region Schepen
        ResultSet schip = myStmt.executeQuery("select * from schip");

        i = 0;
        while (schip.next()){
            schepen.add(i, new Schip(schip.getInt("nr") - 1, schip.getInt("hp"), schip.getInt("kracht")));
            i++;
        }
        //endregion

        //region Drones
        ResultSet drone = myStmt.executeQuery("select * from drone");

        i = 0;
        while (drone.next()){
            drones.add(i, new Drone(drone.getInt("nr") - 1, drone.getString("naam"), drone.getString("beschrijving"), drone.getInt("hp"), drone.getInt("kracht"), drone.getString("uiterlijk"), drone.getInt("level"), drone.getInt("experience")));
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

        //region Vijanden
        ResultSet vijand = myStmt.executeQuery("select * from vijand");

        i = 0;
        while (vijand.next()){
            vijanden.add(i, new Vijand(vijand.getInt("nr") - 1, vijand.getString("naam"), vijand.getString("beschrijving"), vijand.getInt("hp"), vijand.getInt("kracht"), vijand.getString("uiterlijk"), vijand.getInt("experience"), vijand.getInt("score")));
            i++;
        }
        //endregion
    }

    //endregion
}
