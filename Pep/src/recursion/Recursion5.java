package recursion;
import java.util.Arrays;

public class Recursion5 {
	// Proactive call - Soch samjh ke calls lgai
	// print board path
	public static void pbp(int sr, int sc, int dr, int dc, String psf) {
		if (sr == dr && sc == dc) {
			System.out.println(psf);
			return;
		}
		// Horizontal call
		if (sc + 1 <= dc) {
			pbp(sr, sc + 1, dr, dc, psf + "H");
		}
		// Vertical call
		if (sr + 1 <= dr) {
			pbp(sr + 1, sc, dr, dc, psf + "V");
		}

	}

	// Reactive Call
	public static void pbpreactive(int sr, int sc, int dr, int dc, String psf) {
		if (sr == dr && sc == dc) {
			System.out.println(psf);
			return;
		}

		// negatve base case
		if (sr > dr || sc > dc) {
			return;
		}

		pbp(sr, sc + 1, dr, dc, psf + "H");

		pbp(sr + 1, sc, dr, dc, psf + "V");

	}

	static int counter = 0;

	// Reactive Call
	public static void pbpdiagonalreactive(int sr, int sc, int dr, int dc, String psf) {
		if (sr == dr && sc == dc) {
			counter++;
			System.out.println(counter + ". " + psf);
			return;
		}

		// negatve base case
		if (sr > dr || sc > dc) {
			return;
		}

		pbpdiagonalreactive(sr + 1, sc + 1, dr, dc, psf + "D");
		pbpdiagonalreactive(sr, sc + 1, dr, dc, psf + "H");
		pbpdiagonalreactive(sr + 1, sc, dr, dc, psf + "V");

	}

	public static void pbpMutliMoves(int sr, int sc, int dr, int dc, String psf) {
		if(sr == dr && sc == dc) {
			counter++;
			System.out.println(counter + ". " + psf);
			return;
		}
		// Horizontal Calls : ma = minimum amplitude
		for (int ma = 1; ma <= dc - sc; ma++) {
			pbpMutliMoves(sr, sc + ma, dr, dc, psf + "H" + ma);
		}
		// Vertical Calls : ma = minimum amplitude
		for (int ma = 1; ma <= dr - sr; ma++) {
			pbpMutliMoves(sr + ma, sc, dr, dc, psf + "V" + ma);
		}
		// Diagonal Calls : ma = minimum amplitude
		for (int ma = 1; ma <= dc - sc && ma <= dr - sr; ma++) {
			pbpMutliMoves(sr + ma, sc + ma, dr, dc, psf + "D" + ma);
		}

	}
	//Reactive
	public static void floodfill(int sc, int sr, int[][] maze, boolean[][] visited, String psf) {
		if(sr == maze.length - 1 && sc == maze[0].length - 1) {
			counter++;
			System.out.println(counter + ". " + psf);
			return;
		}
		if (IsanInvalidSpot(sc, sr, maze) == true || visited[sr][sc] == true) {
			return;
		}
		
		//Calls sequence DLTR
		visited[sr][sc] = true;
		floodfill(sc, sr + 1, maze, visited, psf + "D");
		floodfill(sc - 1, sr, maze, visited, psf + "L");
		floodfill(sc, sr - 1, maze, visited, psf + "T");
		floodfill(sc + 1, sr, maze, visited, psf + "R");
		visited[sr][sc] = false;
	}
	
	public static boolean IsanInvalidSpot(int sc, int sr, int[][] maze) {
		if(sc > maze[0].length - 1 || sr > maze.length - 1)
			return true;
		else if(sc < 0 || sr < 0)
			return true;
		else if(maze[sr][sc] == 0)
			return true;
		else 
			return false;
	}
	public static void main(String[] args) {
		int[][] maze = {	//1: allowed 0: blocked
				{1, 0, 1, 1},
				{1, 1, 1, 1},
				{1, 0, 1, 1},
				{1, 1, 0, 1}
		};
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		floodfill(0, 0, maze, visited, "");
//		pbpMutliMoves(0, 0, 3, 3, "");
	}
}
