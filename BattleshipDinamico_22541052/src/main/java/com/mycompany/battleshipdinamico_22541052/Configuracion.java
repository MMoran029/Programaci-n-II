package com.mycompany.battleshipdinamico_22541052;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Configuracion {
    
    private JFrame ventana;
    private JLabel lblDificultadActual;
    private JLabel lblModoActual;
    
    public Configuracion() {
        crearVentana();
    }
    
    private void crearVentana() {
        ventana = new JFrame();
        ventana.setTitle("Configuración");
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
        
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 24);
        Font fuenteInfo = new Font("Arial", Font.BOLD, 16);
        
        //  Titulo
        JLabel lblTitulo = new JLabel("CONFIGURACIÓN");
        lblTitulo.setBounds(300, 80, 450, 40);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblTitulo);
        
        // Mostrar configuracion actual   Dificultad
        lblDificultadActual = new JLabel("Dificultad actual: " + Battleship.getDificultad());
        lblDificultadActual.setBounds(300, 150, 450, 30);
        lblDificultadActual.setFont(fuenteInfo);
        lblDificultadActual.setForeground(Color.CYAN);
        lblDificultadActual.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblDificultadActual);
        
        // Mostrar configuracion actual   Modo
        lblModoActual = new JLabel("Modo de Juego actual: " + Battleship.getModoJuego());
        lblModoActual.setBounds(300, 190, 450, 30);
        lblModoActual.setFont(fuenteInfo);
        lblModoActual.setForeground(Color.CYAN);
        lblModoActual.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblModoActual);
        
        // BDificultad
        ImageIcon IBDificultad = new ImageIcon("src/img/BDificultad.png");
        JButton BDificultad = new JButton(IBDificultad);
        BDificultad.setBounds(347, 250, 330, 95);
        BDificultad.setBorderPainted(false);
        BDificultad.setContentAreaFilled(false);
        BDificultad.setFocusPainted(false);
        
        BDificultad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarDificultad();
            }
        });
        
        panelFondo.add(BDificultad);
        
        // BModo de juego
        ImageIcon IBModoJuego = new ImageIcon("src/img/BModoJuego.png");
        JButton BModoJuego = new JButton(IBModoJuego);
        BModoJuego.setBounds(347, 360, 330, 95);
        BModoJuego.setBorderPainted(false);
        BModoJuego.setContentAreaFilled(false);
        BModoJuego.setFocusPainted(false);
        
        BModoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarModoJuego();
            }
        });
        
        panelFondo.add(BModoJuego);
        
        // BVolver
        ImageIcon IBVolver = new ImageIcon("src/img/BVolver.png");
        JButton BVolver = new JButton(IBVolver);
        BVolver.setBounds(60, 535, 300, 100);
        BVolver.setBorderPainted(false);
        BVolver.setContentAreaFilled(false);
        BVolver.setFocusPainted(false);
        
        BVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipal();
                ventana.dispose();
            }
        });
        
        panelFondo.add(BVolver);
        
        ventana.setContentPane(panelFondo);
        ventana.setVisible(true);
    }
    
    private void cambiarDificultad() {
        String[] opciones = {"EASY - 5 barcos", "NORMAL - 4 barcos", "EXPERT - 2 barcos", "GENIUS - 1 barco"};
        
        int seleccion = JOptionPane.showOptionDialog(ventana,
                "Selecciona la dificultad del juego:\n\n" +
                "• EASY: 5 barcos por jugador\n" +
                "• NORMAL: 4 barcos por jugador\n" +
                "• EXPERT: 2 barcos por jugador\n" +
                "• GENIUS: 1 barco por jugador",
                "Cambiar Dificultad",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[1]); 
        
        if (seleccion != -1) {
            String nuevaDificultad = "";
            switch (seleccion) {
                case 0:
                    nuevaDificultad = "EASY";
                    break;
                case 1:
                    nuevaDificultad = "NORMAL";
                    break;
                case 2:
                    nuevaDificultad = "EXPERT";
                    break;
                case 3:
                    nuevaDificultad = "GENIUS";
                    break;
            }
            
            Battleship.setDificultad(nuevaDificultad);
            lblDificultadActual.setText("Dificultad actual: " + nuevaDificultad);
            
            JOptionPane.showMessageDialog(ventana, 
                "Dificultad cambiada a: " + nuevaDificultad + "\n" +
                "Cantidad de barcos: " + Battleship.getCantidadBarcos());
        }
    }
    
    private void cambiarModoJuego() {
        String[] opciones = {"TUTORIAL - Barcos visibles", "ARCADE - Barcos ocultos"};
        
        int seleccion = JOptionPane.showOptionDialog(ventana,
                "Selecciona el modo de juego:\n\n" +
                " TUTORIAL: Muestra todos los barcos en tu tablero\n" +
                " ARCADE: Oculta los barcos (solo ves los impactos)",
                "Cambiar Modo de Juego",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]); 
        
        if (seleccion != -1) {
            String nuevoModo = seleccion == 0 ? "TUTORIAL" : "ARCADE";
            
            Battleship.setModoJuego(nuevoModo);
            lblModoActual.setText("Modo de Juego actual: " + nuevoModo);
            
            JOptionPane.showMessageDialog(ventana, 
                "Modo de juego cambiado a: " + nuevoModo);
        }
    }
}