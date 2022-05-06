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
public class CircleList<T> {
    private LinkedListNode<T> cursor = null;
    
    public boolean isEmpty()
    {
        return cursor == null;
    }
    
    public T front()
    {
        if (!isEmpty())
            return cursor.getNext().getValue();
        else
            return null;
    }
    
    public T back() 
    {
        if (!isEmpty())
            return cursor.getValue();
        else
            return null;
    }
    
    public void advance()
    {
        if (!isEmpty())
            cursor = cursor.getNext();
    }
    
    public void add(T value)
    {
        LinkedListNode<T> node = new LinkedListNode<>(value, null);
        
        if (isEmpty())
        {
            cursor = node;
            cursor.setNext(cursor);
        } else
        {
            node.setNext(cursor.getNext());
            cursor.setNext(node);
        }
    }
    
    public void remove()
    {
        LinkedListNode<T> toRemove = cursor.getNext();
        // remove node after cursor
        if (cursor == toRemove)
            cursor = null;
        else
            cursor.setNext(toRemove.getNext());
    }
    
    @Override
    public String toString()
    {
        if (isEmpty())
            return "[Empty List]";
        
        String sOut = "[";
        LinkedListNode<T> current = cursor.getNext();
        LinkedListNode<T> start = current;
        sOut += current + " -> ";
        current = current.getNext();
        
        while (current != start) // came back to cursor
        {
            sOut += current + " -> ";
            current = current.getNext();
        }
        
        sOut += "]";
        
        return sOut;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        CircleList<String> cList = new CircleList<>();
        
        System.out.println("Enter string to add (none to finish): ");
        String sValue = input.nextLine();
        
        while (!sValue.equalsIgnoreCase("none"))
        {
            cList.add(sValue);
            System.out.println("Enter string to add (none to finish): ");
            sValue = input.nextLine();
        }
        
        System.out.println(cList);
        
        cList.advance();
        cList.advance();
        System.out.println("After advance twice");
        System.out.println(cList);
        
        cList.remove();
        System.out.println("After remove once");
        System.out.println(cList);
        
        cList.remove();
        System.out.println("After remove twice");
        System.out.println(cList);

        cList.remove();
        System.out.println("After remove 3 times");
        System.out.println(cList);
    }
}
