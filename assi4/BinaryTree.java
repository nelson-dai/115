/*
 * Name:Hongyu Dai
 * ID:V00815253
 * Date:2016-03-11
 * What:CSC 115 Assignment 4
 */
 
public class BinaryTree<E> {

	/* The root is inherited by any subclass
	 * so it must be protected instead of private.
	 */
	protected TreeNode<E> root;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() {
		this.root=null;
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) {
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() {
		return root;
	}

 	public int height(){
 		return getHeight(root);
 	}
 		
 	private int getHeight(TreeNode<E> node){
 		if (node==null){
 			return 0;
 		}
 		return 1 + Math.max(getHeight(node.left), getHeight(node.right));
 	}
 	
 	public boolean isEmpty(){
 		return root==null;
 	}
 	
 	public void makeEmpty(){
 		root=null;
 	}
}

	
