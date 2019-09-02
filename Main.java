

public class Main {
	public static void main(String[] args)
	{
		Dijkstra sssp = new Dijkstra(5);
		//Graph
		//A
		sssp.edge(0, 0, 0);
		sssp.edge(0, 1, 50);
		sssp.edge(0, 2, 0);
		sssp.edge(0, 3, 80);
		sssp.edge(0, 4, 0);
		//B
		sssp.edge(1, 0, 0);
		sssp.edge(1, 1, 0);
		sssp.edge(1, 2, 60);
		sssp.edge(1, 3, 90);
		sssp.edge(1, 4, 0);
		//C
		sssp.edge(2, 0, 0);
		sssp.edge(2, 1, 0);
		sssp.edge(2, 2, 0);
		sssp.edge(2, 3, 0);
		sssp.edge(2, 4, 40);
		//D
		sssp.edge(3, 0, 0);
		sssp.edge(3, 1, 0);
		sssp.edge(3, 2, 20);
		sssp.edge(3, 3, 0);
		sssp.edge(3, 4, 70);
		//E
		sssp.edge(4, 0, 0);
		sssp.edge(4, 1, 50);
		sssp.edge(4, 2, 0);
		sssp.edge(4, 3, 0);
		sssp.edge(4, 4, 0);
		System.out.println(sssp.Dijkstras(0));	
		
		
	}
}
