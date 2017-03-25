public class BinarySearchTreeArrayImpl<E> {

	protected TreeNode<E>[] tree;
	protected int root, size;
	protected boolean rootgot = false;

	@SuppressWarnings("unchecked")
	public BinarySearchTreeArrayImpl(int lol) {
		tree = (TreeNode<E>[]) new TreeNode[lol*50];
		root = -1;
		size = 0;
	}

	/**
	 * A method to return the size of this BinarySearchTree object.
	 * 
	 * @param nothing
	 *
	 * @return an integer denoting the size of this BinarySearchTree object.
	 *
	 */
	public int size() {
		return size;
	}

	/**
	 * A recursive method to conduct an in-order traversal of a binary-search tree.
	 * 
	 * @return A string resulting from the in-order traversal of the BST.
	 */
	public String inorder(int start, int end) {
		String temp = "";
		
		if(tree[start]!=null){
		temp += inorder(start * 2 + 1, end);

		temp += " " + tree[start].element.toString();

		temp += inorder( start * 2 + 2, end);
		}

		return temp;
	}

	/**
	 * Determines if there is at least one element in this BinarySearchTree
	 * object that equals a specified element.
	 *
	 * @param obj
	 *            - the element sought in this BinarySearchTree object.
	 *
	 * @return true - if there is an element in this BinarySearchTree object the
	 *         equals obj; otherwise, return false.
	 *
	 * @throws ClassCastException
	 *             - if obj cannot be compared to the elements in this
	 *             BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if obj is null.
	 * 
	 */
	public boolean contains(Object obj) {
		return getTreeNode(obj) != null;
	}

	/**
	 * A method to add the specified element to the Binary Search Tree.
	 *
	 * @param element
	 *            - the element that is to be added to this BinarySearchTree
	 *            object.
	 *
	 * @return true - if this BinarySearchTree object changed as a result of
	 *         this method call (that is, if element was actually inserted);
	 *         otherwise, return false.
	 *
	 * @throws ClassCastException
	 *             - if element cannot be compared to the elements already in
	 *             this BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if element is null.
	 *
	 */
	public boolean add(E element) {

		if (root == -1) {
			if (element == null)
				throw new NullPointerException();
			root = 0;
			tree[root] = new TreeNode<E>(element, -1);
			size++;
			return true;
		} // empty tree
		else {
			int temp = root, comp;

			while (true) {
				comp = ((Comparable) element).compareTo(tree[temp].element);

				if (comp == 0)
					return false;

				if (comp < 0) {
					if (tree[temp].left != -1) {
						temp = tree[temp].left;
					} else {
						tree[temp*2 + 1] = new TreeNode<E>(element, temp);
						tree[temp].left = temp*2 + 1;
						size++;
						return true;
					} // temp.left == null
				} else if (tree[temp].right != -1) {
					temp = tree[temp].right;
				} else {
					tree[temp*2 + 2] = new TreeNode<E>(element, temp);
					tree[temp].right = temp*2 + 2;

					size++;
					return true;
				} // temp.right == null
			}
		}
	}

	/**
	 * Ensures that this BinarySearchTree object does not contain a specified
	 * element.
	 *
	 * @param obj
	 *            - the object whose absence is ensured in this BinarySearchTree
	 *            object.
	 *
	 * @return true - if this BinarySearchTree object changed as a result of
	 *         this method call (that is, if obj was actually removed);
	 *         otherwise, return false.
	 *
	 * @throws ClassCastException
	 *             - if obj cannot be compared to the elements already in this
	 *             BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if obj is null.
	 *
	 */
	public boolean remove(Object obj) {
		TreeNode<E> e = getTreeNode(obj);
		if (e == null)
			return false;
		deleteTreeNode(e);
		return true;
	}

	/**
	 * Finds the TreeNode object that houses a specified element, if there is
	 * such an TreeNode. The worstTime(n) is O(n), and averageTime(n) is O(log
	 * n).
	 *
	 * @param obj
	 *            - the element whose TreeNode is sought.
	 *
	 * @return the TreeNode object that houses obj - if there is such an
	 *         TreeNode; otherwise, return null.
	 *
	 * @throws ClassCastException
	 *             - if obj is not comparable to the elements already in this
	 *             BinarySearchTree object.
	 * @throws NullPointerException
	 *             - if obj is null.
	 *
	 */
	protected TreeNode<E> getTreeNode(Object obj) {
		int temp = root, comp;
		while (temp != -1) {
			comp = ((Comparable) obj).compareTo(tree[temp].element);
			if (comp == 0)
				return tree[temp];
			else if (comp < 0)
				temp = tree[temp].left;
			else
				temp = tree[temp].right;
		} // while
		return null;
	}

	/**
	 * Deletes the element in a specified TreeNode object from this
	 * BinarySearchTree.
	 * 
	 * @param p
	 *            - the TreeNode object whose element is to be deleted from this
	 *            BinarySearchTree object.
	 *
	 * @return the TreeNode object that was actually deleted from this
	 *         BinarySearchTree object.
	 *
	 */
	protected TreeNode<E> deleteTreeNode(TreeNode<E> p) {
        int PutNull = -1;
        int Replace = -1;
        int parent = 0;
        size--;
        
        // If p has two children, replace p's element with p's successor's
        // element, then make p reference that successor.
        if (p.left != -1 && p.right != -1) 
        {
            TreeNode<E> s = successor (p);
            p.element = s.element;
            p = s;            
        } // p had two children
        
        // At this point, p has either no children or one child.
        TreeNode<E> replacement;
        
        if (p.left != -1)
        {
            replacement = tree[p.left];            
            PutNull = p.left;            
        }
        else
        {
            replacement = tree[p.right];
            PutNull = p.right;            
        }
        
        // If p has at least one child, link replacement to p.parent.
        if (replacement != null) 
        {
            // If the node to be replaced has child nodes, take care of shifting
            // them at the appropriate location using the index variables for 
            // tracking 
            if (replacement.left != -1)
            {
                Replace = PutNull;
                PutNull = replacement.left;
                replacement.left = Replace;
            }
            else if (replacement.right != -1)
            {
                Replace = PutNull;
                PutNull = replacement.right;
                replacement.right = Replace;
            }
            // The parent variable is used later to set the parent of the node 
            // being shifted
            if (Replace != - 1)
                parent = replacement.parent;
            
            replacement.parent = p.parent;
            if (p.parent == -1)
                tree[root] = replacement;
            else if (p == tree[tree[p.parent].left])
                tree[tree[p.parent].left]  = replacement;
            else
                tree[tree[p.parent].right] = replacement;
            
             if ((replacement.left != -1) || (replacement.right != -1))
             {
                 // If the above replaced node had a child take care of shifting
                 // the child node. The index nodes to be replaced were taken
                 // care of a bit above and the left and right indexes set
                 tree[Replace] = tree[PutNull];    
                 tree[Replace].parent = parent;
             }
            tree[PutNull] = null;
        }// p has at least one child  
        else if (p.parent == -1)
        {
            tree[root] = null;
            root = -1;
        }    
        else 
        {
            if (p == tree[tree[p.parent].left])
                tree[tree[p.parent].left] = null;
            else
                tree[tree[p.parent].right] = null;        
        } // p has a parent but no children
        return p;
        
        
		/*size--;

		// If node has two children, replace node's element with node's
		// successor's
		// element, then make node reference that successor.
		if (node.left != -1 && node.right != -1) {
			TreeNode<E> s = successor(node);
			node.element = s.element;
			node = s;
		} // node had two children

		// At this point, node has either no children or one child.
		TreeNode<E> replacement;

		if (node.left != -1) {
			replacement = tree[node.left];
		} else {
			replacement = tree[node.right];
		}

		// If node has at least one child, link replacement to node.parent.
		if (replacement != null) {
			replacement.parent = node.parent;
			if (node.parent == -1)
				tree[root] = replacement;
			else if (node == tree[tree[node.parent].left])
				tree[tree[node.parent].left] = replacement;
			else
				tree[tree[node.parent].right] = replacement;
		} // node has at least one child

		else if (node.parent == -1) {
			tree[root] = null;
			root = -1;
		}

		else {
			if (node == tree[tree[node.parent].left])
				tree[tree[node.parent].left] = null;
			else
				tree[tree[node.parent].right] = null;
		} // node has a parent but no children

		return node;*/
	}

	/**
	 * Finds the successor of a specified TreeNode object in this
	 * BinarySearchTree. The worstTime(n) is O(n) and averageTime(n) is
	 * constant.
	 *
	 * @param e
	 *            - the TreeNode object whose successor is to be found.
	 *
	 * @return the successor of e, if e has a successor; otherwise, return null
	 *
	 */
	protected TreeNode<E> successor(TreeNode<E> e) {
		if (e == null) {
			return null;
		} else if (e.right != -1) {
			// successor is leftmost Entry in right subtree of e
			TreeNode p = tree[e.right];
			while (p.left != -1)
				p = tree[p.left];
			return p;
		} // e has a right child
		else {
			// go up the tree to the left as far as possible, then go up
			// to the right.
			TreeNode<E> p = tree[e.parent];
			TreeNode<E> ch = e;
			while (p != null && ch == tree[p.right]) {
				ch = p;
				p = tree[p.parent];
				if (p.parent == -1 && rootgot)
                {
					rootgot = false;
                    p = null;
                    break;
                }   
                if (p.parent == -1 && !rootgot)
                {
                	rootgot = true;                    
                }  
			} // while
			return p;
		} // e has no right child
	}

	protected static class TreeNode<E> {
		E element;
		int parent, left, right;

		public TreeNode(E element, int parent, int left, int right) {
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public TreeNode(E element, int parent) {
			this.element = element;
			this.parent = parent;
			this.left = -1;
			this.right = -1;

		}
	}

	/**
	 * print - Prints the array based tree as a table with 4 columns.
	 */
	void print() {
		int indx;

		for (indx = 0; indx < size; indx++) {
			System.out.println(
					tree[indx].element + ", " + tree[indx].parent + ", " + tree[indx].left + ", " + tree[indx].right);
		}
	}

}
