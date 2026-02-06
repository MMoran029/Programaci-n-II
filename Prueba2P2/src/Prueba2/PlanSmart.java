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
class PlanSmart extends PlanBasico {

    public PlanSmart(int numeroTelefono, String nombreCliente) {
        super(numeroTelefono, nombreCliente);
    }

    @Override
    public double pagoMensual() {
        int minutos = monthlyMinutes();
        int minutosCobrables = Math.max(0, minutos - 300);
        return (minutosCobrables * 0.5) + 20 + 22;
    }

    @Override
    public String toString() {
        return super.toString() + " - Plan Smart";
    }
}

