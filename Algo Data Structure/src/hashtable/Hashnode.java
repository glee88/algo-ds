/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

/**
 *
 * @author glee
 */
public class Hashnode<K, V> {
    private K key;
    private V value;
    
    public Hashnode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
    
    public K getKey()
    {
        return key;
    }
    
    public V getValue()
    {
        return value;
    }
    
    public boolean keyMatch(K key)
    {
        return this.key.equals(key);
    }
    
    @Override 
    public String toString()
    {
        return "<" + key + ", " + value + ">";
    }
            
}
