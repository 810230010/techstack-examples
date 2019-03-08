package com.question;

/**
 * given a binary tree, judge if it is height balanced
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalanceTreeJudge {
    private static boolean result = false;
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }
    private int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(Math.abs(left-right) > 1){
           result = false;
        }
        return left > right ? 1+left : 1+right;
    }
}
