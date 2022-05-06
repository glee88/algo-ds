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
public class DoublyLinkedList<T> {
    private DoublyLinkedListNode<T> head = null;
    private DoublyLinkedListNode<T> tail = null;
    
    DoublyLinkedList() {
        head = new DoublyLinkedListNode<T>(null, null, null); // sentinels
        tail = new DoublyLinkedListNode<T>(null, head, null); // sentinels
        head.setNext(tail);
    }
    
    public boolean isEmpty()
    {
        return head.getNext() == tail;
    }
    
    public T getFront()
    {
        if (isEmpty())
            return null;
        else 
            return head.getNext().getValue();
    }
    
    public T getBack()
    {
        if (isEmpty())
            return null;
        else 
            return tail.getPrev().getValue();
    }    
    
    // insert T before node
    void add(DoublyLinkedListNode<T> node, T value)
    {
        DoublyLinkedListNode<T> tmp = new DoublyLinkedListNode<>(value, null, null);
        
        DoublyLinkedListNode<T> prev = node.getPrev();
        prev.setNext(tmp);
        tmp.setPrev(prev);
        
        tmp.setNext(node);
        node.setPrev(tmp);
    }
    
    public void addFront(T value)
    {
//        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(value, null, null);
        add(head.getNext(), value);
//        DoublyLinkedListNode<T> tmp = head.getNext();
//        
//        head.setNext(node);
//        node.setPrev(head);
//        
//        node.setNext(tmp);
//        tmp.setPrev(node);
    }
    
    public void addBack(T value)
    {
        add(tail, value);
//        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(value, null, null);
//        DoublyLinkedListNode<T> tmp = tail.getPrev();
//        
//        tail.setPrev(node);
//        node.setNext(tail);
//        
//        node.setPrev(tmp);
//        tmp.setNext(node);
    }
    
    // remove node
    T remove(DoublyLinkedListNode<T> node) 
    {
        if (node == head || node == tail || isEmpty()) // safety
            return null;
        
        DoublyLinkedListNode<T> prev = node.getPrev();
        DoublyLinkedListNode<T> next = node.getNext();
        
        prev.setNext(next);
        next.setPrev(prev);
        
        return node.getValue();
    }
    
    public T removeFront()
    {
        return remove(head.getNext());
//        if (isEmpty())
//            return null;
//        
//        DoublyLinkedListNode<T> tmp = head.getNext();
//        head.setNext(tmp.getNext());
//        tmp.getNext().setPrev(head);
//        
//        return tmp.getValue();
    }
    
    public T removeBack()
    {
        return remove(tail.getPrev());
//        if (isEmpty())
//            return null;
//        
//        DoublyLinkedListNode<T> tmp = tail.getPrev();        
//        
//        tail.setPrev(tmp.getPrev());
//        tmp.getPrev().setNext(tail);
//        
//        return tmp.getValue();
    }
    
    @Override
    public String toString()
    {
        DoublyLinkedListNode<T> current = head.getNext();
        String sOut = "";
        while (current != tail)
        {
            sOut += current + " -> ";
            current = current.getNext();
        }
        
        return sOut;
    }
    
    public String reverseToString()
    {
        DoublyLinkedListNode<T> current = tail.getPrev();
        String sOut = "";
        while (current != head)
        {
            sOut += current + " -> ";
            current = current.getPrev();
        }
        
        return sOut;
    }    
    
    public DoublyLinkedList<T> reverse()
    {
        DoublyLinkedList<T> list = new DoublyLinkedList<T>();
        
        DoublyLinkedListNode<T> current = head.getNext();
        
        while (current != tail)
        {
            list.addFront(current.getValue());
            current = current.getNext();
        }
        
        return list;
    }    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        
        System.out.print("Enter number to insert front (-1 to quit): ");
        int value = input.nextInt();
        
        while (value != -1)
        {
            dll.addFront(value);
            System.out.print("Enter number to insert front (-1 to quit): ");
            value = input.nextInt();
        }
        
        System.out.println(dll);
        System.out.println("Backward: " + dll.reverseToString());
        System.out.println(dll.reverse());
        
        System.out.print("Enter number to insert back (-1 to quit): ");
        value = input.nextInt();
        
        while (value != -1)
        {
            dll.addBack(value);
            System.out.print("Enter number to insert back (-1 to quit): ");
            value = input.nextInt();
        }
        
        System.out.println(dll);
        System.out.println("Backward: " + dll.reverseToString());
        System.out.println(dll.reverse());
    }
}
