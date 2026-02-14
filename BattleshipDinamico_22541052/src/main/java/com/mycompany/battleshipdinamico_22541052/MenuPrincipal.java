package com.mycompany.battleshipdinamico_22541052;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPrincipal {
    
    public MenuPrincipal(){
        
        JFrame ventana = new JFrame();
        ventana.setTitle("Menú Principal - Battleship");
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
        
        // BJugar
        ImageIcon IBJugar = new ImageIcon("src/img/BJugar.png");
        JButton BJugar = new JButton(IBJugar);
        BJugar.setBounds(347, 180, 330, 95);
        BJugar.setBorderPainted(false);
        BJugar.setContentAreaFilled(false);
        BJugar.setFocusPainted(false);
        
        BJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Jugar Battleship");
                new Jugador2();
                ventana.dispose();
            }
        });
        
        panelFondo.add(BJugar);
        
        // BConfiguracion
        ImageIcon IBConfiguracion = new ImageIcon("src/img/BConfiguracion.png");
        JButton BConfiguracion = new JButton(IBConfiguracion);
        BConfiguracion.setBounds(347, 280, 330, 95);
        BConfiguracion.setBorderPainted(false);
        BConfiguracion.setContentAreaFilled(false);
        BConfiguracion.setFocusPainted(false);
        
        BConfiguracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Configuración");
                new Configuracion();
                ventana.dispose();
            }
        });
        
        panelFondo.add(BConfiguracion);
        
        ImageIcon IBReportes = new ImageIcon("src/img/BReportes.png");
        JButton BReportes = new JButton(IBReportes);
        BReportes.setBounds(347, 380, 330, 95);
        BReportes.setBorderPainted(false);
        BReportes.setContentAreaFilled(false);
        BReportes.setFocusPainted(false);
        
        BReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reportes");
                // aqui la ventana de reportes cuando la cree
            }
        });
        
        panelFondo.add(BReportes);
        
        ImageIcon IBPerfil = new ImageIcon("src/img/BPerfil.png");
        JButton BPerfil = new JButton(IBPerfil);
        BPerfil.setBounds(347, 480, 330, 95);
        BPerfil.setBorderPainted(false);
        BPerfil.setContentAreaFilled(false);
        BPerfil.setFocusPainted(false);
        
        BPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mi Perfil");
            }
        });
        
        panelFondo.add(BPerfil);
        
        ImageIcon IBRegresar = new ImageIcon("src/img/BRegresar.png");
        JButton BRegresar = new JButton(IBRegresar);
        BRegresar.setBounds(347, 580, 330, 95);
        BRegresar.setBorderPainted(false);
        BRegresar.setContentAreaFilled(false);
        BRegresar.setFocusPainted(false);
        
        BRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cerrando sesión...");
                Battleship.logout(); 
                new MenuL(); 
                ventana.dispose();
            }
        });
        
        panelFondo.add(BRegresar);
        
        ventana.setContentPane(panelFondo);
        ventana.setVisible(true);
    }
}