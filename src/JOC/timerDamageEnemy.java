package JOC;

import javax.swing.*;
import charachters.*;
import charachters.Character;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * La clase {@code timerDamageEnemy} implementa la lógica de daño al enemigo
 * y actualización de las vidas del jugador. También maneja el temporizador para
 * animaciones y acciones relacionadas con diferentes tipos de personajes (Guerrero, Mago, Sacerdote).
 */
public class timerDamageEnemy implements ActionListener {

    private JLabel player;
    private JLabel enemy;
    private JPanel panel;
    private Character playerCharacter;
    private ArrayList<JLabel> lives;
    private JPanel inventory;

    /**
     * Constructor de la clase {@code timerDamageEnemy}.
     *
     * @param player          JLabel que representa al jugador.
     * @param enemy           JLabel que representa al enemigo.
     * @param panel           JPanel donde se muestran los personajes y elementos visuales.
     * @param playerCharacter Objeto que representa al personaje del jugador.
     * @param lives           Lista de etiquetas que representan las vidas del jugador.
     * @param inventory       JPanel que representa el inventario del jugador.
     * @param usuario         Objeto de la clase Usuario.
     */
    public timerDamageEnemy(JLabel player, JLabel enemy, JPanel panel, Character playerCharacter, ArrayList<JLabel> lives, JPanel inventory, Usuario usuario) {
        this.player = player;
        this.enemy = enemy;
        this.panel = panel;
        this.playerCharacter = playerCharacter;
        this.lives = lives;
        this.inventory = inventory;
    }

    /**
     * Método que se invoca cuando ocurre una acción (intersección entre jugador y enemigo).
     * Verifica el tipo de personaje del jugador y ejecuta las acciones correspondientes.
     * Si no hay objetos equipados, reduce las vidas del jugador.
     *
     * @param e El evento de acción que se dispara.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (player.getBounds().intersects(enemy.getBounds())) {
            if (playerCharacter instanceof Warrior && playerCharacter.isObjectEquiped()) {
                createTimerWarrior();
            }
            if (playerCharacter instanceof Wizard && playerCharacter.isObjectEquiped()) {
                createTimerWizard();
            } else if (playerCharacter instanceof Priest && playerCharacter.isObjectEquiped()) {
                playerCharacter.getDamageFromEnemy();
                if (playerCharacter.getLives() == 0) {
                    player.setLocation(300, 300);
                    ((Priest) playerCharacter).useObject();
                    panel.revalidate();
                    panel.repaint();
                }
                dropAndAddLives();
            } else if (!playerCharacter.isObjectEquiped()) {
                playerCharacter.getDamageFromEnemy();
                dropAndAddLives();
                inventory.repaint();
                inventory.revalidate();
            }
        }
    }

    /**
     * Actualiza las vidas del jugador en la interfaz, eliminando o añadiendo etiquetas de vida.
     * Si las vidas llegan a 0, muestra un cuadro de diálogo de derrota y cierra el juego.
     */
    private void dropAndAddLives() {
        int actualLives = playerCharacter.getLives();

        do {
            if (actualLives < lives.size()) {
                if (!lives.isEmpty()) {
                    JLabel live = lives.remove(lives.size() - 1);
                    inventory.remove(live);
                }
            } else if (actualLives > lives.size()) {
                addExtraLive();
            }
        } while (actualLives != lives.size());

        if (actualLives == 0) {
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

    /**
     * Crea y ejecuta un temporizador que muestra una animación de ataque del Mago.
     * Posteriormente, dispara el evento de daño causado por el Mago.
     */
    private void createTimerWizard() {
        String warriorPath = "src/images/magicHealth.gif";
        JLabel gifLabel = showGifAtPlayerPosition(warriorPath);
        Timer hideGifTimer = new Timer(1000, new timerDamageWizard(panel, gifLabel, enemy, (Wizard) playerCharacter));
        hideGifTimer.setRepeats(false);
        hideGifTimer.start();
    }

    /**
     * Crea y ejecuta un temporizador que muestra una animación de ataque del Guerrero.
     * Posteriormente, dispara el evento de daño causado por el Guerrero.
     */
    private void createTimerWarrior() {
        String warriorPath = "src/images/gif_warrior.gif";
        JLabel gifLabel = showGifAtPlayerPosition(warriorPath);
        Timer hideGifTimer = new Timer(1000, new timerDamageWarior(panel, gifLabel, enemy, (Warrior) playerCharacter));
        hideGifTimer.setRepeats(false);
        hideGifTimer.start();
    }

    /**
     * Muestra un gif en la posición del jugador, basado en el camino proporcionado.
     *
     * @param path La ruta del gif que se va a mostrar.
     * @return JLabel que contiene el gif.
     */
    private JLabel showGifAtPlayerPosition(String path) {
        if (playerCharacter instanceof Warrior) {
            Warrior warrior = new Warrior();
            if (Objects.equals(player.getIcon().toString(), playerCharacter.getLeftPath())) {
                ImageIcon gifIconAttack = new ImageIcon(warrior.getGetWarriorAttackGifLeft());
                player.setIcon(gifIconAttack);
            } else if (Objects.equals(player.getIcon().toString(), playerCharacter.getRightPath())) {
                ImageIcon gifIconAttack = new ImageIcon(warrior.getGetWarriorAttackGifRight());
                player.setIcon(gifIconAttack);
            } else if (Objects.equals(player.getIcon().toString(), playerCharacter.getUpPath())) {
                ImageIcon gifIconAttack = new ImageIcon(warrior.getWarriorAttackGifUp());
                player.setIcon(gifIconAttack);
            } else if (Objects.equals(player.getIcon().toString(), playerCharacter.getDowmPath())) {
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

    /**
     * Añade una vida extra al inventario del jugador.
     */
    private void addExtraLive() {
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
