package data;

import java.util.ArrayList;
import java.util.List;

public class ResultSparQL_Titre {

	private List<String> producteurs = new ArrayList<>();
	private List<String> directeurs = new ArrayList<>();
	private List<String> acteurs = new ArrayList<>();
    
    public ResultSparQL_Titre() {
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
}
