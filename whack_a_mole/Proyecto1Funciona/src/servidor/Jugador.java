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
    private int puntaje;
    //private String address;

    public Jugador(String id) {
        this.id = id;
        this.puntaje =0;
        //this.address = address;
    }
    

    public String getId() {
        return id;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    public void restart(){
        puntaje = 0;
    }
    
    public void ganar(){
        puntaje++;
    }  
   
    
}
