/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author naofa
 */
public class Client {

    private final int PORT_NUMBER = 1998;
    private Socket socketClient;
    private ClientController clientController;

    public Client(Socket socketClient,ClientController clientController) {
        this.socketClient = socketClient;
        this.clientController = clientController;
    }
    

    
//    
//    public Client() throws UnknownHostException, IOException{
//    }
//    
    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner scn = new Scanner(System.in);
        Socket client;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        InetAddress ip = InetAddress.getByName("localhost");
        client = new Socket(ip, PORT_NUMBER);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        // sendMessage thread 
        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // read the message to deliver. 
                    String msg = scn.nextLine();

                    try {
                        // write on the output stream 
                        dos.writeUTF(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // readMessage thread 
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client 
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();
    }
}
