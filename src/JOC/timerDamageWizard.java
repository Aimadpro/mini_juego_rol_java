package JOC;
import charachters.Warrior;
import charachters.Wizard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class timerDamageWizard implements ActionListener {
    private JPanel panel;
    private JLabel gif;
    private JLabel enemy;
    private Wizard playerCharacter;

    public timerDamageWizard(JPanel panel, JLabel gif, JLabel enemy, Wizard playerCharacter) {
        this.panel = panel;
        this.gif = gif;
        this.enemy = enemy;
        this.playerCharacter = playerCharacter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerCharacter.usePotion();
        panel.remove(gif);
        panel.revalidate();
        panel.repaint();
    }
}
