package fr.aquarium.object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Files {
	

	
	public static void saveToursIntoFile(List<Fish> entitys, int tour, int carnivores, int herbivores, int algues){
		File file = new File("tours");
		if(!file.exists())
			file.mkdir();
		try {
			PrintWriter pw = new PrintWriter(new File("tours\\tour" +tour+".poisson"));
			pw.write("=====TOUR " + tour + "========\n");
			pw.write("Il y a " +  carnivores + " carnivores dans l'aquarium \n");
			pw.write("Il y a "+  herbivores + " herbivores dans l'aquarium \n");
			pw.write("Il y a "+  algues + " algues dans l'aquarium \n");
			pw.write("========================\n");
			
			for(Fish fish : entitys){
				if(fish instanceof Carnivores){
					pw.write(fish.getName() + ", " + ((Carnivores) fish).getRace() + ", " + fish.getAge() + "\n");
				}else{
					pw.write(fish.getName() + ", " + ((Herbivores) fish).getRace() + ", " + fish.getAge() + "\n");
				}
			}
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
