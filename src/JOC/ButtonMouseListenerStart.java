package JOC;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonMouseListenerStart extends MouseAdapter {
    private JButton button;
    private JPanel panelMain;
    private JPanel selectClassArea;
    private JFrame frame;

    public ButtonMouseListenerStart(JButton button, JPanel panelMain, JPanel selectClassArea, JFrame frame) {
        this.button = button;
        this.panelMain= panelMain;
        this.selectClassArea = selectClassArea;
        this.frame = frame;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setBackground(new Color(227, 168, 14));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setBackground(new Color(192, 169, 22));
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        panelMain.removeAll();
        panelMain.add(selectClassArea);
        frame.repaint();
        frame.revalidate();

    }

}
