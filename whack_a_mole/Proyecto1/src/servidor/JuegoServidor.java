/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author americacastrejon
 */
public class JuegoServidor {
    private static Juego juego;
    private static int tam;
    private static int partidas;
    private static int puntaje_meta;

    public static void main(String args[]) throws IOException {
        tam = 5;
        partidas = 3;    
        puntaje_meta = 5;
        juego = new Juego(tam,partidas);
        
        
        //paramulticast(udp)
        InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
        MulticastSocket s = new MulticastSocket(6789);
        s.joinGroup(group);
        
        new NuevosJugadores(juego).start();
        new MandarTablero(juego,group,s).start();
        new EscuchaJugada(juego,group,s).start(); 
        
        int actual = juego.getActual();
        while(actual <= partidas){
            int puntajeMax = juego.getMaxPuntaje();
            if(puntaje_meta == puntajeMax){
                juego.acabarPartida();
            }          
            actual = juego.getActual();
        }
        
        String ganador = juego.ganador_juego();
        //mandar ganador
    }
    
    
}

class NuevosJugadores extends Thread{
    private Juego juego;
    
    public NuevosJugadores (Juego juego){
        this.juego = juego;
    }
    
    @Override
    public void run(){
        try{
            int serverPort = 7896; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                    System.out.println("Waiting for messages..."); 
                    Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                    Connection c = new Connection(juego,clientSocket);
                    c.start();
            }
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
    
}

class MandarTablero extends Thread{
    private Juego juego;
    private InetAddress group;
    MulticastSocket s;
    
    
    public MandarTablero(Juego juego, InetAddress group, MulticastSocket s){
        this.juego = juego;
        this.group = group;
        this.s = s;
    }
    
    @Override
    public void run(){ 
         try {
             
             while(true){
                if(juego.numJugadores()>0){
                    //InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
                    int topoActual = juego.getTopo();
                    TimeUnit.SECONDS.sleep(3);
                    if(juego.getTopo() == topoActual){
                        String topo = ""+juego.jugar_topo();
                        //s = new MulticastSocket(6789);
                        //s.joinGroup(group); 
                        //s.setTimeToLive(10);
                        //System.out.println("Messages' TTL (Time-To-Live): "+ s.getTimeToLive());
                        byte [] m = topo.getBytes(); 
                        DatagramPacket messageOut = 
                               new DatagramPacket(m, m.length, group, 6789);
                        s.send(messageOut);
                    }
                }
             }

 	}
         catch (SocketException e){
             System.out.println("Socket topo: " + e.getMessage());
	 }catch (InterruptedException ex) {
            Logger.getLogger(MandarTablero.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException e){
             System.out.println("IO topo: " + e.getMessage());
         }
	 finally {
            if(s != null) s.close();
        }
    }
}

class EscuchaJugada extends Thread{
    private Juego juego;
    private InetAddress group;
    MulticastSocket s;
    
    public EscuchaJugada(Juego juego, InetAddress group, MulticastSocket s){
        this.juego = juego;
        this.group = group;
        this.s = s;
    }
    
    @Override
    public void run(){
        DatagramSocket aSocket = null;
	   try{
	    	int serverPort = 6789;
                aSocket = new DatagramSocket(serverPort); 
		byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
 		while(true){
                   System.out.println("Waiting for messages..."); 
 		   DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  		   aSocket.receive(request);
      System.out.println("Se recibió el mensaje:" + new String(request.getData())+ " from: "+ request.getAddress());
                   String jugada = new String(request.getData());
                   int seleccionado = Integer.parseInt(jugada);
                           
                   
    		   if(seleccionado == juego.getTopo()){
                        String dir = request.getAddress().toString();
                        String id = juego.buscar_address(dir);
                        juego.ganar_ronda(id);
                        String topo = ""+juego.jugar_topo();
                        byte [] m = topo.getBytes(); 
                        DatagramPacket messageOut = 
                               new DatagramPacket(m, m.length, group, 6789);
                        s.send(messageOut);  
                   }
		}
	   }
           catch (SocketException e){
                System.out.println("Socket: " + e.getMessage());
	   }
           catch (IOException e) {
               System.out.println("IO: " + e.getMessage());
           }
           finally {
                if(aSocket != null) 
                    aSocket.close();
           }


    }
    
    
    
}

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
                String dir = clientSocket.getRemoteSocketAddress().toString();
                System.out.println("Message received from: " + dir);
                
                int aceptado = juego.agregar_jugador(data,dir);
                if (aceptado != -1 ){
                    out.writeUTF("6789");
                    out.writeUTF("228.5.6.7");
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

