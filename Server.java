import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Server {

	public static void main(String[] args){
		
		try{
			File inputFile = new File("src/measure.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        
	        NodeList nList = doc.getElementsByTagName("rate");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);	        	 
	        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                System.out.println("x : " + eElement.getAttribute("x"));
	                System.out.println("y : " + eElement.getAttribute("y"));
	        	}
	        }	        
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
