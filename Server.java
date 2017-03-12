import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Server {

	public static void main(String[] args) throws IOException{
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		try{
			ServerSocket sc = new ServerSocket(8020);
			Socket client = sc.accept();
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			Document doc = (Document)in.readObject();
			
	        doc.getDocumentElement().normalize();
	        
	        NodeList nList = doc.getElementsByTagName("rate");
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);	        	 
	        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) nNode;
	                dataset.addValue(Integer.parseInt(eElement.getAttribute("y")), "Solar", eElement.getAttribute("x"));
	        	}
	        }	        
	        
	        in.close();
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		JFreeChart lineChartObject = ChartFactory.createLineChart(
		         "March","Day","kW/h",
		         dataset,PlotOrientation.VERTICAL,
		         true,true,false);

		int width = 800; /* Width of the image */
		int height = 600; /* Height of the image */ 
		File lineChart = new File( "src/LineChart.jpeg" ); 
		ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
	}
}
