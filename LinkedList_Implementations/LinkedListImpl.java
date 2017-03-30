import java.util.*;

public class LinkedListImpl<E> implements CollectionInterface<E>, Iterable<E> {

	protected LinkEntry<E> head;
	protected LinkEntry<E> tail;
	protected int num_elements = 0;

	public LinkedListImpl() {
		head = tail = null;
	}

	/**
	 * Methods inherited from CollectionInterface
	 */
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
		else if (i == 1) {
			LinkEntry<E> temp = head;
			head = head.next;
			num_elements--;
			return temp.element;
		} else if (i == size()) {
			LinkEntry<E> prev = null;
			LinkEntry<E> current = head;
			Iterator<E> itr = this.iterator();
			while (itr.hasNext()) {
				prev = current;
				current = current.next;
			}
			prev.next = null;
			tail = prev;
			num_elements--;
			return current.element;
		} else {
			int j = 1;
			LinkEntry<E> prev = null;
			LinkEntry<E> current = head;
			while (j < i) {
				prev = current;
				current = current.next;
				j++;
			}
			LinkEntry<E> temp = current;
			prev.next = current.next;
			current = current.next;
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
		Iterator<E> itr = this.iterator();
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
			tail = ne;
			num_elements++;
			return true;
		} else {
			ne.next = head;
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
			LinkEntry<E> current = head;
			while (j < index) {
				current = current.next;
				j++;
			}
			ne.next = current.next;
			current.next = ne;
			num_elements++;
			return true;
		}
	}

	/**
	 * A method to print the contents of the LinkedList
	 */
	public String toString() {

		StringBuilder result = new StringBuilder();
		Iterator<E> itr = this.iterator();
		while (itr.hasNext()) {
			result.append(itr.next().toString());
		}
		return result.toString();
	}

	/* ------------------------------------------------------------------- */
	/* Inner classes */
	protected class LinkEntry<E> {
		protected E element;
		protected LinkEntry<E> next;
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
