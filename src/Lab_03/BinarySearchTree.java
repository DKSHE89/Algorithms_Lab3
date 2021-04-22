package Lab_03;

import java.util.Iterator;

import Lab_03.RandomizedBinaryTrees.RNode;

public class BinarySearchTree<Key extends Comparable, Val> extends
		AbstractST<Key, Val> implements Iterable<Key> {
	// inner class
	protected class Node {// node of BST
		Key key; // key-value pair
		Val val;
		Node left, right;// reference to the left and to the right subtree
		int Weight;// weight of a node

		Node(Key key, Val val) {// constructor for a node
			this.key = key;
			this.val = val;
			Weight = 1;
		}
	}

	protected Node root;// a root of a BST

	public Val get(Key key) {// get a value, found by the key
		Node x = find(key);// find the necessary node
		if (x != null)// if such node doesn't exist return 0
			return x.val;
		return null;
	}

	private Node find(Key key) {// look for a node by the key
		Node x = root;// start from the root
		while (x != null) {// go from the tree to the leaves
			int cmp = key.compareTo(x.key);// look for a matching key
			if (cmp == 0)// return root if such node is not found
				return x;
			else if (cmp < 0)// if the key is less then the current key go to
								// the left subtree
				x = x.left;
			else
				x = x.right;// otherwise to the right one
		}
		return null;
	}

	public void put(Key key, Val val) {// add a new node
		// test
		root = put(root, key, val);

	}

	protected Node put(Node x, Key key, Val val) {// adding a new node,
													// recursive function

		if (x == null)// if the tree is empty, create a new node
			return new Node(key, val);
		int cmp = key.compareTo(x.key);// compare the key to the current node's
										// key
		if (cmp == 0)// if they match, overwrite the value
			x.val = val;
		else if (cmp < 0){// if its less than a current node's key, add a new
							// node to the left current node's subtree
			x.left = put(x.left, key, val);
			updateWeight(x);
			if (x.right == null || (x.left.Weight > x.right.Weight)){
				x = rotR(x);
				updateWeight(x);
				updateWeight(x.right);
			}
		}
		
		else{// otherwise to the right one
			x.right = put(x.right, key, val);	
			updateWeight(x);
			if (x.left == null || (x.right.Weight > x.left.Weight)){
				x = rotL(x);
				updateWeight(x);
				updateWeight(x.left);
			}
		
		}
		return x;
	}

	public int height() {// get the height of a BST
		return height(root);
	}

	private int height(Node x) {// calculate the height of BST
		int z = 0;
		if (x == null)// if the tree is empty return 0
			return 0;
		else {// else calculate the heights of a left and right subtrees

			int left = height(x.left);
			int right = height(x.right);
			return z = Math.max(left, right) + 1;// find the max value between
													// them
		}
	}

	public int averageDepth() {// calculate the average distance from a root
									// to a node
		return (int)(sumOfDepths(root, 0) / numberOfNodes(root));
	}

	private double sumOfDepths(Node x, double depth) {// find a sum of distances
													// from a root to every node
		if (x == null)
			return 0;
		else {
			return depth + sumOfDepths(x.left, depth + 1)
					+ sumOfDepths(x.right, depth + 1);
		}
	}

	private double numberOfNodes(Node x) {// find the total number of node in a
											// BST
		if (x == null)
			return 0;
		else {
			return 1 + numberOfNodes(x.left) + numberOfNodes(x.right);
		}
	}

	@Override
	public Boolean isEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Key> iterator() {

		return null;
	}

	private Node rotL(Node h) {// rotate a node to the left
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		return x;
	}

	private Node rotR(Node h) {// rotate a node to the right
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		return x;
	}

	private void updateWeight(Node x) {
		x.Weight = 1; // no recursive descent !
		if (x.left != null)
			x.Weight += ((Node) x.left).Weight;
		if (x.right != null)
			x.Weight += ((Node) x.right).Weight;
	}

	@Override
	public void delete(Key key) {
		// TODO Auto-generated method stub

	}

}
