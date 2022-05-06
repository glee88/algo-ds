/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

/**
 *
 * @author glee
 */
public class MaxPriorityQueue<T extends Comparable> {
    private T[] array = null;
    private int heapSize = 0;
    
    public MaxPriorityQueue(T[] arrayElem)
    {
        array = arrayElem;
        heapSize = 0;
    }
    
    public int heapLength()
    {
        return array.length - 1;
    }
    
    public static int left(int i)
    {
        return i * 2;
    }

    public static int right(int i)
    {
        return i * 2 + 1;
    }
    
    public static int parent(int i)
    {
        return i / 2;
    }    

    public void maxHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int largest = i;
        
        if (l <= heapSize && array[l].compareTo(array[i]) > 0)
            largest = l;
        
        if (r <= heapSize && array[r].compareTo(array[largest]) > 0)
            largest = r;
        
        if (largest != i) // i is not the largest
        {
            T temp = array[i]; // swap array[i] and array[largest]
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapify(largest); // after swapping, max heapify the updated node (one of the child)
        }
    }
    
    public T heapMaximum()
    {
        return array[1];
    }
    
    public T heapExtractMaximum()
    {
        T max = array[1];
        
        array[1] = array[heapSize];
        heapSize--;
        maxHeapify(1);
        return max;
    }
    
    public void insert(T x)
    {
        if (heapSize >= heapLength())
        {
            System.out.println("Heap overflow. Insertion failed");
            return;
        }
        heapSize++;
        array[heapSize] = x;
        
        int i = heapSize;
        int par = parent(i);
        while(i > 1 && array[i].compareTo(array[par]) > 0)
        {
            // child at i is bigger than parent. So exchange
            T temp = array[par];
            array[par] = array[i];
            array[i] = temp;
            
            // move up and work
            i = parent(i);
            par = parent(i);
        }
    }
    
    public static void main(String[] args) {
        String[] rgiNums = new String[20];
        MaxPriorityQueue<String> mpq = new MaxPriorityQueue<>(rgiNums);
        
        mpq.insert("hello");
        System.out.println("Max: " + mpq.heapMaximum());
        mpq.insert("world");
        System.out.println("Max: " + mpq.heapMaximum());
        mpq.insert("sc");
        System.out.println("Max: " + mpq.heapMaximum());
        mpq.insert("is");
        System.out.println("Max: " + mpq.heapMaximum());
        mpq.insert("beautiful");
        System.out.println("Max: " + mpq.heapMaximum());
        
        System.out.println("Extracted: " + mpq.heapExtractMaximum());
        System.out.println("Max: " + mpq.heapMaximum());
        
        System.out.println("Extracted: " + mpq.heapExtractMaximum());
        System.out.println("Max: " + mpq.heapMaximum());
        
        System.out.println("Extracted: " + mpq.heapExtractMaximum());
        System.out.println("Max: " + mpq.heapMaximum());
        
        System.out.println("Extracted: " + mpq.heapExtractMaximum());
        System.out.println("Max: " + mpq.heapMaximum());

        System.out.println("Extracted: " + mpq.heapExtractMaximum());
        System.out.println("Max: " + mpq.heapMaximum());

        System.out.println("Extracted: " + mpq.heapExtractMaximum());
        System.out.println("Max: " + mpq.heapMaximum());
        
        
//        Integer[] rgiNums = new Integer[20];
//        MaxPriorityQueue<Integer> mpq = new MaxPriorityQueue<>(rgiNums);
//        
//        mpq.insert(1);
//        System.out.println("Max: " + mpq.heapMaximum());
//        mpq.insert(3);
//        System.out.println("Max: " + mpq.heapMaximum());
//        mpq.insert(2);
//        System.out.println("Max: " + mpq.heapMaximum());
//        mpq.insert(5);
//        System.out.println("Max: " + mpq.heapMaximum());
//        mpq.insert(7);
//        System.out.println("Max: " + mpq.heapMaximum());
//        
//        System.out.println("Extracted: " + mpq.heapExtractMaximum());
//        System.out.println("Max: " + mpq.heapMaximum());
//        
//        System.out.println("Extracted: " + mpq.heapExtractMaximum());
//        System.out.println("Max: " + mpq.heapMaximum());
//        
//        System.out.println("Extracted: " + mpq.heapExtractMaximum());
//        System.out.println("Max: " + mpq.heapMaximum());
//        
//        System.out.println("Extracted: " + mpq.heapExtractMaximum());
//        System.out.println("Max: " + mpq.heapMaximum());
//
//        System.out.println("Extracted: " + mpq.heapExtractMaximum());
//        System.out.println("Max: " + mpq.heapMaximum());
//
//        System.out.println("Extracted: " + mpq.heapExtractMaximum());
//        System.out.println("Max: " + mpq.heapMaximum());
        
    }
}
