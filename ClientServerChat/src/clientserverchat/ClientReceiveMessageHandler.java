/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverchat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author naofa
 */
public class ClientReceiveMessageHandler extends Thread {

    private Socket socketReceive;
    private DataInputStream dataInputStream;
    private ClientController clientController;
    
    public ClientReceiveMessageHandler(Socket socketReceive,ClientController clientController){
        this.socketReceive = socketReceive;
        this.clientController = clientController;
        try{
            this.dataInputStream = (DataInputStream) this.socketReceive.getInputStream();
        }
        catch(IOException io){
            
        }
    }

    @Override
    public void run() {
        String msg="";
        while (!msg.equals("signout")) {
            try {
                // read the message sent to this client 
                msg = dataInputStream.readUTF();
                this.clientController.setChatAreaText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
