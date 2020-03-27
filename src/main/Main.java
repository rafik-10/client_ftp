package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import metier.SimpleFTP;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Client FTP");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args){
        // launch(args);
        SimpleFTP clientFtp = new SimpleFTP();

        try
        {
            clientFtp.connect("127.0.0.1",21,"nassim","369001");

            Thread.sleep(5000);
            File file= new File("src/resources/test-envoie-fichier.txt");
            clientFtp.stor(file);
            //clientFtp.disconnect();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
