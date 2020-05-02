package BSTree;

public class Client {

	public static void main(String[] args) {
//		int[] in = {12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87};
//		int[] pre = {50, 25, 12, 37, 30, 40, 75, 62, 60, 70, 87};
//		BinarySearchTree bst = new BinarySearchTree(pre, in);
		
		int[] in = {10, 20, 30, 40, 50, 60, 70, 80, 90};
		BinarySearchTree bst = new BinarySearchTree(in);
		
		bst.display();
//		bst.add(25);
//		bst.add(55);
		bst.remove(50);
		bst.remove(90);
//		bst.rwsol(); 
		bst.display();
//		System.out.println("Size : " + bst.size());
//		System.out.println("Max : " + bst.max());
//		System.out.println("Min : " + bst.min());
//		int data = 40;
//		System.out.println("Find (" + data + ") : " + bst.find(data));
//		bst.ptrgtp(100);
//		System.out.println();
//		bst.ptrgtp2(100);
		
	}

}