/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph.adjacentListGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 *
 * @author glee
 */
public class MSTKruksal {
    Graph graph;
    
    MSTKruksal(Graph graph)
    {
        this.graph = graph;
    }
    
    public ArrayList<Edge> getMinimumSpanningTree()
    {
        graph.sortEdges(); // sort edges by weight in nondecreasing order
        
        ArrayList<String> vertices = graph.vertices;
        ArrayList<HashSet<String>> vertexSetList = new ArrayList<>(); // tp be used to store tree sets made
        ArrayList<Edge> mst = new ArrayList<>(); // minimmum spanning tree to be added
        
        // make a set for each vertex
        for (int i=0;i<vertices.size();i++)
        {
            HashSet<String> hs = new HashSet<String>();
            hs.add(vertices.get(i));
            
            vertexSetList.add(hs);
        }
        
        ArrayList<Edge> edges = graph.edges;
        
        // go over edges in the order of weight. Sorted earlier
        for (int i=0;i<edges.size();i++) 
        {
            Edge edge = edges.get(i);
            System.out.println(edge);
            HashSet<String> fromSet = getSet(edge.from, vertexSetList);
            HashSet<String> toSet = getSet(edge.to, vertexSetList);
            
            // if u, v are not in the same set, u union v, add (u, v) to MST
            if (!isSameSet(fromSet, toSet))
            {
                fromSet.addAll(toSet); // fromSet = fromSet union toSet
                vertexSetList.remove(toSet); // keep fromSet, and remove toSet from list of sets
                mst.add(edge); // edge with u, v is added to MST
            }
        }
        
        return mst;
    }
    
    // find a set that has vertex as its element from vertexSetsList
    HashSet<String> getSet(String vertex, ArrayList<HashSet<String>> vertexSetsList)
    {
        for (int i=0;i<vertexSetsList.size();i++)
        {
            HashSet<String> hs = vertexSetsList.get(i);
            if (hs.contains(vertex))
                return hs;
        }
        return null;
    }
    
    // check if set a and b are same set, i.e. have same elements
    boolean isSameSet(HashSet<String> a, HashSet<String> b)
    {
        HashSet<Integer> c = (HashSet<Integer>) a.clone();
        c.removeAll(b); // C = A - B
        return c.isEmpty(); // if C is an empty set, A == B
    }
    
    public static void main(String[] args) {
        // test case from p. 632 from Cormen's book 2nd ed.
        Graph graph = new Graph();
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");
        graph.addVertex("g");
        graph.addVertex("h");
        graph.addVertex("i");
        
        graph.connectVertices("a", "b", 4);
        graph.connectVertices("a", "h", 8);
        
        graph.connectVertices("b", "c", 8);
        graph.connectVertices("b", "h", 11);
        
        graph.connectVertices("c", "d", 7);
        graph.connectVertices("c", "i", 2);
        graph.connectVertices("c", "f", 4);
        
        graph.connectVertices("d", "f", 14);
        graph.connectVertices("d", "e", 9);
        
        graph.connectVertices("e", "f", 10);
        
        graph.connectVertices("f", "g", 2);
        
        graph.connectVertices("g", "h", 1);
        graph.connectVertices("g", "i", 6);
        
        graph.connectVertices("h", "i", 7);
        
        MSTKruksal mstKruksal = new MSTKruksal(graph);
//        mstKruksal.getMinimumSpanningTree();
        
        System.out.println(mstKruksal.getMinimumSpanningTree());
    }
}

// A simple graph, but not a graph in adjacent list representation. Just Vertices and Edges. G(V, E)
class Graph
{
    ArrayList<String> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    
    public void addVertex(String i)
    {
        vertices.add(i);
    }
    
    public void connectVertices(String i, String j, double weight)
    {
        edges.add(new Edge(i, j, weight));
    }
    
    public void sortEdges()
    {
        // ArrayList edges is to be sorted by using SortEdge.compare() method (SortEdge implements Comparater)
        edges.sort(new SortEdge()); 
    }
    
    // Comparator to sort Edge objects by weight
    class SortEdge implements Comparator<Edge>
    {
        public int compare(Edge a, Edge b)
        {
            return (int)(a.weight - b.weight);
        }
    }
}


