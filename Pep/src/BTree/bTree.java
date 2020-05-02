package BTree;
import java.util.*;

class BinaryTree {

    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public BinaryTree(int[] dlist) {
        this.root = build(dlist);
    }

    private Node root = null;
    private int size = 0;

    private Node build(int[] dlist) {
        size = 0;
        root = null;
        ArrayList<Node> stack = new ArrayList<>();
        for(int data : dlist) {
            if(data == -1) {
                // pop from stack
                stack.remove(stack.size() - 1);
            } else {
                // make root // node must be present    
                Node nn = new Node(data);
                size++;
                if(root == null) {
                    root = nn;
                } else {
                    Node top = stack.get(stack.size() - 1);
                    if(top.left == null) {
                        top.left = nn;
                    } else {
                        top.right = nn;
                    }
                }
                stack.add(nn);
            }
        }
        return root;
    }

    private void display(Node node) {
        if(node.left != null && node.right != null) {
            // left and right both are present
            System.out.println(node.left.data + " <--" + node.data + 
                                "--> " + node.right.data);
            display(node.left);
            display(node.right);
        } else if(node.left != null) {
            // only left child is there
            System.out.println(node.left.data + " <--" + node.data + "-->  .");
            display(node.left);
        } else if(node.right != null) {
            // only right child is there
            System.out.println(".  <--" + node.data + "--> " + node.right.data);
            display(node.right);
        } else {
            // only node is there, no left child, no right child
            System.out.println(".  <--" + node.data + "-->  .");
        }
    }

    private void display2(Node node) {
        if(node == null) return;
        // left child
        if(node.left != null) {
            System.out.print(node.left.data);
        } else {
            System.out.print(". ");
        }
        // node
        System.out.print(" <--" + node.data + "--> ");
        // right child
        if(node.right != null) {
            System.out.print(node.right.data);
        } else {
            System.out.print(" .");
        }
        System.out.println();
        // calls

        display2(node.left);
        display2(node.right);
    }
    
    public void display() {
        if(root == null) return;
        System.out.println("---Binary Tree---");
        display(root);
        System.out.println("-----------------");
    }

    public void display2() {
        System.out.println("---Binary Tree---");
        display2(root);
        System.out.println("-----------------");
    }

    public int size() {
        return this.size;
    }

    private int size2(Node node) {
        if(node == null) return 0;

        int ls = size2(node.left);
        int rs = size2(node.right);

        return ls + rs + 1;
    }

    public int size2() {
        int sz = size2(root);
        return sz;
    }

    private int max(Node node) {
        if(node == null) return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(node.data, Math.max(lmax, rmax));
    }

    public int max() {
        return max(root);
    }

    private int min(Node node) {
        if(node == null) return Integer.MAX_VALUE;

        int lmin = min(node.left);
        int rmin = min(node.right);

        return Math.min(node.data, Math.min(lmin, rmin));
    }

    public int min() {
        return min(root);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int sum(Node node) {
        if(node == null) return 0;

        int lsum = sum(node.left);
        int rsum = sum(node.right);

        return lsum + rsum + root.data;
    }

    public int sum() {
        return sum(root);
    }

    private int height(Node node) {
        if(node == null) return 0;

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh, rh) + 1;
    }

    public int height() {
        return height(root);
    }

    private boolean find(Node node, int data) {
        if(node == null) return false;

        // self analysis
        if(node.data == data) return true;
        // left analysis
        boolean lres = find(node.left, data);
        if(lres == true) return true;
        // right
        return find(node.right, data);
    }

    public boolean find(int data) {
        return find(root, data);
    }


    // Question ----------------------------------

    private void preOrder(Node node) {
        if(node == null) return;

        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrder() {
        System.out.print("PreOrder : ");
        preOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if(node == null) return;

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void inOrder() {
        System.out.print("InOrder : ");
        inOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public void postOrder() {
        System.out.print("PostOrder : ");
        postOrder(root);
        System.out.println();
    }

    private class Pair {
        Node node;
        int level;

        Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public void levelOrder1() {
        // logic for level order
        LinkedList<Pair> queue = new LinkedList<>();
        int pl = 0;
        queue.addLast(new Pair(root, 0));
        while(queue.size() > 0) {
            Pair rem = queue.removeFirst();
            if(rem.level > pl){
                pl++;
                System.out.println();
            }

            System.out.print(rem.node.data + " ");

            if(rem.node.left != null) {
                queue.addLast(new Pair(rem.node.left, rem.level + 1));
            }
            if(rem.node.right != null) {
                queue.addLast(new Pair(rem.node.right, rem.level + 1));
            }
        }
        System.out.println();
    }
    
    private class IPair {
        Node node;
        boolean print;
        boolean left;
        boolean right;

        IPair(Node node) {
            this.node = node;
            this.left = this.right = false;
        }
    }
 
    public void preOrderItr() {
        LinkedList<IPair> stack = new LinkedList<>();

        System.out.print("PreOrder : ");
        stack.addFirst(new IPair(root));
        while(stack.size() > 0) {
            IPair top = stack.getFirst();

            if(top.print == false) {
                top.print = true;
                System.out.print(top.node.data + ", ");
            } else if(top.left == false) {
                top.left = true;
                if(top.node.left != null) {
                    stack.addFirst(new IPair(top.node.left));
                }
            } else if(top.right == false) {
                top.right = true;
                if(top.node.right != null) {
                    stack.addFirst(new IPair(top.node.right));
                }
            } else {
                stack.removeFirst();
            }
        }
        System.out.println(".");
    }

    public void postOrderItr() {
        LinkedList<IPair> stack = new LinkedList<>();
        System.out.print("PostOrder : ");
        stack.addFirst(new IPair(root));
        while(stack.size() > 0) {
            IPair top = stack.getFirst();

            if(top.left == false) {
                top.left = true;
                if(top.node.left != null) {
                    stack.addFirst(new IPair(top.node.left));
                }
            } else if(top.right == false) {
                top.right = true;
                if(top.node.right != null) {
                    stack.addFirst(new IPair(top.node.right));
                }
            } else if(top.print == false) {
                top.print = true;
                System.out.print(top.node.data + ", ");
            } else {
                stack.removeFirst();
            }
        }
        System.out.println(".");
    }

    public void inOrderItr() {
        LinkedList<IPair> stack = new LinkedList<>();

        System.out.print("InOrder : ");
        stack.addFirst(new IPair(root));
        while(stack.size() > 0) {
            IPair top = stack.getFirst();

            if(top.left == false) {
                top.left = true;
                if(top.node.left != null) {
                    stack.addFirst(new IPair(top.node.left));
                }
            } else if(top.print == false) {
                top.print = true;
                System.out.print(top.node.data + ", ");
            } else if(top.right == false) {
                top.right = true;
                if(top.node.right != null) {
                    stack.addFirst(new IPair(top.node.right));
                }
            } else {
                stack.removeFirst();
            }
        }
        System.out.println(".");
    }

    private void printSingleChild(Node parent, Node child) {
        if(child == null) return;

        if(parent.left == child && parent.right == null) {
            System.out.print(child.data + " ");
        } else if(parent.right == child && parent.left == null) {
            System.out.print(child.data + " ");
        }

        printSingleChild(child, child.left);
        printSingleChild(child, child.right);
    }

    public void printSingleChild() {
        if(root == null) return;
        printSingleChild(root, root.left);
        printSingleChild(root, root.right);
        System.out.println();
    }

    private void removeLeaves(Node parent, Node child) {
        if(child == null) return;

        // identification for leaf node
        if(child.left == null && child.right == null) {
            // identify the side of child and remove it
            this.size--;
            if(parent.left == child) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return;
        }
        removeLeaves(child, child.left);
        removeLeaves(child, child.right);  
    }

    public void removeLeaves() {
        if(root.left == null && root.right == null) {
            root = null;
            return;
        }
        removeLeaves(root, root.left);
        removeLeaves(root, root.right);
    }

    private void root2leaf(Node node, String psf, int cost) {
        if(node.left == null && node.right == null) {
            psf = psf + "->" + node.data;
            cost += node.data;
            System.out.println(psf + " @ " + cost);
            return;
        }

        root2leaf(node.left, psf + node.data + "->", cost + node.data);
        root2leaf(node.right, psf + node.data + "->", cost + node.data);
    } 

    public void root2leaf() {
        if(root == null) return;
        root2leaf(root, "", 0);
    }

    private void pir(Node node, String psf, int cost, int lo, int hi) {
        if(node.left == null && node.right == null) {
            psf = psf + "->" + node.data;
            cost += node.data;
            if(cost >= lo && cost <= hi)
                System.out.println(psf + " @ " + cost);
            return;
        }

        pir(node.left, psf + node.data + "->", cost + node.data, lo, hi);
        pir(node.right, psf + node.data + "->", cost + node.data, lo, hi);
    }

    public void pir(int lo, int hi) {
        if(root == null) return;
        pir(root, "", 0, lo, hi);
    }

    // private void kdown(Node node, int k) {
    //     if(node == null) return;

    //     if(k == 0) {
    //         System.out.print(node.data + " ");
    //         return;
    //     }
        
    //     kdown(node.left, k - 1);
    //     kdown(node.right, k - 1);
    // }

    public void kdown(int k) {
        kdown(root, null, k);
        System.out.println();
    }

    private ArrayList<Integer> nodeToRoot(Node node, int key) {
        if(node == null) return null;

        if(node.data == key) {
            ArrayList<Integer> bres = new ArrayList<>();
            bres.add(node.data);
            return bres;
        }

        ArrayList<Integer> lres = nodeToRoot(node.left, key);
        if(lres != null) {
            lres.add(node.data);
            return lres;
        }
        ArrayList<Integer> rres = nodeToRoot(node.right, key);
        if(rres != null) {
            rres.add(node.data);
            return rres;
        }
        return null;
    }

    public ArrayList<Integer> nodeToRoot(int key) {
        ArrayList<Integer> res = nodeToRoot(root, key);
        return res;
    }

    private ArrayList<Node> nodeToRoot2(Node node, int key) {
        if(node == null) return null;

        if(node.data == key) {
            ArrayList<Node> bres = new ArrayList<>();
            bres.add(node);
            return bres;
        }

        ArrayList<Node> lres = nodeToRoot2(node.left, key);
        if(lres != null) {
            lres.add(node);
            return lres;
        }
        ArrayList<Node> rres = nodeToRoot2(node.right, key);
        if(rres != null) {
            rres.add(node);
            return rres;
        }
        return null;
    }

    public void nodeToRootPrint(int key) {
        ArrayList<Node> res = nodeToRoot2(root, key);
        for(Node node : res) {
            System.out.print(node.data + "->");
        }
        System.out.println();
    }

    private void kdown(Node node, Node prhb, int k) {
        if(node == null || node == prhb) return;

        if(k == 0) {
            System.out.print(node.data + " ");
            return;
        }
        
        kdown(node.left, prhb, k - 1);
        kdown(node.right, prhb,  k - 1);
    }

    public void kfar(int data, int k) {
        ArrayList<Node> path = nodeToRoot2(root, data);
        
        for(int i = 0; i < path.size() && k >= 0; i++, k--) {
            Node node = path.get(i);
            if(i == 0) {
                // prhb == null so that kdown work normally
                kdown(node, null, k);
            } else {
                // prohibited is basically previous node from current i index
                Node prhb = path.get(i - 1);
                kdown(node, prhb, k);
            }
        }
        System.out.println();
    }

    private int diameter(Node node) {
        if(node == null) {
            return 0;
        }

        int lh = height(node.left);
        int rh = height(node.right);

        int ld = diameter(node.left);
        int rd = diameter(node.right);

        int d1 = ld;
        int d2 = rd;
        int d3 = lh + rh + 1;

        return Math.max(d1, Math.max(d2, d3));
    }

    private class DiaPair {
        int ht;
        int dia;
    };

    private DiaPair diameter2(Node node) {
        if(node == null) {
            DiaPair bres = new DiaPair();
            bres.ht = 0;
            bres.dia = 0;

            return bres;
        }

        DiaPair lpair = diameter2(node.left);
        DiaPair rpair = diameter2(node.right);

        DiaPair mpair = new DiaPair();
        mpair.ht = Math.max(lpair.ht, rpair.ht) + 1;
        mpair.dia = Math.max(lpair.ht + rpair.ht + 1, Math.max(lpair.dia, rpair.dia));

        return mpair;
    }

    public int diameter() {
        // int dia = diameter(root);
        DiaPair res = diameter2(root);

        return res.dia;
    }


    private Node construct1(int[] pre, int[] in, int preSt,
                         int preEnd, int inSt, int inEnd) {
        
        if(inSt > inEnd || preSt > preEnd) {
            return null;
        }

        int data = pre[preSt]; // because in virtual array, first member is preStart
        Node node = new Node(data);
        this.size++;
        int idx = -1; // data get in INORDER;
        
        for(int i = inSt; i <= inEnd; i++) {
            if(in[i] == data) {
                idx = i;
                break;
            }
        }

        int ildc = idx - inSt; // inorder left data count

        node.left = construct1(pre, in, preSt + 1, preSt + ildc, inSt, idx - 1);
        node.right = construct1(pre, in, preSt + ildc + 1 , preEnd, idx + 1, inEnd);

        return node;
    }

    // constructor for build a tree with pre and in
    public BinaryTree(int[] pre, int[] in) {
        this.root = construct1(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    private Node construct2(int[] post, int[] in, int postSt, int postEnd,
                int inSt, int inEnd) {
                    
        if(postSt > postEnd || inSt > inEnd) {
            return null;
        }

        int data = post[postEnd];
        Node node = new Node(data);
        // System.out.println(data);
        this.size++;

        int idx = -1; // data get in INORDER;
        
        for(int i = inSt; i <= inEnd; i++) {
            if(in[i] == data) {
                idx = i;
                break;
            }
        }

        int dc = idx - inSt - 1;
        node.left = construct2(post, in, postSt, postSt + dc, inSt, idx - 1);
        node.right = construct2(post, in, postSt + dc + 1, postEnd - 1, idx + 1, inEnd);

        return node;
    }

    // constructor for build a tree with post and in
    public BinaryTree(int[] post, int[] in, boolean flag) {
        this.root = construct2(post, in, 0, post.length - 1, 0, in.length - 1);
    }

    // balfactor1 is of complexity O(n^2)
    private boolean isBalance1(Node node) {
        if(node == null) return true;

        boolean lbal = isBalance1(node.left);
        boolean rbal = isBalance1(node.right);

        if(lbal == true && rbal == true) {
            int lh = height(node.left);
            int rh = height(node.right);
            int balfactor = Math.abs(lh - rh);
            if(balfactor >= 0 && balfactor <= 1) {
                return true;
            }
        }
        return false;
    }

    private class BalPair {
        boolean isBal = false;
        int ht = 0;
    }

    private BalPair isBalance(Node node) {
        if(node == null) {
            BalPair bres = new BalPair();
            bres.ht = 0;
            bres.isBal = true;
            
            return bres;
        }

        BalPair lpair = isBalance(node.left);
        if(lpair.isBal == true) {
            BalPair rpair = isBalance(node.right);
            
            BalPair mpair = new BalPair();
            int factor = Math.abs(lpair.ht - rpair.ht);

            mpair.isBal = lpair.isBal && rpair.isBal && (factor >=0 && factor <= 1) ;
            mpair.ht = Math.max(lpair.ht, rpair.ht) + 1;
            return mpair;
        } else {

            BalPair mpair = new BalPair();
            mpair.isBal = false;
            return mpair;
        }
    }

    public boolean isBalance() {
        BalPair rpair = isBalance(root);
        return rpair.isBal;
        // return isBalance1(root);
    }
    
    // it have copmplexity O(n^2)
    private boolean isBST1(Node node) {
        if(node == null) return true;

        boolean lres = isBST1(node.left);
        if(lres == true) {
            boolean rres = isBST1(node.right);
            if(rres == true) {
                int lmax = max(node.left);
                int rmin = min(node.right);
                if(lmax < node.data && rmin > node.data) {
                    return true;
                }
            }
        }
        return false;
    }

    private class BSTPair {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int size = 0;
        Node bstRoot = null;

        boolean isBST = true;
    };

    private BSTPair isBST(Node node) {
        if(node == null) {
            return new BSTPair();
        }

        BSTPair lp = isBST(node.left);
        if(lp.isBST == true) {
            BSTPair rp = isBST(node.right);
            
            BSTPair mp = new BSTPair();
            mp.isBST = lp.isBST && rp.isBST && (lp.max < node.data && rp.min > node.data);
            mp.min = Math.min(lp.min, Math.min(node.data, rp.min));
            mp.max = Math.max(lp.max, Math.max(node.data, rp.max));
            return mp;
        } else {
            BSTPair mp = new BSTPair();
            mp.isBST = false;
            return mp;
        }
    }

    // default constructor
    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean isBST() {
        BSTPair rp = isBST(root);
        return rp.isBST;
        // return isBST1(root);
    }

    private BSTPair largeBST(Node node) {
        if(node == null) {
            return new BSTPair();
        }

        BSTPair lp = largeBST(node.left);
        BSTPair rp = largeBST(node.right);
        
        BSTPair mp = new BSTPair();
        mp.isBST = lp.isBST && rp.isBST && (lp.max < node.data && rp.min > node.data);
        mp.min = Math.min(lp.min, Math.min(node.data, rp.min));
        mp.max = Math.max(lp.max, Math.max(node.data, rp.max));
        
        if(mp.isBST == true) {
            mp.size = lp.size + rp.size + 1;
            mp.bstRoot = node;
        } else {
            if(lp.size > rp.size) {
                mp.size = lp.size;
                mp.bstRoot = lp.bstRoot;
            } else {
                mp.size = rp.size;
                mp.bstRoot = rp.bstRoot;
            }
        }

        return mp;
    }

    public void largeBST(BinaryTree lbst) {
        BSTPair res = largeBST(root);
        lbst.root = res.bstRoot;
        lbst.size = res.size;
    }

}

public class btree {

    public static void main(String[] args) {
        int[] pre = {70, 60, 50, 25, 12, 37, 75, 62, 59, 67, 87, 
                    65, 62, 80, 75, 90, 85, 95};
        
        int[] in = {12, 25, 37, 50, 59, 62, 67, 75, 87, 60, 62, 65, 
                70, 75, 80, 85, 90, 95};
        
        BinaryTree bt = new BinaryTree(pre, in);
        BinaryTree LBST = new BinaryTree();
        bt.largeBST(LBST);
        LBST.display();
        System.out.println(LBST.size());



        // int[] pre = {50, 25, 12, 55, 30, 40, 38, 42, 75, 87, 80};
        // int[] in = {12, 25, 30, 55, 38, 40, 42, 50, 75, 80, 87};
        // BinaryTree bt = new BinaryTree(pre, in);
        // bt.display();
        // System.out.println("isBalance : " + bt.isBalance());
        // System.out.println("isBST : " + bt.isBST());



        // int[] in = {12, 20, 25, 30, 37, 50, 62, 75, 87};
        // int[] pre = {50, 25, 12, 20, 37, 30, 75, 62, 87};
        // int[] post = {20, 12, 30, 37, 25, 62, 87, 75, 50};
        // BinaryTree bt = new BinaryTree(pre, in);
        // bt.display();

        // int[] in = {25, 37, 50, 62, 75};
        // int[] post = {37, 25, 62, 75, 50};
        // BinaryTree bt = new BinaryTree(post, in, true);
        // bt.display();

        // int[] dlist = {
        //     50, 25, 12, -1, 37, 30, -1, 40, 38, -1, 45, -1, -1, -1, -1,
        //      75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1
        // };
        
        // int[] dlist = {
        //     50, 25, 12, -1, 37, 30, -1, -1, -1, 75, 62, 
        //     60, -1, -1, -1, -1
        // };

        // int[] dialist = {
        //     1, 2, 4, 8, 12, 18, 26, -1, 27, -1, -1, 19, -1,
        //     -1, 13, -1, -1, 9, -1, -1, 5, 10, 14, 20, -1, 
        //     21, 28, 30, -1, 31, 32, -1, 33, -1, -1, -1, 
        //     29, -1, -1, -1, 15, -1, -1, 11, 16, 22, -1, 
        //     23, -1, -1, 17, 24, -1, 25, -1, -1, -1, -1, 
        //     -1, 3, 6, -1, 7, -1, -1, -1
        // };

        // BinaryTree bt = new BinaryTree(dialist);
        // bt.removeLeaves();
        // bt.display();
        // System.out.println("Diameter : " + bt.diameter());



        // bt.kfar(37, 2);
        // ArrayList<Integer> path = bt.nodeToRoot(62);
        // bt.nodeToRootPrint(60);
        // System.out.println(path);
        // bt.root2leaf();
        // bt.pir(150, 250);
        // bt.kdown(5);


        // bt.preOrderItr();
        // bt.postOrderItr();
        // bt.inOrderItr(); 
        // bt.display();
        // bt.removeLeaves();
        // bt.display();
        // bt.printSingleChild();


        // bt.display();
        // System.out.println("Size 1 : " + bt.size());
        // System.out.println("Size 2 : " + bt.size2());
        // System.out.println("Min : " + bt.min());
        // System.out.println("Max : " + bt.max());
        // System.out.println("IsEmpty : " + bt.isEmpty());
        // System.out.println("Sum : " + bt.sum());
        // System.out.println("Height : " + bt.height());
        // System.out.println("Find : (100) " + bt.find(100));
        // bt.preOrder();
        // bt.inOrder();
        // bt.postOrder();
        // bt.levelOrder1();

        // nBinary(20);
    
    }


    static class Pair {
        int num;
        String str = "";

        Pair(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }
    
    static void nBinary(int  n) {
        LinkedList<Pair> queue = new LinkedList<>();
        queue.addLast(new Pair(1, "1"));
        while(queue.size() > 0) {
            Pair rem = queue.removeFirst();
            System.out.println(rem.num + " -> " + rem.str);
            // left add
            if((2 * rem.num) <= n) {
                queue.addLast(new Pair(2 * rem.num, rem.str + "0"));
            }
            // right add
            if((2 * rem.num + 1) <= n) {
                queue.addLast(new Pair(2 * rem.num + 1, rem.str + "1"));
            }
        }
    }
}}
