package one;

public class LinkedList {
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
	/*
	 * 2. Add Two Numbers
	 * You are given two non-empty linked lists representing two non-negative integers. 
	 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * 1.just plus from head, so if we need to find head, is ok to build prehead=run, and ListNode-run run in process, and head is pre.next
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2){
		int sum=0,carry=0;
		ListNode prebefore=new ListNode(0),run=prebefore;
		while(!(l1==null && l2 ==null && carry==0)){
			sum=(l1==null?0:l1.val)+(l2==null?0:l2.val)+carry;
			carry=sum/10;
			run.next=new ListNode(sum%10);
			run=run.next;
			l1=(l1==null?null:l1.next);
			l2=(l2==null?null:l2.next);
			
		}
		ListNode res=prebefore.next;//and it is better to delete the prebefore node
		prebefore=null;
		return res;
	}

}
