#include <iostream> 
#include <list> 
#include <stack> 
#include <queue>
using namespace std;

class Graph
{
	int V;
	list<int>* Adj;
	char* Vert;
public:
	Graph(int V, char* n);
	void AddEdge(int v1, int v2);
	void TopologicalSort();
};

Graph::Graph(int V, char* n)
{
	this->V = V;
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

void Graph::TopologicalSort()
{
	vector<int> InDegree(V, 0);
	vector<char> TopologicalOrder;
	queue<int> Q;
	list<int>::iterator it;
	int i, cnt = 0;
	for (i = 0; i < V; i++)
	{
		for (it = Adj[i].begin(); it != Adj[i].end(); ++it)
		{
			InDegree[*it]++;
		}
	}
	for (i = 0; i < V; i++)
	{
		if (InDegree[i] == 0)
		{
			Q.push(i);
		}
	}
	while (!Q.empty())
	{
		int x = Q.front();
		Q.pop();
		TopologicalOrder.push_back(Vert[x]);
		for (it = Adj[x].begin(); it != Adj[x].end(); ++it)
		{
			if (--InDegree[*it] == 0)
			{
				Q.push(*it);
			}
		}
		cnt++;
	}
	if (cnt != V)
	{
		cout << "There is a cycle\n" << endl;
		return;
	}
	for (i = 0; i < TopologicalOrder.size(); i++)
	{
		cout << TopologicalOrder[i] << " ";
	}
	cout << endl << endl;
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
	cout << "the output of BFS Topological sort on first graph is as follows:" << endl;
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
	cout << "The BFS topological sort on  second graph:" << endl;
	g2.TopologicalSort();
	return 0;
}
