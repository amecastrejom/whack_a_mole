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

    public Jugador(String id, int juego) {
        this.id = id;
        this.juego = juego;
        this.puntaje =0;
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

    public void reset(){
        puntaje = 0;
        juego = 0;
    }
    
    public void ganar(){
        puntaje++;
    }  
    
    
}