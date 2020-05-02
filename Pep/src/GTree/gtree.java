package GTree;
import java.util.*;

public class gtree {
    static class Node {
        int data;
        ArrayList<Node> children = null;

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    static Node build(int[] dlist) {
        Node root = null;
        ArrayList<Node> stack = new ArrayList<>();
        for (int i = 0; i < dlist.length; i++) {
            int data = dlist[i];
            if (data == -1) {
                // pop from top OR pop from end
                stack.remove(stack.size() - 1);
            } else {
                Node nn = new Node(data);
                if (root == null) {
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

    static void display(Node root) {
        if (root == null)
            return;

        System.out.print("[" + root.data + "]-> ");
        // print children of root
        for (int i = 0; i < root.children.size(); i++) {
            Node cn = root.children.get(i);
            System.out.print(cn.data + ", ");
        }
        System.out.println(".");

        for (int i = 0; i < root.children.size(); i++) {
            Node cn = root.children.get(i);
            display(cn);
        }
    }

    static boolean isEmpty(Node root) {
        return root == null;
    }

    static int getSize(Node root) {
        if (root == null)
            return 0;
        int size = 1;

        for (Node child : root.children) {
            size += getSize(child);
        }
        return size;
    }

    static int max(Node root) {
        if (root == null)
            return -1;
        int max = root.data;
        for (Node child : root.children) {
            int rmax = max(child);
            max = Math.max(rmax, max);
        }
        return max;
    }

    static int height(Node root) {
        if (root == null)
            return 0;
        int ht = 0;

        for (Node child : root.children) {
            int rht = height(child);
            ht = Math.max(ht, rht);
        }
        return ht + 1;
    }

    static boolean find(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;

        for (Node child : root.children) {
            boolean rres = find(child, data);
            if (rres) {
                return true;
            }
        }
        return false;
    }

    static void preOrder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + ", ");
        for (Node child : root.children) {
            preOrder(child);
        }
    }

    static void postOrder(Node root) {
        if (root == null)
            return;
        for (Node child : root.children) {
            postOrder(child);
        }
        System.out.print(root.data + ", ");
    }

    static void levelOrder(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);

        System.out.print("LevelOrder => ");
        while (queue.size() > 0) {
            // 1. get + remove
            Node rem = queue.removeFirst();
            // 2. Print
            System.out.print(rem.data + ", ");
            // 3. Add children in queue
            for (Node child : rem.children) {
                queue.addLast(child);
            }
        }
        System.out.println();
    }

    static class Pair {
        Node n;
        int l;

        Pair(Node n, int l) {
            this.n = n;
            this.l = l;
        }
    }

    static void levelOrderLinewise1(Node root) {
        LinkedList<Pair> queue = new LinkedList<>();
        queue.addLast(new Pair(root, 0));
        int prev = 0;
        System.out.println("LevelOrder Linewise => ");
        while (queue.size() > 0) {
            Pair rem = queue.removeFirst();
            if (rem.l > prev) {
                System.out.println();
                prev++;
            }
            System.out.print(rem.n.data + " ");
            for (Node child : rem.n.children) {
                queue.addLast(new Pair(child, rem.l + 1));
            }
        }
        System.out.println();
    }

    static void levelOrderLinewise2(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        queue.addLast(null);
        System.out.println("Level Order Linewise Aprroach 2 :->");
        while (queue.size() > 0) {
            Node rem = queue.removeFirst();
            if (rem == null) {
                System.out.println();
                if (queue.size() > 0) {
                    queue.addLast(null);
                }
            } else {
                System.out.print(rem.data + " ");
                for (Node child : rem.children) {
                    queue.addLast(child);
                }
            }
        }
    }

    static void levelOrderZigZag(Node root) {
        ArrayList<Node> stack = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();

        queue.addLast(root);
        boolean flag = false;

        System.out.println("Level Order in Zig Zag manners : ->");
        while (queue.size() > 0) {
            Node rem = queue.removeFirst();
            System.out.print(rem.data + " ");
            if (flag == true) {
                // left to right data add in stack
                for (int i = 0; i < rem.children.size(); i++) {
                    stack.add(rem.children.get(i));
                }
            } else {
                // right to left data add in stack
                for (int i = rem.children.size() - 1; i >= 0; i--) {
                    stack.add(rem.children.get(i));
                }
            }
            if (queue.size() == 0) {
                // change flag
                flag = !flag;
                // apply an 'enter'
                System.out.println();
                // transfer data from stack to queue
                while (stack.size() > 0) {
                    queue.addLast(stack.remove(stack.size() - 1));
                }
            }
        }
    }

    static void mirror(Node root) {
        // faith
        for (Node child : root.children) {
            mirror(child);
        }

        // merging faith + expectation
        Collections.reverse(root.children);
    }

    static void removeLeaves(Node root) {
        for (int i = root.children.size() - 1; i >= 0; i--) {
            Node child = root.children.get(i);
            if (child.children.size() == 0) {
                root.children.remove(child);
            } else {
                removeLeaves(child);
            }
        }
    }

    static boolean isIsomorphic(Node root1, Node root2) {
        if (root1.children.size() != root2.children.size()) {
            return false;
        } else {
            // that means both the root have same number of children
            for (int i = 0; i < root1.children.size(); i++) {
                Node child1 = root1.children.get(i);
                Node child2 = root2.children.get(i);

                boolean rres = isIsomorphic(child1, child2);
                if (rres == false) {
                    return false;
                }
            }
            return true;
        }
    }

    static boolean isMirror(Node root1, Node root2) {
        if (root1.children.size() != root2.children.size()) {
            return false;
        } else {
            for (int i = 0; i < root1.children.size(); i++) {
                int left = i;
                int right = root1.children.size() - 1 - i;

                Node lc = root1.children.get(left);
                Node rc = root2.children.get(right);

                boolean rres = isMirror(lc, rc);
                if (rres == false)
                    return false;

            }
            return true;
        }
    }

    static boolean isSymetric(Node root) {
        return isMirror(root, root);
    }

    public static void main(String[] args) {
        int[] dlist1 = { 1, 2, 5, -1, -1, 3, -1, 4, 7, -1, 8, -1, -1, -1 };
        Node root1 = build(dlist1);

        int[] dlist2 = { 1, 20, 5, -1, 60, -1, -1, 30, -1, 4, 70, -1, 8, -1, -1, -1 };
        Node root2 = build(dlist2);

        // display(root1);
        // System.out.println();
        // display(root2);
        // System.out.println("Isomorphic : " + isIsomorphic(root1, root2));
        // System.out.println("Mirror : " + isMirror(root1, root2));
        // System.out.println("Symetric : " + isSymetric(root1));
        // System.out.println("Symetric : " + isSymetric(root2));

        // int[] dlist = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1,
        // -1, 90, -1, -1, 40, 100, -1, -1,
        // -1 };
        // Node root = build(dlist);
        // display(root);
        // mirror(root);
        // removeLeaves(root);
        // display(root);

        // System.out.println(isEmpty(root));
        // System.out.println("Size = " + getSize(root));
        // System.out.println("Max = " + max(root));
        // System.out.println("Height = " + height(root));
        // System.out.println("find = " + find(root, 85));

        // System.out.print("PreOrder => ");
        // preOrder(root);
        // System.out.println();

        // System.out.print("PostOrder => ");
        // postOrder(root);
        // System.out.println();

        // levelOrder(root);
        // levelOrderLinewise1(root);
        // levelOrderLinewise2(root);
        // levelOrderZigZag(root);
    }
}