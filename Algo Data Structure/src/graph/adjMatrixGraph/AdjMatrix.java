/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph.adjMatrixGraph;

import java.util.Arrays;

/**
 *
 * @author glee
 */
public class AdjMatrix {
    private double[][] adjMatrix = null;
    private int[][] predecessorMatrix = null;
    
    public AdjMatrix(int nVertices)
    {
        adjMatrix = new double[nVertices][nVertices];
        for (int i=0;i<nVertices;i++)
            for (int j=0;j<nVertices;j++)
            {
                if (i == j)
                    adjMatrix[i][j] = 0;
                else 
                    adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        
        predecessorMatrix = new int[nVertices][nVertices];
        for (int i=0;i<nVertices;i++)
            for (int j=0;j<nVertices;j++)
                predecessorMatrix[i][j] = -1;
    }
    
    public AdjMatrix(double[][] adjMatrix)
    {
        if (adjMatrix == null)
            return;
        
        if (adjMatrix.length < 1 || adjMatrix.length != adjMatrix[0].length)
            return;
        
        this.adjMatrix = adjMatrix;
        
        initializePredecessorMatrix();
    }
    
    public void updateMatrix(double[][] adjMatrix)
    {
        this.adjMatrix = adjMatrix;
    }
    
    void initializePredecessorMatrix()
    {
        predecessorMatrix = new int[adjMatrix.length][adjMatrix.length];
        
        for (int i=0;i<getSize();i++)
        {
            for (int j=0;j<getSize();j++)
            if (adjMatrix[i][j] == Integer.MAX_VALUE || i == j) // disconnected
                predecessorMatrix[i][j] = 0;
            else // connected
                predecessorMatrix[i][j] = i; // on path from i to j, predecessor of path(i,j) is i (since there's an edge btw i and j
        }
    }
    
    public int getSize()
    {
        if (adjMatrix != null)
            return adjMatrix.length;
        else 
            return 0;
    }
    
    boolean isSafe(int i)
    {
        return i >= 0 && i < getSize();
    }
    
    public void connectVertices(int i, int j, double weight)
    {
        if (isSafe(i) && isSafe(j))
        {
            adjMatrix[i][j] = weight;
            predecessorMatrix[i][j] = i;
        }
    }
    
    public void disconnectVertices(int i, int j)
    {
        if (isSafe(i) && isSafe(j))
        {
            adjMatrix[i][j] = Integer.MAX_VALUE;
            predecessorMatrix[i][j] = -1;
        }
    }
    
    public double[][] getAdjMatrix()
    {
        double[][] adjMatrixCopy = new double[adjMatrix.length][];
        
        for (int i=0;i<adjMatrix.length;i++)
            adjMatrixCopy[i] = adjMatrix[i].clone();
        
        return adjMatrixCopy;
    }
    
    public int[][] getPredecessorMatrix()
    {
        int[][] predMatrixCopy = new int[predecessorMatrix.length][];
        
        for (int i=0;i<predecessorMatrix.length;i++)
            predMatrixCopy[i] = predecessorMatrix[i].clone();
        
        return predMatrixCopy;
    }
    
    public void setPredecessor(int i, int j, int predecessor)
    {
        if (isSafe(i) && isSafe(j) && isSafe(predecessor))
            predecessorMatrix[i][j] = predecessor;
    }
    
    public int getPredecessor(int i, int j)
    {
        return predecessorMatrix[i][j];
    }
    
    public String printMatrices()
    {
        String sOut = "Adjacent Matrix \n[";
        for (int i=0;i<getSize();i++)
        {
            sOut += Arrays.toString(adjMatrix[i]) + "\n";
        }
        
        sOut += "] \nPredecessor Matrix\n";
        for (int i=0;i<getSize();i++)
        {
            sOut += Arrays.toString(predecessorMatrix[i]) + "\n";
        }
        
        sOut += "] \n\n\n";
        return sOut;
    }
    
    public String getPath(int i, int j)
    {
        String sPath = "";
        if (i == j)
            sPath = i + "";
        else if (predecessorMatrix[i][j] < 0)
            sPath = "No path from " + i + " to " + j;
        else
        {
            int predOfJ = predecessorMatrix[i][j];
            sPath += getPath(i, predOfJ);
            sPath += " -> " + j;
        }
        
        return sPath;
    }    
    
    public boolean isConnected(int i, int j)
    {
        return adjMatrix[i][j] < Double.POSITIVE_INFINITY;
    }
    
    public static String printMatrices(double[][] adjMatrix, int[][] predecessorMatrix)
    {
        int n = adjMatrix.length;
        
        String sOut = "Adjacent Matrix \n[";
        for (int i=0;i<n;i++)
        {
            sOut += Arrays.toString(adjMatrix[i]) + "\n";
        }
        
        sOut += "]\nPredecessor Matrix\n";
        for (int i=0;i<n;i++)
        {
            sOut += Arrays.toString(predecessorMatrix[i]) + "\n";
        }
        
        sOut += "] \n\n\n";
        return sOut;        
    }
}
