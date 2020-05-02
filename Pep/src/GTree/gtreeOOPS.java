package GTree;

import java.util.*;

class GenericTree {
    private class Node {
        int data;
        ArrayList<Node> children = null;

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    private Node root = null;

    private int size = 0;

    public int size() {
        return this.size;
    }

    public GenericTree(int[] dlist) {
        root = build(dlist);
    }

    private Node build(int[] dlist) {
        Node root = null;
        ArrayList<Node> stack = new ArrayList<>();
        for(int i = 0; i < dlist.length; i++) {
            int data = dlist[i];
            if(data == -1) {
                // pop from top OR pop from end
                stack.remove(stack.size() - 1);
            } else {
                Node nn = new Node(data);
                this.size++;
                if(root == null) {
                    root = nn;
                } else {
                    Node topNode = stack.get(stack.size() - 1);
                    topNode.children.add(nn);
                }
                stack.add(nn);
            }
        }
        return root;
    }

    private void display(Node root) {
        if(root == null) return;

        System.out.print("[" + root.data + "]-> ");
        // print children of root
        for(int i = 0; i < root.children.size(); i++) {
            Node cn = root.children.get(i);
            System.out.print(cn.data + ", ");
        }
        System.out.println(".");

        for(int i = 0; i < root.children.size(); i++) {
            Node cn = root.children.get(i);
            display(cn);
        }
    }

    public void display() {
        display(root);
    }

    public boolean isEmpty() {
        return this.root == null;
    }
}


public class gtreeOOPS {

    public static void main(String[] args) {
        int[] dlist = {
            10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1,
            120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1
        };


        GenericTree gt = new GenericTree(dlist);
        gt.display();
        boolean res = gt.isEmpty();
        System.out.println(res);
        System.out.println(gt.size());
        // Node root = build(dlist);
        // // display(root);
        // System.out.println(isEmpty(root));
    }
}