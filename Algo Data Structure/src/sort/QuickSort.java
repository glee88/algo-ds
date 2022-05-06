/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sort;

import java.util.Arrays;

/**
 *
 * @author glee
 */
public class QuickSort {
    public static int partition(int[] a, int p, int r)
    {
        int x = a[r]; // x is pivot
        int i = p-1; // 
        
        for (int j = p;j < r;j++)
        {
            if (a[j] < x) // a[j] smaller than pivot to be placed at a[++i]
            {
                i++;
                // swap a[i] and a[j]
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        
        // swap a[i+1] and a[r] so that updated a[i+1] has the pivot element
        a[r] = a[i+1];
        a[i+1] = x;
        
        return i+1;
    }
    
    public static int randomizedPartition(int[] a, int p, int r)
    {
        int pivot = (int)(Math.random()*(r-p+1) + p);
        
        // swap a[pivot] and p[r] so that pivot exists at a[r]
        int tmp = a[pivot];
        a[pivot] = a[r];
        a[r] = tmp;
        
        int x = a[r];
        int i = p - 1;
        
        for (int j = p;j < r;j++)
        {
            if (a[j] < x) // a[j] is less than pivot element x. Send a[j] in the front part
            {
                i++; 
                // swap a[i] and a[j]
                if (i != j)
                {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        
        // swap a[i+1] and a[r], which is x, so that pivot element a[r] is located at a[i+1]
        a[r] = a[i+1];
        a[i+1] = x;
        
        return i+1;
    }
    
    public static void quickSort(int[] a, int p, int r)
    {
        if (p < r)
        {
            int q = randomizedPartition(a, p, r); // a[q] is in right place
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }
    
    public static boolean isSorted(int[] a)
    {
        for (int i=0;i<a.length-1;i++)
            if (a[i] > a[i+1])
                return false;
        return true;
    }
    
    public static void main(String[] args) {
        int[] numbers = new int[100];
        for (int i=0;i<numbers.length;i++)
            numbers[i] = (int)(Math.random() * 100000);
        
        quickSort(numbers, 0, numbers.length-1);
        
        System.out.println(Arrays.toString(numbers));
        System.out.println(isSorted(numbers) ? true:false);
    }
}
