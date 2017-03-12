

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

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
			
			
			for(int i = 1; i <= 31; i++){
				Element rate = doc.createElement("rate");
				Attr y = doc.createAttribute("y");
				valueY = (int) (Math.random()*100);
				y.setValue(valueY.toString());			
				rate.setAttributeNode(y);
				
				Attr x = doc.createAttribute("x");
				valueX = i;
				x.setValue(valueX.toString());			
				rate.setAttributeNode(x);						
				rootElement.appendChild(rate);
			}
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("src/measure.xml"));
			transformer.transform(source, result);
			
			Socket client = new Socket(InetAddress.getLocalHost(), 8020);
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(doc);
			out.flush();
			out.close();
			client.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
