/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author wunde
 */
public class Juego {
    private Jugador[] jugadores;
    private int tam;
    private int topo;
    private int juego;

    public Juego() {
        this.jugadores = new Jugador[5];
        this.topo=0;
        this.juego=1;
        this.tam=0;
    }

    public int getJuego() {
        return juego;
    }

    public void next_ronda(int juego) {
        if (juego < 5){
            this.juego++;
            reiniciar();
            for(int i=0; i < tam; i++){
                jugadores[i].setJuego(juego);
            }           
        }else
            this.juego = 0;
            reiniciar();
    }
    
    
    
    private int buscar_jugador(String id){
        int resp = -1;
        if (tam > 0){
            for(int i=0; i < tam; i++){
                if(jugadores[i].getId()== id)
                resp = i;
            }
        }
        return resp;
    }
    
    public int agregar_jugador(String id){
        int resp = -1;//tablero lleno
        if(tam < 5){
            int pos = buscar_jugador(id);
            if(pos == -1){
                Jugador jug = new Jugador(id,juego);
                jugadores[tam]=jug;
                resp = 1;//agregado
            }else{
                resp = 0; //existrente
            }
        }       
        return resp;
    }
    
    public void quitar_jugador(String id){
        if(tam > 0){
            int pos = buscar_jugador(id);
            if(pos != -1){
                jugadores[pos]= jugadores[tam];
                jugadores[tam] = null;
                tam--;
            }
        }     
    }
   
    public void reiniciar_jugador(String id){
        int pos = buscar_jugador(id);
        if(pos != -1){
            jugadores[pos].reset();
        }
    }
    
    public void reiniciar(){
        this.juego = 0;
        if (tam > 0){
            for(int i=0; i<tam; i++){
                jugadores[i].reset();
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
        if(tam > 0){
            int pos = buscar_jugador(id);
            if(pos != -1){
                jugadores[pos].ganar();
            }
        }     
    }
    
    
    public String ganador(){
        String resp ="sin ganador";
        if (tam>0){
            int max = -1;
            for (int i=0; i<tam; i++){
                if(jugadores[i].getPuntaje() > max){
                    max = jugadores[i].getPuntaje();
                    resp = jugadores[i].getId();
                }                
            }
        }
        return resp;
    }
    
    public boolean validar_topo(int golpeado){
        boolean resp = false;
        if (golpeado == topo){
            resp = true;
        }
        return resp;
    }
    
}