package com.question;

/**
 * given a sorted array, and then convert it to a binary search tree
 */
public class ConvertToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0 || nums == null)
            return null;
        return help(nums, 0, nums.length-1);
    }
    private TreeNode help(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = help(nums, left, mid-1);
        root.right = help(nums, mid+1, right);
        return root;
    }
}
