import java.util.*;

public class StackLinkedListImpl<E> implements StackInterface<E>, Iterable<E> {
	private LinkEntry<E> head;
	private int num_elements;

	public StackLinkedListImpl() {
		head = null;
		num_elements = 0;
	}

	/**
	 * Pushes an element onto the stack.
	 * 
	 * @param e, the element to be pushed.
	 * 
	 * @return void
	 **/
	public void push(E e) {
		
		LinkEntry<E> ne = new LinkEntry<E>();
		ne.element = e;
		
		if (head == null)
            head = ne;
		else{
			ne.next = head;		//Adding the element before the head
			head = ne;
		}
		num_elements++;
	}

	/*
	 * Pops the top most element of the stack. 
	 * 
	 * @return The element that has been popped
	 */
	public E pop() {
		if (head==null) throw new EmptyStackException();
		
		E temp = head.element;
		head = head.next;			//returning the element at the head
		num_elements--;
		return temp;
	}

	/*
	 * Gives you a peek at the topmost element of the stack but does not remove it.
	 * 
	 * @return The element you wanna peek at.
	 */
	public E peek() {
		if (head==null) throw new EmptyStackException();
		
		return head.element;
	}

	/*
	 * Gives you the size of the stack
	 * 
	 * @return an integer with the size of the current stack
	 */
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
	{
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
			if (!hasNext()) throw new NoSuchElementException();
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

