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
import javax.swing.JTextField;

public class Jugador2 {
    
    public Jugador2(){
        
        JFrame ventana = new JFrame();
        ventana.setTitle("Seleccionar Jugador 2");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setSize(1024,720);
        
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
        Font fuenteLabel = new Font("Arial", Font.BOLD, 18);
        Font fuenteTexto = new Font("Arial", Font.PLAIN, 16);
        
        // titulo
        JLabel lblTitulo = new JLabel("BATTLESHIP DINÁMICO");
        lblTitulo.setBounds(300, 100, 450, 40);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelFondo.add(lblTitulo);
        
        // Mostrar player 1
        Player player1 = Battleship.getCurrentUser();
        JLabel lblPlayer1 = new JLabel("Player 1: " + player1.getUsername());
        lblPlayer1.setBounds(300, 180, 400, 30);
        lblPlayer1.setFont(fuenteLabel);
        lblPlayer1.setForeground(Color.CYAN);
        panelFondo.add(lblPlayer1);
        
        // Etiqueta para player 2
        JLabel lblPlayer2 = new JLabel("Ingrese el username del Player 2:");
        lblPlayer2.setBounds(250, 250, 500, 30);
        lblPlayer2.setFont(fuenteLabel);
        lblPlayer2.setForeground(Color.WHITE);
        panelFondo.add(lblPlayer2);
        
        // Campo de texto para player 2
        JTextField txtPlayer2 = new JTextField();
        txtPlayer2.setBounds(250, 290, 500, 40);
        txtPlayer2.setFont(fuenteTexto);
        panelFondo.add(txtPlayer2);
        
        // BContinuar
        ImageIcon IBContinuar = new ImageIcon("src/img/BSiguiente.png");
        JButton BContinuar = new JButton(IBContinuar);
        BContinuar.setBounds(660, 535, 300, 100);
        BContinuar.setBorderPainted(false);
        BContinuar.setContentAreaFilled(false);
        BContinuar.setFocusPainted(false);
        
        BContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernamePlayer2 = txtPlayer2.getText().trim();
        
                if(usernamePlayer2.isEmpty()){
                    JOptionPane.showMessageDialog(ventana, "Por favor ingrese el username del Player 2");
                }       
                else if(usernamePlayer2.equalsIgnoreCase(player1.getUsername())){
                    JOptionPane.showMessageDialog(ventana, 
                        "No puedes jugar contra ti mismo. Ingresa otro jugador.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    txtPlayer2.setText("");
                }       
                else {
                    // Buscar si el jugador existe
                    Player player2 = Battleship.buscarJugador(usernamePlayer2);
            
                    if(player2 == null){
                        int opcion = JOptionPane.showConfirmDialog(ventana, 
                            "El jugador '" + usernamePlayer2 + "' no existe.\n¿Desea intentar de nuevo?", 
                            "Jugador no encontrado", 
                            JOptionPane.YES_NO_OPTION);
                        
                        if(opcion == JOptionPane.YES_OPTION){
                            txtPlayer2.setText("");
                        }       
                        else {
                            // volver al menu principal
                            new MenuPrincipal();
                            ventana.dispose();
                        }
                    }       
                    else {
                
                        // Jugador encontrado, iniciar colocación de barcos
                        JOptionPane.showMessageDialog(ventana, 
                            "¡Jugador encontrado!\n" + 
                            "Player 1: " + player1.getUsername() + "\n" + 
                            "Player 2: " + player2.getUsername() + "\n\n" +
                            "Player 1 comenzará colocando sus barcos");

                        new ColocarBarcos(player1, player2);
                        ventana.dispose();
                
                    }
                }
            }
        });
        
        panelFondo.add(BContinuar);
        
        // BVolve
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
}