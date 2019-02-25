package com.question;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs == null)
            return "";
        return longestCommonPrefix(strs, 0, strs.length-1);
    }

    /**
     * brutal force
     * time complexity: O(n)
     * space complexity: O(1)
     * @param strs
     * @return
     */
    private String find(String[] strs){
        StringBuilder result = new StringBuilder("");
        if(strs.length == 0 || strs == null)
            return "";
        if(strs.length == 1)
            return strs[0];
        String firstStr = strs[0];
        String[] arr = firstStr.split("");
        String temp = "";
        for(String charStr : arr){
            result.append(charStr);
            for(String str : strs){
                if(!str.startsWith(result.toString())){
                    if(!result.toString().equals("")){
                        return result.deleteCharAt(result.lastIndexOf(charStr)).toString();
                    }else{
                        return "";
                    }

                }
            }
        }
        return result.toString();
    }

    /**
     * divide and conquer
     * time complexity: O(n)
     * space complexity: O(mlog(n))
     * @param strs
     * @param left
     * @param right
     * @return
     */
    private String longestCommonPrefix(String[] strs, int left, int right){
        if(left == right){
            return strs[left];
        }else {
            int mid = (left + right) >> 1;
            String leftLcpStr = longestCommonPrefix(strs, 0, mid);
            String rightLcpStr = longestCommonPrefix(strs, mid+1, right);
            return getCommonPrefix(leftLcpStr, rightLcpStr);
        }
    }

    private String getCommonPrefix(String leftLcpStr, String rightLcpStr) {
        int minLen = Math.min(leftLcpStr.length(), rightLcpStr.length());
        for(int i=0; i<minLen-1; i++){
            if(leftLcpStr.charAt(i) != rightLcpStr.charAt(i)){
                return leftLcpStr.substring(0, i);
            }
        }
        return leftLcpStr.substring(0, minLen);
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"flow", "flower", "f"}));
    }
}
