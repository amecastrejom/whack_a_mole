/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;
import java.io.IOException;
import servidor.JuegoServidor;

/**
 *
 * @author americacastrejon
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        int num_jugadores = 1;
        int puntos = 2;
        JuegoServidor js = new JuegoServidor(num_jugadores, puntos);
        
        for(int i = 0; i < num_jugadores; i++){
            String username = "Jugador"+i;
            new HiloCliente(username).start();
        }
    }
    
}
