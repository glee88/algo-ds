/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author glee
 */
public class MatchHTML {
    public static Vector<String> getHTMLTags(String html)
    {
        Vector<String> vTags = new Vector<>();
        int start = html.indexOf('<');
        while (start >= 0)
        {
            int end = html.indexOf('>', start);
            if (end < 0)
                break;
            String tag = html.substring(start, end+1);
            vTags.add(tag);
            start = html.indexOf('<', end+1);
        }
        
        return vTags;
    }
    
    public static boolean isHTMLMatched(Vector<String> vHTML)
    {
        Stack<String> stack = new Stack<>();
        
        for (String tag:vHTML)
        {
            if (tag.charAt(1) != '/') // opening tag
                stack.push(tag);
            else // closing tag
            {
                if (stack.isEmpty())
                    return false;
                
                String open = stack.peek(); // check matching tag (top of stack)
                if (open.indexOf(' ') >=0) // tag with attributes
                    open = open.substring(1,open.indexOf(' ')); 
                else
                    open = open.substring(1, open.length() - 1); // remove '>'
                
                String close = tag.substring(2, tag.length() - 1); // remove '>'

                if (open.equalsIgnoreCase(close)) // matches
                    stack.pop();
                else
                    return false;
            }
        }
        
        if (stack.isEmpty())
            return true;
        else 
            return false;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String sHTML = "";
        String s;
        while ((s = input.readLine()) != null && s.length() != 0)
        {
            sHTML += s;
        }
        
        Vector<String> vTags = getHTMLTags(sHTML);
        System.out.println(vTags);
        System.out.println("Matching?: " + isHTMLMatched(vTags));
    }
}
