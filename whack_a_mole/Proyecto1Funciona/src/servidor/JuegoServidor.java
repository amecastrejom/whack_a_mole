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
    private static int puntaje_meta;
    
    //para coneccion
    private static int udpSocket;
    private static String multiGroup;
    private static int multiSocket;

    public static void main(String args[]) throws IOException, InterruptedException {
        tam = 5;    
        puntaje_meta = 3;
        juego = new Juego(tam);
        System.out.println("Topo inicial: "+juego.getTopo());
        udpSocket = 8000;
        multiSocket = 8001;
        multiGroup = "228.5.6.7";
       
        
        
        //paramulticast(udp)
        InetAddress group = InetAddress.getByName(multiGroup); // destination multicast group
        MulticastSocket s = new MulticastSocket(multiSocket);
        s.joinGroup(group);
        
        //para udp (escucha jugadas)
        DatagramSocket aSocket = new DatagramSocket(udpSocket);
       
        StringBuilder sb = new StringBuilder();
        sb.append(udpSocket);
        sb.append(multiSocket);
        sb.append(multiGroup);
        String mensaje = sb.toString();
        System.out.println(mensaje);
        
        new NuevosJugadores(juego, mensaje).start();
        new MandarTablero(juego,group,s,multiSocket).start();
        new EscuchaJugada(juego,aSocket,group,s,multiSocket).start(); 
        System.out.println(juego.numJugadores());
        
        while(true){
            int puntajeMax = juego.getMaxPuntaje();
            //TimeUnit.SECONDS.sleep(1);
            //por alguna razón sin la instrucción del sistema no funciona
            System.out.print("");
            if(puntaje_meta == puntajeMax){
                System.out.println("Partida acabada");
                juego.ganador();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                
                //mandar ganador
                Puntero punt = new Puntero(juego.getUsers(), juego.getPuntajes());
                punt.setTopo(0);
                oos.writeObject(punt);

                byte [] m = baos.toByteArray();
                DatagramPacket messageOut = 
                       new DatagramPacket(m, m.length, group, multiSocket);
                s.send(messageOut);
                TimeUnit.SECONDS.sleep(10);
                juego.restart();
                
            }                      
        }
        
        

    }
    
    
}

class NuevosJugadores extends Thread{
    private Juego juego;
    private String mensaje;
    
    public NuevosJugadores (Juego juego, String mensaje){
        this.juego = juego;
        this.mensaje = mensaje;
        
    }
    
    @Override
    public void run(){
        try{
            int serverPort = 7896; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Esperando jugadores"); 
            while(true) {
                    Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                    Connection c = new Connection(juego,clientSocket,mensaje);
                    c.start();
            }
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
    
}

class MandarTablero extends Thread{
    private Juego juego;
    private InetAddress group;
    private MulticastSocket s;
    private int multiSocket;
    
    
    
    public MandarTablero(Juego juego, InetAddress group, MulticastSocket s, int multiSocket){
        this.juego = juego;
        this.group = group;
        this.s = s;
        this.multiSocket = multiSocket;
        
    }
    
    @Override
    public void run(){ 
         try {
             //System.out.println(juego.numJugadores());
            ByteArrayOutputStream baos;
            ObjectOutputStream oos;
             while(true){
                //System.out.println("Jugadores activos: "+juego.numJugadores());
                //if(juego.numJugadores()>0){
                    System.out.println("Enviando topo automático");
                    //InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
                    int topoActual = juego.getTopo();
                    TimeUnit.SECONDS.sleep(3);
                    if(juego.getTopo() == topoActual && juego.getGanado() == false){
                        baos = new ByteArrayOutputStream();
                        oos = new ObjectOutputStream(baos);
                        Puntero punt = new Puntero(juego.getUsers(), juego.getPuntajes());
                        punt.setTopo(juego.jugar_topo());
                        oos.writeObject(punt);
                        
                        byte [] m = baos.toByteArray();
                        DatagramPacket messageOut = 
                               new DatagramPacket(m, m.length, group, multiSocket);
                        s.send(messageOut);
                    }
                //}
             }

 	}
         catch (SocketException e){
             System.out.println("Socket: " + e.getMessage());
	 }catch (InterruptedException ex) {
            Logger.getLogger(MandarTablero.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException e){
             System.out.println("IO: " + e.getMessage());
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
    DatagramSocket aSocket;
    private int multiSocket;
    
    
    public EscuchaJugada(Juego juego, DatagramSocket aSocket,InetAddress group, MulticastSocket s, int multiSocket){
        this.juego = juego;
        this.group = group;
        this.s = s;
        this.aSocket = aSocket;
        this.multiSocket = multiSocket;
    }
    
    @Override
    public void run(){
	   try{
		byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
                ByteArrayOutputStream baos;
                ObjectOutputStream oos;
                System.out.println("Esperando jugadas"); 
 		while(true){
                    System.out.print("");
                    if(!juego.getGanado()){
                        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                        aSocket.receive(request);   
                        String jugada = new String(request.getData());
                        //se obtiene el topo golpeado del primer valor del mensaje
                        int seleccionado = Character.getNumericValue(jugada.charAt(0));
                        int largo = request.getLength();
                        String jugador = jugada.substring(1,largo);//elimina el primer caracter
                        System.out.println("Jugador: "+jugador+" Golpe: "+ seleccionado);        

                        if(seleccionado == juego.getTopo()){
                             juego.ganar_ronda(jugador);
                             juego.jugar_topo();       
                        }
                        baos = new ByteArrayOutputStream();
                        oos = new ObjectOutputStream(baos);
                        Puntero punt = new Puntero(juego.getUsers(), juego.getPuntajes());
                         punt.setTopo(juego.getTopo());
                         oos.writeObject(punt);

                         byte [] m = baos.toByteArray();
                         DatagramPacket messageOut = 
                                new DatagramPacket(m, m.length, group, multiSocket);
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
        private String mensaje;
        
	public Connection (Juego juego,Socket aClientSocket, String mensaje) {
            this.juego = juego;
            this.mensaje = mensaje;
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
                //buscaba el adress para diferenciar jugadores por computadoras
                //dadas las nuevas condiciones de entrega se ha modificado el código original
                //String dir = clientSocket.getRemoteSocketAddress().toString();
                //System.out.println("Message received from: " + dir);
                
                int aceptado = juego.agregar_jugador(data);                
                System.out.println("pos: "+juego.buscar_jugador(data));
                
                if (aceptado != -1 ){
                    System.out.println("jugador aceptado: "+data);
                    out.writeUTF(mensaje);
                }else{
                    out.writeUTF("-1");
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


