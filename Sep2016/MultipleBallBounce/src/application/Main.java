package application;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author vschwartz
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		MultipleBallPane ballPane = new MultipleBallPane();
		ballPane.setStyle("-fx-border-color: yellow");
		Button btAdd = new Button("+");
		Button btSubtract = new Button("-");
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(btAdd, btSubtract);
		hBox.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(ballPane);
		pane.setBottom(hBox);

		Scene scene = new Scene(pane, 800, 800);       
		primaryStage.setTitle("MultipleBounceBall");
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		btAdd.setOnAction(e -> ballPane.add());
		btSubtract.setOnAction(e -> ballPane.subtract());
		ballPane.setOnMousePressed(e -> ballPane.pause());
		ballPane.setOnMouseReleased(e -> ballPane.play());
		ballPane.setOnMouseClicked(e -> {
			Ball b = new Ball(e.getX(), e.getY(), 20, Color.BLUE);
			b.dx = 0;
			b.dy = 0;
			ballPane.getChildren().add(b);
			
		});



		}

		/**
		 * @param args the command line arguments
		 */
		public static void main(String[] args) {
			launch(args);
		}

		private class MultipleBallPane extends Pane {
			private Timeline animation;

			public MultipleBallPane() {
				animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.play();
			}

			public void add() {
				Color color = new Color(Math.random(),
						Math.random(), Math.random(), .5);
				getChildren().add(new Ball(Math.random() * 700 + 50, Math.random() * 700 + 50, 20, color));
			}

			public void subtract() {
				if (getChildren().size() > 0)
					getChildren().remove(getChildren().size() - 1);
			}

			public void play() {
				animation.play();
			}

			public void pause() {
				animation.pause();
			}

			public void increaseSpeed() {
				animation.setRate(animation.getRate() + .1);
			}

			public void decreaseSpeed() {
				animation.setRate(animation.getRate() > 0 ? animation.getRate() - .1 : 0);
			}

			public DoubleProperty rateProperty() {
				return animation.rateProperty();
			}

			protected void moveBall() {
				for (Node node: this.getChildren()) {
					Ball ball = (Ball)node;

					if (ball.getCenterX() < ball.getRadius() ||
							ball.getCenterX() > getWidth() - ball.getRadius())
						ball.dx *= -1;

					if (ball.getCenterY() < ball.getRadius() ||
							ball.getCenterY() > getHeight() - ball.getRadius())
						ball.dy *= -1; 


					for(int i = 0; i < getChildren().size(); i++)
					{
						if(getChildren().get(i) instanceof Ball && !getChildren().get(i).equals(ball))
						{
							
							if(Math.abs(ball.getCenterX() - ((Ball)getChildren().get(i)).getCenterX()) <= 20 && Math.abs(ball.getCenterY() - ((Ball)getChildren().get(i)).getCenterY()) <= 20)
							{
								/////UNCOMMENT FOR BOUNCING BALLS/////////
								ball.dx = -ball.dx;
								ball.dy = -ball.dy;
								((Ball)getChildren().get(i)).dx = -((Ball)getChildren().get(i)).dx;
								((Ball)getChildren().get(i)).dy = -((Ball)getChildren().get(i)).dy;
								
//								////UNCOMMENT FOR COMBINING BALLS////////
//								ball.setCenterX((ball.getCenterX() + ((Ball)getChildren().get(i)).getCenterX())/2);
//								ball.setCenterY((ball.getCenterY() + ((Ball)getChildren().get(i)).getCenterY())/2);
//								removeTargetBall(i);
							}
						}
					}


					ball.setCenterX(ball.dx + ball.getCenterX());
					ball.setCenterY(ball.dy + ball.getCenterY());
				}
			}
			
			public void removeTargetBall(int i)
			{
				getChildren().remove(i);
			}
		}  
		

		class Ball extends Circle {
			public double dx = Math.random() * 20 + 1, dy = Math.random() * 20 + 1;

			Ball(double x, double y, double radius, Color color) {
				super(x, y, radius);
				setFill(color);
			}
		}
	}
