public class BinaryTree<E> {
	private int index; // This is the index of the next node to insert in the
						// tree.
	private TreeNode<E> root; /* The absolute root of the tree. */

	/*
	 * Default constructor.
	 */
	public BinaryTree() {
		index = 0;
		root = null;
	}

	/*
	 * Recursively calculates the size of the tree; i.e., the number of elements
	 * in the binary tree.
	 */
	public int size() {
		return size_p(root);
	}

	/*
	 * Gives you the size of the tree, i.e, the number of nodes
	 * 
	 * @param t, which would the root of the tree
	 * @return a integer with the size of the tree
	 */
	private int size_p(TreeNode<E> t) {

		int num_nodes = 0;
		if (t != null) {
			num_nodes = size_p(t.get_left()) + 1 + size_p(t.get_right());
		}
		return num_nodes;
	}

	/*
	 * Recursively does an in-order traversal of the tree
	 */
	public void inorder() {
		inorder_p(root);
	}

	/*
	 * A method to traverse the tree in an in-order fashion
	 * 
	 * @param t, which would be the root of the tree
	 * @return nothing
	 */
	private void inorder_p(TreeNode<E> t) {
		if (t != null) {
			inorder_p(t.get_left());
			System.out.println(t.info);
			inorder_p(t.get_right());
		}
	}

	/*
	 * Recursively does an preorder traversal of the tree
	 */
	public void preorder() {
		preorder_p(root);
	}

	/*
	 * A method to traverse the tree in pre-order fashion
	 * 
	 * @param t, which would be the root of the tree
	 * @return nothing
	 */
	private void preorder_p(TreeNode<E> t) {
		if (t != null) {
			System.out.println(t.info);
			preorder_p(t.get_left());
			preorder_p(t.get_right());
		}
	}

	/*
	 * Recursively does an postorder traversal of the tree
	 */
	public void postorder() {
		postorder_p(root);
	}

	/*
	 * A method to traverse the tree in post-order fashion
	 * 
	 * @param t, which would be the root of the tree
	 * @return nothing
	 */
	private void postorder_p(TreeNode<E> t) {
		if (t != null) {
			postorder_p(t.get_left());
			postorder_p(t.get_right());
			System.out.println(t.info);
		}
		return;
	}

	/*
	 * Adds a node to the binary tree. Nodes are added from the left. Thus, each
	 * level in the tree is filled out before a new level is started.
	 * 
	 * @param info, the element to be added
	 * @return nothing
	 */
	public void add(E info) {
		TreeNode<E> node = new TreeNode<E>(info);

		if (index == 0) {
			root = node;
		} else if (index == 1) {
			root.left = node;
		} else if (index == 2) {
			root.right = node;
		} else if (index == 3) {
			root.left.left = node;
		} else if (index == 4) {
			root.left.right = node;
		} else if (index == 5) {
			root.right.left = node;
		}

		index++;
	}

	/*
	 * Each node in the tree is an object of this type.
	 */
	protected static class TreeNode<E> {
		private TreeNode<E> left, right;
		private E info;

		public TreeNode(E info) {
			left = right = null;
			this.info = info;
		}

		public TreeNode<E> get_left() {
			return left;
		}

		public TreeNode<E> get_right() {
			return right;
		}
	}
	
}
