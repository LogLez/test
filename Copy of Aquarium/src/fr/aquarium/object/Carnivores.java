package fr.aquarium.object;

import fr.aquarium.Aquarium;
import fr.aquariumenum.CarnivoresRace;
import fr.aquariumenum.Genre;

public class Carnivores extends Fish{
	
	private CarnivoresRace race;
	
	
	public Carnivores(String name, Genre sexe, CarnivoresRace race, int pv, int age, String sex) {
		super(name, sexe, pv, age, sex);
		this.race = race;
	}

	public CarnivoresRace getRace() {return race;}
	public void setRace(CarnivoresRace race) {this.race = race;}
	
	public void eatFish(Fish fish){
		System.out.println(name + " vient de manger " + fish.getName());
		Aquarium.allFishs.remove(fish); 
	}

	
}
