/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees.binarySearchTree;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author glee
 */
public class BinarySearchTree<T extends Comparable<T>>{
    private BinarySearchTreeNode<T> root = null;
    protected int n = 0;
    
    public BinarySearchTree()
    {
        
    }
    
    public BinarySearchTree(BinarySearchTreeNode<T> root)
    {
        if (root != null)
        {
            this.root = root;
            n++;
        }
    }
    
    public int getSize()
    {
        return n;
    }
    
    public BinarySearchTreeNode<T> search(BinarySearchTreeNode<T> node, T key)
    {
        if (node == null || key == null)
            return null;
        
        if (key.compareTo(node.getElement()) < 0)
            return search(node.getLeft(), key);
        else if (key.compareTo(node.getElement()) > 0)
            return search(node.getRight(), key);
        else
            return node;
    }
    
    public BinarySearchTreeNode<T> search(T key)
    {
        return search(root, key);
    }
    
    public BinarySearchTreeNode<T> iterativeSearch(BinarySearchTreeNode<T> node, T key)
    {
        if (node == null || key == null)
            return null;
        
        while (key.compareTo(node.getElement()) != 0 && node != null)
        {
            if (key.compareTo(node.getElement()) < 0)
                node = node.getLeft();
            else 
                node = node.getRight();
        }
        
        return node;
    }
    
    public BinarySearchTreeNode<T> treeMinimum(BinarySearchTreeNode<T> node)
    {
        if (node == null)
            return null;
        
        while (node.getLeft() != null)
            node = node.getLeft();
        
        return node;
    }
    
    public BinarySearchTreeNode<T> treeMinimum()
    {
        return treeMinimum(root);
    }
    
    public BinarySearchTreeNode<T> treeMaximum(BinarySearchTreeNode<T> node)
    {
        if (node == null)
            return null;
        
        while (node.getRight() != null)
            node = node.getRight();
        
        return node;
    }    
    
    public BinarySearchTreeNode<T> treeMaximum()
    {
        return treeMaximum(root);
    }
    
    public String inorderTreeWalk(BinarySearchTreeNode<T> node)
    {
        if (node == null)
            return "";
        
        String sOut = "";
        sOut += inorderTreeWalk(node.getLeft()) + " ";
        sOut += node.toString() + "";
        sOut += inorderTreeWalk(node.getRight()) + " ";
        
        return sOut;
    }
    
    @Override
    public String toString()
    {
        return inorderTreeWalk(root);
    }
    
    public BinarySearchTreeNode<T> treeSuccessor(BinarySearchTreeNode<T> node)
    {
        if (node == null)
            return null;
        
        if (node.getRight() != null)
            return this.treeMinimum(node.getRight());

        // successor of node is lowest ancester of node whose left child is also ancestor of node
        BinarySearchTreeNode<T> parent = node.getParent();
        while (parent != null && parent.getRight() == node) 
        {
            node = parent;
            parent = parent.getParent();
        }
        
        return parent;
    }
    
    public BinarySearchTreeNode<T> treePredecessor(BinarySearchTreeNode<T> node)
    {
        if (node == null)
            return null;
        
        if (node.getLeft() != null)
            return this.treeMaximum(node.getRight());

        // predecessor of node is lowest ancester of node whose right child is also ancestor of node
        BinarySearchTreeNode<T> parent = node.getParent();
        while (parent != null && parent.getLeft() == node) 
        {
            node = parent;
            parent = parent.getParent();
        }
        
        return parent;
    }    
    
    public BinarySearchTreeNode<T> insert(T value)
    {
        BinarySearchTreeNode<T> parent = null;
        BinarySearchTreeNode<T> node = root;
        
        // move down to find a spot (parent) the value will be a child
        while (node != null)
        {
            parent = node;
            if (value.compareTo(node.getElement()) < 0)
                node = node.getLeft();
            else 
                node = node.getRight();
        }
        
        BinarySearchTreeNode<T> newNode = new BinarySearchTreeNode<T>(value, null, null, null);
        newNode.setParent(parent);
        
        if (parent == null) // tree was empty
            root = newNode;
        else if (newNode.getElement().compareTo(parent.getElement()) < 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
        
        n++;
        return newNode;
    }
    
    // transplant v where u is located
    void transplant(BinarySearchTreeNode<T> u, BinarySearchTreeNode<T> v)
    {
        if (u == null)
            return;
        
        if (u.getParent() == null) // assuming u is a proper tree node, u must be root
            root = v;
        else if (u.isLeftChild())
            u.getParent().setLeft(v);
        else // u is right child
            u.getParent().setRight(v);
        
        if (v != null)
            v.setParent(u.getParent());
    }
    
    public BinarySearchTreeNode<T> delete(BinarySearchTreeNode<T> z)
    {
        if (z == null)
            return null;
        
        BinarySearchTreeNode<T> y;
        
        if (z.getLeft() == null) // z has one child (on right) or less
        {
            y = z.getRight();
            transplant(z, y);
        }
        else if (z.getRight() == null) // z has one child on left
        {
            y = z.getLeft();
            transplant(z, y);
        }
        else // z has both child
        {
            y = this.treeMinimum(z.getRight());
            if (y.getParent() != z)
            {
                transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
        }
        
        return y != null ? y : z.getParent(); // if replaced node y is not null, return it. Otherwise (if null), return parent that's to be restructured
    }
    
//    public void delete
    
    public static void main(String[] args) {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        
//        String[] words = "procedure executes the four cases as follows line handle case node has no left child and lines handle case has a left child".split(" ");
        String[] words = "executes as case procedure four follows the foul prom them promise".split(" ");
//        Set<String> wordsSet = new HashSet<>(java.util.Arrays.asList(words));
//        words = wordsSet.toArray(new String[0]);
//        Collections.shuffle(Arrays.asList(words));
        for (int i=0;i<words.length;i++)
        {
//            int randomIndex = (int)(Math.random() * words.length);
            bst.insert(words[i]);
            System.out.println((i+1) + ": " + words[i]);
        }
        
        System.out.println(bst);
        System.out.println("Max: " + bst.treeMaximum());
        System.out.println("Min: " + bst.treeMinimum());
        System.out.println(new BinarySearchTree(bst.search("them")));
        
        bst.delete(bst.search("procedure"));
        System.out.println(bst);
        System.out.println(new BinarySearchTree(bst.search("executes")));
        
    }
}
