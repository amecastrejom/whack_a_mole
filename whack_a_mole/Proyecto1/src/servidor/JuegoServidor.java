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
    private Juego juego;
    private int num_jugadores;
    private int puntaje_meta;
    private Juega j;
    
    //para coneccion
    private int udpSocket;
    private String multiGroup;
    private int multiSocket;
    
    public JuegoServidor(int num_jugadores, int puntaje_meta){
        this.num_jugadores = num_jugadores;
        this.puntaje_meta = puntaje_meta;
        this.udpSocket = 8000;
        this.multiSocket = 8001;
        this.multiGroup = "228.5.6.7";
        j = new Juega();
        j.start();
    }
    
    class Juega extends Thread{
    
        public void run(){
            try {
                juego = new Juego(num_jugadores, puntaje_meta);
                //System.out.println("Topo inicial: "+juego.getTopo());
                
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
                //System.out.println(mensaje);
                
                new NuevosJugadores(juego, mensaje).start();
                new MandarTablero(juego,group,s,multiSocket).start();
                new EscuchaJugada(juego,aSocket,group,s,multiSocket).start();
                //System.out.println(juego.numJugadores());
                
                while(true){
                    int puntajeMax = juego.getMaxPuntaje();
                    //por alguna razón sin la instrucción del system out no funciona
                    System.out.print("");
                    if(puntaje_meta == puntajeMax){
                        //System.out.println("Partida acabada");
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
                        //TimeUnit.SECONDS.sleep(6);
                        //juego.restart();
                                              
                    }
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(JuegoServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JuegoServidor.class.getName()).log(Level.SEVERE, null, ex);
            }/* catch (InterruptedException ex) {
                Logger.getLogger(JuegoServidor.class.getName()).log(Level.SEVERE, null, ex);
            }*/

        }
    }
    
    public static void main(String args[]) throws IOException, InterruptedException {
        //primer int es el número de jugadores máximo
        //cualquier jugador extra que intente agregarse será avisado en su interfaz que el servidor está lleno
        //el segundo int es el numero de puntos para ganar
        JuegoServidor js = new JuegoServidor (5,3);
        
        //Se ha comentado todos los system out con los que se regulaba el funcionamiento
        //para evitar el tiempo que consumen
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
                //System.out.println("Enviando topo automático");
                //InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
                TimeUnit.SECONDS.sleep(3);
                System.out.print("");
                if(juego.numJugadores() > 0 && juego.topoModificado() == false){
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    Puntero punt = new Puntero(juego.getUsers(), juego.getPuntajes());
                    punt.setTopo(juego.jugar_topo());
                    System.out.println("Topo enviado automatico: "+juego.getTopo());
                    oos.writeObject(punt); 
                    byte [] m = baos.toByteArray();
                    DatagramPacket messageOut = 
                           new DatagramPacket(m, m.length, group, multiSocket);
                    s.send(messageOut);
                    juego.resetModificado();
                }
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
                //System.out.println("Esperando jugadas"); 
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
                        //System.out.println("Jugador: "+jugador+" Golpe: "+ seleccionado);
                        
                        //debido a los delays en envios, el servido alcanza a jugar un punto mas del que debe
                        //por ello se coteja aquí que el puntaje maximo no se haya alcanzado
                        int puntajeMax = juego.getMaxPuntaje();
                        System.out.println();

                        if(seleccionado == juego.getTopo() && puntajeMax < juego.getPuntaje_meta() ){
                             juego.ganar_ronda(jugador);
                             juego.jugar_topo();  
                             baos = new ByteArrayOutputStream();
                             oos = new ObjectOutputStream(baos);
                             Puntero punt = new Puntero(juego.getUsers(), juego.getPuntajes());
                             System.out.println("Topo enviado: "+juego.getTopo());
                             punt.setTopo(juego.getTopo());
                             oos.writeObject(punt);

                             byte [] m = baos.toByteArray();
                             DatagramPacket messageOut = 
                                    new DatagramPacket(m, m.length, group, multiSocket);
                             s.send(messageOut);
                        }
                        
                        
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
                //System.out.println("pos: "+juego.buscar_jugador(data));
                
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

