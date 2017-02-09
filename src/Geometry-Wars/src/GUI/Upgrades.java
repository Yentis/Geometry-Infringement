package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GComponents.*;
import Game.Sound;
import Game.Upgrade;
import Game.Speler;

/**
 * @author Renzie
 */
class Upgrades extends JPanel {
    //region Behaviour

    public void initComponents() throws IOException, FontFormatException {
        removeAll();
        setLayout(new GridBagLayout());
        setOpaque(false);

        GUI.Window window = (GUI.Window) SwingUtilities.getRoot(getParent());
        List<Integer> upgradeList = new ArrayList<>();
        Speler speler = window.getSpel().getSpeler();

        try {
            upgradeList = window.getSpel().checkUpgrades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Make components
        //==================================================

        List<JButton> upgradeComponents = new ArrayList<>();
        JButton back = new GButton("Back");
        JButton Exit = new GButton("Quit");
        JPanel middlePanel = new JPanel(new GridBagLayout());
        JPanel shipPanel = new JPanel(new GridBagLayout());
        JPanel dronePanel = new JPanel(new GridBagLayout());
        JPanel firePanel = new JPanel(new GridBagLayout());
        JLabel lblTitle = new GLabel("Upgrades", true, Color.black);
        JLabel lblNuggets = new GLabel("Nuggets: ", false, Color.white);
        JLabel spaceShipPane = new GPane();
        JLabel dronePane = new GPane();
        JLabel firePane = new GPane();
        JLabel spaceShip = new GLabel("Spaceship", false, Color.black );
        JLabel drone = new GLabel("Drone", false, Color.black );
        JLabel fire = new GLabel("Bullets", false, Color.black );
        JLabel nuggets = new GLabel("" + speler.getNuggets(), false, Color.white);
        JLabel message = new GLabel(" ", false, Color.white);
        JLabel upgradeFire1Info = new GLabel("?", false, Color.black);
        JButton upgradeFire1 = new GButton("");
        JLabel upgradeFire1Price = new GLabel("" + window.getSpel().getUpgrades().get(0).getKost(), false, Color.black);
        JLabel upgradeShip1Info = new GLabel("?", false, Color.black);
        JButton upgradeShip1 = new GButton("");
        JLabel upgradeShip1Price = new GLabel("" + window.getSpel().getUpgrades().get(1).getKost(), false, Color.black);
        JLabel upgradeShip2Info = new GLabel("?", false, Color.black);
        JButton upgradeShip2 = new GButton("");
        JLabel upgradeShip2Price = new GLabel("" + window.getSpel().getUpgrades().get(2).getKost(), false, Color.black);
        JLabel upgradeDrone1Info = new GLabel("?", false, Color.black);
        JButton upgradeDrone1 = new GButton("");
        JLabel upgradeDrone1Price = new GLabel("" + window.getSpel().getUpgrades().get(3).getKost(), false, Color.black);
        JLabel upgradeDrone2Info = new GLabel("?", false, Color.black);
        JButton upgradeDrone2 = new GButton("");
        JLabel upgradeDrone2Price = new GLabel("" + window.getSpel().getUpgrades().get(4).getKost(), false, Color.black);
        JLabel upgradeDrone3Info = new GLabel("?", false, Color.black);
        JButton upgradeDrone3 = new GButton("");
        JLabel upgradeDrone3Price = new GLabel("" + window.getSpel().getUpgrades().get(5).getKost(), false, Color.black);

        upgradeDrone3Price.setFont(new GFont(16));
        upgradeDrone3Info.setFont(new GFont(16));
        upgradeDrone2Price.setFont(new GFont(16));
        upgradeDrone2Info.setFont(new GFont(16));
        upgradeDrone1Price.setFont(new GFont(16));
        upgradeDrone1Info.setFont(new GFont(16));
        upgradeShip2Price.setFont(new GFont(16));
        upgradeFire1Price.setFont(new GFont(16));
        upgradeShip1Info.setFont(new GFont(16));
        upgradeShip1Price.setFont(new GFont(16));
        upgradeShip2Info.setFont(new GFont(16));
        lblTitle.setFont(new GFont(36));
        lblNuggets.setFont(new GFont(18));
        spaceShip.setFont(new GFont(16));
        drone.setFont(new GFont(16));
        fire.setFont(new GFont(16));
        nuggets.setFont(new GFont(18));
        upgradeFire1Info.setFont(new GFont(16));
        upgradeComponents.add(upgradeFire1);
        upgradeComponents.add(upgradeShip1);
        upgradeComponents.add(upgradeShip2);
        upgradeComponents.add(upgradeDrone1);
        upgradeComponents.add(upgradeDrone2);
        upgradeComponents.add(upgradeDrone3);

        int j = 0;
        for (JButton b:upgradeComponents) {
            b.setFont(new GFont(16));
            b.setIcon(new ImageIcon(new ImageIcon(window.getSpel().getUpgrades().get(j).getFoto()).getImage()));
            b.setPreferredSize(new Dimension(104, 123));
            j++;
        }
        shipPanel.setOpaque(false);
        middlePanel.setOpaque(false);

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
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 40;
        c.ipady = 20;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridwidth = 4;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        add(lblTitle, c);

        c.ipadx = 0;
        c.ipady = 0;
        c.insets = new Insets(90, 0, 0, 0);
        add(message, c);

        c.insets = new Insets(20, 0, 0, 700);
        add(lblNuggets, c);
        c.insets = new Insets(20, 0, 0, 450);
        add(nuggets, c);

        //Ship
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        shipPanel.add(spaceShip, c);

        //Upgrade1
        c.insets = new Insets(60, 30, 0, 30);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        shipPanel.add(upgradeShip1, c);

        c.insets = new Insets(40, 30, 0, 0);
        shipPanel.add(upgradeShip1Price, c);

        c.insets = new Insets(40, 120, 0, 0);
        shipPanel.add(upgradeShip1Info, c);

        //Upgrade2
        c.insets = new Insets(60, 30, 0, 30);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        shipPanel.add(upgradeShip2, c);

        c.insets = new Insets(40, 0, 0, 75);
        shipPanel.add(upgradeShip2Price, c);

        c.insets = new Insets(40, 0, 0, 30);
        shipPanel.add(upgradeShip2Info, c);

        //Panel
        c.insets = new Insets(0, 0, 0, 0);
        spaceShipPane.setBorder(new EmptyBorder(200, 150, 200, 150));
        shipPanel.add(spaceShipPane, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 700, 0, 0);
        middlePanel.add(shipPanel, c);

        //Drone
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        dronePanel.add(drone, c);

        //Upgrade1
        c.insets = new Insets(60, 30, 0, 30);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        dronePanel.add(upgradeDrone1, c);

        c.insets = new Insets(40, 30, 0, 0);
        dronePanel.add(upgradeDrone1Price, c);

        c.insets = new Insets(40, 120, 0, 0);
        dronePanel.add(upgradeDrone1Info, c);

        //Upgrade2
        c.insets = new Insets(60, 30, 0, 30);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        dronePanel.add(upgradeDrone2, c);

        c.insets = new Insets(40, 0, 0, 90);
        dronePanel.add(upgradeDrone2Price, c);

        c.insets = new Insets(40, 0, 0, 30);
        dronePanel.add(upgradeDrone2Info, c);

        //Upgrade3
        c.insets = new Insets(140, 30, 0, 30);
        c.anchor = GridBagConstraints.LINE_START;
        dronePanel.add(upgradeDrone3, c);

        c.insets = new Insets(0, 30, 0, 0);
        dronePanel.add(upgradeDrone3Price, c);

        c.insets = new Insets(0, 120, 0, 0);
        dronePanel.add(upgradeDrone3Info, c);

        c.ipady = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        dronePane.setBorder(new EmptyBorder(200, 150, 200, 150));
        dronePanel.add(dronePane, c);

        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        middlePanel.add(dronePanel, c);

        //Bullets
        c.gridx = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.PAGE_START;
        firePanel.add(fire, c);

        //Upgrade1
        c.insets = new Insets(60, 30, 0, 30);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        firePanel.add(upgradeFire1, c);

        c.insets = new Insets(40, 30, 0, 0);
        firePanel.add(upgradeFire1Price, c);

        c.insets = new Insets(40, 120, 0, 0);
        firePanel.add(upgradeFire1Info, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        firePane.setBorder(new EmptyBorder(200, 150, 200, 150));
        firePanel.add(firePane, c);

        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(0, 0, 0, 700);
        middlePanel.add(firePanel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        add(middlePanel, c);

        c.gridx = 2;
        c.gridy = 2;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 140);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(back, c);

        c.insets = new Insets(0, 0, 20, 20);
        add(Exit, c);

        //Add Action Listener
        //==================================================

        back.addActionListener(evt -> {
            new Sound("click");
            window.getCl().show(window.getCards(), "mainmenupanel");
        });

        Exit.addActionListener(evt -> {
            new Sound("click");
            System.exit(0);
        });

        upgradeFire1.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(0);

            buyUpgrade(window, upgrade, message, upgradeFire1, nuggets);
        });

        upgradeFire1Info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Upgrade upgrade = window.getSpel().getUpgrades().get(0);
                message.setText(upgrade.getBeschrijving());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                message.setText(" ");
            }
        });

        upgradeShip1.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(1);

            buyUpgrade(window, upgrade, message, upgradeShip1, nuggets);
        });

        upgradeShip1Info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Upgrade upgrade = window.getSpel().getUpgrades().get(1);
                message.setText(upgrade.getBeschrijving());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                message.setText(" ");
            }
        });

        upgradeShip2.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(2);

            buyUpgrade(window, upgrade, message, upgradeShip2, nuggets);
        });

        upgradeShip2Info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Upgrade upgrade = window.getSpel().getUpgrades().get(2);
                message.setText(upgrade.getBeschrijving());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                message.setText(" ");
            }
        });

        upgradeDrone1.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(3);

            buyUpgrade(window, upgrade, message, upgradeDrone1, nuggets);
        });

        upgradeDrone1Info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Upgrade upgrade = window.getSpel().getUpgrades().get(3);
                message.setText(upgrade.getBeschrijving());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                message.setText(" ");
            }
        });

        upgradeDrone2.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(4);

            buyUpgrade(window, upgrade, message, upgradeDrone2, nuggets);
        });

        upgradeDrone2Info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Upgrade upgrade = window.getSpel().getUpgrades().get(4);
                message.setText(upgrade.getBeschrijving());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                message.setText(" ");
            }
        });

        upgradeDrone3.addActionListener(evt -> {
            new Sound("click");
            Upgrade upgrade = window.getSpel().getUpgrades().get(5);

            buyUpgrade(window, upgrade, message, upgradeDrone3, nuggets);
        });

        upgradeDrone3Info.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Upgrade upgrade = window.getSpel().getUpgrades().get(5);
                message.setText(upgrade.getBeschrijving());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                message.setText(" ");
            }
        });
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