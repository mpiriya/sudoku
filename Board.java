import java.util.HashSet;
import java.util.ArrayList;

public class Board {
	private Tile[][] board;

	public Board() {
		this.board = new Tile[9][9];
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				board[r][c] = new Tile(r,c);
			}
		}
	}

	public Board(Tile[][] board) {
		this.board = board;
	}

	public static void main(String[] args) {
		//initialize board, insert initial values
		//get all possible values for every empty square
		//sort by number of possible values
	}

	//get all possible values for every empty square
	public void initPossibleValues() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c].value != 0) {
					board[r][c].setpossible(possibleValues(r, c));
				}
			}
		}
	}

	public ArrayList<Tile> sortByNumPossible() {
		//flatten 2D array
		ArrayList<Tile> ref = new ArrayList<Tile>();
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				ref.add(board[r][c]);
				sizes.add(board[r][c].getpossibleSize());
			}
		}
		
		//sort using... counting sort
		//citation: https://www.geeksforgeeks.org/counting-sort/
		ArrayList<Tile> output = new ArrayList<Tile>();
		int[] count = new int[10];

		for(int i = 0; i < sizes.size(); i++) {
			count[sizes.get(i)]++;
		}

		for(int i = 1; i < count.length; i++) {
			count[i] += count[i-1];
		}
		
		for(int i = sizes.size() - 1; i >= 0; i--) {
			output.set(count[sizes.get(i)] - 1, ref.get(i));
		}

		return output;
	}

	public ArrayList<Integer> possibleValues(int row, int col) {
		int[] count = new int[10];
		for(int i = 0; i < count.length; i++)
			count[i] = 0;

		for(int x : getRow(row))
			if(x != 0)
				count[x]++;
		for(int x : getCol(col))
			if(x != 0)
				count[x]++;
		for(int x : getBox(row, col))
			if(x != 0)
				count[x]++;

		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		for(int i = 1; i < count.length; i++) {
			if(count[i] == 0) {
				toReturn.add(i);
			}
		}
		return toReturn;
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
			toret[i] = board[row][i].value;

		return toret;
	}

	public int[] getCol(int col) {
		int[] toret = new int[9];
		
		for(int i = 0; i < 9; i++) 
			toret[i] = board[i][col].value;

		return toret;
	}

	public int[] getBox(int row, int col) {
		int[] toret = new int[9];

		int count = 0;

		for(int i = row/3; i < (row/3) + 3; i++) {
			for(int j = col/3; j < (col/3) + 3; j++) {
				toret[count] = board[i][j].value;
				count++;
			}
		}

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