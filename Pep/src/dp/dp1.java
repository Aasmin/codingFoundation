package dp;

import java.util.Scanner;

public class dp1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
//		int[] qb = new int[n + 1];
//		System.out.println(fibTabu(n));
		
		
		int[] jumpsAllowed = new int[n];
		for (int i = 0; i < jumpsAllowed.length; i++) {
			jumpsAllowed[i] = scn.nextInt();
		}
		System.out.println(climbStairWithMinJumps(n, jumpsAllowed));
		scn.close();
	}

	// memorized
	private static int fib(int n, int[] qb) {
		if (n == 0 || n == 1)
			return n;
		if (qb[n] != 0)
			return qb[n];

		int first = fib(n - 1, qb);
		int second = fib(n - 2, qb);
		int fn = first + second;
		qb[n] = fn;
		return fn;
	}
	//Tabulation
	public static int fibTabu(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;	f[1] = 1;
		
		for (int x = 2; x <= n; x++) {
			f[x] = f[x - 1] + f[x - 2];
		}
		return f[n];
	}

	public static int climbStairs(int n) {
		int[] strg = new int[n + 1];
		strg[n] = 1;
		
		for (int i = n - 1; i >= 0; i--) {
			if (i + 1 <= n) {
				strg[i] += strg[i + 1];
			}
			if (i + 2 <= n) {
				strg[i] += strg[i + 2];
			}
			if (i + 3 <= n) {
				strg[i] += strg[i + 3];
			}
		}
		return strg[0];
	}

	public static int climbStairsWithVarJumps(int n) {
		Scanner scn  = new Scanner(System.in);
		int[] jumpsAllowed = new int[n];
		for (int i = 0; i < n ; i++) {
			jumpsAllowed[i] = scn.nextInt();
		}
		int[] strg = new int[n + 1];
		strg[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			if(jumpsAllowed[i] == 0) {
				strg[i] = 0;
			} else {
				for (int j = 1; j <= jumpsAllowed[i]; j++) {
					if(i + j <= n - 1) {
						strg[i] += strg[i + j];
					}
				}
			}
		}
		return strg[0];
	}

	public static int climbStairWithMinJumps(int n, int[] jumpsAllowed) {
		int[] strg = new int[n];
		strg[n - 1] = 0;
		for (int i = n - 2; i >= 0; i--) {
			if(jumpsAllowed[i] == 0)	strg[i] = n;
			else {
				int min = n;
				for (int j = 1; j <= jumpsAllowed[i] && i + j < n; j++) {
					if(strg[i + j] != n)	
						min = Math.min(min, strg[i + j]); 
				}
				if(min !=  n) strg[i] = min + 1;
			}
		}
		return strg[0];
	}
}
