package LinkedListOOPS;

class LinkedList {
    private class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size = 0;
    private Node head = null;
    private Node tail = null;

    private void handleWhenSize0(int data) {
        Node nn = new Node(data); // space;
        head = nn;
        tail = nn;
        size = 1;
    }

    private Node getNodeAt(int indx) {
        Node temp = head;
        for(int i = 0; i < size; i++) {
            if(i == indx) return temp;
            temp = temp.next;
        }
        return null;
    }

    private int handleRemWhenSize1() {
        int temp = head.data;
        head = tail = null;
        size = 0;
        return temp;
    }

    private Node midNode(Node first, Node last) {
        Node slow = first;
        Node fast = first;

        while(fast != last && fast.next != last) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void addLast(int data) {
        if(size == 0) {
            handleWhenSize0(data);
        } else {
            Node nn = new Node(data);
            tail.next = nn;
            tail = nn;
            size++;
        }
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + "-->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void addFirst(int data) {
        if(size == 0) {
            handleWhenSize0(data);
        } else {
            Node nn = new Node(data, head);
            head = nn;
            size++;
        }
    }

    public void addAt(int data, int indx) {
        if(indx < 0 || indx > size) {
            // invalid index
            System.out.println("Error : invalid index");
        } else if(indx == 0){
            // addFirst
            addFirst(data);
        } else if(indx == size) {
            // addLast
            addLast(data);
        } else {
            // particular valid index
            Node nm1 = getNodeAt(indx - 1);
            Node n = new Node(data, nm1.next);
            nm1.next = n;
            size++;
        }
    }

    public int getFirst() {
        if(size == 0) {
            return -1;
        } else {
            return head.data;
        }
    }

    public int getLast() {
        if(size == 0) {
            return -1;
        } else {
            return tail.data;
        }
    }

    public int getAt(int indx) {
        if(indx < 0 || indx >= size) {
            return -1;
        } else {
            Node temp = getNodeAt(indx);
            return temp.data;
        }
    }

    public int removeFirst() {
        if(size == 0) {
            return -1;
        } else if(size == 1) {
            return handleRemWhenSize1();
        } else {
            Node temp = head;
            head = head.next;
            temp.next = null;
            size--;
            return temp.data;
        }
    }

    public int removeLast() {
        if(size == 0) {
            return -1;
        } else if(size == 1) {
            return handleRemWhenSize1();
        } else {
            Node sl = getNodeAt(size - 2);
            int temp = tail.data;
            sl.next = null;
            size--;
            tail = sl;
            return temp;
        }
    }

    public int removeAt(int indx) {
        if(indx < 0 || indx >= size) {
            return -1;
        } else if(indx == 0) {
            return removeFirst();
        } else if(indx == size - 1) {
            return removeLast();
        } else {
            Node nm1 = getNodeAt(indx - 1);
            Node n = nm1.next;
            nm1.next = n.next;
            n.next = null;
            size--;
            return n.data;
        }
    }

    private void mergeTwoSortedList(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;

        while(temp1 != null && temp2 != null) {
            if(temp1.data < temp2.data) {
                this.addLast(temp1.data);
                temp1 = temp1.next;
            } else {
                this.addLast(temp2.data);
                temp2 = temp2.next;
            }
        }

        if(temp1 == null) {
            // temp2 is remain
            while(temp2 != null) {
                this.addLast(temp2.data);
                temp2 = temp2.next;
            }
        }

        if(temp2 == null) {
            // may be temp1 is remain
            while(temp1 != null) {
                this.addLast(temp1.data);
                temp1 = temp1.next;
            }
        }
    }


    private LinkedList mergeSort(Node first, Node last) {
        if(first == last) {
            LinkedList blist = new LinkedList();
            blist.addLast(first.data);
            return blist;
        }
        Node mid = midNode(first, last);

        LinkedList fhalf = mergeSort(first, mid);
        LinkedList shalf = mergeSort(mid.next, last);

        LinkedList sorted = new LinkedList();
        sorted.mergeTwoSortedList(fhalf.head, shalf.head);

        return sorted;
    }
	
	public LinkedList mergeTwoSortedList(LinkedList l1, LinkedList l2) {
		LinkedList ll = new LinkedList();
		ll.mergeTwoSortedList(l1.head, l2.head);
		
		return ll;
	}
    public LinkedList mergeSort() {
        LinkedList res = mergeSort(this.head, this.tail);
        return res;
    }

}

public class Client {

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.addLast(10);
        ll.addLast(80);
        ll.addLast(20);
        ll.addLast(50);
        ll.addLast(70);
        ll.addLast(20);
        ll.addLast(30);
        ll.addLast(100);
        ll.addLast(90);
        
        ll.display();

        LinkedList sl = ll.mergeSort();
        sl.display();

        // System.out.println(ll.size() + " " + ll.isEmpty());


        // ll.addLast(10);
        // ll.addLast(20);
        // ll.addLast(30);
        // ll.addLast(40);
        // ll.addLast(50);
        // ll.addLast(60);

        // // ll.display();
        // // ll.addAt(35, 3);
        // // ll.removeFirst();
        // ll.removeAt(2);
        // // ll.display();
        // ll.removeAt(10);
        // // ll.removeLast();
        // // ll.display();

        // LinkedList l1 = new LinkedList();
        // l1.addLast(10);
        // l1.addLast(30);
        // l1.addLast(60);
        // l1.addLast(90);
        // l1.addLast(95);
        // l1.display();

        // LinkedList l2 = new LinkedList();
        // l2.addLast(10);
        // l2.addLast(25);
        // l2.addLast(40);
        // l2.addLast(50);
        // l2.addLast(60);
        // l2.addLast(80);
        // l2.addLast(100);
        // l2.addLast(110);
        // l2.display();

        // LinkedList res = l1.mergeTwoSortedList(l2);
        // res.display();

        // System.out.println(ll.size() + " " + ll.isEmpty());

    }
}