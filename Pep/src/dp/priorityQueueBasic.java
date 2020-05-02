package dp;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class priorityQueueBasic {

	static class Student implements Comparable<Student>{
		int roll;
		String name;
		double ht;
		//constructor
		Student(int roll, String name, double ht){
			this.roll = roll;
			this.name = name;
			this.ht = ht;
		}
		//helps in comparison
		@Override
		public int compareTo(Student o) {
			// TODO Auto-generated method stub
//			return (int) (this.ht - o.ht);
			double h1 = this.ht;
			double h2 = o.ht;
			if(h1 == h2)	return 0;
			else if (h1 > h2) return 1;
			else return -1;
		}
	}
	
	public static void main(String[] args) {
		Student s1 = new Student(1, "Prakhar", 5.6);
		Student s2 = new Student(2, "Aasmin", 6.2);
		Student s3 = new Student(3, "Jatin", 5.7);
//		Student s4 = new Student(4, "A", 7);
//		Student s5 = new Student(5, "b", 7.5);
		
//		Student[] sarr = new Student[3];
//		sarr[0] = s1;
//		sarr[1] = s2;
//		sarr[2] = s3;
		
		PriorityQueue<Student> pq = new PriorityQueue<Student>(Collections.reverseOrder());
		pq.add(s1);
		pq.add(s2);
		pq.add(s3);
//		pq.add(s4);
//		pq.add(s5);
		
		while (pq.size() != 0) {
			Student s = pq.peek();
			pq.remove();
			System.out.println(s.roll + "\t" + s.name + "\t" + s.ht);
		}
	}
}
