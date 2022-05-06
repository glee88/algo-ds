/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable.openaddress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import hashtable.*;

/**
 *
 * @author glee
 */
public class Hashtable<K, V> {
    private int m = 13; // m is # of hash table slots
    private List<Hashnode<K, V>> list = null;
    private List<K> keyList = null;
    private int n = 0; // # of elements in hashtable
    
    public Hashtable()
    {
        keyList = new ArrayList<>();
        list = new ArrayList<>(m);
        for (int i=0;i<m;i++)
            list.add(null);
    }
    
    public List<K> getKeys()
    {
        return keyList;
    }
    
    private int hashCode(K key) // make key into integer k
    {
        return key.hashCode();
//        String str = key.toString();
//        int keyIntVal = 0;
//        for (int i=0;i<str.length();i++)
//            keyIntVal += (int)(str.charAt(i) << 5); // bitwise shift of 5
//        
//        return keyIntVal;
    }
    
    private int hash(K key, int i) // double hashing probing
    {
        int k = Math.abs(hashCode(key));
        
        int h1 = k % m;
        int h2 = 1 + (k % (m - 1));
        
        return (h1 + i * h2) % m;
    }
    
    public int size()
    {
        return n;
    }
    
    public void insert(Hashnode<K, V> entry)
    {
        if (entry == null)
            return;
        
        int i = 0;
        K key = entry.getKey();
        do {
            int address = hash(key, i);
            if (list.get(address) == null)
            {
                list.set(address, entry);
                System.out.println(entry + " inserted at " + address);
                
                this.keyList.add(key);
                n++;
                return;
            } else if (list.get(address).keyMatch(key)) // key conflict. Overwrite
            {
                System.out.println(entry + " overwritten at " + address);
                list.set(address, entry);
                return;
            } else
            {
                System.out.println("move to next i");
                i++;
            }
            
        } while (i < m);
        
        System.out.println("Hash table overflow. " + entry + " couldn't be inserted.");
    }
    
    public V search(K key)
    {
        int i=0;
        int address = 0;
        address = hash(key, i);
        while (list.get(address) != null && i < m)
        {
            if (list.get(address).keyMatch(key))
            {
//                System.out.println(list.get(address) + " has been found at slot " + address);
                return list.get(address).getValue();
            }
            i++;
            address = hash(key, i);
        } 
        
        return null;
    }
    
    public static void main(String[] args) {
        Hashtable<String, Hashnode<String, String>> ht = new Hashtable<>();
        
        ht.insert(new Hashnode("Maria Carey", "Vision of Love"));

        ht.insert(new Hashnode("Deep Purple", "Highway Star"));
        ht.insert(new Hashnode("Led Zeppelin", "Stairway to Heaven"));
        
        ht.insert(new Hashnode("Maria Carey", "Emotion"));
        ht.insert(new Hashnode("Black Sabbath", "Paranoid"));
        ht.insert(new Hashnode("Van Halen", "1984"));
        ht.insert(new Hashnode("Cormen", "Algorithm"));
        ht.insert(new Hashnode("Metallica", "Master of Puppents"));
        ht.insert(new Hashnode("Titleist", "Pro V1"));
        
        System.out.println(ht.search("Deep Purple"));
        ht.insert(new Hashnode("Maria Carey", "When you believe"));
        
        System.out.println("Size: " + ht.size());
        
        System.out.println(ht.search("Van Halen") != null ? ht.search("Van Halen") : "Not found");
        System.out.println(ht.search("Pro V1") != null ? ht.search("Pro V1") : "Not found");
        System.out.println(ht.search("Black Sabbath") != null ? ht.search("Black Sabbath") : "Not found");
        System.out.println(ht.search("Led Zep") != null ? ht.search("Led Zep") : "Led Zep Not found");
        System.out.println(ht.search("Deep Purple") != null ? ht.search("Deep Purple") : "Deep Purple Not found");
        System.out.println(ht.search("Cormen") != null ? ht.search("Cormen") : "Not found");
    }
}
