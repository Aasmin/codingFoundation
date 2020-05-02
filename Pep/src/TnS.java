import java.util.Arrays;

public class TnS {
	static void quickSort(int[] arr, int lo, int hi) {
		if(lo >= hi) {	//lo = hi means ek banda bacha hai, lo > hi means koi bnda ni bacha
			return;
		}
		
		int pivot = arr[hi];
		int pivIdx = partition(arr, lo, hi, pivot);
		quickSort(arr, lo, pivIdx - 1); //smaller
		quickSort(arr, pivIdx + 1, hi); //larger
	}
	
	static int partition(int[] arr, int lo, int hi, int pivot) {
		int parIdx = lo;
		int itr = lo;
		// 0 to parIdx - 1 => elem <= pivot
		// parIdx to idr - 1 => elem > pivot
		// itr to unknown 
		while(itr <= hi) {		//itr completes its journey i.e itr end tk phunch geya
			if(arr[itr] <= pivot) {
//				int temp = arr[itr];
//				arr[itr] = arr[parIdx];
//				arr[parIdx] = temp;
				swap(arr, itr, parIdx);
				itr++;
				parIdx++;
			} else	{
				itr++;
			}
		}
		
		return parIdx - 1;
	}
	
	static int[] merge(int[] one, int[] two) {
		int[] merged = new int[one.length + two.length];
		int i = 0;		int j = 0;		int k = 0;
		while(i < one.length && j < two.length) {
			if(one[i] < two[j]) {
				merged[k] = one[i];
				i++;	k++;
			}
			else {
				merged[k] = two[j];
				j++;	k++;
			}
		}
		while(i < one.length) {
			merged[k] = one[i];
			i++;	k++;
		}
		while(j < two.length) {
			merged[k] = two[j];
			j++;	k++;
		}
		return merged;
	}
	
	static int[] mergeSort(int[] arr, int lo, int hi) {
		if(lo == hi) {
			int[] barr = new int[1];
			barr[0] = arr[lo];
			return barr;
		}
		int mid = (lo + hi) / 2;
		int[] fhalf = mergeSort(arr, lo, mid);
		int[] shalf = mergeSort(arr, mid + 1, hi);
		return merge(fhalf, shalf);
	}
	
	static boolean linearSeach(int[] arr, int key) {
		for(int val: arr) {
			if(val == key)	return true; 
		}
		
		return false;
	}
	
	static boolean binarySearch(int[] arr, int low, int high, int key) {
		if(low > high) return false;
		
		int mid = (low + high) / 2;
		if(arr[mid] == key)	return true;
		else if(arr[mid] > key) return binarySearch(arr, low, mid - 1, key);
		else return binarySearch(arr, mid + 1, high, key);
	}
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp; 
	}
	static void bubbleSort(int[] arr) {
		int j = arr.length;
		while(j != 0) {
			for (int i = 1; i < arr.length; i++) {
				if(arr[i - 1] > arr[i]) {
					int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
				}
			}
			j--;
		}
	}
	
	static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[minIndex])
					minIndex = j;
			}
			swap(arr, i, minIndex);
		}
	}

	static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if(arr[j] < arr[j-1]) {
					swap(arr, j, j - 1);
				} else {
					break;
				}
			}
		}
//		for (int i = 0; i < arr.length - 1; i++) {
//			for (int j = i + 1; j > 0; j--) {
//				if(arr[j] < arr[j - 1])
//					swap(arr, j, j - 1);
//			}
//		}
	}

	static void nPrimesSieveofEratosthenes(int n) {
		boolean[] arr = new boolean[n+1];
		Arrays.fill(arr, true);
//		for (int i = 0; i < n; i++) {
//			arr[i] = true;
//		}
		arr[0] = false;
		arr[1] = false;
		for (int i = 2; i * i <= n; i++) {
			if(arr[i] == true) {
				for (int j = i * i; j <= n; j = j + i) {
					arr[j] = false;
				}
			}
		}
		System.out.println("\"Prime Numbers: \"");
		for (int i = 0; i < n; i++) {
			if (arr[i] == true) {
				 System.out.print(i + ", ");
			}
		}
		
	}
	

	static int[] primeFacetorisationSieve(int n) {
		int[] arr = new int[n+1];
		for (int i = 0; i < n + 1; i++) {
			arr[i] = i;
		}
		
		for(int i = 2; i * i <= n; i++) {
			if(arr[i] == i) {
				for (int j = i * i; j <= n; j = j + i) {
					if(arr[j] == j) {
						arr[j] = i;
					}
				}
			}
		}
		return arr;
//		System.out.println(Arrays.toString(arr));
//		int div = 2;
//		while (div * div <= n) {
//			if(n % div == 0) {
//				System.out.print(div + " ");
//				n /= div;
//			} else {
//				div++;
//			}
//		}
//		if(n != 1)	System.out.println(n);
	}
	
	static void factorisatiion(int n) {
		int[] arr = primeFacetorisationSieve(n);
		System.out.print(n + " = ");
		while(arr[n] != n) {
			System.out.print(arr[n] + " * ");
			n = n / arr[n];
		}
		System.out.println(n);
	}

	static int[] merge1(int[] one, int[] two) {
		int[] merged = new int[one.length + two.length];
		int i = 0;		int j = 0;		int k = 0;
		while(i < one.length && j < two.length) {
			if(one[i] < two[j]) {
				merged[k] = one[i];
				i++;	k++;
			}
			else {
				merged[k] = two[j];
				j++;	k++;
			}
		}
		while(i < one.length) {
			merged[k] = one[i];
			i++;	k++;
		}
		while(j < two.length) {
			merged[k] = two[j];
			j++;	k++;
		}
		return merged;
	}
	
	static int counter = 0;
	
	static int inversionCount(int[] A, int[] B) {
		int count = 0; 
		int i = 0; int j = 0;
		while(i< A.length && j < B.length) {
			if(A[i] < B[j]) {
				i++;
			} else {
				count += A.length - i;
				j++;
			}
		}
		return count;
	}
	
	static int[] mergeSort1(int[] arr, int lo, int hi) {
		if(lo == hi) {
			int[] barr = new int[1];
			barr[0] = arr[lo];
			return barr;
		}
		int mid = (lo + hi) / 2;
		int[] fhalf = mergeSort(arr, lo, mid);
		int[] shalf = mergeSort(arr, mid + 1, hi);
		
		counter = inversionCount(fhalf, shalf);
		return merge(fhalf, shalf);
	}
	
	static void sort01(int[] arr) {
		int i = 0; int j = 0;
		while(j < arr.length) {
			if(arr[j] == 0) {
				swap(arr, i, j);
				i++;	j++;
			}
			else	j++;
		}
	}
	
	static void sort012(int[] arr) {
		int i = 0; int j = 0;	int k = arr.length - 1;
		while(j <= k) {
			if(arr[j] == 0) {
				swap(arr, i, j);
				i++;	j++;
			}
			else if(arr[j] == 1)	j++;
			else {	
				swap(arr, j, k);
				k--;
			}
		}
	}
	//f(x) = 1.x^n + 2.n^n-1 + 3.n^n-2 + ... + (n-1).x^2 + n.x^1
	static int polynomial(int x, int n) {
		// O(n)
		int sum = 0;
		int xVal = x;
		for (int i = n; i > 0; i--) {
			sum += (i*xVal);
			xVal *= x;
		}
		
		// O(nLogn)
//		int sum = 0; int varN = n;
//		for (int i = 1; i <= n; i++ , varN--) {
//			sum += i * Math.pow(x, varN);
//		}
		return sum;
	}
	
	static int quickSelect(int[] arr, int lo, int hi, int indx) {
		int pivot = arr[hi];
		int pivIdx = partition(arr, lo, hi, pivot);
		if(pivIdx == indx) {
			return arr[indx];
		} else if(indx > pivIdx) {
			//right
			return quickSelect(arr, pivIdx + 1, hi, indx); 
		}
		else
			return quickSelect(arr, lo, pivIdx - 1, indx);
	}
	public static void main(String[] args) {
		int[] arr = {2, 5, 1, 8, 6, 3, 7, 4};
		System.out.println(quickSelect(arr, 0, arr.length - 1, 4));
//		quickSort(arr, 0, arr.length - 1);
//		int[] arr = {0, 1, 2, 1, 1, 0, 1, 0, 1};
//		int[] arr = {2, 2, 3, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 2, 0, 0, 1, 1, 0};
//		sort012(arr);
//		System.out.println(Arrays.toString(arr));
		
//		still left
//		mergeSort1(arr, 0, arr.length - 1);
//		System.out.println(counter);
		
//		factorisatiion(36);
		
//		nPrimesSieveofEratosthenes(35);
		
//		System.out.println(polynomial(2, 3));
		
//		int[] arr = {2, 1, 0, 5, 9, 6, 3, 7};
		//quickSort(arr, 0, arr.length - 1);
//		int[] arr = {10, 7, 2, 1, 5, 9, 8, 6, 12};
//		insertionSort(arr);
//		System.out.println(Arrays.toString(arr));
		
//		System.out.println(Arrays.toString(mergeSort(arr, 0, arr.length - 1)));
//		int[] a = { 2, 5, 9, 15, 19, 20};	int[] b = {0, 3, 7, 8, 11};
//		int[] arr = {10, 7, 2, 1, 5, 9, 8, 6, 12};
//		bubbleSort(arr); 
//		System.out.println(Arrays.toString(arr));
//		int[] arr = {10, 20 , 30, 40, 50, 60, 70, 80, 90};
//		System.out.println(linearSeach(arr, 44));
//		System.out.println(binarySearch(arr, 0, arr.length - 1, 75));
	}
}
