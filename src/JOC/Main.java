package JOC;

import charachters.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {
    private JPanel panelMain;

    private JTextArea titleText;
    private JPanel selectClassArea;
    private JButton startButton;
    private JFrame frame;
    private JPanel selectName;
    private JTextArea chooseName;
    private JButton nextButton;
    private JPanel gameArea;
    private Character charachterSelected;
    private ArrayList<Rectangle> keyStates;
    private  Usuario usuario;



    public Main() {
        frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        panelMain = new JPanel();
        panelMain.setLayout(null);
        frame.add(panelMain);
        Dimension dimension = new Dimension(1920, 1080);
        panelMain.setPreferredSize(dimension);
        panelMain.setSize(1920, 1080);
        panelMain.setFocusable(true);
        panelMain.requestFocus();
        showTitleGame();
        String rutaFondo = "src/images/fondoRPG.jpg";
        chooseBackgrounds(panelMain, dimension, rutaFondo);
        setChooseName();
        setSelectClassArea();
        setStartButton();
        frame.setVisible(true);

    }
    public void setChooseName(){
        selectName = new JPanel();
        Dimension dimension = new Dimension(1920,1080);
        selectName.setPreferredSize(dimension);
        selectName.setSize(1920,1080);
        selectName.setLayout(null);
        selectName.setFocusable(true);
        selectName.requestFocus();
        String rutaFondo = "src/images/fondoRPG.jpg";
        chooseBackgrounds(selectName,dimension,rutaFondo);
        Dimension dimension2 = new Dimension(800, 100);
        Point ubicacion = new Point((int) (selectName.getWidth()/2-dimension2.getWidth()/2), 200);
        String text = "CHOOSE YOUR NAME";
        showTitles(text,ubicacion,dimension2,selectName);
        setNameJText();
        setNextButton();
    }
    public void setNameJText(){
        chooseName = new JTextArea();
        chooseName.setSize(400,50);
        chooseName.setLocation(panelMain.getWidth()/2-chooseName.getWidth()/2,500);
        selectName.add(chooseName);
        chooseName.setFont(new Font("Georgia",Font.BOLD,40));
        selectName.setComponentZOrder(chooseName, 0);

    }
    public void setSelectClassArea(){
        selectClassArea = new JPanel();
        Dimension dimension = new Dimension(1920,1080);
        selectClassArea.setPreferredSize(dimension);
        selectClassArea.setSize(1920,1080);
        selectClassArea.setLayout(null);
        String rutaFondo = "src/images/imagenFondoClase.jpeg";
        selectClassArea.setFocusable(true);
        selectClassArea.requestFocus();
        chooseBackgrounds(selectClassArea,dimension,rutaFondo);
        Dimension dimension2 = new Dimension(800, 100);
        Point ubicacion = new Point((int) (selectClassArea.getWidth()/2-dimension2.getWidth()/2), 200);
        String text = "CHOOSE YOUR CLASS";
        showTitles(text,ubicacion,dimension2,selectClassArea);
        setClassImages();
    }
    public void setGameArea(){
        gameArea = new JPanel();
        Dimension dimension = new Dimension(1920,1080);
        gameArea.setPreferredSize(dimension);
        gameArea.setSize(1920,1080);
        gameArea.setLayout(null);
        String rutaFondo = "src/images/fondoJuegoRol.jpg";
        gameArea.setFocusable(true);
        gameArea.requestFocus();
        chooseBackgrounds(gameArea,dimension,rutaFondo);
        generateWalls();
    }
    public void setClassImages(){
        setGameArea();
        JLabel wizard = new JLabel();
        String pathWizard = "src/images/magoRpg.png";
        Dimension dimension = new Dimension( 500,460);
        Point pointWizard = new Point((int) (selectClassArea.getWidth()/3.5-dimension.getWidth()/2),400);
        showSelectClasses(wizard, pathWizard,pointWizard,selectClassArea,dimension);
        wizard.addMouseListener(new ButtonMouseListenerSelectedClass(charachterSelected, "Wizard", panelMain,gameArea,frame,keyStates,usuario));
        JLabel priest = new JLabel();
        String pathPriest = "src/images/sacerdoteRPG.png";
        Point pointPriest = new Point((int) (selectClassArea.getWidth()/2-dimension.getWidth()/2),400);
        showSelectClasses(priest, pathPriest,pointPriest,selectClassArea,dimension);
        priest.addMouseListener( new ButtonMouseListenerSelectedClass(charachterSelected,"priest",panelMain,gameArea,frame,keyStates,usuario));


        JLabel warrior = new JLabel();
        Dimension dimension2 = new Dimension( 460,500);
        String pathWarrior = "src/images/guereroPNG.png";
        Point pointWarrior = new Point((int) (selectClassArea.getWidth()/1.37-dimension.getWidth()/2),400);
        showSelectClasses(warrior,pathWarrior,pointWarrior,selectClassArea,dimension2);
        warrior.addMouseListener(new ButtonMouseListenerSelectedClass(charachterSelected,"warrior",panelMain,gameArea,frame,keyStates,usuario));

    }
    public void generateWalls(){
        int contadorX = 200;
        int contadorY=132;
        int startY = 100;
        int startX= 200;
        int numberWallsY=25;
        int numberWallsX=48;
        keyStates = new ArrayList<>();
        String pathBorderWalls= "src/images/dungeon/tile004.png";
        generateWallsX(startY,contadorX,pathBorderWalls,numberWallsX,keyStates);
        generateWallsY(startX,contadorY,pathBorderWalls,numberWallsY,keyStates);
        startY = 932;
        startX= 1704;
        generateWallsX(startY,contadorX,pathBorderWalls, numberWallsX,keyStates);
        generateWallsY(startX,contadorY,pathBorderWalls, numberWallsY,keyStates);
        generateTerrain();
        String pathWalls= "src/images/muroJuegoRol.jpg";
        startX= 420;
        numberWallsY=8;
        generateWallsY(startX,contadorY,pathWalls,numberWallsY,keyStates);
        contadorY = 480;
        numberWallsY=14;
        generateWallsY(startX,contadorY,pathWalls,numberWallsY,keyStates);
        startY=320;
        contadorX = 452;
        numberWallsX = 11;
        generateWallsX(startY,contadorX,pathWalls,numberWallsX,keyStates);
        contadorX = 900;
        numberWallsX=7;
        generateWallsX(startY,contadorX,pathWalls,numberWallsX,keyStates);
        contadorY = 132;
        startX = 996;
        numberWallsY=23;
        generateWallsY(startX,contadorY,pathWalls,numberWallsY,keyStates);
        contadorX = startX;
        numberWallsX=19;
        generateWallsX(startY,contadorX,pathWalls,numberWallsX,keyStates);

    }
    public void generateTerrain(){
        int startY = 132;
        int contadorX=232;
        int startX = 232;
        int contadorY = 164;
        String path = "src/images/dungeon/tile001.png";
        ArrayList<Rectangle> x = new ArrayList<>();
        for (int i = 0; i < 46; i++) {
            generateWallsX(startY,contadorX,path,1,x);
            contadorX = contadorX + 32;
            for (int j = 0; j < 24; j++) {

                generateWallsY(startX,contadorY,path,1,x);
                contadorY = contadorY + 32;
            }
            contadorY = 164;
            startX = startX+32;
        }
    }

    public void generateWallsY(int startX , int contadorY, String path, int numberWallsY, ArrayList<Rectangle> status){
        for (int i = 0; i <numberWallsY ; i++) {
                JLabel block = new JLabel();
                block.setSize(32, 32);
                block.setLocation(startX, contadorY);
                ImageIcon blockImage = new ImageIcon(path);
                Image scaledImage = blockImage.getImage().getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                block.setIcon(scaledIcon);
                gameArea.add(block);
                gameArea.setComponentZOrder(block, 0);
                contadorY = contadorY + 32;
                Rectangle bounds1 = block.getBounds();
                status.add(bounds1);
        }

    }
    public  void generateWallsX(int startY, int contadorX, String path, int numberWallsX, ArrayList<Rectangle> status){
        for (int i = 0; i <numberWallsX ; i++) {
            JLabel block = new JLabel();
            block.setSize(32,32);
            block.setLocation(contadorX, startY);
            ImageIcon blockImage = new ImageIcon(path);
            Image scaledImage = blockImage.getImage().getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            block.setIcon(scaledIcon);
            gameArea.add(block);
            gameArea.setComponentZOrder(block, 0);
            contadorX = contadorX+32;
            Rectangle bounds1 = block.getBounds();
            status.add(bounds1);
        }

    }


    public void setNextButton(){
        nextButton = new JButton("START");
        nextButton.setFont(new Font("Impact", Font.BOLD, 40));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(50, 205, 50)); // Color de fondo verde
        nextButton.setFocusPainted(false); // Quitar el resaltado al obtener el foco
        nextButton.setSize(400,75);
        nextButton.setLocation(panelMain.getWidth()/2-nextButton.getWidth()/2, 600);
        selectName.add(nextButton, BorderLayout.CENTER);
        usuario = new Usuario();
        nextButton.addMouseListener(new ButtonMouseListenerName());

    }
    private class ButtonMouseListenerName extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            usuario.setName(chooseName.getText());
            selectName.removeAll();
            selectName.add(selectClassArea);
            frame.repaint();
            frame.revalidate();
            System.out.println(usuario.getName());
        }
    }
    public void setStartButton(){
         startButton = new JButton("START THE ADVENTURE");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(50, 205, 50));
        startButton.setFocusPainted(false);
        startButton.setSize(400,125);
        startButton.setLocation(panelMain.getWidth()/2-startButton.getWidth()/2, 700);
        startButton.addMouseListener(new ButtonMouseListenerStart(startButton, panelMain,selectName,frame));
        panelMain.add(startButton, BorderLayout.CENTER);
        panelMain.setComponentZOrder(startButton,0);
    }

    public void showTitleGame(){
        String texto ="AN UNEXPLORED MAGICAL PLACE I";

        Dimension dimension = new Dimension(1200, 100);
        Point ubicacion = new Point((int) (panelMain.getWidth()/2-dimension.getWidth()/2), 200);
        showTitles(texto, ubicacion,dimension,panelMain);

    }


    public static void main(String[] args) {
        new Main();
    }
    public static void chooseBackgrounds(JPanel panelElegido, Dimension dimension, String rutaFondo){
       JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(rutaFondo));
        backgroundLabel.setBounds(0, 0, dimension.width, dimension.height);
        panelElegido.add(backgroundLabel);

    }
    public static void showTitles(String texto, Point ubicacion, Dimension tamanyo, JPanel panelElegido){
        JTextArea titleText = new JTextArea(texto);
        Font font = new Font("Georgia", Font.PLAIN, 70);

        titleText.setFont(font);
        titleText.setEditable(false);

        titleText.setSize(tamanyo);
        titleText.setLocation(ubicacion);
        titleText.setOpaque(false);
        titleText.setForeground(new Color(255,255,255  , 200));

        panelElegido.add(titleText);
        panelElegido.setComponentZOrder(titleText, 0);
    }

    public  void showSelectClasses(JLabel label, String path, Point point,JPanel panel,Dimension tamanyo){
        label.setSize(tamanyo);
        ImageIcon wizard = new ImageIcon(path);
        Image scaledImage = wizard.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        label.setIcon(scaledIcon);
        label.setLocation(point);
        panel.add(label);
        panel.setComponentZOrder(label, 0);
    }

}
