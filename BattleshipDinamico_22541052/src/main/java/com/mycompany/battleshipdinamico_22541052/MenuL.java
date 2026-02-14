package com.mycompany.battleshipdinamico_22541052;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuL {
    
    public MenuL(){
        
        JFrame Menu=new JFrame();
        Menu.setTitle("Menu");
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.setResizable(false);
        Menu.setSize(1024,720);
        
        ImageIcon icono=new ImageIcon("src/img/Logo.png");
        Menu.setIconImage(icono.getImage());
        
        JPanel panelFondo=new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagen = new ImageIcon("src/img/Menu.png");
                g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        panelFondo.setLayout(null);
        
        // BLogin
        ImageIcon IBLogin = new ImageIcon("src/img/BLogin.png");
        JButton BLogin = new JButton(IBLogin);
        BLogin.setBounds(360, 400, 300, 100);
        BLogin.setBorderPainted(false);
        BLogin.setContentAreaFilled(false);
        BLogin.setFocusPainted(false);
        
        BLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abriendo Login...");
                new Login();
                Menu.dispose();
            }
        });
        
        panelFondo.add(BLogin);
        
        // BSalir
        ImageIcon IBSalir = new ImageIcon("src/img/BSalir.png");
        JButton BSalir = new JButton(IBSalir);
        BSalir.setBounds(360, 535, 300, 100);
        BSalir.setBorderPainted(false);
        BSalir.setContentAreaFilled(false);
        BSalir.setFocusPainted(false);
        
        BSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saliendo del juego...");
                System.exit(0);
            }
        });
        
        panelFondo.add(BSalir);
        
        // BCrearP
        ImageIcon IBCrearO = new ImageIcon("src/img/BCrearP.png");
        JButton BCrearP = new JButton(IBCrearO);
        BCrearP.setBounds(360, 265, 300, 100);
        BCrearP.setBorderPainted(false);
        BCrearP.setContentAreaFilled(false);
        BCrearP.setFocusPainted(false);
        
        BCrearP.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
                System.out.println("Abriendo Crear Perfil...");
                new CrearPerfil();
                Menu.dispose();
            }
        });
        
        panelFondo.add(BCrearP);
        
        Menu.setContentPane(panelFondo);
        Menu.setVisible(true);
    }
    
}