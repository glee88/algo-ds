/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLists;

/**
 *
 * @author glee
 */
public class LinkedListNode<T> {
    private LinkedListNode next = null;
    private T value;
    
    public LinkedListNode() 
    {
    }
    
    public LinkedListNode(T value, LinkedListNode nextNode)
    {
        this.value = value;
        this.next = nextNode;
    }
    
    public T getValue()
    {
        return value;
    }
    
    public void setValue(T value)
    {
        this.value = value;
    }
    
    public LinkedListNode<T> getNext()
    {
        return next;
    }
    
    public void setNext(LinkedListNode<T> node)
    {
        this.next = node;
    }
    
    @Override
    public String toString()
    {
        return this.value.toString();
    }
    
    public void insertAfter(T value)
    {
        LinkedListNode<T> node = new LinkedListNode<>(value, null);
        LinkedListNode<T> tmp = this.next;
        
        this.next = node;
        node.next = tmp;
    }
}
