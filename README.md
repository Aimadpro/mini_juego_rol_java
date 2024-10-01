🎮 RPG Adventure Game
¡Bienvenido a RPG Adventure Game! 🎉 Este es un juego de rol basado en Java con una interfaz gráfica interactiva, donde los jugadores pueden elegir entre diferentes clases de personajes y explorar un mundo mágico lleno de aventuras. 🧙‍♂️⚔️🗺️

🚀 Características
🖼️ Interfaz Gráfica: Diseño de pantalla completo en 1920x1080 con fondos personalizados.
🎭 Selección de Personaje: Elige entre diferentes clases (Mago, Guerrero, Sacerdote).
🏰 Mapas y Paredes Generadas: Mapas dinámicos generados con diferentes terrenos y obstáculos.
📜 Manejo de Eventos: Listeners para detectar la selección de clases, botones de inicio y más.
🔥 Gráficos Mejorados: Imágenes escaladas y ajustadas para un juego visualmente atractivo.
🛠️ Instalación y Ejecución

📁 Clona el repositorio:

git clone https://github.com/tu-usuario/RPG-Adventure-Game.git

🧩 Importa el proyecto a tu IDE favorito (IntelliJ, Eclipse, NetBeans, etc.).

🔧 Configura las dependencias si es necesario (asegúrate de tener configurado el SDK de Java).

▶️ Ejecuta la clase Main.

🎮 Cómo jugar
Inicio: Al iniciar el juego, se mostrará el título principal.
Selecciona tu Clase: Puedes elegir entre diferentes clases (Mago, Guerrero, Sacerdote).
Selecciona un Nombre: Ingresa el nombre de tu personaje para empezar.
Controles: Usa las flechas del teclado
Reúne las 10 monedas y desbloquearás el portal de salida!
¡Empieza la aventura! 🎲🚪


👨‍💻 Tecnologías Utilizadas
1. Java (JDK 8 o superior) ☕
Java es el lenguaje de programación principal de este proyecto. Proporciona un entorno robusto y multiplataforma para desarrollar aplicaciones interactivas y orientadas a objetos. Se utiliza tanto para la lógica del juego como para la gestión de eventos y la interfaz gráfica.

POO (Programación Orientada a Objetos): Uso intensivo de clases para representar las entidades del juego, como personajes, áreas de juego, y controladores.
Colecciones: Como ArrayList para manejar elementos dinámicos como las posiciones de las paredes o personajes seleccionados.
2. Swing para la Interfaz Gráfica 🎨
Swing es una biblioteca de Java que permite la creación de interfaces gráficas de usuario (GUI) con componentes predefinidos como botones, áreas de texto y paneles. En este proyecto, se usa para diseñar toda la estructura visual del juego.
  JFrame: La ventana principal que contiene todos los elementos del juego.
  JPanel: Usado para agrupar las áreas como la pantalla de selección de clase, nombre y área de juego.
  JTextArea y JLabel: Para mostrar el título del juego, descripciones y mensajes interactivos.
  
JButton: Implementado para los botones de interacción, como "Start the Adventure" y "Select Class".
3. Event Listeners 🖱️
Los MouseListener y KeyListener de Java son fundamentales para interactuar con el juego, capturando eventos de teclado y ratón para activar comportamientos como cambiar de panel, seleccionar un personaje o iniciar el juego.
  ButtonMouseListenerStart: Detecta cuando se hace clic en el botón de inicio para cambiar de la pantalla principal a la de selección de personaje.
  ButtonMouseListenerSelectedClass: Maneja la selección de la clase del personaje y la cambia según el clic en la imagen.
  ButtonMouseListenerName: Se utiliza para almacenar el nombre del personaje que el usuario ingresa y avanza a la siguiente pantalla.
  
4. Gestión de Imágenes y Recursos 🖼️
El juego utiliza una variedad de imágenes para representar personajes, fondos y elementos del mapa. Se utiliza ImageIcon y técnicas de escalado (Image.SCALE_SMOOTH) para ajustar las imágenes a los tamaños específicos.
  Imágenes de Fondo: Cada pantalla (inicio, selección de clase y área de juego) tiene un fondo único.
  Sprites de Personajes: Cada personaje (Mago, Sacerdote, Guerrero) tiene su propio sprite que se utiliza para representarlo visualmente en la interfaz.
  Títulos y Textos: Se crean utilizando JTextArea con fuentes personalizadas (Georgia, Impact, etc.) y colores ajustados para mejorar la legibilidad.

6. Gestión de Paneles y Componentes 🗂️
El uso de JPanel permite organizar diferentes áreas de la aplicación de manera modular:
  Panel Principal (panelMain): Contiene la pantalla inicial con el botón de "Start the Adventure".
  Panel de Selección de Clase (selectClassArea): Permite elegir entre diferentes clases de personaje.
  Panel de Selección de Nombre (selectName): Área donde el jugador ingresa su nombre.
  Área de Juego (gameArea): Contiene el mapa generado dinámicamente con paredes y terrenos.

7. Mapeo y Generación Dinámica de Terreno 🌍
La generación de paredes y terrenos en el área de juego se hace mediante la colocación de JLabel en posiciones calculadas dinámicamente.
  Paredes: Se generan usando la función generateWallsY y generateWallsX, que coloca JLabel en un patrón específico basado en la estructura de los mapas RPG clásicos.
  Terreno: El uso de baldosas (tiles) con generateTerrain permite llenar el área de juego con un patrón de suelo, creando una apariencia consistente.

🧙 Clases de Personaje Disponibles
🧙‍♂️ Mago: Especialista en hechizos y magia.
✝️ Sacerdote: Apoya con habilidades de curación y protección.
🛡️ Guerrero: Fuerte en combate cuerpo a cuerpo con gran defensa.
🚧 Mejoras Futuras
🎨 Agregar más clases de personajes y habilidades.
🏰 Expansión del mapa y nuevas áreas explorables.
🎵 Implementación de efectos de sonido y música de fondo.
🤖 Inclusión de enemigos y sistema de combate.
🤝 Contribuciones
¡Las contribuciones son bienvenidas! Si tienes ideas para mejorar el juego o quieres agregar nuevas funcionalidades, siéntete libre de abrir un issue o hacer un pull request. 💡🙌

📜 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo LICENSE para más detalles.

✉️ Contacto

Para cualquier consulta o sugerencia, no dudes en ponerte en contacto:

Nombre: Aimad Aisa Driouchi Correo: aaisad2324@politecnics.barcelona Mi Perfil de LinkedIn
