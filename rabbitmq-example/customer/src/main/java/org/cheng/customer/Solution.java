package org.cheng.customer;

public class Solution {
	static ListNode removeElements(ListNode head,int val) {
		//判断是否为空 开头元素是否为val值
		while(head != null && head.val == val) {
            head = head.next;
        }
		//判断是否为空
        if(head == null) {
            return null;
        }
		
		ListNode prev = head;
		while(prev.next!=null) {
			if(prev.next.val==val) {
				prev.next = prev.next.next;
			}else {
				prev = prev.next;
			}
		}
		return head;
	}
	
	static ListNode removeElements2(ListNode head,int val) {
		//判断是否为空
        if(head == null) {
            return null;
        }
        
        ListNode source = head;
        do{
			System.out.println(head.val);
			head = head.next;
		}while(head!=null);
        
		//判断是否为空 开头元素是否为val值
		while(head != null && head.val == val) {
            head = head.next;
        }
		
		ListNode prev = head;
		while(prev.next!=null) {
			if(prev.next.val==val) {
				prev.next = prev.next.next;
			}else {
				prev = prev.next;
			}
		}
		return head;
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode();
		l1.val = 1;
		
		ListNode l2 = new ListNode();
		l2.val = 2;
		l2.next = l1;
		
		ListNode l3 = new ListNode();
		l3.val = 3;
		l3.next = l2;
		
		ListNode l4 = new ListNode();
		l4.val = 4;
		l4.next = l3;
		
		removeElements(l4,11);
		
		do{
			System.out.println(l4.val);
			l4 = l4.next;
		}while(l4!=null);
	}
}

class ListNode {
	int val;
	ListNode next;
}

