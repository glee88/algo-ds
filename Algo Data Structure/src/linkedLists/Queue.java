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
public class Queue<T> {
    private LinkedListNode<T> front = null;
    private LinkedListNode<T> rear = null;
    
    public Queue()
    {
    }
    
    public void enqueue(T newValue)
    {
        LinkedListNode<T> node = new LinkedListNode<>(newValue, null);
        if (isEmpty())
        {
            front = node;
            rear = node;
            return;
        }
        
        rear.setNext(node);
        rear = node;
    }

    public T dequeue()
    {
        if (front == null)
            return null;

        LinkedListNode<T> node = front;
        front = front.getNext();
        if (front == null)
            rear = null;
        
        return node.getValue();
    }
    
    public boolean isEmpty()
    {
        return front == null && rear == null;
    }
    
    public int getNumElements()
    {
        int num = 0;
        LinkedListNode<T> current = front;
        while (current != null)
        {
            num++;
            current = current.getNext();
        }
        
        return num;
    }

    @Override
    public String toString()
    {
        LinkedListNode<T> current = front;
        String sOut = "";
        while (current != null)
        {
            sOut += current + " -> \n";
            current = current.getNext();
        }
        
        return sOut;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Queue<String> queue = new Queue();
        System.out.print("Enter value to enqueue (none to finish): ");
        String value = input.nextLine();
        
        while (!value.equalsIgnoreCase("none"))
        {
            queue.enqueue(value);
            System.out.println("Current queue Size: " + queue.getNumElements());
            System.out.print("Enter value to push (none to finish): ");
            value = input.nextLine();
        }
        
        System.out.println(queue);
        System.out.println();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("Queue after dequeue twice");
        System.out.println(queue);
    }
}
