package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class Main extends Application {
	
	
	public int times = 1;
	
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root,400,400);
			Color chosenColor = Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
			Circle c1 = new Circle(15, chosenColor);
			root.setOnMouseClicked(e ->
			{
				if(times % 2 == 1)
				{
					c1.setCenterX(e.getX());
					c1.setCenterY(e.getY());
					root.getChildren().add(c1);
					times++;
				}

				else
				{
					Circle c2 = new Circle(e.getX(), e.getY(), 15, chosenColor);
					root.getChildren().add(c2);
					Line distance = new Line(e.getX(), e.getY(), c1.getCenterX(), c1.getCenterY());
					distance.setFill(Color.BLACK);
					Text t = new Text((e.getX() + c1.getCenterX())/2, (e.getY() + c1.getCenterY())/2, "" + Math.sqrt(Math.pow((e.getX() + c1.getCenterX()), 2) + Math.pow((e.getY() + c1.getCenterY()), 2)));
					root.getChildren().addAll(distance, t);
				}
				
			});
	


			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
