import java.util.*;

public class QueueLinkedListImpl<E> implements QueueInterface<E>,Iterable<E> {

	private LinkEntry<E> head;
	private LinkEntry<E> tail;
	private int num_elements;

	
	/**
	 * Adds an element to the end of the queue.
	 * 
	 * @param e, the element to be added.
	 * 
	 * @return void
	 **/
	public void add(E element) {

		LinkEntry<E> ne = new LinkEntry<E>();
		ne.element = element;

		if (head == null && tail == null)
			head = tail = ne;
		else {
			tail.next = ne;			//adding the element after the tail
			tail = ne;
		}
		num_elements++;
	}

	
	/**
	 * Removes an element from the start of the queue.
	 * 
	 * @param void.
	 * 
	 * @return the element that has been removed.
	 **/
	public E remove() {
		if (size() == 0)
			throw new NoSuchElementException();
		LinkEntry<E> temp = head;
		head = head.next;	//removing the element at the head
		num_elements--;
		return temp.element;
	}

	
	/**
	 * Gives you a peek at the first element of the queue.
	 * 
	 * @param void.
	 * 
	 * @return the element at the front of the queue.
	 **/
	public E peek() {
		if (size() == 0)
			throw new NoSuchElementException();
		return head.element;		//returns the element at the head
	}

	
	/**
	 * Gives you the size of the queue.
	 * 
	 * @param void.
	 * 
	 * @return the size/length of the queue.
	 **/
	public int size() {
		return num_elements;
	}

	/* ------------------------------------------------------------------- */
	/* Inner classes */
	protected class LinkEntry<E> {
		protected E element;
		protected LinkEntry<E> next;

		protected LinkEntry() {
			element = null;
			next = null;
		}
	}

	public Iterator<E> iterator() /* From Interface Iterable */

		return new LinkedListIterator();
	}

	protected class LinkedListIterator implements Iterator<E> {
		protected LinkEntry<E> current;

		protected LinkedListIterator() {
			current = head;
		}

		/*
		 * A method to return the element at the current position and to point
		 * the current pointer to the next element
		 * 
		 * @return The current element
		 */
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E lol = current.element;
			current = current.next;
			return lol;
		}

		/*
		 * A method to check if there are any following elements
		 * 
		 * @return A boolean denoting if there's a next element or not
		 */
		public boolean hasNext() {
			return current != null;
		}

	}
}
