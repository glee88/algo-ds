/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.adjacentListGraph;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 *
 * @author glee
 */
public class Dijikstra {
    AdjListGraph graph;
    
    Dijikstra(AdjListGraph graph)
    {
        this.graph = graph;
    }
        
    public ArrayList<DijVertex> SingleSourceShortestPath(String rootName)
    {
        Hashtable<String, DijVertex> htVertex = new Hashtable<>();
        PriorityQueue<DijVertex> priorityQueue = new PriorityQueue<>((x, y) -> (int)(x.d - y.d));
        
        // INITIALIZE_SINGLE_SOURCE
        initializeDijikstra(priorityQueue, htVertex, rootName);

        ArrayList<DijVertex> SS = new ArrayList<>();
        
        while (!priorityQueue.isEmpty())
        {
            DijVertex u = priorityQueue.poll();
            SS.add(u);
            
            ArrayList<Edge> alEdges = graph.getConnectedEdges(u.name);

            for (int i=0;i<alEdges.size();i++)
            {
                Edge edge = alEdges.get(i);
                DijVertex v = htVertex.get(edge.to);
                
                relax(u, v);
            }            
        }
        
        return SS;
    }
    
    public void initializeDijikstra(PriorityQueue<DijVertex> priorityQueue, Hashtable<String, DijVertex> htVertex, String rootName)
    {
        Enumeration<String> enumVertices = graph.getVertices();        
        while (enumVertices.hasMoreElements())
        {
            String vertexName = enumVertices.nextElement();
            int d = Integer.MAX_VALUE;
            if (vertexName.equals(rootName))
                d = 0;
            
            DijVertex vertex = new DijVertex(vertexName, d, "");
            
            priorityQueue.add(vertex);
            htVertex.put(vertexName, vertex);
        }        
    }
    
    public void relax(DijVertex u, DijVertex v)
    {
        if (v.d > u.d + graph.getWeight(u.name, v.name))
        {
            v.d = u.d + graph.getWeight(u.name, v.name);
            v.predecessor = u.name;
        }
    }
    
public static void main(String[] args) {
        // graph example on p.634 Cormen's book 2nd ed.
        AdjListGraph graph = new AdjListGraph();
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");
        graph.addVertex("g");
        graph.addVertex("h");
        graph.addVertex("i");
        
        graph.addUndirectedEdge("a", "b", 4);
        graph.addUndirectedEdge("a", "h", 8);
        
        graph.addUndirectedEdge("b", "c", 8);
        graph.addUndirectedEdge("b", "h", 11);
        
        graph.addUndirectedEdge("c", "d", 7);
        graph.addUndirectedEdge("c", "i", 2);
        graph.addUndirectedEdge("c", "f", 4);
        
        graph.addUndirectedEdge("d", "f", 14);
        graph.addUndirectedEdge("d", "e", 9);
        
        graph.addUndirectedEdge("e", "f", 10);
        
        graph.addUndirectedEdge("f", "g", 2);
        
        graph.addUndirectedEdge("g", "h", 1);
        graph.addUndirectedEdge("g", "i", 6);
        
        graph.addUndirectedEdge("h", "i", 7);
        
        Dijikstra dijikstra = new Dijikstra(graph);
        ArrayList<DijVertex> mst = dijikstra.SingleSourceShortestPath("a");
        System.out.println(mst);
    }
}

class DijVertex 
{
    String name;
    double d;
    String predecessor;

    public DijVertex(String name, double d, String pred)
    {
        this.name = name;
        this.d = d;
        this.predecessor = pred;
    }

//    public static int compare(Vertex one, Vertex another)
//    {
//        return (int)(one.key - another.key);
//    }
    
    @Override
    public String toString()
    {
        return name + "; Pred:" + this.predecessor;
    }
}
