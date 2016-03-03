
public class Board 
{
	private char[][] theBoard;
	
	public Board()
	{
		this.theBoard = new char[6][7];
		this.initBoard();
	}
	
	private void initBoard()
	{
		for(int i = 0; i < this.theBoard.length; i++)
		{
			for(int j = 0; j < this.theBoard[i].length; j++)
			{
				this.theBoard[i][j] = '_';
			}
		}
	}
	
	public int numColumns()
	{
		return this.theBoard[0].length;
	}
	
	public int numRows()
	{
		return this.theBoard.length;
	}
	
	private boolean isLegalColumn(int column)
	{
		return column >= 0 && column < this.numColumns();
	}
	
	private boolean isLegalRow(int row)
	{
		return row >= 0 && row < this.numRows();
	}
	
	private boolean isLegalPosition(int row, int column)
	{
		return this.isLegalColumn(column) && this.isLegalRow(row);
	}
	
	private boolean isChecker(int row, int column)
	{
		return this.theBoard[row][column] != '_';
	}
	
	private boolean isMatch(int row, int column, char checker)
	{
		return this.theBoard[row][column] == checker;
	}
	
	public boolean isWinningDiagonal(int row, int column)
	{
		int numCheckersInRow = 1;
		int currCol;
		int currRow;
		
		//check lower left to upper right
		//check below me
		currCol = column - 1;
		currRow = row + 1;
		while(this.isLegalPosition(currRow, currCol) && 
				this.isChecker(currRow, currCol) &&
				this.isMatch(currRow, currCol, this.theBoard[row][column]))
		{
			numCheckersInRow++;
			currCol--;
			currRow++;
		}
		
		//check above me
		currCol = column + 1;
		currRow = row - 1;
		while(this.isLegalPosition(currRow, currCol) && 
				this.isChecker(currRow, currCol) &&
				this.isMatch(currRow, currCol, this.theBoard[row][column]))
		{
			numCheckersInRow++;
			currCol++;
			currRow--;
		}
		
		if(numCheckersInRow >= 4)
		{
			return true;
		}
		
		numCheckersInRow = 1;
		//check upper left to lower right
		//check above me
		currCol = column - 1;
		currRow = row - 1;
		while(this.isLegalPosition(currRow, currCol) && 
				this.isChecker(currRow, currCol) &&
				this.isMatch(currRow, currCol, this.theBoard[row][column]))
		{
			numCheckersInRow++;
			currCol--;
			currRow--;
		}

		//check below me
		currCol = column + 1;
		currRow = row + 1;
		while(this.isLegalPosition(currRow, currCol) && 
				this.isChecker(currRow, currCol) &&
				this.isMatch(currRow, currCol, this.theBoard[row][column]))
		{
			numCheckersInRow++;
			currCol++;
			currRow++;
		}
		
		return numCheckersInRow >= 4;
	}
	
	public boolean isWinningRow(int row)
	{
		int numCheckersInRow = 0;
		char lastChecker = '?';
		
		for(int i = 0; i < this.numColumns(); i++)
		{
			if(this.theBoard[row][i] == '_')
			{
				if(numCheckersInRow >= 4)
				{
					break;
				}
				numCheckersInRow = 0;
				lastChecker = '?';
			}
			else
			{
				//I must be looking at a checker
				if(lastChecker == '?')
				{
					lastChecker = this.theBoard[row][i];
					numCheckersInRow = 1;
				}
				else
				{
					//I do have a last checker, does it match my current one?
					if(lastChecker == this.theBoard[row][i])
					{
						numCheckersInRow++;
					}
					else
					{
						lastChecker = this.theBoard[row][i];
						numCheckersInRow = 1;
					}
				}
			}
		}
		return numCheckersInRow >= 4;
	}
	
	public boolean isWinningColumn(int column)
	{
		int numCheckersInRow = 0;
		char lastChecker = '?';
		for(int i = this.theBoard.length-1; i >= 0; i--)
		{
			if(this.theBoard[i][column] == '_')
			{
				//we have run out of checkers to check
				break;
			}
			else
			{
				//we see a checker
				if(lastChecker == '?')
				{
					lastChecker = this.theBoard[i][column];
					numCheckersInRow++;
				}
				else
				{
					//there was a last checker, does this one match?
					if(lastChecker == this.theBoard[i][column])
					{
						numCheckersInRow++;
					}
					else
					{
						numCheckersInRow = 1;
						lastChecker = this.theBoard[i][column];
					}
				}
			}
		}
		return numCheckersInRow >= 4;
	}
	
	public boolean dropChecker(char checker, int column)
	{
		if(this.theBoard[0][column] == '_')
		{
			//we can definitely put a checker somewhere in this column
			for(int i = this.theBoard.length-1; i >= 0; i--)
			{
				if(this.theBoard[i][column] == '_')
				{
					this.theBoard[i][column] = checker;
					break;
				}
			}
			return true;
		}
		else
		{
			//we were not able to drop a checker in this column
			return false;
		}
	}
	
	public void display()
	{
		System.out.println("0\t1\t2\t3\t4\t5\t6");
		for(int i = 0; i < this.theBoard.length; i++)
		{
			for(int j = 0; j < this.theBoard[i].length; j++)
			{
				System.out.print(this.theBoard[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
