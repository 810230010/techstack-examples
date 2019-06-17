package com.question;

/**
 * description: given a linked list,check if it has a cycle
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while(null != fastPointer && null != fastPointer.next){
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if(slowPointer == fastPointer)
                return true;
        }
        return false;
    }
}
