/*
* Author: HONGYU DAI
* ID: V00815253
* Date: January 22, 2016
* Filename: SkierList.java
* Details: CSC115 Assignment 1
*/ 

/*
 * The class SkierList creats a list of skiers
 * by adding new skiers and removing skiers. 
 */
public class SkierList { 
	private Skier[] skiers;                   // array storage for skiers
	private int count;                        // the number of skiers in the list
	private static final int INITIAL_CAP = 3; // the initial size of the empty skiers array.

/*
 * The constructor that initialize all the variables.
 * In this case, the variables are the count of how many
 * skiers in the list and  initialize the skiers array 
 * to hold INITIAL_CAP Skier objects. 
 */ 
	public SkierList() {
	    this.skiers = new Skier[INITIAL_CAP];
		this.count = 0;	  
	}

/*
 * Return the number of Skiers in the list
 * since the count is same as the size of the arry
 */
	public int size() {		
		return this.count;
	}
/*
 * Using the position index to look for the skier 
 * and return the skier, if the skier is not in the list
 * null is returned.
 */	
	public Skier get(int index) {	
		if(index>=0 && index<=this.count){   //checking if the index is iin the range
			return skiers[index];
		}else{                               //is not in the range
			return null;                  
		}		    		
	}

/*
 * Using the position index to look for the skier 
 * in order to remove the skier form the list and 
 * all the skiers after that move one position forward 
 * and count -1.
 */	
	public void remove(int index) {        
        if(index < this.count -1 ){            //determain if the skier is in the list
			this.skiers[index] = null;         //remove the skier by seting it to null
            for(int n=0;n<this.count-1-index;n++){ //move forward 
                this.skiers[index+n] = this.skiers[index+n+1];
            }
        }
        this.count--;                          //number of skier reduce one      
}
    
 /*
 * Add a new skier to the list if the arry is full
 * then double its size by creating a new arry with 
 * double size of the first one and copy all the elements 
 * from the first arry, then set the new arry as the defualt one
 */   
public void add(Skier skier) {  
        if(this.count < this.skiers.length){                     //derictly add the new skier if the arry is not full not full
            this.skiers[this.count] = skier;
        }else{                                                  // full...           
            Skier [] addSkier = new Skier[this.skiers.length*2];//creating new arry with double size
            for(int n=0;n<this.skiers.length;n++){             //copying...
                addSkier[n] = this.skiers[n];
            }
            addSkier[this.skiers.length] = skier;              //ste new arry as default
            this.skiers = addSkier;                            //add the new skier
        }        
        this.count++;                                          //increas the size by one
}

/*
 * Go throught the list to look for a skier if the
 * skier is in the list then return the position
 * of the skier. Otherwise -1 is returned
 */
	public int findSkier(Skier skier) {
        for(int n=0;n<this.skiers.length;n++){     //go thought the list
            if(skier.equals(this.skiers[n])){     //comparing
                return n;                        //reture position
            }
        }
				return -1;                     //not in the list
}
	/**
	 * Used primarily as a test harness for the class.
	 * @param args Not used.
 	 */
	public static void main(String[] args) {
		System.out.println("Testing the SkierList class.");
		SkierList list = null;
		try {
			list = new SkierList();
		} catch (Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		// Add some skiers.
		Skier s1 = new Skier("Daffy Duck", 0);
		list.add(s1);
		if (list.size() != 1) {
			System.out.println("Failed at test one.");
			return;
		}
		if (!list.get(0).equals(s1)) {
			System.out.println("Failed at test two.");
			System.out.println("The first skier in the list needs to be in index position 0");
			return;
		}
		if (list.findSkier(s1) == -1) {
			System.out.println("Failed at test three.");
			return;
		}
		Skier s2 = new Skier("Bugs Bunny", 4);
		list.add(s2);
		if (s2.getLevel() != 4) {
			System.out.println("Failed at test four.");
			return;
		}
		if (list.size() != 2) {
			System.out.println("Failed at test five.");
			return;
		}
		Skier tmp1 = list.get(0);
		Skier remaining;
		if (tmp1.equals(s1)) {
			remaining = s2;
		} else {
			remaining = s1;
		}
		list.remove(0);
		if (list.findSkier(remaining) == -1) {
			System.out.println("Failed at test six.");
			return;
		}
		if (list.size() != 1) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (!list.get(0).equals(remaining)) {
			System.out.println("Failed at test eight.");
			return;
		}
		list.remove(0);
		if (list.size() != 0) {
			System.out.println("Failed at test nine.");
			return;
		}
		System.out.println("Testing for expansion.");
		// note that the array has to expand in this test.
		// Creating an initial array with a length >= max is a failure.
		String prefix = "Skier";
		int max = 1000;
		try {
			for (int i=0; i<max; i++) {
				list.add(new Skier(prefix+i));
			}
		} catch (Exception e) {
			System.out.println("Failed at test 10.");
			e.printStackTrace();
			return;
		}
		for (int i=max-1; i>=0; i--) {
			if (list.findSkier(new Skier(prefix+i)) == -1) {
				System.out.println("Failed at test 11.");
				System.out.println("Unable to find skier: "+(prefix+i));
				return;
			}
		}
		System.out.println("All tests passed");
	}
}