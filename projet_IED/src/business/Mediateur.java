package business;

import java.util.ArrayList;

import data.Movie;
import data.ResultSparQL_Acteur;
import data.ResultSparQL_Titre;
import jdbc.JdbcRequest;
import jena.Requetes_Jena;
import rest.XPathManager;

public class Mediateur {
	
	public String resultTitre(String titre) {
		
		Movie movie = new Movie();
		JdbcRequest.getMovieInfo(movie, titre);
		Requetes_Jena req = new Requetes_Jena();
		
		ResultSparQL_Titre result = req.requeteTitre(titre);
		
		movie.setActeurs(result.getActeurs());
		movie.setDirecteurs(result.getDirecteurs());
		movie.setProducteurs(result.getProducteurs());
		
		String resume = XPathManager.getResume(titre);
		
		movie.setResume(resume);
		
		return formatReturn("title", movie);
	}
	
	public String resultActeur(String actName) {
		
		Requetes_Jena req = new Requetes_Jena();
		ResultSparQL_Acteur result = req.requeteActeur(actName);
		
		ArrayList<String> films = result.getFilms();
		ArrayList<Movie> movies = new ArrayList<>();
		
		for(String film: films) {
			Movie movie = new Movie();
			movie.setTitre(film);
			movie.setDirecteurs(result.getRealMap().get(film));
			movie.setProducteurs(result.getProducMap().get(film));
			JdbcRequest.getActorInfo(movie, film);
			movies.add(movie);
		}
		
		String resultString = "";
		boolean isFist = true;
		for(Movie movie: movies) {
			if(isFist) {
				isFist = false;
			}
			else {
				resultString += "\n------------------- \n";
			}
			resultString += formatReturn("actor", movie);
		}
		
		
		return resultString;
	}
	
	
	public String formatReturn(String req, Movie movie) {
		String result = "";
		switch(req) {
			case "title":
				result += "Titre: "+movie.getTitre();
				result += "\nDate: "+movie.getDate();
				result += "\nGenre: "+movie.getGenre();
				result += "\nDistributeur: "+movie.getDistributeur();
				result += "\nBudget: "+movie.getBudget();
				result += "\nRevenus US: "+movie.getRevenusUS();
				result += "\nRevenus Monde: "+movie.getRevenusMonde();
				
				result += "\nActeurs: ";
				for(String s: movie.getActeurs()) {
					result += s +", ";
				}
				result += "\nProducteurs: ";
				for(String s: movie.getProducteurs()) {
					result += s +", ";
				}
				result += "\nDirecteurs: ";
				for(String s: movie.getDirecteurs()) {
					result += s +", ";
				}

				result += "\nR�sum�: "+movie.getResume();
				break;
			case "actor":
				result += "Titre: "+movie.getTitre();
				result += "\nDate: "+movie.getDate();
				result += "\nGenre: "+movie.getGenre();
				result += "\nDistributeur: "+movie.getDistributeur();
				
				result += "\nProducteurs: ";
				for(String s: movie.getProducteurs()) {
					result += s +", ";
				}
				result += "\nDirecteurs: ";
				for(String s: movie.getDirecteurs()) {
					result += s +", ";
				}
				break;
			default:
				break;
		}
		return result;
	}
}
