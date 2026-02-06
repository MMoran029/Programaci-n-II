/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_rr;

/**
 *
 * @author mjosu
 */
public class Main {
    public static void main (String [] args){
        Operacion op= new Operacion();
        
        op.leerNumeros();
        op.sumar();
        op.restar();
        op.multiplicar();
        op.dividir();
        op.mostrarResultado();
        
    }
    
}
