/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees.binarySearchTree;

import java.util.Vector;

/**
 *
 * @author glee
 */
public class BinarySearchTreeNode<T extends Comparable<T>> {
    private T elem = null;
    private BinarySearchTreeNode<T> left = null;
    private BinarySearchTreeNode<T> right = null;
    private BinarySearchTreeNode<T> parent = null;
    
    public BinarySearchTreeNode(T elem, BinarySearchTreeNode<T> left, BinarySearchTreeNode<T> right, BinarySearchTreeNode<T> parent)
    {
        this.elem = elem;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public T getElement()
    {
        return elem;
    }
    
    public void setElement(T elem)
    {
        this.elem = elem;
    }
    
    public BinarySearchTreeNode<T> getLeft()
    {
        return left;
    }
    
    public BinarySearchTreeNode<T> getRight()
    {
        return right;
    }

    public BinarySearchTreeNode<T> getParent()
    {
        return parent;
    }
    
    public void setLeft(BinarySearchTreeNode<T> left)
    {
        this.left = left;
    }
    
    public void setRight(BinarySearchTreeNode<T> right)
    {
        this.right = right;
    }

    public void setParent(BinarySearchTreeNode<T> parent)
    {
        this.parent = parent;
    }
    
    public BinarySearchTreeNode<T> getSibling()
    {
        if (getParent() != null)
            return this == getParent().getLeft() ? getParent().getRight() : getParent().getLeft();
        else
            return null;
    }
    
    public Vector<BinarySearchTreeNode<T>> getChildren()
    {
        Vector<BinarySearchTreeNode<T>> vChildren = new Vector<BinarySearchTreeNode<T>>();
        
        vChildren.add(getLeft());
        vChildren.add(getRight());
        return vChildren;
    }

    public boolean isRoot()
    {
        return parent == null;
    }
    
    public boolean isLeaf()
    {
        return left == null && right == null;
    }    
    
    public boolean isLeftChild()
    {
        if (getParent() == null)
            return false;
        
        if (getParent().getLeft() == this)
            return true;
        else
            return false;
    }
    
    public boolean isRightChild()
    {
        if (getParent() == null)
            return false;
        
        if (getParent().getRight() == this)
            return true;
        else
            return false;
    }    
    
    @Override
    public String toString()
    {
        return this.getElement().toString();
    }
    
}
