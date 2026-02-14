package com.mycompany.battleshipdinamico_22541052;
import java.util.ArrayList;

public class Battleship {
    //jugadores
    private static ArrayList<Player> listaJugadores = new ArrayList<>();
    
    // Usuario  registrados
    private static Player currentUser = null;
    
    //configuracion
    private static String dificultad = "NORMAL";
    private static String modoJuego = "TUTORIAL"; 
    
    //    Tableros
    private char[][] tableroJugador1;
    private char[][] tableroJugador2;
    
    public Battleship() {
        tableroJugador1 = new char[8][8];
        tableroJugador2 = new char[8][8];
        inicializarTableros();
    }
    
    public static boolean registrarJugador(String username, String password) {
        // verififcar que el username no se repta
        for (Player p : listaJugadores) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        
        Player nuevoJugador = new Player(username, password);
        listaJugadores.add(nuevoJugador);
        return true;
    }
    
    public static boolean login(String username, String password) {
        for (Player p : listaJugadores) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                currentUser = p;
                return true;
            }
        }
        return false;
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static Player getCurrentUser() {
        return currentUser;
    }
    
    public static ArrayList<Player> getListaJugadores() {
        return listaJugadores;
    }
    
    public static Player buscarJugador(String username) {
        for (Player p : listaJugadores) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }
    
    public static boolean eliminarJugador(String username) {
        return listaJugadores.removeIf(p -> p.getUsername().equals(username));
    }
    
    //Configuracion
    public static void setDificultad(String dif) {
        dificultad = dif;
    }
    
    public static String getDificultad() {
        return dificultad;
    }
    
    public static void setModoJuego(String modo) {
        modoJuego = modo;
    }
    
    public static String getModoJuego() {
        return modoJuego;
    }
    
    public static int getCantidadBarcos() {
        switch (dificultad) {
            case "EASY":
                return 5;
            case "NORMAL":
                return 4;
            case "EXPERT":
                return 2;
            case "GENIUS":
                return 1;
            default:
                return 4;
        }
    }
    
    // metodos  del tablero
    private void inicializarTableros() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tableroJugador1[i][j] = '~';
                tableroJugador2[i][j] = '~';
            }
        }
    }
    
    public char[][] getTableroJugador1() {
        return tableroJugador1;
    }
    
    public char[][] getTableroJugador2() {
        return tableroJugador2;
    }
    
    // ranking ordenado por puntos
    public static ArrayList<Player> getRankingJugadores() {
        ArrayList<Player> ranking = new ArrayList<>(listaJugadores);
        
        // ordenar por puntos
        for (int i = 0; i < ranking.size() - 1; i++) {
            for (int j = 0; j < ranking.size() - i - 1; j++) {
                if (ranking.get(j).getPuntos() < ranking.get(j + 1).getPuntos()) {
                    Player temp = ranking.get(j);
                    ranking.set(j, ranking.get(j + 1));
                    ranking.set(j + 1, temp);
                }
            }
        }
        
        return ranking;
    }
    
    // generar tablero
    
    public static char[][] regenerarTablero(char[][] tableroOriginal) {
        char[][] nuevoTablero = new char[8][8];
        
        // generar agua
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                nuevoTablero[i][j] = '~';
            }
        }
        
        // barcos   sus tamaños  los vivos
        ArrayList<Character> barcosVivos = new ArrayList<>();
        ArrayList<Integer> tamanosBarcos = new ArrayList<>();
        
        // barcos que aun no estan hundidos
        boolean tienePA = false, tieneAZ = false, tieneSM = false, tieneDT = false;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char celda = tableroOriginal[i][j];
                if (celda == 'P') tienePA = true;
                if (celda == 'A') tieneAZ = true;
                if (celda == 'S') tieneSM = true;
                if (celda == 'D') tieneDT = true;
            }
        }
        
        // Agregar barcos a la lista
        if (tienePA) {
            barcosVivos.add('P');
            tamanosBarcos.add(5);
        }
        if (tieneAZ) {
            barcosVivos.add('A');
            tamanosBarcos.add(4);
        }
        if (tieneSM) {
            barcosVivos.add('S');
            tamanosBarcos.add(3);
        }
        if (tieneDT) {
            barcosVivos.add('D');
            tamanosBarcos.add(2);
        }
        
        //colocar cada barco aleatoriamente
        java.util.Random random = new java.util.Random();
        
        for (int b = 0; b < barcosVivos.size(); b++) {
            char tipoBarco = barcosVivos.get(b);
            int tamaño = tamanosBarcos.get(b);
            boolean colocado = false;
            int intentos = 0;
            
            while (!colocado && intentos < 100) {
                int fila = random.nextInt(8);
                int columna = random.nextInt(8);
                boolean horizontal = random.nextBoolean();
                
                if (puedeColocarBarco(nuevoTablero, fila, columna, tamaño, horizontal)) {
                        colocarBarcoEnTablero(nuevoTablero, fila, columna, tamaño, horizontal, tipoBarco);
                        colocado = true;
                }
                intentos++;
            }
            }
            return nuevoTablero;
        }

        public static char[][] regenerarTableroConImpactos(char[][] tableroOriginal, java.util.HashMap<Character, Integer> impactos) {
        char[][] nuevoTablero = new char[8][8];
    
        // agua
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                nuevoTablero[i][j] = '~';
            }
        }
        //la cantidad de barcos  segun la dificultad
        int cantidadPermitida = getCantidadBarcos();
        //   identificar barcos vivos
        java.util.ArrayList<Character> barcosVivos = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> tamanosBarcos = new java.util.ArrayList<>();
    
        // Prioridad
        if (impactos.get('P') < 5 && barcosVivos.size() < cantidadPermitida) {
            barcosVivos.add('P');
            tamanosBarcos.add(5);
        }
        if (impactos.get('A') < 4 && barcosVivos.size() < cantidadPermitida) {
            barcosVivos.add('A');
            tamanosBarcos.add(4);
        }
        if (impactos.get('S') < 3 && barcosVivos.size() < cantidadPermitida) {
            barcosVivos.add('S');
            tamanosBarcos.add(3);
        }
        if (impactos.get('D') < 2 && barcosVivos.size() < cantidadPermitida) {
            barcosVivos.add('D');
            tamanosBarcos.add(2);
        }
    
        // En easy se repite el destructor
        if (cantidadPermitida == 5 && barcosVivos.size() == 4 && impactos.get('D') < 2) {
            barcosVivos.add('D');
            tamanosBarcos.add(2);
        }
    
        java.util.Random random = new java.util.Random();
    
        for (int b = 0; b < barcosVivos.size(); b++) {
            char tipoBarco = barcosVivos.get(b);
            int tamaño = tamanosBarcos.get(b);
            boolean colocado = false;
            int intentos = 0;
        
            while (!colocado && intentos < 100) {
                int fila = random.nextInt(8);
                int columna = random.nextInt(8);
                boolean horizontal = random.nextBoolean();
            
                if (puedeColocarBarco(nuevoTablero, fila, columna, tamaño, horizontal)) {
                    colocarBarcoEnTablero(nuevoTablero, fila, columna, tamaño, horizontal, tipoBarco);
                    colocado = true;
                }
                intentos++;
            }
        }
        return nuevoTablero;
    }

    private static boolean puedeColocarBarco(char[][] tablero, int fila, int columna, int tamaño, boolean horizontal) {
        if (horizontal) {
            if (columna + tamaño > 8) return false;
            for (int j = columna; j < columna + tamaño; j++) {
                if (tablero[fila][j] != '~') return false;
            }
        } else {
            if (fila + tamaño > 8) return false;
            for (int i = fila; i < fila + tamaño; i++) {
                if (tablero[i][columna] != '~') return false;
            }
        }
        return true;
    }

    private static void colocarBarcoEnTablero(char[][] tablero, int fila, int columna, int tamaño, boolean horizontal, char tipo) {
        if (horizontal) {
            for (int j = columna; j < columna + tamaño; j++) {
                tablero[fila][j] = tipo;
            }
        } else {
            for (int i = fila; i < fila + tamaño; i++) {
                tablero[i][columna] = tipo;
            }
        }
    }

    public static boolean verificarBarcoHundido(char[][] tablero, char tipoBarco) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j] == tipoBarco) {
                    return false; 
                }
            }
        }
        return true;
    }

    public static int contarBarcosRestantes(char[][] tablero) {
        boolean tienePA = false, tieneAZ = false, tieneSM = false, tieneDT = false;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char celda = tablero[i][j];
                if (celda == 'P') tienePA = true;
                if (celda == 'A') tieneAZ = true;
                if (celda == 'S') tieneSM = true;
                if (celda == 'D') tieneDT = true;
            }
        }
        
        int total = 0;
        if (tienePA) total++;
        if (tieneAZ) total++;
        if (tieneSM) total++;
        if (tieneDT) total++;
        
        return total;
    }

    public static String obtenerNombreBarco(char codigo) {
        switch (codigo) {
            case 'P': return "Portaaviones";
            case 'A': return "Acorazado";
            case 'S': return "Submarino";
            case 'D': return "Destructor";
            default: return "";
        }
    }
    
    
    
}