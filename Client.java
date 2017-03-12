

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Client {

	public static void main(String[] args) {
		
		Integer valueX = 1;
		Integer valueY = 2;		
		
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			
			Element rootElement = doc.createElement("measure");
			doc.appendChild(rootElement);
			
			Element rate = doc.createElement("rate");
			Attr x = doc.createAttribute("x");
			x.setValue("1");			
			rate.setAttributeNode(x);
			rootElement.appendChild(rate);
			
			Element rate2 = doc.createElement("rate");
			Attr y = doc.createAttribute("y");
			y.setValue("1");			
			rate2.setAttributeNode(y);
			rootElement.appendChild(rate2);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("src/measure.xml"));
			transformer.transform(source, result);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
