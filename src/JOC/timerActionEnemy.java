package JOC;

import charachters.Character;
import charachters.Skeleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class timerActionEnemy implements ActionListener {
    private JLabel player;
    private JLabel enemy;
    private int enemyX;
    private int enemyY;
    private int playerX;
    private int playerY;
    private ArrayList<Rectangle> keyStates;
    private Skeleton skeleton;
    private JPanel panel;
    private Timer damageTimer;
    private Character playerCharacter;
    private ArrayList<JLabel> lives;
    private JPanel inventory;
    private Usuario usuario;

    public timerActionEnemy(JLabel player, JLabel enemy, ArrayList<Rectangle> keyStates, JPanel panel,Character playerCharacter,ArrayList<JLabel> lives,JPanel inventory,Usuario usuario) {
        this.player = player;
        this.enemy = enemy;
        this.keyStates = keyStates;
        this.skeleton = new Skeleton();
        this.panel = panel;
        this.playerCharacter = playerCharacter;
        this.lives = lives;
        this.inventory = inventory;
        this.usuario = usuario;
        enemyDamageTimer();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        enemyMovement();
    }
    public void enemyDamageTimer(){
        this.damageTimer = new Timer(1500, new timerDamageEnemy(player, enemy, panel,playerCharacter,lives,inventory,usuario));
        this.damageTimer.start();
    }


    private void enemyMovement(){
        int enemyXOriginal = enemy.getX();
        int enemyYOriginal = enemy.getY();
        enemyX = enemy.getX();
        enemyY = enemy.getY();
        playerX = player.getX();
        playerY = player.getY();

        int diffX = playerX - enemyX;
        int diffY = playerY - enemyY;
        int absDiffX = Math.abs(diffX);
        int absDiffY = Math.abs(diffY);
        Random rand = new Random();
        int numRand = rand.nextInt((7 - 4) + 1) + 4;
        skeleton.setSpeed(numRand);
        if (absDiffX > absDiffY) {
            if (diffX > 0) {
                enemyX += skeleton.getSpeed();
                enemy.setIcon(new ImageIcon(skeleton.getRightPath()));
            } else if (diffX < 0) {
                enemyX -= skeleton.getSpeed();
                enemy.setIcon(new ImageIcon(skeleton.getLeftPath()));
            }
        } else {
            if (diffY > 0) {
                enemyY += skeleton.getSpeed();
                enemy.setIcon(new ImageIcon(skeleton.getDowmPath()));
            } else if (diffY < 0) {
                enemyY -= skeleton.getSpeed();
                enemy.setIcon(new ImageIcon(skeleton.getUpPath()));
            }
        }

        enemy.setLocation(enemyX, enemyY);

        if (checkWalls()) {
            enemy.setLocation(enemyXOriginal, enemyYOriginal);
        }

    }

    private boolean checkWalls() {
        for (Rectangle keyState : keyStates) {
            if (keyState.intersects(enemy.getBounds())) {
                return true;
            }
        }
        return false;
    }
}

