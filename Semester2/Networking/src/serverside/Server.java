package serverside;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import client.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class Server extends Application {	
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			Scene scene = new Scene(root,400,400);
			Button b1 = new Button("I Like To Rotate");
			root.getChildren().add(b1);



			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Server at " + InetAddress.getLocalHost());

			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
				
			});

			new Thread(() -> {
				try {					
					//One for the server
					ServerSocket serverSocket = new ServerSocket(8000);

					//Allows the client to connect to the server
					Socket socket = serverSocket.accept();

					//Creates a data input stream from the socket
					DataInputStream fromClient = new DataInputStream(socket.getInputStream());

					while (true) {

						int currentTask = fromClient.readInt();
						if(currentTask == 0)
							Platform.runLater(() -> b1.setRotate(b1.getRotate() + 10));
						else if(currentTask == 1)
						{
							String color = fromClient.readUTF();
							Platform.runLater(() -> {
								try {
									root.setStyle("-fx-background-color: " + color + ";");
								} catch (Exception e1) {}
							});
						}

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
}
