/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author glee
 */
public class SetAndPriorityQueueTest {
    public static int inSameSet(int a, int b, ArrayList<HashSet<Integer>> hss)
    {
        for (int i=0;i<hss.size();i++)
            if (hss.get(i).contains(a) && hss.get(i).contains(b))
                return i;
        
        return -1;
    }
    
    public static void main(String[] args) {
        HashSet<Integer> hs1 = new HashSet<>();
        hs1.add(5);
        hs1.add(5);
        hs1.add(2);
        hs1.add(1);
        hs1.add(3);
        hs1.add(3);
        
        System.out.println("hs1: " + hs1);

        HashSet<Integer> hs2 = new HashSet<>();
        hs2.add(5);
        hs2.add(5);
        hs2.add(2);
        hs2.add(1);
        hs2.add(3);
        hs2.add(3);
        hs2.remove(2);
        
        System.out.println("hs2: " + hs2);
        
        ArrayList<HashSet<Integer>> al= new ArrayList<>();
        
        al.add(hs2);
        al.add(hs1);
        
        System.out.println("in same set of "+ inSameSet(5, 2, al));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0;i<10;i++)
            pq.add((int)(Math.random()*20));
        
        for (int i=0;i<5;i++)
            System.out.print(pq.poll() + " ");
        
        System.out.println("");
        for (int i=0;i<10;i++)
            pq.add((int)(Math.random()*20));

        for (int i=0;i<10;i++)
            System.out.print(pq.poll() + " ");        
            
    }
}
