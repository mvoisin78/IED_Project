package parsing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainParse {

	public static void main(String[] args) throws IOException {
		ArrayList<String> genres = new ArrayList<>();
		genres.add("Adventure");
		genres.add("Comedy");
		genres.add("Drama");
		genres.add("Action");
		genres.add("Thriller-or-Suspense");
		genres.add("Romantic-Comedy");
		
		for(String genre: genres) {
			FileWriter csvWriter = new FileWriter(genre+".csv");
			csvWriter.append("Name");
			csvWriter.append(";");
			csvWriter.append("Distributor");
			csvWriter.append(";");
			csvWriter.append("Genre");
			csvWriter.append("\n");
			
			for(int annee = 2000; annee<=2015;annee ++) {
				Document doc = Jsoup.connect("https://www.the-numbers.com/market/"+annee+"/genre/"+genre).get();
				Element table = doc.select("#page_filling_chart table").get(0); //select the first table.
				Elements rows = table.select("tr");
		
				//System.out.println(rows.get(1).text());
				for (int i = 1; i < rows.size()-2; i++) { //first row is the col names so skip it.
				    Element row = rows.get(i);
				    Elements cols = row.select("td");
				    
				    csvWriter.append(cols.get(1).text() +";"+ cols.get(3).text()+";"+genre);
				    csvWriter.append("\n");
				    System.out.println(cols.get(1).text() +"   "+ cols.get(3).text());
				}
			}
			csvWriter.flush();
			csvWriter.close();
		}
	}
	
	public static void test() throws IOException {
		Document doc = Jsoup.connect("https://www.the-numbers.com/market/2015/genre/Drama").get();
		System.out.println(doc.title());
		Elements newsHeadlines = doc.select("#page_filling_chart b a");
		for (Element headline : newsHeadlines) {
		  System.out.println(headline.attr("title")+ "    " + headline.absUrl("href") );
		}
	}
}
