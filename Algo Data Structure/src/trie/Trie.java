/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trie;

import java.util.ArrayList;

/**
 *
 * @author glee
 */
public class Trie {
    static int ALHPABET_SIZE = 27;
    static TrieNode root;
    
    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALHPABET_SIZE];
        boolean bLeaf = false;
        String value = null;

        TrieNode()
        {
        }
        
        TrieNode(String value)
        {
            setValue(value);
        }
       
        public boolean isLeaf()
        {
            return bLeaf;
        }
        
        public void setLeaf(boolean bLeaf)
        {
            this.bLeaf = bLeaf;
        }
        
        public void setValue(String value)
        {
            this.value = value;
        }
        
        public String getValue()
        {
            return value;
        }
        
        @Override
        public String toString()
        {
            return value + "; Leaf: " + bLeaf;
        }
    }
        
    Trie()
    {
        root = new TrieNode();
    }

    public void insert(String[] words)
    {
        for (int i=0;i<words.length;i++)
        {
            insert(words[i]);
        }
    }
    // simple insert
    public void insert(String key)
    {
        key = key.toLowerCase();
        TrieNode current = root;
        TrieNode prev = null;
        for (int i=0;i<key.length();i++)
        {
            int index = key.charAt(i) - 'a';
            
            if (current.children[index] == null) {
                current.setLeaf(false);
                current.children[index] = new TrieNode();
            }
            
            current = current.children[index];
        }
        
        current.setValue(key);
        current.setLeaf(true);
    }
    
    public void compressedInsert(String key)
    {
        key = key.toLowerCase();
        TrieNode current = root;
        for (int i=0;i<key.length();i++)
        {
            int index = key.charAt(i) - 'a';
            
            if (current != null && current.isLeaf())
            {
                String value2 = current.getValue(); 
                do {
//                    String value2 = current.children[index].getValue();
                    TrieNode newNode = new TrieNode();
                    newNode.setLeaf(false); // nonleaf node
                    current.children[index] = newNode;
                    
                    // prepare for next iteration
                    current = current.children[index];
                    i++; // move to next letter in key
                    
                    if (value2.length() <= i || key.length() <= i)
                    {
                        i--;
                        break;
                    }
                    index = key.charAt(i) - 'a';
                } while (value2.charAt(i) == key.charAt(i)); // add nonleaf node until prefixes are same
                
                TrieNode newNode = new TrieNode();
                newNode.setLeaf(true); // nonleaf node for value2
                newNode.setValue(value2);
                current.children[value2.charAt(i) - 'a'] = newNode;
                
                newNode = new TrieNode();
                newNode.setLeaf(true); // nonleaf node for key
                newNode.setValue(key);
                current.children[index] = newNode;
                break;
            }
            else if (current.children[index] != null && !current.children[index].isLeaf())
            {   // will have to go down the trie
                current = current.children[index];
            } else if (current.children[index] != null && current.children[index].isLeaf())
            {
                // need to make nonleaf node(s)
                // another key (value2) to be a sibling of the new node being added
                String value2 = current.children[index].getValue(); 
                do {
//                    String value2 = current.children[index].getValue();
                    TrieNode newNode = new TrieNode();
                    newNode.setLeaf(false); // nonleaf node
                    current.children[index] = newNode;
                    
                    // prepare for next iteration
                    current = current.children[index];
                    i++; // move to next letter in key
                    index = key.charAt(i) - 'a';
                } while (value2.charAt(i) == key.charAt(i)); // add nonleaf node until prefixes are same
                
                TrieNode newNode = new TrieNode();
                newNode.setLeaf(true); // nonleaf node for value2
                newNode.setValue(value2);
                current.children[value2.charAt(i) - 'a'] = newNode;
                
                newNode = new TrieNode();
                newNode.setLeaf(true); // nonleaf node for key
                newNode.setValue(key);
                current.children[index] = newNode;
                
                break;
            } else // current.children[index] == null 
            {
                TrieNode newNode = new TrieNode();
                newNode.setLeaf(true);
                newNode.setValue(key);
                current.children[index] = newNode;
                break;
            }
        }
    }
    
    public TrieNode search(String key)
    {
        TrieNode current = root;
        key = key.toLowerCase();
        
        for (int i=0;i<key.length();i++) 
        {
            if (current != null && !current.isLeaf())
            {
                int index = key.charAt(i) - 'a';
                current = current.children[index];
            } else 
                break;
        }
        
        if (current != null && current.getValue()!= null && current.getValue().equals(key))
            return current;
        else
            return null;
    }
    
    public ArrayList<String> getStringStartsWith(String prefix)
    {
        ArrayList<String> alWords = new ArrayList<>();
        
        TrieNode subtrie = findSubTrie(prefix);
        
        preorderVisitTrie(alWords, subtrie);
        return alWords;
    }
    
    public TrieNode findSubTrie(String prefix)
    {
        TrieNode current = root;
        prefix = prefix.toLowerCase();
        
        for (int i=0;i<prefix.length();i++) 
        {
            if (current != null && !current.isLeaf())
            {
                int index = prefix.charAt(i) - 'a';
                current = current.children[index];
            } else 
                break;
        }        
        
        return current;
    }
    
    public void preorderVisitTrie(ArrayList<String> alWords, TrieNode subroot)
    {
        if (subroot == null) 
            return;
        
        if (subroot.getValue() != null)
            alWords.add(subroot.getValue());
        
        for (int i=0;i<subroot.children.length;i++)
        {
            preorderVisitTrie(alWords, subroot.children[i]);
        }
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        String message = "Another use case is in indexing with a boolean DataFrame such as one produced by a scalar comparison";
        trie.insert(message.split(" "));
        message = "All Lander University graduates are broadly educated well informed equipped for responsible participation in the civic lives of their communities and prepared to continue their education or launch their careers";
        trie.insert(message.split(" "));
//        
//        trie.insert("He");
//        trie.insert("Hell");
//        trie.insert("Hello");
        
//        System.out.println(trie.search("her"));
        
        ArrayList<String> alWords = new ArrayList<>();
        alWords = trie.getStringStartsWith("in");
        System.out.println(alWords);
    }
}
