package com.question;

import java.util.HashMap;
import java.util.Map;

public class LinkedListIntersection {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        int aLen = getLen(headA);
        int bLen = getLen(headB);
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        if(aLen > bLen){
            for(int i=0; i<aLen-bLen; i++){
                pointerA = pointerA.next;
            }
        }else{
            for(int i=0; i<bLen-aLen; i++){
                pointerB = pointerB.next;
            }
        }
        while(pointerA != null && pointerB != null){
            if(pointerA == pointerB){
                return pointerA;
            }
            pointerA = pointerA.next;
            pointerB = pointerB.next;
        }
        return null;
    }
    private int getLen(ListNode node){
        int size = 0;
        ListNode pointer = node;
        while(pointer != null){
            size++;
            pointer = pointer.next;
        }
        return size;
    }
}
