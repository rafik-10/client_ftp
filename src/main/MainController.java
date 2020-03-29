package main;

import file_explorer.SimpleFileTreeItem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import observable.ObservableStringBuffer;
import service.RemoteFileExplorer;
import service.SimpleFTP;

import java.io.File;
import java.io.IOException;
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

    @FXML
    private SplitPane fileExplorerSP;

    private TreeView<String> remoteFilesTV;

    public MainController()
    {
        connectionModel =new ConnectionModel();
        simpleFTP = new SimpleFTP();
        buffer= ObservableStringBuffer.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Client FTP");
        primaryStage.setScene(scene);
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
            buffer.append(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reqResTA.textProperty().bind(buffer);

        TreeView<File> localFilesTV = new TreeView<File>(
                new SimpleFileTreeItem(new File("C:\\")));
        remoteFilesTV=new TreeView(new RemoteFileExplorer(simpleFTP,"/")) ;

        fileExplorerSP.getItems().add(localFilesTV);
        fileExplorerSP.getItems().add(remoteFilesTV);
    }

    public static void main(String[] args){
        launch(args);
    }
}
