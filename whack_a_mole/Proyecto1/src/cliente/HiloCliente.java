/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Puntero;

/**
 *
 * @author wunde
 */
public class HiloCliente extends Thread{
    private String usuario;
    private String multiGroup;
    private int multiSocket;
    private int udpSocket;
    private int topo_activo;
    
    public HiloCliente(String username){
        this.usuario = username;
    }
    
    @Override
    public void run(){
        System.out.println("entro al run");
        Socket s = null;
	    try {
                
                //se conecta para darse de alta en el juego
	    	int serverPort = 7896;
	   	
                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);    
		DataInputStream in = new DataInputStream( s.getInputStream());
		DataOutputStream out =
			new DataOutputStream( s.getOutputStream());
		out.writeUTF(usuario);        	// UTF is a string encoding 
                System.out.println("Mensaje enviado");
                
                String mensaje = in.readUTF(); 
                if (!mensaje.equals("-1")){
                    //si es -1 el tablero esta lleno y no hace nada más
                    //de otro modo recibe el mensaje con los demas puertos a los que debe conectarse
                    udpSocket = Integer.parseInt(mensaje.substring(0,4));
                    mensaje = mensaje.substring(4);
                    multiSocket = Integer.parseInt(mensaje.substring(0,4));
                    mensaje = mensaje.substring(4);
                    multiGroup = mensaje;
                    
                    //prepara las conecciones de multicast
                    MulticastSocket ms = new MulticastSocket(multiSocket);
                    InetAddress group = InetAddress.getByName(multiGroup); // destination multicast group 
                    ms.joinGroup(group);
                    byte[] buffer = new byte[1000];
                    String ganador;
                    ByteArrayInputStream bais;
                    ObjectInputStream ois;

                    //entra en el loop de escucha
                    while(true) {
                        DatagramPacket messageIn = 
                            new DatagramPacket(buffer, buffer.length);
                        ms.receive(messageIn);
                        bais = new ByteArrayInputStream(buffer);
                        ois = new ObjectInputStream(bais);
                        Object readObject = ois.readObject();
                        
                        //Castea el objeto recibido y obtiene el topo asignado
                        Puntero punt = (Puntero)readObject;
                        topo_activo = punt.getTopo();
                        System.out.println("llego topo "+topo_activo);
                        
                        if (topo_activo == 0){
                            //si el topo es 0 el juego ha acabado
                            ganador = punt.ganador();
                            
                            //no opera nada porque eso es en la interfaz
                            //y no compete a las mediciones de estres de las conexiones
                            
                            //el juego se reiniciaria pero se opto por romper el loop
                            //para terminar y tomar las medidas
                            break;
                        }else{
                            //si el topo es valido imita el juego con retardo de 
                            //un jugador y envía mla jugada
                            DatagramSocket aSocket = new DatagramSocket();
                            StringBuilder sb = new StringBuilder();
                            sb.append(topo_activo);
                            sb.append(usuario);
                            String myMessage = sb.toString();
                            byte [] m = myMessage.getBytes();

                            InetAddress aHost = InetAddress.getByName("localhost");
                            DatagramPacket request = new DatagramPacket(m, m.length, aHost, udpSocket);
                            //delay en dar click
                            int segs = 1+(int)(Math.random() * 3);
                            TimeUnit.SECONDS.sleep(segs);
                            System.out.println("golpe topo "+topo_activo);
                            aSocket.send(request);	
                        }

                    }
                    
                    

                }
                    
       	    } 
            catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	    }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
    	    } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                if(s!=null){ 
                    try {
                        s.close();
                    } catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
                }    
            }

    }
    
}
