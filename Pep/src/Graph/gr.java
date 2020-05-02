package Graph;
import java.util.*;


public class gr {
	static class Edge {
		int nbr;
		int wt;
		
		Edge(int nbr, int wt) {
			this.nbr = nbr;
			this.wt = wt;
		}
	}
	
	static void  addEdge(ArrayList<ArrayList<Edge>> graph, int v1, int v2, int wt) {
		graph.get(v1).add(new Edge(v2, wt));
		graph.get(v2).add(new Edge(v1, wt));
	}
	
	static void display(ArrayList<ArrayList<Edge>> graph) {
		System.out.println("-----GRAPH-----");
		for(int v = 0; v < graph.size(); v++) {
			System.out.print(v + " -> ");
			for(Edge e : graph.get(v)) {
				System.out.print(e.nbr + "-" + e.wt + ", ");
			}
			System.out.println();
		}
		
		System.out.println("---------------");
	}
	
	static boolean hasPath(ArrayList<ArrayList<Edge>> graph, int src, int dst, boolean[] vis) {
		vis[src] = true;
		if(src == dst) return true;
		for(Edge e : graph.get(src)) {
			int n = e.nbr;
			if(vis[n] == false) {
				boolean rres = hasPath(graph, n, dst, vis);
				if(rres == true) return true;
			}
		}
		return false;
	}
	
	// maarking is based on current vertices
	static void printAllPath1(ArrayList<ArrayList<Edge>> graph, int src, int dst,
			boolean[] vis, String psf, int wsf) {
		// printing
		if(src == dst) {
			System.out.println(psf + dst + " @ " + wsf);
			return;
		}
		// marking
		vis[src] = true;
		// make a loop and inside loop make a call
		for(Edge e : graph.get(src)) {
			int nbr = e.nbr;
			int wt = e.wt;
			if(vis[nbr] == false) {
				printAllPath1(graph, nbr, dst, vis, psf + src + "->", wsf + wt);
			}
		}
		// unmark
		vis[src] = false;
	}
	
	// marking is based on neighbours
	static void printAllPath2(ArrayList<ArrayList<Edge>> graph, int src, int dst,
			boolean[] vis, String psf, int wsf) {
		// printing
		if(src == dst) {
			System.out.println(psf + dst + " @ " + wsf);
			return;
		}
		// make a loop and inside loop make a call
		for(Edge e : graph.get(src)) {
			int nbr = e.nbr;
			int wt = e.wt;
			if(vis[nbr] == false) {
				vis[nbr] = true;
				printAllPath2(graph, nbr, dst, vis, psf + src + "->", wsf + wt);
				vis[nbr] = false;
			}
		}
	}
	
	static class Mhelper {
		int minCost = Integer.MAX_VALUE;
		String minPath = "";
		int maxCost = Integer.MIN_VALUE;
		String maxPath = "";
	}
	
	static void min_maxPath(ArrayList<ArrayList<Edge>> graph, int src, int dst, String psf,
			int wsf, boolean[] vis, Mhelper res) {
		// printing
				if(src == dst) {
					String path = psf + dst;
					// min Set in res
					if(res.minCost > wsf) {
						res.minCost = wsf;
						res.minPath = path;
					}
					// max set in res
					if(res.maxCost < wsf) {
						res.maxPath = path;
						res.maxCost = wsf;
					}
					return;
				}
				// marking
				vis[src] = true;
				// make a loop and inside loop make a call
				for(Edge e : graph.get(src)) {
					int nbr = e.nbr;
					int wt = e.wt;
					if(vis[nbr] == false) {
						min_maxPath(graph, nbr, dst, psf + src + "->", wsf + wt, vis, res);
					}
				}
				// unmark
				vis[src] = false;
	}
	
	static class CFhelper {
		// qualified min
		int ceil = Integer.MAX_VALUE;
		String cpath = "";
		// qualified max
		int floor = Integer.MIN_VALUE;
		String fpath = "";
	}
	
	static void cfPath(ArrayList<ArrayList<Edge>> graph, int src, int dst, String psf,
			int wsf, boolean[] vis, CFhelper res, int fact) {
		// printing
				if(src == dst) {
					String path = psf + dst;
					// ceil
					if(wsf > fact && wsf < res.ceil) {
							res.ceil = wsf;
							res.cpath = path;
					}
					
					// floor
					if(wsf < fact) {
						if(wsf > res.floor) {
							res.floor = wsf;
							res.fpath = path;
						}
					}
					return;
				}
				// marking
				vis[src] = true;
				// make a loop and inside loop make a call
				for(Edge e : graph.get(src)) {
					int nbr = e.nbr;
					int wt = e.wt;
					if(vis[nbr] == false) {
						cfPath(graph, nbr, dst, psf + src + "->", wsf + wt, vis, res, fact);
					}
				}
				// unmark
				vis[src] = false;
	}
	
	static class kPair implements Comparable<kPair>{
		int wt;
		String path = "";
		
		kPair(int wt, String path) {
			this.wt = wt;
			this.path = path;
		}
		
		public int compareTo(kPair o) {
			return this.wt - o.wt;
		}
	}
	
	static void kthLargest1(ArrayList<ArrayList<Edge>> graph,int src, int dst, String psf,
			int wsf, boolean[] vis, PriorityQueue<kPair> pq) {
		// printing
		if(src == dst) {
			pq.add(new kPair(wsf, psf + dst));
			return;
		}
		// marking
		vis[src] = true;
		// make a loop and inside loop make a call
		for(Edge e : graph.get(src)) {
			int nbr = e.nbr;
			int wt = e.wt;
			if(vis[nbr] == false) {
				kthLargest1(graph, nbr, dst, psf + src + "->", wsf + wt, vis, pq);
			}
		}
		// unmark
		vis[src] = false;
	}
	
	
	
	static void kthLargest(ArrayList<ArrayList<Edge>> graph,int src, int dst, String psf,
			int wsf, boolean[] vis, int k) {
		
		
//		~~~~~~~~APPROACH - I~~~~~~~
//		PriorityQueue<kPair> pq = new PriorityQueue<>(Collections.reverseOrder());
//		kthLargest1(graph, src, dst, psf, wsf, vis, pq);
//		
//		kPair rem = null;
//		while(k != 0) {
//			rem = pq.remove();
//			k--;
//		}
//		System.out.println(rem.path + " @  " + rem.wt);
		
		
//		~~~~~~~~APPROACH - II~~~~~~~~~~~~~~~
		int fact = Integer.MAX_VALUE;
		
		int floor = 0;
		String path = "";
		for(int i = 0; i < k; i++) {
			CFhelper fpair = new CFhelper();
			cfPath(graph, src, dst, "", 0, vis, fpair, fact);
			floor = fpair.floor;
			path = fpair.fpath;
			fact = floor;
		}
		
		System.out.println(path + " @ " + floor);
	}
	
	static boolean dfsi(ArrayList<ArrayList<Edge>> graph, int src, int dst) {
		// stack
		ArrayList<Integer> stack = new ArrayList<Integer>();
		stack.add(src);
		// visited
		boolean[] vis = new boolean[graph.size()];
		vis[src] = true;
		
		while(stack.size() > 0) {
			// get + rem
			int rem = stack.remove(stack.size() - 1);
			// mark
			vis[rem] = true;
			// work - check for dest;
			if(rem == dst) {
				return true;
			}
			// add neighbours
			for(Edge e : graph.get(rem)) {
				int nbr = e.nbr;
				if(vis[nbr] == false) {
					stack.add(nbr);
				}
			}
		}
		
		return false;
	}
	
	static boolean bfs(ArrayList<ArrayList<Edge>> graph, int src, int dst) {
		// queue
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.addLast(src);
		// visited
		boolean[] vis = new boolean[graph.size()];
		
		while(queue.size() > 0) {
			// get + remove
			int rem = queue.removeFirst();
			// mark*
						
			if(vis[rem] == true) continue;
			vis[rem] = true;
			
			// work
			if(rem == dst) return true;
			
			// add nbrs
			for(Edge e : graph.get(rem)) {
				int nbr = e.nbr;
				if(vis[nbr] == false) {
					queue.addLast(nbr);
				}
			}
		}
		return false;
	}
	
	static class bfsHelper {
		int cost;
		String psf;
		int vtx;
		
		bfsHelper(int vtx, String psf, int cost) {
			this.vtx = vtx;
			this.cost = cost;
			this.psf = psf;
		}
	}
	
	static void bfsPath(ArrayList<ArrayList<Edge>> graph, int src) {
		LinkedList<bfsHelper> queue = new LinkedList<>();
		queue.addLast(new bfsHelper(src, "" + src, 0));
		// visited
		boolean[] vis = new boolean[graph.size()];
		
		while(queue.size() > 0) {
			// get + remove
			bfsHelper rem = queue.removeFirst();
			// mark*
						
			if(vis[rem.vtx] == true) continue;
			vis[rem.vtx] = true;
			
			// work - print
			System.out.println(rem.vtx + " " + rem.psf + " @ " + rem.cost);
			
			// add nbrs
			for(Edge e : graph.get(rem.vtx)) {
				int nbr = e.nbr;
				if(vis[nbr] == false) {
					queue.addLast(new bfsHelper(nbr,rem.psf + nbr, rem.cost + e.wt));
				}
			}
		}
	}
	
	static class fireHelper {
		int x;
		int y;
		int t;
		
		fireHelper(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	
	static boolean isValidFire(int[][] city, int x, int y) {
		if(x < 0 || x >= city.length || y < 0 || y >= city[0].length) {
			return false;
		} else if(city[x][y] > 0 || city[x][y] == -1) {
			return false;
		}
		return true;
	}
	
	static void fire_in_city(int[][] city) {
		LinkedList<fireHelper> queue = new LinkedList<>();
		for(int i = 0; i < city.length; i++) {
			for(int j = 0; j < city[0].length; j++) {
				if(city[i][j] == 0) {
					// fire
					queue.addLast(new fireHelper(i, j, 0));
				}
			}
		}
		
		while(queue.size() > 0) {
			fireHelper rem = queue.removeFirst();
			int x = rem.x;
			int y = rem.y;
			int t = rem.t;
			
			if(city[x][y] > 0) {
				// already burnt previously
				continue;
			}
			city[x][y] = t;
			
			System.out.println("(" + x + "," + y + ") burn at time T = " + t);
			
			//top
			if(isValidFire(city, x - 1, y) == true) {
				queue.addLast(new fireHelper(x - 1, y, t + 1));
			}
			// down
			if(isValidFire(city, x + 1, y) == true) {
				queue.addLast(new fireHelper(x + 1, y, t + 1));
			}
			//left
			if(isValidFire(city, x, y - 1) == true) {
				queue.addLast(new fireHelper(x, y - 1, t + 1));
			}
			//right
			if(isValidFire(city, x, y + 1) == true) {
				queue.addLast(new fireHelper(x, y + 1, t + 1));
			}
		}
	}
	
	static String getComponent(ArrayList<ArrayList<Edge>> graph,
			int src, boolean[] vis) {
		String comp = "";
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		while(queue.size() > 0) {
			int rem = queue.removeFirst();
			if(vis[rem] == true) continue;
			vis[rem] = true;
			comp += rem;
			for(Edge e : graph.get(rem)) {
				int nbr = e.nbr;
				queue.addLast(nbr);
			}
		}
		return comp;
	}
	
	
	static void gcc(ArrayList<ArrayList<Edge>> graph) {
		boolean[] vis = new boolean[graph.size()];
		ArrayList<String> comps = new ArrayList<String>();
		
		for(int v = 0; v < graph.size(); v++) {
			if(vis[v] == false) {
				String comp = getComponent(graph, v, vis);
				comps.add(comp);
			}
		}
		System.out.println(comps);
	}
	
	static boolean isConnected(ArrayList<ArrayList<Edge>> graph) {
		boolean[] vis = new boolean[graph.size()];
		int count = 0;
		
		for(int v = 0; v < graph.size(); v++) {
			if(vis[v] == false) {
				String comp = getComponent(graph, v, vis);
				count++;
			}
		}
		return count == 1;
	}
	
	static class pair {
        int x;
        int y;
        
        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static boolean isValid_IsLand(char[][] graph, int x, int y) {
        if(x < 0 || x >= graph.length || y < 0 || y >= graph[0].length) {
            return false;
        } else if(graph[x][y] == '0') {
            return false;
        }
        return true;
    }
    
    
    static void getComp(char[][] graph, int i, int j) {
        LinkedList<pair> queue = new LinkedList<>();
        
        queue.addLast(new pair(i, j));
        
        while(queue.size() > 0) {
            pair rem = queue.removeFirst();
            int x = rem.x;
            int y = rem.y;
            
            if(graph[x][y] == '0') continue;
            graph[x][y] = '0';
            
            // top
            if(isValid_IsLand(graph, x - 1, y) == true) {
                queue.addLast(new pair(x - 1, y));
            }
            // down
            if(isValid_IsLand(graph, x + 1, y) == true) {
                queue.addLast(new pair(x + 1, y));
            }
            // left
            if(isValid_IsLand(graph, x, y - 1) == true) {
                queue.addLast(new pair(x, y - 1));
            }
            // right
            if(isValid_IsLand(graph, x, y + 1) == true) {
                queue.addLast(new pair(x, y + 1));
            }
        }
        
    }
    
    
    static int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    getComp(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
	
    static boolean isCyclicComp(ArrayList<ArrayList<Edge>> graph,
    		int src, boolean[] vis) {
    	LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		while(queue.size() > 0) {
			int rem = queue.removeFirst();
			if(vis[rem] == true) return true;
			vis[rem] = true;
			for(Edge e : graph.get(rem)) {
				int nbr = e.nbr;
				if(vis[nbr] == false)
					queue.addLast(nbr);
			}
		}
		return false;
    }
    
    static boolean isCyclic(ArrayList<ArrayList<Edge>> graph) {
    	boolean[] vis = new boolean[graph.size()];
    	
    	for(int v = 0; v < graph.size(); v++) {
    		if(vis[v] == false) {
    			boolean res = isCyclicComp(graph, v, vis);
    			if(res == true) return true;
    		}
    	}
    	return false;
    }
    
    // Date : 23 April 2020
    
    static class Bhelper {
    	int vtx;
    	int t;
    	
    	Bhelper(int vtx, int t) {
    		this.vtx = vtx;
    		this.t = t;
    	}
    }
    
    static boolean isBipertiteComp(ArrayList<ArrayList<Edge>> graph, 
    		int src, int[] vis) {
    	LinkedList<Bhelper> queue = new LinkedList<>();
    	queue.addLast(new Bhelper(src, 0));
    	
    	while(queue.size() > 0) {
    		Bhelper rem = queue.removeFirst();
    		int vtx = rem.vtx;
    		int t = rem.t;
    		
    		if(vis[vtx] == -1) {
    			vis[vtx] = t % 2;
    		} else if(vis[vtx] == t %2){
    			continue;
    		} else {
    			return false;
    		}
    		
    		for(Edge e : graph.get(vtx)) {
    			int nbr = e.nbr;
    			if(vis[nbr] == -1) {
    				queue.addLast(new Bhelper(nbr, t + 1));
    			}
    		}
    	}
    	return true;
    }
    
    static boolean isBipertite(ArrayList<ArrayList<Edge>> graph) {
    	int[] vis = new int[graph.size()];
    	Arrays.fill(vis, -1);
    	
    	for(int v = 0; v < graph.size(); v++) {
    		if(vis[v] == -1) {
    			boolean res = isBipertiteComp(graph, v, vis);
    			if(res == false) return false;
    		}
    	}
    	return true;
    }
	
    static void hamiltonian(ArrayList<ArrayList<Edge>> graph, int src,
    		boolean[] vis, ArrayList<Integer> psf) {
    	
    	if(psf.size() == graph.size() - 1) {
    		psf.add(src);
    		int first = psf.get(0);
    		int last = psf.get(psf.size() - 1);
    		
    		boolean isCycle = false;
    		
    		for(Edge e : graph.get(first)) {
    			int nbr = e.nbr;
    			if(nbr == last) {
    				isCycle = true;
    				break;
    			}
    		}
    		
    		System.out.print(psf);
    		if(isCycle) {
    			System.out.println("*");
    		} else {
    			System.out.println(".");
    		}
    		psf.remove(psf.size() - 1);
    		return;
    	}
    	
    	
    	vis[src] = true;
    	for(Edge e : graph.get(src)) {
    		int nbr = e.nbr;
    		if(vis[nbr] == false) {
    			psf.add(src);
    			hamiltonian(graph, nbr, vis, psf);
    			psf.remove(psf.size() - 1);
    		}
    	}
    	
    	vis[src] = false;
    }
    
    static class Dhelper implements Comparable<Dhelper>{
    	int vtx;
    	String psf;
    	int cost;
    	
    	Dhelper(int vtx, String psf, int cost) {
    		this.vtx = vtx;
    		this.psf = psf;
    		this.cost = cost;
    	}
    	
    	public int compareTo(Dhelper o) {
    		return this.cost - o.cost;
    	}
    }
    
    static void djikstras(ArrayList<ArrayList<Edge>> graph, int src) {
    	PriorityQueue<Dhelper> pq = new PriorityQueue<>();
    	pq.add(new Dhelper(src, "" + src, 0));
    	boolean[] vis = new boolean[graph.size()];
    	while(pq.size() > 0) {
    		Dhelper rem = pq.remove();
    		int vtx = rem.vtx;
    		String psf = rem.psf;
    		int cost = rem.cost;
    		
    		if(vis[vtx] == true) continue;
    		vis[vtx] = true;
    		
    		System.out.println(vtx + " via " + psf + " @ " + cost);
    		
    		for(Edge e : graph.get(vtx)) {
    			int nbr = e.nbr;
    			int wt = e.wt;
    			if(vis[nbr] == false) {
    				pq.add(new Dhelper(nbr, psf + nbr, cost + wt));
    			}
    		}
    	}
    }
    
    static class Phelper implements Comparable<Phelper> {
    	int v;
    	int p;
    	int w;
    	
    	Phelper(int v, int p, int w) {
    		this.v = v;
    		this.p = p;
    		this.w = w;
    	}
    	
    	public int compareTo(Phelper o) {
    		return this.w - o.w;
    	}
    }
    
    static void Prims(ArrayList<ArrayList<Edge>> graph) {
    	// prepare mst(Minimum Spanning Tree) graph
    	ArrayList<ArrayList<Edge>> mst = new ArrayList<>();
    	for(int v = 0; v < graph.size(); v++) {
    		mst.add(new ArrayList<Edge>());
    	}
    	// make priority Queue
    	PriorityQueue<Phelper> pq = new PriorityQueue<>();
    	pq.add(new Phelper(0, -1, 0));
    	boolean[] vis = new boolean[graph.size()];
    	
    	while(pq.size() > 0) {
    		Phelper rem = pq.remove();
    		int v = rem.v;
    		int p = rem.p;
    		int w = rem.w;
    		
    		if(vis[v] == true) continue;
    		vis[v] = true;
    		
    		if(p != -1) {
    			addEdge(mst, v, p, w);
    		}
    		
    		for(Edge e : graph.get(v)) {
    			int nbr = e.nbr;
    			int wt = e.wt;
    			
    			if(vis[nbr] == false) {
    				pq.add(new Phelper(nbr, v, wt));
    			}
    		}
    		
    	}
    	
    	
    	display(mst);
    	
    }
    
	public static void main(String[] args) {
		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		int vtx = 7;
		for(int i = 0; i < vtx; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		addEdge(graph, 0, 1, 10);
		addEdge(graph, 1, 2, 10);
		addEdge(graph, 2, 3, 10);
		addEdge(graph, 0, 3, 40);
		addEdge(graph, 3, 4, 2);
		addEdge(graph, 4, 5, 3);
		addEdge(graph, 5, 6, 3);
		addEdge(graph, 4, 6, 8);
//		addEdge(graph, 2, 5, 0);
		
//		System.out.println(isBipertite(graph));
//		boolean[] vis = new boolean[vtx];
//		ArrayList<Integer> psf = new ArrayList<>();
// 		hamiltonian(graph, 5, vis, psf);
//		djikstras(graph, 0);
		Prims(graph);
		
		
		
		
		
//		display(graph);
//		bfsPath(graph, 0);
		int[][] city = {
				{-2, -2, 0, -2, -2, -2},
				{-2, -1, -1, -2, -1, -1},
				{-2, -2, -1, -2, -2, -2},
				{-2, -2, -2, -2, -1, -1},
				{-2, -1, -1, 0, -2, -2}
		};
//		fire_in_city(city);
//		gcc(graph);
//		System.out.println(isConnected(graph));
//		System.out.println(isCyclic(graph));
		
		
		
		
		
//		boolean[] vis = new boolean[vtx];
//		System.out.println("Has Path ? b/w 0 to 6 : " + hasPath(graph, 0, 6, vis));
//		printAllPath1(graph, 0, 6, vis, "", 0);
//		System.out.println();
//		vis[0] = true;
//		printAllPath2(graph, 0, 6, vis, "", 0);
//		vis[0] = false;
		
//		Mhelper res = new Mhelper();
//		min_maxPath(graph, 0, 6, "", 0, vis, res);
//		System.out.println("Min Path : " + res.minPath + " @ " + res.minCost);
//		System.out.println("Max Path : " + res.maxPath + " @ " + res.maxCost);
//		
//		CFhelper cf = new CFhelper();
//		cfPath(graph, 0, 6, "", 0, vis, cf, 40);
//		System.out.println("ceil(40) : " + cf.cpath + " @ " + cf.ceil);
//		System.out.println("floor(40) : " + cf.fpath + " @ " + cf.floor);
		
//		kthLargest(graph, 0, 6, "", 0, vis, 3);
//		System.out.println(dfsi(graph, 0, 6));
//		System.out.println(bfs(graph, 0, 6));
		
	}

}
