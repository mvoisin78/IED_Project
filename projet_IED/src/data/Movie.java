package data;

import java.util.ArrayList;
import java.util.List;

public class Movie {

	private String titre;
	private String date;
	private String genre;
	private String distributeur;
	private String budget;
	private String revenusUS;
	private String revenusMonde;
	
    private List<String> producteurs = new ArrayList<>();
    private List<String> directeurs = new ArrayList<>();
    private List<String> acteurs = new ArrayList<>();
    
	private String resume;
	
	public void movie() {
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDistributeur() {
		return distributeur;
	}

	public void setDistributeur(String distributeur) {
		this.distributeur = distributeur;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getRevenusUS() {
		return revenusUS;
	}

	public void setRevenusUS(String revenusUS) {
		this.revenusUS = revenusUS;
	}

	public String getRevenusMonde() {
		return revenusMonde;
	}

	public void setRevenusMonde(String revenusMonde) {
		this.revenusMonde = revenusMonde;
	}

	public List<String> getProducteurs() {
		return producteurs;
	}

	public void setProducteurs(List<String> producteurs) {
		this.producteurs = producteurs;
	}

	public List<String> getDirecteurs() {
		return directeurs;
	}

	public void setDirecteurs(List<String> directeurs) {
		this.directeurs = directeurs;
	}

	public List<String> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<String> acteurs) {
		this.acteurs = acteurs;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		String value ="";
		value += "Titre: "+titre;
		value += "\nDate: "+date;
		value += "\nGenre: "+genre;
		value += "\nDistributeur: "+distributeur;
		value += "\nBudget: "+budget;
		value += "\nRevenus US: "+revenusUS;
		value += "\nRevenus Monde: "+revenusMonde;
		
		value += "\nActeurs: ";
		for(String s: acteurs) {
			value += s +", ";
		}
		value += "\nProducteurs: ";
		for(String s: producteurs) {
			value += s +", ";
		}
		value += "\nDirecteurs: ";
		for(String s: directeurs) {
			value += s +", ";
		}

		value += "\nRésumé: "+resume;
		
		return value;
	}
	
	
	
	
	
}
