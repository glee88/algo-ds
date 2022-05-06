/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees.avlTree;

import java.util.Vector;
import trees.binarySearchTree.BinarySearchTreeNode;

/**
 *
 * @author glee
 */
public class AVLTreeNode<T extends Comparable<T>> extends BinarySearchTreeNode<T> {
    private int height;
    
    private AVLTreeNode<T> left = null;
    private AVLTreeNode<T> right = null;
    private AVLTreeNode<T> parent = null;    
    
    public AVLTreeNode(T elem, AVLTreeNode<T> left, AVLTreeNode<T> right, AVLTreeNode<T> parent)
    {
        super(elem, left, right, parent);
    }
    
    public AVLTreeNode(BinarySearchTreeNode<T> bstn)
    {
        super(bstn.getElement(), (AVLTreeNode<T>) bstn.getLeft(), (AVLTreeNode<T>) bstn.getRight(), (AVLTreeNode<T>) bstn.getParent());
    }
    
    public void setHeight(int ht)
    {
        height = ht;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public AVLTreeNode<T> getLeft()
    {
        return left;
    }
    
    public AVLTreeNode<T> getRight()
    {
        return right;
    }

    public AVLTreeNode<T> getParent()
    {
        return parent;
    }
    
    public void setLeft(AVLTreeNode<T> left)
    {
        this.left = left;
    }
    
    public void setRight(AVLTreeNode<T> right)
    {
        this.right = right;
    }

    public void setParent(AVLTreeNode<T> parent)
    {
        this.parent = parent;
    }
    
    public AVLTreeNode<T> getSibling()
    {
        if (getParent() != null)
            return this == getParent().getLeft() ? getParent().getRight() : getParent().getLeft();
        else
            return null;
    }
    
    @Override
    public String toString()
    {
        return this.getElement().toString() + ":" + getHeight();
    }
}
