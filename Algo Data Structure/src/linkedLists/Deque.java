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
public class Deque<T> {
    private DoublyLinkedList<T> dLL = new DoublyLinkedList<>();
    private int size = 0;
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public T front()
    {
        if (!isEmpty())
            return dLL.getFront();
        else 
            return null;
    }

    public T back()
    {
        if (!isEmpty())
            return dLL.getBack();
        else 
            return null;
    }    
    
    public void insertFront(T value)
    {
        dLL.addFront(value);
        size++;
    }
    
    public void insertBack(T value)
    {
        dLL.addBack(value);
        size++;
    }
    
    public T removeFront()
    {
        if (!isEmpty()) {
            size--;
            return dLL.removeFront();
        } else
            return null;
    }
    
    public T removeBack()
    {
        if (!isEmpty()) {
            size--;
            return dLL.removeBack();
        } else
            return null;
    }
    
    @Override
    public String toString()
    {
        return dLL.toString();
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Deque<String> dQueue = new Deque<>();
System.out.println("Fron, back: " + dQueue.front() + ", " + dQueue.back());        
        System.out.println("Enter string to enqueue (none to finish): ");
        String sValue = input.nextLine();
        
        while (!sValue.equalsIgnoreCase("none"))
        {
            dQueue.insertBack(sValue);
            System.out.println("Enter string to enqueue (none to finish): ");
            sValue = input.nextLine();
        }
        
        System.out.println(dQueue);
        System.out.println("Fron, back: " + dQueue.front() + ", " + dQueue.back());
        
        dQueue.removeFront();
        System.out.println("After dequeue once");
        System.out.println(dQueue);

        dQueue.removeFront();
        System.out.println("After dequeue twice");
        System.out.println(dQueue);
        System.out.println("Fron, back: " + dQueue.front() + ", " + dQueue.back());
        
        dQueue.removeFront();
        System.out.println("After dequeue 3 times");
        System.out.println(dQueue);   
        
        // test as stack
        System.out.println("Enter string to stack (none to finish): ");
        sValue = input.nextLine();
        
        while (!sValue.equalsIgnoreCase("none"))
        {
            dQueue.insertFront(sValue);
            System.out.println("Enter string to stack (none to finish): ");
            sValue = input.nextLine();
        }
        
        System.out.println(dQueue);
        
        dQueue.removeFront();
        System.out.println("After pop once");
        System.out.println(dQueue);

        dQueue.removeFront();
        System.out.println("After pop twice");
        System.out.println(dQueue);

        dQueue.removeFront();
        System.out.println("After pop 3 times");
        System.out.println(dQueue);         
    }
}
