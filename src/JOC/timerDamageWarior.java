package JOC;

import charachters.Warrior;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class timerDamageWarior implements ActionListener {
    private JPanel panel;
    private JLabel gif;
    private JLabel enemy;
    private Warrior playerCharacter;

    public timerDamageWarior(JPanel panel, JLabel gif, JLabel enemy, Warrior playerCharacter) {
        this.panel = panel;
        this.gif = gif;
        this.enemy=enemy;
        this.playerCharacter = playerCharacter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.remove(gif);
        panel.remove(enemy);
        enemy.setLocation(1920,1080);
        playerCharacter.useSword();
        panel.revalidate();
        panel.repaint();
    }
}
