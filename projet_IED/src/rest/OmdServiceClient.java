package rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OmdServiceClient {
	
	//APIKEY
	public static final String APIKEY = "f5230550";
	
	//URL POUR CHERCHER LE FILM PAR SON TITRE
	public static final String SEARCH_URL = "http://www.omdbapi.com/?t=TITLE&r=xml&apikey=APIKEY";
	
	public static String sendGetRequest(String requestUrl) {
		StringBuffer response = new StringBuffer();
		
		
		try {
			
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			
			String line;
			
			while((line = buffer.readLine())!= null) {
				response.append(line);
			}
			
			buffer.close();
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.toString();
		
	}
	
	
	public static String searchMovieByTitle(String title) {
		try {
			//Pour accepter titre de film avec espace
			title = URLEncoder.encode(title,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//remplace le titre et l'APIKEY dans l'URL
		String requestURL = SEARCH_URL.replaceAll("TITLE", title).replaceAll("APIKEY", APIKEY);
		return sendGetRequest(requestURL);
	}
	
	 @SuppressWarnings("unused")
	public static void createFilexml(String movieName,String content)
	    {
	  
	        try {
	  
	            // Recevoir le fichier
	        	movieName = movieName.replace(" ", "_");
	            File f = new File(movieName+".xml");
	  
	            // Créer un nouveau fichier
	            // Vérifier s'il n'existe pas
	            
	            if (f.createNewFile()) {
	            	Path path = Paths.get(movieName+".xml");
	            	byte[] bs = content.getBytes();
	            	Path writtenFilePath = Files.write(path, bs);
	            }
	            
	        }
	        catch (Exception e) {
	            System.err.println(e);
	        }
	    }
	 
	 public static void deleteFilexml(String movieName){
		 try{
			 movieName = movieName.replace(" ", "_");
	         Files.deleteIfExists(Paths.get(movieName+".xml")); 
	     } 
	     
		 catch(NoSuchFileException e) 
	     { 
	         System.out.println("No such file/directory exists"); 
	     }
	     catch(IOException e) 
	     { 
	         System.out.println("Invalid permissions."); 
	     } 
	 } 
}
