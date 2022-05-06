/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph.adjacentListGraph;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author glee
 */

public class AdjListGraph
{
    Hashtable<String, ArrayList<Edge>> vertices = new Hashtable<>();
    
    public void addVertex(String v)
    {
        vertices.put(v, new ArrayList<Edge>());
    }
    
    public void addUndirectedEdge(String i, String j, double weight)
    {
        // add i -> j
        ArrayList<Edge> edgeList = vertices.get(i);
        edgeList.add(new Edge(i, j, weight));
        
        // add j -> i
        edgeList = vertices.get(j);
        edgeList.add(new Edge(j, i, weight));
    }
    
    public void addDirectedEdge(String i, String j, double weight)
    {
        // add i -> j
        ArrayList<Edge> edgeList = vertices.get(i);
        edgeList.add(new Edge(i, j, weight));
    }

    public ArrayList<Edge> getConnectedEdges(String u)
    {
        return vertices.get(u);
    }
    
    public boolean isConnected(String u, String v)
    {
        ArrayList<Edge> edges = getConnectedEdges(u);
        for (int i=0;i<edges.size();i++)
        {
            Edge edge = edges.get(i);
            if (edge.to.equals(v))
                return true;
        }
        
        return false;
    }
    
    public double getWeight(String u, String v)
    {
        ArrayList<Edge> edges = getConnectedEdges(u);
        for (int i=0;i<edges.size();i++)
        {
            Edge edge = edges.get(i);
            if (edge.to.equals(v))
                return edge.weight;
        }
        
        return -1;
    }    
    
    public Enumeration<String> getVertices()
    {
        return vertices.keys();
    }
}

class Edge 
{
    String from;
    String to;
    double weight;
    
    public Edge(String from, String to, double weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    public int compareTo(Object edge)
    {
        Edge otherEdge = (Edge) edge;
        
        if (this.weight > otherEdge.weight)
            return 1;
        else if (this.weight == otherEdge.weight)
            return 0;
        else 
            return -1;
    }
    
    public String toString()
    {
        return "from: " + from + ", to: " + to + ", weight: " + weight + "\n";
    }
}