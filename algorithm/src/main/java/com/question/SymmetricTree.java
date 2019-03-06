package com.question;

import java.util.Deque;
import java.util.LinkedList;

/**
 * judge if a tree is symmetric
 */
public class SymmetricTree {
    /**
     * 双端队列
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        Deque<TreeNode> deque = new LinkedList();
        deque.addFirst(root.left);
        deque.addLast(root.right);
        while(!deque.isEmpty()){
            TreeNode left = deque.pollFirst();
            TreeNode right = deque.pollLast();
            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null)
                return false;
            if(left.val != right.val){
                return false;
            }else{
                deque.addFirst(left.left);
                deque.addFirst(left.right);

                deque.addLast(right.left);
                deque.addLast(right.right);
            }
        }
        return true;
    }

    /**
     * recursion
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetric2(root.left, root.right);
    }
    private boolean isSymmetric2(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null){
            return true;
        }
        return node1 != null && node2 != null && node1.val == node2.val && isSymmetric2(node1.left, node2.right) && isSymmetric2(node1.right, node2.left);
    }
}
