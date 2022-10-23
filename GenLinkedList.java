import java.util.*;
import java.lang.*;

public class GenLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	int size = 0;

	
	public void addFront(T e) {
		Node<T> newNode = new Node<T>(e);
		// If the head is null there is not linked list and to add it and set the tail and head to the new node
		if(head == null) {
			head = newNode;
			tail = head;
		}
		// Otherwise just change the head node
		else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	public void addEnd(T e) {
		Node<T> newNode = new Node<T>(e);
		// if the tail is null then their is no LL and to create the LL and connect the head and tail to the new node
		if(tail ==  null) {
			head = newNode;
			tail = head;
		}
		// otherwise set the tail to the new node
		else {
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}
	
	public T removeFront() {
		Node<T> olddata = head;
		
		// if list is empty
		if(head == null) {
			throw new NoSuchElementException();
		}
		
		// if there is one node
		if (head == tail) {
			olddata = head;
			head = tail = null;
		}
		
		// if there is more than one node
		else {
			olddata = head;
			head = head.next;
		}
		size--;
		return olddata.element;
	}
	
	public T removeEnd() {
		T olddata;
		
		// if list is empty
		if(head == null) {
			throw new NoSuchElementException();
		}
		
		// if there is one node
		if (head == tail) {
			olddata = head.element;
			head = tail = null;
		}
		
		// if there is more than one node
		else {
			Node<T> current = head;
			while(current.next != tail) {
				current = current.next;
			}
			olddata = tail.element;
			tail = current;
			tail.next = null;			
		}
		size--;
		return olddata;
		
	}
	
	// This method sets the value of a node at the index 
	public T set(int index, T value) {
		// check if the index is out of the bounds of the LL
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Node<T> current = head;
		T oldValue = head.element;
		// iterate through the LL until we hit the index
	    for (int i = 0; i < size; i++) {
	    	// if we hit the index then change the value and store the old one to be returned
	    	if (i == index) {
	    	oldValue = current.element;
	        current.element = value;
	        break;
	      }
	    	// otherwise keep iterating
	    	oldValue = current.element;
	    	current = current.next;
	    }
		
		return oldValue;
	}
	
	// This method gets the value of what ever node is at the given index
	public T get(int index) {
		// check the bounds of the index
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException();
		}
	    Node<T> current = head;
	    // iterate till we hit that index
	    for (int i = 0; i < index; i++) {
	      current = current.next;
	    }
	    // return the value of the index
	    return current.element;
	}
	
	// This is for swapping two nodes at two indexes
	public void swap(int index1, int index2) {
		// If they are the same index then nothing to be done
		if (index1 == index2)
            return;
		
		// If the first index is out of the bounds of the linked list
		if (index1 < 0 || index1 > size - 1) {
			System.out.println("Index" + index1 + "is out of the bounds of the linked list");
			throw new ArrayIndexOutOfBoundsException();
		}
		// If the second index is out the of the bounds of the linked list
		if (index2 < 0 || index2 > size - 1) {
			System.out.println("Index" + index2 + "is out of the bounds of the linked list");
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Node<T> prevIndex1 = null, currIndex1 = head;
		// iterate till we hit the first index
		for (int i = 0; i < index1; i++) {
		    prevIndex1 = currIndex1;
			currIndex1 = currIndex1.next;
		}
		Node<T> prevIndex2 = null, currIndex2 = head;
		// iterate till we hit the second index
		for (int i = 0; i < index2; i++) {
		    prevIndex2 = currIndex2;
			currIndex2 = currIndex2.next;
		}
		
		if (prevIndex1 != null)
            prevIndex1.next = currIndex2;
        else // make y the new head
            head = currIndex2;
		
		if (prevIndex2 != null)
            prevIndex2.next = currIndex1;
        else // make x the new head
            head = currIndex1;
		
		Node<T> temp = currIndex1.next;
        currIndex1.next = currIndex2.next;
        currIndex2.next = temp;
		
		
		
	}
	
	public void shift(int offset) {
		if (offset == 0)
            return;
        Node<T> current = head;
 
        if (offset > 0) {
        	int count = 1;
        	
        	// If the linked list is empty then there is nothing to shift
        	if (head == null) {
        		return;
        	}
        	// if the offset is larger than the size of the linked list then take the mod of it
        	if (offset > size)
                offset = offset % size;
        	
        	// iterate till we hit our offset node and as long as that node isn't null
        	while (count < offset && current != null) {
        		current = current.next;
        		count++;
        	}

        	// if that offset node is null then we return since it is invalid
        	if (current == null)
        		return;
 
        	// create a variable for the starting offset node to which current is currently pointing at
        	Node<T> kthNode = current;

        	// current iterates to the last node of the linked list
        	while (current.next != null)
           	current = current.next;
 
        	// connect the last node to the first node
        	current.next = head;

        	// change the head to the node after the offset
        	head = kthNode.next;
        	
        	// set the offset node as the last node
        	kthNode.next = null;
        	
        	// set the tail node to the offset node
        	tail = kthNode;
        }
        else {
        	
        	// Since the offset will be negative to indicate clockwise shift we take the absolute value so we can utilize the value
        	offset = Math.abs(offset);
        	
        	// If the linked list is empty then there is nothing to shift
        	if (head == null) {
        		return;
        	}
        	
        	// if the offset is larger than the size of the linked list then take the mod of it
        	if (offset > size)
                offset = offset % size;
        	
        	// We want to start the offset from the tail of the linked list
        	offset = size - offset;
        	
        	// if the offset is 0 or is equal to the size of the linked list then there is no need to shift anything
        	if (offset == 0 || offset == size)
                return;
        	
        	// iterate current till it hits the offset or null
            int count = 1;
            while (count < offset && current != null)
            {
                current = current.next;
                count++;
            }
            
            // if the current is null then the offset is equal to the size of the linked list
            if (current == null)
                return;
            
            //  create a node to hold the offset node
            Node<T> kthNode = current;
            // create a temp node to help connect the last and first node
            Node<T> temp = tail;
            // link the last node to connect to the first node
            temp.next = head;
            // set the head to the the node after the offset
            head = kthNode.next;
            // make the offset the last node
            kthNode.next = null;
            tail = kthNode;
           
        	
        }
        
	}
	
	public void removeMatching(T e) {
		Node<T> previous = null;
		Node<T> current = head;
		
		// Check to see if the head has the matching element, if it does then remove it and check the new head
		while (current.element == e && current != null ) 
        {
            head = current.next; 
            current = head;
            size--;
        }
		
		// Iterate through the linked list
		while (current != null) 
        {
            // iterate through the linked list to remove the element until we hit the end of the linked list or hit a node we need to remove
            while (current != null && current.element != e ) 
            {
            	// keeping track of the previous node so if we need to delete one we can rejoin the list together
                previous = current;
                current = current.next;
            }
  
            // If we hit the end of the linked list then there is nothing more to do
            if (current == null)
                return;
  
            // Remove the element from the linked list 
            previous.next = current.next;
  
            // Update the current node to keep iterating through the linked list
            current = previous.next;
            size--;
        }
		tail = previous;
	}
	
	public void erase(int index, int numberOfElements) {
		Node<T> previous = null;
		Node<T> current = head;
		
		// Check to see if the index plus the number of elements goes out of the bounds of the LL
		if (index + numberOfElements > size) {
			System.out.println("That index and the number of elements is out of the bounds of the linked list");
			throw new ArrayIndexOutOfBoundsException();
		}
		// iterate to the index while keeping track of the previous index so we can rejoin the LL
		for (int i = 0; i < index; i++) {
		    previous = current;
			current = current.next;
		}
		int startingSize = size;
		int left = numberOfElements;
		// erase the number of elements needed by unlinking them
		for(int i = 0; i <= numberOfElements; i++) {
			// If the number of elements is equal to the original size of the LL then set the last one to null and re set the tail
			if(index + numberOfElements == startingSize) {
				for(i = 0; i < left; i++) {
				previous.next = current;
				tail = previous;
				current = null;
				}
				break;
			}
			else {
				previous.next = current;
				current = current.next;
				size--;
			}
			left--;
			
		}
		
	}
	
	public void inserList(List <T> list, int index){

	   if (list.isEmpty())
	      return;
	   
	   if(index > size) {
		   System.out.println("Index" + index + "is out of the bounds of the linked list");
			throw new ArrayIndexOutOfBoundsException();
	   }

	   // Check head to see if there is a linked list already if not create one and begin inserting
	   if (head == null) {
	      
		  Node <T> node = new Node <T> (list.get(0));
	      head = node;
	      
	      // after creating first node iterate through remaining values and create nodes for them
	      for (int k = 1; k < list.size(); k++) {
	         node.next = new Node <T> (list.get(k));
	         node = node.next;
	      }
	   } 
	   // if the insertion index is the start of the list then save the old head node and add to the list before joining it back up
	   else if (index == 0) {
	      Node <T> node = new Node <T> (list.get(0));
	      Node <T> oldHead = head;
	      head = node;
	      // iterate adding all the new nodes before joining up the old head node to the rest of the list
	      for (int k = 1; k < list.size(); k++) {
	         node.next = new Node <T> (list.get(k));
	         node = node.next;
	      }
	      node.next = oldHead;

	   } 
	   // If there is a linked list already and it isn't being inserted at the head
	   else {
	      Node <T> previous = null;
	      Node <T> current = head;
	      // iterate to the index point
	      for (int i = 0; i < index; i++) {
	         previous = current;
	         current = current.next;
	      }
	      // start insertion at the index value
	      Node <T> begin = previous;
	      // save the old next node in the linked list
	      Node <T> old_node = begin.next;
	      
	      // create nodes for every value in the list
	      for (T itemvalue: list) {

	         Node <T> GenNode = new Node<T>(itemvalue);
	         begin.next = GenNode;
	         begin = GenNode;
	      }
	      // rejoin up the linked list to the rest of it
	      begin.next = old_node;
	   }

	}


	// This is just a testing thing and isn't apart of the project
	public void reverse() {
		if ( head == null || head == tail) 
			return;
		
		Node<T> p1 = null;
		Node<T> p2 = head;
		Node<T> p3 = head.next;
		
		while (p3 != null) {
			p2.next = p1;
			p1 = p2;
			p2 = p3;
			p3 = p3.next;
		}
		
		p2.next = p1;
		
		Node<T> temp = head;
		head = tail;
		tail = temp;
	}
	

	
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		
		Node<T> current = head;
	    while (current != null){
	    	sb.append(current.element + " ");
	    	current = current.next;
	    }
	      sb.append("]");

	    return sb.toString();
	}
	
	
	
	private static class Node<T> {
	    T element;
	    Node<T> next;

	    public Node(T element) {
	      this.element = element;
	    }
	  }
	
	
	public static void main(String args[]) {
		GenLinkedList<Integer> list = new GenLinkedList<Integer>();
		
		
		System.out.println("Starting with an empty list");
		System.out.println(list);

		System.out.println("\nDemonstrating addFront");
		list.addFront(0);
		System.out.println(list);
		
		System.out.println("\nDemonstrating addEnd");
		list.addEnd(1);
		System.out.println(list);
		
		System.out.println("\nDemonstrating addEnd with multiple values");
		for(int i = 2; i < 10; i++) {
			list.addEnd(i);
		}
		System.out.println(list);
		
		System.out.println("\nDemonstrating removeFront");
		list.removeFront();
		System.out.println(list);
		
		System.out.println("\nDemonstrating removeEnd");
		list.removeEnd();
		System.out.println(list);
		
		
		System.out.println("\nDemonstrating set at index 2 with a value of 0");
		System.out.println("The old value of index 2 was = " + list.set(2, 0));
		System.out.println(list);
		
		
		System.out.println("\nDemonstrating get at index 4");
		System.out.println("The value in index 4 is = " + list.get(4));
		System.out.println(list);
		
		System.out.println("\nDemonstrating swap with index 1 and 6");
		list.swap(1, 6);
		System.out.println(list);
		
		
		System.out.println("\nDemonstrating shift by shifting -2");
		list.shift(-2);
		System.out.println(list);
		
		System.out.println("\nDemonstrating shift by shifting 1");
		list.shift(1);
		System.out.println(list);
		
		System.out.println("\nDemonstrating removeMatching by first adding another 5 to the front");
		list.addFront(5);
		System.out.println(list);
		System.out.println("Then removing all 5's");
		list.removeMatching(5);
		System.out.println(list);
			
		System.out.println("\nDemonstrating erase by removing 3 nodes starting at index 1");
		list.erase(1, 3);
		System.out.println(list);
		
		System.out.println("\nDemonstrating insertList by adding the list [-5,-4,-3,-2,-1] starting at index 2");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
		for(int i = -5; i < 0; i++ ) {
			arrayList.add(i);
		}
		
		list.inserList(arrayList, 2);
		System.out.println(list);
		
		
	}
	

}