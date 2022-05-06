/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.adjacentListGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author glee
 */
public class DepthFirstSearch {
    AdjListGraph graph;
    Map<String, DFSVertex> mapVertices = new HashMap<>();
    int time = 0;
    DepthFirstSearch(AdjListGraph graph)
    {
        this.graph = graph;
    }
    
    public void dfs()
    {
        initialize();
        
        Enumeration<String> vertexNames = graph.vertices.keys();
        
        while (vertexNames.hasMoreElements())
        {
            String vertexName = vertexNames.nextElement();
            DFSVertex vertex = mapVertices.get(vertexName);
            if (vertex.color == DFSVertex.WHITE)
                dfsVisit(vertexName);
        }
        
        List<DFSVertex> listVertices = new ArrayList<DFSVertex>(mapVertices.values());
        Collections.sort(listVertices, (v1, v2) -> v2.finishTime - v1.finishTime);
        System.out.println(listVertices);
    }
    
    void dfsVisit(String vertexName)
    {
        time++;
        DFSVertex vertex = mapVertices.get(vertexName);
        vertex.discoveryTime = time;
        vertex.color = DFSVertex.GRAY;
        
        ArrayList<Edge> connected = graph.getConnectedEdges(vertexName);
        
        for (int i=0;i<connected.size();i++)
        {
            Edge edge = connected.get(i);
            String connectedVertexName = edge.to;
            DFSVertex connectedVertex = mapVertices.get(connectedVertexName);
            if (connectedVertex.color == DFSVertex.WHITE)
            {
                connectedVertex.predecessor = vertex.name;
                dfsVisit(connectedVertexName);
            }
        }
        
        time++;
        vertex.color = DFSVertex.BLACK;
        vertex.finishTime = time;
    }
    
    void initialize()
    {
        time = 0;
        Enumeration<String> vertexNames = graph.vertices.keys();
        
        while (vertexNames.hasMoreElements())
        {
            String vertexName = vertexNames.nextElement();
            mapVertices.put(vertexName, new DFSVertex(vertexName));
        }
    }
    
    void topologicalSort()
    {
        dfs();
        List<DFSVertex> listVertices = new ArrayList<DFSVertex>(mapVertices.values());
        Collections.sort(listVertices, (v1, v2) -> v2.finishTime - v1.finishTime);
        System.out.println(listVertices);
    }
    
    class DFSVertex
    {
        String name;
        String predecessor;
        int color;
        int discoveryTime;
        int finishTime;
        final static int WHITE = 0, GRAY = 1, BLACK = 1;
        
        DFSVertex(String name)
        {
            this.name = name;
            predecessor = "";
            color = WHITE;
            discoveryTime = 0;
            finishTime = 0;
        }
        
        @Override
        public String toString()
        {
            return name + ", predecessor: " + predecessor + ", d: " + discoveryTime + ", f:" + finishTime;
        }
    }
    
    public static void main(String[] args) {
        AdjListGraph graph = new AdjListGraph();
//        graph.addVertex("s");
//        graph.addVertex("t");
//        graph.addVertex("u");
//        graph.addVertex("v");
//        graph.addVertex("w");
//        graph.addVertex("x");
//        graph.addVertex("y");
//        graph.addVertex("z");
//        
//        graph.addDirectedEdge("s", "z", 0);
//        graph.addDirectedEdge("s", "w", 0);
//        
//        graph.addDirectedEdge("t", "u", 0);
//        graph.addDirectedEdge("t", "v", 0);
//        
//        graph.addDirectedEdge("u", "t", 0);
//        graph.addDirectedEdge("u", "v", 0);
//        
//        graph.addDirectedEdge("v", "s", 0);
//        graph.addDirectedEdge("v", "w", 0);
//        
//        graph.addDirectedEdge("w", "x", 0);
//        
//        graph.addDirectedEdge("x", "z", 0);
//        
//        graph.addDirectedEdge("y", "x", 0);
//        
//        graph.addDirectedEdge("z", "y", 0);
//        graph.addDirectedEdge("z", "w", 0);
        

//  test for Topological Sort
        graph.addVertex("shirt");
        graph.addVertex("tie");
        graph.addVertex("jacket");
        graph.addVertex("belt");
        graph.addVertex("watch");     
        graph.addVertex("undershorts");
        graph.addVertex("pants");
   
        graph.addVertex("shoes");
        graph.addVertex("socks");

        graph.addDirectedEdge("undershorts", "pants", 0);
        graph.addDirectedEdge("undershorts", "shoes", 0);
        
        graph.addDirectedEdge("pants", "shoes", 0);
        graph.addDirectedEdge("pants", "belt", 0);

        graph.addDirectedEdge("belt", "jacket", 0);
        
        graph.addDirectedEdge("shirt", "belt", 0);
        graph.addDirectedEdge("shirt", "tie", 0);
        
        graph.addDirectedEdge("tie", "jacket", 0);
        
        graph.addDirectedEdge("socks", "shoes", 0);
        DepthFirstSearch dfs = new DepthFirstSearch(graph);
        dfs.topologicalSort();
//        dfs.dfs();
        
    }
}
