public class Main {
	public static void main(String[] args) {
		int[][] vals = new int[9][9];
		for(int i = 0; i < vals.length; i++) {
			for(int j = 0; j < vals[0].length; j++) {
				vals[i][j] = i + j;
				System.out.print(vals[i][j] + " ");
			}
			System.out.println();
		}
		
		Board b = new Board(vals);

		int[] a = b.getRow(0);
		int[] c = b.getCol(5);
		int[] d = b.getBox(0, 0);

		for(int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
		for(int i = 0; i < c.length; i++)
			System.out.print(c[i] + " ");
		System.out.println();
		for(int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
		System.out.println();
	}
}