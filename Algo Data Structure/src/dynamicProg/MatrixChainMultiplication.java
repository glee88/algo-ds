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
public class MatrixChainMultiplication {
    // implementation of MATRIX-CHAIN-ORDER on p.375 from Cormen book 2nd ed.
    public static int[][][] matrixChainOrder(int[] p)
    {
        int n = p.length-1;
        int[][] m = new int[n+1][n+1]; // use [1..n][1..n]
        int[][] s = new int[n+1][n+1]; // use [1..n][2..n]
        
        // not really necessary in Java
        for (int i=0;i<=n;i++)
            m[i][i] = 0;
        
        // m[i,j] = min i<=k<j ( m[i, k] + m[k+1, j] + p[i-1]*p[k]*p[j])
        
        for (int l = 2;l<=n;l++) // l is chain length: 2..n
        {
            for (int i=1;i<=n-l+1;i++) // i of m[i, j]
            {
                int j = i + l - 1; // calculate j
                m[i][j] = Integer.MAX_VALUE;
                for (int k=i;k<j;k++) // i <= k < j
                {
                    int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j]) // found a better chain
                    {
                        m[i][j] = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                        s[i][j] = k;
                    }
                }
            }
        }
        int[][][] solutions = new int[2][][];
        solutions[0] = m;
        solutions[1] = s;
        return solutions;
    }
    
    public static String printOptimalParenthesis(int[][] s, int i, int j)
    {
        String sOut = "";
        
        if (i == j)
            sOut = "A" + i;
        else{
            sOut = "(";
            sOut += printOptimalParenthesis(s, i, s[i][j]); // i..k: s[i,k] is k
            sOut += printOptimalParenthesis(s, s[i][j]+1, j); // k+1..j: s[i,k] is k
            sOut += ")";
        }
        
        return sOut;
    }
    
    public static void main(String[] args) {
        // example from Fig. 15.5 on p.376 from Cormen book 2nd ed
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        
        int[][][] results = matrixChainOrder(p);
        System.out.println("m: " + Arrays.deepToString(results[0]));
        System.out.println("s: " + Arrays.deepToString(results[1]));
        
        System.out.println(printOptimalParenthesis(results[1], 2, 5));
        System.out.println(printOptimalParenthesis(results[1], 4, 5));
        System.out.println(printOptimalParenthesis(results[1], 1, 6));
    }
}
