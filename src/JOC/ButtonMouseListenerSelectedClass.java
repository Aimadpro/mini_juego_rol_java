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

public class ButtonMouseListenerSelectedClass extends MouseAdapter {
    private JPanel panelActual;
    private JPanel siguientePanel;
    private JFrame frame;
    private Character characterSelected;
    private String eleccion;
     private ArrayList<Rectangle> keyStates;
     private JLabel player;
     private JLabel object;
     private JPanel inventoryPanel;
     JLabel counterLabel;
     private ArrayList<JLabel>livesArray;
     private JLabel objetShowInventory;
     private Usuario usuario;


    public ButtonMouseListenerSelectedClass(Character decisionJugador, String eleccion, JPanel panelActual, JPanel siguientePanel, JFrame frame,ArrayList<Rectangle> keyStates,Usuario usuario) {
        this.panelActual = panelActual;
        this.siguientePanel = siguientePanel;
        this.frame = frame;
        this.characterSelected = decisionJugador;
        this.eleccion = eleccion;
        this.keyStates = keyStates;
        this.usuario = usuario;
    }

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

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            int width = label.getWidth();
            int height = label.getHeight();
            label.setSize((int) (width * 1.1), (int) (height * 1.1));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel label = (JLabel) e.getSource();
            int width = label.getWidth();
            int height = label.getHeight();
            label.setSize((int) (width / 1.1), (int) (height / 1.1));
        }
    }

    private void createPlayer() {
        player = new JLabel();
        player.setSize(32, 32);
        player.setLocation(300, 300);
        ImageIcon blockImage = new ImageIcon(characterSelected.getDowmPath());
        player.setIcon(blockImage);
        player.setVisible(true);

        siguientePanel.add(player);
        siguientePanel.setComponentZOrder(player, 0);
        movementPlayer listener = new movementPlayer(player,characterSelected,keyStates,siguientePanel,object,inventoryPanel,counterLabel,livesArray,objetShowInventory,usuario);
        siguientePanel.addKeyListener(listener);
    }
    private void createObjects(){
        Point positionObect = new Point(300,850);
        Rectangle sizeObject = new Rectangle(32,32);
        Rectangle sizeObjectInventory = new Rectangle(76,76);
        Point PositionObjectInventory = new Point(900,40);
        if (characterSelected instanceof Warrior){
            objetShowInventory = createObjectLabels("src/images/dungeon/sword.png",PositionObjectInventory,sizeObjectInventory,inventoryPanel,0);
            object=  createObjectLabels("src/images/dungeon/sword.png",positionObect,sizeObject,siguientePanel,1);
        }
        else if (characterSelected instanceof Wizard){
            objetShowInventory = createObjectLabels("src/images/dungeon/potion.png",PositionObjectInventory,sizeObjectInventory,inventoryPanel,0);
            object = createObjectLabels("src/images/dungeon/potion.png",positionObect,sizeObject,siguientePanel,1);
        }
        else if (characterSelected instanceof Priest){
            objetShowInventory = createObjectLabels("src/images/dungeon/mitra.png",PositionObjectInventory,sizeObjectInventory,inventoryPanel,0);
            object = createObjectLabels("src/images/dungeon/mitra.png",positionObect,sizeObject,siguientePanel,1);
        }


    }
    public JLabel createObjectLabels(String path, Point position,Rectangle size,JPanel panel, int condition){
        JLabel object = new JLabel();
        object.setSize(size.getSize());
        ImageIcon coinImage = new ImageIcon(path);
        Image coinImageScaled = coinImage.getImage().getScaledInstance(object.getWidth(), object.getHeight(), Image.SCALE_SMOOTH);
        object.setIcon(new ImageIcon(coinImageScaled));
            object.setLocation(position);

        object.setVisible(true);
        if (condition==1) {
            panel.add(object);
            panel.setComponentZOrder(object, 0);
        }

        return object;

    }
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

    private void addImageLabel(JPanel panel) {
        JLabel imageLabel = new JLabel();
        imageLabel.setSize(76, 76);
        ImageIcon imageIcon = new ImageIcon("src/images/dungeon/dollar.png"); // Reemplaza con la ruta de tu imagen
        Image imageScaled = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(imageScaled));
        panel.add(imageLabel);
        panel.setComponentZOrder(imageLabel, 0);
    }

    private void addCounterLabel(JPanel panel) {
        counterLabel = new JLabel("0");
        counterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        counterLabel.setForeground(Color.WHITE);
        panel.add(counterLabel);
        panel.setComponentZOrder(counterLabel, 0);
    }

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
