/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.Vector;

/**
 *
 * @author glee
 */
public class BinaryTreeNode<T> {
    private T elem = null;
    private BinaryTreeNode<T> left = null;
    private BinaryTreeNode<T> right = null;
    private BinaryTreeNode<T> parent = null;
    
    public BinaryTreeNode(T elem, BinaryTreeNode<T>left, BinaryTreeNode<T>right, BinaryTreeNode<T>parent)
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
    
    public BinaryTreeNode<T> getLeft()
    {
        return left;
    }
    
    public BinaryTreeNode<T> getRight()
    {
        return right;
    }

    public BinaryTreeNode<T> getParent()
    {
        return parent;
    }
    
    public void setLeft(BinaryTreeNode<T> left)
    {
        this.left = left;
    }
    
    public void setRight(BinaryTreeNode<T> right)
    {
        this.right = right;
    }

    public void setParent(BinaryTreeNode<T> parent)
    {
        this.parent = parent;
    }
    
    public BinaryTreeNode<T> getSibling()
    {
        if (getParent() != null)
            return this == getParent().getLeft() ? getParent().getRight() : getParent().getLeft();
        else
            return null;
    }
    
    public Vector<BinaryTreeNode<T>> getChildren()
    {
        Vector<BinaryTreeNode<T>> vChildren = new Vector<BinaryTreeNode<T>>();
        
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
}
