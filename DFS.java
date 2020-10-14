import java.io.*; 
import java.util.*; 
  

class DFS 
{ 
    private int V;    
    private LinkedList<Integer> adj[];  
  
    
    DFS(int v) 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; i++) 
            adj[i] = new LinkedList(); 
    } 
  
    
    void addEdge(int u,int v) 
    { 
        adj[u].add(v); 
        adj[v].add(u);
    } 
  
    
    
    void DFStraversal(int v,boolean visited[]) 
    { 
        
        visited[v] = true; 
        
        System.out.print(v+" "); 
  
        
        Iterator<Integer> i = adj[v].listIterator(); 
        while (i.hasNext()) 
        { 
            int n = i.next(); 
            if (!visited[n]) 
                DFStraversal(n, visited); 
        } 
    } 
  
    
    void DFS() 
    { 
        
        boolean visited[] = new boolean[V]; 
	    System.out.println("DFS");
        for(int i=0;i<V;i++) 
        { if(!visited[i])
  
        
        DFStraversal(i, visited);
	    System.out.println();
        }
    } 

  
  

  
    
    public static void main(String args[]) 
    { 
        DFS g = new DFS(21); 
  
        g.addEdge(0, 1); 
        g.addEdge(0, 5); 
        g.addEdge(1, 2); 
        g.addEdge(1, 5); 
        g.addEdge(1,6); 
        g.addEdge(1,3);
        g.addEdge(2,5);
        g.addEdge(2, 6);
        g.addEdge(2,3 );
        g.addEdge(5,4);
        g.addEdge(3,4);
        g.addEdge(7, 8);
        g.addEdge(7,10);
        g.addEdge(7,11);	
        g.addEdge(8,10);
        g.addEdge(8,9);
        g.addEdge(8,14);
        g.addEdge(9,10);
        g.addEdge(9,12);
        g.addEdge(9,14);
        g.addEdge(12,13);
        g.addEdge(12,15);
        g.addEdge(16,17);
        g.addEdge(17,18);
        g.addEdge(17,19);
        g.addEdge(18,19);
        g.addEdge(19,20);
  
        g.DFS();
    } 
} 
