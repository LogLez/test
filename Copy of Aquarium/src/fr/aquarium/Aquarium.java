package fr.aquarium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import fr.aquarium.object.Carnivores;
import fr.aquarium.object.Files;
import fr.aquarium.object.Fish;
import fr.aquarium.object.Herbivores;
import fr.aquariumenum.CarnivoresRace;
import fr.aquariumenum.Genre;
import fr.aquariumenum.HerbivoresRace;


public class Aquarium {

	public static List<Algue> algues = new ArrayList<Algue>();
	public static List<Fish> allFishs = new ArrayList<Fish>();
	
	public static List<Carnivores> carnivores = new ArrayList<Carnivores>();
	public static List<Herbivores> herbivores = new ArrayList<Herbivores>();
		
	
	public static void main(String[] args){
		passTime();
		System.out.println("Il y a " + carnivores.size() + " carnivore dans l'aquarium !");
		System.out.println("Il y a " + herbivores.size() + " herbivores dans l'aquarium !");
		System.out.println("Il y a " + algues.size() + " algues dans l'aquarium !");

	}
	
	
	public static void addCarnivoresIntoAquarium(String name, Genre sexe, CarnivoresRace  race, String sex){
		Carnivores carv = new Carnivores(name, sexe, race, 5, 0, sex);
		carnivores.add(carv);
		allFishs.add(carv);
	}
	public static void addHerbivoresIntoAquarium(String name, Genre sexe, HerbivoresRace race, String sex){
		Herbivores herb = new Herbivores(name, sexe, race, 5, 0, sex);
		herbivores.add(herb);
		allFishs.add(herb);
	}
	
	public static void addAlguesIntoAquarium(){algues.add(new Algue(10, 0));}
	
	
	public static void passTime(){
		Map<Fish, Fish> death = new HashMap<Fish, Fish>();
		List<Fish> condamned = new ArrayList<Fish>();
		
		for(int i=0 ; i<21 ; i++){
			List<Fish> vieississement = new ArrayList<Fish>();
			addCarnivoresIntoAquarium("bernard", Genre.MASCULIN, CarnivoresRace.Mérou, "Hermaphrodite");
			addCarnivoresIntoAquarium("Bernadette", Genre.FEMININ, CarnivoresRace.POISSON_CLOWN, "Opportuniste");

			addHerbivoresIntoAquarium("bart", Genre.MASCULIN, HerbivoresRace.BAR, "Hermaphrodite");
			addHerbivoresIntoAquarium("bartette", Genre.FEMININ, HerbivoresRace.CARPE, "Mono");

			addAlguesIntoAquarium();
			
			
			System.out.println(allFishs.size() + " poissons dans l'aquarium");
			
///////////+1 AGE

			for(Fish fish : allFishs){fish.addAge(1);}
			for(Algue algue : algues){
				algue.addAge(1);
				if(algue.getAge() >= 20) algues.remove(algue);
			}

////////////
			for(Carnivores carv : carnivores){
				//SEULEMENT SIL A FAIM
				if(carv.getPV() <= 5){
					int r = new Random().nextInt(allFishs.size() );
					if(carv.equals(allFishs.get(r))){
						if(r > 1){
							death.put(carv, allFishs.get(1));
						}else{
							death.put(carv, allFishs.get(new Random().nextInt(allFishs.size() - 1) + 1));
						}
						
					}else{
						death.put(carv, allFishs.get(r));
					}
			
				}
			}
			
			for(Entry<Fish, Fish> deaths : death.entrySet()){
				deaths.getKey().eatFish(deaths.getValue());
			}
			
			for(Herbivores fish : herbivores){
				//SEULEMENT SIL A FAIM
				if(fish.getPV() <= 5){
					if(!algues.isEmpty()){fish.eatFish(algues.get(new Random().nextInt(algues.size()))); fish.addPv(3);}}}
			for(Entry<Fish, Fish> fish_eaten : death.entrySet()){
				fish_eaten.getKey().eatFish(fish_eaten.getValue());
				fish_eaten.getKey().addPv(5);
			}
			
			
			//1 TOUR PASSE MINIMUM
			if(i == 0) continue;
			
			
			if(i == 10){
				for(Fish fish : allFishs){
					if(i == 10 ){
						if(fish instanceof Carnivores){
			               if(!((Carnivores) fish).getRace().equals("Hermaphrodite")) continue;
			               if(fish.getGenre() == Genre.MASCULIN) fish.setGenre(Genre.FEMININ);
			               if(fish.getGenre() == Genre.FEMININ) fish.setGenre(Genre.MASCULIN);
						}else{
							if(!((Herbivores) fish).getRace().equals("Hermaphrodite")) continue;
				            if(fish.getGenre() == Genre.MASCULIN) fish.setGenre(Genre.FEMININ);
				             if(fish.getGenre() == Genre.FEMININ) fish.setGenre(Genre.MASCULIN);
						}
					}
				}
			}
			
			
			reproduction();
			
			
			for(Fish fish : allFishs){
				
				if(fish.getAge() >= 20){	vieississement.add(fish);}	
				fish.addPv(-1);
				
				if(fish.getPV() <= 0) condamned.add(fish);
			}
			
			for(Fish deaths : condamned){allFishs.remove(deaths); System.out.println(deaths.getName() + " vient de mourrir de faim !");}
			for(Fish deaths : vieississement){		
				System.out.println(deaths.getName() + " est mort de vieillissement !");
				if(death instanceof Carnivores) carnivores.remove(deaths); else herbivores.remove(deaths);
				allFishs.remove(deaths);
				
			}
			
			Files.saveToursIntoFile(allFishs, i, carnivores.size(), herbivores.size(), algues.size());

		}
		
		
		
	}
	
	
	public static void alguesEaten(Algue algue){algue.setPV(algue.getPV() - 2);	}
	public static void fishEaten(Fish fish){fish.setPV(fish.getPV() - 4);	}

	

	
	public static void reproduction(){
		Fish current = null;
		for(Fish fish : allFishs){

			
			if(current == null){
				if(fish instanceof Herbivores){current = (Herbivores)fish;continue;
				}else{current = (Carnivores)fish;continue;}
			}
			
			
			if(current.getGenre() != fish.getGenre()){

				switch(current.getSexe()) {
                case "Mono":
                	if(fish instanceof Carnivores){
                	   if(current instanceof Carnivores){
                		   if(((Carnivores) current).getRace().equals(((Carnivores) fish).getRace())){
                				  current = null;
                				  addCarnivoresIntoAquarium("Bébé", Genre.FEMININ, ((Carnivores) fish).getRace(), fish.getSexe());
                				  System.out.println("UN BEBE VIENT DE NAITRE");
                			   }
                		   }
                	   }else{                		
	                	   if(current instanceof Herbivores){
	                		   if(((Herbivores) current).getRace().equals(((Herbivores) fish).getRace())){
	                			   current = null;
	                			   addHerbivoresIntoAquarium("Bébé", Genre.MASCULIN, ((Herbivores) fish).getRace(), fish.getSexe());
	                			  System.out.println("UN BEBE VIENT DE NAITRE");
	                		   }
	                	   }
	                   }	                   	
                    break;
                case "Hermaphrodite":

                   if(fish instanceof Carnivores){

                	   if(current instanceof Carnivores){

                		   if(((Carnivores) current).getRace().equals(((Carnivores) fish).getRace())){

                				  current = null;
                				  addCarnivoresIntoAquarium("Bébé", Genre.FEMININ, ((Carnivores) fish).getRace(), fish.getSexe());
                				  System.out.println("UN BEBE VIENT DE NAITRE");

                			   }
                		   }
                	   }else{           

	                	   if(current instanceof Herbivores){
	                		   if(((Herbivores) current).getRace().equals(((Herbivores) fish).getRace())){
	                			   current = null;
	                			   addHerbivoresIntoAquarium("Bébé", Genre.MASCULIN, ((Herbivores) fish).getRace(), fish.getSexe());
	                				  System.out.println("UN BEBE VIENT DE NAITRE");

	                		   }
	                	   }
	                   }
                   break;
                case "Opportuniste":

                	if(fish instanceof Carnivores){
	                	   if(current instanceof Carnivores){
	                		   if(((Carnivores) current).getRace().equals(((Carnivores) fish).getRace())){
	                				  current = null;
	                				  addCarnivoresIntoAquarium("Bébé", Genre.FEMININ, ((Carnivores) fish).getRace(), fish.getSexe());
	                				  System.out.println("UN BEBE VIENT DE NAITRE");
	                			   }
	                		   }
	                	   }else{                		
		                	   if(current instanceof Herbivores){
		                		   if(((Herbivores) current).getRace().equals(((Herbivores) fish).getRace())){
		                			   current = null;
		                			   addHerbivoresIntoAquarium("Bébé", Genre.MASCULIN, ((Herbivores) fish).getRace(), fish.getSexe());
		                				  System.out.println("UN BEBE VIENT DE NAITRE");

		                		   }
		                	   }
	                	   }
                    break;
                }
			}
			
			
			
		}
		
	}
	
}
	