package fr.aquarium;

public class Algue {
	
	protected int pv, age;
	
	public Algue(int pv, int age) {
		this.pv= pv;
		this.age = age;
	}
	
	public int getPV(){ return this.pv;	}
	public void setPV(int pv){ this.pv = pv; }
	public int getAge(){ return this.age;	}
	public void setAge(int age){ this.age = age; }
	public void addAge(int age){ this.age += age; }
}
