package JOC;

import charachters.Character;
import charachters.Priest;
import charachters.Warrior;
import charachters.Wizard;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase que extiende MouseAdapter para gestionar las interacciones del jugador al seleccionar una clase de personaje
 * y realizar las acciones correspondientes en la interfaz del juego.
 */
public class ButtonMouseListenerSelectedClass extends MouseAdapter {

    private JPanel panelActual; // El panel actual donde está interactuando el jugador.
    private JPanel siguientePanel; // El siguiente panel al que se transicionará tras la selección.
    private Character characterSelected; // Personaje seleccionado por el jugador.
    private String eleccion; // Clase seleccionada por el jugador (Warrior, Wizard, Priest).
    private ArrayList<Rectangle> keyStates; // Estados clave para gestionar el movimiento del jugador.
    private JLabel player; // Representación visual del jugador.
    private JLabel object; // Objeto del inventario del jugador.
    private JPanel inventoryPanel; // Panel donde se visualiza el inventario del jugador.
    private JLabel counterLabel; // Etiqueta para mostrar el contador de oro u otro recurso.
    private ArrayList<JLabel> livesArray; // Lista de etiquetas que representan las vidas del jugador.
    private JLabel objetShowInventory; // Etiqueta que muestra el objeto en el inventario.
    private Usuario usuario; // Información del usuario del juego.

    /**
     * Constructor para la clase ButtonMouseListenerSelectedClass.
     *
     * @param decisionJugador  Personaje seleccionado por el jugador.
     * @param eleccion         La clase de personaje seleccionada ("wizard", "warrior", "priest").
     * @param panelActual      El panel actual del juego donde está interactuando el jugador.
     * @param siguientePanel   El panel al que se transiciona después de la selección.
     * @param frame            El marco de la ventana del juego.
     * @param keyStates        Lista de estados de teclas que se utilizan para el movimiento del personaje.
     * @param usuario          El usuario que está jugando.
     */
    public ButtonMouseListenerSelectedClass(Character decisionJugador, String eleccion, JPanel panelActual, JPanel siguientePanel, JFrame frame, ArrayList<Rectangle> keyStates, Usuario usuario) {
        this.panelActual = panelActual;
        this.siguientePanel = siguientePanel;
        this.characterSelected = decisionJugador;
        this.eleccion = eleccion;
        this.keyStates = keyStates;
        this.usuario = usuario;
    }

    /**
     * Método que se ejecuta cuando el usuario hace clic en la clase de personaje.
     * Realiza la transición al siguiente panel y configura el inventario, objeto y jugador seleccionado.
     *
     * @param e Evento de clic del ratón.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (eleccion.toLowerCase()) {
            case "wizard":
                characterSelected = new Wizard();
                break;
            case "warrior":
                characterSelected = new Warrior();
                break;
            case "priest":
                characterSelected = new Priest();
                break;
            default:
                throw new IllegalArgumentException("Elección de clase no válida: " + eleccion);
        }

        panelActual.removeAll();
        panelActual.add(siguientePanel);
        panelActual.revalidate();
        panelActual.repaint();

        siguientePanel.setFocusable(true);
        siguientePanel.requestFocusInWindow();
        createObjects();
        inventoryPanel();
        createPlayer();
    }

    /**
     * Método que aumenta el tamaño de los iconos cuando el ratón entra en un área interactuable.
     *
     * @param e Evento del ratón.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            int width = label.getWidth();
            int height = label.getHeight();
            label.setSize((int) (width * 1.1), (int) (height * 1.1));
        }
    }

    /**
     * Método que reduce el tamaño de los iconos cuando el ratón sale del área interactuable.
     *
     * @param e Evento del ratón.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            int width = label.getWidth();
            int height = label.getHeight();
            label.setSize((int) (width / 1.1), (int) (height / 1.1));
        }
    }

    /**
     * Crea la representación gráfica del jugador en el panel del juego.
     */
    private void createPlayer() {
        player = new JLabel();
        player.setSize(32, 32);
        player.setLocation(300, 300);
        ImageIcon blockImage = new ImageIcon(characterSelected.getDowmPath());
        player.setIcon(blockImage);
        player.setVisible(true);

        siguientePanel.add(player);
        siguientePanel.setComponentZOrder(player, 0);
        movementPlayer listener = new movementPlayer(player, characterSelected, keyStates, siguientePanel, object, inventoryPanel, counterLabel, livesArray, objetShowInventory, usuario);
        siguientePanel.addKeyListener(listener);
    }

    /**
     * Crea los objetos del inventario basados en la clase del personaje.
     */
    private void createObjects() {
        Point positionObect = new Point(300, 850);
        Rectangle sizeObject = new Rectangle(32, 32);
        Rectangle sizeObjectInventory = new Rectangle(76, 76);
        Point PositionObjectInventory = new Point(900, 40);

        if (characterSelected instanceof Warrior) {
            objetShowInventory = createObjectLabels("src/images/dungeon/sword.png", PositionObjectInventory, sizeObjectInventory, inventoryPanel, 0);
            object = createObjectLabels("src/images/dungeon/sword.png", positionObect, sizeObject, siguientePanel, 1);
        } else if (characterSelected instanceof Wizard) {
            objetShowInventory = createObjectLabels("src/images/dungeon/potion.png", PositionObjectInventory, sizeObjectInventory, inventoryPanel, 0);
            object = createObjectLabels("src/images/dungeon/potion.png", positionObect, sizeObject, siguientePanel, 1);
        } else if (characterSelected instanceof Priest) {
            objetShowInventory = createObjectLabels("src/images/dungeon/mitra.png", PositionObjectInventory, sizeObjectInventory, inventoryPanel, 0);
            object = createObjectLabels("src/images/dungeon/mitra.png", positionObect, sizeObject, siguientePanel, 1);
        }
    }

    /**
     * Crea etiquetas gráficas para los objetos en el inventario o el mundo del juego.
     *
     * @param path       Ruta de la imagen del objeto.
     * @param position   Posición del objeto.
     * @param size       Tamaño del objeto.
     * @param panel      Panel donde se añadirá el objeto.
     * @param condition  Condición para añadir el objeto en el panel.
     * @return Etiqueta JLabel que representa el objeto.
     */
    public JLabel createObjectLabels(String path, Point position, Rectangle size, JPanel panel, int condition) {
        JLabel object = new JLabel();
        object.setSize(size.getSize());
        ImageIcon coinImage = new ImageIcon(path);
        Image coinImageScaled = coinImage.getImage().getScaledInstance(object.getWidth(), object.getHeight(), Image.SCALE_SMOOTH);
        object.setIcon(new ImageIcon(coinImageScaled));
        object.setLocation(position);
        object.setVisible(true);
        if (condition == 1) {
            panel.add(object);
            panel.setComponentZOrder(object, 0);
        }
        return object;
    }

    /**
     * Configura el panel del inventario para mostrar objetos y recursos del jugador.
     */
    private void inventoryPanel() {
        inventoryPanel = createInventoryPanel();
        siguientePanel.add(inventoryPanel);
        siguientePanel.setComponentZOrder(inventoryPanel, 0);

        addImageLabel(inventoryPanel);
        addCounterLabel(inventoryPanel);
        addLivesIcons(inventoryPanel, characterSelected.getLives());

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * Crea el panel que contendrá el inventario del jugador.
     *
     * @return Panel configurado para mostrar el inventario.
     */
    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel();
        panel.setLocation(200, 0);
        panel.setSize(1504, 100);
        panel.setBackground(new Color(60, 63, 65));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBorder(new CompoundBorder(
                new LineBorder(Color.DARK_GRAY, 3),
                new EmptyBorder(10, 10, 10, 10)
        ));
        return panel;
    }

    /**
     * Añade una imagen al panel de inventario.
     *
     * @param panel Panel donde se añadirá la imagen.
     */
    private void addImageLabel(JPanel panel) {
        JLabel imageLabel = new JLabel();
        imageLabel.setSize(76, 76);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/dollar.png");
        Image imageScaled = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(imageScaled));
        panel.add(imageLabel);
        panel.setComponentZOrder(imageLabel, 0);
    }

    /**
     * Añade un contador de recursos (como oro) al panel de inventario.
     *
     * @param panel Panel donde se añadirá el contador.
     */
    private void addCounterLabel(JPanel panel) {
        counterLabel = new JLabel("0");
        counterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        counterLabel.setForeground(Color.WHITE);
        panel.add(counterLabel);
        panel.setComponentZOrder(counterLabel, 0);
    }

    /**
     * Añade los iconos que representan las vidas del jugador al panel de inventario.
     *
     * @param panel      Panel donde se añadirán las vidas.
     * @param livesCount Número de vidas del jugador.
     */
    private void addLivesIcons(JPanel panel, int livesCount) {
        livesArray = new ArrayList<>();
        for (int i = 0; i < livesCount; i++) {
            JLabel lives = new JLabel();
            lives.setSize(76, 76);
            ImageIcon heartImage = new ImageIcon("src/images/dungeon/heart.png");
            Image heartImageScaled = heartImage.getImage().getScaledInstance(lives.getWidth(), lives.getHeight(), Image.SCALE_SMOOTH);
            lives.setIcon(new ImageIcon(heartImageScaled));
            lives.setVisible(true);
            livesArray.add(lives);
            inventoryPanel.add(lives);
            inventoryPanel.setComponentZOrder(lives, 0);
        }
    }
}
