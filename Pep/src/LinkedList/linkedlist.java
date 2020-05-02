package LinkedList;
public class linkedlist {

    static class Node {
        int data;
        Node next;

        Node() {
            this.data = 0;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    static Node addLast(Node head, int data) {
        if(head == null) {
            Node nn = new Node(data, null);
            return nn;
        } else {
            Node temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            Node nn = new Node(data, null);
            temp.next = nn;
        }
        return head;
    }

    static void display(Node head) {
        while(head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    static int getSize(Node head) {
        int count = 0;
        while(head != null) {
            head = head.next;
            count++;
        }
        return count; 
    }

    static boolean isEmpty(Node head) {
        return head == null;
    }

    static Node addFirst(Node head, int data) {
        Node nn = new Node(data, head);
        return nn;
    }

    static Node getNodeAt(Node head, int idx) {
        if(head == null) {
            return null;
        }
        int j = 0;
        for(Node i = head; i != null; i = i.next) {
            if(j == idx) {
                return i;
            }
            j++; 
        }
        return null;
    }

    static Node addAt(Node head, int data, int idx) {
        int sz = getSize(head);
        if(idx < 0 || idx > sz) {
            System.out.println("Error : Invalid Index");
        } else if(idx == 0) {
            head = addFirst(head, data);
        } else if(idx == sz) {
            head = addLast(head, data);
        } else {
            Node nm1 = getNodeAt(head, idx - 1);
            Node n = new Node(data, nm1.next);
            n.next = nm1.next;
            nm1.next = n;
        }
        return head;
    }

    static int getFirst(Node head) {
        if(head == null){
            return 0;
        }
        return head.data;
    }
    
    static int getLast(Node head) {
        if(head == null){
            return 0;
        }
        while(head.next != null) {
            head = head.next;
        }
        return head.data;
    } 

    static int getAt(Node head, int idx) {
        if(head == null){
            return 0;
        }
        Node n = getNodeAt(head, idx);
        if(n == null) return 0;
        return n.data;
    }

    static Node removeFirst(Node head) {
    	if(head == null) {
    		return null;
    	}
    	Node temp1 = head;
    	Node temp2 = head.next;
    	temp1.next = null;
    	return temp2;
    }
    
    static Node removeLast(Node head) {
    	if(head == null) {
    		return null;
    	} else if (head.next == null) {
    		return null;
    	} else {
    		Node temp = head;
    		while(temp.next.next != null) {
    			temp = temp.next;
    		}
    		temp.next = null;
    		return head;
    	}
    }
    
    static Node removeAt(Node head, int idx) {
    	int sz = getSize(head);
    	if(idx < 0 || idx >= sz)
    		System.out.println("Error : Invalid Index");
    	else if (idx == 0)
    		head = removeFirst(head);
    	else if (idx == sz - 1)
    		head = removeLast(head);
    	else {
    		Node nm1 = getNodeAt(head, idx - 1);
    		Node n = nm1.next;
    		nm1.next = n.next;
    		n.next = null;
    	}
    	return head;    		
    }
    
    //LinkedList Questions
    static Node reverseDataIterative(Node head) {
    	int li = 0;
    	int ri = getSize(head) - 1;
    	while(li < ri) {
    		Node left = getNodeAt(head, li);
    		Node right = getNodeAt(head, ri);
    		
    		int temp = left.data;
    		left.data = right.data;
    		right.data = temp;
    		
    		li++;	ri--;
    	}
    	return head;
    }
    
    static Node reversePointerIterative(Node head) {
    	Node prev = null;
    	Node curr = head;
    	
    	while(curr != null) {
    		// 1. prepare next pointer
    		Node next = curr.next;
    		// 2. make connection of curr to previous
    		curr.next = prev;
    		// 3. prev point to curr
    		prev = curr;
    		// 4. curr point tp next
    		curr = next;
    	}
    	return prev;
    }
    
    static int kthFromLast(Node head, int k) {
    	Node slow = head;
    	Node fast = head;
    	for (int i = 0; i < k; i++) {
    		fast = fast.next;
    	}
    	while(fast != null) {
    		slow = slow.next;
    		fast = fast.next;
    	}
    	return slow.data;
    }
    
    static int getMid(Node head) {
    	if(head == null)
    		return 0;
    	
    	Node slow = head;
    	Node fast = head;
    	
    	while(fast.next != null && fast.next.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	return slow.data;
    }
    
    static void reverseDisplayRecursive(Node head) {
    	if(head == null) {
    		return;
    	}
    	reverseDisplayRecursive(head.next);
    	System.out.print(head.data + " -> ");
    }
    
    static Node hd = null;
    static void reversePointerRecursive(Node head) {
    	if(head == null)
    		return;
    	
    	Node first = head;
    	Node second = head.next;
    	if(second == null) {
    		hd = second;
    		return;
    	}
    	
    	reversePointerRecursive(second);
    	second.next = first;
    	
    }
    
    static Node left = null;
    static void reverseDataRecursively(Node right, int floor, int size) {
    	if(right == null)	return;
    	reverseDataRecursively(right.next, floor + 1, size);
    	
    	if(floor >= size / 2) {
    		int temp = left.data;
    		left.data = right.data;
    		right.data = temp;
    		
    		left = left.next;
    	}
    	
    }
    
    public static void main(String[] args) {
        Node head = null;
        head = addLast(head, 10);
        head = addLast(head, 20);
        head = addLast(head, 30);
        head = addLast(head, 40);
        head = addLast(head, 50);
//        head = addLast(head, 60);
//        head = addLast(head, 70);
        display(head);
        // head = addFirst(head, 1);
        // head = addFirst(head, 2);
        // System.out.println(getSize(head));
        // System.out.println(isEmpty(head));
        // head = addAt(head, 35, 3);
//        System.out.println(getFirst(head));
//        System.out.println(getLast(head));
//        System.out.println(getAt(head, -1));
//        head = removeLast(head);
//        head = removeAt(head, 0);
//        reverseDataIterative(head);
//        display(head);
//        head = reversePointerIterative(head);
//        display(head);
//        System.out.println(kthFromLast(head, 2));
//        System.out.println(getMid(head));
//        reverseDisplayRecursive(head);
//        reversePointerRecursive1(head, null);
        
//        reversePointerRecursive(head);
//        head.next = null;
//        head = hd;
//        display(head);
        
        left = head;
        int sz = getSize(head);
        reverseDataRecursively(head, 0, sz);
        display(head);
        
    }
}