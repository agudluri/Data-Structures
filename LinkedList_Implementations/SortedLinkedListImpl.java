import java.util.Iterator;

public class SortedLinkedListImpl<E> extends LinkedListImpl<E> {

	public SortedLinkedListImpl() {
		super();
	}

	/**
	 * Adds element e in sorted order in the collection class (linked list).
	 * 
	 * @param e, the element to be added
	 * 
	 * @return a boolean true if e was added successfully, false otherwise.
	 */
	public boolean add(E e) {

		if (is_empty()) {
			return add(Where.BACK, e);
		}

		if (((Comparable<E>) e).compareTo(head.element) < 0) {
			return add(Where.FRONT, e);
		}

		else {
			int i = 0;
			LinkEntry<E> current = head;
			Iterator<E> itr = this.iterator();
			while (itr.hasNext()) {
				if (((Comparable<E>) e).compareTo(itr.next()) < 0)
					break;
				current = current.next;
				i++;
			}
			return add(Where.MIDDLE, i, e);
		}
	}

	/**
	 * Print the sorted linked list in reverse order using recursion.
	 */
	public void reverse_print(LinkEntry<E> h) {
		if (h != null) {
			reverse_print(h.next);
			System.out.print(h.element);
		}
	}

	//overriden method
	public void reverse_print(){
		reverse_print(head);
	}

}
