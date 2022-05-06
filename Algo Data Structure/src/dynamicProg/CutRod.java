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
public class CutRod {
    public static int memoizedCutRod(int[] profits, int n)
    {
        int[] r = new int[n+1]; // s[j] is cut location to get max revenue r[j]. Will use only s[1 .. n]
        for (int i=0;i<r.length;i++)
            r[i] = Integer.MIN_VALUE;
        
        return memoizedCutRodAux(profits, n, r);
    }
    
    public static int memoizedCutRodAux(int[] profits, int n, int[] r)
    {
        if (r[n] >= 0) // if r[n] has been calculated, use the value instead of recalculating it
            return r[n];
        
        int q;
        if (n == 0)
            q = 0;
        else {
            q = Integer.MIN_VALUE;
            for (int i=1;i<=n;i++)
            {
                int val = profits[i] + memoizedCutRodAux(profits, n-i, r);
                if (q < val)
                    q = val;
            }
            r[n] = q;
        }
        return q;
    }
    
    
    public static int[][] extendedBottomUpCutRod(int[] profits, int n)
    {
        int[][] results = new int[2][];
        int[] r = new int[n+1]; // r[j] is max revenue with length j
        int[] s = new int[n+1]; // s[j] is cut size to get max revenue r[j]. Will use only s[1 .. n]
        results[0] = r;
        results[1] = s;
        
        r[0] = 0;
        
        for (int j=1;j<=n;j++)
        {
            int q = Integer.MIN_VALUE;
            for (int i=1;i<=j;i++)
            {
                if (q < profits[i] + r[j - i])
                {
                    q = profits[i] + r[j - i];
                    s[j] = i;
                }
            }
            r[j] = q;
        }
        
        return results;
    }
    
    public static String printCutRodSolution(int[] p, int n)
    {
        int[][] results = extendedBottomUpCutRod(p, n);
        int[] r = results[0];
        int[] s = results[1];
        
        String sOut = "Max profit with length " + n + ": " + r[n] + "\nSlice Sizes: ";
        while (n > 0){
            sOut += s[n] + " ";
            n = n - s[n];
        }
        
        return sOut;
    }
    
    public static void main(String[] args) {
        // example from p.360 Cormen book 2nd ed
        int[] profits = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        
//        int[][] results = extendedBottomUpCutRod(profits, 10);
//        System.out.println(Arrays.deepToString(results));

//        System.out.println(printCutRodSolution(profits, 7));
        
        for (int i=0;i<=10;i++)
            System.out.println(memoizedCutRod(profits, i));
    }
    
}
