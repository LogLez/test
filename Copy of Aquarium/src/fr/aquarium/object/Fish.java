package fr.aquarium.object;

import fr.aquarium.Algue;
import fr.aquarium.Aquarium;
import fr.aquariumenum.Genre;

public class Fish {
	
	protected String name, sex;
	protected Genre genre;
	protected int pv, age;
	
	public Fish(String name, Genre genre, int pv, int age, String sex) {
		this.name = name;
		this.genre= genre;
		this.pv = pv;
		this.age = age;
		this.sex = sex;
	}
	

	public String getName() {	return name;}
	public void setName(String name) {		this.name = name;}
	public Genre getGenre() {		return genre;}
	public void setGenre(Genre genre) {	this.genre = genre;}
	public int getPV() {		return pv;}
	public void setPV(int pv) {	this.pv = pv;}
	public void addPv(int pv){ this.pv += pv; }
	public int getAge() {		return age;}
	public void setAge(int pv) {	this.age = pv;}
	public void addAge(int pv){ this.age += pv; }
	public String getSexe() {	return sex;}
	public void setSexe(String pv){ this.sex = pv; }
	
	
	public void eatFish(Object nourriture){
		
		if(nourriture instanceof Algue){
			Aquarium.alguesEaten((Algue) nourriture);
			
			if(((Algue) nourriture).getPV() <= 0){
				Aquarium.algues.remove(nourriture);
				System.out.println("une algue vient d'etre mangé entièrement");
			}else{
				System.out.println("un bout d'algue vient d'etre mangé");
			}
		}
		
		if(nourriture instanceof Fish){
			
			Aquarium.fishEaten((Fish) nourriture);
			
			if(((Fish) nourriture).getPV() <= 0){
				Aquarium.allFishs.remove(nourriture);
				System.out.println(((Fish) nourriture).getName() + " vient d'etre mangé");
				
				//SUPRESSIONS DE LA LISTE PROPICE A SON ESPECES
				if(nourriture instanceof Carnivores){Aquarium.carnivores.remove(nourriture);
				}else{Aquarium.herbivores.remove(nourriture);}
				//
				
			}else{
				System.out.println(((Fish) nourriture).getName() + " vient de se faire mordre");
			}
			
			
		}
	}
	
}
