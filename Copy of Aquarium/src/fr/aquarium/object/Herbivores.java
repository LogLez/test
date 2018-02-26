package fr.aquarium.object;

import fr.aquarium.Algue;
import fr.aquarium.Aquarium;
import fr.aquariumenum.HerbivoresRace;
import fr.aquariumenum.Genre;

public class Herbivores extends Fish{

	private HerbivoresRace race;
	
	public Herbivores(String name, Genre sexe, HerbivoresRace race, int pv, int age, String sex) {
		super(name, sexe, pv, age, sex);
		this.race = race;
	}	
	public HerbivoresRace getRace() {return race;}
	public void setRace(HerbivoresRace race) {this.race = race;}
	
	
	public void eatAlgue(Algue algue){
		System.out.println(name + " vient de manger une algue");
		Aquarium.algues.remove(algue);
	}

}
