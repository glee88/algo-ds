/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees.avlTree;

import java.util.Set;
import java.util.TreeSet;
import trees.binarySearchTree.BinarySearchTree;

/**
 *
 * @author glee
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    private AVLTreeNode<T> root = null;
    
    private final int ALLOWED_IMBALANCE = 1;
    
    public AVLTree()
    {
        super();
    }
    
    public AVLTree(AVLTreeNode<T> root)
    {
        if (root != null)
        {
            setRoot(root);
            n++;
        }
    }    
    
    public void setRoot(AVLTreeNode<T> root)
    {
        this.root = root;
        if (root != null)
            root.setParent(null);
    }
    public void setHeight(AVLTreeNode<T> node)
    {
        if (node == null)
            return;
        
        int heightLeft = height(node.getLeft());
        int heightRight = height(node.getRight());
        
        node.setHeight(1 + (heightLeft > heightRight ? heightLeft : heightRight));
    }
    
    public int height(AVLTreeNode<T> node)
    {
        return node == null ? 0: node.getHeight();
    }
    
    public AVLTreeNode<T> search(AVLTreeNode<T> node, T key)
    {
        return (AVLTreeNode<T>) super.search(node, key);
//        if (node == null || key == null)
//            return null;
//        
//        if (key.compareTo(node.getElement()) < 0)
//            return search(node.getLeft(), key);
//        else if (key.compareTo(node.getElement()) > 0)
//            return search(node.getRight(), key);
//        else
//            return node;
    }
    
    public AVLTreeNode<T> search(T key)
    {
        return search(root, key);
    }    
    
    public boolean isBalanced(AVLTreeNode<T> node)
    {
        if (node == null)
            return true;
        
        int balance = Math.abs(height(node.getLeft()) - height(node.getRight()));
        return balance <= 1;
    }
    
    public AVLTreeNode<T> insert(T value)
    {
        AVLTreeNode<T> parent = null;
        AVLTreeNode<T> node = root;
        
        // move down to find a spot (parent) the value will be a child
        while (node != null)
        {
            parent = node;
            if (value.compareTo(node.getElement()) < 0)
                node = node.getLeft();
            else 
                node = node.getRight();
        }
        
        AVLTreeNode<T> newNode = new AVLTreeNode<>(value, null, null, null);
        newNode.setParent(parent);
        
        if (parent == null) // tree was empty
            setRoot(newNode);
        else if (newNode.getElement().compareTo(parent.getElement()) < 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
        
        n++;

        setHeight(newNode);
        rebalance(newNode);
        return newNode;
    }    
    
//    public AVLTreeNode<T> insert(T value)
//    {
//        AVLTreeNode<T> insertedNode = (AVLTreeNode<T>) super.insert(value);
//        setHeight(insertedNode);
//        rebalance(insertedNode);
//        
//        return insertedNode;
//    }
    
    public AVLTreeNode<T> delete(AVLTreeNode<T> z)
    {
        if (z == null)
            return null;
        
        AVLTreeNode<T> replaced = null;
        
        if (z == null)
            return null;
        
        AVLTreeNode<T> y;
        
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
            y = (AVLTreeNode<T>) treeMinimum(z.getRight());
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
        
        if (z == root)
            setRoot(y);
        
        replaced = y != null ? y : z.getParent(); // if replaced node y is not null, return it. Otherwise (if null), return parent that's to be restructured
        
        setHeight(replaced);
        rebalance(replaced);
        
        n--;
        
        return replaced;
    }
    
        // transplant v where u is located
    void transplant(AVLTreeNode<T> u, AVLTreeNode<T> v)
    {
        if (u == null)
            return;
        
        if (u.getParent() == null) // assuming u is a proper tree node, u must be root
            setRoot(v);
        else if (u.isLeftChild())
            u.getParent().setLeft(v);
        else // u is right child
            u.getParent().setRight(v);
        
        if (v != null)
            v.setParent(u.getParent());
    }
    
    /*
    @param: z is to be rebalanced
    */
    public void rebalance(AVLTreeNode<T> node)
    {
        AVLTreeNode<T> z = node;
        restructure(z);
        
        while (z != root) 
        {
            z = z.getParent();
            setHeight(z);
            if (!isBalanced(z))
            {
                z = restructure(z); // z is new subtree root. 
                setHeight(z.getLeft());
                setHeight(z.getRight());
            }
        }
                
                
    }
    
    public AVLTreeNode<T> restructure(AVLTreeNode<T> node)
    {
        if (node == null)
            return null;
        
        AVLTreeNode<T> z = node;
        AVLTreeNode<T> newSubtreeRoot= null;
        
        if (height(z.getLeft()) - height(z.getRight()) > ALLOWED_IMBALANCE) // unbalanaced and z.left has higher height
        {
            if (height(z.getLeft().getLeft()) >= height(z.getLeft().getRight()))
                newSubtreeRoot = rotateWithLeftChild(z);  // 0 - 0 - 0 (z)
            else 
                newSubtreeRoot = doubleRotateWithLeftChild(z);  //    0 (z)
                                                                // 0
                                                                //    0
            setHeight(z);
            setHeight(newSubtreeRoot);
            return newSubtreeRoot;
        } else if (height(z.getRight()) - height(z.getLeft()) > ALLOWED_IMBALANCE) // unbalanaced and z.right has higher height
        {
            if (height(z.getRight().getRight()) >= height(z.getRight().getLeft()))
                newSubtreeRoot = rotateWithRightChild(z);  // (z) 0 - 0 - 0 
            else
                newSubtreeRoot = doubleRotateWithRightChild(z); // 0 (z)
                                                                //     0
            setHeight(z);
            setHeight(newSubtreeRoot);
            return newSubtreeRoot;                                                                // 0
        }
        
        return node; // if restructuring wasn't necessary, returns the node
    }

    public AVLTreeNode<T> rotateWithRightChild(AVLTreeNode<T> node)
    {
        // see p.443. Fig 10.10 (a) in Goodrich C++ book
        if (node == null)
            return null;

        AVLTreeNode<T> z = node;
        AVLTreeNode<T> a = z;
        AVLTreeNode<T> b = a.getRight();
//        AVLTreeNode<T> c = b.getRight();
        
//        AVLTreeNode<T> T0 = a.getLeft();
        AVLTreeNode<T> T1 = b.getLeft();
//        AVLTreeNode<T> T2 = c.getLeft();
//        AVLTreeNode<T> T3 = c.getRight();
        
        if (z != root) {
            if (z.isRightChild())
                z.getParent().setRight(b);
            else
                z.getParent().setLeft(b);
            
            b.setParent(z.getParent());
        }
        else // z is root
            setRoot(b);
        
        b.setLeft(a);
        a.setParent(b);
        
        a.setRight(T1);
        if (T1 != null)
            T1.setParent(a);
        
        setHeight(a);
        setHeight(b);
        return b;
    }
    
    public AVLTreeNode<T> rotateWithLeftChild(AVLTreeNode<T> node)
    {
        // see p.443. Fig 10.10 (b) in Goodrich C++ book
        if (node == null)
            return null;

        AVLTreeNode<T> z = node;
        AVLTreeNode<T> c = z;
        AVLTreeNode<T> b = c.getLeft();
//        AVLTreeNode<T> a = b.getLeft();
        
//        AVLTreeNode<T> T0 = a.getLeft();
//        AVLTreeNode<T> T1 = a.getRight();
        AVLTreeNode<T> T2 = b.getRight();
//        AVLTreeNode<T> T3 = c.getRight();
        
        if (z != root) {
            if (z.isLeftChild())
                z.getParent().setLeft(b);
            else 
                z.getParent().setRight(b);
            
            b.setParent(z.getParent());
        }
        else // z is root
            setRoot(b);
        
        b.setRight(c);
        c.setParent(b);
        
        c.setLeft(T2);
        if (T2 != null)
            T2.setParent(c);
        
        setHeight(c);
        setHeight(b);
        return b;
    }

    public AVLTreeNode<T> doubleRotateWithRightChild(AVLTreeNode<T> node)
    {
        AVLTreeNode<T> z = node;
        AVLTreeNode<T> a = z;
        AVLTreeNode<T> c = a.getRight();
        AVLTreeNode<T> b = c.getLeft();
        
        rotateWithLeftChild(c);
        rotateWithRightChild(z);
        
        return b;
    }
    
    public AVLTreeNode<T> doubleRotateWithLeftChild(AVLTreeNode<T> node)
    {
        AVLTreeNode<T> z = node;
        AVLTreeNode<T> c = z;
        AVLTreeNode<T> a = c.getLeft();
        AVLTreeNode<T> b = a.getRight();
        
        rotateWithRightChild(a);
        rotateWithLeftChild(z);
        
        return b;
    }

    @Override
    public String toString()
    {
        return inorderTreeWalk(root);
    }
    
    public static void main(String[] args) {
        AVLTree<String> avlTree = new AVLTree<>();
        
        String[] words = "there are so many interesting words in the English language, but people tend to use the same ones over and over again when they’re having a conversation. If you want to change up your vocab a little, try including some of the 250 unique words (with their meanings) we rounded up below. Some of them are ones you may have heard before, but never knew what they meant and others may be words you never knew existed. Either way, incorporating one or several of these unusual works into your dialog will impress people. Try starting by memorizing five words and their meanings each day and then challenge yourself to use them within 24 hours. Once you do, move on and memorize five more you can use. Before you know it, you’ll know all kinds of new words. We even listed them in ABC order.".split("[() .,]");
        
//        String[] words = "executes as case procedure four follows the foul prom them promise".split(" ");
//        Set<String> wordsSet = new TreeSet<>(java.util.Arrays.asList(words));
//        words = wordsSet.toArray(new String[0]);
//        Collections.shuffle(Arrays.asList(words));

//        for (int i=words.length-1;i>=0;i--)
        for (int i=0;i<words.length;i++)
        {
//            int randomIndex = (int)(Math.random() * words.length);
            avlTree.insert(words[i]);
            System.out.println((i+1) + ": " + words[i]);
            System.out.println(avlTree.toString());
        }
        
        System.out.println(avlTree);
        
        for (int i=0;i<words.length;i++)
        {
//            int randomIndex = (int)(Math.random() * words.length);
            avlTree.delete(avlTree.search(words[i]));
            System.out.println((i+1) + ": " + words[i]);
            System.out.println(avlTree.toString());
        }

        
//        System.out.println("Max: " + avlTree.treeMaximum());
//        System.out.println("Min: " + avlTree.treeMinimum());
//        
//        AVLTreeNode <String> node = avlTree.search("promise");
//        System.out.println(new AVLTree(node));
//        avlTree.delete(node);
//        System.out.println(avlTree);
//        
//        node = avlTree.search("them");
//        avlTree.delete(node);
//        System.out.println(avlTree);
//        avlTree.delete(avlTree.search("procedure"));
//        System.out.println(avlTree);
//        System.out.println(new AVLTree(avlTree.search("executes")));
        
    }    
}
