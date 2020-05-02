package Practice;

import java.io.*;
import java.util.*;

public class Practice{
  

public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();

    // code
    Stack<String> pre = new Stack<>();
    Stack<String> post = new Stack<>();
    Stack<Character> ops = new Stack<>();
    
    
    for(int i = 0; i < exp.length(); i++){
        char ch = exp.charAt(i);
        if(ch == '('){
            ops.push(ch);
        } else if((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
            pre.push(ch + "");
            post.push(ch + "");
        } else if(ch == ')') {
            while('(' != ops.peek()){
                char op = ops.pop();
                
                String v2 = post.pop(); 
                String v1 = post.pop();
                String v = v1 + v2 + op;
                post.push(v);
                
                v2 = pre.pop(); 
                v1 = pre.pop();
                v = op + v1 + v2;
                pre.push(v);
            }
            ops.pop();
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/'){
            while('(' != ops.peek() && precedence(ch) < precedence(ops.peek())
                        && ops.size() > 0){
                char op = ops.pop();
                
                String v2 = post.pop(); 
                String v1 = post.pop();
                String v = v1 + v2 + op;
                post.push(v);
                
                v2 = pre.pop(); 
                v1 = pre.pop();
                v = op + v1 + v2;
                pre.push(v);
            }
            ops.push(ch);
        }
    }
    
    while(ops.size() > 0){
        char op = ops.pop();
        
        String v2 = post.pop(); 
        String v1 = post.pop();
        String v = v1 + v2 + op;
        post.push(v);
        
        v2 = pre.pop(); 
        v1 = pre.pop();
        v = op + v1 + v2;
        pre.push(v);
    }
    System.out.println(pre.pop());
    System.out.println(post.pop());
 }
 
 public static int precedence(char op){
     if(op == '+' || op == '-')
        return 1;
     else if(op == '*' || op == '/')
        return 2;
     else
        return 0;
 }
}