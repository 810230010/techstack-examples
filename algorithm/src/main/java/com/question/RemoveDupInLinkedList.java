package com.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sorted linked list remove duplicate parts keeps one
 * 1->2->2->3    ->      1->2->3
 */
public class RemoveDupInLinkedList {
    public ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> map = new HashMap();
        if(head == null || head.next == null){
            return head;
        }
        ListNode p = head;
        ListNode newHead = new ListNode(head.val);
        map.put(head.val, 1);
        ListNode newP = newHead;
        while(null != p){
            int val = p.val;
            if(!map.containsKey(val)){
                newP.next = new ListNode(val);
                newP = newP.next;
                map.put(val, 1);
            }
            p = p.next;
        }
        return newHead;
    }
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode p = head;
        while(null != p && null != p.next){
            if(p.next.val == p.val){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
        return head;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int _val){
        this.val = _val;
    }
}