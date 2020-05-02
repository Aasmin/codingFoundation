package recursion;

public class Recursion7 {
	static int counter = 0;

	// chessboard length = no of queens
	// Reactive - it runs slow
	public static void nqueens(boolean[][] chess, int qpsf, String asf, int lqpb) {
		if (qpsf == chess.length) {
			counter++;
			if (theChessBoardIsValid(chess) == true) {
				System.out.println(counter + ". " + asf);
			}
			return;
		}
		// lqpb = yeh box is liye pass kiya tan jo next queen iske agle wale box pe
		// jaage place ho
		for (int b = lqpb + 1; b < chess.length * chess.length; b++) {
			int row = b / chess.length;
			int col = b % chess.length;

			if (chess[row][col] == false) { // we can't place queen in this box
				chess[row][col] = true;
				nqueens(chess, qpsf + 1, asf + "q" + (qpsf + 1) + "b" + b + " ", b);
				chess[row][col] = false;
			}
		}
	}

	// Proactive
	public static void nqueensProactive(boolean[][] chess, int qpsf, String asf, int lqpb) {
		if (qpsf == chess.length) {
			counter++;
			System.out.println(counter + ". " + asf);
			return;
		}
		// lqpb = yeh box is liye pass kiya tan jo next queen iske agle wale box pe
		// jaage place ho
		for (int b = lqpb + 1; b < chess.length * chess.length; b++) {
			int row = b / chess.length;
			int col = b % chess.length;

			if (chess[row][col] == false) { // we can't place queen in this box
				if (theQueenIsSafe(chess, row, col) == true) {
					chess[row][col] = true;
					if (theChessBoardIsValid(chess) == true) {
						nqueensProactive(chess, qpsf + 1, asf + "q" + (qpsf + 1) + "b" + b + " ", b);
					}
					chess[row][col] = false;
				}
			}
		}
	}

	public static void nqueensLikeSS(boolean[][] chess, int box, int qpsf, String asf) {

		if (qpsf == chess.length) { // sari queens beth gayi
			System.out.println(asf);
			return;
		}

		if (box >= chess.length * chess.length) {
			return;
		}
		nqueensLikeSS(chess, box + 1, qpsf, asf); // queen not increase

		int row = box / chess.length;
		int col = box % chess.length;
		if (chess[row][col] == false) {
			if(theQueenIsSafe(chess, row, col)) {
				chess[row][col] = true;
				if(theChessBoardIsValid(chess) == true)
					nqueensLikeSS(chess, box + 1, qpsf + 1, asf + "q" + (qpsf + 1) + "b" + box + " ");
				chess[row][col] = false;
			}
		}

	}

	private static boolean theChessBoardIsValid(boolean[][] chess) {
		for (int row = 0; row < chess.length; row++) {
			for (int col = 0; col < chess[row].length; col++) {
				if (chess[row][col] == true) { // this checks whether queen is at specific (row,col)
					if (theQueenIsSafe(chess, row, col) == false) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean theQueenIsSafe(boolean[][] chess, int row, int col) {
		int[][] dirs = { { 0, -1 }, // N --> {x, y}
				{ +1, -1 }, // NE
				{ +1, 0 }, // E
				{ +1, +1 }, // SE
				{ 0, +1 }, // S
				{ -1, +1 }, // SW
				{ -1, 0 }, // W
				{ -1, +1 }, // NW
		};
		for (int dirInd = 0; dirInd < dirs.length; dirInd++) {
			for (int dist = 1; true; dist++) {
				// finding potential position of enemy queen
				int eqCol = col + dist * dirs[dirInd][0];
				int eqRow = row + dist * dirs[dirInd][1];

				if (eqCol < 0 || eqRow < 0 || eqCol >= chess[0].length || eqRow >= chess.length) {
					break;
				}

				if (chess[eqRow][eqCol] == true) { // if enemy queen found
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		boolean[][] chess = new boolean[4][4];
//		nqueensProactive(chess, 0, "", -1); // lqpb = -1 because we started the loop from b = lqpb + 1
		nqueensLikeSS(chess, 0, 0, "");
	}
}
