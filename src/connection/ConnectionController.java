package connection;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.SimpleFTP;

public class ConnectionController extends Application {

    @FXML
    Button connecterBtn;

    @FXML
    TextField hoteTF;

    @FXML
    TextField idTF;

    @FXML
    TextField passwdPF;

    @FXML
    TextField portTF;

    @FXML
    Label errorMsgLbl;

    ConnectionModel connectionModel;

    SimpleFTP simpleFTP;

    public ConnectionController ()
    {
        connectionModel =new ConnectionModel();
        simpleFTP = new SimpleFTP();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("connection.fxml"));
        primaryStage.setTitle("Connexion au serveur FTP");
        primaryStage.setScene(new Scene(root, 800, 400));

        primaryStage.show();
    }

    @FXML
    public void seConnecter(ActionEvent actionEvent) {
        connectionModel.setHote(hoteTF.getText());
        connectionModel.setId(idTF.getText());
        connectionModel.setPasswd(passwdPF.getText());
        connectionModel.setPort(Integer.parseInt(portTF.getText()));

        try {
            simpleFTP.connect(connectionModel);
        }catch (Exception e)
        {
            errorMsgLbl.setText(e.getMessage());
        }
    }
}
