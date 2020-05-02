package dp;
import java.util.*;

public class main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n1 = scn.nextInt();
        int n2 = scn.nextInt();
        int[][] cost = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++) {
				cost[i][j] = scn.nextInt();
			}
		}
		goldMine(cost);
    }
    
    public static void goldMine(int[][] cost) {
		 int[][] strg = new int[cost.length][cost[0].length];
		 
		 for (int j = cost[0].length - 1; j >= 0; j--) {
			for (int i = 0; i <= cost.length - 1; i++) {
				if(j == cost[0].length - 1) {
					strg[i][j] = cost[i][j];
				} else if (i == 0) {
					strg[i][j] = cost[i][j] + Math.max(strg[i][j + 1], strg[i + 1][j + 1]);
				} else if (i == cost.length - 1) {
					strg[i][j] = cost[i][j] + Math.max(strg[i - 1][j + 1], strg[i][j + 1]);
				}
				else {
					strg[i][j] = cost[i][j] + Math.max(strg[i - 1][j + 1], Math.max(strg[i][j + 1], strg[i + 1][j + 1]));
				}
			}
		}
	    int max = strg[0][0];
		for (int j = 1; j < strg.length; j++) {
			if(max < strg[j][0]) {
				max = strg[j][0];
			}
		}
		System.out.println(max);
		
	 }
	

}
