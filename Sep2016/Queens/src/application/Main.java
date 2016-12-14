package application;

import javax.swing.JOptionPane;


import QueensLogic.NQueens;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class Main extends Application {

	public static int size;

	@Override
	public void start(Stage primaryStage) {
		try {

			size = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of rows/columns"));

			GridPane root = new GridPane();
			Scene scene = new Scene(root,800,800);
			int board[][] = NQueens.solve(size);
			//printArr(board);
			Image image = new Image("https://openclipart.org/image/2400px/svg_to_png/18663/portablejim-Chess-tile-Queen-1.png");
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					Pane p1 = new Pane();
					p1.getStyleClass().add("p1");
					Label l = new Label();
					
					if(board[i][j] == 1)
					{
						ImageView iv = new ImageView(image);
						iv.setFitHeight(0.75 *(800/size));
						iv.setFitWidth(0.75 *(800/size));
						l.setGraphic(iv);
						l.setAlignment(Pos.CENTER);
						l.setPrefHeight(800/size);
						l.setPrefWidth(800/size);
						p1.getChildren().add(l);
						root.add(p1, j, i);
					}
					else
					{
						l.setPrefHeight(800/size);
						l.setPrefWidth(800/size);
						p1.getChildren().add(l);
						root.add(p1, j, i);
					}
				}

			}

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void printArr(int[][] a) {
		for (int[] i :a) {
			for (int j: i) {
				System.out.print(j == 0 ? " _ " : " Q ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
