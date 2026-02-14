package com.mycompany.battleshipdinamico_22541052;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class ColocarBarcos {
    
    private JFrame ventana;
    private JButton[][] botonesTablero;
    private char[][] tablero;
    private Player jugador;
    private Player jugadorContrario;
    private char[][] tableroJugador;
    private char[][] tableroContrario;
    private boolean esPlayer1;
    private String[] tiposBarcos = {"PA","AZ","SM","DT"};
    private int[] tamañosBarcos = {5,4,3,2};
    private int barcoActual =0;
    private int barcosColocados = 0;
    private int totalBarcos;
    private JLabel lblInstrucciones;
    
    // player 1
    public ColocarBarcos(Player jugador, Player jugadorContrario) {
        this.jugador = jugador;
        this.jugadorContrario = jugadorContrario;
        this.tablero = new char[8][8];
        this.botonesTablero = new JButton[8][8];
        this.totalBarcos = Battleship.getCantidadBarcos();
        this.esPlayer1 = true;
        this.tableroContrario = null;
        
        inicializarTablero();
        crearVentana();
    }
    
    // player 2
    public ColocarBarcos(Player jugador, Player jugadorContrario, char[][] tableroPlayer1) {
        this.jugador = jugador;
        this.jugadorContrario = jugadorContrario;
        this.tablero = new char[8][8];
        this.botonesTablero = new JButton[8][8];
        this.totalBarcos = Battleship.getCantidadBarcos();
        this.esPlayer1 = false;
        this.tableroContrario = tableroPlayer1; // Guardar el tablero del Player 1
        
        inicializarTablero();
        crearVentana();
    }
    
    private void inicializarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = '~';
            }
        }
    }
    
    private void crearVentana() {
        ventana = new JFrame();
        ventana.setTitle("Colocar Barcos - " + jugador.getUsername());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setSize(1024, 720);
        
        ImageIcon icono = new ImageIcon("src/img/Logo.png");
        ventana.setIconImage(icono.getImage());
        
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon("src/img/Menu.png");
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        panelFondo.setLayout(null);
        
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 20);
        Font fuenteInstrucciones = new Font("Arial", Font.BOLD, 16);
        
        // titulo
        JLabel lblTitulo = new JLabel("Colocando Barcos - " + jugador.getUsername());
        lblTitulo.setBounds(300, 30, 450, 30);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblTitulo);
        
        //  Instrucciones
        lblInstrucciones = new JLabel(obtenerInstruccion());
        lblInstrucciones.setBounds(200, 70, 650, 30);
        lblInstrucciones.setFont(fuenteInstrucciones);
        lblInstrucciones.setForeground(Color.YELLOW);
        lblInstrucciones.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblInstrucciones);
        
        // tablero 8x8
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(8, 8, 2, 2));
        panelTablero.setBounds(262, 120, 500, 500);
        panelTablero.setOpaque(false);
        
        //  botones del tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int fila = i;
                final int columna = j;
                
                JButton boton = new JButton("~");
                boton.setFont(new Font("Arial", Font.BOLD, 20));
                boton.setBackground(new Color(70, 130, 180));
                boton.setForeground(Color.WHITE);
                boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                boton.setFocusPainted(false);
                
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        colocarBarco(fila, columna);
                    }
                });
                
                botonesTablero[i][j] = boton;
                panelTablero.add(boton);
            }
        }
        
        panelFondo.add(panelTablero);
        
        ventana.setContentPane(panelFondo);
        ventana.setVisible(true);
    }
    
    private String obtenerInstruccion() {
        if (barcosColocados >= totalBarcos) {
            return "¡Todos los barcos colocados! Presiona OK para continuar";
        }
        
        String tipoBarco = obtenerNombreBarco(tiposBarcos[barcoActual]);
        int tamaño = tamañosBarcos[barcoActual];
        
        return "Coloca " + tipoBarco + " (" + tiposBarcos[barcoActual] + ") - Tamaño: " + tamaño + " casillas";
    }
    
    private String obtenerNombreBarco(String codigo) {
        switch (codigo) {
            case "PA": return "Portaaviones";
            case "AZ": return "Acorazado";
            case "SM": return "Submarino";
            case "DT": return "Destructor";
            default: return "";
        }
    }
    
        private void colocarBarco(int fila, int columna) {
        if (barcosColocados >= totalBarcos) {
            JOptionPane.showMessageDialog(ventana, "Ya has colocado todos tus barcos.\nPresiona OK para continuar.");
        
            // Continuar automaticamente
            ventana.dispose();
        
            if (esPlayer1) {
                // cambio de turno para colocar
                JOptionPane.showMessageDialog(null, 
                    jugadorContrario.getUsername() + ", es tu turno de colocar los barcos");
                new ColocarBarcos(jugadorContrario, jugador, tablero);
            } 
            else {
                //  iniciar el juego
                JOptionPane.showMessageDialog(null, 
                    "¡Ambos jugadores listos! El juego comenzará");
                new VentanaJuego(jugadorContrario, jugador, tableroContrario, tablero);
            }
        
            return;
        }
    
        String tipoBarco = tiposBarcos[barcoActual];
        int tamaño = tamañosBarcos[barcoActual];
    
        // Preguntar orientacion de los barcos
        String[] opciones = {"Horizontal", "Vertical"};
        int orientacion = JOptionPane.showOptionDialog(ventana,
            "¿Cómo quieres colocar el barco?",
            "Orientación",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]);
    
        if (orientacion == -1) return;
    
        boolean esHorizontal = (orientacion == 0);
    
        // ver que el barco entre en la tabla
        if (esHorizontal) {
            if (columna + tamaño > 8) {
                JOptionPane.showMessageDialog(ventana, "El barco no cabe horizontalmente desde esta posición");
                return;
            }
        
            // ver que no haya otro barco
            for (int j = columna; j < columna + tamaño; j++) {
                if (tablero[fila][j] != '~') {
                    JOptionPane.showMessageDialog(ventana, "Ya hay un barco en esa posición");
                    return;
                }
            }
        
            // Colocar el barco
            for (int j = columna; j < columna + tamaño; j++) {
            tablero[fila][j] = tipoBarco.charAt(0);
            botonesTablero[fila][j].setText(tipoBarco);
            botonesTablero[fila][j].setBackground(new Color(34, 139, 34));
            }
        } 
        else {
            if (fila + tamaño > 8) {
                JOptionPane.showMessageDialog(ventana, "El barco no cabe verticalmente desde esta posición");
                return;
            }
        
            // ver que no haya otro barco
            for (int i = fila; i < fila + tamaño; i++) {
                if (tablero[i][columna] != '~') {
                    JOptionPane.showMessageDialog(ventana, "Ya hay un barco en esa posición");
                    return;
                }
            }
        
            // Colocar el barco
            for (int i = fila; i < fila + tamaño; i++) {
                tablero[i][columna] = tipoBarco.charAt(0);
                botonesTablero[i][columna].setText(tipoBarco);
                botonesTablero[i][columna].setBackground(new Color(34, 139, 34));
            }
        }
    
        barcosColocados++;
    
        // ir al siguiente barco
        if (barcosColocados < totalBarcos) {
            barcoActual = (barcoActual + 1) % 4;
        
            // En modo EASY se puede repetir el Destructor
            if (barcosColocados == 4 && Battleship.getDificultad().equals("EASY")) {
                barcoActual = 3;
            }
        } 
        else {
            // Todos los barcos colocados - mostrar confirmación
            int respuesta = JOptionPane.showConfirmDialog(ventana,
                    "¡Todos los barcos colocados! ¿Continuar?",
                    "Barcos Listos",
                    JOptionPane.OK_CANCEL_OPTION);
        
            ventana.dispose();
        
            if (esPlayer1) {
            // P1 termino, ahora le toca al P2
            JOptionPane.showMessageDialog(null, 
                jugadorContrario.getUsername() + ", es tu turno de colocar los barcos");
            new ColocarBarcos(jugadorContrario, jugador, tablero);
            }
            else {
                // P2 termino, iniciar el juego
                JOptionPane.showMessageDialog(null, 
                    "¡Ambos jugadores listos! El juego comenzará");
                new VentanaJuego(jugadorContrario, jugador, tableroContrario, tablero);
            }
        }
    
        lblInstrucciones.setText(obtenerInstruccion());
    }
}