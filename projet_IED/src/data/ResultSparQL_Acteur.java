package data;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultSparQL_Acteur {

	private ArrayList<String> films = new ArrayList<>();
	private HashMap<String, ArrayList<String>> producMap = new HashMap<String,ArrayList<String>>();
	private HashMap<String, ArrayList<String>> realMap = new HashMap<String,ArrayList<String>>();
    
	public ResultSparQL_Acteur() {
	}

	public HashMap<String, ArrayList<String>> getProducMap() {
		return producMap;
	}

	public void setProducMap(HashMap<String, ArrayList<String>> producMap) {
		this.producMap = producMap;
	}

	public HashMap<String, ArrayList<String>> getRealMap() {
		return realMap;
	}

	public void setRealMap(HashMap<String, ArrayList<String>> realMap) {
		this.realMap = realMap;
	}

	public ArrayList<String> getFilms() {
		return films;
	}

	public void setFilms(ArrayList<String> films) {
		this.films = films;
	}
	
	
}
