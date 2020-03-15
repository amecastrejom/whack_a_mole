/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphaproyect;

/**
 *
 * @author Ivana
 */
public class Jugador {
    
    private String id;
    private int puntaje;
    private int juego;

    public Jugador(String id, int puntaje, int juego) {
        this.id = id;
        this.puntaje = puntaje;
        this.juego = juego;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getJuego() {
        return juego;
    }

    public void setJuego(int juego) {
        this.juego = juego;
    }
    
    
}
