package JOC;

import charachters.Character;
import charachters.Skeleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class movementPlayer extends KeyAdapter {

    private JLabel player;
    private Character playerCharacter;
    private Set<Integer> pressedKeys = new HashSet<>();
    private ArrayList<Rectangle> keyStates;
    private JPanel panel;
    private int contadorHab2;
    private int contadorHab1;
    private int contadorHab3;
    private int contadorHab4;
    private int contadorHab5;
    private ArrayList<JLabel> coinsList = new ArrayList<>();
    private int contadorCoins;
    private JLabel object;
    private JPanel inventoryPanel;
    private JLabel counterLabel;
    private ArrayList<JLabel>lives;
    private int counterInt=0;
    private JLabel portal;
    private JLabel objectShowInventory;
    private Usuario usuario;

    public movementPlayer(JLabel player, Character playerCharacter, ArrayList<Rectangle> keyStates, JPanel panel,JLabel object,JPanel inventoryPanel,JLabel counterLabel,ArrayList<JLabel>lives,JLabel objectShowInventory,Usuario usuario) {
        this.player = player;
        this.playerCharacter = playerCharacter;
        this.keyStates = keyStates;
        this.panel = panel;
        this.contadorHab2 = 0;
        this.contadorHab1 = 0;
        this.contadorHab3 = 0;
        this.contadorHab4 = 0;
        this.contadorHab5 = 0;
        this.contadorCoins = 0;
        this.inventoryPanel = inventoryPanel;
        this.counterLabel = counterLabel;
        this.lives = lives;
        this.object = object;
        this.objectShowInventory = objectShowInventory;
        this.usuario = usuario;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());

        movePlayer();
        createCoins();
        manageCoins();
        receiveObject();
        managePortal();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        movePlayer();
    }

    private void movePlayer() {
        int x = player.getX();
        int y = player.getY();
        int speed = playerCharacter.getSpeed();
        boolean right = pressedKeys.contains(KeyEvent.VK_RIGHT);
        boolean left = pressedKeys.contains(KeyEvent.VK_LEFT);
        boolean up = pressedKeys.contains(KeyEvent.VK_UP);
        boolean down = pressedKeys.contains(KeyEvent.VK_DOWN);

        int originalX = x;
        int originalY = y;

        if (right) {
            x += speed;
            player.setIcon(new ImageIcon(playerCharacter.getRightPath()));
        }
        if (left) {
            x -= speed;
            player.setIcon(new ImageIcon(playerCharacter.getLeftPath()));
        }
        if (up) {
            y -= speed;
            player.setIcon(new ImageIcon(playerCharacter.getUpPath()));
        }
        if (down) {
            y += speed;
            player.setIcon(new ImageIcon(playerCharacter.getDowmPath()));
        }

        player.setLocation(x, y);

        if (checkWalls(player)) {
            player.setLocation(originalX, originalY);
        }
        useWhenCreateFunctions();
    }


    private void manageCoins() {
        for (int i = 0; i < coinsList.size(); i++) {
            JLabel coin = coinsList.get(i);


            if (checkWalls(coin)) {
                panel.remove(coin);
                coinsList.remove(i);
                contadorCoins--;
                i--;
                panel.revalidate();
                panel.repaint();

            }


            if (player.getBounds().intersects(coin.getBounds())) {
                panel.remove(coin);
                counterInt =  counterInt+1;
                counterLabel.setText(String.valueOf(counterInt));
                coinsList.remove(i);
                contadorCoins--;
                i--;
                panel.revalidate();
                panel.repaint();
                playerCharacter.addGold();
            }
        }
    }
    public void managePortal(){
        if (playerCharacter.getGold()>9){
            createPortal();

            if (player.getBounds().intersects(portal.getBounds())){
                usuario.guardarDadesDb(playerCharacter.getLives(), playerCharacter.getGold());
                JOptionPane optionPane = new JOptionPane("¡Has ganado el juego!", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog("Victoria");
                dialog.setModal(true);
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        System.exit(0);
                    }
                });
                timer.setRepeats(false);
                timer.start();

                dialog.setVisible(true);
            }
        }

    }
    public void createPortal(){
        portal = new JLabel();
        portal.setSize(64, 64);
        portal.setLocation(1400,800);
        portal.setIcon(new ImageIcon("src/images/rpgPortal.png"));
        panel.add(portal);
        panel.setComponentZOrder(portal, 0);
        panel.revalidate();
        panel.repaint();
    }

    private void useWhenCreateFunctions() {
        whenCreateHab2();
        whenCreateHab1();
        whenCreteHab3();
        whenCreateHab4();
        whenCreateHab5();
    }

    private boolean checkWalls(JLabel label) {
        for (Rectangle rect : keyStates) {
            if (rect.intersects(label.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private JLabel createEnemy(Point position) {
        JLabel enemy = new JLabel();
        Skeleton enemyS = new Skeleton();
        enemy.setSize(64, 64);
        enemy.setLocation(position);
        enemy.setIcon(new ImageIcon(enemyS.getDowmPath()));
        panel.add(enemy);
        panel.setComponentZOrder(enemy, 0);
        panel.revalidate();
        panel.repaint();

        return enemy;
    }

    private void whenCreateHab2() {
        Point enemyPosition = null;
        switch (contadorHab2) {
            case 0:
                enemyPosition = new Point(800, 700);
                break;
            case 1:
                enemyPosition = new Point(800, 500);
                break;
            case 2:
                enemyPosition = new Point(600, 700);
                break;
        }
        if (player.getX() >= 420 && player.getX() < 452 && contadorHab2 < 3) {
            JLabel enemy = createEnemy(enemyPosition);
            Timer timer = new Timer(100, new timerActionEnemy(player, enemy, keyStates,panel,playerCharacter,lives,inventoryPanel,usuario));
            timer.start();
            contadorHab2++;
        }
    }

    private void whenCreateHab1() {
        Point enemyPosition = (contadorHab1 == 0) ? new Point(250, 300) : new Point(300, 400);
        if (player.getX() >= 132 && player.getX() < 419 && player.getY() > 800 && player.getY() < 900 && contadorHab1 < 2) {
            JLabel enemy = createEnemy(enemyPosition);
            Timer timer = new Timer(100, new timerActionEnemy(player, enemy, keyStates,panel,playerCharacter,lives,inventoryPanel,usuario));
            timer.start();
            contadorHab1++;
        }
    }

    private void whenCreteHab3() {
        Point enemyPosition = (contadorHab3 == 0) ? new Point(500, 170) : new Point(500, 250);
        if (player.getX() >= 804 && player.getX() < 868 && player.getY() > 320 && player.getY() < 352 && contadorHab3 < 2) {
            JLabel enemy = createEnemy(enemyPosition);
            Timer timer = new Timer(100, new timerActionEnemy(player, enemy, keyStates,panel,playerCharacter,lives,inventoryPanel,usuario));
            timer.start();
            contadorHab3++;
        }
    }

    private void whenCreateHab4() {
        Point enemyPosition = null;
        switch (contadorHab4) {
            case 0:
                enemyPosition = new Point(1060, 500);
                break;
            case 1:
                enemyPosition = new Point(1500, 500);
                break;
            case 2:
                enemyPosition = new Point(1200, 700);
                break;
            case 3:
                enemyPosition = new Point(1600, 700);
                break;
            case 4:
                enemyPosition = new Point(1100, 700);
                break;
        }
        if (player.getX() >= 996 && player.getX() < 1028 && contadorHab4 < 5) {
            JLabel enemy = createEnemy(enemyPosition);
            Timer timer = new Timer(100, new timerActionEnemy(player, enemy, keyStates,panel,playerCharacter,lives,inventoryPanel,usuario));
            timer.start();
            contadorHab4++;
        }
    }

    private void whenCreateHab5() {
        Point enemyPosition = null;
        switch (contadorHab5) {
            case 0:
                enemyPosition = new Point(1200, 170);
                break;
            case 1:
                enemyPosition = new Point(1600, 250);
                break;
            case 2:
                enemyPosition = new Point(1200, 700);
                break;
            case 3:
                enemyPosition = new Point(1600, 700);
                break;
            case 4:
                enemyPosition = new Point(1100, 700);
                break;
        }
        if (player.getX() >= 1604 && player.getX() < 1732 && player.getY() > 320 && player.getY() < 352 && contadorHab5 < 2) {
            JLabel enemy = createEnemy(enemyPosition);
            Timer timer = new Timer(100, new timerActionEnemy(player, enemy, keyStates,panel,playerCharacter,lives,inventoryPanel,usuario));
            timer.start();
            contadorHab5++;
        }
    }

    private void createCoins() {
        while (contadorCoins<4){
            JLabel coin = new JLabel();
            coin.setSize(64, 64);
            ImageIcon coinImage = new ImageIcon("src/images/dungeon/dollar.png");
            Image coinImageScaled = coinImage.getImage().getScaledInstance(coin.getWidth(), coin.getHeight(), Image.SCALE_SMOOTH);
            coin.setIcon(new ImageIcon(coinImageScaled));
            Point position = generateRandomPosition();
            coin.setLocation(position);
            coin.setVisible(true);
            panel.add(coin);
            panel.setComponentZOrder(coin, 0);
            coinsList.add(coin);
            contadorCoins++;}


    }
    private Point generateRandomPosition() {
        int finalY = 932;
        int finalX = 1704;
        int startX = 200;
        int startY = 100;

        Random rand = new Random();
        int randomX = rand.nextInt((finalX - startX) + 1) + startX;
        int randomY = rand.nextInt((finalY - startY) + 1) + startY;
        return new Point(randomX, randomY);
    }
    private void receiveObject(){
        if (player.getBounds().intersects(object.getBounds())) {
            panel.remove(object);
            inventoryPanel.add(objectShowInventory);
            inventoryPanel.revalidate();
            playerCharacter.addObject();
        }

        if (!playerCharacter.isObjectEquiped()) {
            inventoryPanel.remove(objectShowInventory);
            inventoryPanel.revalidate();

        }
        inventoryPanel.repaint();
        panel.repaint();
    }



}
