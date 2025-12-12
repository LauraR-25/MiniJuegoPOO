🏁 Carrera de Buses: Coca-Cola vs Pepsi

🎮 Descripción del Juego
Un mini juego de carreras desarrollado en Java para la materia de Programación Orientada a Objetos (POO), donde dos jugadores compiten para llegar primero a la meta pulsando sus respectivos botones lo más rápido posible.

🎯 Temática
Competencia de marcas icónicas: Coca-Cola (autobús rojo) vs Pepsi (autobús azul) en una emocionante carrera sobre una pista profesional.

👥 Jugadores
Jugador Coca-Cola: Controla el autobús rojo
Jugador Pepsi: Controla el autobús azul

🎪 Características Principales
- Interfaz gráfica intuitiva y colorida
- Sistema de progreso basado en pulsaciones (0-100%)
- Detección precisa de llegada a meta
- Animaciones suaves de movimiento
- Sistema de puntuación con contador de tiempo
- Gestión de nombres de jugadores personalizables

🕹️ Instrucciones de Juego
Pantalla de Inicio
1. Ingresa los nombres de los jugadores (opcional)
2. Presiona el botón "INICIAR CARRERA" o la tecla ENTER

Durante la Carrera
- Jugador Coca-Cola: Presiona BARRA ESPACIADORA repetidamente 
- Jugador Pepsi: Presiona ENTER repetidamente 
- Objetivo: Llegar primero a las 100 pulsaciones

Controles
    Función	   │  Tecla	  │            Descripción
   Coca-Cola   │ ESPACIO  │ 	  Avanza el autobús rojo
     Pepsi	   │  ENTER	  │       Avanza el autobús azul
Volver al menú │   ESC	  │   Regresa a pantalla de selección

🏗️ Arquitectura del Proyecto
Estructura de Paquetes

MiniJuegoPOO/
├── main/
│   └── Main.java              # Punto de entrada
├── vista/
│   ├── VentanaPrincipal.java  # Ventana principal
│   ├── PanelSeleccion.java    # Panel de configuración
│   └── PanelCarrera.java      # Panel de juego
├── controlador/
│   ├── ControladorJuego.java  # Lógica principal
│   └── ControladorBotones.java
└── modelo/
    ├── Juego.java             # Estado del juego
    ├── Jugador.java           # Información jugador
    ├── Bus.java               # Representación del autobús
    └── Meta.java              # Línea de meta

Clases Principales
- Juego: Gestiona estado, jugadores y lógica de carrera 
- Jugador: Almacena nombre, estadísticas y bus asignado 
- Bus: Controla posición, velocidad y progreso 
- PanelCarrera: Renderiza gráficos y animaciones 
- ControladorJuego: Maneja eventos y flujo del juego

🔧 Tecnologías Utilizadas
- Lenguaje: Java 8+ 
- GUI: Swing/AWT 
- Paradigma: Programación Orientada a Objetos (POO)
- Patrones: MVC (Modelo-Vista-Controlador)

🚀 Compilación y Ejecución
# Compilar
javac main/Main.java
# Ejecutar
java main.Main

📊 Reglas del Juego
1. Cada pulsación aumenta el progreso del autobús 
2. El primer autobús en alcanzar 100% de progreso gana 
3. El tiempo se mide en segundos reales 
4. Se registran las pulsaciones totales de cada jugador 
5. Al terminar, se muestran estadísticas y opción para nueva carrera

🎨 Elementos Visuales
- Autobuses: Diseños diferenciados por color 
- Pista: Con líneas de carril y bordes amarillos 
- Meta: Línea roja punteada con bandera de cuadros 
- Información: Tiempo y pulsaciones centradas 
- Efectos: Estrella dorada para el ganador

🎯 Objetivos de Aprendizaje POO
- Implementación de herencia y polimorfismo 
- Uso de encapsulamiento en modelos 
- Aplicación del patrón MVC 
- Manejo de eventos y hilos 
- Diseño de interfaces gráficas 
- Gestión de estado de la aplicación

