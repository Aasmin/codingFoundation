package Stack;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Stack<Character> st = new Stack<Character>();
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == '(' || ch == '{' || ch == '[') {
			    st.push(ch);
			} else if (ch == ')') {
			    if(st.peek() != '(' || st.size() == 0){
			        System.out.println(false);
			        return;
			    }
			} else if (ch == '}') {
			    if(st.peek() != '{' || st.size() == 0){
			        System.out.println(false);
			        return;
			    }
			} else if (ch == ']') {
			    if(st.peek() != '[' || st.size() == 0){
			        System.out.println(false);
			        return;
			    }
			} else {
			    
			}
        }
        if(st.size() == 0)
             System.out.println(true);
        else 
            System.out.println(false);
    }
}




// Duplicate Brackets

//import java.util.Scanner;
//import java.util.Stack;
//
//public class Main {
//	public static void main(String[] args) throws Exception {
//        Stack<Character> st = new Stack<Character>();
//		Scanner scn = new Scanner(System.in);
//		String str = scn.next();
//		
//		for (int i = 0; i < str.length(); i++) {
//			char ch = str.charAt(i);
//			if(ch == '(' || ch == '{'||  ch == '[') {
//			    st.push(ch);
//			} else if (ch == ')') {
//			    if(st.peek() != '('){
//			        System.out.println(false);
//			        return;
//			    }
//			} else if (ch == '}') {
//			    if(st.peek() != '{'){
//			        System.out.println(false);
//			        return;
//			    }
//			} else if (ch == ']') {
//			    if(st.peek() != '['){
//			        System.out.println(false);
//			        return;
//			    }
//			} else {
//			    
//			}
//        }
//    System.out.println(true);
//}}    
