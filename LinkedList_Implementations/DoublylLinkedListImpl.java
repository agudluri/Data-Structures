import java.util.*;

public class DoublyLinkedListImpl<E> implements CollectionInterface<E>, Iterable<E> {

	private LinkEntry<E> head;
	private LinkEntry<E> tail;
	private int num_elements = 0;

	public DoublyLinkedListImpl() {
		head = tail = null;
	}

	public boolean is_empty() {
		if (head == null)
			return true;
		return false;
	}

	public boolean is_full() {
		return false;
	}

	public int size() {
		return num_elements;
	}

	/*
	 * Adds element e at the end of the linked list.
	 * 
	 * @param e, the element that needs to be added
	 * 
	 * @return a boolean denoting if the element has been added or not
	 */
	public boolean add(E e) {
		add(Where.BACK, e);
		return true;
	}

	/**
	 * Removes the element at the given index.
	 * 
	 * @param i,
	 *            an integer denoting the index
	 * 
	 * @return the removed element at that index
	 */
	public E remove(int i) {

		if (i < 1 || i > size())
			return null;

		else if (i == 1) {		// i == 1, it is the head. Remove the head.
			LinkEntry<E> temp = head;
			head = head.next;
			head.previous = null;
			num_elements--;
			return temp.element;

		} else if (i == size()) {		//if i == size, it is the tail. Remove it.
			LinkEntry<E> temp = tail;
			tail = tail.previous;
			tail.next = null;
			num_elements--;
			return temp.element;

		} else {		// The element must be somewhere in the middle.

			int j = 1;
			LinkEntry<E> current = head;
			while (j<i) {
				current = current.next;
				j++;
			}
			LinkEntry<E> temp = current;
			current.previous.next = current.next;
			current = current.next;
			current.previous = temp.previous;
			num_elements--;
			return temp.element;
		}
	}

	/**
	 * Determines if e is in the collection.
	 * 
	 * @param e,
	 *            The element to be checked for existence
	 * 
	 * @return A boolean value denoting the existence of the said element
	 */
	public boolean contains(E e) {
		boolean lol = false;
		Iterator<E> itr = this.iterator(head);
		while (itr.hasNext()) {
			if (itr.next().equals(e))
				lol = true;
		}
		return lol;
	}

	/**
	 * Method to add an element to either the front of the linked list or the
	 * end of the linked list, depending on the value of the parameter where.
	 * 
	 * @param where,
	 *            denoting where the element needs to be added
	 * @param e,
	 *            the element that needs to be added
	 * 
	 * @return a boolean value denoting if the element has been added or not
	 */
	public boolean add(Where where, E e) {

		if (where == Where.MIDDLE)
			return false;

		LinkEntry<E> ne = new LinkEntry<E>();
		ne.element = e;

		if (head == null && tail == null) {
			head = tail = ne;
			num_elements++;
			return true;
		}

		else if (where == Where.BACK) {
			tail.next = ne;
			ne.previous = tail;
			tail = ne;
			num_elements++;
			return true;
		} else {
			ne.next = head;
			head.previous = ne;
			head = ne;
			num_elements++;
			return true;
		}
	}

	/**
	 * A method to insert elements in the middle
	 * 
	 * @param where,
	 *            denoting where the element needs to be added
	 * @param index,
	 *            denoting the position the element needs to be added at
	 * @param e,
	 *            the element that needs to be added
	 * 
	 * @return a boolean value denoting if the element has been added or not
	 */
	public boolean add(Where where, int index, E e) {
		
		if (index < 1 || index > size())
			return false;

		if (where != Where.MIDDLE)
			return false;
		else {
			LinkEntry<E> ne = new LinkEntry<E>();
			ne.element = e;

			int j = 1;
			int k = size();

			// To make it easier, if the element is before the middle, I'm traversing from the head
			// and if it is after the middle, I'm traversing from the tail
			
			
			if (index <= ((j + k) / 2)) {

				LinkEntry<E> temp;
				LinkEntry<E> current = head;
				while (j < index) {
					current = current.next;
					j++;
				}
				temp = current.next;
				ne.next = temp;
				current.next = ne;
				temp.previous = ne;
				ne.previous = current;
			}

			if (index > ((j + k) / 2)) {
				LinkEntry<E> temp;
				LinkEntry<E> current = tail;
				while (j > index) {
					current = current.previous;
					j--;
				}
				temp = current.previous;
				ne.previous = temp;
				current.previous = ne;
				temp.next = ne;
				ne.next = current;
			}

			num_elements++;
			return true;
		}
	}

	/**
	 * Method to print the doubly linked list starting at the beginning.
	 */
	public void print_from_beginning() {
		Iterator<E> itr = this.iterator(head);
		while (itr.hasNext()) {
			System.out.print(itr.next());
		}
		System.out.println();
	}

	/**
	 * Method to print the doubly linked list starting the end.
	 */
	public void print_from_end() {
		Iterator<E> itr = this.iterator(tail);
		while (((ListIterator<E>) itr).hasPrevious()) {
			System.out.print(((ListIterator<E>) itr).previous());
		}
		System.out.println();
	}

	/* ------------------------------------------------------------------- */
	/* Inner classes */
	protected class LinkEntry<E> {
		protected E element;
		protected LinkEntry<E> next;
		protected LinkEntry<E> previous;

		protected LinkEntry() {
			element = null;
			next = previous = null;
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public ListIterator<E> iterator(LinkEntry<E> something) /* From Interface Iterable */
	{
		return new DoubleLinkedListIterator(something);
	}

	protected class DoubleLinkedListIterator implements ListIterator<E> {
		
		protected LinkEntry<E> current;

		protected DoubleLinkedListIterator(LinkEntry<E> something) {
			current = something;
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
		 * A method to return the element at the current position and to point
		 * the current pointer to the previous element
		 * 
		 * @return The current element
		 */
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			E lol = current.element;
			current = current.previous;
			return lol;
		}

		/*
		 * A method to check if there are any following elements
		 * 
		 * @return A boolean denoting if there's a next element or not
		 */
		public boolean hasNext() {
			return current!= null;
		}

		/*
		 * A method to check if there are any elements before
		 * 
		 * @return A boolean denoting if there's a next element or not
		 */
		public boolean hasPrevious() {
			return current!= null;
		}

		public void remove() {
			/**
			 * Leave it blank for now.
			 */
			return;
		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
