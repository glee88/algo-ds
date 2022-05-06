/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Arrays;

/**
 *
 * @author glee
 */
public class QuickSortPractice {
    static void quickSort(int[] nums, int start, int end)
    {
        if (start > end)
            return;
                    
        int indexPartition = partition(nums, start, end);
        quickSort(nums, start, indexPartition - 1);
        quickSort(nums, indexPartition + 1, end);
    }
    
    static int partition(int[] nums, int start, int end)
    {
        int i=start-1;
        int indexPivot = (int)(Math.random() * (end - start + 1) + start);
        
        // pick pivot and move pivot to end
        int pivot = nums[indexPivot];
        nums[indexPivot] = nums[end];
        nums[end] = pivot;
        
        for (int j=start;j<end;j++)
        {
            if (nums[j]<pivot)
            {
                //swap nums[i++] and nums[j]
                i++;
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
        }
        
        // swap pivot with nums[i++]
        i++;
        nums[end] = nums[i];
        nums[i] = pivot;
        return i;
    }    
    
    public static void main(String[] args) {
        int[] numbers = new int[100];
        
        for (int i=0;i<numbers.length;i++)
            numbers[i] = (int)(Math.random() * 100);
        
        quickSort(numbers, 0, numbers.length-1);
        
        System.out.println(Arrays.toString(numbers));
    }
}
