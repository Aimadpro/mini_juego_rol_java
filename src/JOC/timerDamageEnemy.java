package JOC;
import javax.print.DocFlavor;
import javax.swing.*;

import charachters.*;
import charachters.Character;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class timerDamageEnemy implements  ActionListener{
    private  JLabel player;
    private JLabel enemy;

    private JPanel panel;
    private Skeleton skeleton;
    private Character playerCharacter;
    private ArrayList<JLabel> lives;
    private JPanel inventory;
    private int counterLivesQuitted;
    private  Usuario usuario;
    public timerDamageEnemy(JLabel player, JLabel enemy,  JPanel panel,Character playerCharacter,ArrayList<JLabel> lives,JPanel inventory,Usuario usuario) {
        this.player = player;
        this.enemy = enemy;
        this.skeleton = new Skeleton();
        this.panel = panel;
        this.playerCharacter = playerCharacter;
        this.lives = lives;
        this.inventory = inventory;
        this.usuario = usuario;
        int counterLivesQuitted= lives.size()-1;

    }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getBounds().intersects(enemy.getBounds())) {
                    if (playerCharacter instanceof Warrior && playerCharacter.isObjectEquiped()) {
                        createTimerWarrior();
                    }
                    if (playerCharacter instanceof Wizard && playerCharacter.isObjectEquiped()) {
                        createTimerWizard();

                    } else if (playerCharacter instanceof Priest && playerCharacter.isObjectEquiped()){
                        playerCharacter.getDamageFromEnemy();
                        System.out.println(playerCharacter.getLives());
                        if (playerCharacter.getLives()==0) {
                            player.setLocation(300,300);
                            ((Priest) playerCharacter).useObject();
                            panel.revalidate();
                            panel.repaint();
                        }
                        dropAndAddLives();
                    }
                    else if (!playerCharacter.isObjectEquiped()){
                        System.out.println(playerCharacter.getLives());
                        playerCharacter.getDamageFromEnemy();
                        dropAndAddLives();
                        inventory.repaint();
                        inventory.revalidate();



                    }
                }
            }
    private void dropAndAddLives() {
        int actualLives = playerCharacter.getLives();
        System.out.println(lives.size() + " Lives.size");

        do {
            if (actualLives < lives.size()) {
                if (!lives.isEmpty()) {
                    JLabel live = lives.remove(lives.size() - 1);
                    inventory.remove(live);
                    counterLivesQuitted--;
                }
            } else if (actualLives > lives.size()) {
                addExtraLive();
            }
        } while (actualLives != lives.size());
        if (actualLives == 0) {
            System.out.println(playerCharacter.getGold());
            usuario.guardarDadesDb(playerCharacter.getLives(), playerCharacter.getGold());

                JOptionPane optionPane = new JOptionPane("¡Has perdido el juego!", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog("Derrota");
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

        inventory.repaint();
        inventory.revalidate();
    }


     private void createTimerWizard(){
         String warriorPath = "src/images/magicHealth.gif";
         JLabel gifLabel = showGifAtPlayerPosition(warriorPath);
         Timer hideGifTimer = new Timer(1000, new timerDamageWizard(panel,gifLabel,enemy, (Wizard) playerCharacter) );
         hideGifTimer.setRepeats(false);
         hideGifTimer.start();
     }

    private void createTimerWarrior(){
        String warriorPath = "src/images/gif_warrior.gif";
        JLabel gifLabel = showGifAtPlayerPosition(warriorPath);
        Timer hideGifTimer = new Timer(1000, new timerDamageWarior(panel,gifLabel,enemy, (Warrior) playerCharacter) );
        hideGifTimer.setRepeats(false);
        hideGifTimer.start();

    }
    private JLabel showGifAtPlayerPosition( String path) {

        if (playerCharacter instanceof Warrior) {
             Warrior warrior = new Warrior();
            if (Objects.equals(player.getIcon().toString(), playerCharacter.getLeftPath())){
                ImageIcon gifIconAttack = new ImageIcon(warrior.getGetWarriorAttackGifLeft());
                player.setIcon(gifIconAttack);

            } else if (Objects.equals(player.getIcon().toString(), playerCharacter.getRightPath())) {
                ImageIcon gifIconAttack = new ImageIcon(warrior.getGetWarriorAttackGifRight());
                player.setIcon(gifIconAttack);
            }
            else if (Objects.equals(player.getIcon().toString(), playerCharacter.getUpPath())) {
                ImageIcon gifIconAttack = new ImageIcon(warrior.getWarriorAttackGifUp());
                player.setIcon(gifIconAttack);
            }
            else if (Objects.equals(player.getIcon().toString(), playerCharacter.getDowmPath())) {
                ImageIcon gifIconAttack = new ImageIcon(warrior.getWarriorAttackGifDown());
                player.setIcon(gifIconAttack);
            }
        }

        JLabel gifLabel = new JLabel();
            gifLabel.setSize(64, 64);
            ImageIcon gifIcon = new ImageIcon(path);
            gifLabel.setIcon(gifIcon);
            gifLabel.setLocation(player.getX(), player.getY());
            panel.add(gifLabel);
            panel.setComponentZOrder(gifLabel, 0);
            panel.revalidate();
            panel.repaint();
        return gifLabel;
    }
    private void addExtraLive(){
        JLabel livesLabel = new JLabel();
        livesLabel.setSize(76, 76);
        ImageIcon heartImage = new ImageIcon("src/images/dungeon/heart.png");
        Image heartImageScaled = heartImage.getImage().getScaledInstance(livesLabel.getWidth(), livesLabel.getHeight(), Image.SCALE_SMOOTH);
        livesLabel.setIcon(new ImageIcon(heartImageScaled));
        livesLabel.setVisible(true);
        lives.add(livesLabel);
        inventory.add(livesLabel);
        inventory.setComponentZOrder(livesLabel, 0);
    }

}
