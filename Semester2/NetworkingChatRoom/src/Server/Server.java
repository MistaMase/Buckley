package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Server extends Application {
	
	DataOutputStream toClient;
	DataInputStream fromClient;

	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			
			ScrollPane chatBox = new ScrollPane();
			chatBox.setVbarPolicy(ScrollBarPolicy.NEVER);
			chatBox.setHbarPolicy(ScrollBarPolicy.NEVER);
			TextArea newMessageBox = new TextArea();
			newMessageBox.setPrefHeight(30);
			newMessageBox.setMinHeight(30);
			newMessageBox.setWrapText(true);
			VBox layout = new VBox();
			VBox messages = new VBox();
			chatBox.setMinWidth(300);
			messages.setMinWidth(300);
			chatBox.setPrefSize(300, 400);
			chatBox.setContent(messages);
			layout.getChildren().addAll(chatBox, newMessageBox);
			root.getChildren().add(layout);
			chatBox.vvalueProperty().bind(messages.heightProperty());

		
			Scene scene = new Scene(root,300,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setTitle("Server at " + InetAddress.getLocalHost());
			System.out.println(InetAddress.getLocalHost());
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			
			newMessageBox.setOnKeyPressed(e -> {
				if(newMessageBox.getText() != "" && e.getCode() == KeyCode.ENTER)
					try {
						toClient.writeUTF(newMessageBox.getText().replace("\n", ""));
						Label l = new Label(newMessageBox.getText().replace("\n", ""));
						l.setTextAlignment(TextAlignment.RIGHT);
						l.setAlignment(Pos.BASELINE_RIGHT);
						l.setMaxWidth(300);
						l.setMinWidth(300);
						l.setPrefWidth(300);
						l.setWrapText(true);
						l.setStyle("-fx-text-fill: #ff2146; ");
						messages.getChildren().add(l);	
						newMessageBox.setText("");
					} catch (IOException e1) {}
			});
			
			
			new Thread(() -> {
				Platform.runLater(() -> new Client.Client().start(new Stage())); 
			}).start();

						
			new Thread(() -> {
				try {
					startServer();
					while(true)
					{
						String newText = fromClient.readUTF();
						Platform.runLater(() -> {
							Label l = new Label(newText);
							l.setTextAlignment(TextAlignment.LEFT);
							l.setMaxWidth(300);
							l.setMinWidth(300);
							l.setPrefWidth(300);
							l.setWrapText(true);
							l.setStyle("-fx-text-fill: #68a2ff; ");
							messages.getChildren().add(l);	
						});
					}
				} catch (Exception e) {}
			}).start();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void startServer() {
		try {
		ServerSocket serverSocket = new ServerSocket(8000);
		Socket socket = serverSocket.accept(); 
		toClient = new DataOutputStream(socket.getOutputStream());
		fromClient = new DataInputStream(socket.getInputStream());
		
		} catch(Exception e) {}
	}
	
}
