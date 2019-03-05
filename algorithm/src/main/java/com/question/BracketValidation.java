package com.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 验证括号合法性[{}][]
 */
public class BracketValidation {
    private static final Map<Character, Character> mappings = new HashMap<Character, Character>();
    static {
        mappings.put('}', '{');
        mappings.put(']', '[');
        mappings.put(')', '(');
    }

    /**
     * stack
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if(s.equals(""))
            return true;
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<s.length(); i++){
            if(!mappings.containsKey(s.charAt(i))){
                stack.push(s.charAt(i));
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                Character openBracket = stack.pop();
                if(openBracket != mappings.get(s.charAt(i))){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new BracketValidation().isValid("[]"));
    }
}
