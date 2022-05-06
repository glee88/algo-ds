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
public class Stack<T> {
    private LinkedListNode<T> top = null;
    
    public Stack()
    {
    }
    
    public void push(T value)
    {
        LinkedListNode<T> node = new LinkedListNode<>(value, null);

        node.setNext(top);
        top = node;
    }
    
    public boolean isEmpty()
    {
        return top == null;
    }
    
    public int getNumElements()
    {
        int num = 0;
        LinkedListNode<T> current = top;
        while (current != null)
        {
            num++;
            current = current.getNext();
        }
        
        return num;
    }
    
    public T pop()
    {
        LinkedListNode<T> node = top;
        if (top != null)
            top = top.getNext();
        
        return node.getValue();
    }

    @Override
    public String toString()
    {
        LinkedListNode<T> current = top;
        String sOut = "";
        while (current != null)
        {
            sOut += current + " -> \n";
            current = current.getNext();
        }
        
        return sOut;
    }
    
    public T peek()
    {
        return top.getValue();
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Stack<String> stack = new Stack<String>();
        System.out.print("Enter value to push (none to finish): ");
        String value = input.nextLine();
        
        while (!value.equalsIgnoreCase("none"))
        {
            stack.push(value);
//            System.out.println("Current Stack Size: " + stack.getNumElements());
            System.out.print("Enter value to push (none to finish): ");
            value = input.nextLine();
        }
        
        System.out.println(stack);
        
        System.out.println("");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        
        System.out.println("Stack after pop twice");
        System.out.println(stack);
    }
}
