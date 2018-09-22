/*
* Name: Hongyu Dai (Nelson) 
* ID:	V00815253
* Date: 2016-02-27
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
* This class creats a reference based stack
*/
public class StringStack {

	private Node head;
	
	public StringStack(){  //constractor
		this.head=null;
	}

	public boolean isEmpty() { //empty stack
		return head==null;
	}

	public String pop() {  //delet the top one
	 if(isEmpty()){
		 throw new StackEmptyException("The stack is empty");
	 }else{
		 String e = head.value;
		 head=head.next;
		 return e;
	 }
	}

	public String peek() { //look at the top one
		if(isEmpty()){
			throw new StackEmptyException("The stack is empty");
		}else{
			return head.value;
		}
	}

	public void push(String item) { //add one on the top
		Node n = new Node(item);
		if(isEmpty()){
			head=n;
		}else{
			n.next=head;
			head=n;
		}
		
	}

	public void popAll() {  //delet everything in the stack
		head=null;
	}
    public static void main(String[] args) { //tester
		StringStack test = new StringStack();
		System.out.println("The stack is empty ");
		System.out.println(test.isEmpty());
				
		
		System.out.println("Adding items in to stack");
		test.push("haha");
		test.push("hoho");
		test.push("heihei");
		System.out.println("should show heiehi:" + test.peek());
		System.out.println("should show heiehi:" + test.pop());
		System.out.println("should show hoho:" + test.pop());
		System.out.println("should show haha:" + test.pop());
				
		
		System.out.println("Adding items in to stack then delet all");
		test.push("haha");
		test.push("hoho");
		test.push("heihei");
		test.popAll();
		System.out.println("should show true:" + test.isEmpty());
		
		
		
		//System.out.println("Testing Exception");
		//test.peek();
		//test.pop();
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
