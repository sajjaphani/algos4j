package org.algos4j.list.ext;

/**
 * A test class to test merging of two generic linked lists holding <code>Integer</code>s.
 * 
 * @author psajja
 *
 */
public class MergeSortedListsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr1 = {10, 20, 30, 40};
		int[] arr2 = {5, 15, 25, 35};

		LinkedList<Integer> list1 = new LinkedList<>();
		
		for(int elt : arr1) {
			list1.insertLast(elt);
		}
		System.out.println("Linked List 1");
		System.out.println(list1);
		
		LinkedList<Integer> list2 = new LinkedList<>();
		for(int elt : arr2) {
			list2.insertLast(elt);
		}
		System.out.println("Linked List 2");
		System.out.println(list2);
		
		@SuppressWarnings("unchecked")
		LinkedList<Integer> [] array = new LinkedList[2];
		array[0] = list1;
		array[1] = list2;
		System.out.println("Merged List");
		LinkedList<Integer> mergedList = LinkedListUtil.mergeSortedLists(array);
		mergedList.print();
	}
}
