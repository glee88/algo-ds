/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLists;

import java.util.Scanner;

/**
 *
 * @author glee
 */
public class LinkedQueue<T> {
    private CircleList<T> cList = new CircleList<>();
    private int n = 0; // size
    
    public boolean isEmpty()
    {
        return n == 0;
    }
    
    public T front()
    {
        if (!isEmpty())
            return cList.front();
        else 
            return null;
    }
    
    public void enqueue(T value)
    {
        cList.add(value);
        cList.advance();
        n++;
    }
    
    public void dequeue()
    {
        if (isEmpty()) 
        {
            System.out.println("Dequeue of empty queue");
            return;
        }
        
        cList.remove();
        n--;
    }
    
    @Override
    public String toString()
    {
        return cList.toString();
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        LinkedQueue<String> lQueue = new LinkedQueue<>();
        
        System.out.println("Enter string to enqueue (none to finish): ");
        String sValue = input.nextLine();
        
        while (!sValue.equalsIgnoreCase("none"))
        {
            lQueue.enqueue(sValue);
            System.out.println("Enter string to enqueue (none to finish): ");
            sValue = input.nextLine();
        }
        
        System.out.println(lQueue);
        
        lQueue.dequeue();
        System.out.println("After dequeue once");
        System.out.println(lQueue);

        lQueue.dequeue();
        System.out.println("After dequeue twice");
        System.out.println(lQueue);

        lQueue.dequeue();
        System.out.println("After dequeue 3 times");
        System.out.println(lQueue);
    }
}
