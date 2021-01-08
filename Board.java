import java.util.HashSet;

public class Board {
	private int[][] board;

	public Board() {
		this.board = new int[9][9];
	}

	public Board(int[][] board) {
		this.board = board;
	}

	public boolean isValid() {
		for(int i = 0; i < 9; i++) {
			if(containsDuplicates(getRow(i)))
				return false;
			if(containsDuplicates(getCol(i)))
				return false;
		}

		for(int i = 0; i < 3; i++) 
			for(int j = 0; j < 3; j++)
				if(containsDuplicates(getBox(i,j)))
					return false;

		return true;
	}

	public boolean containsDuplicates(int[] arr) {
		HashSet<Integer> hs = new HashSet<Integer>();
		
		for(int i : arr) {
			if(hs.contains(i))
				return true;
			else
				hs.add(i);
		}

		return false;
	}

	public int[] getRow(int row) {
		int[] toret = new int[9];
		
		for(int i = 0; i < 9; i++) 
			toret[i] = board[row][i];

		return toret;
	}

	public int[] getCol(int col) {
		int[] toret = new int[9];
		
		for(int i = 0; i < 9; i++) 
			toret[i] = board[i][col];

		return toret;
	}

	public int[] getBox(int row, int col) {
		int[] toret = new int[9];

		int count = 0;

		for(int i = row*3; i < (row*3) + 3; i++)
			for(int j = col*3; j < (col*3) + 3; j++) 
				toret[count++] = board[i][j];

		return toret;
	}

	public String toString() {
		String toret = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				toret += board[i][j] + " ";
			}
			toret += "\n";
		}
		return toret;
	}
}