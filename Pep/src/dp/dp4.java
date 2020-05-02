package dp;

import java.util.Scanner;

public class dp4 {

	 static int paintHouse1(int[][] costs) {
        // write your code here
        int cost = 3;
        int houses = costs.length;
        if(houses == 0) return 0;
        int[][] strg = new int[houses][3];
        
        strg[0][0] = costs[0][0];
        strg[0][1] = costs[0][1];
        strg[0][2] = costs[0][2];
        
        for (int i = 1; i < houses; i++) {
			for (int j = 0; j < 3; j++) {
				if(j == 0) {
					strg[i][j] = costs[i][j] + Math.min(strg[i - 1][1], strg[i - 1][2]);
				} else if(j == 1) {
					strg[i][j] = costs[i][j] + Math.min(strg[i - 1][0], strg[i - 1][2]);
				} else {
					strg[i][j] = costs[i][j] + Math.min(strg[i - 1][0], strg[i - 1][1]);
				}
			}
		}
        int min = Integer.MAX_VALUE;
        for (int colors = 0; colors < strg[0].length; colors++) {
			min = Math.min(min, strg[houses - 1][colors]);
		}
        
        return min;
    }

	 static void ceilAndFloor(int[] arr, int val) {
		 int max = Integer.MIN_VALUE;
		 int min = Integer.MAX_VALUE;
		 
		 for (int i = 0; i < arr.length; i++) {
			if(arr[i] > max && arr[i] <= val) {
				max = arr[i];//floor
			}
			
			if(arr[i] < min && arr[i] >= val) {
				min = arr[i];//ceil
			}
		}
		 
		 System.out.println(min);	
		 System.out.println(max);	
	 }
	 
	 static int paintHouse2(int[][] costs) {
		 System.out.println(costs[0].length);
		 int house = costs.length;
		 if(house == 0)	return 0;
		 
		 int color = costs[0].length;
		 
		 int[][] strg = new int[house][color];
		 int min = Integer.MAX_VALUE;
		 int secmin = Integer.MAX_VALUE;
		 
		 //for coloring of first house
		 for (int i = 0; i < color; i++) {
			strg[0][i] = costs[0][i];
			if(min >= strg[0][i]) {
				secmin = min;
				min = strg[0][i];
			} else if (secmin > strg[0][i]) {
				secmin = strg[0][i];
			}
		}
		 
		 for (int i = 1; i < house; i++) {
			 //prepare current min and current second min for next ITR
			 int curmin = Integer.MAX_VALUE;
			 int cursecmin = Integer.MAX_VALUE;
			 for (int j = 0; j < color; j++) {
				if(min == strg[i - 1][j]) {
					strg[i][j] = costs[i][j] + secmin;
				} else {
					strg[i][j] = costs[i][j] + min;
				}
				
				if(curmin >= strg[i][j]) {
					cursecmin = curmin;
					curmin = strg[i][j];
				} else if (cursecmin >= strg[i][j]) {
					cursecmin = strg[i][j];
				}
			}
			 
			 min = curmin;
			 secmin = cursecmin;
		}
		 return min;
	 }

	 static int countDecoding(char[] digits)  {
		 
		 int[] strg = new int[digits.length + 1];
		 strg[0] = 1;
		 strg[1] = 1;
		 for (int i = 2; i < strg.length; i++) {
			int c1 = 0;
			int c2 = 0;
			if(digits[i - 1] - '0' != 0) {
				c1 = strg[i - 1];
			}
			if(Integer.parseInt("" + digits[i - 2] + digits[i - 1]) < 27 && digits[i - 2] - '0' != 0) {
				c2 = strg[i - 2];
			}
			
			strg[i] = c1 + c2 != 0 ? c1 + c2 : - 1;

			//simplified
//			if(c1 + c2 != 0)
//				strg[i] = c1 + c2;
//			else {
//				strg[i] = -1;
//			}
		}
		 return strg[digits.length];
		 
	 }
	 
	 public static void main(String[] args) {

		 int [][] costs = {
				 {14, 2, 11},
				 {11, 14, 5},
				 {14, 3, 10}
		 };
		 int[][] costs1 = {};
//		 System.out.println(paintHouse1(costs1));
//		 System.out.println(paintHouse2(costs1));
//		 char[] arr = {'1', '2'};
//		 System.out.println(Integer.parseInt("" + arr[0] + arr[1]));
		 char[] digits = {'1', '2', '3', '4'};
		 char[] digits1 = {'1', '1', '2', '3'};
		 char[] digits2 = {'1', '1', '2', '2', '0', '3', '4', '0'};
		 System.out.println(countDecoding(digits));
		 
//		 Scanner scn = new Scanner(System.in);
//		 int n = scn.nextInt();
//		 int[] arr = new int[n];
//		 for (int i = 0; i < n; i++) {
//			arr[i] = scn.nextInt();
//		}
//		 int val = scn.nextInt();
//		 ceilAndFloor(arr, val);
	 }
}
