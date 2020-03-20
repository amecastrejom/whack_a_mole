/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.Serializable;

/**
 *
 * @author wunde
 */
public class Puntero implements Serializable{
    
    private int topo;
    private String[] users;
    private int[] puntajes;

    public Puntero(String[] _users, int[] _puntajes) {
        this.users = _users;
        this.puntajes = _puntajes;
    }

    public int getTopo() {
        return topo;
    }

    public void setTopo(int topo) {
        this.topo = topo;
    }
    
    /* public void agrega_jugador(String username, int puntaje){
        if (ocupados<users.length){
            users[ocupados]=username;
            puntajes[ocupados]=puntaje;
            ocupados++;
        }
    }*/
    
    private int max_puntaje(){
        int pos = -1;
        int max = -1;
        for(int i =0; i < puntajes.length; i++){
            if(puntajes[i]>max ){
                pos = i;
                max = puntajes[i];
            }
        }
        return pos;
    }
    
    public String ganador (){
        String resp = "";
        int pos = max_puntaje();
        if(pos != -1)
            resp = users[pos];
        return resp;
    }
    
    
    
}
