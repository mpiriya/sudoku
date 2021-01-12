import java.util.HashSet;
import java.util.ArrayList;

public class Board {
	private Tile[][] board;

	public Board() {
		this.board = new Tile[9][9];
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				this.board[r][c] = null;
			}
		}
	}

	public Board(int[][] board) {
		this.board = new Tile[9][9];
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				this.board[r][c] = new Tile(r,c,board[r][c]);
			}
		}
	}

	public static void main(String[] args) {
		//initialize board, insert initial values
		//get all possible values for every empty square
		//sort by number of possible values
		//

		int[][] vals = {
						{6, 0, 0, 4, 5, 0, 0, 9, 0},
						{1, 0, 0, 0, 0, 0, 0, 5, 0},
						{0, 8, 0, 1, 9, 0, 0, 7, 0},
						{4, 7, 0, 9, 3, 0, 1, 6, 8},
						{0, 0, 0, 6, 0, 1, 0, 0, 0},
						{9, 6, 1, 0, 2, 8, 0, 3, 4},
						{0, 1, 0, 0, 6, 4, 0, 8, 0},
						{0, 3, 0, 0, 0, 0, 0, 0, 7},
						{0, 4, 0, 0, 7, 3, 0, 0, 6}
					};

		Board board = new Board(vals);
		board.setPossibleValues();

		Tile[] sorted = board.sortByNumPossible();
		// for(Tile t : sorted) {
		// 	System.out.println(t);
		// }

		int numInserted = board.fill1PTiles(sorted);
		while(numInserted != 0) {
			sorted = board.sortByNumPossible();
			numInserted = board.fill1PTiles(sorted);
			System.out.println("HI");
		}

		System.out.println(board);
	}

	//get all possible values for every empty square
	public void setPossibleValues() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c].value == 0) {
					board[r][c].setpossible(possibleValues(r, c));
				}
			}
		}
	}

	public Tile[] sortByNumPossible() {
		//flatten 2D array
		ArrayList<Tile> ref = new ArrayList<Tile>();
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c].getpossible() != null && board[r][c].getpossible().size() != 0) {
					ref.add(board[r][c]);
					sizes.add(board[r][c].getpossible().size());
				}
			}
		}
		
		//sort using... counting sort
		//citation: https://www.geeksforgeeks.org/counting-sort/
		Tile[] output = new Tile[sizes.size()];
		int[] count = new int[10];

		for(int i = 0; i < sizes.size(); i++) {
			count[sizes.get(i)]++;
		}

		for(int i = 1; i < count.length; i++) {
			count[i] += count[i-1];
		}
		
		for(int i = sizes.size() - 1; i >= 0; i--) {
			output[count[sizes.get(i)] - 1] = ref.get(i);
			count[sizes.get(i)]--;
		}

		return output;
	}

	public int fill1PTiles(Tile[] sorted) {
		int count = 0;

		for(Tile t : sorted) {
			if(t.getpossible().size() > 1) {
				break;
			}

			int toRemove = t.getpossible().remove(0);
			count++;
			//sets the value of the tile to the only possible value at that tile
			this.board[t.row()][t.col()].value = toRemove;
		}
		setPossibleValues();

		return count;
	}

	public ArrayList<Integer> possibleValues(int row, int col) {
		boolean[] count = new boolean[10];
		for(int i = 0; i < count.length; i++)
			count[i] = true;

		for(int x : getRow(row))
			if(x != 0)
				count[x] = false;
		for(int x : getCol(col))
			if(x != 0)
				count[x] = false;
		for(int x : getBox(row, col))
			if(x != 0)
				count[x] = false;

		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		for(int i = 1; i < count.length; i++) {
			if(count[i]) {
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

	public Tile[][] getboard() {
		return board;
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

		for(int i = row/3*3; i < (row/3*3) + 3; i++) {
			for(int j = col/3*3; j < (col/3*3) + 3; j++) {
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
				toret += board[i][j].value + " ";
			}
			toret += "\n";
		}
		return toret;
	}
}