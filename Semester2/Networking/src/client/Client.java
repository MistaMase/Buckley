package client;
	
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class Client extends Application {
	
	DataOutputStream toServer = null;
	String serverAddress = "";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			Scene scene = new Scene(root,400,400);
			Button b1 = new Button("Rotate Dem Buttons");
			b1.setAlignment(Pos.CENTER);
			TextField tf = new TextField();
			tf.setPromptText("Enter a color");
			VBox vb = new VBox(25);
			vb.setAlignment(Pos.CENTER);
			vb.getChildren().addAll(b1, tf);
			tf.setPrefSize(300, 75);
			root.getChildren().add(vb);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.show();
			primaryStage.setTitle("Client at " + InetAddress.getLocalHost());
			
			//Initial Server Address Screen
			StackPane serverAdd = new StackPane();
			Scene first = new Scene(serverAdd, 400, 400);
			TextField stringAdd = new TextField();
			stringAdd.setPromptText("Enter the IP Address of your server");
			stringAdd.setPrefSize(300, 75);
			serverAdd.getChildren().add(stringAdd);
			primaryStage.setScene(first);
			
			stringAdd.setOnAction(e -> {
				serverAddress = stringAdd.getText();
				connectToServer();
				primaryStage.setScene(scene);
			});
			
			tf.setOnAction(e -> {
				try {
					toServer.writeInt(1);
					toServer.writeUTF(tf.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				tf.setText("");
			});
			
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			
			
			b1.setOnAction(e -> {
				try {
					toServer.writeInt(0);
				} catch (Exception f) {}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	void connectToServer() {
		try {
			Socket socket = new Socket(serverAddress, 8000);
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {}
		
	}
}
