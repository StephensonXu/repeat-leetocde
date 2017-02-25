package one;

public class reverse {
	/*
	 * 7. Reverse Integer
	 * Reverse digits of an integer.
	 * Example1: x = 123, return 321
	 * Example2: x = -123, return -321
	 * note: overflow->0
	 * -12%10=-2; and get res+=res*10+mod to reverse;
	 */
	public int reverseInteger(int x) {
	    long res=0;
	    while(x!=0){
			res=res*10+x%10;
			//判断overflow
			//1.记录*10之前数和之后数，看是否对应准确
			//res用long类型
			if(res>Integer.MAX_VALUE || res<Integer.MIN_VALUE) return 0;
			x=x/10;
		}
		return (int) res;
	}
	/*
	 * 206. Reverse Linked List
	 * Reverse a singly linked list.
	 * Hint:
	 * A linked list can be reversed either iteratively or recursively. Could you implement both?
	 * prehead 不断迭代插入
	 * 递归也方便 reverseList(head.next).end.next=head;
	 */
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	public ListNode reverseList(ListNode head){
	    ListNode pre=new ListNode(0), tmp=pre;
	    while(head!=null){
	    	tmp=head;
	    	head=head.next;
	    	tmp.next=pre.next;
	    	pre.next=tmp;	 	
	    }
	    return pre.next;
	}
	/*
	 * 92. Reverse Linked List II
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
	 * For example:
	 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * return 1->4->3->2->5->NULL.
	 * Note:
	 * Given m, n satisfy the following condition:
	 * 1 ≤ m ≤ n ≤ length of list.
	 */
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	ListNode pre=new ListNode(0), dummy=pre;
    	dummy.next=head;
        while(m-->1){
        	pre=pre.next;
        	head=head.next;
        	n--;
        }
        pre.next=null;
        ListNode store=head;
        while(n-->0){
        	ListNode tmp=head;
        	head=head.next;
        	tmp.next=pre.next;
        	pre.next=tmp;
        }
        store.next=head;
        return dummy.next;//use dummy-better        
    }

}
