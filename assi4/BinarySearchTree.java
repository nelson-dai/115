/*
 * Name:Hongyu Dai
 * ID:V00815253
 * Date:2016-03-11
 * What:CSC 115 Assignment 4
 */

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	public BinarySearchTree() {
		super();
	}
	
	//To help tree to insert when the tree is not empty
	private void helpInsert(E item){ 
		TreeNode<E> newNode= new TreeNode<E>(item);
		TreeNode<E> nNode = root;
		TreeNode<E> p;
		while(true){
			p = nNode;
			if(item.compareTo(nNode.item)<0){
				nNode=nNode.left;
				if(nNode==null){
					p.left=newNode;
					return;
				}
			}else{
				nNode=nNode.right;
				if(nNode==null){
					p.right=newNode;
					return;
				}
			}
		}
		
	}
	public void insert(E item){ 
		TreeNode<E> newNode= new TreeNode<E>(item);
		if(root==null){
			root=newNode;
		}else{
			helpInsert(item);
		}
		
	}
	
	//To search an item
	public E retrieve(E key){
		TreeNode<E> nNode=root;
		while(nNode.item!=key){
			if(key.compareTo(nNode.item)<0){
				nNode=nNode.left;
			}else{
				nNode=nNode.right;
			}
			if(nNode==null)
				return null;
		}
		return nNode.item;
	}
 


	/**
	 * Places all the items in the tree into a sorted list.
	 * @return the sorted list.
	 */
	public ArrayList<E> inOrder() {
		ArrayList<E> list = new ArrayList<E>();
		collectInOrder(list,root);
		return list;
	}


	private void collectInOrder(ArrayList<E> list, TreeNode<E> node) {
 		if(node != null){
 			collectInOrder(list,node.left);
 			list.add(node.item);
 			collectInOrder(list,node.right);
 		}
	}
	
	//To delete an item from the tree
	public void delete(E key){
		TreeNode<E> nNode = root;
		TreeNode<E> p = root;
		boolean left = true;
		while(nNode.item != key){
			p = nNode;
			if(key.compareTo(nNode.item)<0){
				left=true;
				nNode=nNode.left;
			}else{
				left=false;
				nNode=nNode.right;
			}
		}if(nNode==null)
			return;
	
	if(nNode.left==null && nNode.right==null){
		if(nNode==root){
			root=null;
		}else if(left){
			p.left=null;
		}else{
			p.right=null;
			}
		}else if(nNode.right==null){
			if(nNode==root)
				root=nNode.left;
			else if(left)
				p.left=nNode.left;
			else p.right=nNode.left;
		}
		else if(nNode.left == null){
			if(nNode==root)
				root = nNode.right;
			else if(left)
				p.left=nNode.right;
			else p.right=nNode.left;
			
		}
		else{
			TreeNode<E> r=helpR(nNode);
			if(nNode == root)
				root=r;
			else if(left)
				p.left=r;
			else p.left.right=r;
			r.left=nNode.left;
		}
	return;
	}
	
	//To help to replace or exchange two items when the item
	//we want to delete has child
	public TreeNode<E> helpR(TreeNode<E> reN){
		TreeNode<E> rp = reN;
		TreeNode<E> r = reN;
		TreeNode<E> nNode = reN.right;
		
		while(nNode!=null){
			rp=r;
			r=nNode;
			nNode=nNode.left;
		}
		if(r!=reN.right){
			rp.left=r.right;
			r.right=reN.right;
		}
		return r;
		
		
	}
	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {


		
		BinarySearchTree<PatientLocation> tree = new BinarySearchTree<PatientLocation>();
		PatientLocation p1 = new PatientLocation("Duck", "Donald", 338);
		PatientLocation p2 = new PatientLocation("Mouse", "Minnie",116);
		PatientLocation p3 = new PatientLocation("Dog", "Goofie",422);
		PatientLocation p4 = new PatientLocation("Newman","Alfred",607);
		PatientLocation p5 = new PatientLocation("Newman","Lada",001);
		PatientLocation p6 = new PatientLocation("Newman","AA",001);
		PatientLocation p7 = new PatientLocation("Hongyu","Dai",815253);
		PatientLocation p8 = new PatientLocation("Alen","Don",13);
		tree.insert(p1);
		tree.insert(p4);
		tree.insert(p3);
		tree.insert(p2);
		tree.insert(p5);
		tree.insert(p6);
		tree.insert(p7);
		tree.insert(p8);
		tree.delete(p2);
		System.out.println(tree.retrieve(p3));
		System.out.println(tree.retrieve(p7));
		System.out.println(" ");
		ArrayList<PatientLocation> list  = tree.inOrder();
		System.out.println(list);
		// draw the tree in its current state:
		DrawableBTree<PatientLocation> dbt = new DrawableBTree<PatientLocation>(tree);
		dbt.showFrame();

	}

}
