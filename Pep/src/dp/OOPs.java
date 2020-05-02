package dp;

public class OOPs {
	//Overloading
	static int max(int a, int b) {
		if(a > b) return a;
		else return b;
	}
	static int max(int a, int b, int c) {
		if(a > b && a > c) return a;
		else if(b > c) return b;
		else return c;
	}
	
	static class Car{
		int price;
		String color;
		int ratings;
		
		Car(){
			price = 10000;
			color = "Black";
			ratings = 3;
		}
		
		Car(int pr, int rt){
			price = pr;
			ratings = rt;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a = max(15, 20);
//		int b = max(5, 10, 16);
//		System.out.println(a + " " + b);
		
		Car c1 = new Car();
		Car c2 = new Car();
		System.out.println(c1.price);
		System.out.println(c1.color);
		System.out.println(c1.ratings);
	}

}
