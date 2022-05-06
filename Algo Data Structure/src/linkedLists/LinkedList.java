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
public class LinkedList<T> {
    private LinkedListNode<T> head = null;
    private LinkedListNode<T> tail = null;
    
    public LinkedList()
    {
    }
    
    public void addBack(T value)
    {
        LinkedListNode<T> node = new LinkedListNode<T>(value, null);
        
        if (tail == null)
            head = tail = node;
        else 
        {
            tail.setNext(node);
            tail = node;
        }
    }
    
    public void addFront(T value)
    {
        LinkedListNode<T> node = new LinkedListNode<T>(value, null);
        
        if (head == null)
            head = tail = node;
        else 
        {
            node.setNext(head);
            head = node;
        }
    }
    
    public T removeFront()
    {
        if (head == null)
            return null;
        
        T val = head.getValue();
        head = head.getNext();
        
        return val;
    }
    
    public LinkedListNode<T> search(T value)
    {
        LinkedListNode<T> current = head;
        while (current != null)
        {
            if (current.getValue().equals(value))
                break;
            current = current.getNext();
        }
        
        return current;
    }
    
    public boolean delete(T value)
    {
        LinkedListNode<T> current = head, prev = null;
        while (current != null)
        {
            if (current.getValue().equals(value))
            {   // found node to remove
                if (prev == null) // head is to be removed
                {
                    head = current.getNext();
                } else
                {
                    prev.setNext(current.getNext());
                }
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        
        return false; // hasn't found a node with value
    }
    
    @Override
    public String toString() 
    {
        LinkedListNode current = head;
        String sOut = "";
        while (current != null)
        {
            sOut += current + " -> ";
            current = current.getNext();
        }
        
        return sOut;
    }
    
    public LinkedListNode getNThNode(int n)
    {
        LinkedListNode current = head;
        for (int i = 0;i < n && current != null; i++)
        {
            current = current.getNext();
        }
        
        return current;
    }
    
    public LinkedList<T> reverse()
    {
        LinkedList<T> list = new LinkedList<T>();
        
        LinkedListNode<T> current = head;
        
        while (current != null)
        {
            list.addFront(current.getValue());
            current = current.getNext();
        }
        
        return list;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        LinkedList<Integer> linkedList = new LinkedList();

        System.out.println("Enter values (None) to finish: ");
//        String sValue = input.nextLine();
//        
//        while (!sValue.equalsIgnoreCase("None"))
//        {
//            LinkedListNode<String> node = new LinkedListNode<String>(sValue, null);
//            linkedList.add(node);
//            System.out.print("Enter values (None) to finish: ");
//            sValue = input.nextLine();
//        }
//        
//        linkedList.printValues();
//        
//        LinkedListNode node = linkedList.getNThNode(2);
//        node.insertAfter(new LinkedListNode("Third", null));

        Integer iValue = input.nextInt();
        
        while (iValue != -1)
        {
            linkedList.addBack(iValue);
            System.out.print("Enter values (-1 to finish): ");
            iValue = input.nextInt();
        }
        
        System.out.println(linkedList);
        System.out.println("");
        
        LinkedListNode node = linkedList.getNThNode(2);
        node.insertAfter(3);
        System.out.println(linkedList);
        System.out.println("");
        
        System.out.println("Search 4: " + linkedList.search(4));
        linkedList.delete(4);
        System.out.println("After delete 4: " + linkedList);        
        System.out.println("Search after delete 4: " + linkedList.search(4));

        
//        System.out.println(linkedList.removeFront());
//        System.out.println(linkedList.removeFront());
//        System.out.println(linkedList.removeFront());
//        
//        System.out.println(linkedList);
//        
//        System.out.println(linkedList.reverse());
    }
}
