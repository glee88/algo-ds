/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dynamicProg;

import java.util.Arrays;

/**
 *
 * @author glee
 */
public class OptimalBinarySearchTree {
    final static int LEFT = 0, RIGHT = 1;
    
    public static Object[] findOptimalBinarySearchTree(double[] p, double[] q)
    {
        int n = p.length; // n is # of keys, i.e. tree nodes
        double[] pOriginal = p;
        p = new double[n+1];
        System.arraycopy(pOriginal, 0, p, 1, n); // p now has values in index of 1..n  with p[0] = 0
//        System.out.println(Arrays.toString(p));

        // w(i,j) = S l:i->j p[l] + S l:i-1->j q[l] = w(i, j-1) + p[j] + q[j]
        //        = w(i, r-1) + p[r] + w(r+1, j)
        // e[i, j] = e[i, r-1] + e[r+1, j] + w(i, r-1) + w(r+1, j) + p[r]
        //         = min i<=r<=j {e[i, r-1] + e[r+1, j] + w(i, j)}, if i >= j
        //     Or, = q[i-1], if j = i-1
        double[][] e = new double[n+2][n+1], // e[1..n+1, 0..n], w[1..n+1, 0..n]
                   w = new double[n+2][n+1];
        int[][] root = new int[n+1][n+1];
        
        for (int i=1;i<n+1;i++) {
            e[i][i-1] = q[i-1]; 
            w[i][i-1] = q[i-1];
        }
        
        for (int l = 1;l <= n; l++)
            for (int i=1;i<=n-l+1;i++)
            {
                int j = i + l - 1;
                e[i][j] = Double.MAX_VALUE; // infinity
                w[i][j] = w[i][j-1] + p[j] + q[j];
                for (int r=i;r<=j;r++)
                {
                    double t = e[i][r-1] + e[r+1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
                
            }
        
        Object[] toReturn = new Object[2];
        toReturn[0] = e;
        toReturn[1] = root;
        
        return toReturn;
    }
    
    public static String printOptimalBST(int[][] roots, int i, int j)
    {
        int n = roots.length - 1; // roots is int[n+1][n+1]
        
        if (i > j)
            return "";

        String sOut = "";
        
        if (i == j)
            sOut += i;
        else { // i < j
            sOut += "(" + printOptimalBST(roots, i, roots[i][j] - 1) + ")"; // left subtree
            sOut +=  " " + roots[i][j] + " ";                               // the node
            if (roots[i][j] + 1 <= j) // if right subtree isn't empty
                sOut += "(" + printOptimalBST(roots, roots[i][j] + 1, j) + ")"; // right subtree
        }
        return sOut;
    }
    
    // solution to 15.5.1 on p.403 from Cormen book 3rd ed
    public static String constructOptimalBST(int[][] roots)
    {
        return printOptimalBSTStructure(roots, 1, roots.length-1, -1, LEFT);
    }
    
    public static String printOptimalBSTStructure(int[][] roots, int i, int j, int k, int leftOrRight)
    {
        int n = roots.length - 1; // roots is int[n+1][n+1]
        String submessage = " is " + (leftOrRight == RIGHT ? " right " : " left ") + " child of " + "k" + k ;
        if (i > j)
            return "d" + j + submessage + "\n";

        String sOut = "";

        // visit nodes in PreOrder
        
        // string for the node
        if (k == -1)
            sOut += "k" + roots[i][j] + " is the root.\n";
        else 
            sOut += "k" + roots[i][j] + submessage + "\n";

        // string for left node
        sOut += printOptimalBSTStructure(roots, i, roots[i][j] - 1, roots[i][j], LEFT);

        // string for right node
        sOut += printOptimalBSTStructure(roots, roots[i][j] + 1, j, roots[i][j], RIGHT);

        return sOut;
    }    
    
    public static void main(String[] args) {
        // example from p.399 from Cormen book 3rd ed
//        Object[] returned = findOptimalBinarySearchTree(     new double[]{.15, .1, .05, .1, .2}, 
//                                                        new double[]{.05, .1, .05, .05, .05, .1});
        
        // 15.5-2 from p.404 from Cormen book 3rd ed
        Object[] returned = findOptimalBinarySearchTree(     new double[]{.04, .06, .08, .02, .1, .12, .14}, 
                                                        new double[]{.06, .06, .06, .06, .05, .05, .05, .05});
        double[][] e = (double[][]) returned[0];
        int[][] roots = (int[][]) returned[1];
        
        System.out.println(Arrays.deepToString(roots));
        System.out.println(printOptimalBST(roots, 1, roots.length-1));
        System.out.println(constructOptimalBST(roots));
    }
}
