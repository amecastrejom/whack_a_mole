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
public class Jugador {
    private String id;
    private int juego;
    private int puntaje;
    private int partidas_ganadas;

    public Jugador(String id, int juego) {
        this.id = id;
        this.juego = juego;
        this.puntaje =0;
        this.partidas_ganadas = 0;
    }
    
    

    public String getId() {
        return id;
    }

    public int getJuego() {
        return juego;
    }

    public void setJuego(int juego) {
        this.juego = juego;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }
    
    public void reset(int juego){
        puntaje = 0;
        this.juego = juego;
        
    }
    public void restart(){
        reset(1);
        partidas_ganadas = 0;
    }
    
    public void ganar(){
        puntaje++;
    }  
    
    public void ganar_partida(){
        partidas_ganadas++;
    }
    
    
}
