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
public class Heap {
    private int[] array = null;
    private int heapSize = 0;
    
    public Heap(int[] arrayElem)
    {
        array = arrayElem;
        heapSize = arrayElem.length - 1;
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
    
    public void minHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        
        int smallest = i;
        
        if (l <= heapSize && array[l] < array[smallest])
            smallest = l;
        
        if (r <= heapSize && array[r] < array[smallest])
            smallest = r;
        
        // smallest should be at array[i], so swap array[i] and array[smallest]
        if (smallest != i)
        {
            int tmp = array[i];
            array[i] = array[smallest];
            array[smallest] = tmp;
            minHeapify(smallest);
        }
    }
    
    public void maxHeapify(int i)
    {
        int l = left(i);
        int r = right(i);
        int largest = i;
        
        if (l <= heapSize && array[l] > array[i])
            largest = l;
        
        if (r <= heapSize && array[r] > array[largest])
            largest = r;
        
        if (largest != i) // i is not the largest
        {
            int temp = array[i]; // swap array[i] and array[largest]
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapify(largest); // after swapping, max heapify the updated node (one of the child)
        }
    }
    
    public void buildMaxHeap()
    {
        heapSize = heapLength();
        
        for (int i=heapLength()/2;i>=1;i--) // build max heap bottom up starting from nonleaf node
            maxHeapify(i);
    }

    public void buildMinHeap()
    {
        heapSize = heapLength();
        
        for (int i=heapLength()/2;i>=1;i--) // build min heap bottom up starting from nonleaf node
            minHeapify(i);
    }
    
    public void heapSortNonIncreasing()
    {
        buildMinHeap(); // make initial heap. The largest is at array[1]
        
        for (int i=heapSize;i>=2;i--)
        {
            int temp = array[i];
            array[i] = array[1];
            array[1] = temp;
            
            heapSize--;
            minHeapify(1);
        }
    }
    
    public void heapSort()
    {
        buildMaxHeap(); // make initial heap. The largest is at array[1]
        for (int i=heapLength(); i >= 2;i--)
        {
            int temp = array[i]; // swap array[1] (the largest) and array[i]
            array[i] = array[1];
            array[1] = temp;
            
            // element at array[heapSize] is already sorted, so heapSize-- to avoid touching it
            heapSize--; 
            maxHeapify(1); // after swapping, array[1] may not be the largest. So maxheapfiy to have the largest at array[1]
        }
    }
    
    @Override
    public String toString()
    {
        return java.util.Arrays.toString(array);
    }
    
    public static void main(String[] args) {
        int[] numbers = {0, 4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    
        Heap heap = new Heap(numbers);
        heap.heapSortNonIncreasing();
//        heap.heapSort();
        System.out.println(heap);
    }
}
