package com.mycompany.battleshipdinamico_22541052;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class VentanaJuego {
    
    private JFrame ventana;
    private Player player1;
    private Player player2;
    private char[][] tableroPlayer1;
    private char[][] tableroPlayer2;
    
    // impactos por barco
    private HashMap<Character, Integer> impactosP1; 
    private HashMap<Character, Integer> impactosP2;
    
    private JButton[][] botonesTableroDefensa;
    private JButton[][] botonesTableroAtaque;
    private boolean turnoPlayer1 = true;
    private JLabel lblTurno;
    private JLabel lblBarcosP1;
    private JLabel lblBarcosP2;
    private JPanel panelTableroDefensa;
    private JPanel panelTableroAtaque;
    
    public VentanaJuego(Player p1, Player p2, char[][] tableroP1, char[][] tableroP2) {
        this.player1 = p1;
        this.player2 = p2;
        this.tableroPlayer1 = copiarTablero(tableroP1);
        this.tableroPlayer2 = copiarTablero(tableroP2);
        this.botonesTableroDefensa = new JButton[8][8];
        this.botonesTableroAtaque = new JButton[8][8];
        
        // Inicializar tracking de impactos
        this.impactosP1 = new HashMap<>();
        this.impactosP2 = new HashMap<>();
        
        impactosP1.put('P', 0); // Portaaviones
        impactosP1.put('A', 0); // Acorazado
        impactosP1.put('S', 0); // Submarino
        impactosP1.put('D', 0); // Destructor
        
        impactosP2.put('P', 0);
        impactosP2.put('A', 0);
        impactosP2.put('S', 0);
        impactosP2.put('D', 0);
        
        crearVentana();
    }
    
    private char[][] copiarTablero(char[][] original) {
        char[][] copia = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copia[i][j] = original[i][j];
            }
        }
        return copia;
    }
    
    private int obtenerVidasBarco(char tipoBarco) {
        switch (tipoBarco) {
            case 'P': return 5; // Portaaviones
            case 'A': return 4; // Acorazado
            case 'S': return 3; // Submarino
            case 'D': return 2; // Destructor
            default: return 0;
        }
    }
    
    private void crearVentana() {
        ventana = new JFrame();
        ventana.setTitle("Battleship Dinámico");
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
        
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);
        Font fuenteInfo = new Font("Arial", Font.BOLD, 14);
        
        // Etiqueta de turno
        lblTurno = new JLabel("Turno de: " + player1.getUsername());
        lblTurno.setBounds(350, 20, 350, 30);
        lblTurno.setFont(fuenteTitulo);
        lblTurno.setForeground(Color.YELLOW);
        lblTurno.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblTurno);
        
        // Contador de barcos
        int barcosP1 = contarBarcosVivos(impactosP1);
        int barcosP2 = contarBarcosVivos(impactosP2);
        
        lblBarcosP1 = new JLabel(player1.getUsername() + ": " + barcosP1 + " barcos");
        lblBarcosP1.setBounds(100, 510, 300, 25);
        lblBarcosP1.setFont(fuenteInfo);
        lblBarcosP1.setForeground(Color.CYAN);
        panelFondo.add(lblBarcosP1);
        
        lblBarcosP2 = new JLabel(player2.getUsername() + ": " + barcosP2 + " barcos");
        lblBarcosP2.setBounds(600, 510, 300, 25);
        lblBarcosP2.setFont(fuenteInfo);
        lblBarcosP2.setForeground(Color.CYAN);
        panelFondo.add(lblBarcosP2);
        
        // Etiqueta tablero defensa
        JLabel lblDefensa = new JLabel("MI TABLERO");
        lblDefensa.setBounds(80, 60, 200, 25);
        lblDefensa.setFont(new Font("Arial", Font.BOLD, 14));
        lblDefensa.setForeground(Color.WHITE);
        panelFondo.add(lblDefensa);
        
        // Etiqueta tablero ataque
        JLabel lblAtaque = new JLabel("ATACAR ENEMIGO");
        lblAtaque.setBounds(550, 60, 200, 25);
        lblAtaque.setFont(new Font("Arial", Font.BOLD, 14));
        lblAtaque.setForeground(Color.WHITE);
        panelFondo.add(lblAtaque);
        
        // Panel tablero defensa (izquierda)
        panelTableroDefensa = new JPanel();
        panelTableroDefensa.setLayout(new GridLayout(8, 8, 2, 2));
        panelTableroDefensa.setBounds(50, 90, 400, 400);
        panelTableroDefensa.setOpaque(false);
        
        // Panel tablero ataque (derecha)
        panelTableroAtaque = new JPanel();
        panelTableroAtaque.setLayout(new GridLayout(8, 8, 2, 2));
        panelTableroAtaque.setBounds(520, 90, 400, 400);
        panelTableroAtaque.setOpaque(false);
        
        actualizarTableros();
        
        panelFondo.add(panelTableroDefensa);
        panelFondo.add(panelTableroAtaque);
        
        // Botón Rendirse (en medio abajo)
        JButton btnRendirse = new JButton("RENDIRSE");
        btnRendirse.setBounds(412, 600, 200, 50);
        btnRendirse.setFont(new Font("Arial", Font.BOLD, 16));
        btnRendirse.setBackground(new Color(139, 0, 0));
        btnRendirse.setForeground(Color.WHITE);
        btnRendirse.setFocusPainted(false);
        btnRendirse.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        btnRendirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rendirse();
            }
        });
        
        panelFondo.add(btnRendirse);
        
        ventana.setContentPane(panelFondo);
        ventana.setVisible(true);
    }
    
    private void actualizarTableros() {
        panelTableroDefensa.removeAll();
        panelTableroAtaque.removeAll();
        
        Player jugadorActual = turnoPlayer1 ? player1 : player2;
        char[][] tableroDefensa = turnoPlayer1 ? tableroPlayer1 : tableroPlayer2;
        
        String modoJuego = Battleship.getModoJuego();
        
        // Crear tablero de defensa (izquierda)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton boton = new JButton();
                boton.setFont(new Font("Arial", Font.BOLD, 16));
                boton.setBackground(new Color(70, 130, 180));
                boton.setForeground(Color.WHITE);
                boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                boton.setEnabled(false);
                
                char celda = tableroDefensa[i][j];
                
                if (modoJuego.equals("TUTORIAL")) {
                    if (celda == 'P' || celda == 'A' || celda == 'S' || celda == 'D') {
                        boton.setText(String.valueOf(celda));
                        boton.setBackground(new Color(34, 139, 34));
                    } else if (celda == 'X') {
                        boton.setText("X");
                        boton.setBackground(Color.RED);
                    } else {
                        boton.setText("~");
                    }
                } else {
                    if (celda == 'X') {
                        boton.setText("X");
                        boton.setBackground(Color.RED);
                    } else {
                        boton.setText("~");
                    }
                }
                
                botonesTableroDefensa[i][j] = boton;
                panelTableroDefensa.add(boton);
            }
        }
        
        // Crear tablero de ataque (derecha)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int fila = i;
                final int columna = j;
                
                JButton boton = new JButton("~");
                boton.setFont(new Font("Arial", Font.BOLD, 16));
                boton.setBackground(new Color(70, 130, 180));
                boton.setForeground(Color.WHITE);
                boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                boton.setFocusPainted(false);
                
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        atacar(fila, columna);
                    }
                });
                
                botonesTableroAtaque[i][j] = boton;
                panelTableroAtaque.add(boton);
            }
        }
        
        panelTableroDefensa.revalidate();
        panelTableroDefensa.repaint();
        panelTableroAtaque.revalidate();
        panelTableroAtaque.repaint();
        
        actualizarContadores();
    }
    
    private void atacar(int fila, int columna) {
        char[][] tableroEnemigo = turnoPlayer1 ? tableroPlayer2 : tableroPlayer1;
        HashMap<Character, Integer> impactosEnemigo = turnoPlayer1 ? impactosP2 : impactosP1;
        Player jugadorAtacante = turnoPlayer1 ? player1 : player2;
        Player jugadorDefensor = turnoPlayer1 ? player2 : player1;
        
        char celda = tableroEnemigo[fila][columna];
        
        if (celda == '~') {
            // Fallo
            JOptionPane.showMessageDialog(ventana, "¡Agua! Fallaste el tiro");
            botonesTableroAtaque[fila][columna].setText("F");
            botonesTableroAtaque[fila][columna].setBackground(Color.GRAY);
            botonesTableroAtaque[fila][columna].setEnabled(false);
            
            cambiarTurno();
        } else if (celda == 'P' || celda == 'A' || celda == 'S' || celda == 'D') {
            // Impacto
            char tipoBarco = celda;
            
            // Marcar el impacto en el tablero
            tableroEnemigo[fila][columna] = 'X';
            
            // Incrementar contador de impactos para este barco
            int impactosActuales = impactosEnemigo.get(tipoBarco);
            impactosActuales++;
            impactosEnemigo.put(tipoBarco, impactosActuales);
            
            int vidasTotales = obtenerVidasBarco(tipoBarco);
            int vidasRestantes = vidasTotales - impactosActuales;
            
            String nombreBarco = Battleship.obtenerNombreBarco(tipoBarco);
            String codigoBarco = obtenerCodigoBarco(tipoBarco);
            
            // Mostrar mensaje de impacto
            JOptionPane.showMessageDialog(ventana, 
                "¡IMPACTO EN " + codigoBarco + "!\n" +
                "Le diste a un " + nombreBarco + "!\n" +
                "Impactos: " + impactosActuales + "/" + vidasTotales + "\n" +
                "Quedan " + vidasRestantes + " impactos para hundirlo");
            
            botonesTableroAtaque[fila][columna].setText("X");
            botonesTableroAtaque[fila][columna].setBackground(Color.RED);
            botonesTableroAtaque[fila][columna].setEnabled(false);
            
            // Verificar si el barco está hundido
            if (impactosActuales >= vidasTotales) {
                JOptionPane.showMessageDialog(ventana, 
                    "¡HUNDISTE EL " + codigoBarco + "!\n" +
                    "¡El " + nombreBarco.toUpperCase() + " de " + 
                    jugadorDefensor.getUsername() + " ha sido destruido!");
            }
            
            // Actualizar contadores
            actualizarContadores();
            
            // Verificar si ganó
            int barcosRestantes = contarBarcosVivos(impactosEnemigo);
            
            if (barcosRestantes == 0) {
                finalizarJuego(jugadorAtacante, jugadorDefensor, false);
                return;
            }
            
            // REGENERAR EL TABLERO DEL ENEMIGO (manteniendo los impactos)
            JOptionPane.showMessageDialog(ventana, 
                "¡Los barcos de " + jugadorDefensor.getUsername() + 
                " se están regenerando aleatoriamente!");
            
            if (turnoPlayer1) {
                tableroPlayer2 = Battleship.regenerarTableroConImpactos(tableroPlayer2, impactosP2);
            } else {
                tableroPlayer1 = Battleship.regenerarTableroConImpactos(tableroPlayer1, impactosP1);
            }
            
            cambiarTurno();
        } else if (celda == 'X') {
            JOptionPane.showMessageDialog(ventana, "Ya atacaste esta posición");
        }
    }
    
    private int contarBarcosVivos(HashMap<Character, Integer> impactos) {
        int barcosVivos = 0;
        
        // Portaaviones (5 vidas)
        if (impactos.get('P') < 5) barcosVivos++;
        
        // Acorazado (4 vidas)
        if (impactos.get('A') < 4) barcosVivos++;
        
        // Submarino (3 vidas)
        if (impactos.get('S') < 3) barcosVivos++;
        
        // Destructor (2 vidas)
        if (impactos.get('D') < 2) barcosVivos++;
        
        return barcosVivos;
    }
    
    private String obtenerCodigoBarco(char tipo) {
        switch (tipo) {
            case 'P': return "PA";
            case 'A': return "AZ";
            case 'S': return "SM";
            case 'D': return "DT";
            default: return "";
        }
    }
    
    private void rendirse() {
        Player jugadorActual = turnoPlayer1 ? player1 : player2;
        Player jugadorContrario = turnoPlayer1 ? player2 : player1;
        
        int confirmacion = JOptionPane.showConfirmDialog(ventana,
                "¿" + jugadorActual.getUsername() + ", estás seguro de que quieres rendirte?\n" +
                jugadorContrario.getUsername() + " ganará la partida.",
                "Confirmar Rendición",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            finalizarJuego(jugadorContrario, jugadorActual, true);
        }
    }
    
    private void finalizarJuego(Player ganador, Player perdedor, boolean porRendicion) {
        String mensaje;
        String resultado;
        
        if (porRendicion) {
            mensaje = "¡" + ganador.getUsername() + " HA GANADO!\n\n" +
                     perdedor.getUsername() + " se retiró del juego.";
            resultado = ganador.getUsername() + " ganó porque " + 
                       perdedor.getUsername() + " se retiró en modo " + 
                       Battleship.getDificultad();
        } else {
            mensaje = "¡" + ganador.getUsername() + " HA GANADO LA PARTIDA!\n\n" +
                     "Todos los barcos de " + perdedor.getUsername() + 
                     " han sido hundidos!";
            resultado = ganador.getUsername() + " hundió todos los barcos de " + 
                       perdedor.getUsername() + " en modo " + 
                       Battleship.getDificultad();
        }
        
        JOptionPane.showMessageDialog(ventana, mensaje);
        
        ganador.sumarPuntos(3);
        
        ganador.agregarPartida(resultado);
        perdedor.agregarPartida(resultado);
        
        new MenuPrincipal();
        ventana.dispose();
    }
    
    private void cambiarTurno() {
        
        // Cerrar la ventana antes de mostrar el mensaje
        ventana.setVisible(false);
    
        turnoPlayer1 = !turnoPlayer1;
        Player jugadorActual = turnoPlayer1 ? player1 : player2;
    
        // Mostrar mensaje de cambio de turno con la ventana cerrada
        JOptionPane.showMessageDialog(null, 
            "Turno de " + jugadorActual.getUsername() + "\nPresiona OK para continuar",
            "Cambio de Turno",
            JOptionPane.INFORMATION_MESSAGE);
    
        // Actualizar el label y los tableros
        lblTurno.setText("Turno de: " + jugadorActual.getUsername());
        actualizarTableros();
        
        // Volver a mostrar la ventana después de que presione OK
        ventana.setVisible(true);
    }
    
    private void actualizarContadores() {
        int barcosP1 = contarBarcosVivos(impactosP1);
        int barcosP2 = contarBarcosVivos(impactosP2);
        
        lblBarcosP1.setText(player1.getUsername() + ": " + barcosP1 + " barcos");
        lblBarcosP2.setText(player2.getUsername() + ": " + barcosP2 + " barcos");
    }
}