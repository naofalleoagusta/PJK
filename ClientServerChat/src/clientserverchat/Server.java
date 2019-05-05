/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Vector;

/**
 *
 * @author naofa
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private final int PORT_NUMBER =1998;
    private boolean toggle = false;
    private static int numClient = 1;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    Vector<ClientHandler> clients = new Vector<ClientHandler>();
    
    public void startServer() throws IOException{
        this.toggle = true;
        this.serverSocket= new ServerSocket(PORT_NUMBER);
        while(this.toggle){
            this.socket = null;
            this.socket = this.serverSocket.accept();
            System.out.println("Client"+numClient+" is connected");
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            ClientHandler ch = new ClientHandler("Client"+numClient, dataInputStream, dataOutputStream, socket);
            clients.add(ch);
            numClient++;
        }
    }

    public int getPORT_NUMBER() {
        return PORT_NUMBER;
    }
    
    
    
    public void sendtoall(String name,String message){
        for (ClientHandler c : clients) {
                c.sendMessage(name,message);
        }
    }
    
    class ClientHandler extends Thread{
        private String name;
        private DataInputStream input;
        private DataOutputStream output;
        private Socket clientSocket;
        
        public ClientHandler(String name, DataInputStream dataInputStream,DataOutputStream dataOutputStream,Socket clientSocket){
            this.name = name;
            this.clientSocket = clientSocket;
            this.input = dataInputStream;
            this.output = dataOutputStream;
            start();
        }
        
        public void sendMessage(String name,String chatmsg){
            try{
                output.writeUTF(name + ": " + chatmsg);
            }
            catch(IOException iOException){
                
            }
        }
        
        @Override
        public void run() {
            String msg="";
            try {
                while (!msg.equals("close")) {
                    msg = input.readUTF();
                    if (msg.equals("end")) {
                        clients.remove(this);
                        break;
                    }
                    sendtoall(this.name,msg); 
                }
                clients.remove(this);
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } 
        
    }
}
