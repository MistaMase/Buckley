package application;


import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,800);

			Circle cursor = new Circle(400, 400, 4, Color.RED);
			cursor.setFill(Color.RED);
			root.getChildren().add(cursor);
			
			int[][] cursorHistory = new int[800][800];
			for(int i = 0; i < 800; i++)
			{
				for(int j = 0; j < 800; j++)
				{
					cursorHistory[i][j] = 50;
				}
			}
			
			


			Timeline walk = new Timeline(new KeyFrame(Duration.millis(100), e -> {
				int newPath = (int)(Math.random() * 4) + 1;
				if(newPath == 1)
				{
					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX() - 1, cursor.getCenterY());
					PathTransition pt = new PathTransition(Duration.millis(80), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursorHistory[(int)(cursor.getCenterX())-1][(int)cursor.getCenterY()] = cursorHistory[(int)(cursor.getCenterX()) - 1][(int) cursor.getCenterY()] - 10;
						cursor.setLayoutX(0);
						cursor.setTranslateX(0);
						cursor.setCenterX(cursor.getCenterX() - 1);
						newWalk.setStroke(Color.GRAY);
						newWalk.setOpacity(cursorHistory[(int)(cursor.getCenterX())-1][(int)cursor.getCenterY()]/100);
						//System.out.println(newWalk.getOpacity());
						root.getChildren().add(newWalk);
						cursor.toFront();
					});

				}
				else if(newPath == 2)
				{
					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX(), cursor.getCenterY() + 1);
					PathTransition pt = new PathTransition(Duration.millis(80), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursorHistory[(int) cursor.getCenterX()][(int)(cursor.getCenterY()) + 1] = cursorHistory[(int) cursor.getCenterX()][(int)(cursor.getCenterY())+1] - 10;
						cursor.setTranslateY(0);
						cursor.setLayoutY(0);
						cursor.setCenterY(cursor.getCenterY() + 1);
						newWalk.setStroke(Color.GRAY);
						newWalk.setOpacity(cursorHistory[(int) cursor.getCenterX()][(int)(cursor.getCenterY()) + 1]/100);
						//System.out.println(newWalk.getOpacity());
						root.getChildren().add(newWalk);
						cursor.toFront();
					});
				}
				else if(newPath == 3)
				{
					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX() + 1, cursor.getCenterY());
					PathTransition pt = new PathTransition(Duration.millis(80), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursorHistory[(int)(cursor.getCenterX())-1][(int)cursor.getCenterY()] = cursorHistory[(int)(cursor.getCenterX())-1][(int) cursor.getCenterY()] - 10;
						cursor.setLayoutX(0);
						cursor.setTranslateX(0);
						cursor.setCenterX(cursor.getCenterX() + 1);
						newWalk.setStroke(Color.GRAY);
						newWalk.setOpacity(cursorHistory[(int)(cursor.getCenterX())-1][(int)cursor.getCenterY()]/100);
						//System.out.println(newWalk.getOpacity());
						root.getChildren().add(newWalk);
						cursor.toFront();
					});
				}
				else if(newPath == 4)
				{
					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX(), cursor.getCenterY() - 1);
					PathTransition pt = new PathTransition(Duration.millis(80), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursorHistory[(int) cursor.getCenterX()][(int)(cursor.getCenterY())-1] = cursorHistory[(int) cursor.getCenterX()][(int)(cursor.getCenterY())-1] - 10;
						cursor.setLayoutY(0);
						cursor.setTranslateY(0);
						cursor.setCenterY(cursor.getCenterY() - 1);
						newWalk.setStroke(Color.GRAY);
						newWalk.setOpacity(cursorHistory[(int) cursor.getCenterX()][(int)(cursor.getCenterY())-1]/100);
						//System.out.println(newWalk.getOpacity());
						root.getChildren().add(newWalk);
						cursor.toFront();
					});
				}
			}));
			walk.setCycleCount(Timeline.INDEFINITE);
			walk.play();


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
