/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.*;
import java.io.*;
import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Puntero;

/**
 *
 * @author americacastrejon
 */
public class gui_juego extends javax.swing.JFrame {
    private static int udpSocket;
    private static int multiSocket;
    private static String multiGroup;
    private static String username;
    private static int topo_activo;
    private boolean jugando;
    private static EscuchaTablero et;
    
    int casilla = 0;
    private String posTopo;
    private int id;
    private static int consecutivo=1;
    /**
     * Creates new form gui_juego
     */
    public gui_juego(int udpSocket, int multiSocket, String multiGroup, String username) {
        initComponents();
        this.udpSocket = udpSocket;
        this.multiSocket = multiSocket;
        this.multiGroup = multiGroup;
        this.username = username;
        this.jugando = true;
        this.topo_activo = 0;
        consecutivo++; id=consecutivo;
        //System.out.print("jugador"+id+" consecutivo"+consecutivo);
        //consecutivo++;
        et = new EscuchaTablero();
        et.start();
        lbl2.setVisible(false);
        ganlbl.setVisible(false);
        jugadorlbl.setText("Jugador: " + username);
    }

    public int getId() {
        return id;
    }
    

    public void setPosTopo() {
        this.posTopo = posTopo;
        System.out.println(this.posTopo);
        update();       
    }
    
    
    public void update(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        lbl = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Puntajelbl = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        jugadorlbl = new javax.swing.JLabel();
        ganlbl = new javax.swing.JLabel();
        puntoslbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn1.setBackground(new java.awt.Color(153, 204, 255));
        btn1.setBorderPainted(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(153, 204, 255));
        btn3.setBorderPainted(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(153, 204, 255));
        btn2.setBorderPainted(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(153, 204, 255));
        btn4.setBorderPainted(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(153, 204, 255));
        btn5.setBorderPainted(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setBackground(new java.awt.Color(153, 204, 255));
        btn6.setBorderPainted(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setBackground(new java.awt.Color(153, 204, 255));
        btn7.setBorderPainted(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setBackground(new java.awt.Color(153, 204, 255));
        btn8.setBorderPainted(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn9.setBackground(new java.awt.Color(153, 204, 255));
        btn9.setBorderPainted(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl.setFont(new java.awt.Font("Comfortaa Light", 1, 24)); // NOI18N
        lbl.setText("Whack A Mole");

        lbl2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        lbl2.setText("El juego reiniciará en unos segundos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Puntaje");

        Puntajelbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Puntajelbl.setText("0");

        lbl1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jugadorlbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jugadorlbl.setText("Jugador :");

        ganlbl.setText("El jugador : ha ganado");

        puntoslbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        puntoslbl.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jugadorlbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbl)
                                .addGap(35, 35, 35)))
                        .addGap(134, 134, 134)
                        .addComponent(Puntajelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbl2)
                                .addGap(177, 177, 177))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ganlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(puntoslbl)
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Puntajelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jugadorlbl)
                    .addComponent(lbl))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl1)
                    .addComponent(ganlbl, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(puntoslbl)
                .addGap(4, 4, 4)
                .addComponent(lbl2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
        casilla = 1;
        envia();
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
        casilla = 2;
        envia();
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        casilla = 3;
        envia();
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        casilla = 4;
        envia();
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        casilla = 5;
        envia();
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
        casilla = 6;
        envia();
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
        casilla = 7;
        envia();
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
        casilla = 8;
        envia();
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
        casilla = 9;
        envia();
    }//GEN-LAST:event_btn9ActionPerformed
    
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
         //
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui_juego(udpSocket, multiSocket, multiGroup,username).setVisible(true);
                
            }
        });
                        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Puntajelbl;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JLabel ganlbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jugadorlbl;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel puntoslbl;
    // End of variables declaration//GEN-END:variables

    private void envia(){
	DatagramSocket aSocket = null;
	  try {
		aSocket = new DatagramSocket();
                StringBuilder sb = new StringBuilder();
                sb.append(casilla);
                sb.append(username);
		String myMessage = sb.toString();
                byte [] m = myMessage.getBytes();
		
                InetAddress aHost = InetAddress.getByName("localhost");
                //InetAddress aHost = InetAddress.getByAddress("localhost", new byte[] {(byte)148, (byte)205, (byte)199 ,(byte) 100});
		int serverPort = udpSocket;
    		DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
                System.out.println(myMessage);
    		aSocket.send(request);	
                
	  }
          catch (SocketException e){
                System.out.println("Socket: " + e.getMessage());
	  }
          catch (IOException e){
              System.out.println("IO: " + e.getMessage());
          }
	  finally{
            if(aSocket != null) 
                aSocket.close();
         }
        

    }
    
    private void asignaColor(){
            grises();
            Color morado = new Color(144, 24, 173);
            switch (topo_activo) {
            case 1:  btn1.setBackground(morado);
                     break;
            case 2:  btn2.setBackground(morado);
                     break;
            case 3:  btn3.setBackground(morado);
                     break;
            case 4:  btn4.setBackground(morado);
                     break;
            case 5:  btn5.setBackground(morado);
                     break;
            case 6:  btn6.setBackground(morado);
                     break;
            case 7:  btn7.setBackground(morado);
                     break;
            case 8:  btn8.setBackground(morado);
                     break;
            case 9:  btn9.setBackground(morado);
                     break;
            default: 
                     break;
        }        
    }
    
    private void grises(){
        Color azul = new Color(153,204,255);
        btn1.setBackground(azul);
        btn2.setBackground(azul);
        btn3.setBackground(azul);
        btn4.setBackground(azul);
        btn5.setBackground(azul);
        btn6.setBackground(azul);
        btn7.setBackground(azul);
        btn8.setBackground(azul);
        btn9.setBackground(azul);
    }
    class EscuchaTablero extends Thread{
        
        public EscuchaTablero(){
            System.out.println("Escucha topos lista");
        }
 

    
        @Override
        public void run(){
            
            MulticastSocket s =null;
             try {
                    InetAddress group = InetAddress.getByName(multiGroup); // destination multicast group 
                    s = new MulticastSocket(multiSocket);
                    s.joinGroup(group);
                    System.out.println("Esperando topos");

                    byte[] buffer = new byte[1000];
                    String ganador;
                    ByteArrayInputStream bais;
                    ObjectInputStream ois;
                    while(true) {
                        DatagramPacket messageIn = 
                            new DatagramPacket(buffer, buffer.length);
                        s.receive(messageIn);
                        bais = new ByteArrayInputStream(buffer);
                        ois = new ObjectInputStream(bais);
                        Object readObject = ois.readObject();
                        Puntero punt = (Puntero)readObject;
                        topo_activo = punt.getTopo();
                     
                        puntoslbl.setText(String.valueOf(punt.puntaje(username)));
                        if (topo_activo == 0){
                            grises();
                            //mensajear ganador
                            ganador = punt.ganador();
                            if (ganador.equals(username)){
                                //ganó
                                lbl.setText("Haz ganado");
                                lbl2.setVisible(true);
                                ganlbl.setVisible(false);
                                
                            }else{
                                //perdió
                                lbl.setText("Haz perdido");
                                ganlbl.setVisible(true);
                                ganlbl.setText("El ganador fue: " + ganador);
                                lbl2.setVisible(true);
                            }
                        }else{
                            grises();
                            asignaColor();
                            lbl2.setVisible(false);
                            lbl.setText("Whack A Mole");
                            ganlbl.setVisible(false);
                        }

                    }		
                }
             catch (SocketException e){
                 System.out.println("Socket: " + e.getMessage());
             }
             catch (IOException e){
                 System.out.println("IO: " + e.getMessage());
             } catch (ClassNotFoundException ex) {
                Logger.getLogger(gui_juego.class.getName()).log(Level.SEVERE, null, ex);
            }
             finally {
                if(s != null) s.close();
            }
        }
    }
}

