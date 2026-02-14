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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
    
    public Login(){
        
        JFrame ventana = new JFrame();
        ventana.setTitle("Login");
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
        
        Font fuenteLabel = new Font("Arial", Font.BOLD, 18);
        Font fuenteTexto = new Font("Arial", Font.PLAIN, 16);
        
        //   nombre de usuario
        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setBounds(200, 200, 250, 30);
        lblUsuario.setFont(fuenteLabel);
        lblUsuario.setForeground(Color.WHITE);
        panelFondo.add(lblUsuario);
        
        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 240, 400, 40);
        txtUsuario.setFont(fuenteTexto);
        panelFondo.add(txtUsuario);
        
        // la contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(200, 300, 200, 30);
        lblPassword.setFont(fuenteLabel);
        lblPassword.setForeground(Color.WHITE);
        panelFondo.add(lblPassword);
        
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 340, 400, 40);
        txtPassword.setFont(fuenteTexto);
        panelFondo.add(txtPassword);
        
        // BIngresar
        ImageIcon IBIngresar = new ImageIcon("src/img/BSiguiente.png");
        JButton BIngresar = new JButton(IBIngresar);
        BIngresar.setBounds(660, 535, 300, 100);
        BIngresar.setBorderPainted(false);
        BIngresar.setContentAreaFilled(false);
        BIngresar.setFocusPainted(false);
        
        BIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword());
        
            if(usuario.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Por favor complete todos los campos");
            }   
            else {
                //   Verificar logi
                boolean loginExitoso = Battleship.login(usuario, password);
            
                if(loginExitoso){
                    JOptionPane.showMessageDialog(ventana, "Bienvenido " + usuario + "!");
                
                    // volver
                    new MenuPrincipal();
                    ventana.dispose();
                }   
                else {
                    JOptionPane.showMessageDialog(ventana, 
                        "Usuario o contraseña incorrectos", 
                        "Error de Login", 
                        JOptionPane.ERROR_MESSAGE);
                
                    // Limpiar espacios
                    txtPassword.setText("");
                }
            }
            }
        });
        
        panelFondo.add(BIngresar);
        
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
                new MenuL();
                ventana.dispose();
            }
        });
        
        panelFondo.add(BVolver);
        
        ventana.setContentPane(panelFondo);
        ventana.setVisible(true);
    }
}