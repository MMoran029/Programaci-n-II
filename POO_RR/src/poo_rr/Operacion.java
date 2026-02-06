package poo_rr;

import javax.swing.JOptionPane;


public class Operacion {
   
    // Atributos
    int num1;
    int num2;
    int suma;
    int resta;
    int multiplicacion;
    int division;
    
    // Metodo
    public void leerNumeros(){
        num1 = Integer.parseInt(JOptionPane.showInputDialog("Digite un numero"));
        num2 = Integer.parseInt(JOptionPane.showInputDialog("Digite un numero"));
        
    }
    
    public void sumar(){
        suma = num1+num2;
        
    }
    
    public void restar(){
        resta = num1-num2;
        
    }
    
    public void multiplicar(){
        multiplicacion = num1*num2;
        
    }
    
    public void dividir(){
        division = num1/num2;
        
    }
    
    public void mostrarResultado(){
        System.out.println("La suma es: "+suma);
        System.out.println("La resta es: "+resta);
        System.out.println("La multiplicacion es: "+multiplicacion);
        System.out.println("La division es: "+division);
    }
    
}
