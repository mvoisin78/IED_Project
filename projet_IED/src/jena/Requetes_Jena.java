package jena;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ResourceFactory;

import data.ResultSparQL_Acteur;
import data.ResultSparQL_Titre;

public class Requetes_Jena {
	
	public ResultSparQL_Titre requeteTitre(String titleStr) {
		ResultSparQL_Titre result = new ResultSparQL_Titre();
		ParameterizedSparqlString qs = new ParameterizedSparqlString(""
                + "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX dbo:     <http://dbpedia.org/ontology/>"
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                + ""
                + "\n"
                + "select distinct ?dname ?pname ?aname where {\n"
                + "  ?film a dbo:Film;\n"
                + "  	foaf:name ?label;\n"
                + " 	dbo:director ?d;\n"
                + "		dbo:producer ?p;\n"
                + "		dbo:starring ?a.\n"
                + "  ?d foaf:name ?dname.\n"
                + "  ?p foaf:name ?pname.\n"
                + "  ?a foaf:name ?aname.\n"
                + "}");

        Literal title = ResourceFactory.createLangLiteral(titleStr, "en");
        qs.setParam("label", title);

        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = exec.execSelect();
        List<String> producteurs = result.getProducteurs();
        List<String> directeurs = result.getDirecteurs();
        List<String> acteurs = result.getActeurs();
        while (results.hasNext()) {
        	QuerySolution soln = results.nextSolution();
        	if(!isInclude(directeurs, soln.get("dname").toString().split("@")[0])) {
        		directeurs.add(soln.get("dname").toString().split("@")[0]);
        	}
        	if(!isInclude(producteurs, soln.get("pname").toString().split("@")[0])) {
        		producteurs.add(soln.get("pname").toString().split("@")[0]);
        	}
        	if(!isInclude(acteurs, soln.get("aname").toString().split("@")[0])) {
        		acteurs.add(soln.get("aname").toString().split("@")[0]);
        	}
        }
        return result;
	}
	
	public ResultSparQL_Acteur requeteActeur(String actName) {
		ResultSparQL_Acteur result = new ResultSparQL_Acteur();
		ParameterizedSparqlString qs = new ParameterizedSparqlString(""
                + "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX dbo:     <http://dbpedia.org/ontology/>"
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                + ""
                + "\n"
                + "SELECT distinct ?ftitle ?dname ?pname WHERE {\n"
                + "  ?film a dbo:Film;\n"
                + "  	foaf:name ?ftitle ;\n"
                + " 	dbo:director ?d;\n"
                + "		dbo:producer ?p;\n"
                + "		dbo:starring ?actor.\n"
                + "  ?d foaf:name ?dname.\n"
                + "  ?p foaf:name ?pname.\n"
                + "  ?actor foaf:name ?label ."
                + "}");

        Literal act = ResourceFactory.createLangLiteral(actName, "en");
        qs.setParam("label", act);

        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = exec.execSelect();
        
        HashMap<String, ArrayList<String>> producMap = result.getProducMap();
        HashMap<String, ArrayList<String>> realMap = result.getRealMap();
        ArrayList<String> films = result.getFilms();
        
        while (results.hasNext()) {
        	QuerySolution soln = results.nextSolution();
        	
        	String titre = soln.get("ftitle").toString().split("@")[0];
        	String producteur = soln.get("pname").toString().split("@")[0];
        	String realisateur = soln.get("dname").toString().split("@")[0];
        	
        	if(!isInclude(films, titre)) {
        		films.add(titre);
        	}
        	
        	if(!producMap.containsKey(titre)){
        		ArrayList<String> al = new ArrayList<>();
        		al.add(producteur);
        		producMap.put(titre, al);
        	}
        	else {
        		if(!isInclude(producMap.get(titre),producteur)) {
        			producMap.get(titre).add(producteur);
        		}
        	}
        	
        	if(!realMap.containsKey(titre)){
        		ArrayList<String> al = new ArrayList<>();
        		al.add(realisateur);
        		realMap.put(titre, al);
        	}
        	else {
        		if(!isInclude(realMap.get(titre),realisateur)) {
        			realMap.get(titre).add(realisateur);
        		}
        	}
        	
        }
        return result;
	}
	
	private static boolean isInclude(List<String> list, String name) {
		for(String people: list) {
			if(people.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
}
