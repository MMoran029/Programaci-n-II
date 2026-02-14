package com.mycompany.battleshipdinamico_22541052;

public class Player {
    private String username;
    private String password;
    private int puntos;
    private String[] historialPartidas; 
    private int contadorPartidas;
    
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.historialPartidas = new String[10];
        this.contadorPartidas = 0;
    }
    
    
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }
    
    public String[] getHistorialPartidas() {
        return historialPartidas;
    }
    
    public int getContadorPartidas() {
        return contadorPartidas;
    }
    
    //   agregar una partida al historial
    public void agregarPartida(String resultado) {
        if (contadorPartidas < 10) {
            // agregar al final
            historialPartidas[contadorPartidas] = resultado;
            contadorPartidas++;
        } else {
            // posicion hacia atras caundo esta lleno
            for (int i = 9; i > 0; i--) {
                historialPartidas[i] = historialPartidas[i - 1];
            }
            historialPartidas[0] = resultado;
        }
    }
    
    //  reorganizar 
    private void reorganizarHistorial() {
        if (contadorPartidas <= 1) return;
        
        String temp = historialPartidas[contadorPartidas - 1];
        for (int i = contadorPartidas - 1; i > 0; i--) {
            historialPartidas[i] = historialPartidas[i - 1];
        }
        historialPartidas[0] = temp;
    }
    
    @Override
    public String toString() {
        return "Username: " + username + " | Puntos: " + puntos;
    }
}