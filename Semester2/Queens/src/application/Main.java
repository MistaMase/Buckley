package application;

import javax.swing.JOptionPane;


import javafx.application.Application;
import javafx.application.Platform;
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
	public static ImageView[][] iv;

	

	@Override
	public void start(Stage primaryStage) {
		try {

			size = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of rows/columns"));
			Label[][] l = new Label[size][size];
			iv = new ImageView[size][size];
			GridPane root = new GridPane();
			Scene scene = new Scene(root,800,800);
			Image image = new Image("https://openclipart.org/image/2400px/svg_to_png/18663/portablejim-Chess-tile-Queen-1.png");
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					Pane p1 = new Pane();
					p1.getStyleClass().add("p1");
					l[i][j] = new Label();

					iv[i][j] = new ImageView(image);
					iv[i][j].setFitHeight(0.75 *(800/size));
					iv[i][j].setFitWidth(0.75 *(800/size));
					iv[i][j].setVisible(false);
					l[i][j].setGraphic(iv[i][j]);
					l[i][j].setAlignment(Pos.CENTER);
					l[i][j].setPrefHeight(800/size);
					l[i][j].setPrefWidth(800/size);
					p1.getChildren().add(l[i][j]);
					root.add(p1, j, i);

				}
			}
			
			NQueens nq = new NQueens(size);
			Thread th = new Thread(nq);
			th.start();			

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			
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
	
public class NQueens implements Runnable {
		int N;
		int[][] board;

		
		public NQueens(int size)
		{
			N = size;
			board = new int[N][N];
			System.out.println("N: " + N);
		}

		public boolean solve(int row) { 
			if (row == N) return true;
			for (int j = 0; j < N; j++) {
				iv[row][j].setVisible(true);
				if (isSafe(row, j)) 
				{
					board[row][j] = 1;
					if (solve(row + 1)) return true;
					board[row][j] = 0;
					iv[row][j].setVisible(false);

				}
				try { Thread.sleep(125); }
				catch(Exception e) {}
				iv[row][j].setVisible(false);

			}
			return false;
		}

		private boolean isSafe(int i, int j) 
		{
			for(int row = 0; row < board.length; row++)
			{
				for(int col = 0; col < board[row].length; col++)
				{
					if(board[row][col] == 1)
					{
						//Vertically
						if(col == j)
							return false;
						
						//Horizontally
						if(row == i)
							return false;
						
						//Diagonally
						if(Math.abs(row - i) == Math.abs(col - j))
							return false;
					}
				}
			}
			
			return true;
		}
		
		@Override
		public void run() {
			solve(0);
		}
		
		public int[][] getBoard()
		{
			return board;
		}
	}
	
}
