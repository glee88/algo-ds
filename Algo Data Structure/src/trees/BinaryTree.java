/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author glee
 */
public class BinaryTree<T> {
    protected BinaryTreeNode<T> root = null;
    protected int n = 0;
    
    public void addRoot(T elem)
    {
        root = new BinaryTreeNode<>(elem, null, null, null);
        n = 1;
    }
    
    public BinaryTreeNode<T> getRoot()
    {
        return root;
    }
    
    public boolean isEmpty()
    {
        return n == 0;
    }
    
    public int size() 
    {
        return n;
    }
    
    public BinaryTreeNode<T> removeAbove(BinaryTreeNode<T> node)
    {
        BinaryTreeNode<T> parent = node.getParent();
        BinaryTreeNode<T> sibling = (parent.getLeft() == node) ? parent.getRight() : parent.getLeft();
        
        if (parent == root)
        {
            sibling = root;
            sibling.setParent(null);
        } else
        {
            BinaryTreeNode<T> gParent = parent.getParent();
            if (parent == gParent.getLeft())
                gParent.setLeft(sibling);
            else 
                gParent.setRight(sibling);
            
            sibling.setParent(gParent);
            
            n = n - 2;
        }
        
        return sibling;
    }
    
    public BinaryTreeNode<T> expand(BinaryTreeNode<T> node, T leftElem, T rightElem)
    {
        BinaryTreeNode<T> left = new BinaryTreeNode<>(leftElem, null, null, node);
        BinaryTreeNode<T> right = new BinaryTreeNode<>(rightElem, null, null, node);
        
        node.setLeft(left);
        node.setRight(right);
        
        return left;
    }
    
    public void preOrder(Vector<T> vNode, BinaryTreeNode<T> node)
    {
        if (vNode == null || node == null)
            return;
        
        vNode.add(node.getElement());
        preOrder(vNode, node.getLeft());
        preOrder(vNode, node.getRight());
    }
    
    public void inOrder(Vector<T> vNode, BinaryTreeNode<T> node)
    {
        if (vNode == null || node == null)
            return;
        
        inOrder(vNode, node.getLeft());
        vNode.add(node.getElement());
        inOrder(vNode, node.getRight());
    }

    public void postOrder(Vector<T> vNode, BinaryTreeNode<T> node)
    {
        if (vNode == null || node == null)
            return;
        
        postOrder(vNode, node.getLeft());
        postOrder(vNode, node.getRight());
        vNode.add(node.getElement());        
    }
    

    
    int height(BinaryTreeNode<T> node)
    {
        if (node.isLeaf() || node == null)
            return 0;
        
        int h = 0;
        Vector<BinaryTreeNode<T>> vChildren = node.getChildren();
        
        Iterator iter = vChildren.iterator();
        
        while (iter.hasNext()){
            int nodeHeight =  height((BinaryTreeNode<T>)iter.next());
            h = h > nodeHeight ? h: nodeHeight;
        }

        return h + 1;
    }
    
    public int getTreeHeight()
    {
        return height(getRoot());
    }
    
    public static void main(String[] args) {
        BinaryTree<String> bt = new BinaryTree<>();
        
        bt.addRoot("-");
        BinaryTreeNode<String> left = bt.expand(bt.getRoot(), "/", "+"); // left is "/"
        
            left = bt.expand(left, "x", "+");
                BinaryTreeNode<String> right = left.getSibling(); // left is "x". right is "+"
                BinaryTreeNode<String> rleft = bt.expand(right, "-", "2");
                bt.expand(rleft, "9", "5");

            left = bt.expand(left, "+", "3"); // left is "x"
            left = bt.expand(left, "3", "1");
        
        right = bt.getRoot().getRight(); // right is "+"
            left = bt.expand(right, "x", "6"); // left is "x"
            left = bt.expand(left, "3", "-"); // left is "3"
            right = left.getSibling(); //  right is "-"
            bt.expand(right, "7", "4");
     
        Vector<String> vNode = new Vector<String>();
        bt.preOrder(vNode, bt.getRoot());
        System.out.println("preorder Traversal: " + vNode);
        
        vNode.clear();
        bt.inOrder(vNode, bt.getRoot());
        System.out.println("inorder Traversal: " + vNode);

        vNode.clear();
        bt.postOrder(vNode, bt.getRoot());
        System.out.println("postorder Traversal: " + vNode);

        System.out.println("Height of Tree: " + bt.getTreeHeight());
    }
}
