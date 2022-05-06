/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph.adjacentListGraph;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.PriorityQueue;

/**
 *
 * @author glee
 */
public class MSTPrim {
    AdjListGraph graph;
    
    MSTPrim(AdjListGraph graph)
    {
        this.graph = graph;
    }
    
    public ArrayList<Vertex> mstPrim(String root)
    {
        Enumeration<String> enumVertices = graph.getVertices();
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>((x, y) -> (int)(x.key - y.key));
//        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>((x, y) -> Vertex.compare(x,y));
        
        ArrayList<Vertex> mst = new ArrayList<>();
        Hashtable<String, Vertex> htVertex = new Hashtable<>();
        
        while (enumVertices.hasMoreElements())
        {
            String vertexName = enumVertices.nextElement();
            int key = Integer.MAX_VALUE;
            if (vertexName.equals(root))
                key = 0;
            
            Vertex vertex = new Vertex(vertexName, key, "");
            
            priorityQueue.add(vertex);
            htVertex.put(vertexName, vertex);
        }
        
        while (!priorityQueue.isEmpty())
        {
            Vertex u = priorityQueue.poll();
            ArrayList<Edge> alEdges = graph.getConnectedEdges(u.name);
            mst.add(u);
            for (int i=0;i<alEdges.size();i++)
            {
                Edge edge = alEdges.get(i);
                Vertex v = htVertex.get(edge.to);
                if (!inMST(edge.to, mst) && edge.weight < v.key)
                {
                    v.predecessor = u.name;
                    v.key = edge.weight;
                    // decrease key (by remove v, then add v with updated key)
                    priorityQueue.remove(v);
                    priorityQueue.add(v);
                }
            }
        }

        return mst;
    }
    
    public boolean inMST(String v, ArrayList<Vertex> mst)
    {
        for (int i=0;i<mst.size();i++)
            if (mst.get(i).name.equals(v))
                return true;
        
        return false;
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
        
        MSTPrim mstPrim = new MSTPrim(graph);
        ArrayList<Vertex> mst = mstPrim.mstPrim("a");
        System.out.println(mst);
    }
}

class Vertex 
{
    String name;
    double key;
    String predecessor;

    public Vertex(String name, double key, String pred)
    {
        this.name = name;
        this.key = key;
        this.predecessor = pred;
    }

//    public static int compare(Vertex one, Vertex another)
//    {
//        return (int)(one.key - another.key);
//    }
    
    @Override
    public String toString()
    {
        return name;
    }
}





