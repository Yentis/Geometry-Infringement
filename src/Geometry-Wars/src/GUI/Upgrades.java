package GUI;

import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import GComponents.*;
import Game.Sound;
import Game.Upgrade;
import Game.Speler;

/**
 * @author Renzie
 */
class Upgrades extends GPanel {
    //region Instance Variables

    private Upgrades panel = this;

    //endregion

    //region Behaviour

    @Override
    public void initComponents() throws IOException, FontFormatException {
        panel.removeAll();

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(panel.getParent());
        List<Integer> upgradeList = new ArrayList<>();
        Speler speler = window.getSpel().getSpeler();

        try {
            upgradeList = window.getSpel().checkUpgrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Make components
        //==================================================

        //JButton skins = new GButton("Skins", 18f, 205,690,133,35);
        //JButton techTree = new GButton("Tech Tree", 18f, 365,690,287,35);
        //JButton goldenNuggets = new GButton("Buy Golden Nuggets", 18f, 681,690,287,35);
        JButton back = new GButton("Back", 18f, 52,690,133,35);
        JLabel lblNuggets = new GLabel("Nuggets: ", 18f, 52,60,150,35, false, Color.white);
        JLabel spaceShipPane = new GPane(52,138,287,535);
        JLabel dronePane = new GPane(365,138,287,534);
        JLabel firePane = new GPane(681,138,287,534);
        JLabel spaceShip = new GLabel("Spaceship", 16f, 151,149,100,28, false, Color.black );
        JLabel drone = new GLabel("Drone", 16f, 482,149,87,28, false, Color.black );
        JLabel fire = new GLabel("Bullets", 16f, 807,149,87,28, false, Color.black );
        JLabel upgradesPane = new GPane(365,10,287,98);
        JLabel upgrades = new GLabel("Upgrades", 36f, 400,22,218,74, false, Color.black);
        JLabel nuggets = new GLabel("" + speler.getNuggets(), 18f, 200,60,150,35, false, Color.white);
        JLabel message = new GLabel("", 24f, 365, 82, 330, 74, false, Color.white);
        JButton upgradeShip1Info = new GButton("", 16f, 179,193,16,21);
        JButton upgradeShip1 = new GButton("", 16f,75,214,104,123);
        JLabel upgradeShip1Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(1).getKost()), 16f, 85,143,104,123, false, Color.black);
        JButton upgradeShip2Info = new GButton("", 16f, 315,193,16,21);
        JButton upgradeShip2 = new GButton("", 16f,211,214,104,123);
        JLabel upgradeShip2Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(2).getKost()), 16f, 226,143,104,123, false, Color.black);
        //JButton upgradeShip3 = new GButton("",16f,75,368,104,123);
        JButton upgradeDrone1 = new GButton("", 16f, 392,214,104,123);
        JButton upgradeDrone1Info = new GButton("", 16f, 496,193,16,21);
        JLabel upgradeDrone1Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(3).getKost()), 16f, 407,143,104,123, false, Color.black);
        JButton upgradeDrone2 = new GButton("",16f, 528,214,104,123);
        JButton upgradeDrone2Info = new GButton("", 16f, 632,193,16,21);
        JLabel upgradeDrone2Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(4).getKost()), 16f, 543,143,104,123, false, Color.black);
        JButton upgradeDrone3 = new GButton("",16f, 392,368,104,123);
        JButton upgradeDrone3Info = new GButton("", 16f, 496,346,16,21);
        JLabel upgradeDrone3Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(5).getKost()), 16f, 407,293,104,123, false, Color.black);
        JButton upgradeFire1 = new GButton("", 16f, 704,218,104,123);
        JButton upgradeFire1Info = new GButton("", 16f, 808,193,16,21);
        JLabel upgradeFire1Price = new GLabel(String.valueOf(window.getSpel().getUpgrades().get(0).getKost()), 16f, 715,143,104,123, false, Color.black);
        //JButton upgradeFire2 = new GButton("", 16f, 841,218,104,123);
        //JButton upgradeFire3 = new GButton("", 16f, 704,368,104,123);

        upgradeShip1.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(1).getFoto()));
        upgradeShip1Info.setIcon(new ImageIcon("resources\\Media\\info.png"));
        upgradeShip1Info.setOpaque(false);
        upgradeShip1Info.setBorderPainted(false);
        upgradeShip2Info.setIcon(new ImageIcon("resources\\Media\\info.png"));
        upgradeShip2Info.setOpaque(false);
        upgradeShip2Info.setBorderPainted(false);
        upgradeDrone1Info.setIcon(new ImageIcon("resources\\Media\\info.png"));
        upgradeDrone1Info.setOpaque(false);
        upgradeDrone1Info.setBorderPainted(false);
        upgradeDrone2Info.setIcon(new ImageIcon("resources\\Media\\info.png"));
        upgradeDrone2Info.setOpaque(false);
        upgradeDrone2Info.setBorderPainted(false);
        upgradeDrone3Info.setIcon(new ImageIcon("resources\\Media\\info.png"));
        upgradeDrone3Info.setOpaque(false);
        upgradeDrone3Info.setBorderPainted(false);
        upgradeFire1Info.setIcon(new ImageIcon("resources\\Media\\info.png"));
        upgradeFire1Info.setOpaque(false);
        upgradeFire1Info.setBorderPainted(false);
        upgradeShip2.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(2).getFoto()));
        upgradeDrone1.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(3).getFoto()));
        upgradeDrone2.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(4).getFoto()));
        upgradeDrone3.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(5).getFoto()));
        upgradeFire1.setIcon(new ImageIcon(window.getSpel().getUpgrades().get(0).getFoto()));

        for (int i:upgradeList) {
            switch (i){
                case 1:
                    upgradeFire1.setEnabled(false);
                    break;
                case 2:
                    upgradeShip1.setEnabled(false);
                    break;
                case 3:
                    upgradeShip2.setEnabled(false);
                    break;
                case 4:
                    upgradeDrone1.setEnabled(false);
                    break;
                case 5:
                    upgradeDrone2.setEnabled(false);
                    break;
                case 6:
                    upgradeDrone3.setEnabled(false);
                    break;
            }
        }

        //region Matthias
        /*
            x for every column of upgrades
            1: 75x
            2: 211x
            3: 392x
            4: 528x
            5: 704x
            6: 841x

            y for every row of upgrades
            1: 218
            2: 358
            3: 504

            width and height for buttons of upgrades
            width: 104
            height: 123

         */
        //endregion

        //Add Action Listener
        //==================================================

        back.addActionListener(evt -> {
            new Sound("click");
            panel.setVisible(false);
            window.getMainMenu().setVisible(true);
        });

        upgradeFire1.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(0);

            buyUpgrade(window, upgrade, message, upgradeFire1, nuggets);
        });

        upgradeFire1Info.addActionListener(evt -> {
            Upgrade upgrade = window.getSpel().getUpgrades().get(0);

            message.setText(upgrade.getBeschrijving());
        });

        upgradeShip1.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(1);

            buyUpgrade(window, upgrade, message, upgradeShip1, nuggets);
        });

        upgradeShip1Info.addActionListener(evt -> {
            Upgrade upgrade = window.getSpel().getUpgrades().get(1);

            message.setText(upgrade.getBeschrijving());
        });

        upgradeShip2.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(2);

            buyUpgrade(window, upgrade, message, upgradeShip2, nuggets);
        });

        upgradeShip2Info.addActionListener(evt -> {
            Upgrade upgrade = window.getSpel().getUpgrades().get(2);

            message.setText(upgrade.getBeschrijving());
        });

        upgradeDrone1.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(3);

            buyUpgrade(window, upgrade, message, upgradeDrone1, nuggets);
        });

        upgradeDrone1Info.addActionListener(evt -> {
            Upgrade upgrade = window.getSpel().getUpgrades().get(3);

            message.setText(upgrade.getBeschrijving());
        });

        upgradeDrone2.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(4);

            buyUpgrade(window, upgrade, message, upgradeDrone2, nuggets);
        });

        upgradeDrone2Info.addActionListener(evt -> {
            Upgrade upgrade = window.getSpel().getUpgrades().get(4);

            message.setText(upgrade.getBeschrijving());
        });

        upgradeDrone3.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(5);

            buyUpgrade(window, upgrade, message, upgradeDrone3, nuggets);
        });

        upgradeDrone3Info.addActionListener(evt -> {
            Upgrade upgrade = window.getSpel().getUpgrades().get(5);

            message.setText(upgrade.getBeschrijving());
        });
        //Add Components
        //==================================================
        panel.add(back);
        panel.add(nuggets);
        panel.add(lblNuggets);
        panel.add(upgrades);
        panel.add(upgradesPane);
        //panel.add(skins);
        //panel.add(techTree);
        //panel.add(goldenNuggets);
        panel.add(spaceShip);
        panel.add(drone);
        panel.add(fire);
        panel.add(upgradeDrone1);
        panel.add(upgradeDrone1Price);
        panel.add(upgradeDrone1Info);
        panel.add(upgradeDrone2);
        panel.add(upgradeDrone2Price);
        panel.add(upgradeDrone2Info);
        panel.add(upgradeDrone3);
        panel.add(upgradeDrone3Price);
        panel.add(upgradeDrone3Info);
        panel.add(upgradeShip1);
        panel.add(upgradeShip1Price);
        panel.add(upgradeShip1Info);
        panel.add(upgradeShip2);
        panel.add(upgradeShip2Price);
        panel.add(upgradeShip2Info);
        //panel.add(upgradeShip3);
        panel.add(upgradeFire1);
        panel.add(upgradeFire1Price);
        panel.add(upgradeFire1Info);
        //panel.add(upgradeFire2);
        //panel.add(upgradeFire3);
        panel.add(spaceShipPane);
        panel.add(dronePane);
        panel.add(firePane);
        panel.add(message);
    }

    private void buyUpgrade(GUI.Window window, Upgrade upgrade, JLabel message, JButton button, JLabel nuggets){
        try {
            if(window.getSpel().buyUpgrade(upgrade.getKost(), (upgrade.getNr() + 1))){
                button.setEnabled(false);
                message.setText("Upgrade purchased.");
            } else {
                message.setText("Not enough nuggets.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            window.getSpel().initDankabank();
            window.getSpel().logIn(window.getSpel().getSpeler().getGebruikersnaam());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nuggets.setText(String.valueOf(window.getSpel().getSpeler().getNuggets()));
    }

    //endregion
}