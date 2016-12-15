package RecursionSolver;

public class Solver 
{
	private int[][] board = new int[9][9];


	public Solver(String[][] input){
		for(int i = 0; i < input.length; i++)
			for(int j = 0; j < input[i].length; j++)
				if(!input[i][j].equals(""))
				{
					board[i][j] = Integer.valueOf(input[i][j]);
				}

		if(!checkIfFull())
			solve(0);
	}

	private boolean solve(int space)
	{		
		for (int i = 0; i < 9; i++) 
		{
	        for (int j = 0; j < 9; j++) 
	        {
	            if (board[i][j] != 0) 
	                continue;
	            
	            for (int num = 1; num < 10; num++) 
	            {
	                if (isSafe(i, j, num)) 
	                {
	                    board[i][j] = num;
	                    if (solve(space)) 
	                        return true;
	                    else 
	                        board[i][j] = 0;
	                }
	            }
	            return false;
	        }
	    }
	    return true;
	}



	private boolean isSafe(int i, int j, int num)
	{
		for(int row = 0; row < board.length; row++)
		{
			for(int col = 0; col < board[row].length; col++)
			{
				//Vertically
				for(int z = 0; z < 9; z++)
				{
					if(board[z][j] == num && i != z)
						return false;
				}

				//Horizontally
				for(int z = 0; z < 9; z++)
				{
					if(board[i][z] == num && j != z)
						return false;
				}

				//Square
				for(int a = (i/3) * 3; a < ((i/3) * 3 + 3); a++)
				{
					for(int b = (j/3) * 3; b < ((j/3) * 3 + 3); b++)
					{
						if(board[a][b] == num && a != i && b != j)
							return false;
					}
				}
			}
		}
		return true;
	}

	private boolean checkIfFull(){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(board[i][j] == 0)
					return false;
		return true;
	}



	private void printArr()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}


	public int[][] getSolved()
	{
		return board;
	}
}
