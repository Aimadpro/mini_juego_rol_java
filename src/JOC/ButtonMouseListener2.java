package JOC;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonMouseListener2 extends MouseAdapter {
    private JTextArea textoGuardar;
    private Usuario usuario;
    private JPanel panelActual;
    private JPanel panelSiguiente;
    private JFrame frame;

    public ButtonMouseListener2(JTextArea textoGuardar, Usuario usuario, JPanel panelActual, JPanel panelSiguiente, JFrame frame) {
        this.textoGuardar = textoGuardar;
        this.usuario = usuario;
        this.panelActual = panelActual;
        this.panelSiguiente = panelSiguiente;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        usuario.setName(textoGuardar.getText());
        panelActual.removeAll();
        panelActual.add(panelSiguiente);
        frame.repaint();
        frame.revalidate();
    }



}
