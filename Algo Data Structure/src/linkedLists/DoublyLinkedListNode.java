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
public class DoublyLinkedListNode<T> {
    private DoublyLinkedListNode<T> next = null;
    private DoublyLinkedListNode<T> prev = null;
    private T value;
    
    public DoublyLinkedListNode() 
    {
    }
    
    public DoublyLinkedListNode(T value, DoublyLinkedListNode<T> prev, DoublyLinkedListNode<T> next)
    {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
    
    public T getValue()
    {
        return value;
    }
    
    public void setValue(T value)
    {
        this.value = value;
    }
    
    public DoublyLinkedListNode<T> getNext()
    {
        return next;
    }

    public DoublyLinkedListNode<T> getPrev()
    {
        return prev;
    }
    
    public void setNext(DoublyLinkedListNode<T> node)
    {
        this.next = node;
    }

    public void setPrev(DoublyLinkedListNode<T> node)
    {
        this.prev = node;
    }
    
    @Override
    public String toString()
    {
        return this.value.toString();
    }
    
    public void insertAfter(T value)
    {
        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(value, null, null);
        DoublyLinkedListNode<T> tmp = this.next;
        
        this.next = node;
        node.prev = this;
        
        node.next = tmp;
        tmp.prev = node;
    }
}
