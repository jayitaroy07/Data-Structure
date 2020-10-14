#include <iostream> 
#include <list> 
#include <stack> 
using namespace std;

class Graph
{
	int V;
	bool Cycle;
	list<int>* Adj;
	char* Vert;
	void TopologicalSortI(int v, bool visited[], bool done[], stack<int>& st);
public:
	Graph(int V, char* n);
	void AddEdge(int v1, int v2);
	void TopologicalSort();
};

Graph::Graph(int V, char* n)
{
	this->V = V;
	Cycle = false;
	Adj = new list<int>[V];
	Vert = new char[V];
	for (int i = 0; i < V; i++)
	{
		Vert[i] = n[i];
	}
}

void Graph::AddEdge(int v1, int v2)
{
	Adj[v1].push_back(v2);
}

void Graph::TopologicalSortI(int v, bool visited[], bool done[], stack<int>& st)
{
	visited[v] = true;
	list<int>::iterator it;
	for (it = Adj[v].begin(); it != Adj[v].end(); ++it)
	{
		if (!visited[*it])
		{
			TopologicalSortI(*it, visited, done, st);
		}
		else if (visited[*it] && !done[*it])
		{
			cout << "There is a cycle in this graph\n" << endl;
			Cycle = true;
		}
	}
	st.push(v);
	done[v] = true;
}

void Graph::TopologicalSort()
{
	stack<int> st;
	int i;
	bool* visited = new bool[V];
	bool* done = new bool[V];
	for (i = 0; i < V; i++)
	{
		visited[i] = false;
		done[i] = false;
	}
	for (i = 0; i < V; i++)
	{
		if (!visited[i])
		{
			TopologicalSortI(i, visited, done, st);
		}
	}
	if (!Cycle)
	{
		while (!st.empty())
		{
			cout << Vert[st.top()] << " ";
			st.pop();
		}
		cout << endl << endl;
	}
}

int main()
{
	char G1[] = { 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	Graph g1(14, G1);
	g1.AddEdge(0, 4);
	g1.AddEdge(0, 5);
	g1.AddEdge(0, 11);
	g1.AddEdge(1, 2);
	g1.AddEdge(1, 4);
	g1.AddEdge(1, 8);
	g1.AddEdge(2, 5);
	g1.AddEdge(2, 6);
	g1.AddEdge(2, 9);
	g1.AddEdge(3, 2);
	g1.AddEdge(3, 6);
	g1.AddEdge(3, 13);
	g1.AddEdge(4, 7);
	g1.AddEdge(5, 8);
	g1.AddEdge(5, 12);
	g1.AddEdge(6, 5);
	g1.AddEdge(8, 7);
	g1.AddEdge(9, 10);
	g1.AddEdge(9, 11);
	g1.AddEdge(10, 13);
	g1.AddEdge(12, 9);
	cout << "the output of DFS Topological sort on first graph is as follows:" << endl;
	g1.TopologicalSort();
	char G2[] = { '1', '2', '3', '4', '5', '6', '7', '8' };
	Graph g2(8, G2);
	g2.AddEdge(0, 1);
	g2.AddEdge(0, 4);
	g2.AddEdge(0, 5);
	g2.AddEdge(1, 2);
	g2.AddEdge(1, 4);
	g2.AddEdge(1, 6);
	g2.AddEdge(2, 3);
	g2.AddEdge(3, 4);
	g2.AddEdge(4, 6);
	g2.AddEdge(4, 7);
	g2.AddEdge(5, 4);
	g2.AddEdge(5, 7);
	g2.AddEdge(6, 3);
	g2.AddEdge(6, 7);
	cout << "DFS topological sort on second graph:" << endl;
	g2.TopologicalSort();
	return 0;
}
