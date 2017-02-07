package Client;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


public class Client extends Application {
	
	DataOutputStream toServer;
	DataInputStream fromServer;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			
			ScrollPane chatBox = new ScrollPane();
			chatBox.setVbarPolicy(ScrollBarPolicy.NEVER);
			chatBox.setHbarPolicy(ScrollBarPolicy.NEVER);
			TextArea newMessageBox = new TextArea();
			newMessageBox.setPrefHeight(28);
			newMessageBox.setMinHeight(28);
			newMessageBox.setWrapText(true);
			VBox layout = new VBox();
			VBox messages = new VBox();
			chatBox.setPrefSize(300, 400);
			messages.setMinWidth(300);
			chatBox.setContent(messages);
			layout.getChildren().addAll(chatBox, newMessageBox);
			root.getChildren().add(layout);
			chatBox.vvalueProperty().bind(messages.heightProperty());

			
			
			Scene scene = new Scene(root,300,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setTitle("Client at " + InetAddress.getLocalHost());
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			
			newMessageBox.setOnKeyPressed(e -> {
				if(newMessageBox.getText() != "" && e.getCode() == KeyCode.ENTER)
					try {
						toServer.writeUTF(newMessageBox.getText().replace("\n", ""));
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
			
			connectToServer();
			
			new Thread(() -> {
				try {
					while(true)
					{
						String newText = fromServer.readUTF();
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
	
	public void connectToServer() {
		try {
			Socket socket = new Socket("localhost", 8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {}
	}
}
