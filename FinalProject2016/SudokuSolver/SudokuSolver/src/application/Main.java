package application;
	
import RecursionSolver.Solver;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;


public class Main extends Application {
	
	private String[][] board = new String[9][9];
	TextField[][] fields = new TextField[9][9];
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Pane root = new Pane();
			VBox vb = new VBox();
			primaryStage.setTitle("Sudoku Solver");
			vb.getStyleClass().add("vb");
			GridPane almostRoot = new GridPane();
			Scene scene = new Scene(root,450,500);
					
			Line line = new Line(-400, 225, 400, 225);
			MediaPlayer mp = new MediaPlayer(new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "sounds/NyanCatoriginal.mp3"));
			Image nyancat = new Image("https://s-media-cache-ak0.pinimg.com/originals/01/d1/41/01d1418ce594c386e3515dea83673111.gif");
			ImageView cat = new ImageView(nyancat);
			cat.setVisible(false);
			cat.setFitHeight(400);
			cat.setPreserveRatio(true);
			cat.setBlendMode(BlendMode.OVERLAY);

			
			
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
				{
					GridPane gp = new GridPane();
					for(int k = 0; k < 3; k++)
					{
						for(int l = 0; l < 3; l++)
						{
							fields[i*3 + k][j*3 + l] = new TextField();
							fields[i*3 + k][j*3 + l].setPrefHeight(50);
							fields[i*3 + k][j*3 + l].setPrefWidth(50);
							fields[i*3 + k][j*3 + l].getStyleClass().add("tf");
							gp.add(fields[i*3 + k][j*3 + l], l, k);
							
						}
					}
					gp.setPrefSize(150, 150);
					gp.getStyleClass().add("gp");
					almostRoot.add(gp, j, i);
					
				}
			
			
			vb.getChildren().add(almostRoot);
			
			HBox hb = new HBox();
			hb.setPrefSize(450, 100);
			
			Button clear = new Button("CLEAR");
			clear.setPrefSize(450/2, 50);
			clear.getStyleClass().add("clear");
			
			Button solve = new Button("SOLVE");
			solve.setPrefSize(450/2, 50);
			solve.getStyleClass().add("solve");

			
			hb.getChildren().addAll(solve, clear);
			vb.getChildren().add(hb);
			
			root.getChildren().addAll(vb, cat);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			clear.setOnAction(e -> {
				
				for(int i = 0; i < 9; i++)
					for(int j = 0; j < 9; j++)
					{
						fields[i][j].setText("");
					}
			});
			
			solve.setOnAction(e -> {
				
				PathTransition ncmove = new PathTransition();
				ncmove.setDuration(Duration.millis(3500));
				ncmove.setPath(line);
				cat.setVisible(true);
				mp.seek(Duration.millis(3750));
				mp.play();
				ncmove.setCycleCount(1);
				ncmove.setNode(cat);
				ncmove.setInterpolator(Interpolator.LINEAR);
				ncmove.play();
				ncmove.setOnFinished(g -> {
					cat.setVisible(false);
					mp.pause();
				});
				
				for(int i = 0; i < 9; i++)
					for(int j = 0; j < 9; j++)
					{
						board[i][j] = fields[i][j].getText();
					}
				
				
				Solver s = new Solver(board);
				int[][] tempBoard = s.getSolved();
				
				for(int i = 0; i < 9; i++)
					for(int j = 0; j < 9; j++)
						fields[i][j].setText("" + tempBoard[i][j]);
				
				
			});
			
			
			root.setOnKeyPressed(e -> {
				switch (e.getCode())
				{
					case CONTROL: System.out.println(mp.getStatus()); if(mp.getStatus() == MediaPlayer.Status.READY || mp.getStatus() == MediaPlayer.Status.PAUSED) { mp.seek(Duration.millis(0)); mp.play(); } else mp.pause(); break;
					default: break;
				}
			});		
				
			
			

			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
