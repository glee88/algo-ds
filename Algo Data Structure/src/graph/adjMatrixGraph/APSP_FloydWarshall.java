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
public class APSP_FloydWarshall {
    AdjMatrix adjMatrix = null;
    
    public APSP_FloydWarshall(double[][] adjMatrix)
    {
        this.adjMatrix = new AdjMatrix(adjMatrix);
    }
    
    public APSP_FloydWarshall(int nVertices)
    {
        this.adjMatrix = new AdjMatrix(nVertices);
    }
    
    int getNumberOfVertices()
    {
        return this.adjMatrix.getSize();
    }
    
    void connectVertices(int i, int j, int w)
    {
        adjMatrix.connectVertices(i, j, w);
    }
    
    public void findAllPairsShortestPath()
    {
        if (adjMatrix == null)
            return;
        
        double[][] D0 = adjMatrix.getAdjMatrix(); // get a copy of adj matrix W
        int n = getNumberOfVertices();
        
        double[][] Dk_1 = D0;
        for (int k=0;k<n;k++)
        {
            double[][] Dk = new double[n][n];
            for (int i=0;i<n;i++)
                for (int j=0;j<n;j++)
                {
                    if (Dk_1[i][j] > Dk_1[i][k] + Dk_1[k][j]) // adding vertex k is shorter path from i to j
                    {
                        Dk[i][j] = Dk_1[i][k] + Dk_1[k][j];
                        int predecessor = adjMatrix.getPredecessor(k, j); // since k is now in the shortest path from i to j, pred(k, j) should be pred of j
                        adjMatrix.setPredecessor(i, j, predecessor);
                    } else
                        Dk[i][j] = Dk_1[i][j];
                }

            Dk_1 = Dk;
            
//            System.out.println("k = " + k  + AdjMatrix.printMatrices(Dk_1, adjMatrix.getPredecessorMatrix()));
        }
        adjMatrix.updateMatrix(Dk_1); // predecessor is updated in the loop
    }
    
    public String getPath(int i, int j)
    {
        return adjMatrix.getPath(i, j);
    }
    
    public boolean[][] getTransitiveClosure()
    {
        int n = adjMatrix.getSize();
        
        boolean[][] T0 = new boolean [n][n];
        
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                if (i == j || adjMatrix.isConnected(i, j))
                    T0[i][j] = true;
                else 
                    T0[i][j] = false;
        
        boolean[][] Tk_1 = T0;
        for (int k = 0;k < n;k++)
        {
//            System.out.println("k=" + k + "\n" + Arrays.deepToString(Tk_1));
            boolean[][] Tk = new boolean[n][n];
            for (int i=0;i<n;i++)
                for (int j=0;j<n;j++)
                    Tk[i][j] = Tk_1[i][j] || (Tk_1[i][k] && Tk_1[k][j]);
            
            Tk_1 = Tk;
        }
//        System.out.println("k=" + (n-1) + "\n" + Arrays.deepToString(Tk_1));
        return Tk_1;
    }
    
    public static void main(String[] args) {
        // APSP test. On p.696 Cormen book
        APSP_FloydWarshall fw = new APSP_FloydWarshall(5);
        fw.connectVertices(0, 1, 3);
        fw.connectVertices(0, 2, 8);
        fw.connectVertices(0, 4, -4);
        fw.connectVertices(1, 3, 1);
        fw.connectVertices(1, 4, 7);
        fw.connectVertices(2, 1, 4);
        fw.connectVertices(3, 0, 2);
        fw.connectVertices(3, 2, -5);
        fw.connectVertices(4, 3, 6);
        
        System.out.println(fw.adjMatrix.printMatrices());
        
        fw.findAllPairsShortestPath();
        System.out.println("Result: " + fw.adjMatrix.printMatrices());
        
        // Transitive Closure Test
//        APSP_FloydWarshall fw2 = new APSP_FloydWarshall(4);
//        fw2.connectVertices(1, 2, 1);
//        fw2.connectVertices(1, 3, 1);
//        fw2.connectVertices(2, 1, 1);
//        fw2.connectVertices(3, 0, 1);
//        fw2.connectVertices(3, 2, 1);
//        System.out.println(fw2.adjMatrix.printMatrices());
//        
//        System.out.println(Arrays.deepToString(fw2.getTransitiveClosure()));
        
        // print paths for all pairs
        for (int i=0;i<5;i++)
            for (int j=0;j<5;j++)
                if (i != j)
                    System.out.println(fw.adjMatrix.getPath(i, j));
        
    }
}
