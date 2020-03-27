package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import observable.ObservableStringBuffer;
import service.SimpleFTP;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Application implements Initializable {
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
    TextArea reqResTA;

    ConnectionModel connectionModel;

    SimpleFTP simpleFTP;

    private ObservableStringBuffer buffer;

    public MainController()
    {
        connectionModel =new ConnectionModel();
        simpleFTP = new SimpleFTP();
        buffer= ObservableStringBuffer.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        primaryStage.setTitle("Client FTP");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
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
            reqResTA.setText(e.getMessage());
        }
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reqResTA.textProperty().bind(buffer);
    }
}
