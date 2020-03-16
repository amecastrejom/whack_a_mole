/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author wunde
 */
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
        Juego juego;
        
	public Connection (Juego juego,Socket aClientSocket) {
            this.juego = juego;
	    try {
		clientSocket = aClientSocket;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
	    try {			                 // an echo server
                String data = in.readUTF();
                if (!data.equals("cerrarThread")){
                    System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                    int aceptado = juego.agregar_jugador(data);
                    if (aceptado != -1 ){
                        out.writeUTF("6789");
                        out.writeUTF("228.5.6.7");
                    }
                }else{
                    try {
                    clientSocket.close();
                    } catch (IOException e){
                        System.out.println(e);
                    }
                    
                }
                
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}
