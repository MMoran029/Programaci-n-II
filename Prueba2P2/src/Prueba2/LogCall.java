package Prueba2;

public class LogCall {
    
    private int NumeroMarcado;
    private int minutos;
    
    public LogCall(int NumeroMarcado, int minutos){
        this.NumeroMarcado = NumeroMarcado;
        this.minutos = minutos;
        
    }
    
    public int getMinutos(){
        return minutos;
    }
    
    public int getNumeroMarcado(){
        return NumeroMarcado;
    }
    
    @Override
    public String toString(){
        return "Numero: "+NumeroMarcado + ", Minutos: "+minutos;
    }
    
}
