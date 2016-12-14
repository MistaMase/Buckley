package QueensLogic;
public class NQueens {
	static int N;
	static int[][] board;

	
	public static int[][] solve(int size)
	{
		N = size;
		board = new int[N][N];
		solve(0, 0);
		return board;
	}

	private static boolean solve(int row, int a) { 
		if (row == N) return true;
		for (int j = 0; j < N; j++) {
			if (isSafe(row, j)) {
				board[row][j] = 1;
				if (solve(row + 1, 0)) return true;
				board[row][j] = 0;
			}
		}
		return false;
	}

	private static boolean isSafe(int i, int j) 
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

	private static void printArr(int[][] a) {
		for (int[] i :a) {
			for (int j: i) {
				System.out.print(j == 0 ? " _ " : " Q ");
			}
			System.out.println();
		}
	}
}
