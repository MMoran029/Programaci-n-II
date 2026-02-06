/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba2;

/**
 *
 * @author mjosu
 */
public class Main {
    public static void main(String[] args) {
        PlanBasico pb = new PlanBasico(123456, "Mario");
        pb.call(987654, 50);
        pb.call(555555, 30);
        pb.imprimir();
        System.out.println("Pago mensual: $" + pb.pagoMensual());

        PlanSmart ps = new PlanSmart(111111, "Mario Smart");
        ps.call(222222, 400);
        ps.imprimir();
        System.out.println("Pago mensual: $" + ps.pagoMensual());
    }
}
