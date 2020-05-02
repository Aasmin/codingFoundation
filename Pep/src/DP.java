import java.util.Arrays;

public class DP {

	static int fiboMemorization(int n, int[] arr) {
		if(n == 0 || n == 1)
			return n;
		if(arr[n] != 0)
			return arr[n];
		
		int n1 = fiboMemorization(n - 1, arr);
		int n2 = fiboMemorization(n - 1, arr);
		int sum = n1 + n2;
		arr[n] = sum;
		return sum;
			
	}
	static void fiboTabulation(int n) {
		

		int[] arr = new int[n+1];
		arr[0] = 0;
		arr[1] = 1;
		for (int i = 2; i < n; i++) {
			arr[i] = arr[i - 1] + arr[i-2];
		}
		System.out.println(Arrays.toString(arr));
	}

	static int stairPath(int n) {
		int[] arr = new int[n + 1];
		arr[0] = 1;
		for (int i = 1; i < n + 1; i++) {
			if(i - 1 > -1) {
				arr[i] += arr[i - 1];
			}
			if(i - 2 > -1) {
				arr[i] += arr[i - 2];
			}
		}
		return arr[n];
	}
	
	static int minStairPath(int n) {
		
		int[] arr = new int[n + 1];
		arr[0] = 0; arr[1] = 1;
		
		for (int i = 2; i < n + 1; i++) {
			int min = Math.min(arr[i - 1], arr[i - 2]);
			arr[i] = min + 1;
		}
		return arr[n];
	}
	
	
	static int goldMine(int[][] arr) {
		int[][] ans = new int[arr.length][arr[0].length];
		for(int i = 0; i <= arr.length - 1; i ++) {
			ans[i][arr.length - 1] = arr[i][arr.length - 1];
		}
		
		//starting from bottom right corner to up
		for (int col = ans[0].length - 2; col > -1; col--) {
			for (int row = ans.length - 1; row > -1; row--) {
				//last row with 2 possibilities
				if(row == ans.length -1) {
					int max = Math.max(arr[row][col + 1], arr[row - 1][col + 1]);
					ans[row][col] = max + arr[row][col];
				} else if (row == 0) {
					int max = Math.max(arr[row][col + 1], arr[row + 1][col + 1]);
					ans[row][col] = max + arr[row][col];
				}
				else {
					int max = Math.max(Math.max(arr[row][col + 1], arr[row - 1][col + 1]), arr[row + 1][col + 1]);
					ans[row][col] = max + arr[row][col];
				}
			}
		}
		// Find the max value by iterating the last row
		int max = ans[0][0];
		System.out.println(ans.length);
		for (int i = 1; i < ans.length; i++) {
			if(ans[i][0] > max)	{
				max = ans[i][0]; 
				System.out.println(i);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[][] arr = { {1, 3, 1, 5},
		                {2, 2, 4, 1},
		                {5, 0, 2, 3},
		                {0, 6, 1, 2}};
		System.out.println(goldMine(arr));
		
//		System.out.println(minStairPath(5));
//		int n = 5;
////		int[] arr = new int[n+1];
//		fiboTabulation(n);
	}
}

