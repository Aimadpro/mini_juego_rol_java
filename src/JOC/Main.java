package JOC;

import charachters.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase principal que gestiona la interfaz gráfica del juego.
 * La clase incluye diferentes áreas del juego como la selección de clase, nombre, y el área principal de juego.
 * Proporciona métodos para configurar los paneles, imágenes y botones necesarios para interactuar con el usuario.
 */
public class Main {

    /** Panel principal del juego */
    private JPanel panelMain;

    /** Área de texto para mostrar el título */
    private JTextArea titleText;

    /** Panel para la selección de clases */
    private JPanel selectClassArea;

    /** Botón para iniciar el juego */
    private JButton startButton;

    /** Ventana principal del juego */
    private JFrame frame;

    /** Panel para la selección del nombre */
    private JPanel selectName;

    /** Área de texto para introducir el nombre */
    private JTextArea chooseName;

    /** Botón para pasar a la siguiente pantalla */
    private JButton nextButton;

    /** Panel del área de juego */
    private JPanel gameArea;

    /** Objeto de tipo Character que representa el personaje seleccionado */
    private Character charachterSelected;

    /** Lista de rectángulos que representan los estados de las teclas */
    private ArrayList<Rectangle> keyStates;

    /** Objeto Usuario para almacenar el nombre del jugador */
    private Usuario usuario;

    /**
     * Constructor de la clase Main. Inicializa la ventana principal y configura los paneles de selección.
     */
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

    /**
     * Configura el panel para la selección del nombre.
     */
    public void setChooseName(){
        selectName = new JPanel();
        Dimension dimension = new Dimension(1920,1080);
        selectName.setPreferredSize(dimension);
        selectName.setSize(1920,1080);
        selectName.setLayout(null);
        selectName.setFocusable(true);
        selectName.requestFocus();
        String rutaFondo = "src/images/fondoRPG.jpg";
        chooseBackgrounds(selectName, dimension, rutaFondo);
        Dimension dimension2 = new Dimension(800, 100);
        Point ubicacion = new Point((int) (selectName.getWidth()/2-dimension2.getWidth()/2), 200);
        String text = "CHOOSE YOUR NAME";
        showTitles(text, ubicacion, dimension2, selectName);
        setNameJText();
        setNextButton();
    }

    /**
     * Añade un campo de texto para introducir el nombre del jugador.
     */
    public void setNameJText(){
        chooseName = new JTextArea();
        chooseName.setSize(400, 50);
        chooseName.setLocation(panelMain.getWidth()/2-chooseName.getWidth()/2,500);
        selectName.add(chooseName);
        chooseName.setFont(new Font("Georgia", Font.BOLD, 40));
        selectName.setComponentZOrder(chooseName, 0);
    }

    /**
     * Configura el área de selección de clase del personaje.
     */
    public void setSelectClassArea(){
        selectClassArea = new JPanel();
        Dimension dimension = new Dimension(1920,1080);
        selectClassArea.setPreferredSize(dimension);
        selectClassArea.setSize(dimension);
        selectClassArea.setLayout(null);
        String rutaFondo = "src/images/imagenFondoClase.jpeg";
        selectClassArea.setFocusable(true);
        selectClassArea.requestFocus();
        chooseBackgrounds(selectClassArea, dimension, rutaFondo);
        Dimension dimension2 = new Dimension(800, 100);
        Point ubicacion = new Point((int) (selectClassArea.getWidth()/2-dimension2.getWidth()/2), 200);
        String text = "CHOOSE YOUR CLASS";
        showTitles(text, ubicacion, dimension2, selectClassArea);
        setClassImages();
    }

    /**
     * Configura el área de juego donde se desarrollarán las interacciones principales.
     */
    public void setGameArea(){
        gameArea = new JPanel();
        Dimension dimension = new Dimension(1920,1080);
        gameArea.setPreferredSize(dimension);
        gameArea.setSize(1920,1080);
        gameArea.setLayout(null);
        String rutaFondo = "src/images/fondoJuegoRol.jpg";
        gameArea.setFocusable(true);
        gameArea.requestFocus();
        chooseBackgrounds(gameArea, dimension, rutaFondo);
        generateWalls();
    }

    /**
     * Muestra las imágenes de las clases disponibles (mago, sacerdote, guerrero).
     */
    public void setClassImages(){
        setGameArea();
        JLabel wizard = new JLabel();
        String pathWizard = "src/images/magoRpg.png";
        Dimension dimension = new Dimension(500, 460);
        Point pointWizard = new Point((int) (selectClassArea.getWidth()/3.5-dimension.getWidth()/2),400);
        showSelectClasses(wizard, pathWizard, pointWizard, selectClassArea, dimension);
        wizard.addMouseListener(new ButtonMouseListenerSelectedClass(charachterSelected, "Wizard", panelMain, gameArea, frame, keyStates, usuario));

        JLabel priest = new JLabel();
        String pathPriest = "src/images/sacerdoteRPG.png";
        Point pointPriest = new Point((int) (selectClassArea.getWidth()/2-dimension.getWidth()/2),400);
        showSelectClasses(priest, pathPriest, pointPriest, selectClassArea, dimension);
        priest.addMouseListener(new ButtonMouseListenerSelectedClass(charachterSelected, "priest", panelMain, gameArea, frame, keyStates, usuario));

        JLabel warrior = new JLabel();
        Dimension dimension2 = new Dimension(460, 500);
        String pathWarrior = "src/images/guereroPNG.png";
        Point pointWarrior = new Point((int) (selectClassArea.getWidth()/1.37-dimension.getWidth()/2),400);
        showSelectClasses(warrior, pathWarrior, pointWarrior, selectClassArea, dimension2);
        warrior.addMouseListener(new ButtonMouseListenerSelectedClass(charachterSelected, "warrior", panelMain, gameArea, frame, keyStates, usuario));
    }



    /**
     * Genera las paredes y el terreno del área de juego.
     * Este método crea un mapa basado en coordenadas predefinidas, utilizando imágenes específicas para las paredes exteriores e interiores.
     * La configuración inicial de las paredes y el terreno ayuda a establecer los límites y el entorno visual del juego.
     */
    public void generateWalls() {
        int contadorX = 200;
        int contadorY = 132;
        int startY = 100;
        int startX = 200;
        int numberWallsY = 25;
        int numberWallsX = 48;
        keyStates = new ArrayList<>();
        String pathBorderWalls = "src/images/dungeon/tile004.png";

        // Genera las paredes exteriores del mapa
        generateWallsX(startY, contadorX, pathBorderWalls, numberWallsX, keyStates);
        generateWallsY(startX, contadorY, pathBorderWalls, numberWallsY, keyStates);
        startY = 932;
        startX = 1704;
        generateWallsX(startY, contadorX, pathBorderWalls, numberWallsX, keyStates);
        generateWallsY(startX, contadorY, pathBorderWalls, numberWallsY, keyStates);

        // Genera el terreno dentro del área del juego
        generateTerrain();

        // Genera paredes interiores adicionales para el diseño del nivel
        String pathWalls = "src/images/muroJuegoRol.jpg";
        startX = 420;
        numberWallsY = 8;
        generateWallsY(startX, contadorY, pathWalls, numberWallsY, keyStates);
        contadorY = 480;
        numberWallsY = 14;
        generateWallsY(startX, contadorY, pathWalls, numberWallsY, keyStates);

        startY = 320;
        contadorX = 452;
        numberWallsX = 11;
        generateWallsX(startY, contadorX, pathWalls, numberWallsX, keyStates);

        contadorX = 900;
        numberWallsX = 7;
        generateWallsX(startY, contadorX, pathWalls, numberWallsX, keyStates);

        contadorY = 132;
        startX = 996;
        numberWallsY = 23;
        generateWallsY(startX, contadorY, pathWalls, numberWallsY, keyStates);

        contadorX = startX;
        numberWallsX = 19;
        generateWallsX(startY, contadorX, pathWalls, numberWallsX, keyStates);
    }

    /**
     * Genera el terreno del área de juego utilizando un patrón específico.
     * El método crea un área de terreno dentro de las paredes establecidas, rellenando con imágenes de baldosas y estableciendo
     * los límites visuales y físicos del mapa.
     */
    public void generateTerrain() {
        int startY = 132;
        int contadorX = 232;
        int startX = 232;
        int contadorY = 164;
        String path = "src/images/dungeon/tile001.png";
        ArrayList<Rectangle> x = new ArrayList<>();

        for (int i = 0; i < 46; i++) {
            generateWallsX(startY, contadorX, path, 1, x);
            contadorX = contadorX + 32;

            for (int j = 0; j < 24; j++) {
                generateWallsY(startX, contadorY, path, 1, x);
                contadorY = contadorY + 32;
            }
            contadorY = 164;
            startX = startX + 32;
        }
    }

    /**
     * Genera una fila vertical de paredes en el área del juego.
     * @param startX Coordenada X de inicio.
     * @param contadorY Coordenada Y inicial.
     * @param path Ruta de la imagen que se utilizará para las paredes.
     * @param numberWallsY Número de paredes a generar en la fila vertical.
     * @param status Almacena los rectángulos correspondientes a las paredes generadas para el control de colisiones.
     */
    public void generateWallsY(int startX, int contadorY, String path, int numberWallsY, ArrayList<Rectangle> status) {
        for (int i = 0; i < numberWallsY; i++) {
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

    /**
     * Genera una fila horizontal de paredes en el área del juego.
     * @param startY Coordenada Y de inicio.
     * @param contadorX Coordenada X inicial.
     * @param path Ruta de la imagen que se utilizará para las paredes.
     * @param numberWallsX Número de paredes a generar en la fila horizontal.
     * @param status Almacena los rectángulos correspondientes a las paredes generadas para el control de colisiones.
     */
    public void generateWallsX(int startY, int contadorX, String path, int numberWallsX, ArrayList<Rectangle> status) {
        for (int i = 0; i < numberWallsX; i++) {
            JLabel block = new JLabel();
            block.setSize(32, 32);
            block.setLocation(contadorX, startY);
            ImageIcon blockImage = new ImageIcon(path);
            Image scaledImage = blockImage.getImage().getScaledInstance(block.getWidth(), block.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            block.setIcon(scaledIcon);
            gameArea.add(block);
            gameArea.setComponentZOrder(block, 0);
            contadorX = contadorX + 32;
            Rectangle bounds1 = block.getBounds();
            status.add(bounds1);
        }
    }

    /**
     * Configura el botón "Start" para la selección de nombre del personaje.
     * El botón se añade a la interfaz de selección y establece un listener para capturar el nombre introducido por el usuario.
     */
    public void setNextButton() {
        nextButton = new JButton("START");
        nextButton.setFont(new Font("Impact", Font.BOLD, 40));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(50, 205, 50));
        nextButton.setFocusPainted(false);
        nextButton.setSize(400, 75);
        nextButton.setLocation(panelMain.getWidth() / 2 - nextButton.getWidth() / 2, 600);
        selectName.add(nextButton, BorderLayout.CENTER);
        usuario = new Usuario();
        nextButton.addMouseListener(new ButtonMouseListenerName());
    }

    /**
     * Clase interna para manejar el evento de clic en el botón de nombre.
     * Establece el nombre del usuario y cambia el panel a la siguiente pantalla de selección de clase.
     */
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

    /**
     * Configura el botón "Start" que inicia el juego.
     * Añade el botón de inicio a la interfaz de selección de clases y establece un listener
     * para manejar el clic del usuario, cambiando el panel a la pantalla de selección de nombre.
     */
    public void setStartButton() {
        startButton = new JButton("START THE ADVENTURE");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(50, 205, 50));
        startButton.setFocusPainted(false);
        startButton.setSize(400, 125);
        startButton.setLocation(panelMain.getWidth() / 2 - startButton.getWidth() / 2, 700);
        startButton.addMouseListener(new ButtonMouseListenerStart(startButton, panelMain, selectName, frame));
        panelMain.add(startButton, BorderLayout.CENTER);
        panelMain.setComponentZOrder(startButton, 0);
    }

    /**
     * Muestra el título principal del juego en el panel inicial.
     * El título se posiciona y formatea en el centro del panel principal, con un tamaño y fuente
     * determinados, estableciendo el ambiente visual del juego.
     */
    public void showTitleGame() {
        String texto = "AN UNEXPLORED MAGICAL PLACE I";

        Dimension dimension = new Dimension(1200, 100);
        Point ubicacion = new Point((int) (panelMain.getWidth() / 2 - dimension.getWidth() / 2), 200);
        showTitles(texto, ubicacion, dimension, panelMain);
    }



    public static void main(String[] args) {
        new Main();
    }
    /**
     * Establece el fondo de un panel con la imagen proporcionada.
     *
     * @param panelElegido El panel al que se le va a añadir el fondo.
     * @param dimension    Dimensiones del fondo.
     * @param rutaFondo    Ruta de la imagen de fondo.
     */
    public static void chooseBackgrounds(JPanel panelElegido, Dimension dimension, String rutaFondo){
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(rutaFondo));
        backgroundLabel.setBounds(0, 0, dimension.width, dimension.height);
        panelElegido.add(backgroundLabel);
    }

    /**
     * Muestra un título en un panel especificado con el estilo y formato definidos.
     *
     * @param texto          El texto del título a mostrar.
     * @param ubicacion      Posición en el panel donde se colocará el título.
     * @param tamanyo        Tamaño del área del título.
     * @param panelElegido   Panel en el que se va a mostrar el título.
     */
    public static void showTitles(String texto, Point ubicacion, Dimension tamanyo, JPanel panelElegido){
        JTextArea titleText = new JTextArea(texto);
        Font font = new Font("Georgia", Font.PLAIN, 70);

        titleText.setFont(font);
        titleText.setEditable(false);  // Evita la edición del área de texto
        titleText.setSize(tamanyo);
        titleText.setLocation(ubicacion);
        titleText.setOpaque(false);  // Hace transparente el fondo del texto
        titleText.setForeground(new Color(255, 255, 255, 200));  // Color blanco con transparencia

        panelElegido.add(titleText);
        panelElegido.setComponentZOrder(titleText, 0);  // Asegura que el texto esté visible sobre otros elementos
    }

    /**
     * Muestra la selección de clases en el panel con un estilo de presentación.
     * Carga la imagen de la clase y la posiciona en el panel.
     *
     * @param label    Etiqueta en la que se cargará la imagen de la clase.
     * @param path     Ruta de la imagen de la clase.
     * @param point    Posición en la que se colocará la clase en el panel.
     * @param panel    Panel en el que se añadirá la clase.
     * @param tamanyo  Dimensiones de la etiqueta.
     */
    public void showSelectClasses(JLabel label, String path, Point point, JPanel panel, Dimension tamanyo){
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
