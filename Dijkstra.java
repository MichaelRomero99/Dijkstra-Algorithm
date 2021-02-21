import java.util.Arrays;

public class Dijkstra {
	private Vertex[] ssspList;
	private int[][] graph;
	
	public static final int INFINITY = 2147483647;	
	//single source shortests path algorithm
	
	public Dijkstra(int sz)
	{
		ssspList = new Vertex[sz];
		graph = new int[sz][sz];
	}
	
	public String toString(int source)
	{
		int count = 0;
		String solution = "The shortest path distance from the source " + source + " to each vertice costs: ";
		for(Vertex v: ssspList)
		{
			if(source != count)
			{
				solution = solution + v.distance + " to index: " +  v.indexInGraph + ", ";
			}
			count++;
		}
		return solution;
	}
	//sssp algorithm
	public String Dijkstras(int source) {			
		HeapStructure minHeap = new HeapStructure();
		initializeGraph(ssspList, source, minHeap);
		int j = ssspList.length-1;
		while(j != 0)
		{
			Vertex min = minHeap.findMin();
			if(min.distance != INFINITY)
			{
				loopGraph(min.indexInGraph);				
				min.known = true;		
			}
			minHeap.delete();
			j--;
		}
		//solution(source,ssspList);
		return toString(source);
	}
	
	public void loopGraph(int source)
	{
		for(int i = 0; i < graph.length;i++)
		{
			if(graph[source][i] != INFINITY)
			{
				relax(source, i);
			}
		}
	}
	
	public void relax(int u, int v)
	{
		if(ssspList[u].distance + graph[u][v] < ssspList[v].distance)
		{
			ssspList[v].distance = ssspList[u].distance + graph[u][v];
			//update parent
			ssspList[v].parent = ssspList[u];
			
		}
	}
	
	public void edge(int indexRow, int indexColumn, int destination) 
	{
		if(graph[indexRow][indexColumn] == 0){
			Vertex aVertice = new Vertex();
			aVertice.indexInGraph = indexRow;
			ssspList[indexRow] = aVertice;
		}		
		if (destination != 0) {		
			graph[indexRow][indexColumn] = destination;
		}
		else
		{
			graph[indexRow][indexColumn] = INFINITY;
		}

	}

	public void initializeGraph(Vertex[] g, int source, HeapStructure minHeap) 
	{
		int count = 0;		
		for(Vertex v: g)
		{
			//initialize
			v.indexInGraph = count;
			v.parent = null;
			v.known = false;
			if(count == source)
			{
				v.distance = 0;
			}
			else
			{
				v.distance = INFINITY;
			}
			minHeap.insert(v);
			count++;
		}
		
	}

	public class Vertex {
		private int indexInGraph;
		private int distance;
		private Vertex parent;
		private boolean known;
	}
	
	public class HeapStructure {	
		private Vertex[] heap;
		private int INDEX = 1;	
		public HeapStructure() 
		{
			heap = new Vertex[6];
		}
	//insets to the tree
		public void insert(Vertex v)
		{
			heap[0] = null;
			if(heap[1]!= null)
			{
				INDEX++;
			}
			heap[INDEX] = v;
			if(INDEX != 1)
			{
				bubbleUp(INDEX);
			}
		}
		public int parent(int current)
		{
			return current/2;
		}
		public void bubbleUp(int currentIndex)
		{	
			if(parent(currentIndex) >= 1)
			{
				int parentIndexOfCurrent = parent(currentIndex);
				if(heap[parentIndexOfCurrent].distance > heap[currentIndex].distance)
				{
					Vertex parentDataHolder = heap[parentIndexOfCurrent];
					heap[parentIndexOfCurrent] = heap[currentIndex];
					heap[currentIndex] = parentDataHolder;
				}
				bubbleUp(parentIndexOfCurrent);
			}
			
		}
	//returns the root
		public Vertex findMin()
		{
			return heap[1];
		}
	//left child
		public int left(int parent)
		{
			int c = parent * 2;
			if (c+1 > heap.length) 
			{
				c = heap.length + 1;
			}
			return c;
		}
	//right child
		public int right(int parent) 
		{
			int c = (parent * 2) + 1;
			if (c+1 > heap.length || heap[c]== null) 
			{
				c = heap.length + 1;
			}
			return c;
		}
		//builds the heap
		public void BuildMinHeap()
		{		
			for(int i = INDEX/2; i > 0;i--)
			{
				minHeapify(i); 
			}
			
		}
	//delets from heap
		public Vertex delete() 
		{
			Vertex delete = findMin();
			for (int i = heap.length - 1; i > 0; i--) 
			{
				if (heap[i] != null)
				{
					heap[1] = heap[i];					 
					heap[i] = null;
					break;
				}
			}
			minHeapify(1);
			return delete;
		}
		public int findLength()
		{
			int count = 0;			
			for (Vertex i : heap) {
			    if (i != null) {
			        count++;
			    }
			}
			return count;
		
		}
		//fixes order of heap
		public void minHeapify(int index) 
		{
			int min;
			int left = left(index);
			int right = right(index);
			//will check if it will swap to left child or right child
			//min has the child it will swap to
			if (left <= findLength() && heap[index].distance > heap[left].distance) 
			{
				min = left;
			} else 
			{
				min = index;
			}
			//will only check the right child if there's a right child
			if (right <= findLength() && heap[index].distance > heap[right].distance) 
			{
				if (heap[right].distance < heap[left].distance) 
				{
					min = right;
				}
			}
			//makes the swap
			//will not swap if there's only one vertex in the heap
			if (min != index) 
			{
				//current root
				Vertex c = heap[index];
				heap[index] = heap[min];
				if (min == left) 
				{
					heap[left] = c;
				} 
				else if (min == right)
				{
					heap[right] = c;
				}
				
				//Will check if you still have to keep going down the heap to swap
				int check = min * 2;
				int checkTwo = (min * 2) + 1;
				if (check <= findLength() || checkTwo <= findLength()) //checks the length first
				{
					minHeapify(min);	
				}
			}
		}
		
	}
	
	
}
