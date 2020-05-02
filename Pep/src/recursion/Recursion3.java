package recursion;

public class Recursion3 {
	public static void printSS(String que, String asf) {	//asf = answer so far
		if(que.length() == 0) {
			System.out.println(asf);
			return;
		}
		char ch = que.charAt(0);
		String roq = que.substring(1);
		printSS(roq, asf);
		printSS(roq, asf + ch);
	}
	
	public static void printSSwithASCII(String que, String asf) {	//asf = answer so far
		if(que.length() == 0) {
			System.out.println(asf);
			return;
		}
		char ch = que.charAt(0);
		String roq = que.substring(1);
		printSSwithASCII(roq, asf);
		printSSwithASCII(roq, asf + ch);
		printSSwithASCII(roq, asf + (int)ch);
	}

	static String[] codes = {".", "abc", "def", "ghi", "jkl",
	            "mno", "pqr", "st", "uvwx", "yz"};
	
	public static void printKPC(String que, String asf) {
		if(que.length() == 0) {
			System.out.println(asf);
			return;
		}
		char ch = que.charAt(0);
		String roq = que.substring(1);
		String chcode = codes[ch-'0'];
		for (int i = 0; i < chcode.length(); i++) {
			char chchoice = chcode.charAt(i); 
			printKPC(roq, asf + chchoice);
		}
	}
	
	static int count = 0;
	public static void printBoardPathsWith16Openingwithsnl(int src, int dest, int[] snl, int[] moves, int mvidx) {
		if(src == dest) {	// agar aap jeet gaye ho then game end karo
			System.out.println("WIN");
			return;
		}
		
		if(mvidx == moves.length) {		//agar moves khatam hogyi
			System.out.println(src);
			return;
		}
		
		if(src == 0) {
			if(moves[mvidx] == 1 || moves[mvidx] == 6) 
				printBoardPathsWith16Openingwithsnl(1, dest, snl, moves, mvidx + 1);
			else
					printBoardPathsWith16Openingwithsnl(src, dest, snl, moves, mvidx + 1);
		} else {
			if(snl[src] != 0)	//found snake or ladder
				printBoardPathsWith16Openingwithsnl(snl[src], dest, snl, moves, mvidx);
			else {
				if(src + moves[mvidx] <= dest) 	//if move is possible
					printBoardPathsWith16Openingwithsnl(src + moves[mvidx], dest, snl, moves, mvidx + 1);
				else 		//if move add hone par board se bahr ja rhe ho, then skip the move
					printBoardPathsWith16Openingwithsnl(src, dest, snl, moves, mvidx + 1);
			}
		}
	}
	public static void printBoardPathsWith16Openingwithladders(int src, int dest, int[] ladders, String psf) {
		if(src == dest) {
			count++;
			System.out.println(count + ". " + psf);
			return;
			
		}
		
		// Negative base case
		// Reactive base case - Recovered from a bad call
		if(src > dest) {
			return;
		}
		
		if(src == 0) {
			printBoardPathsWith16Openingwithladders(1, dest, ladders, psf + 1);
			printBoardPathsWith16Openingwithladders(1, dest, ladders,  psf + 6);		//6 fenkne pe bhi 
		} else if(ladders[src] != 0) {
			printBoardPathsWith16Openingwithladders(ladders[src], dest, ladders, psf + " [" + src + "->" + ladders[src] + "] ");
		}
		else {
			for (int dice = 1; dice <= 6; dice++) {  //dice ke outcomes
				int intermediate = src + dice;
				printBoardPathsWith16Openingwithladders(intermediate, dest, ladders, psf + dice);
			}
		}
		
	}
	public static void printBoardPathsWith16Opening(int src, int dest, String psf) {
		if(src == dest) {
			count++;
			System.out.println(count + ". " + psf);
			return;
			
		}
		
		// Negative base case
		// Reactive base case - Recovered from a bad call
		if(src > dest) {
			return;
		}
		
		if(src == 0) {
			printBoardPathsWith16Opening(1, dest, psf + 1);
			printBoardPathsWith16Opening(1, dest, psf + 6);		//6 fenkne pe bhi 
		} else {
			for (int dice = 1; dice <= 6; dice++) {  //dice ke outcomes
				int intermediate = src + dice;
				printBoardPathsWith16Opening(intermediate, dest, psf + dice);
			}
		}
		
	}
	public static void printBoardPaths(int src, int dest, String psf) {
		if(src == dest) {
			count++;
			System.out.println(count + ". " + psf);
			return;
			
		}
		
		// Negative base case
		// Reactive base case - Recovered from a bad call
		if(src > dest) {
			return;
		}
		for (int dice = 1; dice <= 6; dice++) {  //dice ke outcomes
			int intermediate = src + dice;
			printBoardPaths(intermediate, dest, psf + dice);
		}
	}
	public static void printBoardPathsproactive(int src, int dest, String psf) {
		if(src == dest) {
			count++;
			System.out.println(count + ". " + psf);
			return;
			
		}
		
		for (int dice = 1; dice <= 6; dice++) {  //dice ke outcomes
			int intermediate = src + dice;
			if(intermediate <= dest)
				printBoardPathsproactive(intermediate, dest, psf + dice);
		}
	}
	
	public static void main(String[] args) {
		int[] snl = new int[21];
		snl[3] = 17;
		snl[7] = 11;
		snl[13] = 5;
		snl[19] = 2;
		int[] moves1 = {2, 5, 3, 4, 6, 3, 4, 3, 5, 1, 2, 3};
		int[] moves2 = {2, 5, 3, 4, 6, 3, 4, 3, 5, 1, 1, 6, 5, 2, 3, 5};
		printBoardPathsWith16Openingwithsnl(0, 20, snl, moves1, 0);
//		int[] ladders = new int[21];
//		ladders[3] = 17;
//		ladders[7] = 11;
//		printBoardPathsWith16Openingwithladders(0, 20, ladders, "");
//		printSSwithASCII("ab", "");
//		printKPC("179", "");
	}
}
