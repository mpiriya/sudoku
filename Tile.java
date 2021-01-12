import java.util.ArrayList;

public class Tile {
	private final int row;
	private final int col;
	public int value;
	private ArrayList<Integer> possible;

	public Tile(int row, int col) {
		this(row, col, 0);
	}

	public Tile(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}

	public ArrayList<Integer> getpossible() {
		return possible;
	}

	public int getpossibleSize() {
		return possible.size();
	}

	public void setpossible(ArrayList<Integer> possible) {
		this.possible = possible;
	}

	public String toString() {
		String toret = "ROW: " + row + ", COL: " + col + ", VAL: " + value + "\n\t";
		for(Integer i : possible)
			toret += "" + i.intValue() + ", ";
		toret += "\n";
		return toret;
	}
}
