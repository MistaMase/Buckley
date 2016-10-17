package application;

import java.util.ArrayList;

import javafx.animation.Animation;
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

	public Timeline walk;

	@Override
	public void start(Stage primaryStage) {
		
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,800);

			Circle cursor = new Circle(400, 400, 4, Color.RED);
			cursor.setFill(Color.RED);
			root.getChildren().add(cursor);
			
			for(int i = 0; i <= 800; i+= 20)
			{
				Line graph = new Line(i, 0, i, 800);
				graph.setStroke(Color.LIGHTGRAY);
				root.getChildren().add(graph);
			}
			for(int i = 0; i <= 800; i += 20)
			{
				Line graph = new Line(0, i, 800, i);
				graph.setStroke(Color.LIGHTGRAY);
				root.getChildren().add(graph);
			}
			

			boolean[][] cursorHistory = new boolean[801][801];
			cursorHistory[400][400] = true;
			
			cursor.toFront();




			walk = new Timeline(new KeyFrame(Duration.millis(250), e -> {
				ArrayList<Integer> possible = new ArrayList<Integer>();
				possible.add(new Integer(1));
				possible.add(new Integer(2));
				possible.add(new Integer(3));
				possible.add(new Integer(4));
				try{
					if(cursorHistory[(int)(cursor.getCenterX()) - 20][(int)(cursor.getCenterY())] == true)
					{
						possible.remove(possible.indexOf(new Integer(1)));
					}
				} catch(ArrayIndexOutOfBoundsException ex)
				{
					System.out.println("HI");
					possible.remove("1");
				}

				try{
					if(cursorHistory[(int)(cursor.getCenterX())][(int)(cursor.getCenterY()) + 20] == true)
					{
						possible.remove(possible.indexOf(new Integer(2)));
					}
				} catch(ArrayIndexOutOfBoundsException ex)
				{
					System.out.println("HI");
					possible.remove("2");
				}
				try{
					if(cursorHistory[(int)(cursor.getCenterX()) + 20][(int)(cursor.getCenterY())] == true)
					{
						possible.remove(possible.indexOf(new Integer(3)));
					}
				} catch(ArrayIndexOutOfBoundsException ex)
				{
					System.out.println("HI");
					possible.remove("3");
				}
				try{
					if(cursorHistory[(int)(cursor.getCenterX())][(int)(cursor.getCenterY()) - 20] == true)
					{
						possible.remove(possible.indexOf(new Integer(4)));
					}
				} catch(ArrayIndexOutOfBoundsException ex)
				{
					System.out.println("HI");
					possible.remove("4");
				}

				int newPath;

				if(possible.size() == 0)
					newPath = 0;
				else
				{
					newPath = (int)(Math.random() * 4) + 1;

					//System.out.println("Random: " + newPath);

					while(!possible.contains(newPath) && walk.getStatus() == Animation.Status.RUNNING)
					{
						newPath = (int)(Math.random() * 4) + 1;
						//System.out.println("Random: " + newPath);

					}
				}

				if(newPath == 0)
				{
					walk.pause();
				}
				else if(newPath == 1)
				{
					cursorHistory[(int)(cursor.getCenterX()) - 20][(int)(cursor.getCenterY())] = true;
					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX() - 20, cursor.getCenterY());
					PathTransition pt = new PathTransition(Duration.millis(200), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursor.setLayoutX(0);
						cursor.setTranslateX(0);
						cursor.setCenterX(cursor.getCenterX() - 20);
						newWalk.setStroke(Color.BLACK);
						newWalk.setOpacity(100);
						root.getChildren().add(newWalk);
						cursor.toFront();
					});

				}
				else if(newPath == 2)
				{
					cursorHistory[(int)(cursor.getCenterX())][(int)(cursor.getCenterY()) + 20] = true;

					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX(), cursor.getCenterY() + 20);
					PathTransition pt = new PathTransition(Duration.millis(200), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursor.setTranslateY(0);
						cursor.setLayoutY(0);
						cursor.setCenterY(cursor.getCenterY() + 20);
						newWalk.setStroke(Color.BLACK);
						newWalk.setOpacity(100);
						root.getChildren().add(newWalk);
						cursor.toFront();
					});
				}
				else if(newPath == 3)
				{
					cursorHistory[(int)(cursor.getCenterX()) +  20][(int)(cursor.getCenterY())] = true;

					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX() + 20, cursor.getCenterY());
					PathTransition pt = new PathTransition(Duration.millis(200), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursor.setLayoutX(0);
						cursor.setTranslateX(0);
						cursor.setCenterX(cursor.getCenterX() + 20);
						newWalk.setStroke(Color.BLACK);
						newWalk.setOpacity(100);
						root.getChildren().add(newWalk);
						cursor.toFront();
					});
				}
				else if(newPath == 4)
				{
					cursorHistory[(int)(cursor.getCenterX())][(int)(cursor.getCenterY()) - 20] = true;

					Line newWalk = new Line(cursor.getCenterX(), cursor.getCenterY(), cursor.getCenterX(), cursor.getCenterY() - 20);
					PathTransition pt = new PathTransition(Duration.millis(200), newWalk, cursor);
					pt.play();
					pt.setOnFinished(f -> {
						cursor.setLayoutY(0);
						cursor.setTranslateY(0);
						cursor.setCenterY(cursor.getCenterY() - 20);
						newWalk.setStroke(Color.BLACK);
						newWalk.setOpacity(100);
						root.getChildren().add(newWalk);
						cursor.toFront();
					});
				}

				//				for(int i = 0; i < 800; i++)
				//				{
				//					for(int j = 0; j < 800; j++)
				//					{
				//						if(cursorHistory[i][j] == true)
				//							System.out.println(i + ", " + j);
				//					}
				//
				//				}
			}));
			walk.setCycleCount(Timeline.INDEFINITE);


			root.setOnMouseClicked(e -> {
//				if(walk.getStatus() == Animation.Status.PAUSED)
//				{
//					System.out.println("HI");
//					for(int i = 0; i < 801; i++)
//					{
//						for(int j = 0; j < 801; j++)
//						{
//							cursorHistory[i][j] = false;
//						}
//					}
//					
//					while()
//					cursor.setCenterX(400);
//					cursor.setCenterY(400);
//					cursorHistory[400][400] = true;
//				}
				walk.playFromStart();
			});




			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}



	public static void main(String[] args) {
		launch(args);
	}
}
