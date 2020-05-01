package rest;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
 
public class XPathManager 
{
    public static String getResume(String movieName)
    {
    	String content = OmdServiceClient.searchMovieByTitle(movieName);
    			
    	OmdServiceClient.createFilexml(movieName,content);
    	
    	try {
	        //Build DOM
	 
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        factory.setNamespaceAware(true); // never forget this!
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(movieName.replace(" ", "_")+".xml");
	 
	        //Create XPath
	        
	        XPathFactory xpathfactory = XPathFactory.newInstance();
	        XPath xpath = xpathfactory.newXPath();
	        
	        XPathExpression expr = xpath.compile("//root/movie/@plot");
	        Object result = expr.evaluate(doc, XPathConstants.STRING);
	        String resume = (String) result;
	        
	        OmdServiceClient.deleteFilexml(movieName);
	        
	        return resume;
	        
    	}catch(Exception e){
        	System.out.println(e.getMessage());
        }
    	
    	return null;
    }
}