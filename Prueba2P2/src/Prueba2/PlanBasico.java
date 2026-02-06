package Prueba2;

import java.util.ArrayList;


public class PlanBasico{
    private int numeroTelefono;
    private String nombreCliente;
    private ArrayList<LogCall> llamadas;
    
    public PlanBasico(int numero,String nombre){
        this.numeroTelefono = numero;
        this.nombreCliente = nombre;
        this.llamadas = new ArrayList<>();
    }
    
    public int getNumero(){
        return numeroTelefono;
    }
    
    public String getNombre(){
        return nombreCliente;
    }
    
    // registrar llamada
    public void call(int numero, double minutos){
        LogCall nueva = new LogCall(numero, (int) minutos );
        llamadas.add(nueva);

    }
    
    // retorna la cantidad de minutos
    public int monthlyMinutes() {
        int total = 0;
        for (LogCall lc : llamadas) {
            total += lc.getMinutos();
        }
        return total;
    }

    // pago mensual
    public double pagoMensual() {
        return monthlyMinutes() * 0.5;
    }

    @Override
    public String toString() {
        return "Numero: " + numeroTelefono + " - Cliente: " + nombreCliente;
    }

    // imprimir
    public void imprimir() {
        System.out.println(this.toString());
        for (LogCall lc : llamadas) {
            System.out.println("   " + lc.toString());
        }
    }

    
}
