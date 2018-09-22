/*
* Name: Hongyu Dai (Nelson) 
* ID:	V00815253
* Date: 2016-02-27
* Filename: Node.java
* Details: CSC115 Assignment 3
* This class creats a Node whict is used by stack
*/ 
public class Node{
	Node next;
	String value;
	
public Node(){
		this.next=null;
		this.value="";
	}
public Node(String s){
		this.next=null;
		this.value=s;
	}		
}