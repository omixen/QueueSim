/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.net.URL;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import java.io.File;


/**
 *
 * @author Cody
 */
public class Config {
    
  
    private Document config;
    
    
    public Config(String url) throws Exception {
       File fXmlFile = new File(url);
  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	this.config = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	this.config.getDocumentElement().normalize();
        
    }

    public Hashtable stationTypes(){   
            return this.getElement("service_station_type");
    }
    
    public Hashtable customerTypes(){   
            return this.getElement("customer_types");
    }
    
    public Hashtable queues(){   
            return this.getElement("queue");
    }
    
    public Hashtable stations(){   
            return this.getStations();
    }
      
    public Hashtable settings(){   
            return this.getSettings("settings");
    }
        
    public Hashtable getElement(String type_name){
        
        NodeList nList = this.config.getElementsByTagName(type_name);
            Hashtable<String, String> types = new Hashtable<String, String>();   

                       for (int temp = 0; temp < nList.getLength(); temp++) {

                           Node nNode = nList.item(temp);
                           
                         

                           if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                   Element eElement = (Element) nNode;
                                   NodeList pList = (NodeList) eElement.getElementsByTagName("property");    
                                   
                                   if ( eElement.getAttribute("id").toString() != "") {
                                        String label1 = eElement.getAttribute("id").toString() + "." + "id";
                                        String value2 = eElement.getAttribute("id").toString();
                                        types.put(label1, value2); 
                                   }
                                   
                                    if ( eElement.getAttribute("type").toString() != "") {
                                        String label1 = eElement.getAttribute("id").toString() + "." + "type";
                                        String value2 = eElement.getAttribute("type").toString();
                                        types.put(label1, value2); 
                                   }
                                      for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {

                                            Node pNode = pList.item(temp2);

                                                Element pElement = (Element) pNode;


                                                String label = pElement.getAttribute("id");
                                                String value = pElement.getTextContent();

                                                types.put(label, value);    

                                      }                              
                           }
                   }

           return types;
    }
    
        public Hashtable getSettings(String type_name){
        
        NodeList nList = this.config.getElementsByTagName(type_name);
            Hashtable<String, String> types = new Hashtable<String, String>();   

                       for (int temp = 0; temp < nList.getLength(); temp++) {

                           Node nNode = nList.item(temp);
                           
                         

                           if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                   Element eElement = (Element) nNode;
                                   NodeList pList = (NodeList) eElement.getElementsByTagName("property");    
                                   
                                   if ( eElement.getAttribute("id").toString() != "") {
                                        String label1 = eElement.getAttribute("id").toString() + "." + "id";
                                        String value2 = eElement.getAttribute("id").toString();
                                        types.put(label1, value2); 
                                   }
                                   
                                    if ( eElement.getAttribute("type").toString() != "") {
                                        String label1 = eElement.getAttribute("id").toString() + "." + "type";
                                        String value2 = eElement.getAttribute("type").toString();
                                        types.put(label1, value2); 
                                   }
                                      for (int temp2 = 0; temp2 < pList.getLength(); temp2++) {

                                            Node pNode = pList.item(temp2);

                                                Element pElement = (Element) pNode;


                                                String label = "SETTINGS" + "." +pElement.getAttribute("id");
                                                String value = pElement.getTextContent();

                                                types.put(label, value);    

                                      }                              
                           }
                   }

           return types;
    }
    
    
    
    
    public Hashtable getStations(){
        
        NodeList nList = this.config.getElementsByTagName("service_station");
            Hashtable<String, String> types = new Hashtable<String, String>();   

                       for (int temp = 0; temp < nList.getLength(); temp++) {

                           Node nNode = nList.item(temp);
                           
                         

                           if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                   Element eElement = (Element) nNode;
                                   NodeList qList = (NodeList) eElement.getElementsByTagName("assigned_queue");    
                                   NodeList cList = (NodeList) eElement.getElementsByTagName("allowed_customer_type");
                                   
                                   if ( eElement.getAttribute("id").toString() != "") {
                                        String label1 = eElement.getAttribute("id").toString() + "." + "id";
                                        String value2 = eElement.getAttribute("id").toString();
                                        types.put(label1, value2); 
                                   }
                                   
                                    if ( eElement.getAttribute("type").toString() != "") {
                                        String label1 = eElement.getAttribute("id").toString() + "." + "type";
                                        String value2 = eElement.getAttribute("type").toString();
                                        types.put(label1, value2); 
                                   }
                                    for (int temp2 = 0; temp2 < cList.getLength(); temp2++) {
                                          Node pNode = cList.item(temp2);
                                              Element pElement = (Element) pNode;
                                              String label = eElement.getAttribute("id").toString() +  "." + pElement.getNodeName().toString()  +  "." + pElement.getAttribute("id").toString();
                                              String value = pElement.getTextContent();
                                              types.put(label, value);    
                                    }   
                                    
                                    for (int temp2 = 0; temp2 < qList.getLength(); temp2++) {
                                          Node qNode = qList.item(temp2);
                                              Element qElement = (Element) qNode;
                                              String label = eElement.getAttribute("id").toString() +  "." + qElement.getNodeName().toString()  +  "." + qElement.getAttribute("id").toString();
                                              String value = qElement.getTextContent();
                                              types.put(label, value);    
                                    }
                           }
                   }

           return types;
    }
    


    //    public String asXML(){
//        return this.config.asXML();
//    }
    

    
    
//    public ArrayList<String> types(){
//        //List list = this.config.selectNodes("//types");
//        ArrayList<String> types =new ArrayList();
//        Element root = config.getRootElement();
//           // iterate through child elements of root with element name "foo"
//        for ( Iterator i = root.elementIterator("types"); i.hasNext(); ) {
//            Element type = (Element) i.next();
//            
//            
//            
//             System.out.println(type.getName());
//            types.add(type.getStringValue());
//        }
//       
//       return types;
//    }
//    
//    
//    public void bar(Document document) throws DocumentException {
//
//        Element root = document.getRootElement();
//
//        // iterate through child elements of root
//        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
//            Element element = (Element) i.next();
//            // do something
//        }
//
//        // iterate through child elements of root with element name "foo"
//        for ( Iterator i = root.elementIterator( "foo" ); i.hasNext(); ) {
//            Element foo = (Element) i.next();
//            // do something
//        }
//
//        // iterate through attributes of root 
//        for ( Iterator i = root.attributeIterator(); i.hasNext(); ) {
//            Attribute attribute = (Attribute) i.next();
//            // do something
//        }
//     }
    
    
}
