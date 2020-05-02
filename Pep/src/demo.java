import java.util.*;
public class demo {

    static ArrayList<String> gss(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        
        // if(str.length() == 1) {
        //     ArrayList<String> bres = new ArrayList<>();
        //     bres.add("");
        //     bres.add(str);
        //     return bres;
        // }

        char ch = str.charAt(0); // a for first level
        ArrayList<String> rres = gss(str.substring(1)); // faith
        
        ArrayList<String> mres = new ArrayList<>();
        for(int i = 0; i < rres.size(); i++) {
            mres.add("-" + rres.get(i));
            mres.add(ch + rres.get(i));
        }

        return mres;
    }

    static void printSS(String ques, int i, String ans) {

        if(i == ques.length()) {
            System.out.print(ans + ", ");
            return;
        }

        char ch = ques.charAt(i);
        
        // yes call
        printSS(ques, i + 1, ans + ch);
        // no call
        printSS(ques, i + 1, ans + "-");
    }

    static ArrayList<String> gssASCII(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        ArrayList<String> rres = gssASCII(str.substring(1));
        ArrayList<String> mres = new ArrayList<>();
        for(int i = 0; i < rres.size(); i++) {
            mres.add("-" + rres.get(i));
            mres.add(ch + rres.get(i));
            mres.add((int)ch + rres.get(i));
        }
        return mres;
    }

    static String[] codes = {".:;", "abc", "def", "ghi", "jkl",
                    "mno", "pqrs", "tuv", "wx", "yz"};
    
    static ArrayList<String> gkpc(String str) {
        if(str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);
        int num = ch - '0';     
        ArrayList<String> rres = gkpc(str.substring(1));
        ArrayList<String> mres = new ArrayList<>();
        String opt = codes[num]; // ghi
        for(int i = 0; i < opt.length(); i++) { // g in first iteration
            for(int j = 0; j < rres.size(); j++) {
                mres.add(opt.charAt(i) + rres.get(j));
            }
        }
        return mres;
    }

    static ArrayList<String> getStair(int n, int k) {
        if(n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't Move
            return bres;
        }

        ArrayList<String> mres = new ArrayList<>();

        for(int i = 1; i <= k; i++) { // loop if possible moves
            if(n - i >= 0) {
                ArrayList<String> rres = getStair(n - i, k);
                for(int j = 0; j < rres.size(); j++) {
                    mres.add(i + rres.get(j));
                }
            }
        }
        return mres;
    }

    static ArrayList<String> getMazePath(int cr, int cc, int dr, int dc) {
        if(cc == dc && cr == dr) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't Move
            return bres;
        }
        
        ArrayList<String> mres = new ArrayList<>();
        //Intermediate - I == Horizontal move
        if(cc + 1 <= dc) {
            ArrayList<String> rres = getMazePath(cr, cc + 1, dr, dc);
            for(String rstr : rres) {
                mres.add("H" + rstr);
            }
        } 
        //Intermediate - II == vertical move
        if(cr + 1 <= dr) {
            ArrayList<String> rres = getMazePath(cr + 1, cc, dr, dc);
            for(String rstr : rres) {
                mres.add("V" + rstr);
            }
        }
        return mres;
    }
    //not complete
    static ArrayList<String> getMazePath2(int cr, int cc, int dr, int dc) {
        if(cc == dc && cr == dr) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add(""); // don't Move
            return bres;
        }
        
        ArrayList<String> mres = new ArrayList<>();
        //Intermediate - I == Horizontal move
        for (int i = 1; i <= dc - cc; i++) {
        	 ArrayList<String> rres = getMazePath(cr, cc + i, dr, dc);
             for(String rstr : rres) {
                 mres.add("H" + i + rstr);
             }
		}
        //Intermediate - II == vertical move
        for(int j = 1; j <= dr - cr ; j++) {
            ArrayList<String> rres  = getMazePath(cr + j, cc, dr, dc);
            for(String rstr : rres) {
                mres.add("V" + j + rstr );
            }
        }
        // Diagonal
        for(int i = 1; i <= dc - cc && i <= dr - cr ; i++) { 
            ArrayList<String> rres  = getMazePath(cr + i, cc + i, dr, dc);
            for(String rstr : rres) {
                mres.add("D" + i + rstr);
            }
        }
        return mres;
    }

    static void printKPC(String que, String ans) {
        
    }

    static void printStaircase(int n, int k, String ans) {
    	if(n == 0) {
    		System.out.print(ans + " ");
    	}
    	for (int i = 1; i <= k; i++) {
			if(n - i >= 0) { 
				printStaircase(n - i, k, ans + i);
			}
		}
    }
    
    static void printASCII(String str, String ans) {
    	if(str.length() == 0) {
    		System.out.print(ans + " ");
    		return;
    	}
    	
    	char ch = str.charAt(0);
    	printASCII(str.substring(1), "_" + ans);
    	printASCII(str.substring(1), ch + ans);
    	printASCII(str.substring(1), (int)ch + ans);
    }
    
    static void printMazepath1(int cr, int cc, int dr, int dc, String ans) {
    	if(cc == dc && cr == dr) {
    		System.out.print(ans + " ");
    		return;
    	}
    	if(cc + 1 <= dc) {
    		printMazepath1(cr, cc + 1, dr, dc, "H" + ans);
    	}
    	if(cr + 1 <= dr) {
    		printMazepath1(cr + 1, cc, dr, dc, "V" + ans);
    	}
    }

    static void printMazepath2(int cr, int cc, int dr, int dc, String ans) {
    	if(cc == dc && cr == dr) {
    		System.out.print(ans + " ");
    		return;
    	}
    	for (int i = 1; i <= dc - cc; i++) {
       	 printMazepath2(cr, cc + i, dr, dc, "H" + i + ans);
		}
       //Intermediate - II == vertical move
       for(int i = 1; i <= dr - cr ; i++) {
    	   printMazepath2(cr + i, cc, dr, dc, "V" + i + ans);
       }
       // Diagonal
       for(int i = 1; i <= dc - cc && i <= dr - cr ; i++) { 
    	   printMazepath2(cr + i, cc + i, dr, dc, "D" + i + ans);
           }
    }
    public static void main(String[] args) {
        //ArrayList<String> res = getMazePath2(0, 0, 2, 2);
         ArrayList<String> res = getStair(3, 3);
        // ArrayList<String> res = gkpc("389");
        // ArrayList<String> res = gkpc("389");
        // ArrayList<String> res = gssASCII("ab");
       // System.out.println(res);
    	//printStaircase(4, 3, "");
    	//printASCII("ab", "");
//    	printMazepath2(0, 0, 2, 2, "");
//         ArrayList<String> res = gss("abc");
         System.out.println(res);
        // System.out.print("[");
        // printSS("abc", 0, "");
        // System.out.println("]");
    }
}