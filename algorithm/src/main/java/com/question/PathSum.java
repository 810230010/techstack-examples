package com.question;

import java.util.Stack;

/**
 * given a sum, judge if the tree root-to-leaf path has the sum to target sum
 */
public class PathSum {
    //recursive
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null && root.val == sum)
            return true;
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }

    //iterative
    public boolean hasPathSum2(TreeNode root, int sum) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null && root.val == sum)
            return true;
        Stack<TreeNode> stack = new Stack();
        Stack<Integer> valStack = new Stack<Integer>();
        stack.push(root);
        valStack.push(root.val);
        while(!stack.isEmpty()){
            TreeNode tempNode = stack.pop();
            Integer tempVal = valStack.pop();
            if(tempNode.left == null && tempNode.right == null && tempVal == sum)
                return true;
            if(tempNode.left != null){
                stack.push(tempNode.left);
                valStack.push(tempVal+tempNode.left.val);
            }
            if(tempNode.right != null){
                stack.push(tempNode.right);
                valStack.push(tempVal+tempNode.right.val);
            }
        }
        return false;
    }
}
