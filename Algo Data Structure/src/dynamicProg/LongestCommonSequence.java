/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dynamicProg;

/**
 *
 * @author glee
 */
public class LongestCommonSequence {
    final static int UP = 0, LEFT = 1, DIAG = 2;
    
    public static String LCS(String x, String y)
    {
        char[] lcs = null;
        int m = x.length(), n = y.length();
        
        x = " " + x; // add buffer
        y = " " + y; 
       
        int[][] c = new int[m+1][n+1];
        int[][] b = new int[m+1][n+1];
        for (int i=0;i<m;i++)
            c[i][0] = 0;
        
        for (int i=0;i<n;i++)
            c[0][i] = 0;
        
        // if xi == yj, c[i, j] = c[i-1, j-1] + 1
        // else if c[i-1, j] > c[i, j-1], c[i, j] = c[i-1, j]
        // else c[i, j] = c[i, j - 1]
        int lcsLength = 0;
        for (int i=1;i<=m;i++)
            for (int j=1;j<=n;j++)
            {
                if (x.charAt(i) == y.charAt(j)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = DIAG;
                    lcsLength++;
                } else if (c[i][j-1] > c[i-1][j])
                {
                    c[i][j] = c[i][j-1];
                    b[i][j] = LEFT;
                } else 
                {
                    c[i][j] = c[i-1][j];
                    b[i][j] = UP;
                }
            }
        
        return printLCS(x, b, m, n);
    }
    
    public static String printLCS(String x, int[][] b, int i, int j)
    {
        if (i == 0 || j == 0)
            return "";

        String sOut = "";
        if (b[i][j] == DIAG)
        {
            sOut += printLCS(x, b, i-1, j-1);
            sOut += x.charAt(i)+"";
        } else if (b[i][j] == UP)
        {
            sOut += printLCS(x, b, i-1, j);
        } else { // LEFT
            sOut += printLCS(x, b, i, j- 1);
        }
        
        return sOut;
    }
    
    public static void main(String[] args) {
//        String x = "ABCBDAB", y = "BDCABA";
        String x = "AGGTAB", y = "GXTXAYB";
        System.out.println(LCS(x, y));
    }
}
