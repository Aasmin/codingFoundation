package dp;
import java.util.*;

public class dp2 {

	 public static void main(String[] args) throws Exception {
	        Scanner scn = new Scanner(System.in);
	        int n = scn.nextInt();
	        //values for knapsack
	        int[] ques = new int[n];
	        for (int i = 0; i < ques.length; i++) {
				ques[i] = scn.nextInt();
			}
	        //weights for kanpsack
	        int[] wts = new int[n];
	        for (int i = 0; i < wts.length; i++) {
	        	wts[i] = scn.nextInt();
	        }
	        int target = scn.nextInt();
//	        zeroOneKnapsack(ques, wts, target);
	        unboundedKnapsack(ques, wts, target);
//	        coinChangePermutation(ques, target);
//	        targetSum(ques, target);
//	        coinChangeCombination(ques, target);
//	        // take the values of question
//	        for(int i = n - 1; i >= 0; i--) {
//	            ques[i] = scn.nextInt();
//	        }
//	        
//	        int[] strg = new int[n];
//	        strg[0] = 0;
//	        
//	        for(int i = 1; i < n; i++) {
//	            int k = ques[i];
//	            int jump = n;
//	            for(int j = 1; j <= k && i - j >= 0; j++) {
//	                jump = Math.min(jump, strg[i - j]);
//	            }
//	            strg[i] = jump + 1;
//	        }
////	        System.out.println(strg[n - 1]);
////	        pathFromMinJumps(ques, strg, "", ques.length);
//	        countcostPath(0, 0, 3, 3);
//	        Scanner scn = new Scanner(System.in);
//	        int n1 = scn.nextInt();
//	        int n2 = scn.nextInt();
//	        int[][] cost = new int[n1][n2];
//	        for (int i = 0; i < n1; i++) {
//				for (int j = 0; j < n2; j++) {
//					cost[i][j] = scn.nextInt();
//				}
//			}
//	       int[][] cost1 = {
//	        {0, 1, 4, 2, 8, 2},
//	        {4, 3, 6, 5, 0, 4},
//	        {1, 2, 4, 1, 4, 6},
//	        {2, 0, 7, 3, 2, 2},
//	        {3, 1, 5, 9, 2, 4},
//	        {2, 7, 0, 8, 5, 1}
//	       };
//	       
//	       int[][] strg = minCostcostTravel(0, 0, cost.length - 1, cost[0].length - 1, cost);
//	       for (int i = 0; i < strg.length; i++) {
//			for (int j = 0; j < strg[0].length; j++) {
//				System.out.print(strg[i][j] + " ");
//			}
//			System.out.println();
//		    }
//	       System.out.println();
//	       minCostMazePath(strg, "", 0, 0);
//	       goldMine(cost1);
//		 int[] strg = {0, 1, 2, 2, 11, 3, 3, 4, 4, 4, 5};
//		 int[] ques = {0, 1, 1, 2, 0, 3, 4, 2, 3, 5, 3};
//		 pathFromMinJumps(ques, strg, "", 10);
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
 
	 public static int[][] minCostcostTravel(int srow, int scol, int drow, int dcol, int[][] cost) {
		 int strg[][] = new int[drow + 1][ dcol + 1];
		 
		 for (int i = drow; i >= 0; i--) {
			for (int j = dcol; j >= 0; j--) {
				if(i == drow && j == dcol) {
					strg[i][j] = cost[i][j];
				} else if(i == drow) {
					strg[i][j] = cost[i][j] + strg[i][j + 1];
				} else if(j == dcol) {
					strg[i][j] = cost[i][j] + strg[i + 1][j];
				}  else {
					strg[i][j] = cost[i][j] + Math.min(strg[i][j+1], strg[i+1][j]);
				}
			}
		}
		 System.out.println(strg[0][0]);
		 return strg;
	 }
	 
	 public static void countcostPath(int srow, int scol, int drow, int dcol) {
		 int[][] strg = new int[drow+1][dcol+1];
		 
 		 for (int i = drow; i >= 0; i--) {
			for (int j = dcol; j >= 0; j--) {
				if(i == drow || j == dcol) {
					strg[i][j] = 1;
				} else {
					strg[i][j] = strg[i][j + 1] + strg[i + 1][j];
				}
			}
		}
 		 System.out.println(strg[0][0]);
	 }
	 
	 static int count = 0; 
	 static void pathFromMinJumps(int[] ques, int[] strg, String psf, int cstair) {
	        if(cstair == 0) {
	            count++;
	            System.out.println(count + ". " + psf + "0");
	            return;
	        }
	        
	        int pjump = ques[cstair];
	        int mjump = strg[cstair];
	        
	        for(int i = 1; i <= pjump; i++) {
	            if(strg[cstair - i] == (mjump - 1)) {
	            	pathFromMinJumps(ques, strg, psf + cstair + "->", cstair - i);
	            }
	        }
	    }

	 static void minCostMazePath(int[][] strg, String psf, int crow, int ccol) {
		 if(crow == strg.length - 1 && ccol == strg[0].length - 1) {
			 System.out.println(psf + "end.");
			 return;
		 }
		 
		 int minCost;	int nrow, ncol;
		 // last col
		 if(ccol == strg[0].length - 1) {
			 nrow = crow + 1;	ncol = ccol;
			 minCostMazePath(strg, psf  + crow + "," + ccol + " -> ", nrow, ncol);
		 } else if (crow == strg.length - 1) {
			 ncol = ccol + 1;	nrow = crow;
			 minCostMazePath(strg, psf  + crow + "," + ccol + " -> ", nrow, ncol);
		 } else {
			 minCost = Math.min(strg[crow + 1][ccol], strg[crow][ccol + 1]);
			 if(minCost == strg[crow + 1][ccol] &&  minCost == strg[crow][ccol + 1]) {
				 minCostMazePath(strg, psf  + crow + "," + ccol + " -> ", crow + 1, ccol);
				 minCostMazePath(strg, psf  + crow + "," + ccol + " -> ", crow, ccol + 1);
			 } else if(minCost == strg[crow + 1][ccol]) {
				 nrow = crow + 1;	ncol = ccol;
				 minCostMazePath(strg, psf  + crow + "," + ccol + " -> ", nrow, ncol);
			 } else {
				 ncol = ccol + 1;	nrow = crow;
				 minCostMazePath(strg, psf  + crow + "," + ccol + " -> ", nrow, ncol);
			 }
		 }
		 
	 }
	 
	 static void goldMinePath (int[][] strg) {
		 int max = strg[0][0];
		 for (int i = 1; i < strg.length; i++) {
			if(max < strg[i][0]) {
				max = strg[i][0];
			}
		}
		 for (int i = 0; i < strg.length; i++) {
			if(max == strg[i][0]) {
				goldMinePathFun(strg, "", i, 0);
			}
		}
	 }
	 
	 static void goldMinePathFun (int[][] strg, String psf, int crow, int ccol) {
		 
		 
	 }

	 static void targetSum(int[] que, int target) {
		 
		 boolean[][] strg = new boolean[que.length + 1][target + 1];
		 for (int i = 0; i < strg.length; i++) {
			for (int j = 0; j < strg[0].length; j++) {
				if (i == 0 && j == 0) {
					strg[i][j] = true;
				} else if (i == 0) {
					strg[i][j] = false;
				} else if (j == 0) {
					strg[i][j] = true;
				} else {
					int cel = que[i - 1];
					if(j < cel) {
						strg[i][j] = strg[i - 1][j];
					} else if (j == cel) {
						strg[i][j] = true;
					} else {
						if(strg[i - 1][j] == true) {
							strg[i][j] = true;
						} else if (strg[i - 1][j - cel] == true) {
							strg[i][j] = true;
						} else {
							strg[i][j] = false;
						}
					}
				}
			}
		}
		 System.out.println(strg[que.length][target]);
	 }

	 static void coinChangeCombination(int[] que, int target) {
		 
		 int[] strg = new int[target + 1];
		 strg[0] = 1;
		 
		 for (int i = 0; i < que.length; i++) {
			 int coin = que[i];
			for (int j = coin; j < strg.length; j++) {
				strg[j] += strg[j - coin];
			}
		}
		 System.out.println(strg[target]);
		 
	 }

	 static void coinChangePermutation(int[] que, int target) {
		 int[] strg = new int[target + 1];
		 strg[0] = 1;
		 
		 for (int i = 1; i <= target; i++) {
			 //target == i
			 for (int coin : que) {
				 if(coin <= i) {
					 strg[i] += strg[i - coin];
				 }
			 }
		}
		 System.out.print(strg[target]);
	 }

	 static void zeroOneKnapsack (int[] values, int[] weights, int maxCap) {
		 
		 int[][] strg = new int[weights.length + 1][maxCap + 1];
		 
		 for (int i = 0; i <= weights.length; i++) {
			for (int j = 0; j <= maxCap; j++) {
				if(i == 0 || j == 0) {
					strg[i][j] = 0;
				} else {
					int wt = weights[i - 1];
					int val = values[i - 1];
					if (j < wt) {
						strg[i][j] = strg[i - 1][j];
					} else {
						strg[i][j] = Math.max(strg[i - 1][j], strg[i - 1][j - wt] + val);
					}
				}
			}
		}
		 System.out.println(strg[weights.length][maxCap]);
	 }

	 static void unboundedKnapsack(int[] values, int[] weights, int maxCap) {
		 
		 int[] strg = new int[maxCap + 1];
		 for (int i = 0; i < weights.length; i++) {
			int wt = weights[i];
			int val = values[i];
			for (int j = wt; j <= maxCap; j++) {
				strg[j] = Math.max(strg[j], strg[j - wt] + val);
			}
		}
		 System.out.println(strg[maxCap]);
	 }
}
