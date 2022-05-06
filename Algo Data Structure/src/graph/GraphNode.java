/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import linkedLists.LinkedList;

/**
 *
 * @author glee
 */
//public class GraphNode<V extends Comparable<V>> implements Comparable<V> {
public class GraphNode<V> {

    V value;
    V predecessor;
    int color = WHITE;
    public final static int WHITE = 0, GRAY = 1, BLACK = 2;
    int discoveryTime = -1;
    int finishingTime = -1;
    LinkedList<V> likedNodes = null; 

    public GraphNode(V value)
    {
        this.value = value;
        this.predecessor = null;
        setColor(WHITE);
        likedNodes = new LinkedList<V>();
    }
    
    public GraphNode(V value, V predecessor, int color)
    {
        this.value = value;
        this.predecessor = predecessor;
        setColor(color);
        likedNodes = new LinkedList<V>();
    }
    
//    @Override
//    public int compareTo(V value)
//    {
//        return this.value.compareTo(value);
//    }
    
    public LinkedList<V> getLinkedNodes()
    {
        return likedNodes;
    }
    
    public V getValue()
    {
        return value;
    }
    
    public int getColor()
    {
        return color;
    }
    
    public V getPredecessor()
    {
        return predecessor;
    }
    
    public int getDiscoveryTime()
    {
        return discoveryTime;
    }
    
    public int getFinishingTime()
    {
        return finishingTime;
    }

    public void setValue(V value)
    {
        this.value = value;
    }
    
    public void setColor(int color)
    {
        if (color >= WHITE && color <= BLACK)
            this.color = color;
    }
    
    public void setPredecessor(V pred)
    {
        predecessor = pred;
    }
    
    public void setDiscoveryTime(int d)
    {
        if (d >= -1)
            discoveryTime = d;
    }
    
    public void setFinishingTime(int f)
    {
        if (f >= -1)
            finishingTime = f;
    }
    
    public boolean sameAs(V value)
    {
        return this.value.equals(value);
    }
    
    @Override
    public String toString()
    {
        String sOut = "";
        String sColor = color == WHITE ? "white" : (color == GRAY ? "gray": "black");
        sOut = value + ", d:" + discoveryTime + ", f: " + finishingTime + ", color: " + sColor + ", LinkedNodes: " + getConnectedNodes();
        return sOut;
    }
    
    public String getConnectedNodes()
    {
        String sOut = "";
        sOut += this.getLinkedNodes();
        return sOut;
    }
}
