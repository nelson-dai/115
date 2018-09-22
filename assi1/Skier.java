/*
* Author: HONGYU DAI
* ID: V00815253
* Date: January 19, 2016
* Filename: Skier.java
* Details: CSC115 Assignment 1
*/ 


/*
 * The class Skier creats an object called skier that 
 * stores name of the skier and the level of the skier.
 */
public class Skier {	
	private String name; // the unique name of the skier
	private int level; // level of skill

/*
 * The constructor that initialize all the variables.
 * In this case, the variables are the name and level  
 * of the skier.
 */
	public Skier(String name) {
		this.name=name;
		this.level=0;
	}
/*
 * Creates a Skier with the given name and level.
 */
	public Skier(String name, int level) {
		this.name=name;
		this.level=level;
	}
 
 /*
 *Updates the name of the skier.
 */
	public void setName(String name) {
		this.name=name;
	}
/*
 *returns the updated name of the skier 
 *form the setName method
 */	
	public String getName() {		
		return this.name;
	}
	
/*
 *Updates the level of the skier since
 *there are only 5 levels(0-4) if the level 
 * is not between that range we set it to 0 
 */
	public void setLevel(int level) {
		if((level >= 0) && (level <= 4)){ //determine if the level is between 0-4
		this.level = level;
		}else {
			this.level = 0; 		     //set level to 0 since its not between 0-4
		}		
	}

/*
 *returns the updated level of the skier 
 *form the setlevel method
 */	
	public int getLevel() {	
			return this.level;		
	}
	
/*
 *Determines if the skier is already created 
 *by name
 */	
	public boolean equals(Skier other) {
		if(this.name.equals(other.getName())){
			return true;
		}else {
			return false;
		}
	}
/*
 *Out print the skier with name and level
 */	
	public String toString() {
		return this.name+" (level"+this.level+")";
	}

	public static void main(String[] args) {
		System.out.println("Testing the Skier class.");
		Skier s1 = null;
		try {
			s1 = new Skier("Howie SnowSkier", 4);
		} catch(Exception e) {
			System.out.println("Constructor not working.");
			e.printStackTrace();
			return;
		}
		if (!s1.getName().equals("Howie SnowSkier")) {
			System.out.println("Failed at test one.");
			return;
		}
		if (s1.getLevel() != 4) {
			System.out.println("Failed at test two.");
			return;
		}
		Skier s2 = new Skier("Baby Skier");
		if (!s2.getName().equals("Baby Skier")) {
			System.out.println("Failed at test three.");
			return;
		}
		if (s2.getLevel() != 0) {
			System.out.println("Failed at test four.");
			return;
		}
		s2.setName("Better Skier");
		s2.setLevel(1);
		if (!s2.getName().equals("Better Skier") || s2.getLevel() != 1) {
			System.out.println("Failed at test five.");
			return;
		}
		if (s1.equals(s2)) {
			System.out.println("Failed at test six.");
			return;
		}
		if (!s1.equals(new Skier("Howie SnowSkier",4))) {
			System.out.println("Failed at test seven.");
			return;
		}
		if (s1.toString().equals("Howie SnowSkier (level 0)")) {
			System.out.println("Failed at test eight.");
			System.out.println("Expected: Howie SnowSkier (level 0)");
			System.out.println("Got:      "+s1.toString());
			return;
		}
		System.out.println("All tests passed.");
	}
}
	