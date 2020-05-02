package recursion;

public class Recursion6 {

	static int counter = 0;
	public static void permutations(boolean[] boxes, int tq, int qpsf, String asf) {
		if(qpsf == tq) {
			counter++;
			System.out.println(counter + ". " + asf);
			return;
		}
		
		for (int b = 0; b < boxes.length; b++) {
			if(boxes[b] == false) {	// we can't place queen in this box
				boxes[b] = true;	//pre area
				permutations(boxes, tq, qpsf + 1, asf + "q" + (qpsf + 1) + "b" + b);	
				boxes[b] = false;	// replacement area
			}
		}
	}
	//lqpb = last queen is placed in which box
	public static void combinations(boolean[] boxes, int tq, int qpsf, String asf, int lqpb) {
		if(qpsf == tq) {
			counter++;
			System.out.println(counter + ". " + asf);
			return;
		}
		
		for (int b = lqpb + 1; b < boxes.length; b++) {
			if(boxes[b] == false) {	// we can't place queen in this box
				boxes[b] = true;	//pre area
				combinations(boxes, tq, qpsf + 1, asf + "q" + (qpsf + 1) + "b" + b, b);	
				boxes[b] = false;	// replacement area
			}
		}
	}
	
	public static void coinChangeCombinations(int[] denominations, int amttobepaid, int lastpaymentmadeinst, String asf) {
		if(amttobepaid == 0) {
			counter++;
			System.out.println(counter + ". " + asf);
			return;
		}
		//denom = {2, 3, 5, 6}
		// agar lastpymtinst is 5, so we can only pay with 5 or 6 now.
		for (int installment = lastpaymentmadeinst; installment < denominations.length; installment++) { 
			if (amttobepaid >= denominations[installment]) {
				coinChangeCombinations(denominations, amttobepaid - denominations[installment], installment, asf + denominations[installment]);
				
			}
		}
	}

	public static void coinChangePerm(int[] denominations, int amttobepaid, int lastpaymentmadeinst, String asf) {
		if(amttobepaid == 0) {
			counter++;
			System.out.println(counter + ". " + asf);
			return;
		}
		//denom = {2, 3, 5, 6}
		// agar lastpymtinst is 5, so we can only pay with 5 or 6 now.
		for (int installment = 0; installment < denominations.length; installment++) { 
			if (amttobepaid >= denominations[installment]) {
				coinChangePerm(denominations, amttobepaid - denominations[installment], installment, asf + denominations[installment]);
				
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		coinChangePerm(new int[] {2, 3, 5, 6}, 10, 0, "");
//		coinChangeCombinations(new int[] {2, 3, 5, 6}, 10, 0, "");
//		combinations(new boolean[4], 2, 0, "", -1);
//		permutations(new boolean[4], 2, 0, "");

	}

}
