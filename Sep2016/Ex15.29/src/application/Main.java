package application;
	
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;


public class Main extends Application {	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			
			Polyline stopsignpost = new Polyline();
			stopsignpost.getPoints().addAll(315., 295., 325., 295., 325., 400., 315., 400., 315., 295.);
			stopsignpost.setFill(Color.BLACK);
			root.getChildren().add(stopsignpost);
			
			Polyline road = new Polyline();
			road.getPoints().addAll(0., 400., 0., 370., 400., 370., 400., 400., 400.);
			road.setFill(Color.GRAY);
			root.getChildren().add(road);
			
			Polyline stopsign = new Polyline();
			stopsign.getPoints().addAll(310., 250., 330., 250., 340., 260., 340., 280., 330., 290., 310., 290., 300., 280., 300., 260., 310., 250.);
			stopsign.setFill(Color.RED);
			stopsign.setScaleX(1.2);
			stopsign.setScaleY(1.2);
			root.getChildren().add(stopsign);
			
			Polyline pline = new Polyline();
			pline.getPoints().addAll(0., 360., 0., 350., 10., 330., 30., 330., 40., 350., 40., 360., 30., 360., 30., 365., 25., 365., 25., 360., 15., 360., 15., 365., 10., 365., 10., 360., 0., 360.);
			pline.setFill(Color.ORANGE);
			root.getChildren().add(pline);
			
			Text stop = new Text("STOP");
			stop.setX(303);
			stop.setY(275);
			stop.setStroke(Color.WHITE);
			root.getChildren().add(stop);
			
			Timeline tline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
				pline.setLayoutX((pline.getLayoutX() == 400) ? -20:(pline.getLayoutX() + 1));
			}));
			tline.setRate(50);
			tline.setCycleCount(Timeline.INDEFINITE);
			tline.play();
			Text speed = new Text("" + tline.getRate() + " px/sec");
			speed.setX(20);
			speed.setY(20);
			root.getChildren().add(speed);
			
			root.setOnKeyPressed(e -> {
				switch(e.getCode())
				{
				case UP: tline.setRate((int)(tline.getRate() * 1.1)); speed.setText(tline.getRate() + " px/sec"); break;
				case DOWN: if(tline.getRate() >= 20) { tline.setRate((int)(tline.getRate() * .9)); speed.setText(tline.getRate() + " px/sec"); } break;
				case SPACE: if(tline.getStatus() == Animation.Status.RUNNING) { tline.pause(); speed.setText("0 px/sec"); } else if(tline.getStatus() == Animation.Status.PAUSED) { tline.play(); speed.setText(tline.getRate() + " px/sec"); }  break;
				default: break;
				}
			});
			
			
						
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			root.requestFocus();
			pline.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
