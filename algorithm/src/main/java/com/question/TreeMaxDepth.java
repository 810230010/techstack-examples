package com.question;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class TreeMaxDepth {
    //recursive
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }

    //iterative  dfs
    public int maxDepth2(TreeNode root){
        if(root == null)
            return 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<Integer> depth = new Stack<Integer>();
        stack.push(root);
        depth.push(1);
        int max = 0;
        while (!stack.isEmpty()){
            TreeNode father = stack.pop();
            int tempDepth = depth.pop();
            max = Math.max(max, tempDepth);
            if(father.left != null){
                stack.push(father.left);
                depth.push(tempDepth + 1);
            }
            if(father.right != null){
                stack.push(father.right);
                depth.push(tempDepth + 1);
            }
        }
        return max;
    }
    //iterative bfs
    public int maxDepth3(TreeNode root){
        if(root == null)
            return 0;
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                TreeNode node = queue.remove();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            max++;

        }
        return max;
    }
}
