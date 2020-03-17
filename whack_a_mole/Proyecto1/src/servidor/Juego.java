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
    private int actual;
    private int partidas;

    public Juego(int tam, int partidas) {
        this.jugadores = new Jugador[tam];
        this.topo=jugar_topo();
        this.actual=1;
        this.ocupados=0;
        this.tam = tam;
        this.partidas = partidas;
    }

    public int getActual() {
        return actual;
    }

    public void next_ronda(int juego) {
        if (juego < partidas){
            this.actual++;
            reiniciar();
            for(int i=0; i < ocupados; i++){
                jugadores[i].setJuego(juego);
            }           
        }else
            this.actual = 1;
            reiniciar();
    }
    
    
    
    private int buscar_jugador(String id){
        int resp = -1;
        if (ocupados > 0){
            for(int i=0; i < ocupados; i++){
                if(jugadores[i].getId()== id)
                resp = i;
            }
        }
        return resp;
    }
    
    public int agregar_jugador(String id){
        int resp = -1;//tablero lleno
        if(ocupados < tam){
            int pos = buscar_jugador(id);
            if(pos == -1){
                Jugador jug = new Jugador(id,actual);
                jugadores[ocupados]=jug;
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
   
    public void reiniciar(){
        this.actual = 1;
        if (ocupados > 0){
            for(int i=0; i<ocupados; i++){
                jugadores[i].restart();
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
    
    public void ganar_partida(){
        if(ocupados > 0){   
            String id = ganador_partida();
            int pos = buscar_jugador(id);
            if(pos != -1){
                jugadores[pos].ganar_partida();
            }
            for(int i = 0; i < ocupados; i++){
                jugadores[i].reset(actual);
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
    
    public String ganador_partida(){
        String resp ="sin ganador";
        if (ocupados>0){
            int max = -1;
            for (int i=0; i<ocupados; i++){
                if(jugadores[i].getPuntaje() > max){
                    max = jugadores[i].getPuntaje();
                    resp = jugadores[i].getId();
                }                
            }
        }
        return resp;
    }    
    
    public String ganador_juego(){
        String resp ="sin ganador";
        if (ocupados>0){
            int max = -1;
            for (int i=0; i<ocupados; i++){
                if(jugadores[i].getPartidas_ganadas() > max){
                    max = jugadores[i].getPartidas_ganadas();
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
    
    public void acabarPartida(){
        if(ocupados > 0){
            actual++;
            ganar_partida();
        }
    }
