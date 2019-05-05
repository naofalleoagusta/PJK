/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverchat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author naofa
 */
public class ClientController implements Initializable {
    
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField chatSendArea;
    @FXML
    private Button sendButton;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setChatAreaText(String msg){
        this.chatArea.setText(this.chatArea.getText()+"\n"+msg);
    }
    
    public void resetChatSendArea(){
        this.chatSendArea.setText("");
    }
}
