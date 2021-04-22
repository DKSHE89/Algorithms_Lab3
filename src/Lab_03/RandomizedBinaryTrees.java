package Lab_03;

import java.util.Iterator;
import java.util.Stack;

import Lab_03.BinarySearchTree.Node;

public class RandomizedBinaryTrees <Key extends Comparable,Val> extends AbstractST <Key,Val> implements Iterable<Key>  {
	
	protected class Node{
		Key key;
		Val val;
		Node left,right;
		
		Node(Key key, Val val){
			this.key = key;
			this.val = val;
		}
	}

	protected RNode root;
	

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
	
	private Node rotR ( Node h)
	{
		Node x = h.left ;
		h.left = x.right ;
		x.right = h;
		return x;
	}
	
	private Node rotL ( Node h)
	{
		Node x = h.right ;
		h.right = x.left ;
		x.left = h;
		return x;
	}
	
	private RNode putRoot ( RNode x, Key key , Val val)
	{
		if (x== null ) return new RNode (key , val );
		int cmp = key.compareTo (x.key );
		if ( cmp ==0) x. val = val ;
		else if (cmp <0) {
			x. left = putRoot ((( RNode )x. left ),key , val );
			x = (RNode) rotR((RNode)x);
		}
		else {
			x. right = putRoot ((( RNode )x. right ),key ,val );
			x = (RNode) rotL((RNode)x);
		}
		return x;
	}
	
	protected Node putRoot ( Node x, Key key , Val val )
	{
	if (x== null ) return new Node (key , val );
	int cmp = key.compareTo (x. key );
	if ( cmp ==0) x. val = val ;
	else if (cmp <0) {
	x. left = putRoot (x.left ,key , val );
	x = rotR (x);
	} else {
	x. right = putRoot (x.right ,key , val );
	x = rotL (x);
	}
	return x;
	}
	
	public void put ( Key key , Val val ) {
		root = putRBST ((( RNode ) root ),key , val );
		}
		private RNode putRBST ( RNode x, Key key , Val val )
		{
			if ( x== null ) 
				return new RNode (key , val );
			int cmp = key.compareTo (x. key );
			if ( cmp ==0) {
				x. val = val ;
				return x;
			}
		// flip coin whether to put it as root
		if ( Math . random () * ((( RNode )x).W +1) <1)
			return putRoot (x,key , val );
		// ok -- lost : does not become the root
		if (cmp <0) {
		   x. left = putRBST ((( RNode )x.left ),key , val );
		   //RECURSIVE ASCEND HAPPENS HERE
		}
		else {
		   x. right = putRBST ((( RNode )x.right ),key , val );
		 //RECURSIVE ASCEND HAPPENS HERE
		}
		
		updateW (x); // update the weights
		return x;
		}
		
	protected class RNode extends Node{
		int W; // weight of the tree
		RNode (Key key , Val val){
			super (key ,val );
				W =1;
			}
		}
	
	private void updateW ( RNode x){
		x.W =1; // no recursive descent !
		if (x. left != null ) 
			x.W +=(( RNode ) x.left ).W;
		if (x. right != null ) 
			x.W +=(( RNode ) x.right ).W;
	}

		public Boolean isEmpty() {
			if (root == null) return true;
			return false;
		}

		public Val get(Key key) {
			RNode x	= find(key);
			if	( x	!= null	) return x.val;	
			return null	;
		}
		
		private RNode find ( Key	key	) {
			RNode x = root ;	
			while (x !=	null ){
				int	cmp	= key. compareTo(x.key);
				if (cmp	== 0 )	return	x;
				else if( cmp<0)	x = (RNode) x.left;
				else x = (RNode) x.right;	
				}	
			return	null;
		}

		@Override
		public void delete(Key key) {
			
		}

		public Iterator<Key> iterator(){
			return new BSTIterator (); 
			}		
			
		private class BSTIterator implements Iterator <Key>{
			private Stack <Node> stack = new Stack <Node>();
			private void pushLeft (Node x) {
				while (x != null) {
					stack.push(x);		
					x =	x.left;
					}
				}
		BSTIterator() {
			pushLeft (root); 
			}

		public boolean hasNext (){
			return	!stack.isEmpty();
			}

		public void remove() {
			
		}		
		
		public Key next(){
			Node x = stack.pop();
			pushLeft( x.right);
			return x.key;
			}
		}
}
