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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author naofa
 */
public class ServerController implements Initializable {

    @FXML
    private TextArea logTextArea;
    @FXML
    private Button startServerButton;
    @FXML
    private Button addClientButton;
    private Server server;

    /**
     * Initializes the controller class.
     */

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @FXML
    private void addClient(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void startServer(ActionEvent event) {
        this.server = new Server();
        try {
            this.server.startServer();
            this.logTextArea.setText("Server is started at port "+this.server.getPORT_NUMBER()+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
