package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			Line xaxis = new Line(0, 200, 400, 200);
			Line yaxis = new Line(200, 0, 200, 400);
			root.getChildren().addAll(xaxis, yaxis);
			Polyline polyline = new Polyline();
			ObservableList<Double> list = polyline.getPoints();
			double scaleFactor = 0.0125;
			for(int x = -100; x <= 100; x++)
			{
				list.add(x + 200.0);
				list.add(scaleFactor * x * x);
			}
			polyline.setRotate(180);
			polyline.setLayoutY(75);
			Text y = new Text(215, 15, "y");
			Text x = new Text(15, 215, "x");
			root.getChildren().addAll(polyline, x, y);
			
			
			
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
