/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author wunde
 */
public class Juego {
    private Jugador[] jugadores;
    private int tam;
    private int ocupados;
    private int topo;
    private boolean ganado;
    private int puntaje_meta;

    public Juego(int tam, int puntaje_meta) {
        this.jugadores = new Jugador[tam];
        this.topo=jugar_topo();
        this.ocupados=0;
        this.tam = tam;
        this.ganado = false;
        this.puntaje_meta = puntaje_meta;
    }

    public int getPuntaje_meta() {
        return puntaje_meta;
    }
    public int getOcupados(){
        return ocupados;
    }
    
    public boolean getGanado(){
        return ganado;
    }
    
    public int numJugadores(){
        return ocupados;
    }
    
    /*public String buscar_address(String address){
        String resp = "";
        if (ocupados > 0){
            for(int i=0; i < ocupados; i++){
                if(jugadores[i].getAddress()== address)
                resp = jugadores[i].getId();
            }
        }
        return resp;
    }*/
    
    public int buscar_jugador(String id){
        int resp = -1;
        
        if (ocupados > 0){
            int i = 0;
            while(i < ocupados && resp == -1){
                if(jugadores[i].getId().equals(id)){
                    resp = i;
                }
                i++;
            }
        }
        return resp;
    }
    
    public int agregar_jugador(String id){
        int resp = -1;//tablero lleno
        if(ocupados < tam){
            int pos = buscar_jugador(id);
            if(pos == -1){
                Jugador jug = new Jugador(id);
                jugadores[ocupados]=jug;
                ocupados++;
                resp = 1;//agregado
            }else{
                resp = 0; //existente
            }
        }       
        return resp;
    }
    
    public void quitar_jugador(String id){
        if(ocupados > 0){
            int pos = buscar_jugador(id);
            if(pos != -1){
                jugadores[pos]= jugadores[ocupados];
                jugadores[ocupados] = null;
                ocupados--;
            }
        }     
    }
    
    public int jugar_topo(){
        this.topo = 1 + (int)(Math.random() * 9);
        return topo;
    }

    public int getTopo() {
        return topo;
    }
    
    public void ganar_ronda(String id){
        if(ocupados > 0){
            int pos = buscar_jugador(id);
            if(pos != -1){
                jugadores[pos].ganar();
            }
        }     
    }
    
    public int getMaxPuntaje(){
        int resp = -1;
        if (ocupados>0){
            for (int i=0; i<ocupados; i++){
                if(jugadores[i].getPuntaje() > resp){
                    resp = jugadores[i].getPuntaje();
                }                
            }
        }
        return resp;
    } 
    
    public String ganador(){
        String resp ="";
        if (ocupados>0){
            int max = -1;
            for (int i=0; i<ocupados; i++){
                if(jugadores[i].getPuntaje() > max){
                    max = jugadores[i].getPuntaje();
                    resp = jugadores[i].getId();
                }                
            }
            ganado = true;
        }
        return resp;
    } 
    
    public void restart(){
        for(int i=0; i<ocupados; i++){
            jugadores[i].restart();
        }
        ganado = false;
    }
    
    public int puntaje_jugador(String id){
        int resp = -1;
        if(ocupados > 0){
            int pos = buscar_jugador(id);
            if (pos != -1){
                resp = jugadores[pos].getPuntaje();
            }
        }
        return resp;
    }
    
    public int[] getPuntajes(){
        int[]puntajes = new int[ocupados];
        for(int i = 0; i < ocupados; i++){
            puntajes[i] = jugadores[i].getPuntaje();
        }
        return puntajes;
    }
    
    public String[] getUsers(){
        String[]users = new String[ocupados];
        for(int i = 0; i < ocupados; i++){
            users[i] = jugadores[i].getId();
        }
        return users;
    }
    
}
