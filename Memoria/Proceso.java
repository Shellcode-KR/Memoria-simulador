/*
 * Intentare hacer unos ajustes para poner todo en una clase
 */
 

/**
 *
 * @author FLAViO
 */
public class Proceso  {
    int memoriaOcupada, tiempoMemoria=1;
    int nProceso,tiLlegada;
    boolean so = false;
    boolean atendiendo = false;
    
    //Metodo de creacion de procesos
    public Proceso(int mo, int tm,int numeroproceso,int tiempollegada){
        memoriaOcupada = mo;
        tiempoMemoria = tm;
        tiLlegada=tiempollegada;
        nProceso=numeroproceso;
        
       
    }
    //Metodo de creacion de SO;
    public Proceso(int mo,boolean sso){
        memoriaOcupada = mo;
        tiempoMemoria = 999;
        tiLlegada=0;
        nProceso=0;
        if(sso = true){
            so = true;
        }
    }

    public  int getTiLlegada() {
        return tiLlegada;
    }
    
    @Override
    public String toString(){
        if(so==true){
        return "[ SO , "+memoriaOcupada+" ]";
        }else{
        return "[#"+nProceso+", "+memoriaOcupada+", "+tiempoMemoria+", "+atendiendo+" "+tiLlegada+"]";
        }
    }
    
    public void atenderProceso(){
        tiempoMemoria--;
        atendiendo = true;
        
    }
}
