package dp;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class dp3 {
	static class Item implements Comparable<Item>{
		int weight;
		int price;
		double priceBywt;
		
		public Item(int wt, int pr, double pbywt) {
			// TODO Auto-generated constructor stub
			this.weight = wt;
			this.price = pr;
			this.priceBywt = pbywt;
		}
		
		public int compareTo(Item o) {
			double pbwt1 = this.priceBywt;
			double pbwt2 = o.priceBywt;
			if (pbwt1 == pbwt2) return 0;
			else if (pbwt1 > pbwt2) return 1;
			else return -1;
		}
	}
	
	static void arrangeBuildings(int n) {
		//storage
		int[] inc = new int[n + 1];
		int[] exc = new int[n + 1];
		inc[1] = 1;
		exc[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			inc[i] = exc[i - 1];
			exc[i] = exc[i - 1] + inc[i - 1];
		}
		long oneSide = inc[n] + exc[n];
		System.out.println(oneSide^2);
	}
	
	static void countBinaryStrings(int n) {
		//storage
		int[] zero = new int[n + 1];
		int[] one = new int[n + 1];
		zero[1] = 1;
		one[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			zero[i] = one[i - 1];
			one[i] = one[i - 1] + zero[i - 1];
		}
		long oneSide = zero[n] + one[n];
		System.out.println(oneSide);
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] vals = new int[n + 1];
		for (int i = 0; i < n; i++) {
			vals[i] = scn.nextInt();
		}
		int[] wts = new int[n + 1];
		for (int i = 0; i < n; i++) {
			wts[i] = scn.nextInt();
		}
		int cap = scn.nextInt();
		
		PriorityQueue<Item> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < n; i++) {
			pq.add(new Item(wts[i], vals[i], vals[i] /(double) wts[i]));
		}
		
		int rcap = cap;
		double amount = 0;
		while(rcap != 0) {
			Item i = pq.peek();	//top
			pq.remove();	//remove from top
			
			if(rcap >= i.weight) {
				rcap -= i.weight;
				amount += i.price;
			} else {
				amount = amount + (rcap * i.priceBywt);
				rcap = 0;
			}
		}
		System.out.println(amount);
//		arrangeBuildings(n);
//		countBinaryStrings(n);
	}
}
