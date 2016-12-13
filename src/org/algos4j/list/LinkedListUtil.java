package org.algos4j.list;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import org.algos4j.list.LinkedList.Node;

/**
 * This class has utility methods on linked list data structures.
 * 
 * @author psajja
 *
 */
public class LinkedListUtil {

	/**
	 * Not-instanciable.
	 */
	private LinkedListUtil() {
	}
	
	/**
	 * Constructs a singly linked list from a given array.
	 * 
	 * @param array
	 * 		input array
	 * 
	 * @return
	 * 		linked list constructed from the given array
	 * 
	 * @throws NullPointerException
	 * 		if the input array is null
	 */
	public static LinkedList createList(int[] array) {
		if(array == null)
			throw new NullPointerException("Input array can not be null.");
	
		LinkedList list = new LinkedList();
		for(int elt : array)
			list.insertLast(elt);
		
		return list;
	}
	
	/**
	 * Computes the number of nodes present in the given linked list.
	 * Recursive approach.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @return
	 * 		returns the size of the list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static int size(LinkedList linkedList) {
		if(linkedList == null)
			throw new NullPointerException("Linked list not be null.");
	
		return size(linkedList.getHead());
	}
	
	/**
	 * Computes the size of the linked list recursively.
	 * 
	 * @param head
	 * 		current head
	 * 
	 * @return
	 * 		size
	 */
	private static int size(Node node) {
		if (node == null)
			return 0;
		
		return 1 + size(node.next);
	}

	/**
	 * Searches for the given element in the linked list.
	 * Recursive approach.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param element
	 * 		element to find
	 * 
	 * @return
	 * 		true if the element is found, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static boolean search(LinkedList linkedList, int element) {
		if(linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		
		return search(linkedList.getHead(), element);
	}
	
	/**
	 * Recursively searches for the given element.
	 * 
	 * @param head
	 * 		current head
	 * @param element
	 * 		element to find
	 * 
	 * @return
	 * 		true if the element is found, false otherwise
	 */
	private static boolean search(Node node, int element) {
		if (node == null)
			return false;
		
		if (node.getData() == element)
			return true;
		
		return search(node.next, element);
	}

	/**
	 * Check whether the given linked list is in sorted order.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @return
	 * 		true if the list is sorted, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static boolean isSorted(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node current = linkedList.getHead();
		if (current == null)
			return true;

		while (current.next != null) {
			if (current.getData() <= current.next.getData())
				current = current.next;
			else
				return false;
		}

		return true;
	}
	
	/**
	 * Get the value of the middle element in the given linked list. Returns the
	 * second element in the middle pair if there are even number of elements in
	 * the list.
	 * 
	 * @param linkedList
	 * 		input linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static int getMiddle(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node slow = linkedList.getHead();
		Node fast = slow;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow.getData();
	}
	
	/**
	 * Get the value of the middle element in the given linked list. Returns the
	 * first element in the middle pair if there are even number of elements in
	 * the list.
	 * 
	 * @param linkedList
	 * 		input linked list
	 * 
	 * @throws NullPointerException
	 *  	if the linked list is null
	 */
	static int getMiddle1(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node slow = linkedList.getHead();
		Node fast = slow;
		int i = 0;

		while (fast.next != null) {
			if (i == 0) {
				fast = fast.next;
				i = 1;
			} else if (i == 1) {
				slow = slow.next;
				fast = fast.next;
				i = 0;
			}
		}

		return slow.getData();
	}
	
	/**
	 * Get nth node value from the end of the list.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param n
	 * 		node position
	 * 
	 * @return
	 * 		node value if present
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is empty.
	 * @throws IllegalArgumentException
	 * 		if the position is not valid or not exists
	 */
	public static int getNthFromEnd(LinkedList linkedList, int n) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (n < 0)
			throw new IllegalArgumentException("Invalid position " + n);
		
		Node ref = linkedList.getHead();
		Node head = linkedList.getHead();

		for (int i = 0; i < n; i++) {
			if (ref == null)
				throw new IllegalArgumentException(
						"No such element at position " + n);
			ref = ref.next;
		}

		while (ref != null) {
			head = head.next;
			ref = ref.next;
		}

		return head.getData();
	}
	
	/**
	 * Utility method to find the number of occurrences of a given element.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param elt
	 * 		element to find
	 * 
	 * @return
	 * 		number of occurrences the element found
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static int occurs(LinkedList linkedList, int elt) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node head = linkedList.getHead();
		int count = 0;
		while (head != null) {
			if (head.getData() == elt)
				count++;
			head = head.next;
		}

		return count;
	}
	
	/**
	 * Returns the reversed version of the linked list.
	 * 
	 * @param linedList
	 * 		input linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 * 
	 */
	public static void reverse(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		
		linkedList.setHead(reverse(linkedList.getHead()));
	}
	
	/**
	 * Reverse the nodes.
	 * 
	 * @param head
	 * 		current head node
	 * 
	 * @return
	 * 		new head
	 */
	private static Node reverse(Node head) {
		Node current = head;
		Node prev = null;
		Node next = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		
		return prev;
	}
	
	/**
	 * Reverses the given linked list. Recursive version of {@link #reverse(LinkedList)}
	 * 
	 * @param linkedList
	 * 		input linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	static void reverseRecursive(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be empty.");
	
		linkedList.setHead(reverseRecursive(linkedList.getHead()));
	}
	
	/**
	 * Reverses the list recursively.
	 * 
	 * @param head
	 * 		current head node
	 * 
	 * @return
	 * 		new head node
	 */
	private static Node reverseRecursive(Node head) {
		 if(head==null || head.next == null)
		        return head;
		 
		    Node next = head.next;
		    head.next = null;
		 
		    Node newHead = reverseRecursive(next);
		    next.next = head;
		 
		    return newHead;
	}

	/**
	 * Checks whether the given linked list has a loop. Floyd algorithm.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @return
	 * 		true if there is loop, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static boolean hasLoop(LinkedList linkedList) {
		if(linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
	
		Node slow = linkedList.getHead();
		Node fast = linkedList.getHead();
		
		while(slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		
			if(slow == fast)
				return true;
		}
		
		return false;
	}
	
	/**
	 * This method detects and removes the loop if any.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 		
	 * @return
	 * 		true if it finds the loop and removed the loop, false otherwise.
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static boolean removeLoop(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node slow = linkedList.getHead();
		if (slow == null)
			return false;
		Node fast = slow.next;

		while (fast != null && fast.next != null) {
			if (slow == fast)
				break;
			slow = slow.next;
			fast = fast.next.next;
		}

		if (slow == fast) {
			slow = linkedList.getHead();
			while (slow != fast.next) {
				slow = slow.next;
				fast = fast.next;
			}

			fast.next = null;
	
			return true;
		}

		return false;
	}
	
	/**
	 * Insert an element in the sorted linked list. This method does not
	 * explicitly check whether the list is sorted or not.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param elt
	 * 		element to insert
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	static void insertInSortedList(LinkedList linkedList, int elt) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node current = linkedList.getHead();
		if (current == null || current.getData() > elt)
			linkedList.insertFront(elt);
		else {
			while (current.next != null && current.next.getData() < elt)
				current = current.next;
			
			Node newNode = LinkedList.createNode(elt);
			newNode.next = current.next;
			current.next = newNode;
		}
	}
	
	/**
	 * Merge the two sorted linked lists. This method does not explicitly test
	 * whether the lists are sorted or not.
	 * 
	 * @param linkedList1
	 *  	first linked list
	 * @param linkedList2
	 *  	second linked list
	 * 
	 * @return 
	 * 		merged list
	 * 
	 * @throws NullPointerException
	 *  	if one of the linked list is null
	 */
	public static LinkedList merge(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");
		LinkedList mergedList = new LinkedList();
		Node head1 = linkedList1.getHead();
		Node head2 = linkedList2.getHead();
		if (head1 == null && head2 == null)
			return mergedList;

		// Dummy node
		Node head = LinkedList.createNode(Integer.MIN_VALUE);
		Node current = head;
		while (head1 != null && head2 != null) {
			if (head1.getData() <= head2.getData()) {
				current.next = new Node(head1.data);
				head1 = head1.next;
			} else {
				current.next = new Node(head2.data);
				head2 = head2.next;
			}
			current = current.next;
		}

		if (head1 != null) {
			while(head1 != null) {
				current.next = new Node(head1.data);
				head1 = head1.next;
				current = current.next;
			}
		} else {
			while(head2 != null) {
				current.next = new Node(head2.data);
				head2 = head2.next;
				current = current.next;
			}
		}
		
		mergedList.setHead(head.next);

		return mergedList;
	}
	
	/**
	 * Merge the two sorted linked lists. This method does not explicitly test
	 * whether the lists are sorted or not.
	 * 
	 * @param linkedList1
	 *  	first linked list
	 * @param linkedList2
	 *   	second linked list
	 * 
	 * @return 
	 * 		merged list
	 * 
	 * @throws NullPointerException
	 *  	if one of the linked list is null
	 */
	static LinkedList mergeRecursive(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");
	
		LinkedList linkedList = new LinkedList();
		linkedList.setHead(merge(linkedList1.getHead(), linkedList2.getHead()));
	
		return linkedList;
	}
	
	/**
	 * Recursively merge the linked list.
	 * 
	 * @param head1
	 * 		head node of first list
	 * @param head2
	 * 		head node of second list
	 * 
	 * @return
	 * 		new head of the list
	 */
	private static Node merge(Node head1, Node head2) {
		Node head = null;
		if(head1 == null)
			return head2;
		else if(head2 == null)
			return head1;
		if(head1.getData() <= head2.getData()) {
			head = new Node(head1.data);
			head.next = merge(head1.next, head2);
		} else {
			head = new Node(head2.data);
			head.next = merge(head1, head2.next);
		}
		
		return head;
	}

	/**
	 * Merge the second list into the first one at alternate positions. The
	 * nodes in second linked list will be removed while merge and excess nodes
	 * from linked list 2 are omitted.
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @throws NullPointerException
	 * 		if any of the linked list is null
	 */
	public static void mergeAtAlternatePosition(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");
	
		Node list1Curr = linkedList1.getHead();
		Node list2Curr = linkedList2.getHead();

		Node list1Next, list2Next;

		while (list1Curr != null && list2Curr != null) {

			list1Next = list1Curr.next;
			list2Next = list2Curr.next;

			list2Curr.next = list1Next;
			list1Curr.next = list2Curr;

			list1Curr = list1Next;
			list2Curr = list2Next;
		}
		
		linkedList2.setHead(list2Curr);
	}
	
	/**
	 * Given a linked list, tests whether the length is odd.
	 * 
	 * @param linkedList
	 * 		input linked list
	 * 
	 * @return
	 * 		true if the length is odd, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static boolean hasOddLength(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node head = linkedList.getHead();
		while(head != null && head.next != null)
			head = head.next.next;
		if(head == null)
			return false;
	
		return true;
	}
	
	/**
	 * Check whether the linked list is palindrome or not.
	 * It checks the numbers in the linked list to verify the list is palindrome.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @return
	 * 		true if the linked list is palindrome, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static boolean isPalindrome(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();

		if (head == null || head.next == null)
			return true;

		Node slow = linkedList.getHead();
		Node fast = slow;

		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		Node slowHead = slow.next;
		slow.next = null;

		// Reverse second part
		Node node1 = slowHead;
		Node node2 = node1.next;

		while (node1 != null && node2 != null) {
			Node temp = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = temp;
		}

		slowHead.next = null;

		// Compare two sublists
		Node mid = (node2 == null ? node1 : node2);
		Node first = head;
		while (mid != null) {
			if (mid.getData() != first.getData())
				return false;

			mid = mid.next;
			first = first.next;

		}

		return true;
	}
	
	/**
	 * Checks whether there is an intersection node that is common in both linked lists.
	 * 
	 * @param linkedList1
	 * 		linked list 
	 * @param linkedList2
	 * 		linked list
	 * 
	 * @return
	 * 		the intersection node if any, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if one or the two linked lists are null
	 */
	static Node getIntersectionNode(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");
		
		int len1 = 0;
		int len2 = 0;

		Node head1 = linkedList1.getHead();
		Node head2 = linkedList2.getHead();

		if (head1 == null || head2 == null)
			return null;

		while (head1.next != null) {
			len1++;
			head1 = head1.next;
		}

		while (head2.next != null) {
			len2++;
			head2 = head2.next;
		}

		// Check the end nodes are intersecting
		if (head1 != head2)
			return null;

		head1 = linkedList1.getHead();
		head2 = linkedList2.getHead();

		int diff = 0;
		if (len1 > len2) {
			diff = len1 - len2;
			while (diff > 0) {
				head1 = head1.next;
				diff--;
			}
		} else {
			diff = len2 - len1;
			while (diff > 0) {
				head2 = head2.next;
				diff--;
			}
		}

		while (head1 != head2) {
			head1 = head1.next;
			head2 = head2.next;
		}

		return head1;
	}
	
	/**
	 * Remove duplicates from a linked list.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static void removeDuplicates(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Map<Integer, Integer> eltMap = new HashMap<>();
		Node current = linkedList.getHead();

		if (current == null)
			return;

		while (current.next != null) {
			if (eltMap.containsKey(current.next.getData())) {
				current.next = current.next.next;
			} else {
				eltMap.put(current.getData(), 1);
				current = current.next;
			}
		}
	}
	
	/**
	 * Remove duplicates from a sorted linked list.
	 * This method does not check whether the list is sorted or not.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	static void removeDuplicatesInSortedList(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		
		Node current = linkedList.getHead();

		if (current == null)
			return;

		while (current.next != null) {
			if (current.getData() == current.next.getData()) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
	}
	
	/**
	 * Performs pairwise swap of the elements in a given linked list.
	 * Starting form the front, each consecutive pairs are swapped.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static void swapInPairs(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();
		int temp = 0;
		while (head != null && head.next != null) {
			temp = head.getData();
			head.setData(head.next.getData());
			head.next.setData(temp);
			head = head.next.next;
		}
	}
	
	/**
	 * This method swaps the kth node from both ends.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param k
	 * 		position of node from both ends
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 * @throws IllegalArgumentException
	 * 		if position k is less than or equal to zero
	 */
	public static void swapKth(LinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("'k' must be > 0.");

		int n = linkedList.size();

		if (n < k)
			throw new IllegalArgumentException("'k' must be <= " + n);

		if (2 * k - 1 == n)
			return;

		Node front = linkedList.getHead();
		Node frontPrev = null;
		for (int i = 1; i < k; i++) {
			frontPrev = front;
			front = front.next;
		}

		Node end = linkedList.getHead();
		Node endPrev = null;
		for (int i = 1; i < n - k + 1; i++) {
			endPrev = end;
			end = end.next;
		}

		if (frontPrev != null)
			frontPrev.next = end;

		if (endPrev != null)
			endPrev.next = front;

		Node temp = front.next;
		front.next = end.next;
		end.next = temp;

		if (k == 1)
			linkedList.setHead(end);

		if (k == n)
			linkedList.setHead(front);
	}
	
	/**
	 * Swaps the node references in the linked list represented by data1 and
	 * data2. If there are multiple data items, it picks the first occurrence.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param data1
	 * 		first element data
	 * @param data2
	 * 		second element data
	 * 
	 * @throws NullPointerException
	 *  	if the linked list is null
	 * @throws IllegalArgumentException
	 *    	if one or two elements given are not exists.
	 */
	public static void swap(LinkedList linkedList, int data1, int data2) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		if (data1 == data2)
			return;

		Node prevElt1 = null, currentElt1 = linkedList.getHead();
		Node prevElt2 = null, currentElt2 = linkedList.getHead();

		while (currentElt1 != null && currentElt1.getData() != data1) {
			prevElt1 = currentElt1;
			currentElt1 = currentElt1.next;
		}

		while (currentElt2 != null && currentElt2.getData() != data2) {
			prevElt2 = currentElt2;
			currentElt2 = currentElt2.next;
		}

		String errorMesg = "Elements(s)";
		if (currentElt1 == null)
			errorMesg = errorMesg + " " + data1;
		if (currentElt2 == null)
			errorMesg += " " + data2;

		if (currentElt1 == null || currentElt2 == null)
			throw new IllegalArgumentException(errorMesg + " not found");

		if (prevElt1 == null) {
			// elt2 is the root
			prevElt2.next = currentElt1;
			Node temp = currentElt2.next;
			currentElt2.next = currentElt1.next;
			currentElt1.next = temp;
			linkedList.setHead(currentElt2);
		} else if (prevElt2 == null) {
			// elt1 is the root
			prevElt1.next = currentElt2;
			Node temp = currentElt2.next;
			currentElt2.next = currentElt1.next;
			currentElt1.next = temp;
			linkedList.setHead(currentElt1);
		} else {
			prevElt1.next = currentElt2;
			prevElt2.next = currentElt1;
			Node temp = currentElt2.next;
			currentElt2.next = currentElt1.next;
			currentElt1.next = temp;
		}
	}
	
	/**
	 * Make tail node as head of the list.
	 * 
	 * @param linkedList
	 * 		Linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static void makeTailAsHead(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();

		if (head == null || head.next == null)
			return;

		while (head.next.next != null)
			head = head.next;

		linkedList.insertFront(head.next.getData());
		head.next = null;
	}
	
	/**
	 * Union of the given two unsorted linked lists.
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @return
	 * 		union of the given two linked lists
	 * 
	 * @throws NullPointerException
	 * 		if any of the given linked lists are null
	 */
	public static LinkedList union(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");

		Map<Integer, Integer> unionMap = new HashMap<Integer, Integer>();

		LinkedList unionList = new LinkedList();

		Node head = linkedList1.getHead();

		while (head != null) {
			if (!unionMap.containsKey(head.getData())) {
				unionList.insertLast(head.getData());
				unionMap.put(head.getData(), 0);
			}
			head = head.next;
		}

		head = linkedList2.getHead();

		while (head != null) {
			if (!unionMap.containsKey(head.getData())) {
				unionList.insertLast(head.getData());
				unionMap.put(head.getData(), 0);
			}
			head = head.next;
		}

		return unionList;
	}
	
	/**
	 * Intersection of two linked lists.
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @return
	 * 		new linked list which is intersection of given lists
	 * 
	 * @throws NullPointerException
	 * 		if one of the input linked list is null
	 */
	public static LinkedList intersection(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");

		Map<Integer, Integer> intrsectMap = new HashMap<Integer, Integer>();

		LinkedList intrsectList = new LinkedList();

		Node head = linkedList1.getHead();

		while (head != null) {
			intrsectMap.put(head.getData(), 0);
			head = head.next;
		}

		head = linkedList2.getHead();

		while (head != null) {
			if (intrsectMap.containsKey(head.getData())) {
				intrsectList.insertLast(head.getData());
				intrsectMap.remove(head.getData());
			}
			head = head.next;
		}

		return intrsectList;
	}
	
	/**
	 * Intersection of two sorted linked lists.
	 * This method does not check whether the lists are sorted or not.
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @return
	 * 		new linked list which is intersection of given lists
	 * 
	 * @throws NullPointerException
	 * 		if one of the input linked list is null
	 */
	static LinkedList intersectionSorted(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList can not be null.");

		LinkedList newList = new LinkedList();
		Node head1 = linkedList1.getHead();
		Node head2 = linkedList2.getHead();
		while (head1 != null && head2 != null) {
			if (head1.getData() == head2.getData()) {
				newList.insertLast(head1.getData());
				head1 = head1.next;
				head2 = head2.next;
			} else if (head1.getData() < head2.getData()) {
				head1 = head1.next;
			} else {
				head2 = head2.next;
			}
		}

		return newList;
	}
	
	/**
	 * Deletes alternate nodes from the given linked list.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static void removeAlternateNodes(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node head = linkedList.getHead();
	
		while (head != null && head.next != null) {
			head.next = head.next.next;
			head = head.next;
		}
	}
	
	/**
	 * Delete the nodes, if there is a greater node right side.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static void removeNodesHasNextGreater(LinkedList linkedList) {
	
		reverse(linkedList);
		
		Node current = linkedList.getHead();

		Node maxNode = linkedList.getHead();
		Node temp = null;

		while (current != null && current.next != null) {
			if (current.next.getData() < maxNode.getData()) {
				temp = current.next;
				current.next = temp.next;
				temp = null;
			} else {
				current = current.next;
				maxNode = current;
			}
		}
		
		reverse(linkedList);
	}
	
	/**
	 * Removes the n nodes by skipping first m nodes in the given linked list.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param n
	 * 		nodes to remove
	 * @param m
	 * 		initial nodes to skip
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 * @throws IllegalArgumentException
	 * 		if either n or m is negative
	 */
	public static void remove(LinkedList linkedList, int n, int m) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (n <= 0 || m <= 0)
			throw new IllegalArgumentException("'m' and 'n' must be > 0.");

		Node current = linkedList.getHead();

		// Skip till one less node
		for (int i = 1; i < m && current != null; i++)
			current = current.next;

		if (current == null)
			return;
		
		// Skip one more node
		Node temp = current;
		for (int i = 1; i <= n + 1 && temp != null; i++)
			temp = temp.next;
		
		current.next = temp;
		current = temp;
	}
	
	/**
	 * Splits the given linked list into two halves, each list contains the alternative nodes.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @return
	 * 		array of split lists
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static LinkedList[] split(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
	
		LinkedList[] listArray = new LinkedList[2];
		listArray[0] = new LinkedList();
		listArray[1] = new LinkedList();
		
		Node head = linkedList.getHead();
		while (head != null) {
			listArray[0].insertLast(head.getData());
			head = head.next;
			if (head != null) {
				listArray[1].insertLast(head.getData());
				head = head.next;
			}
		}

		return listArray;
	}
	
	/**
	 * Check whether the two linked lists are identical.
	 * It checks the data in the linked list for identical test.
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @return
	 * 		true if they are identical, false otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static boolean areIdentical(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node head1 = linkedList1.getHead();
		Node head2 = linkedList2.getHead();
		while (head1 != null && head2 != null) {
			if (head1.getData() != head2.getData())
				return false;
			
			head1 = head1.next;
			head2 = head2.next;
		}

		return head1 == null && head2 == null;
	}
	
	/**
	 * Performs pairwise swap (reverse) of the elements by reference in a given
	 * linked list. Also called reverse by reference.
	 * 
	 * @param linkedList
	 * 		given linked list
	 * 
	 * @throws NullPointerException
	 *    	if the linked list is null
	 */
	public static void reverseInPair(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();
		if (head == null || head.next == null)
			return;

		// Dummy node
		Node dummyHead = new Node(Integer.MIN_VALUE);
		dummyHead.next = head;

		Node first = head;
		Node second = head.next;

		Node prev = dummyHead;
		while (first != null && second != null) {
			prev.next = second;

			Node temp = second.next;
			second.next = first;
			prev = first;
			first.next = temp;

			first = first.next;

			if (temp != null)
				second = temp.next;
		}

		// Set the new head
		linkedList.setHead(dummyHead.next);
	}

	/**
	 * Performs pairwise swap of the elements by reference in a given linked list. 
	 * Recursive version of {@link #reverseInPair(LinkedList)}.
	 * 
	 * @param linkedList
	 * 		given linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	static void reverseInPairRecursive(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
	
		linkedList.setHead(reverseInPairRecursive(linkedList.getHead()));
		
	}
	
	/**
	 * Recursively reverse the list in pairs.
	 * 
	 * @param head
	 * 		current head
	 * 
	 * @return
	 * 		the new head
	 */
	private static Node reverseInPairRecursive(Node head) {
		if(head == null || head.next == null)
			return head;
		
		// Store for next recursive call
        Node nextHead = head.next.next;
 
        Node currHead = head.next;
 
        head.next.next = head;
 
        head.next = reverseInPairRecursive(nextHead);
 
        return currHead;
	}
	
	/**
	 * Reverse the elements in groups of a given size. If the group size is more
	 * than the linked list size, it simply reverses whole linked list.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param size
	 * 		size of the group
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 * @throws IllegalArgumentException
	 * 		if the given size is negative
	 */
	public static void reverseInGroup(LinkedList linkedList, int size) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (size <= 0)
			throw new IllegalArgumentException("Size must be positive number > 0.");
		if (size == 1)
			return;

		linkedList.setHead(reverseInGroup(linkedList.getHead(), size));
	}
	
	/**
	 * Reverse the linked list in group of size.
	 * 
	 * @param head
	 * 		head node
	 * @param size
	 * 		group size
	 * 
	 * @return
	 * 		new head of linked list
	 */
	private static Node reverseInGroup(Node head, int size) {
		Node current = head;
		Node prevCurrent = head;
		Node prevTail = null;
		while (current != null) {
			int count = 0;
			Node tail = null;
			while (current != null && count < size) {
				Node next = current.next;
				current.next = tail;
				tail = current;
				current = next;
				count++;
			}
			
			if (prevTail != null)
				prevTail.next = tail;
			else
				head = tail;

			prevTail = prevCurrent;
			prevCurrent = current;
		}

		return head;
	}

	/**
	 * Reverse the elements in groups of given size. 
	 * Recursive version of {@link #reverseInGroup(LinkedList, int)}
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param size
	 * 		size of the group
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 * @throws IllegalArgumentException
	 * 		if the given size is negative
	 */
	static void reverseInGroupRecursive(LinkedList linkedList, int size) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if(size <= 0 )
			throw new IllegalArgumentException("Size must be positive number > 0.");
		if(size == 1)
			return;

		linkedList.setHead(reverseInGroupRecursive(linkedList.getHead(), size));
	}

	/**
	 * Reverse the linked list in groups.
	 * 
	 * @param head
	 * 		current head node
	 * @param size
	 * 		size of group
	 * 
	 * @return
	 * 		new head
	 */
	private static Node reverseInGroupRecursive(Node head, int size) {
		Node current = head;
		Node next = null;
		Node prev = null;

		int count = 0;

		while (count < size && current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}
		
		if (next != null)
			head.next = reverseInGroupRecursive(next, size);

		return prev;
	}
	
	/**
	 * Reverse the alternative k nodes in the given linked list. This method
	 * reverses every other k nodes in the linked list. If the group size is
	 * more than the linked list size, it simply reverses whole linked list.
	 * 
	 * @param linkedList
	 *            linked list
	 * @param size
	 *            size of the group
	 * 
	 * @throws NullPointerException
	 *             if the input linked list is null
	 * @throws IllegalArgumentException
	 *             if the size is is negative
	 */
	public static void reverseAlternateK(LinkedList linkedList, int size) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (size < 0)
			throw new IllegalArgumentException("Size must be positive number.");
		if (size <= 1)
			return;

		linkedList.setHead(reverseAlternateK(linkedList.getHead(), size));
	}

	/**
	 * Reverse the alternative k nodes recursively.
	 * 
	 * @param head
	 * 		current head
	 * @param size
	 * 		size of the group
	 * 
	 * @return
	 * 		new head node
	 */
	private static Node reverseAlternateK(Node head, int k) {
		Node current = head;
		Node next = null, prev = null;
		int count = 0;

		while (current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}

		if (head != null) {
			head.next = current;
		}

		count = 0;
		while (count < k - 1 && current != null) {
			current = current.next;
			count++;
		}

		if (current != null) {
			current.next = reverseAlternateK(current.next, k);
		}

		return prev;
	}
	
	/**
	 * Segregates the even odd nodes. Assumption: The nodes are such that each
	 * even node follows an odd node.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 */
	public static void segregateEvenOdd(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		Node head = linkedList.getHead();
		if (head == null || head.next == null)
			return;

		Node tail = linkedList.getTail();

		Node prev = null, next = null;

		Node last = tail;
		Node newHead = null;

		while (head != tail) {
			if (head.data % 2 == 1) {
				next = head.next;
				last.next = head;
				last = head;
				last.next = null;
				head = next;
				if (null != prev) {
					prev.next = next;
				}
			} else {
				if (newHead == null)
					newHead = head;

				prev = head;
				head = head.next;
			}
		}

		linkedList.setHead(newHead);
	}
	
	/**
	 * Given a list which contains, 0s and 1s and 2s, it arranges the list items
	 * such that the numbers in the result list are 0s followed by 1s followed
	 * by 2s.
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the input linked list is null
	 * 
	 */
	public static void segregate0s1s2s(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		int count[] = { 0, 0, 0 };

		Node current = linkedList.getHead();

		while (current != null) {
			count[current.getData()] += 1;
			current = current.next;
		}

		int i = 0;
		current = linkedList.getHead();

		while (current != null) {
			if (count[i] == 0)
				++i;
			else {
				current.setData(i);
				--count[i];
				current = current.next;
			}
		}
	}
	
	/**
	 * Add two numbers represented by linked lists. This method does not
	 * validate that the linked list numbers are only digits.
	 * 
	 * <pre>
	 * 	List 1:	5->6->3,    number - 563
	 * 	List 2:	8->4->2,    number - 824
	 * 	Result:	1->4->0->5, number - 1405
	 * </pre>
	 * 
	 * @param linkedList1
	 *            first linked list
	 * @param linkedList2
	 *            second linked list
	 * 
	 * @return resultant linked list
	 * 
	 * @throws NullPointerException
	 *             if the linked lists are null
	 */
	public static LinkedList add(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");

		LinkedList result = new LinkedList();

		Node node1 = linkedList1.getHead();
		Node node2 = linkedList2.getHead();

		if (node1 == null) {
			while (node2 != null) {
				result.insertLast(node2.getData());
				node2 = node2.next;
			}

			return result;
		}

		if (node2 == null) {
			while (node1 != null) {
				result.insertLast(node1.getData());
				node1 = node1.next;
			}

			return result;
		}

		Node head = null;

		int size1 = linkedList1.size();
		int size2 = linkedList2.size();

		int[] carry = new int[1];

		if (size1 == size2) {
			head = addSameSize(node1, node2, carry);
			result.setHead(head);
		} else {
			int diff = Math.abs(size1 - size2);

			// Make first list large
			if (size1 < size2) {
				Node temp = node1;
				node1 = node2;
				node2 = temp;
			}

			// Skip nodes
			Node curr = node1;
			while (diff != 0) {
				curr = curr.next;
				diff--;
			}

			head = addSameSize(curr, node2, carry);
			result.setHead(head);
			// Add Skipped nodes with carry to the result
			addSkippedNodesWithCarry(node1, curr, carry, result);
		}

		// Add carry to the front
		if (carry[0] > 0)
			result.insertFront(carry[0]);
		
		return result;
	}
	
	/**
	 * Add two lists given their starting nodes of the same size.
	 * 
	 * @param head1
	 * 		first list head
	 * @param head2
	 * 		second  list head
	 * @param carry
	 * 		holds/propagates carry
	 * 
	 * @return
	 * 		new node which represents the result list.
	 */
	private static Node addSameSize(Node head1, Node head2, int[] carry) {
		if (head1 == null)
			return null;

		int sum;

		Node result = new Node(0);

		result.next = addSameSize(head1.next, head2.next, carry);

		sum = head1.getData() + head2.getData() + carry[0];
		carry[0] = sum / 10;
		sum = sum % 10;

		result.setData(sum);

		return result;
	}

	/**
	 * After adding lists of same size using {@link #addReverse(LinkedList, LinkedList)}, 
	 * we need to add the skipped nodes along with the array.
	 * 
	 * @param head1
	 * 		the bigger list
	 * @param cur
	 * 		node to which we need to add from bigger list
	 * @param carry
	 * 		holds/propagates carry
	 * @param result
	 * 		the result list to which we need to add
	 */
	private static void addSkippedNodesWithCarry(Node head1, Node cur, int[] carry, LinkedList result) {
		int sum;

		// Process till skipped node
		if (head1 != cur) {
			addSkippedNodesWithCarry(head1.next, cur, carry, result);

			sum = head1.getData() + carry[0];
			carry[0] = sum / 10;
			sum %= 10;

			result.insertFront(sum);
		}
	}

	/**
	 * Add two numbers represented by linked lists. 
	 * Assumption: the numbers are in linked list are in reverse order.
	 * 
	 * <pre>
	 * 	List 1: 	5->6->3, number - 365
	 * 	List 2: 	8->4->2, number - 248
	 * 	Result:		3->1->6, number - 613
	 * </pre>
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @return
	 * 		resultant linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked lists are null
	 */
	static LinkedList addReverse(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node node1 = linkedList1.getHead();
		Node node2 = linkedList2.getHead();

		Node head = null;
		Node prev = null;
		Node temp = null;

		int carry = 0, sum;

		while (node1 != null || node2 != null) {

			sum = carry + (node1 != null ? node1.getData() : 0) + (node2 != null ? node2.getData() : 0);
			carry = (sum >= 10) ? 1 : 0;
			sum = sum % 10;

			temp = new Node(sum);

			if (head == null)
				head = temp;
			else
				prev.next = temp;

			prev = temp;

			if (node1 != null)
				node1 = node1.next;

			if (node2 != null)
				node2 = node2.next;
		}

		if (carry > 0)
			temp.next = new Node(carry);

		return new LinkedList(head);
	}
	
	/**
	 * Rotate the given linked list counter-clockwise by k nodes. 
	 * It does nothing if the number of elements are less than k.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param k
	 * 		rotation size
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 * @throws IllegalArgumentException
	 * 		if the given rotation is invalid ( <= 0 )
	 */
	public static void rotate(LinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (k < 0)
			throw new IllegalArgumentException("Rotation size must be positive number.");

		if (k == 0)
			return;

		Node current = linkedList.getHead();

		for (int i = 1; i < k && current != null; i++)
			current = current.next;

		if (current == null)
			return;

		Node kthNode = current;

		while (current.next != null)
			current = current.next;

		current.next = linkedList.getHead();
		linkedList.setHead(kthNode.next);
		kthNode.next = null;
	}
	
	/**
	 * Given a linked list rotate the list to the right by k places. 
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param k
	 * 		size of rotation
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null 
	 * @throws IllegalArgumentException
	 * 		if the given rotation is invalid ( <=0 )
	 */
	public static void rotateRight(LinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (k < 0)
			throw new IllegalArgumentException("Rotation size must be positive number.");

		Node head = linkedList.getHead();
		int count = 0;
		Node newHead = head;

		while (head.next != null) {
			head = head.next;
			count++;
		}
		
		count++;

		int newLen = Math.abs(count - (k % count));
		if (newLen == 0) {
			linkedList.setHead(newHead);
			return;
		}

		head.next = newHead;

		while (newLen-- > 0)
			head = head.next;

		newHead = head.next;
		head.next = null;

		linkedList.setHead(newHead);
	}

	/**
	 * Given a linked list of size n, it returns the last element from the
	 * beginning such that n % k == 0. For n = 16, k = 3, it returns the 15th
	 * node from beginning.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param k
	 * 		modulus
	 * 
	 * @return 
	 * 		modular node if present, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null 
	 * @throws IllegalArgumentException
	 * 		if k is <= 0
	 */
	static Node getModularFromBegin(LinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("Modulus must be > 0.");
		
		Node head = linkedList.getHead();
		Node modularNode = null;
		
		int i = 0;
		while (head != null) {
			if (i % k == 0)
				modularNode = head;
			head = head.next;
			i += 1;
		}

		return modularNode;
	}
	
	/**
	 * Given a linked list of size n, it returns the last element from the end
	 * such that n % k == 0. It is equal to the kth node from end.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param k
	 * 		modulus
	 * 
	 * @return 
	 * 		modular node if present, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null 
	 * @throws IllegalArgumentException
	 * 		if k is <= 0
	 */
	static Node getModularFromEnd(LinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("Modulus must be > 0.");
		
		Node head = linkedList.getHead();
		Node modularNode = head;

		for (int i = 0; i < k; i++) {
			if (head == null)
				return null;
			else
				head = head.next;
		}

		while (head != null) {
			head = head.next;
			modularNode = modularNode.next;
		}

		return modularNode;
	}
	
	/**
	 * Given a linked list of size n, it returns the fractional node (n/k) th.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param k
	 * 		fraction
	 * 
	 * @return 
	 * 		modular node if present, null otherwise
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 * @throws IllegalArgumentException
	 * 		if k is <= 0
	 */
	static Node getFractionalNode(LinkedList linkedList, int k) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");
		if (k <= 0)
			throw new IllegalArgumentException("k must be > 0.");
		
		Node head = linkedList.getHead();
		Node fractionalNode = head;

		int i = 0;
		while (head != null) {
			if (i % k == 0) {
				if (fractionalNode == null)
					fractionalNode = head;
				else
					fractionalNode = fractionalNode.next;
			}
			i += 1;
			head = head.next;
		}

		return fractionalNode;
	}

	/**
	 * Given a linked list returns the random node. 
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 * @throws IllegalStateException
	 * 		if the linked list is empty
	 */
	public static int random(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();
		if (head == null)
			throw new IllegalStateException("LinkedList is empty.");
		
		Random random = new Random();
		int result = head.getData();
		Node current = head;
		
		for (int n = 2; current != null; n++) {
			// Update if probability is 1/n
			if (random.nextInt(n) % n == 0)
				result = current.getData();

			current = current.next;
		}

		return result;
	}

	/**
	 * Given a linked list, it rearranges the list elemetns such that 
	 * List: l1, l2, l3,...., ln-1, ln becomes L: l1, ln, l2, ln-1,....
	 * 
	 * @param linkedList
	 * 		linked list
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static void rearrange(LinkedList linkedList) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();
		Node slow = head, fast = slow.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		Node node1 = head;
		Node node2 = slow.next;
		slow.next = null;

		// Reverse the second half
		node2 = reverse(node2);

		// Dummy node holds the merged list
		head = new Node(Integer.MIN_VALUE);

		Node current = head;
		while (node1 != null || node2 != null) {

			if (node1 != null) {
				current.next = node1;
				current = current.next;
				node1 = node1.next;
			}

			if (node2 != null) {
				current.next = node2;
				current = current.next;
				node2 = node2.next;
			}
		}

		linkedList.setHead(head.next);
	}
	
	/**
	 * Given a linked list, it partitions the list such that nodes less than the given 
	 * element comes before nodes greater than element. Preserve relative order.
	 * 
	 * @param linkedList
	 * 		linked list
	 * @param elt
	 * 		partition element
	 * 
	 * @throws NullPointerException
	 * 		if the linked list is null
	 */
	public static void partition(LinkedList linkedList, int elt) {
		if (linkedList == null)
			throw new NullPointerException("LinkedList can not be null.");

		Node head = linkedList.getHead();

		if (head == null)
			return;

		Node newHead = new Node(Integer.MIN_VALUE);
		newHead.next = head;

		Node moreHead = new Node(Integer.MIN_VALUE);

		Node prev = newHead;
		Node less = head;
		Node more = moreHead;

		while (less != null) {
			if (less.data < elt) {
				less = less.next;
				prev = prev.next;
			} else {
				more.next = less;
				prev.next = less.next;

				less = prev.next;
				more = more.next;
			}
		}

		more.next = null;
		prev.next = moreHead.next;

		linkedList.setHead(newHead.next);
	}
	
	/**
	 * Get the linked list containing the max sum path from the two lists.
	 * We need to switch the current list when the current element is > sum till now.
	 * <pre>
	 * Input:  [1, 3, 30, 90, 120, 240, 511], [0, 3, 12, 32, 90, 125, 240, 249]
	 * Output: [1, 3, 12, 32, 90, 125, 240, 511]
	 * </pre>
	 * 
	 * @param linkedList1
	 * 		first linked list
	 * @param linkedList2
	 * 		second linked list
	 * 
	 * @throws NullPointerException
	 * 		if any of the linked list is null
	 */
	public static LinkedList maxSumList(LinkedList linkedList1, LinkedList linkedList2) {
		if (linkedList1 == null || linkedList2 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");

		Node result = null;

		Node head1 = linkedList1.getHead();
		Node head2 = linkedList2.getHead();

		Node pre1 = head1, curr1 = head1;
		Node pre2 = head2, curr2 = head2;

		while (curr1 != null || curr2 != null) {
			int sum1 = 0, sum2 = 0;

			while (curr1 != null && curr2 != null && curr1.getData() != curr2.getData()) {

				if (curr1.getData() < curr2.getData()) {
					sum1 += curr1.getData();
					curr1 = curr1.next;
				} else {
					sum2 += curr2.getData();
					curr2 = curr2.next;
				}
			}

			if (curr1 == null) {
				while (curr2 != null) {
					sum2 += curr2.getData();
					curr2 = curr2.next;
				}
			}

			if (curr2 == null) {
				while (curr1 != null) {
					sum1 += curr1.getData();
					curr1 = curr1.next;
				}
			}

			if (pre1 == head1 && pre2 == head2)
				result = (sum1 > sum2) ? pre1 : pre2;
			else {
				if (sum1 > sum2)
					pre2.next = pre1.next;
				else
					pre1.next = pre2.next;
			}

			pre1 = curr1;
			pre2 = curr2;

			if (curr1 != null)
				curr1 = curr1.next;

			if (curr2 != null)
				curr2 = curr2.next;
		}

		return new LinkedList(result);
	}
	
	/**
	 * Check whether there exists a triplet (one element from each list), which
	 * sum equal to the given number. This method assumes that the lists are
	 * already sorted. Time: O(n*n). If the lists are not sorted, then they can
	 * be sorted with O(n * log n) and can use this method.
	 * 
	 * @param linkedList1
	 *            first linked list
	 * @param linkedList2
	 *            second linked list
	 * @param linkedList3
	 *            third linked list
	 * 
	 * @return
	 * 		an array containing one element from each list
	 * 
	 * @throws NullPointerException
	 * 		if any of the linked lists are null
	 * @throws NoSuchElementException
	 * 		if there is no combination found
	 */
	public static int[] findTriplet(LinkedList linkedList1, LinkedList linkedList2, LinkedList linkedList3,
			int givenNumber) {

		if (linkedList1 == null || linkedList2 == null || linkedList3 == null)
			throw new NullPointerException("LinkedList(s) can not be null.");

		Node node1 = linkedList1.getHead();

		while (node1 != null) {
			Node node2 = linkedList2.getHead();
			Node node3 = linkedList3.getHead();

			while (node2 != null && node3 != null) {
				int sum = node1.data + node2.data + node3.data;
				if (sum == givenNumber) {
					return new int[] {node1.data, node2.data, node3.data};
				} else if (sum < givenNumber) {
					node2 = node2.next;
				} else {
					node3 = node3.next;
				}
			}
			node1 = node1.next;
		}

		throw new NoSuchElementException("There are no such combination exists");
	}
	
	/**
	 * Compares the given two strings lexicographically using linked list.
	 * It returns 0 if both strings are same, 1 if first string is greater, 
	 * -1 if second string is greater.
	 * It constructs <code>LinkedList</code>s to compare the strings.
	 * 
	 * @param string1
	 * 		first string
	 * @param string2
	 * 		second string
	 * 
	 * @return
	 * 		0 if both strings are same, 1 if first string is greater, -1 if second string is greater.
	 * 
	 * @throws NullPointerException
	 * 		if one or two strings are null
	 */
	public static int compare(String string1, String string2) {

		LinkedList list1 = new LinkedList();
		for (char chr : string1.toCharArray())
			list1.insertLast(chr);

		LinkedList list2 = new LinkedList();
		for (char chr : string2.toCharArray())
			list2.insertLast(chr);

		Node node1 = list1.getHead();
		Node node2 = list2.getHead();

		while (node1 != null && node2 != null && node1.getData() == node2.getData()) {
			node1 = node1.next;
			node2 = node2.next;
		}

		if (node1 != null && node2 != null)
			return node1.getData() > node2.getData() ? 1 : -1;

		if (node1 != null && node2 == null)
			return 1;

		if (node1 == null && node2 != null)
			return -1;

		return 0;
	}
}