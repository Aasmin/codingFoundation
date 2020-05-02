package BSTree;

import java.util.ArrayList;

public class BinarySearchTree {

	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			this.left = this.right = null;
		}
	}

	private Node root = null;

	private int size = 0;
	
	public BinarySearchTree() {}

	public void display() {
		if (root == null)
			return;
		System.out.println("-Binary Serach Tree-");
		display(root);
		System.out.println("--------------------");
	}

	private void display(Node node) {
		if (node.left != null && node.right != null) {
			// left and right both are present
			System.out.println("   " + node.left.data + " <--" + node.data + "--> " + node.right.data);
			display(node.left);
			display(node.right);
		} else if (node.left != null) {
			// only left child is there
			System.out.println("   " + node.left.data + " <--" + node.data + "-->  .");
			display(node.left);
		} else if (node.right != null) {
			// only right child is there
			System.out.println("   .  <--" + node.data + "--> " + node.right.data);
			display(node.right);
		} else {
			// only node is there, no left child, no right child
			System.out.println("   .  <--" + node.data + "-->  .");
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private Node construct1(int[] pre, int[] in, int preSt, int preEnd, int inSt, int inEnd) {

		if (inSt > inEnd || preSt > preEnd) {
			return null;
		}

		int data = pre[preSt]; // because in virtual array, first member is preStart
		Node node = new Node(data);
		this.size++;
		int idx = -1; // data get in INORDER;

		for (int i = inSt; i <= inEnd; i++) {
			if (in[i] == data) {
				idx = i;
				break;
			}
		}

		int ildc = idx - inSt; // inorder left data count

		node.left = construct1(pre, in, preSt + 1, preSt + ildc, inSt, idx - 1);
		node.right = construct1(pre, in, preSt + ildc + 1, preEnd, idx + 1, inEnd);

		return node;
	}

	// constructor for build a tree with pre and in
	public BinarySearchTree(int[] pre, int[] in) {
		this.root = construct1(pre, in, 0, pre.length - 1, 0, in.length - 1);
	}
	
	public BinarySearchTree(int[] in) {
		this.root = construct(in, 0, in.length - 1);
	}
	
	private Node construct(int[] in, int lo, int hi) {
		if(lo > hi) return null;
		
		int mid = (lo + hi) / 2;
		int data = in[mid];
		
		Node node = new Node(data);
		this.size++;
		
		node.left = construct(in, lo, mid - 1);
		node.right = construct(in, mid + 1, hi);
		
		return node;
	}
	
	public int min() {
		if(root == null) return -1;
		return min(root);
	}
	
	private int min(Node node) {
		if(node.left == null) return node.data;
		
		return min(node.left);
	}
	
	public int max() {
		if(root == null) return -1;
		return max(root);
	}
	
	private int max(Node node) {
		if(node.right == null) return node.data;
		
		return max(node.right);
	}
	
	public boolean find(int data) {
		return find(root, data);
	}
	
	private boolean find(Node node, int data) {
		if(node == null) return false;
		
		if(node.data == data) {
			return true;
		} else if(node.data > data) {
			// left side
			return find(node.left, data);
		} else {
			// right size
			return find(node.right, data);
		}
	}
	
	// ptrgtp == print target pair
	public void ptrgtp(int targ) {
		ptrgtp1(root, targ);
	}
	
	private void ptrgtp1(Node node, int targ) {
		if(node == null) return;
		
		int avl = node.data;
		int req = targ - node.data;
		
		if(req != avl && find(this.root,req) == true && req > avl) {
			System.out.println(avl + " + " + req + " = " + targ);
		}
		
		ptrgtp1(node.left, targ);
		ptrgtp1(node.right, targ);
	}
	
	private void filler(ArrayList<Integer> list, Node node) {
		if(node == null) return;
		
		filler(list, node.left);
		list.add(node.data);
		filler(list, node.right);
	}
	
	public void ptrgtp2(int targ) {
		ptrgtp2(root, targ);
	}
	
	private void ptrgtp2(Node node, int targ) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		filler(list, node);
		int lo = 0;
		int hi = list.size() - 1;
		
		while(lo < hi) {
			int dsum = list.get(lo) + list.get(hi);
			if(dsum == targ) {
				int ldata = list.get(lo);
				int hdata = list.get(hi);
				System.out.println(ldata + " + " + hdata + " = " + dsum);
				
				lo++;
				hi--;
			} else if(dsum < targ) {
				lo++;
			} else {
				hi--;
			}
		}
		
	}
	
	private int sum = 0;
	
	// replace with sum of largest
	public void rwsol() {
		rwsol(root);
	}
	
	private void rwsol(Node node) {
		if(node == null) return;
		
		rwsol(node.right); // right set
		
		// self settlement and impact on sum variable
		int temp = node.data;
		node.data = sum;
		sum += temp;
		
		rwsol(node.left); // left set
	}
	
	public void add(int data) {
		this.root = add(root, data);
	}
	
	private Node add(Node node, int data) {
		if(node == null) {
			Node nn = new Node(data);
			this.size++;
			return nn;
		}
		
		if(node.data > data) {
			// add in left side
			node.left = add(node.left, data);
		} else if(node.data < data) {
			// add in right side
			node.right = add(node.right, data);
		}
		return node;
	}
	
	public void remove(int data) {
		if(find(root, data) == true) {
			this.root = remove(root, data);
		}
	}
	
	private Node remove(Node node, int data) {
		if(node == null) return null;
		
		if(node.data > data) {
			node.left = remove(node.left, data);
		} else if(node.data < data) {
			node.right = remove(node.right, data);
		} else {
			 if(node.left != null && node.right != null) {
				 int lmax = max(node.left);
				 node.data = lmax;
				 node.left = remove(node.left, lmax);
			 } else {
				 node = node.left == null ? node.right : node.left;
			 }
		}
		return node;
	}
	
	
	
	
	
}