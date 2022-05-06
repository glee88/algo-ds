/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.adjListGraph;
import graph.GraphNode;
import hashtable.Hashnode;
import hashtable.openaddress.Hashtable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import linkedLists.*;

/**
 *
 * @author glee
 */
public class AdjacentListGraph<V> {
    private Hashtable<V, GraphNode<V>> graphTable = new Hashtable<>();
    private int time = 0;
    
    public boolean isEmpty()
    {
        return graphTable.size() == 0;
    }
    
    public GraphNode<V> addNode(V value)
    {
        GraphNode<V> newNode = new GraphNode<>(value);
        newNode.getLinkedNodes().addBack(value);
        graphTable.insert(new Hashnode(value, newNode));
        return newNode;
    }
    
    void addNode(V value, Hashtable<V, GraphNode<V>> graph)
    {
        GraphNode<V> newNode = new GraphNode<>(value);
        newNode.getLinkedNodes().addBack(value);
        graph.insert(new Hashnode(value, newNode));
    }
    
    public int size()
    {
        return graphTable.size();
    }
    
    public boolean hasEdge(V node1, V node2)
    {
        GraphNode<V> node1List = graphTable.search(node1);
        if (node1List.getLinkedNodes().search(node2) != null)
            return true;
        else
            return false;
    }
    
    public void connect(V node1, V node2)
    {
//        if (hasEdge(node1, node2))
//            return; // already connected
//        else
        {
            GraphNode<V> graphNode = graphTable.search(node1);
            graphNode.getLinkedNodes().addBack(node2);
        }
    }
    
    void connect(V node1, V node2, Hashtable<V, GraphNode<V>> graph)
    {
        {
            GraphNode<V> graphNode = graph.search(node1);
            graphNode.getLinkedNodes().addBack(node2);
        }
    }    
    
    public void connectBothDirections(V node1, V node2)
    {
        if (hasEdge(node1, node2) && hasEdge(node2, node1))
            return; // already connected
        else
        {
            GraphNode<V> graphNode1 = graphTable.search(node1);
            graphNode1.getLinkedNodes().addBack(node2);
            
            GraphNode<V> graphNode2 = graphTable.search(node2);
            graphNode2.getLinkedNodes().addBack(node1);
        }
    }

    @Override
    public String toString()
    {
        String sOut = "";
        List<V> keys = graphTable.getKeys();
        
        Iterator<V> iter = keys.iterator();
        
        while (iter.hasNext())
        {
            V key = iter.next();
            GraphNode<V> graphNode = graphTable.search(key);
            sOut += graphNode + "\n";
        }
        
        return sOut;
    }
    
    public String toString(Hashtable<V, GraphNode<V>> graph)
    {
        String sOut = "";
        List<V> keys = graph.getKeys();
        
        Iterator<V> iter = keys.iterator();
        
        while (iter.hasNext())
        {
            V key = iter.next();
            GraphNode<V> graphNode = graph.search(key);
            sOut += graphNode + "\n";
        }
        
        return sOut;
    }    
    
    public void breadthFirstSearch(V nodeName)
    {
        List<V> keys = graphTable.getKeys();
        Iterator<V> iter = keys.iterator();
        // initialize
        while (iter.hasNext())
        {
            V key = iter.next();
            GraphNode<V> u = graphTable.search(key);
            u.setColor(GraphNode.WHITE);
            u.setDiscoveryTime(Integer.MAX_VALUE);
            u.setPredecessor(null);            
        }
        
        // set start node, and add to queue
        GraphNode<V> startNode = graphTable.search(nodeName);
       
        startNode.setColor(GraphNode.GRAY);
        startNode.setDiscoveryTime(0);
        startNode.setPredecessor(null);
        
        Queue<GraphNode<V>> queue = new Queue<>();

        queue.enqueue(startNode);
        
        while (!queue.isEmpty())
        {
            GraphNode<V> u = queue.dequeue();
            System.out.println(u.getValue() + " visited");
            LinkedList<V> nodeList = u.getLinkedNodes();
            
            int i=1;
            LinkedListNode<V> vName = nodeList.getNThNode(i);
            while (vName != null)
            {
                GraphNode<V> v = graphTable.search(vName.getValue());
                if (v.getColor() == GraphNode.WHITE)
                {
                    v.setColor(GraphNode.GRAY);
                    v.setDiscoveryTime(u.getDiscoveryTime() + 1);
                    v.setPredecessor(u.getValue());
//                    System.out.println(v);
                    queue.enqueue(v);
                }
                
                vName = nodeList.getNThNode(++i);
            }
            u.setColor(GraphNode.BLACK);
        }
    }
//    public void disconnect(V node1, V node2)
//    {
//        if (!hasEdge(node1, node2))
//            return; // already unconnected
//        else
//        {
//            LinkedList<V> llNode1 = graphTable.search(node1); // linked list doesn't have remove method yet
//        }
//        
//    }
    
    void initializeGraphNodesForSearch()
    {
        List<V> keys = graphTable.getKeys();
        Iterator<V> iter = keys.iterator();
        // initialize
        while (iter.hasNext())
        {
            V key = iter.next();
            GraphNode<V> u = graphTable.search(key);
            u.setColor(GraphNode.WHITE);
            u.setDiscoveryTime(Integer.MAX_VALUE);
            u.setPredecessor(null);            
        }
        time = 0;
    }
    
    void initializeGraphNodesForSearch(Hashtable<V, GraphNode<V>> graph)
    {
        List<V> keys = graph.getKeys();
        Iterator<V> iter = keys.iterator();
        // initialize
        while (iter.hasNext())
        {
            V key = iter.next();
            GraphNode<V> u = graph.search(key);
            u.setColor(GraphNode.WHITE);
            u.setDiscoveryTime(Integer.MAX_VALUE);
            u.setPredecessor(null);            
        }
        time = 0;
    }    
    
    public void depthFirstSearch()
    {
        initializeGraphNodesForSearch(); // set color to white, predecessor to null, discovery time to inf, set time to 0
        
//        time = 0;

        List<V> keys = graphTable.getKeys();
        Iterator<V> iter = keys.iterator();
        
        // initialize
        while (iter.hasNext())
        {
            V key = iter.next();
            GraphNode<V> u = graphTable.search(key);
            if (u.getColor() == GraphNode.WHITE)
                depthFirstSearchVisit(u);
        }
    }
    
    void depthFirstSearchVisit(GraphNode<V> u)
    {
        time++;
        
        u.setDiscoveryTime(time);
        u.setColor(GraphNode.GRAY);

        LinkedList<V> nodeList = u.getLinkedNodes();

        int i=1;
        LinkedListNode<V> vName = nodeList.getNThNode(i);
        while (vName != null)
        {
            GraphNode<V> v = graphTable.search(vName.getValue());
            if (v.getColor() == GraphNode.WHITE)
            {
                v.setPredecessor(u.getValue());
                depthFirstSearchVisit(v);
//                    System.out.println(v);
            }

            vName = nodeList.getNThNode(++i);
        }
        
        u.setColor(GraphNode.BLACK);
        time++;
        u.setFinishingTime(time);
    }
    
    void depthFirstSearchVisit(GraphNode<V> u, Hashtable<V, GraphNode<V>> graph, ArrayList<GraphNode<V>> list)
    {
        time++;
        
        u.setDiscoveryTime(time);
        u.setColor(GraphNode.GRAY);

        LinkedList<V> nodeList = u.getLinkedNodes();

        int i=1;
        LinkedListNode<V> vName = nodeList.getNThNode(i);
        while (vName != null)
        {
            GraphNode<V> v = graph.search(vName.getValue());
            if (v.getColor() == GraphNode.WHITE)
            {
                v.setPredecessor(u.getValue());
                depthFirstSearchVisit(v, graph, list);
//                    System.out.println(v);
            }

            vName = nodeList.getNThNode(++i);
        }

        list.add(u);
        u.setColor(GraphNode.BLACK);
        time++;
        u.setFinishingTime(time);
    }    
    
    public ArrayList<ArrayList<GraphNode<V>>> stronglyConnectedComponents()
    {
        ArrayList<ArrayList<GraphNode<V>>> scc = new ArrayList<ArrayList<GraphNode<V>>>();
        
        depthFirstSearch(); // 1st DFS to calculate finishing time 
        
        // prepare graph nodes in the order of finishing time for 2nd DFS, which identifies SCC
        List<GraphNode<V>> listNode = new ArrayList<>();
        List<V> keys = this.graphTable.getKeys();
        
        for (int i=0;i<keys.size();i++)
        {
            listNode.add(this.graphTable.search(keys.get(i)));
        }
        
        listNode.sort(new SortV()); // sort by finishing time
        
        Hashtable<V, GraphNode<V>> transposeGraph = getTransposeOfGraph();
        System.out.println("Transpose of Graph");
        System.out.println(this.toString(transposeGraph) + "\n\n");

        initializeGraphNodesForSearch(transposeGraph);        
//        // second DFS to identify Strongly Connected Components. Visit nodes in the order of finishing time
        int nSCC = 0;
        for (int i=0;i<listNode.size();i++)
        {
            GraphNode<V> u = listNode.get(i); // nodes in listNode is just for visit ordering, but not real nodes
            u = transposeGraph.search(u.getValue()); // now get real node from Transpose graph Gt
            
            if (u.getColor() == GraphNode.WHITE) // at least one node exist, so make a SCC
            {
                ArrayList<GraphNode<V>> list = new ArrayList<>();
                scc.add(list);
                nSCC++;

                System.out.println("Strongly Connected Component " + nSCC);

                depthFirstSearchVisit(u, transposeGraph, list);
            }
        }
        
        return scc;
    }
    
    Hashtable<V, GraphNode<V>> getTransposeOfGraph()
    {
        Hashtable<V, GraphNode<V>> graphReverse = new Hashtable<>();
        ArrayList<GraphNode<V>> list = new ArrayList<>();
        
        List<V> keys = this.graphTable.getKeys();
        
        // add nodes to graphReverse
        for (int i=0;i<keys.size();i++)
        {
            GraphNode<V> node = graphTable.search(keys.get(i));
            addNode(node.getValue(), graphReverse);
        }
        
        // add reverse edges to graphReverse
        for (int i=0;i<keys.size();i++)
        {
            GraphNode<V> fromNode = graphTable.search(keys.get(i));
            LinkedList<V> linkedListConnectedNodes = fromNode.getLinkedNodes();
            
            int j=1;
            LinkedListNode<V> toNode = linkedListConnectedNodes.getNThNode(j);
            while (toNode != null)
            {
                connect(toNode.getValue(), fromNode.getValue(), graphReverse); // there's edge from node to vName. add reverse edge from vName to node
                toNode = linkedListConnectedNodes.getNThNode(++j);
            }
        }
        
        return graphReverse;
    }
    
    public void topologicalSort()
    {
        depthFirstSearch(); // DFS to calculate finishing time for topological sort
        
        List<GraphNode<V>> listNode = new ArrayList<>();
        List<V> keys = this.graphTable.getKeys();
        
        for (int i=0;i<keys.size();i++)
        {
            listNode.add(this.graphTable.search(keys.get(i)));
        }
        
        listNode.sort(new SortV()); // sort by finishing time
        
        Queue<GraphNode<V>> queue = new Queue<>();
        for (int i=0;i<listNode.size();i++)
            queue.enqueue(listNode.get(i));
//            System.out.println(i+1 + " " + listNode.get(i));

        System.out.println(queue);
    }
    
    // Comparator to sort by finishing time of GraphNode
    class SortV implements Comparator<GraphNode<V>>
    {
        public int compare(GraphNode<V> a, GraphNode<V> b)
        {
            return b.getFinishingTime() - a.getFinishingTime();
        }
    }
    
    public static void main(String[] args) {
        AdjacentListGraph<String> graph = new AdjacentListGraph<>();

        // test for Strongly Connected Component
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");
        graph.addNode("f");
        graph.addNode("g");
        graph.addNode("h");
//
        graph.connect("a", "b");
        
        graph.connect("b", "c");
        graph.connect("b", "e");
        graph.connect("b", "f");
        
        graph.connect("c", "d");
        graph.connect("c", "g");
        
        graph.connect("d", "c");
        graph.connect("d", "h");
        
        graph.connect("e", "a");
        graph.connect("e", "f");
        
        graph.connect("f", "g");
        
        graph.connect("g", "f");
        graph.connect("g", "h");
        
        graph.connect("h", "h");
        
        
        
        ArrayList<ArrayList<GraphNode<String>>> scc = graph.stronglyConnectedComponents();
        System.out.println(scc);
        // Test for DFS
//        graph.addNode("u");
//        graph.addNode("v");
//        graph.addNode("w");
//        graph.addNode("x");
//        graph.addNode("y");
//        graph.addNode("z");
//
//        graph.connect("u", "v");
//        graph.connect("u", "x");
//        
//        graph.connect("v", "y");
//        
//        graph.connect("w", "y");
//        graph.connect("w", "z");
//        
//        graph.connect("x", "v");
//        graph.connect("y", "x");
//        graph.connect("z", "z");
//
//        graph.depthFirstSearch();
//        
//        System.out.println("After DFS: " + graph+"\n\n");
    

//  test for Topological Sort
//        graph.addNode("shirt");
//        graph.addNode("tie");
//        graph.addNode("jacket");
//        graph.addNode("belt");
//        graph.addNode("watch");     
//        graph.addNode("undershorts");
//        graph.addNode("pants");
//   
//        graph.addNode("shoes");
//        graph.addNode("socks");
//
//        graph.connect("undershorts", "pants");
//        graph.connect("undershorts", "shoes");
//        
//        graph.connect("pants", "shoes");
//        graph.connect("pants", "belt");
//
//        graph.connect("belt", "jacket");
//        
//        graph.connect("shirt", "belt");
//        graph.connect("shirt", "tie");
//        
//        graph.connect("tie", "jacket");
//        
//        graph.connect("socks", "shoes");
//        
//        
//        graph.topologicalSort();


// set up for BFS        
//        graph.addNode("r");
//        graph.addNode("s");
//        graph.addNode("t");
//        graph.addNode("u");
//        graph.addNode("v");
//        graph.addNode("w");
//        graph.addNode("x");
//        graph.addNode("y");
////        graph.addNode("z");
//        
//        graph.connectBothDirections("s", "r");
//        graph.connectBothDirections("s", "w");
//        
//        graph.connectBothDirections("r", "v");
//        
//        graph.connectBothDirections("w", "t");
//        graph.connectBothDirections("w", "x");
//        
//        graph.connectBothDirections("t", "x");
//        graph.connectBothDirections("t", "u");
//        
//        graph.connectBothDirections("u", "x");
//        graph.connectBothDirections("u", "y");
//        
//        graph.connectBothDirections("x", "y");
//        
//        System.out.println(graph);
//        
//        graph.breadthFirstSearch("s");
        
    }
}
