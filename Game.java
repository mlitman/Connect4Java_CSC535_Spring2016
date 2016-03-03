import java.util.Scanner;


public class Game 
{
	private Board theBoard;
	private Player player1;
	private Player player2;
	private Scanner input;
	
	public Game()
	{
		this.theBoard = new Board();
		this.player1 = new Player("player 1", 'R');
		this.player2 = new Player("player 2", 'B');
		this.input = new Scanner(System.in);
	}
	
	public void start()
	{
		Player currPlayer = this.player1;
		int column;
		int numberOfMoves = 0;
		int maxMoves = this.theBoard.numColumns() * this.theBoard.numRows();
		do
		{
			this.theBoard.display();
			
			//whose turn is it?
			do
			{
				//try to make a move
				column = this.getNumberFromPlayer(currPlayer);
			}
			while(!this.theBoard.dropChecker(currPlayer.getChecker(), column));
			
			//a move has been made, was their a winner?
			numberOfMoves++;
			
			if(this.isWinner())
			{
				System.out.println(currPlayer.getName() + " has WON the GAME!!!!!");
				break;
			}
			
			if(currPlayer == this.player1)
			{
				currPlayer = this.player2;
			}
			else
			{
				currPlayer = this.player1;
			}
			
		}
		while(numberOfMoves < maxMoves);
		if(numberOfMoves == maxMoves)
		{
			System.out.println("The Game was a TIE!!!");
		}
	}
	
	private boolean isWinner()
	{
		//check for vertical winners
		for(int i = 0; i < this.theBoard.numColumns(); i++)
		{
			if(this.theBoard.isWinningColumn(i))
			{
				return true;
			}
		}
		
		//check for horizontal winners
		for(int i = 0; i < this.theBoard.numRows(); i++)
		{
			if(this.theBoard.isWinningRow(i))
			{
				return true;
			}
		}
		
		for(int i = 0; i < this.theBoard.numRows(); i++)
		{
			for(int j = 0; j < this.theBoard.numColumns(); j++)
			{
				if(this.theBoard.isWinningDiagonal(i, j))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private int getNumberFromPlayer(Player p)
	{
		System.out.print(p.getName() + " Please enter a column:" );
		return this.input.nextInt();
	}
}
